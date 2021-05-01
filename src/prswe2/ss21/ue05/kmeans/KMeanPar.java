package prswe2.ss21.ue05.kmeans;

import java.awt.Color;
import java.sql.Time;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

import prswe2.ss21.ue05.inout.In;
import prswe2.ss21.ue05.inout.Out;
import prswe2.ss21.ue05.inout.Window;

/**
 * Sequential k-mean clustering algorithm.
 */
@SuppressWarnings("unused")
public class KMeanPar {

    /**
     * Number of data points
     */
    private static final int N = 10000000;

    /**
     * Number of clusters
     */
    private static final int K = 7;

    /**
     * Max values of x and y coordinates of data points
     */
    private static final int SIZE = 600;

    /**
     * Random number generator
     */
    private static final Random RAND = new Random();

    private static final int N_EXPERIMENTS = 20;


    /**
     * Creates random data points.
     * x and y coordinates are randomly chosen between 0 and {@link SIZE}.
     *
     * @param n number of points
     * @return array of n data points
     */
    private static DataPoint[] createRandomData(int n) {
        DataPoint[] points = new DataPoint[n];
        for (int i = 0; i < n; i++) {
            points[i] = new DataPoint(RAND.nextInt(SIZE), RAND.nextInt(SIZE));
        }
        return points;
    }

    /**
     * Main method to start execution
     *
     * @param args not used
     */
    public static void main(String[] args) {
        KMeanPar kMeansAlgo = new KMeanPar(createRandomData(N), K);
        int runs = 10;
        Long sum = 0l;
        for(int i = 0; i < runs; i++) {
            Long start = System.nanoTime();
            kMeansAlgo.cluster();
            Long end = System.nanoTime();
            sum += (end - start);
        }
        System.out.println("Elapsed Time in ms: " + sum * Math.pow(10, 6));
    }

    /**
     * Number of clusters
     */
    private final int k;

    /**
     * The data points to cluster
     */
    private final DataPoint[] data;

    /**
     * Points representing the cluster centroids
     */
    private Point[] centroids;

    /**
     * Constructor initializing the data points to cluster
     * and the number of clusters.
     *
     * @param data the data points
     * @k the number of clusters
     */
    public KMeanPar(DataPoint[] data, int k) {
        super();
        this.k = k;
        this.data = data;
        centroids = new Point[k];
    }

    /**
     * Does the clustering of the data points.
     * As a result, the data points get assigned ids for the clusters.
     */
    public void cluster() {
        doInitialClustering();
        computeCentroids();
        // disable output for time measurement
        // output();
        boolean stable = false;
        while (!stable) {
            stable = doNewClustering();
            computeCentroids();
            // disable output for time measurement
            // output();
        }
        Out.println("Completed");
    }

    /**
     * Does a random initial clustering of the data points into k clusters.
     * As a result, the data points get assigned initial ids for clusters.
     */
    private void doInitialClustering() {
        for (int i = 0; i < data.length; i++) {
            data[i].cluster = RAND.nextInt(k);
        }
    }

    /* TODO
    Erzeugen Sie einen eigenen ExecutorService für die parallele Verarbeitung und stellen Sie diesen in einer
    statischen Variablen bereit. Wählen Sie einen geeigneten Executor.
 */
    // Runtime.getRuntime().availableProcessors() * 2 gives (Cores + HyperThreading) * 2, in my case with 6 Cores+HT, this yields 24
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    /* TODO
    Implementieren Sie eine innere Task-Klasse ClusterTask, welche Callable<Boolean> implementiert
    und zwei Felder from und to definiert. Die Methode call soll das Clustering im Bereich from bis to
    (exklusiv) ausführen. Der Boolesche Rückgabewert gibt an, ob sich im Clustering eine Änderung ergeben
    hat oder die Cluster für die Datenpunkte im Bereich stabil sind.
    */
    private class ClusterTask implements Callable<Boolean> {
        private int from;
        private int to;

        public ClusterTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Boolean call() throws Exception {
            boolean stable = true;
            // TODO: parallelize using thread pool ---------------------
            for (int i = from; i < to; i++) {
                int closestCluster = getClosestCluster(data[i]);
                if (stable && data[i].cluster != closestCluster) {
                    stable = false;
                }
                data[i].cluster = closestCluster;
            }
            return stable;
        }
    }

    /**
     * Does a new clustering of the data points.
     *
     * @return true, if there was no change in the clustering, false otherwise.
     */
    private boolean doNewClustering() {
        /* TODO
        Legen Sie P ClusterTask-Objekte an, wobei P die gewünschte Parallelität ist (als Wert für P ist das
        Doppelte ihrer Prozessorkerne sinnvoll). Jeder ClusterTask soll einen etwa gleich großen Teil der
        Datenobjekte behandeln.
         */
        int nClusterTasks = Runtime.getRuntime().availableProcessors() * 2;
        int arrayChunkSize = (data.length + nClusterTasks - 1) / nClusterTasks;
        ClusterTask[] clusterTasks = new ClusterTask[nClusterTasks];
        Future<Boolean>[] results = new Future[nClusterTasks];
        for (int i = 0; i < nClusterTasks; i++) {
            //System.out.println(data.length + ", from: " + i * arrayChunkSize + ", to " + (i + 1) * arrayChunkSize);
            clusterTasks[i] = new ClusterTask(i * arrayChunkSize, (i + 1) * arrayChunkSize > data.length ? data.length : (i + 1) * arrayChunkSize);
            results[i] = executorService.submit(clusterTasks[i]);
        }

        /* TODO
        In der Methode doNewClustering sollen die ClusterTasks dem Executor zur Ausführung übergeben
        werden. Warten Sie auf die Beendigung der asynchronen Ausführung der Tasks. Das Ergebnis der
        Methode ist dann die Und-Verknüpfung der Ergebnisse der einzelnen Tasks (stable) */
        for (Future<Boolean> f : results) {
            try {
                if (!f.get()) {
                    return false;
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Not properly executed!");
            }
        }
        return true;
    }

    /* TODO
    Implementieren Sie eine innere Klasse RecursiveSumTask, die von RecursiveTask<int[][]> ableitet
    (Ergebnis ist also Summen für x-, y-Koordinaten und Anzahl wie oben). RecursiveSumTask arbeitet
    wieder mit zwei Indizes from und to, die den zu behandelnden Datenbereich bestimmen. */

    private class RecursiveSumTask extends RecursiveTask<int[][]> {

        private static final int THRESHOLD = 8;
        private int sums[][];
        private static int from;
        private static int to;

        public RecursiveSumTask(int[][] sums, int from, int to) {
            this.sums = sums;
            this.from = from;
            this.to = to;
        }

        /* TODO
        In der Methode compute soll nun nach dem Prinzip Divide-Conquer die Summenbildung entweder
        sequentiell oder ab einem bestimmten Threshold durch Abspalten von zwei Subtasks und anschließenden
        Zusammenführen der Teilergebnisse berechnet werden. Definieren Sie eine Konstante für den Threshhold
        und überlegen Sie sich einen sinnvollen Wert. */

        @Override
        protected int[][] compute() {
            int[][] newSums = new int[sums[0].length][sums[1].length];
//            if (data.length < THRESHOLD) {
                newSums = computePartialSums(sums, from, to);
//            } else {
//                int middle = data.length / 2;
//                RecursiveSumTask sumTaskOne = new RecursiveSumTask(sums, 0, middle);
//                RecursiveSumTask sumTaskTwo = new RecursiveSumTask(sums, middle, data.length);
//
//                sumTaskOne.fork();
//                sumTaskTwo.fork();
//
//                System.arraycopy(sumTaskOne.join(), 0, newSums, 0, middle);
//                System.arraycopy(sumTaskTwo.join(), middle, newSums, middle, data.length);
//            }
            return newSums;
        }

        protected int[][] computePartialSums(int[][] partSums, int from, int to) {
            for (int i = from; i < to; i++) {
                sums[0][data[i].cluster] += data[i].x;
                sums[1][data[i].cluster] += data[i].y;
                sums[2][data[i].cluster]++;
            }
            return partSums;
        }

    }


    /**
     * Computes the cluster centroids for the current clustering.
     * Result is are new points in the array {@link centroids}.
     */
    private void computeCentroids() {
        /* TODO
        Starten Sie die parallele Summenbildung in der Methode computeCentroids.*/

        // sums[0][j] sum of x-coordiantes of points in cluster j
        // sums[1][j] sum of y-coordiantes of points in cluster j
        // sums[2][j] number of points in cluster j
        int[][] sums = new int[3][k];


        // TODO: parallelize with fork-join recursive tasks ------------
//        for (int i = 0; i < data.length; i++) {
//            sums[0][data[i].cluster] += data[i].x;
//            sums[1][data[i].cluster] += data[i].y;
//            sums[2][data[i].cluster]++;
//        }

        RecursiveSumTask rst = new RecursiveSumTask(sums, 0, data.length);
        sums = ForkJoinPool.commonPool().invoke(rst);

        // -------------------------------------------------------------
        for (int j = 0; j < centroids.length; j++) {
            if (sums[2][j] != 0) {
                centroids[j] = new Point(sums[0][j] / sums[2][j],
                        sums[1][j] / sums[2][j]);
            }
        }
    }

    /**
     * Computes the cluster with the centroid closest to the given data point.
     *
     * @param p the data point
     * @return the index of the closest cluster
     */
    private int getClosestCluster(DataPoint p) {
        int minCluster = -1;
        double minDist = Double.MAX_VALUE;
        for (int c = 0; c < centroids.length; c++) {
            double dist = computeDist(p, centroids[c]);
            if (dist < minDist) {
                minCluster = c;
                minDist = dist;
            }
        }
        return minCluster;
    }

    /**
     * Computes the square of the distance of two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the square of the distance
     */
    private double computeDist(Point p1, Point p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    // ---- output -----------------------------------------------------

    /**
     * Constant for delaying the output in ms
     */
    private static final int DELAY = 1000; // ms

    /**
     * Colors used for showing the clustering of data points in {@link inout.Window} output.
     */
    private static final Color[] COLORS =
            {Color.RED, Color.BLUE, Color.CYAN, Color.GREEN,
                    Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.BLACK,
                    Color.DARK_GRAY, Color.YELLOW, Color.PINK};

    /**
     * Puts out the current clustering by
     * <ul>
     *   <li> drawing data points and cluster centroids on {@link inout.Window} </li>
     *   <li> printing the cluster centroids </li>
     *   <li>  delaying the computation </li>
     * </ul>
     */
    private void output() {
        draw();
        printClusters();
        delay();
    }

    /**
     * Draws the data points and cluster centroids on {@link inout.Window}.
     */
    private void draw() {
        Window.clear();
        for (int i = 0; i < data.length; i++) {
            Window.fillCircle(data[i].x, data[i].y, 2,
                    COLORS[data[i].cluster % COLORS.length]);
        }
        for (int c = 0; c < centroids.length; c++) {
            Window.drawCircle(centroids[c].x, centroids[c].y, 4, COLORS[c % COLORS.length]);
        }
    }

    /**
     * Prints out the cluster centroids.
     */
    private void printClusters() {
        for (Point cc : centroids) {
            Out.print(cc.toString() + " ");
        }
        Out.println();
    }

    /**
     * Delays the computation for allowing inspection of intermediate results.
     * Delay is defined by constant {@link Delayed}.
     * Alternatively, a user input may be used to stop computation.
     */
    private void delay() {
        // Uncomment for controlling steps by users
//		Out.print("next step: ");
//		In.readLine(); // request any input from user to continue
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
        }
    }
}
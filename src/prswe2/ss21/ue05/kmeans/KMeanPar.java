package prswe2.ss21.ue05.kmeans;

import java.awt.Color;
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
    private static final int N = 1000000;

    /**
     * Number of clusters
     */
    private static final int K = 10;

    /**
     * Max values of x and y coordinates of data points
     */
    private static final int SIZE = 600;

    /**
     * Random number generator
     */
    private static final Random RAND = new Random();

    private static final int N_EXPERIMENTS = 20;

    // Runtime.getRuntime().availableProcessors() * 2 gives (Cores + HyperThreading) * 2, in my case with 6 Cores+HT, this yields 24
    private static final int N_THREADS = Runtime.getRuntime().availableProcessors() * 2;

    // set true for output, false for time measurement
    private static final boolean OUTPUT = false;

    // set true to enable user interaction
    private static final boolean USER_INTERACTION = false;

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
        int runs = 15;
        Long sum = 0l;
        for (int i = 0; i < runs; i++) {
            Long start = System.nanoTime();
            kMeansAlgo.cluster();
            Long end = System.nanoTime();
            if (i >= 5) { // 5 runs for warmup then add up 10 runs
                sum += (end - start);
            }
        }
        System.out.println("Elapsed Time for 10 runs after 5 warmup runs (warmup excluded) with N=" + N + ", K= " + K + " in ms: " + sum / Math.pow(10, 6));
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
        if (OUTPUT) {
            output();
        }
        boolean stable = false;
        while (!stable) {
            stable = doNewClustering();
            computeCentroids();
            if (OUTPUT) {
                output();
            }
        }
        if (OUTPUT) {
            Out.println("Completed");
        }
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

    private static final ExecutorService executorService = Executors.newWorkStealingPool(N_THREADS);

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
        int arrayChunkSize = (data.length + N_THREADS - 1) / N_THREADS;
        ClusterTask[] clusterTasks = new ClusterTask[N_THREADS];
        Future<Boolean>[] results = new Future[N_THREADS];
        for (int i = 0; i < N_THREADS; i++) {
            clusterTasks[i] = new ClusterTask(i * arrayChunkSize, (i + 1) * arrayChunkSize > data.length ? data.length : (i + 1) * arrayChunkSize);
            results[i] = executorService.submit(clusterTasks[i]);
        }
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

    public class RecursiveSumTask extends RecursiveTask<int[][]> {

        private final int from;
        private final int to;
        // since threads/tasks also have a cost, do not split under 1000 values, works faster sequentially
        private static final int THRESHOLD = 1000;

        public RecursiveSumTask(int from, int to) {
            super();
            this.from = from;
            this.to = to;
        }

        @Override
        protected int[][] compute() {
            if (to - from < THRESHOLD) {
                return computePartial(from, to);
            } else {
                int middle = (from + to) / 2;
                RecursiveSumTask sumTaskOne = new RecursiveSumTask(from, middle + 1);
                RecursiveSumTask sumTaskTwo = new RecursiveSumTask(middle + 1, to);
                sumTaskOne.fork();
                sumTaskTwo.fork();
                return merge(sumTaskOne.join(), sumTaskTwo.join());
            }
        }

        private int[][] computePartial(int from, int to) {
            int[][] partSums = new int[3][k];
            for (int i = from; i < to; i++) {
                partSums[0][data[i].cluster] += data[i].x;
                partSums[1][data[i].cluster] += data[i].y;
                partSums[2][data[i].cluster]++;
            }
            return partSums;
        }

        private static int[][] merge(int[][] one, int[][] two) {
            for (int i = 0; i < one.length; i++) {
                for (int j = 0; j < one[i].length; j++) {
                    one[i][j] += two[i][j];
                }
            }
            return one;
        }
    }

    /**
     * Computes the cluster centroids for the current clustering.
     * Result is are new points in the array {@link centroids}.
     */
    private void computeCentroids() {
        int[][] sums = new int[3][k];
        RecursiveSumTask rst = new RecursiveSumTask(0, data.length);
        sums = ForkJoinPool.commonPool().invoke(rst);

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
        if (USER_INTERACTION) {
            Out.print("next step: ");
            In.readLine(); // request any input from user to continue
        }
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
        }
    }
}
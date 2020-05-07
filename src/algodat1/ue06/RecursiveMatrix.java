package algodat1.ue06;

public class RecursiveMatrix {

    public static final int m = 10; // rows
    public static final int n = 9; // cols

    public char[][] L; // labyrinth
    public char[][] V; // visited

    public RecursiveMatrix() {
        // create matrices
        this.L = new char[m][n];
        this.V = new char[m][n];

        // fill matrices with '.'
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                L[r][c] = '.';
                V[r][c] = '.';
            }
        }
        // fill matrices with Es and Xs
        this.setLPostion(0, 6, 'X');
        this.setLPostion(1, 2, 'X');
        this.setLPostion(1, 6, 'X');
        this.setLPostion(2, 2, 'X');
        this.setLPostion(2, 7, 'X');
        this.setLPostion(3, 3, 'X');
        this.setLPostion(3, 6, 'X');
        this.setLPostion(4, 0, 'X');
        this.setLPostion(4, 2, 'E');
        this.setLPostion(5, 2, 'X');
        this.setLPostion(5, 7, 'E');
        this.setLPostion(5, 8, 'X');
        this.setLPostion(6, 7, 'X');
        this.setLPostion(7, 2, 'X');
        this.setLPostion(8, 4, 'X');
        this.setLPostion(8, 5, 'X');
        this.setLPostion(8, 8, 'E');
        this.setLPostion(9, 2, 'E');
        this.setLPostion(9, 3, 'X');
        this.setLPostion(9, 7, 'X');
        this.setLPostion(9, 8, 'X');
    }

    public static void main(String[] args) {
        int i = 0; // start row
        int j = 0; // start col

        RecursiveMatrix matrix = new RecursiveMatrix();

        matrix.printL();
        System.out.println();
        matrix.printV();
        System.out.println(nCells(matrix.L, m, n, 0, 0, 0));
    }

    public static boolean nCells(char[][] L, int m, int n, int i, int j, int len) {


        return false;
    }

    protected void setLPostion(int row, int col, char c) {
        this.L[row][col] = c;
    }

    protected char getLPosition(int row, int col) {
        return this.L[row][col];
    }

    protected void setVPostion(int row, int col, char c) {
        this.V[row][col] = c;
    }

    protected char getVPosition(int row, int col) {
        return this.V[row][col];
    }

    protected void printL() {
        System.out.println("  |----------------------------|");
        System.out.print("  |");
        for (int c = 0; c < n; c++) {
            System.out.print(" " + (c) + " ");
        }
        System.out.println(" |");
        System.out.println("  |----------------------------|");
        for (int r = 0; r < m; r++) {
            System.out.print(r + " |");
            for (int c = 0; c < n; c++) {
                System.out.print(" " + getLPosition(r, c) + " ");
            }
            System.out.println(" | ");
        }
        System.out.println("  |----------------------------|");
    }

    protected void printV() {
        System.out.println("  |----------------------------|");
        System.out.print("  |");
        for (int c = 0; c < n; c++) {
            System.out.print(" " + (c) + " ");
        }
        System.out.println(" |");
        System.out.println("  |----------------------------|");
        for (int r = 0; r < m; r++) {
            System.out.print(r + " |");
            for (int c = 0; c < n; c++) {
                System.out.print(" " + getVPosition(r, c) + " ");
            }
            System.out.println(" | ");
        }
        System.out.println("  |----------------------------|");
    }
}
package swe1.ws17.ue05;// Arrays only needed if alternate printing is used (method printMatrix)
// please ignore when rating this homework, only for reference and learning purposes
// import java.util.Arrays;

import swe1.input.Input;

public class Grid {
    public static void main(String[] args) {
// declare variables
        int rows = 5;
        int columns = 5;
        int userrow = 0;
        int usercolumn = 0;
        char usercharacter = '*';
        char anotherinput = '*';
// initialize grid
        char grid[][] = new char[rows][columns];
        grid = initializeArray(grid, rows, columns);
// loop userinputs		
        do {
            userrow = inputRow(userrow);
            usercolumn = inputColumn(usercolumn);
            usercharacter = inputCharacter(usercharacter);
            grid[userrow][usercolumn] = usercharacter;
            printMatrix(grid, rows, columns);
            anotherinput = inputAnotherinput(anotherinput);
        } while (anotherinput == 'y');
    }

    // initialize array
    public static char[][] initializeArray(char grid[][], int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            grid[i][0] = '*';
            for (int j = 0; j < columns; j++) {
                grid[i][j] = '*';
            }
            ;
        }
        ;
        return grid;
    }

    // input row
    public static int inputRow(int userrow) {
        do {
            System.out.print("Insert row (0-4): ");
            userrow = Input.readInt();
        } while (userrow < 0 || userrow > 4);
        return userrow;
    }

    // input column
    public static int inputColumn(int usercolumn) {
        do {
            System.out.print("Insert column (0-4): ");
            usercolumn = Input.readInt();
        } while (usercolumn < 0 || usercolumn > 4);
        return usercolumn;
    }

    // input character
    public static char inputCharacter(char usercharacter) {
        System.out.print("Insert character: ");
        usercharacter = Input.readChar();
        return usercharacter;
    }

    // another input?
    public static char inputAnotherinput(char anotherinput) {
        do {
            System.out.print("Do you want to enter another character (y...yes, n...no): ");
            anotherinput = Input.readChar();
        } while (anotherinput != 'y' && anotherinput != 'n');
        return anotherinput;
    }

    // print matrix (grid)
    public static void printMatrix(char[][] grid, int rows, int columns) {
// 		please ignore the different print commands when rating this homework
//		they're only for reference and learning purposes
//		System.out.println(Arrays.deepToString(grid));
//		System.out.println(Arrays.deepToString(grid).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
package swe1.ws17.ue05;

import java.util.Random;
import java.util.Arrays;

import swe1.input.Input;

public class Lottery {

    private static final int NUMBERS_PER_TIP = 2; // defines the numbers per tip
    private static final int MAX_VALUE = 30; // defines the maximum value of any tip number
    private static final int MAX_TIPS = 10; // defines the number of tips which can be entered

    private static final boolean ENABLE_RANDOM_DRAW = true; // activates random draw simulation

    public static void main(String[] args) {
        int[][] lottery;    //container for entered tips
        int tipCount = 0;    //registered tips count
        int action = 1;

        // TODO: initialize array
        lottery = new int[MAX_TIPS][NUMBERS_PER_TIP];

        while (action != 0) {
            System.out.println("Main Menu:");
            System.out.println("  1) Show all tips");
            System.out.println("  2) Register tip");
            System.out.println("  3) Simulate lottery draw");
            System.out.println("  0) Exit");
            System.out.print("Enter Menu Function: ");
            action = Input.readInt();

            switch (action) {
                // TODO: define cases for printing tips and registering tips
                case 1:
                    printLotteryTips(lottery, tipCount);
                    break;
                case 2:
                    if (tipCount >= MAX_TIPS) {
                        System.out.println("Maximum number of tips (" + MAX_TIPS + ") reached, no more tips allowed!");
                    } else {
                        enterTip(lottery, tipCount);
                        tipCount = tipCount + 1;
                    }
                    break;
                case 3:
                    int[] draw;
                    if (ENABLE_RANDOM_DRAW)
                        draw = simulateDraw();
                    else {
                        //use index+1 for the numbers on the tip
                        draw = new int[NUMBERS_PER_TIP];
                        for (int i = 0; i < draw.length; i++) {
                            draw[i] = (i + 1) % MAX_VALUE;
                        }
                    }
                    //TODO: call printWinner method
                    printWinner(lottery, draw, tipCount);
                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Function not defined");
                    break;
            }
        }
    }

    private static void enterTip(int[][] lottery, int tipCount) {
        System.out.printf("Please insert numbers for your tip <1-%d>!\n", MAX_VALUE);
        for (int i = 0; i < lottery[0].length; i++) {
            // TODO: allow only input in the number value range (1 - MAX_VALUE)
            System.out.print("Number 1: ");
            int number1 = Input.readInt();
            System.out.print("Number 2: ");
            int number2 = Input.readInt();
            if ((number1 < 1 || number1 > 30) || (number2 < 1 || number2 > 30)) {
                System.out.println("At least one number out of lottery range - tip not registered!");
            } else {
                lottery[tipCount][i] = number1;
                lottery[tipCount][i + 1] = number2;
            }
            ;
            break;
        }
    }

    private static int[] simulateDraw() {
        //generate random values for any tip number
        Random rndGen = new Random(System.nanoTime());
        int[] draw = new int[NUMBERS_PER_TIP];
        for (int j = 0; j < draw.length; j++) {
            draw[j] = rndGen.nextInt(MAX_VALUE) + 1;
        }
        return draw; //return array with randomly generated numbers
    }

    private static void printWinner(int[][] lottery, int[] draw, int tipCount) {
        // TODO: check all registered tips and print if winner or not
        System.out.print("The winning numbers are: ");
        System.out.print(Arrays.toString(draw));
        System.out.println();
        for (int i = 0; i < MAX_TIPS; i++) {
            int g = i + 1;
            if (draw[0] == lottery[i][0] && draw[1] == lottery[i][1]) {
                System.out.println("Draw Nr. " + g + " wins!");
            } else {
                System.out.println("Draw Nr. " + g + " loses!");
            }
        }
    }

    private static void printLotteryTips(int[][] lottery, int tipCount) {
        // TODO: print all entered lottery tips
        for (int i = 0; i < lottery.length; i++) {
            int g = i + 1;
            System.out.print("Draw Nr. " + g + ":");
            for (int j = 0; j < lottery[i].length; j++) {
                System.out.print("  " + lottery[i][j] + " ");
            }
            System.out.println();
        }
    }
}
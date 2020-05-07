package swe1.ws17.ue04;
// load needed function for array

import java.util.Arrays;

import swe1.input.Input;

public class ArrayCalculations {
    public static void main(String[] args) {
// declare variables	
        int ArraySize = 0;
        int ArrayPosition = 0;
        int ArrayValue = 0;
        int ArrayAverage = 0;
        int ArraySum = 0;
        int ArrayValues = 0;
// ask for arraysize, quit on invalid input		
        System.out.print("Enter the desired size for array (0 or negative to quit): ");
        ArraySize = Input.readInt();
        if (ArraySize <= 0) {
            System.out.println("Invalid input - quit!");
            System.exit(0);
        }
// create array
        int[] Array = new int[ArraySize];
// fill array with negative values		
        for (int i = 0; i < ArraySize; i++) {
            Array[i] = -1;
        }
        ;
// loop user inputs to fill array with userdefined values			
        do {
            System.out.println(Arrays.toString(Array));
            System.out.print("Enter desired ArrayPosition (0 or negative to quit): ");
            ArrayPosition = Input.readInt();
// subtract 1 from Arrayposition to account for ArrayIndex starting at 0
            ArrayPosition = ArrayPosition - 1;
// quit on invalid input
            if (ArrayPosition >= ArraySize || ArrayPosition < 0) {
                System.out.println("Negative value, 0 or invalid arrayindex entered, quit!");
                break;
            }
            System.out.print("Enter desired value for entered position: ");
            ArrayValue = Input.readInt();
            if (ArrayValue >= 0) {
                Array[ArrayPosition] = ArrayValue;
            } else {
                System.out.println("Negative value entered, value not added to array!");
            }

        } while (ArraySize >= 0 && ArraySize <= ArraySize);
// loop array to find calculateable values 
        for (int r = 0; r < ArraySize; r++) {
// Sum entered values, except -1 and count entered values
            if (Array[r] != -1) {
                ArraySum = ArraySum + Array[r];
                ArrayValues += 1;
            }
        }
// Calculate average
        if (ArrayValues > 0) {
            ArrayAverage = ArraySum / ArrayValues;
        } else {
            System.out.println("Not enough values for calculation entered!");
        }
        ;
// print results
        System.out.println("Array Average:" + ArrayAverage);
        System.out.println("Array Sum:" + ArraySum);
    }
}
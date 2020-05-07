package swe1.ws17.ue03;
// purpose: find smallest and largest number, calculate range of unlimited user inputs, error on 0, 1 and null(empty)

import swe1.input.Input;

// load needed function for array
import java.util.ArrayList;
// load needed function for array "calculations
import java.util.Collections;

public class Range {
    public static void main(String[] args) {

// declare variables
        int inputnumber = 0;
        int smallest = 0;
        int largest = 0;
        int range = 0;
        int inputcount = 0;

// declare arraylist for <unlimited> inputs
        ArrayList<Integer> list = new ArrayList<Integer>();

// loop as long "inputnumber" is equal or bigger than  0
        {
            while (inputnumber >= 0) {
// input new number
                System.out.print("Enter a number (negative to quit): ");
                inputnumber = Input.readInt();
// add new number to list (only if equal or bigger than 0)
                if (inputnumber >= 0) {
                    list.add(inputnumber);
                }
            }
        }
// determine if no valid inputs were made
        if (list.isEmpty()) {
            System.out.println("No number entered, cannot calculate range");
            System.exit(0);
        }

// set/check number of inputs
        inputcount = list.size();

// check for smallest and largest number and calculate range
        smallest = Collections.min(list);
        largest = Collections.max(list);
        range = largest - smallest;

// check how many values were entered and display results		
        switch (inputcount) {
            case 1: {
                System.out.println("Only one number entered, cannot calculate range");
                System.exit(0);
            }

            default: {
                System.out.println("Smallest entered number: " + smallest);
                System.out.println("Largest entered number: " + largest);
                System.out.println("Range (Max - Min): " + range);
                System.exit(0);
            }
        }    // end switch
    }        // end method
}            // end class
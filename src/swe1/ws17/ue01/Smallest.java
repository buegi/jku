package swe1.ws17.ue01;
//purpose: find smallest number of unlimited positive inputs, abort on negative

//load needed function for array

import swe1.input.Input;

import java.util.ArrayList;
//load needed function to sort array
import java.util.Collections;

//start code
public class Smallest {
    public static void main(String[] args) {

//declare variables
        System.out.println("Enter a number (negative to quit)");
        int inputnumber = Input.readInt();

//declare array for <unlimited> inputs
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(inputnumber);

//loop as long "inputnumber" is bigger than 0
        {
            while (inputnumber >= 0) {

//input new number
                System.out.println("Enter a number (negative to quit)");
                inputnumber = Input.readInt();

// add new number to list (only if > 0)
                if (inputnumber >= 0) {
                    list.add(inputnumber);
                }

//check for smallest number
                Collections.sort(list);
                // if (inputnumber < inputnumber)
                // 	{inputnumber = smallestnumber;}
            }
        }

//output smallest number (position 0 of sorted array)
        System.out.println("Smallest entered number (<0 not accepted): " + list.get(0));

    }
}
package swe1.ws17.ue03;

import swe1.input.Input;

public class NumberInformation {
    public static void main(String[] args) {

//declare variables
        int inputnumber = 0;
        char operation = 0;

//loop as long "inputnumber" is equal or bigger than  0
        while (inputnumber >= 0) {
            System.out.print("Enter a number (negative to exit): ");
            inputnumber = Input.readInt();
// end program if negative value is entered 
            if (inputnumber < 0) {
                System.out.println("Good bye!");
                break;
            } else
// start code if entered value is greater than 0 	
            {
                System.out.print("Type of operation (r ...round, c ... count, s ... sum): ");
                operation = Input.readChar();
// determine operation for calculation		
                switch (operation) {
                    case 'c': {
// calculate length of entered number
                        int length = 0;
                        int calculate = inputnumber;
                        while (calculate > 0) {
                            length = (length + 1);
                            calculate = calculate / 10;
                        }
// output calculated values and blank line
                        System.out.println("Number of digits in entered number: " + length);
                        System.out.println();
                        break;
                    }
                    case 'r': {
// calculate round to nearest multiple of 10
                        //System.out.println("number: " +inputnumber);
                        //System.out.println("case r: " +operation);
                        int result = 0;
                        double multiple = 0;
                        double calculate = (double) (inputnumber);
                        multiple = Math.round(calculate / 10) * 10;
                        result = (int) (multiple);
// output calculated values and blank line
                        System.out.println("Nearest multiple of 10 for entered number: " + result);
                        System.out.println();
                        break;
                    }
                    case 's': {
// calculate sum of digits of entered numbers
                        int sum = 0;
                        int calculate = inputnumber;
                        while (calculate > 0) {
                            sum = sum + calculate % 10;
                            calculate = calculate / 10;
                        }
// output calculated values and blank line
                        System.out.println("Sum of digits in entered number: " + sum);
                        System.out.println();
                        break;
                    }
                    default: {
// output information about not implemented value for operation and blank line
                        System.out.println("\"" + operation + "\"" + " is not implemented as an operation!");
                        System.out.println();
                        break;
                    }
                }    //end switch
            }        //end else
        }            //end while
    }                //end method
}                    //end class
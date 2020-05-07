package swe1.ws17.ue02;

import swe1.input.Input;

public class CameraRating {
    public static void main(String[] args) {

        // declare variables for calculations - can take int on rounded and int value
        float calculatedoverallscore = 0;
        int roundedoverallscore = 0;
        int integeroverallscore = 0;

        //get raw values and declare variables - need to take float instead of int, to calculate mathematically correct
        System.out.print("Enter a value for image quality (1-10): ");
        float imagequality = Input.readInt();
        System.out.print("Enter a value for battery life (1-10): ");
        float batterylife = Input.readInt();
        System.out.print("Enter a value for zoom range (1-10): ");
        float zoomrange = Input.readInt();

        // line break one for println, one more for each \n - only for learning and reference purposes - ignore this line please
        System.out.println("\n");

        //calculate score and display it
        calculatedoverallscore = ((6 * imagequality + 1 * batterylife + 3 * zoomrange) / 10);
        System.out.println("Overall Score: " + calculatedoverallscore);

        //calculate rounded score and display it
        roundedoverallscore = Math.round(calculatedoverallscore);
        System.out.println("Rounded Overall Score: " + roundedoverallscore);

        //calculate integer score and display it
        integeroverallscore = (int) (calculatedoverallscore);
        System.out.println("Integer Overall Score: " + integeroverallscore);

        System.exit(0);
    }
}
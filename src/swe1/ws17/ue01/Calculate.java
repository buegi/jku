package swe1.ws17.ue01;

//purpose: calculate area for rectangle or square

import swe1.input.Input;

public class Calculate {
    public static void main(String[] args) {

// declare variables & decide figurine
        int length = 0;
        int width = 0;
        int area = 0;
//ask for square or rectangle, store in char variable
        System.out.println("What do you want to calculate?");
        System.out.println("Type (r)ectangle or (s)quare");
        char geometricfigure = Input.readChar();

// determine rectangle or square
        switch (geometricfigure) {
            case 's':
// input values
                System.out.println("You chose (s)quare!");
                System.out.println("Enter a value for length (l)");
                length = Input.readInt();
// calculate square
                area = length * length;
                System.out.println("The area of your square is:");
                System.out.println(+area);
                System.exit(0);

            case 'r':
// input values
                System.out.println("You chose (r)ectangle!");
                System.out.println("Enter a value for length (l)");
                length = Input.readInt();
                System.out.println("Enter a value for width (w)");
                width = Input.readInt();
// calculate rectangle
                area = length * width;
                System.out.println("The area of your rectangle is:");
                System.out.println(+area);
                System.exit(0);
        }
        System.out.println("Type of geometric figure not implemented");
    }
}
package swe1.ws17.ue02;

import swe1.input.Input;

public class TipCalculator {
    public static void main(String[] args) {

        // declare variables
        float sumDollar = 0.0f;

        // Error corrected: Type changed from float to int
        // Original code line: 		float tipRate = 0;
        int tipRate = 0;

        // Error corrected: Division of float through integer results in 0
        // Original code line: 		int conversion = 85 / 100;
        float conversion = (float) (85.0 / 100.0);

        // ask for user input of bill value and service quality
        System.out.print("Enter sum of restaurant bill in US-Dollar: ");
        sumDollar = Input.readFloat();
        System.out.print("Enter kind of service (r for regular, g for good): ");
        char service = Input.readChar();

        // set appropriate tipRate value for entered service quality
        if (service == 'r') {
            tipRate = 15;
        }
        if (service == 'g') {
            tipRate = 20;
        }

        // calculate sum in Euro
        float sumEuro = sumDollar * conversion;

        //calculate tip in Euro
        // Error corrected: type changed and operation corrected, was multiplication with character
        // Original code line:		float tip = sumEuro * service;
        float tip = (float) (sumEuro * (tipRate / 100.0));

        // calculate total bill value in Euro
        // Error corrected: type changed from int to float and operation changed to addition
        // Original code line:		int totalBill = (int)(sumEuro * tip);
        float totalBill = (float) (sumEuro + tip);

        // display results
        System.out.println("restaurant bill in Euro: " + sumEuro);
        System.out.println("appropriate tip in Euro: " + tip);
        System.out.println("bill including tip in Euro: " + totalBill);
    }
}
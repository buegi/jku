package prswe2.ss21.ue02.download.main;

import prswe2.ss21.ue02.download.calculator.CalculatorTest;
import prswe2.ss21.ue02.download.jayunit.JayUnit;

public class Runner {
    public static void main(String[] args) {
        JayUnit.runTests(CalculatorTest.class);
    }
}
package prswe2.ss21.ue02.download.main;

import prswe2.ss21.ue02.download.calculator.CalculatorTest;
import prswe2.ss21.ue02.download.jayunit.JayUnit;

import java.lang.reflect.InvocationTargetException;

public class Runner {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JayUnit.runTests(CalculatorTest.class);
    }
}
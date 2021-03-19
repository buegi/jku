package prswe2.ss21.ue02.download.calculator;

import prswe2.ss21.ue02.download.jayunit.*;

public class CalculatorTest {

    private Calculator sut;    // system under test

    // TODO init sut
    @BeforeTest
    public void init() {
        this.sut = new Calculator();
    }

    // TODO divide by 0, expect ArithmeticException
    @MyTest
    @ExpectException
    public void testDivideZero() {
        try {
            sut.divide(5 / 0);
        } catch(ArithmeticException ae) {
            System.out.println(ae.toString());
        }

    }

    // TODO add -1, check if state == -1 afterwards
    @MyTest
    public void testNegativeAdd() {
        sut.add(-1);
        sut.printState();
    }

    // TODO test if rem == 0 after reset
    @MyTest
    public void testResetRem() {
        sut.printRem();
        //
    }

    // TODO empty test, should be ignored by JayUnit
    @MyTest(ignore = true)
    public void dummyTest() {


    }
}
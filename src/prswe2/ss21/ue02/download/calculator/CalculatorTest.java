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
    public void testDivideZero() {
        sut.divide(5 / 0);
    }

    // TODO add -1, check if state == -1 afterwards
    @MyTest
    public void testNegativeAdd() {
        // Assert(sut.add(-5), sut.getClass().state == -1);
    }

    // TODO test if rem == 0 after reset
    @MyTest
    public void testResetRem() {

        //
    }
    // TODO empty test, should be ignored by JayUnit
    @MyTest
    public void dummyTest() {


    }
}
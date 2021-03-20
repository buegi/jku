package prswe2.ss21.ue02.download.calculator;

import prswe2.ss21.ue02.download.jayunit.*;

import java.lang.reflect.Field;

public class CalculatorTest {

    private Calculator sut;    // system under test

    // TODO init sut
    @BeforeTest
    public void init() {
        this.sut = new Calculator();
    }

    // TODO divide by 0, expect ArithmeticException
    @MyTest
    @ExpectException(expected = ArithmeticException.class)
    public void testDivideZero() {
        sut.divide(5 / 0);
    }

    // TODO add -1, check if state == -1 afterwards
    @MyTest
    public void testNegativeAdd() throws NoSuchFieldException, IllegalAccessException, TestFailedException {
        sut.add(-1);
        Field state = sut.getClass().getDeclaredField("state");
        state.setAccessible(true);
        // "Assert"
        if (state.getInt(sut) != -1) {
            throw new TestFailedException();
        }
    }

    // TODO test if rem == 0 after reset
    @MyTest
    public void testResetRem() throws NoSuchFieldException, IllegalAccessException, TestFailedException {
        Field rem = sut.getClass().getDeclaredField("rem");
        rem.setAccessible(true);
        rem.setInt(sut, -1);

        if (rem.getInt(sut) != 0) {
            throw new TestFailedException();
        }
    }

    // TODO empty test, should be ignored by JayUnit
    @MyTest(ignore = true)
    public void dummyTest() {

    }
}
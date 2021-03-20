package prswe2.ss21.ue02.download.jayunit;

import org.junit.Before;
import prswe2.ss21.ue02.download.calculator.Calculator;
import prswe2.ss21.ue02.download.calculator.CalculatorTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JayUnit {

    public static void runTests(Class<?> testClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object o = testClass.getDeclaredConstructor().newInstance();

        List<Method> beforeTestMethods = new LinkedList<>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(BeforeTest.class)).forEach(beforeTestMethods::add);

        if (beforeTestMethods.size() != 1) {
            throw new TestClassException("Too less or too many BeforeTestMethods");
        }

        List<Method> myTestMethods = new LinkedList<>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> (m.isAnnotationPresent(MyTest.class)
                        && !m.getAnnotation(MyTest.class).ignore()
                        && !m.isAnnotationPresent(ExpectException.class))
                ).forEach(myTestMethods::add);

        List<Method> expectExceptionMethods = new LinkedList<>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(ExpectException.class))
                .forEach(expectExceptionMethods::add);

        myTestMethods.forEach(m -> {
            beforeTestMethods.forEach(b -> {
                try {
                    b.invoke(o);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            boolean thrown = false;
            try {
                System.out.print(m.getName());
                m.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof TestFailedException) {
                    System.out.println("  Failed");
                    thrown = true;
                }
            }
            if (!thrown) {
                System.out.println("  OK");
            }
        });

        expectExceptionMethods.forEach(m -> {
            beforeTestMethods.forEach(b -> {
                try {
                    b.invoke(o);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            try {
                System.out.print(m.getName());
                m.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if (m.getAnnotation(ExpectException.class).expected().isInstance(e.getCause())) {
                    System.out.print(" OK");
                }
            }
            System.out.println();
        });
    }
}
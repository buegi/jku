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
        // TODO
        Object o = testClass.getDeclaredConstructor().newInstance();

        List<Method> beforeTestMethods = new LinkedList<>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(BeforeTest.class)).forEach(l -> beforeTestMethods.add(l));

        /* falls mehrere @BeforeTest Methoden vorhanden sind, soll eine TestClassException
           geworfen werden */
        if (beforeTestMethods.size() <= 0 || beforeTestMethods.size() > 1) {
            throw new TestClassException("Too less or too many BeforeTestMethods");
        }

        List<Method> myTestMethods = new LinkedList<>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> (m.isAnnotationPresent(MyTest.class)
                        && !m.getAnnotation(MyTest.class).ignore()
                        && !m.isAnnotationPresent(ExpectException.class))
                ).forEach(l -> myTestMethods.add(l));

        List<Method> expectExceptionMethods = new LinkedList<>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(ExpectException.class))
                .forEach(l -> expectExceptionMethods.add(l));

//        System.out.println("BeforeTestMethods:");
//        beforeTestMethods.forEach(m -> System.out.println("   " + m.getName()));
//        System.out.println("MyTestMethods:");
//        myTestMethods.forEach(m -> System.out.println("   " + m.getName()));
//        System.out.println("ExpectExceptionMethods:");
//        expectExceptionMethods.forEach(m -> System.out.println("   " + m.getName()));

        myTestMethods.forEach(m -> {
            beforeTestMethods.forEach(b -> {
                try {
                    System.out.println(b.getName());
                    b.invoke(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            try {
                System.out.println(m.getName());
                m.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if (e.getCause().)
                // testfailedexception ? failed : ok;
                 e.printStackTrace();
            }
        });

        expectExceptionMethods.forEach(m -> {
            beforeTestMethods.forEach(b -> {
                try {
                    System.out.println(b.getName());
                    b.invoke(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            try {
                System.out.println();
                System.out.print(m.getName());
                    m.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if (m.getAnnotation(ExpectException.class).expected().isInstance(e.getCause())) {
                    System.out.print(" OK");
                }
            }
        });





		/* führt alle mit @MyTest annotierten Methoden aus (die nicht mit ignore gekennzeichnet sind).
           Tests, die eine TestFailedException werfen, schlagen fehl. Tests, die „durchlaufen“ sind
           erfolgreich (siehe Hinweis zu Exceptions). Das Ergebnis jedes Tests wird auf der
           Kommandozeile ausgegeben ("testX ok" oder "testY failed"). */


        /* Tests, die eine Exception erwarten, sind nur dann erfolgreich, wenn genau diese Exception
           geworfen wurde (siehe Hinweis zu Exceptions) */


	    /* Wird eine Exception gefangen, die weder in der ExpectException Annotation spezifiziert,
           noch eine TestFailedException ist (z.B. eine IllegalArgumentException, wenn eine
           Testmethode unerlaubterweise Parameter erwartet hätte), soll diese mit geeigneter
           Fehlermeldung in eine TestClassException geschachtelt und weitergeworfen werden */


    }
}
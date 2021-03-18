package prswe2.ss21.ue02.download.jayunit;

import prswe2.ss21.ue02.download.calculator.Calculator;
import prswe2.ss21.ue02.download.calculator.CalculatorTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JayUnit {

    public static void runTests(Class<?> testClass) {
        // TODO
		/* falls mehrere @BeforeTest Methoden vorhanden sind, soll eine TestClassException
           geworfen werden */

        List<Method> beforeTest = getBeforeTestMethods(testClass);

        if (beforeTest.size() <= 0 || beforeTest.size() > 1) {
            throw new TestClassException(testClass.toString());
        }

        List<Method> myTest = getMyTestMethods(testClass);
        myTest.forEach(m -> {

            beforeTest.forEach(b -> {
                try {
                    System.out.println(b);
                    b.invoke(testClass.getConstructor().newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            try {
                System.out.println(m);
                m.invoke(testClass.getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
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

    private static List<Method> getBeforeTestMethods(Class<?> testClass) {
        List<Method> beforeTestMethods = new LinkedList<Method>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(BeforeTest.class)).forEach(l -> beforeTestMethods.add(l));
//        print for debugging
//        System.out.println(beforeTestMethods.size());
//        beforeTestMethods.forEach(m -> System.out.println(m.toString()));
        return beforeTestMethods;
    }

    private static List<Method> getMyTestMethods(Class<?> testClass) {
        List<Method> myTestMethods = new LinkedList<Method>();
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(MyTest.class) && !m.isAnnotationPresent(ExpectException.class))
                .forEach(l -> myTestMethods.add(l));
//        print for debugging
//        System.out.println(myTestMethods.size());
//        myTestMethods.forEach(m -> System.out.println(m.toString()));
        return myTestMethods;
    }
}
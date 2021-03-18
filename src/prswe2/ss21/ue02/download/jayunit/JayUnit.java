package prswe2.ss21.ue02.download.jayunit;

import prswe2.ss21.ue02.download.calculator.CalculatorTest;

import java.util.Arrays;

public class JayUnit {

    public static void runTests(Class<?> testClass) {
        // TODO
		/* falls mehrere @BeforeTest Methoden vorhanden sind, soll eine TestClassException
           geworfen werden */

        System.out.println(Arrays.stream(testClass.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(BeforeTest.class)).count());
        if (Arrays.stream(testClass.getMethods())
                .filter(m -> m.isAnnotationPresent(BeforeTest.class)).count() <= 0 ||
                Arrays.stream(testClass.getMethods())
                        .filter(m -> m.isAnnotationPresent(BeforeTest.class)).count() > 1) {
            throw new TestClassException(testClass.toString());
        }
        Arrays.stream(testClass.getMethods())
                .filter(m -> m.isAnnotationPresent(BeforeTest.class)).forEach(l -> System.out.println(l.getName()));


		/* führt alle mit @MyTest annotierten Methoden aus (die nicht mit ignore gekennzeichnet sind).
           Tests, die eine TestFailedException werfen, schlagen fehl. Tests, die „durchlaufen“ sind
           erfolgreich (siehe Hinweis zu Exceptions). Das Ergebnis jedes Tests wird auf der
           Kommandozeile ausgegeben ("testX ok" oder "testY failed"). */
        System.out.println(Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(MyTest.class)).count());
        Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(MyTest.class)).forEach(l -> {
            try {
                System.out.println(l);
                l.invoke(testClass.getConstructor().newInstance(), l, 0, 5);
            } catch (Exception e) {
                System.out.println("Exception:" + e.toString());
            }
        });

        /* Tests, die eine Exception erwarten, sind nur dann erfolgreich, wenn genau diese Exception
           geworfen wurde (siehe Hinweis zu Exceptions) */


	    /* Wird eine Exception gefangen, die weder in der ExpectException Annotation spezifiziert,
           noch eine TestFailedException ist (z.B. eine IllegalArgumentException, wenn eine
           Testmethode unerlaubterweise Parameter erwartet hätte), soll diese mit geeigneter
           Fehlermeldung in eine TestClassException geschachtelt und weitergeworfen werden */


    }

}
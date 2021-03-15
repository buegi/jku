package prswe2.ss21.ue02.demo.demo;

import java.lang.reflect.InvocationTargetException;

import prswe2.ss21.ue02.demo.annotation.Parse;
import prswe2.ss21.ue02.demo.annotation.Parser;
import prswe2.ss21.ue02.demo.person.Person;

public class Demo3 {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<Person> p = Person.class;

        if (p.isAnnotationPresent(Parse.class)) {
            Parse pAnn = p.getAnnotation(Parse.class);
            Class<? extends Parser> parserCl = pAnn.value();

            Object o = parserCl.getConstructor().newInstance().parse("Hans Male");
            System.out.println(o);
        }
    }
}
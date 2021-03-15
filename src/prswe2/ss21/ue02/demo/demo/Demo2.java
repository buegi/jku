package prswe2.ss21.ue02.demo.demo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

import prswe2.ss21.ue02.demo.box.Box;
import prswe2.ss21.ue02.demo.person.Gender;
import prswe2.ss21.ue02.demo.person.Person;

public class Demo2 {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Box<String> box = new Box<>("A");

        System.out.println(box);
        Class<Box<String>> cl = (Class<Box<String>>) box.getClass();

        TypeVariable<?> tv = cl.getTypeParameters()[0];
        System.out.println(tv);

        Field f = cl.getDeclaredField("val");
        System.out.println(f.getType());

        Method m = cl.getDeclaredMethod("setVal", Serializable.class);
        m.invoke(box, "B");

        System.out.println(box);
    }
}
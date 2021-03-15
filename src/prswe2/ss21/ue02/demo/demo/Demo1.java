package prswe2.ss21.ue02.demo.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import prswe2.ss21.ue02.demo.person.Gender;
import prswe2.ss21.ue02.demo.person.Person;

public class Demo1 {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
//		Person p = new Person("Hans", Gender.Male);

//		Class<?> cl1 = p.getClass();
        Class<Person> cl1 = Person.class;
        try {
            Class<Person> cl3 = (Class<Person>) Class.forName("person.Person");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Constructor<Person> pConst = cl1.getConstructor(String.class, Gender.class);
        Person p = pConst.newInstance("Max", Gender.Male);

        System.out.println(p);

        Method getNameM = cl1.getDeclaredMethod("getName");
        if (getNameM.getReturnType().equals(String.class)) {
            System.out.println(getNameM.invoke(p));
        }

        Method setNameM = cl1.getDeclaredMethod("setName", String.class);
        setNameM.invoke(p, "Fritz");

        System.out.println(p);

        Field nameF = cl1.getDeclaredField("name");
        nameF.setAccessible(true);
        nameF.set(p, "Max");

        System.out.println(p);
    }

    public static void print(Class<?> c) {
        System.out.println("package " + c.getPackage().getName() + ";");
        System.out.print(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName());
        System.out.print(" extends " + c.getSuperclass().getSimpleName() + " ");
        if (c.getInterfaces().length != 0) {
            System.out.print("implements ");
            for (Class<?> i : c.getInterfaces()) {
                System.out.print(i.getSimpleName() + " ");
            }
        }
        System.out.println("{");

        for (Field f : c.getDeclaredFields()) {
            System.out.println("\t" + Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName() + ";");
        }

        for (Method m : c.getDeclaredMethods()) {
            System.out.print("    " + Modifier.toString(m.getModifiers()) + " "
                    + m.getReturnType().getSimpleName() + " "
                    + m.getName() + "(");
            System.out.print(Arrays.stream(m.getParameters())
                    .map(p -> p.getType().getSimpleName() + " " + p.getName())
                    .collect(Collectors.joining(", ")));
            System.out.println(");");
        }
        System.out.println("}");
    }
}
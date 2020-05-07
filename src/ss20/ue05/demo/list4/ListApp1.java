package ss20.ue05.demo.list4;

import ss20.ue05.demo.persons.Person;
import ss20.ue05.demo.persons.Student;
import ss20.ue05.demo.persons.Teacher;

public class ListApp1 {

    public static void main(String[] args) {
        List<Person> persons = new LinkedList<Person>();
        persons.add(new Student("Alfred"));
        persons.add(new Teacher("Hauerbichler"));
        for (Person p : persons) {
            System.out.println(p.toString());
        }

        List<Student> students = new LinkedList<Student>();
        students.add(new Student("Mike"));
        students.add(new Student("Joe"));
        for (Student s : students) {
            s.learn();
        }

        List<Teacher> teachers = new LinkedList<Teacher>();
        teachers.add(new Teacher("Maier"));
        teachers.add(new Teacher("Huber"));
        for (Teacher t : teachers) {
            t.teach();
        }

        List<String> strings = new LinkedList<String>();
        strings.add("Hello");
        strings.add("World");
        strings.add("!");
        for (String s : strings) {
            System.out.println(s);
        }

        // Object[] o1 = students.toArray();

        // Object[] o1 = students.toEArray();
        // Person[] pa1 = students.toEArray();

        // Person[] pa2 = students.toArray(new Person[0]);
        // System.out.println(pa2);
        // System.out.println(Arrays.toString(pa2));

        // Student[] sa1 = students.toArray(new Student[0]);
        // System.out.println(sa1);
        // System.out.println(Arrays.toString(sa1));

        // Student[] sa = teachers.toArray(new Student[0]);
    }
}
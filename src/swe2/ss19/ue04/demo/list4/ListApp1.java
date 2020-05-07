package swe2.ss19.ue04.demo.list4;

import swe2.ss19.ue04.demo.persons.Person;
import swe2.ss19.ue04.demo.persons.Student;
import swe2.ss19.ue04.demo.persons.Teacher;

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

    }

}

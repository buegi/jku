package ss19.ue05.demo.generics2;

import ss19.ue05.demo.persons.Person;
import ss19.ue05.demo.persons.Student;
import ss19.ue05.demo.persons.Teacher;

public class ListApp1 {

    public static void main(String[] args) {

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

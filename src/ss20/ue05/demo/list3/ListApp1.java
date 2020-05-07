package ss20.ue05.demo.list3;

import ss20.ue05.demo.persons.Student;
import ss20.ue05.demo.persons.Teacher;

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
    }
}
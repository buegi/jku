package ss19.ue04.demo.list3;

import persons.Person;
import persons.Student;
import persons.Teacher;

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
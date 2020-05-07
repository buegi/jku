package swe2.ss19.ue06.demo.coll10;

public class Student extends Person {

	final int studentId;

	public Student(String firstName, String surName, int studentId) {
		super(firstName, surName);
		this.studentId = studentId;
	}

}

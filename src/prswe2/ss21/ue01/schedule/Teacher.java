package prswe2.ss21.ue01.schedule;

import java.util.*;
import java.util.stream.Collectors;

public class Teacher implements Comparable<Teacher> {
	
	private final String name;
	private Set<Subject> subjects;
	private Map<SchoolClass, Unit> lessons;
	
	public Teacher(String name, Subject...subjects) {
		this.name = name; 
		// TODO
	}

	@Override
	public int compareTo(Teacher other) {
		return this.name.compareToIgnoreCase(other.name);
	}

}
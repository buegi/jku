package prswe2.ss21.ue01.schedule;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Teacher implements Comparable<Teacher> {
	
	private final String name; 
	
	public Teacher(String name, Subject...subjects) {
		this.name = name; 
		// TODO
	}

	@Override
	public int compareTo(Teacher other) {
		return this.name.compareToIgnoreCase(other.name);
	}

}

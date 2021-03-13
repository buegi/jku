package prswe2.ss21.ue01.schedule;

import java.util.*;
import java.util.stream.Collectors;

public class Teacher implements Comparable<Teacher> {

    private final String name;
    private final Set<Subject> subjects = new TreeSet<Subject>();

    public Teacher(String name, Subject... subjects) {
        this.name = name;
        Arrays.stream(subjects).forEach(s -> this.subjects.add(s));
    }

    public String getName() {
        return this.name;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public int compareTo(Teacher other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(name, teacher.name) && Objects.equals(subjects, teacher.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subjects);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{Name: " + name + ", Subjects: ");
        subjects.stream().forEach(s -> sb.append(s + ", "));
        sb.append("}");
        return sb.toString();
    }
}
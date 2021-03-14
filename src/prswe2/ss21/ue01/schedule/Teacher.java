package prswe2.ss21.ue01.schedule;

import java.util.*;

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

    @Override
    public int compareTo(Teacher other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(this.name, teacher.name) && Objects.equals(this.subjects, teacher.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.subjects);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{Name: " + this.name + ", Subjects: ");
        this.subjects.stream().forEach(s -> sb.append(s + ", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }
}
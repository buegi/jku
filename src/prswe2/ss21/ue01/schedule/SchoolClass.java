package prswe2.ss21.ue01.schedule;

import static prswe2.ss21.ue01.schedule.Subject.Bio;
import static prswe2.ss21.ue01.schedule.Subject.English;
import static prswe2.ss21.ue01.schedule.Subject.German;
import static prswe2.ss21.ue01.schedule.Subject.Math;
import static prswe2.ss21.ue01.schedule.Subject.Physics;
import static prswe2.ss21.ue01.schedule.Subject.*;

import java.util.*;
import java.util.stream.Collectors;

public class SchoolClass implements Comparable<SchoolClass> {

    private final int number;
    private final String name;
    private final Map<Subject, Teacher> subjects = new TreeMap<Subject, Teacher>();

    public SchoolClass(int n, String name) {
        this.number = n;
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public Map<Subject, Teacher> getSubjects() {
        return this.subjects;
    }

    @Override
    public int compareTo(SchoolClass o) {
        if (this.number == o.number) {
            return this.name.compareTo(o.name);
        } else {
            return Integer.compare(this.number, o.number);
        }
    }

    public void defineSubject(Subject subject, Teacher teacher) {
        this.subjects.put(subject, teacher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return number == that.number && Objects.equals(name, that.name) && Objects.equals(subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, subjects);
    }

    @Override
    public String toString() {
        return ("ClassNumber: " + number + ", Name: " + name);
    }
}
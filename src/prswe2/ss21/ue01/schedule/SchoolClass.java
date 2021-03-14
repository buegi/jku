package prswe2.ss21.ue01.schedule;

import java.util.*;

public class SchoolClass implements Comparable<SchoolClass> {

    private final int schoolLevel;
    private final String name;
    private final Map<Subject, Teacher> subjects = new HashMap<>();

    public SchoolClass(int n, String name) {
        this.schoolLevel = n;
        this.name = name;
    }

    public int getSchoolLevel() {
        return this.schoolLevel;
    }

    public String getName() {
        return this.name;
    }

    public Map<Subject, Teacher> getSubjects() {
        return this.subjects;
    }

    @Override
    public int compareTo(SchoolClass o) {
        return this.schoolLevel == o.schoolLevel ? this.name.compareTo(o.name)
                : Integer.compare(this.schoolLevel, o.schoolLevel);
    }

    public void defineSubject(Subject subject, Teacher teacher) {
        this.subjects.put(subject, teacher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return this.schoolLevel == that.schoolLevel && Objects.equals(this.name, that.name)
                && Objects.equals(this.subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.schoolLevel, this.name, this.subjects);
    }

    @Override
    public String toString() {
        return ("{SchoolLevel: " + this.schoolLevel + ", Name: " + this.name + "}");
    }
}
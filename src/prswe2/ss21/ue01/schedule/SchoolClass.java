package prswe2.ss21.ue01.schedule;

import java.util.*;

public class SchoolClass implements Comparable<SchoolClass> {

    private final int lvl;
    private final String name;
    private final Map<Subject, Teacher> subjects = new HashMap<Subject, Teacher>();

    public SchoolClass(int n, String name) {
        this.lvl = n;
        this.name = name;
    }

    public int getLvl() {
        return this.lvl;
    }

    public String getName() {
        return this.name;
    }

    public Map<Subject, Teacher> getSubjects() {
        return this.subjects;
    }

    @Override
    public int compareTo(SchoolClass o) {
        return this.lvl == o.lvl ? this.name.compareTo(o.name) : Integer.compare(this.lvl, o.lvl);
    }

    public void defineSubject(Subject subject, Teacher teacher) {
        this.subjects.put(subject, teacher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return this.lvl == that.lvl && Objects.equals(this.name, that.name)
                && Objects.equals(this.subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lvl, this.name, this.subjects);
    }

    @Override
    public String toString() {
        return ("{ClassNumber: " + this.lvl + ", Name: " + this.name + "}");
    }
}
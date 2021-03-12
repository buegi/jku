package prswe2.ss21.ue01.schedule;

import java.util.Comparator;
import java.util.Objects;

public class Lesson implements Comparable<Lesson> {

    private SchoolClass schoolClass;
    private Unit unit;
    private Subject subject;

    public Lesson(SchoolClass schoolClass, Unit unit, Subject subject) {
        this.schoolClass = schoolClass;
        this.unit = unit;
        this.subject = subject;
    }

    @Override
    public int compareTo(Lesson o) {


        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(schoolClass, lesson.schoolClass) && unit == lesson.unit && subject == lesson.subject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolClass, unit, subject);
    }

    @Override
    public String toString() {
        return ("SchoolClass: " + schoolClass + " Unit: " + unit + " Subject: " + subject);
    }
}
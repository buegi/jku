package prswe2.ss21.ue01.schedule;

import java.util.Comparator;
import java.util.Objects;

public class Lesson implements Comparable<Lesson> {

    private SchoolClass schoolClass;
    private Unit unit;
    private Subject subject;
    private Teacher teacher;

    public Lesson(SchoolClass schoolClass, Unit unit, Subject subject, Teacher teacher) {
        this.schoolClass = schoolClass;
        this.unit = unit;
        this.subject = subject;
        this.teacher = teacher;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public int compareTo(Lesson o) {
        if (this.schoolClass.equals(o.schoolClass)) {
            if (this.unit == o.unit) {
                return this.subject.compareTo(o.subject);
            } else {
                return this.unit.compareTo(o.unit);
            }
        }
        return this.schoolClass.compareTo(o.schoolClass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(schoolClass, lesson.schoolClass) && unit == lesson.unit && subject == lesson.subject && Objects.equals(teacher, lesson.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolClass, unit, subject, teacher);
    }

    @Override
    public String toString() {
        return ("{SchoolClass: " + schoolClass + " Unit: " + unit + " Subject: " + subject + " Teacher: " + teacher + "}");
    }
}
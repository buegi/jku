package prswe2.ss21.ue01.schedule;

import java.util.Objects;

public class Lesson implements Comparable<Lesson> {

    private final SchoolClass schoolClass;
    private final Unit unit;
    private final Subject subject;
    private final Teacher teacher;

    public Lesson(SchoolClass schoolClass, Unit unit, Subject subject, Teacher teacher) {
        this.schoolClass = schoolClass;
        this.unit = unit;
        this.subject = subject;
        this.teacher = teacher;
    }

    public SchoolClass getSchoolClass() {
        return this.schoolClass;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public Subject getSubject() {
        return this.subject;
    }

    @Override
    public int compareTo(Lesson o) {
        if (this.schoolClass.equals(o.schoolClass)) {
            if (this.unit == o.unit) {
                return this.subject == o.subject ? this.teacher.compareTo(o.teacher)
                        : this.subject.compareTo(o.subject);
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
        return Objects.equals(this.schoolClass, lesson.schoolClass)
                && this.unit == lesson.unit && this.subject == lesson.subject
                && Objects.equals(this.teacher, lesson.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.schoolClass, this.unit, this.subject, this.teacher);
    }

    @Override
    public String toString() {
        return ("{SchoolClass: " + this.schoolClass + ", Unit: "
                + this.unit + ", Subject: " + this.subject + ", Teacher: " + this.teacher + "}");
    }
}
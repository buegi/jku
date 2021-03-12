package prswe2.ss21.ue01.schedule;

import java.util.*;
import java.util.stream.Collectors;

public class School {

    private final Set<Teacher> teachers = new TreeSet<Teacher>();
    private final Set<SchoolClass> schoolClasses = new TreeSet<SchoolClass>();
    private final List<Lesson> lessons = new LinkedList<Lesson>();

    public School() {
    }

    public void defineClasses(SchoolClass... cls) {
        Arrays.stream(cls).forEach(s -> this.schoolClasses.add(s));
    }

    public void defineTeachers(Teacher... ts) {
        Arrays.stream(ts).forEach(t -> this.teachers.add(t));
    }

    public void defineLesson(SchoolClass clss, Unit unit, Subject subj) {
        this.lessons.add(new Lesson(clss, unit, subj));
    }

}
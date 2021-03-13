package prswe2.ss21.ue01.schedule;

import java.util.*;
import java.util.stream.Collectors;

public class School {

    private final Set<Teacher> teachers = new TreeSet<Teacher>();
    private final Set<SchoolClass> schoolClasses = new TreeSet<SchoolClass>();
    private final Map<SchoolClass, List<Lesson>> schedule = new TreeMap<SchoolClass, List<Lesson>>();

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Set<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public Map<SchoolClass, List<Lesson>> getSchedule() {
        return this.schedule;
    }

    public School() {
    }

    public void defineClasses(SchoolClass... cls) {
        Arrays.stream(cls).forEach(s -> this.schoolClasses.add(s));
    }

    public void defineTeachers(Teacher... ts) {
        Arrays.stream(ts).forEach(t -> this.teachers.add(t));
    }

    public void defineLesson(SchoolClass clss, Unit unit, Subject subj) {
        Lesson newLesson = new Lesson(clss, unit, subj, clss.getSubjects().get(subj));
        if (this.schedule.containsKey(clss)) {
            this.schedule.get(clss).add(newLesson);
            return;
        }
        this.schedule.put(clss, new LinkedList<Lesson>());
        this.schedule.get(clss).add(newLesson);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(teachers, school.teachers) && Objects.equals(schoolClasses, school.schoolClasses) && Objects.equals(schedule, school.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachers, schoolClasses, schedule);
    }
}
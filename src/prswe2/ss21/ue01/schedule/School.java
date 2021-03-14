package prswe2.ss21.ue01.schedule;

import java.util.*;

public class School {

    private final Set<Teacher> teachers = new HashSet<>();
    private final Set<SchoolClass> schoolClasses = new HashSet<>();
    private final Map<SchoolClass, List<Lesson>> schedule = new HashMap<>();

    public School() {
    }

    public Set<Teacher> getTeachers() {
        return this.teachers;
    }

    public Set<SchoolClass> getSchoolClasses() {
        return this.schoolClasses;
    }

    public Map<SchoolClass, List<Lesson>> getSchedule() {
        return this.schedule;
    }

    public void defineClasses(SchoolClass... cls) {
        this.schoolClasses.addAll(Arrays.asList(cls));
    }

    public void defineTeachers(Teacher... ts) {
        this.teachers.addAll(Arrays.asList(ts));
    }

    public void defineLesson(SchoolClass clss, Unit unit, Subject subj) {
        if (!this.schedule.containsKey(clss)) {
            this.schedule.put(clss, new LinkedList<>());
        }
        this.schedule.get(clss).add(new Lesson(clss, unit, subj, clss.getSubjects().get(subj)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(this.teachers, school.teachers)
                && Objects.equals(this.schoolClasses, school.schoolClasses)
                && Objects.equals(this.schedule, school.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.teachers, this.schoolClasses, this.schedule);
    }
}
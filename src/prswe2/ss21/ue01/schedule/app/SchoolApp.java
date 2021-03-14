package prswe2.ss21.ue01.schedule.app;

import static prswe2.ss21.ue01.schedule.Subject.Art;
import static prswe2.ss21.ue01.schedule.Subject.*;
import static prswe2.ss21.ue01.schedule.Unit.*;

import prswe2.ss21.ue01.schedule.*;

import java.util.*;
import java.util.stream.Collectors;

public class SchoolApp {
    public static void main(String[] args) {

        School sch = new School();

        Teacher maier = new Teacher("Maier", Physics, Math);
        Teacher auer = new Teacher("Auer", Geo, Sports);
        Teacher reisner = new Teacher("Reisner", English, German);
        Teacher ebner = new Teacher("Ebner", History, Art);
        Teacher fischer = new Teacher("Fischer", English, Geo);
        Teacher huber = new Teacher("Huber", Bio, German);

        sch.defineTeachers(maier, auer, reisner, ebner, fischer);
        SchoolClass a1 = new SchoolClass(1, "1a");
        SchoolClass b1 = new SchoolClass(1, "1b");
        SchoolClass a2 = new SchoolClass(2, "2a");
        SchoolClass b2 = new SchoolClass(2, "2b");
        SchoolClass a3 = new SchoolClass(3, "3a");
        SchoolClass b3 = new SchoolClass(3, "3b");
        SchoolClass a4 = new SchoolClass(4, "4a");
        SchoolClass b4 = new SchoolClass(4, "4b");

        sch.defineClasses(a1, b1, a2, b2, a3, b3, a4, b4);

        a1.defineSubject(German, huber);
        a1.defineSubject(English, reisner);
        a1.defineSubject(Math, maier);
        a1.defineSubject(History, ebner);
        a1.defineSubject(Geo, auer);
        a1.defineSubject(Bio, huber);
        a1.defineSubject(Art, ebner);
        a1.defineSubject(Sports, auer);

        sch.defineLesson(a1, Mon1, English);
        sch.defineLesson(a1, Mon2, Math);
        sch.defineLesson(a1, Mon3, Math);
        sch.defineLesson(a1, Mon4, Bio);
        sch.defineLesson(a1, Mon5, Art);
        sch.defineLesson(a1, Mon6, Art);

        sch.defineLesson(a1, Tue1, German);
        sch.defineLesson(a1, Tue2, German);
        sch.defineLesson(a1, Tue3, History);
        sch.defineLesson(a1, Tue4, Sports);
        sch.defineLesson(a1, Tue5, Sports);

        sch.defineLesson(a1, Wen1, History);
        sch.defineLesson(a1, Wen3, English);
        sch.defineLesson(a1, Wen4, English);
        sch.defineLesson(a1, Wen5, Bio);
        sch.defineLesson(a1, Wen6, Bio);

        sch.defineLesson(a1, Thu1, Math);
        sch.defineLesson(a1, Thu2, History);
        sch.defineLesson(a1, Thu3, Geo);
        sch.defineLesson(a1, Thu4, English);
        sch.defineLesson(a1, Thu5, German);

        sch.defineLesson(a1, Fri1, English);
        sch.defineLesson(a1, Fri2, German);
        sch.defineLesson(a1, Fri3, Math);
        sch.defineLesson(a1, Fri4, Geo);

        // b1

        b1.defineSubject(German, reisner);
        b1.defineSubject(English, reisner);
        b1.defineSubject(Math, maier);
        b1.defineSubject(History, ebner);
        b1.defineSubject(Geo, fischer);
        b1.defineSubject(Bio, huber);
        b1.defineSubject(Art, ebner);
        b1.defineSubject(Sports, auer);

        sch.defineLesson(b1, Mon1, History);
        sch.defineLesson(b1, Mon3, English);
        sch.defineLesson(b1, Mon4, English);
        sch.defineLesson(b1, Mon5, Bio);
        sch.defineLesson(b1, Mon6, Bio);

        sch.defineLesson(b1, Tue1, Math);
        sch.defineLesson(b1, Tue2, Math);
        sch.defineLesson(b1, Tue3, English);
        sch.defineLesson(b1, Tue4, Bio);
        sch.defineLesson(b1, Tue5, Art);
        sch.defineLesson(b1, Tue6, Art);

        sch.defineLesson(b1, Wen1, German);
        sch.defineLesson(b1, Wen2, German);
        sch.defineLesson(b1, Wen3, History);
        sch.defineLesson(b1, Wen4, Sports);
        sch.defineLesson(b1, Wen5, Sports);

        sch.defineLesson(b1, Thu1, English);
        sch.defineLesson(b1, Thu2, German);
        sch.defineLesson(b1, Thu3, Math);
        sch.defineLesson(b1, Thu4, Geo);

        sch.defineLesson(b1, Fri1, Math);
        sch.defineLesson(b1, Fri2, History);
        sch.defineLesson(b1, Fri3, Geo);
        sch.defineLesson(b1, Fri4, English);
        sch.defineLesson(b1, Fri5, German);

        // print result of queries
        System.out.println("----- Test to String -----");
        System.out.println(maier.toString());
        System.out.println(a1.toString());


        System.out.println("----- Set of Lessons for one Class (1a) on one Day (Mon) -----");
        Set<Lesson> lessons1AMonday = sch.getSchedules().get(a1).stream()
                .filter(l -> l.getUnit().getDay().equals(Day.Mon))
                .collect(Collectors.toSet());
        // output lessons and sum of lessons
        lessons1AMonday.forEach(sc -> System.out.println(sc.toString()));
        System.out.println("#Items " + lessons1AMonday.size());


        System.out.println("----- Set of Lessons for one Teacher (Reisner) on one Day (Mon)  -----");
        Set<Lesson> lessonsReisnerMonday = new TreeSet<>();
        sch.getSchedules().forEach((sc, ll) -> ll.stream()
                .filter(l -> l.getTeacher().equals(reisner) && l.getUnit().getDay().equals(Day.Mon))
                .forEach(lessonsReisnerMonday::add));
        // output lessons and sum of lessons
        lessonsReisnerMonday.forEach(l -> System.out.println(l.toString()));
        System.out.println("#Items " + lessonsReisnerMonday.size());


        System.out.println("----- Set of Lessons for one Teacher (Huber) for one SchoolClass (1a) -----");
        Set<Lesson> lessonsHuberA1 = sch.getSchedules().get(a1).stream()
                .filter(l -> l.getTeacher().equals(huber))
                .collect(Collectors.toSet());
        // output lessons and sum of lessons
        lessonsHuberA1.forEach(l -> System.out.println(l.toString()));
        System.out.println("#Items " + lessonsHuberA1.size());


        System.out.println("----- Set of Lessons for one Teacher (Maier) for one Subject (Math) -----");
        Set<Lesson> lessonsMaierMath = new TreeSet<>();
        sch.getSchedules().forEach((sc, ll) -> ll.stream()
                .filter(l -> l.getTeacher().equals(maier) && l.getSubject().equals(Math))
                .forEach(lessonsMaierMath::add));
        // output lessons and sum of lessons
        lessonsMaierMath.forEach(l -> System.out.println(l.toString()));
        System.out.println("#Items " + lessonsMaierMath.size());


        System.out.println("----- Set of Lessons for one SchoolClass (1b) for one Subject (English) -----");
        Set<Lesson> lessonsB1English = sch.getSchedules().get(b1).stream()
                .filter(l -> l.getSubject().equals(English))
                .collect(Collectors.toSet());
        // output lessons and sum of lessons
        lessonsB1English.forEach(l -> System.out.println(l.toString()));
        System.out.println("#Items " + lessonsB1English.size());


        System.out.println("----- Set of Lessons for School sorted by SchoolClass, Unit -----");
        // define comparator for sorting
        Set<Lesson> allLessonsSortedBySchoolClassThenUnit =
                new TreeSet<>((o1, o2) -> o1.getSchoolClass().compareTo(o2.getSchoolClass()) == 0 ?
                        o1.getUnit().compareTo(o2.getUnit()) : o1.getSchoolClass().compareTo(o2.getSchoolClass()));
        // add lessons (automatically sorted by comparator)
        sch.getSchedules().forEach((sc, ll) -> allLessonsSortedBySchoolClassThenUnit.addAll(ll));
        // output lessons and sum of lessons
        allLessonsSortedBySchoolClassThenUnit.forEach(l -> System.out.println(l.toString()));
        System.out.println("#Items " + allLessonsSortedBySchoolClassThenUnit.size());


        System.out.println("----- Set of Lessons for Teachers sorted by Teacher, Unit -----");
        // define comparator for sorting
        Set<Lesson> allLessonsSortedByTeacherThenUnit =
                new TreeSet<>((o1, o2) -> o1.getTeacher().compareTo(o2.getTeacher()) == 0 ?
                        o1.getUnit().compareTo(o2.getUnit()) : o1.getTeacher().compareTo(o2.getTeacher()));
        // add lessons (automatically sorted by comparator)
        sch.getSchedules().forEach((sc, ll) -> allLessonsSortedByTeacherThenUnit.addAll(ll));
        // output lessons and sum of lessons
        allLessonsSortedByTeacherThenUnit.forEach(l -> System.out.println(l.toString()));
        System.out.println("#Items " + allLessonsSortedByTeacherThenUnit.size());
    }
}
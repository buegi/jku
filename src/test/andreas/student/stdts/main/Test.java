package test.andreas.student.stdts.main;

import test.andreas.student.stdts.Student;
import test.andreas.student.stdts.StudentList;

public class Test {

    public static void main(String[] args) {

        StudentList stlist = new StudentList();

        Student s1 = new Student("11", "111", 11111111);
        Student s2 = new Student("33", "333", 33333333);
        Student s3 = new Student("22", "222", 22222222);

        stlist.insert(s1);
        stlist.insert(s2);
        stlist.insert(s3);

        System.out.println(stlist.toString());
    }
}
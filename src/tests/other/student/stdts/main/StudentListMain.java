package tests.other.student.stdts.main;

import tests.other.student.inout.In;
import tests.other.student.inout.Out;
import tests.other.student.stdts.Student;
import tests.other.student.stdts.StudentList;

public class StudentListMain {

    public static void main(String[] args) {

        // TODO erzeugen der StudentList
        StudentList list = new StudentList();
        // Read students from students.csv and build up studentsList
        In.open("students.csv");
        String line = In.readLine();
        while (line.length() > 0) {
            String[] elems = line.split(";");
            String lastName = elems[0];
            String firstName = elems[1];
            int stdtId = Integer.parseInt(elems[2]);

            // TODO: fill studentList
            list.insert(new Student(firstName, lastName,stdtId));

            line = In.readLine();
        }
        In.close();

        // Read points from results.csv and set points for assignments of students
        In.open("results.csv");
        line = In.readLine();
        while (line.length() > 0) {
            String[] elems = line.split(";");
            int stdtId = Integer.parseInt(elems[0]);
            int p1 = Integer.parseInt(elems[1]);
            int p2 = Integer.parseInt(elems[2]);
            int p3 = Integer.parseInt(elems[3]);
            int p4 = Integer.parseInt(elems[4]);
            int p5 = Integer.parseInt(elems[5]);
            int p6 = Integer.parseInt(elems[6]);

            // TODO: set points

            line = In.readLine();
        }
        In.close();

        // TODO: compute and output results of students

        // TODO: Build list of students for different grades and print them

    }

}

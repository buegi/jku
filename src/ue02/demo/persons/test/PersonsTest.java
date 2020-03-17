//package ue02.demo.persons.test;
//
//import inout.Out;
//import persons.Person;
//import persons.PersonException;
//import persons.Professor;
//import persons.Student;
//import persons.Study;
//
//public class PersonsTest {
//
//	/**
//	 * Test class for persons class system
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		testStudent();
//		testProfessor();
//		testCallsExceptions();
//	}
//
//	private static void testStudent() {
//		Student s = new Student("Franz", Study.CS);
//
//		if (! s.getName().equals("Franz")) {
//			Out.println("Error: in name of Student");
//		} else {
//			Out.println("OK: name of Student");
//		}
//
//		if (s.getStudy() != Study.CS) {
//			Out.println("Error: in study of Student");
//		} else {
//			Out.println("OK: study of Student");
//		}
//
//		s.setStudy(Study.IE);
//		if (s.getStudy() != Study.IE) {
//			Out.println("Error: in setStudy of Student");
//		} else {
//			Out.println("OK: setStudy of Student");
//		}
//	}
//
//	private static void testGiro_positiveWithdrawl() {
//		GiroKonto g = new Giro(1000, -5000);
//		try {
//			g.withdraw(600);
//			if(g.balance() != 400) {
//				Out.println("Error: Withdrawl in positive range did not work as expected");
//			}
//		} catch(Exception ex) {
//			Out.println("Error: Withdrawl in positive range threw an exception did not work as expected");
//		}
//	}
//
//	private static void testGiro_negativeWithdrawl() {
//		GiroKonto g = new Giro(1000, -5000);
//		g.withdraw(1200);
//
//		if(g.balance() != -200) {
//			Out.println("Error: Withdrawl in positive range did not work as expected");
//		}
//	}
//
//	private static void testGiro_tooMuchWithdrawl() {
//		GiroKonto g = new Giro(1000, -5000);
//
//		g.withdraw(6500);
//
//		if(g.balance() != -200) {
//			Out.println("Error: Withdrawl in positive range did not work as expected");
//		}
//	}
//
//	private static void testProfessor() {
//		Professor p = new Professor("Wirth", "Programming languages");
//		if (! p.getName().equals("Wirth")) {
//			Out.println("Error: in name of Student");
//		} else {
//			Out.println("OK: name of Professor");
//		}
//
//		if (! p.getArea().equals("Programming languages")) {
//			Out.println("Error in area of professor");
//		} else {
//			Out.println("OK: area of Professor");
//		}
//
//	}
//
//	private static void testCallsExceptions() {
//		Student stdt = new Student("Hans", Study.CS);
//		Professor prof = new Professor("Wirth", "Software");
//
//		Person p1 = stdt;
//		Person p2 = prof;
//
//		try {
//			p2.calls(p1);
//			Out.println("OK: Professor calls Student");
//		} catch (PersonException e) {
//			Out.println("Error: exception in professor calls student");
//		}
//
//		try {
//			p1.calls(p2);
//			Out.println("Error: should throw exception in student calls professor");
//		} catch (PersonException e) {
//			Out.println("OK: exception in student calls professor");
//		}
//
//	}
//
//}

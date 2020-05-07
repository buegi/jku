package swe2.ss19.ue02.app;

import swe2.ss19.ue02.employee.*;
import swe2.ss19.ue02.exception.*;

import swe2.ss19.ue02.inout.*;

public class TestEmployee {

    public static void main(String[] args) throws SupervisorException {

        Manager wendy = new Manager("Wendy M. Grace", 7000, 5000);
        Manager lee = new Manager("Lee C. Norris", 6000, 10000);

        Expert raquel = new Expert("Raquel A. Swanson", wendy, 3500);
        Expert mary = new Expert("Mary C. Lucas", wendy, 3000);
        Expert yvonne = new Expert("Yvonne S. Hamilton", lee, 4000);
        Expert michael = new Expert("Michael D. Madden", lee, 2000);

        Assistant joe = new Assistant("Joe S. Burder", raquel, 2000);
        Assistant cliff = new Assistant("Cliff T. Smith", mary, 1800);
        Assistant julia = new Assistant("Julia Ginswein", yvonne, 1900);
        Assistant susan = new Assistant("Susan McIllroy", michael, 2100);

        Employee[] employees = new Employee[]{wendy, lee, raquel, mary, yvonne, michael, joe, cliff, julia, susan};

        Out.println("Current salaries:");
        for (Employee employee : employees) {
            Out.println(employee.toString());
            Out.println("  Salary/month: " + employee.getMonthlySalary());
            Out.println("  Salary/year: " + employee.getAnnualSalary());
        }

        Out.println();

        for (Employee employee : employees) {
            Out.println(employee.toString() + " working: ");
            for (Work work : Work.values()) {
                try {
                    employee.doWork(work);
                } catch (WorkException excpt) {
                    Out.format("Exception: %s %n", excpt.getMessage());
                }
            }
            Out.println();
        }

        // test cases for setSupervisor
        // try to set manager as supervisor for assistant => exception
        try {
            joe.setSupervisor(wendy);
        } catch (SupervisorException e) {
            joe.printSupervisorNotSet();
        }

        // try to set assistant as supervisor for assistant => exception
        try {
            joe.setSupervisor(cliff);
        } catch (SupervisorException e) {
            joe.printSupervisorNotSet();
        }

        // try to set expert as supervisor for assistant => OK
        try {
            joe.setSupervisor(michael);
        } catch (SupervisorException e) {
            joe.printSupervisorNotSet();
        }

        // try to set assistant as supervisor for expert => exception
        try {
            raquel.setSupervisor(cliff);
        } catch (SupervisorException e) {
            raquel.printSupervisorNotSet();
        }

        try {
            // try to set expert as supervisor for expert => exception
            raquel.setSupervisor(mary);
        } catch (SupervisorException e) {
            raquel.printSupervisorNotSet();
        }

        // try to set manager as supervisor for expert => OK
        try {
            raquel.setSupervisor(wendy);
        } catch (SupervisorException e) {
            raquel.printSupervisorNotSet();
        }
    }
}
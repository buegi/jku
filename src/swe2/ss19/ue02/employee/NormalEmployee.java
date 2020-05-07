package swe2.ss19.ue02.employee;

import swe2.ss19.ue02.exception.SupervisorException;

public abstract class NormalEmployee extends Employee {

	protected Employee supervisor;

	public NormalEmployee(String name, Double salary, Employee supervisor) {
		super(name, salary);
		this.supervisor = supervisor;
	}

	public void setSupervisor(Employee supervisor) throws SupervisorException {

		// if Assistant => supervisor must be expert
		if ((this instanceof Assistant) && (supervisor instanceof Expert)) {
			this.supervisor = supervisor;
			// if Expert => supervisor must be manager
		} else if ((this instanceof Expert) && (supervisor instanceof Manager)) {
			this.supervisor = supervisor;
			// throw exception if supervisor not matching restrictions
		} else {
			throw new SupervisorException("Wrong type of supervisor! Supervisor not set!", supervisor);
		}
	}

	public Employee getSupervisor() {
		return this.supervisor;
	}

	public double getAnnualSalary() {
		return (super.monthlySalary * 14);
	}

	public void printSupervisorNotSet() {
		System.out.println("Wrong type of supervisor! Supervisor not set!");
	}

	protected void printRedirectToSupervisor() {
		System.out.println(this.getName() + ": I will redirect this to my supervisor!");
	}

	protected void printDealInBestManner() {
		System.out.println(this.getName() + ": I will deal with this in the best manner!");
	}

}
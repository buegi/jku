package ss19.ue02.employee;

import ss19.ue02.exception.WorkException;

public class Manager extends Employee {

	protected double bonus;

	public Manager(String name, double salary, double bonus) {
		super(name, salary);
		this.bonus = bonus;
	}

	protected double getBonus() {
		return bonus;
	}

	protected void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getAnnualSalary() {
		return ((super.getMonthlySalary() * 12) + this.getBonus());
	}

	protected void printSignContract() {
		System.out.println(this.getName() + ": I will gladly sign the contract!");
	}

	public void doWork(Work work) throws ss19.ue02.exception.WorkException {

		switch (work) {

		case SignContract:
			this.printSignContract();
			break;

		case Test:
			throw new WorkException(super.WorkException, work);

		case Implement:
			throw new WorkException(super.WorkException, work);

		case Design:
			throw new WorkException(super.WorkException, work);
		}
	}
}
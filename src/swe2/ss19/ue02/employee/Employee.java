package swe2.ss19.ue02.employee;

public abstract class Employee {

	protected String name;
	protected double monthlySalary;
	protected final String WorkException = "I am not responsible for this kind of work!";

	public Employee(String name, Double salary) {
		this.name = name;
		this.monthlySalary = salary;
	}

	public abstract double getAnnualSalary();

	public abstract void doWork(Work work) throws swe2.ss19.ue02.exception.WorkException;

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public double getMonthlySalary() {
		return monthlySalary;
	}

	protected void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		Employee emp = (Employee) other;
		if (name == null) {
			if (emp.name != null)
				return false;
		} else if (!name.equals(emp.name))
			return false;
		return true;
	}
}
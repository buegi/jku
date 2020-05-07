package swe2.ss19.ue02.employee;

public class Assistant extends NormalEmployee {

	public Assistant(String name, Expert expert, double salary) {
		super(name, salary, expert);
	}

	public void doWork(Work work) throws swe2.ss19.ue02.exception.WorkException {
		if ((work == Work.Implement) || (work == Work.Test)) {
			this.printDealInBestManner();

		} else {
			this.printRedirectToSupervisor();
			this.getSupervisor().doWork(work);
		}
	}
}
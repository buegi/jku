package ss19.ue02;

public class Expert extends NormalEmployee {

	public Expert(String name, Manager manager, double salary) {
		super(name, salary, manager);
	}

	public void doWork(Work work) throws WorkException {
		if (work == Work.Test) {
			throw new WorkException(super.WorkException, work);

		} else if ((work == Work.Design) || (work == Work.Implement)) {
			this.printDealInBestManner();
		} else {
			this.printRedirectToSupervisor();
			this.getSupervisor().doWork(work);
		}
	}
}
package ss19.ue02;

public class Assistant extends NormalEmployee {

	public Assistant(String name, Expert expert, double salary) {
		super(name, salary, expert);
	}

	public void doWork(Work work) throws WorkException {
		if ((work == Work.Implement) || (work == Work.Test)) {
			this.printDealInBestManner();

		} else {
			this.printRedirectToSupervisor();
			this.getSupervisor().doWork(work);
		}
	}
}
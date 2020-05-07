package ss19.ue01;

import java.time.*;

public final class Calendar {

	private List appList;

	public Calendar() {
		this.appList = new List();
	}

	public boolean addAppointment(Appointment newApp) {

		Node newNode = new Node(newApp);

		// if list is empty, create head node
		if (appList.isEmpty()) {
			return appList.insertAsFirst(newNode);
		}

		Node current = appList.getFirst();

		while (current != null) {

			// only one node exists and node must be inserted as new head
			if ((current.getPrev() == null) && (current.getNext() == null)
					&& (newNode.getAppointment().getEnd().isBefore(current.getAppointment().getStart()))) {
				appList.insertAsFirst(newNode);
				return true;
			}

			// no previous node
			if ((current.getPrev() == null)
					&& (newNode.getAppointment().getEnd().isBefore(current.getAppointment().getStart()))) {
				return appList.insertBefore(current, newNode);
			}
			// no next node
			if ((current.getNext() == null)
					&& (newNode.getAppointment().getStart().isAfter(current.getAppointment().getEnd()))) {
				return appList.insertAfter(current, newNode);
			}

			// existing previous node, newapp start after previous end and newapp current
			// start
			if ((current.getPrev() != null)
					&& (newNode.getAppointment().getStart().isAfter(current.getPrev().getAppointment().getEnd())
							&& (newNode.getAppointment().getEnd().isBefore(current.getAppointment().getStart())))) {
				return appList.insertBefore(current, newNode);

			}
			// existing next node, newapp start after current end and newapp end before next
			// start
			if ((current.getNext() != null) && (newNode.getAppointment().getStart()
					.isAfter(current.getAppointment().getEnd())
					&& (newNode.getAppointment().getEnd().isBefore(current.getNext().getAppointment().getStart())))) {
				return appList.insertAfter(current, newNode);
			}

			current = current.getNext();

		}

		return false;

	}

	public Appointment getAppointmentNextToTime(LocalDateTime time) {
		Node current = appList.getFirst();

		while (current != null) {

			if (current.getAppointment().getEnd().isAfter(time)) {
				return current.getAppointment();

			}

			current = current.getNext();
		}

		return null; // TODO

	}

	public Appointment getAppointmentFirstofDay(LocalDate date) {
		Node current = appList.getFirst();

		while (current != null) {
			if (current.getAppointment().getDate().isEqual(date)) {
				return current.getAppointment();
			}

			current = current.getNext();
		}
		return null;

	}

	public Appointment getNextAppointment(Appointment current) {
		Node actual = appList.getFirst();

		while ((actual != null)) {
			if ((actual.getAppointment() == current) && (actual.getNext() != null)) {
				return actual.getNext().getAppointment();
			}

			actual = actual.getNext();
		}

		return null;

	}

	public boolean removeAppointment(int id) {
		return appList.remove(id);
	}

	public void printAll() {
		Node current = appList.getFirst();

		while (current != null) {

			System.out.println(current.getAppointment().toString());

			current = current.getNext();

		}

	}

}
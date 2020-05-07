package swe2.ss19.ue01.calendar;

public class Node {

	// this nodes appointment data
	private Appointment appointment;
	// next node
	private Node next;
	// previous
	private Node prev;

	// Constructor for setting appointment and next node
	Node(Appointment appointment) {
		this.appointment = appointment;
	}

	Appointment getAppointment() {
		return appointment;
	}

	void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	Node getNext() {
		return next;
	}

	void setNext(Node next) {
		this.next = next;
	}

	Node getPrev() {
		return prev;
	}

	void setPrev(Node prev) {
		this.prev = prev;
	}

}
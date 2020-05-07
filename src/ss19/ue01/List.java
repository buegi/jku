package ss19.ue01;

public class List {

	private Node first;

	List() {
		this.first = null;
	}

	boolean isEmpty() {
		return first == null;
	}

	Node getFirst() {
		return first;
	}

	void setFirst(Node first) {
		this.first = first;
	}

	boolean insertAsFirst(Node newNode) {
		if (this.getFirst() == null) {
			this.setFirst(newNode);
			return true;
		} else {
			return false;
		}
	}

	boolean insertAfter(Node current, Node newNode) {
		newNode.setPrev(current);
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		return true;
	}

	boolean insertBefore(Node current, Node newNode) {
		if (current == first) {
			newNode.setNext(first);
			first = newNode;
			return true;
		} else {
			newNode.setPrev(current.getPrev());
			newNode.setNext(current);
			current.setPrev(newNode);
			return true;
		}
	}

	boolean remove(int id) {
		Node current = first;

		while (current != null) {

			// check Appointment ID
			if (current.getAppointment().getId() == id) {

				// if head has to be deleted
				if (current == first) {
					first = current.getNext();
					return true;
				}

				// if no previous entry for node
				if (current.getPrev() != null) {
					current.getPrev().setNext(current.getNext());
				}
				// if no next entry for node
				if (current.getNext() != null) {
					current.getNext().setPrev(current.getPrev());
				}
				return true;
			}
			current = current.getNext();
		}

		return false;
	}
}
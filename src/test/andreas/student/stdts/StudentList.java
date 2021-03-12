package test.andreas.student.stdts;

public class StudentList {

    private Node head;
    private int entries;

    public StudentList() {
        this.entries = 0;
    }

    class Node {
        private Student val;
        private Node next;

        Node(Student student, Node next) {
            this.val = student;
            this.next = next;
        }

        Node(Student student) {
            this.val = student;
        }

        Student getVal() {
            return this.val;
        }

        Node getNext() {
            return this.next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        void setVal(Student val) {
            this.val = val;
        }

    }

    boolean isEmpty() {
        return this.head == null && this.entries == 0;
    }

    public boolean insert(Student student) {
        // TODO insert
        if (isEmpty()) {
            head = new Node(student);
            entries++;
            System.out.println(head);
            return true;
        }
        Node act = head;
        Node prv = null;

        while (!isEmpty() && act.next != null) {
            prv = act;
            act = act.getNext();
        }

        if (act.getNext() == null) { // tail
            act.next = new Node(student);
            entries++;
            return true;
        }

        if (act.getNext() != null) {
            Node temp = new Node(student);
            prv.setNext(temp);
            temp.setNext(act);
            entries++;
            return true;
        }
        return false;
    }

    Student remove(Student student) {
        // TODO remove
        return student;
    }


    StudentList StudenListByGrade() {
        // TODO
        return new StudentList();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Node act = head;

        while (!isEmpty() && act != null) {
            str.append(act.getVal().getFirstName() + " ");
            act = act.getNext();
        }
        return str.toString();
    }
}
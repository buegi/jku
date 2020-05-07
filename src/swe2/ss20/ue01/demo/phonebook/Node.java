package swe2.ss20.ue01.demo.phonebook;

/**
 * Class implementing the nodes for the linear list of phone book entries
 */
final class Node {

    /**
     * the entry value
     */
    Entry entry;

    /**
     * the next node
     */
    Node next;

    /**
     * Constructor for setting entry and next node
     *
     * @param entry the entry value
     * @param next  the next node reference
     */
    Node(Entry entry, Node next) {
        this.entry = entry;
        this.next = next;
    }

    /**
     * Constructor for setting entry
     *
     * @param entry the entry value
     */
    Node(Entry entry) {
        this(entry, null);
    }

}
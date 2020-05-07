package swe2.ss20.ue01.demo.phonebook;

/**
 * Class EntryList implements a linked list for phone book entries. Stores the entries in
 * alphabetical order.
 */
final class EntryList {

    /**
     * head of the list
     */
    private Node head;

    /**
     * Constructor
     */
    EntryList() {
        head = null;
    }

    /**
     * Insert a new entry with name, area code and phone number.
     * Maintains an alphabetical order.
     *
     * @param name     name of the new entry
     * @param areaCode area code of the new entry
     * @param number   phone number of the new entry
     * @return the newly inserted node
     */
    Node insertEntry(String name, String areaCode, String number) {
        return insertEntry(new Entry(name, areaCode, number));
    }

    /**
     * Inserts an entry value or replaces existing with equal name.
     * Replaces an existing entry with equal name.
     * Maintains an alphabetical order.
     *
     * @param entry the new entry
     * @return the newly inserted node
     */
    Node insertEntry(Entry entry) {
        Node pred = null;
        Node curr = head;

        // find correct position to insert new entry
        while (curr != null
                && curr.entry.getName().compareToIgnoreCase(entry.getName()) < 0) {
            pred = curr;
            curr = curr.next;
        } // while

        if (curr != null && curr.entry.getName().equalsIgnoreCase(entry.getName())) {
            // replace entry with equal name
            curr.entry = entry;
            return curr;
        } else {
            // insert new entry in alphabetical order
            Node node = new Node(entry);
            if (pred == null) { // insert in front of head
                head = node;
            } else {
                pred.next = node;
            }
            node.next = curr;

            return node;
        }
    }

    /**
     * Looks up the entry with name.
     *
     * @param name the name of the entry
     * @return the entry node found, null if not contained
     */
    Node lookup(String name) {
        Node node = head;

        // search for entry with name >= name
        while (node != null && node.entry.getName().compareToIgnoreCase(name) < 0) {
            node = node.next;
        } // while

        if (node == null || !node.entry.getName().equalsIgnoreCase(name)) {
            return null; // not found
        }

        return node;
    }

    /**
     * Returns the first entry node in the list.
     *
     * @return the first node in the list, null if empty
     */
    Node firstEntry() {
        return head;
    }

    /**
     * Returns the next entry node for the given current node.
     *
     * @param current the given current node
     * @return the next node after the node current
     */
    Node nextEntry(Node current) {
        if (current == null) {
            return null; // no next entry
        }
        return current.next; // return next entry
    } // nextEntry

    /**
     * Returns true if list is empty, false otherwise.
     *
     * @return true if list is empty, false otherwise
     */
    boolean isEmpty() {
        return head == null;
    }

    /**
     * Deletes the current node.
     * Parameter <code>node</code> must not be null.
     *
     * @param node the node to be deleted
     */
    public void delete(Node node) {
        if (node == head) head = head.next;
        Node prev = head;
        while (prev.next != node) {
            prev = prev.next;
        }
        prev.next = node.next;
    }
}

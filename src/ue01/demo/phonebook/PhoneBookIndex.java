package ue01.demo.phonebook;

/**
 * Class realizing an index for the phone book. Consists of an
 * array for the 26 characters. Each field in the array points
 * to the first entry with the respective character. If such an
 * entry does not exist, null is contained.
 */
final class PhoneBookIndex {

    /**
     * Number of characters
     */
    private static final int NUM_CHARS = 26;

    /**
     * Array to store references to first entry node of characters.
     */
    private final Node[] index = new Node[NUM_CHARS];

    /**
     * Gets the first entry node with or after the given character.
     *
     * @param ch the given character
     * @return the entry node with or after the given character
     */
    Node getFirstOfChar(char ch) {
        // compute index for character ch (using lower case letters only)
        int i = convertCharToIndex(ch);
        if (i < 0 || i >= NUM_CHARS) {
            return null;
        }

        // find first node after character
        while (index[i] == null && i < NUM_CHARS - 1) {
            i++;
        }
        return index[i];
    }


    /**
     * Updates the index when a new entry node has been inserted.
     * Looks if this node is the first entry with the respective character
     * and then updates the index.
     *
     * @param newNode the newly inserted node
     */
    void updateFirstOfChar(Node newNode) {
        char ch = newNode.entry.getName().charAt(0);
        Node firstOfChar = getFirstOfChar(ch);
        if (firstOfChar == null ||
                newNode.entry.getName().compareToIgnoreCase(firstOfChar.entry.getName()) < 0) {
            setFirstOfChar(ch, newNode);
        }
    }

    /**
     * Sets the first entry for the given character
     *
     * @param ch   the character
     * @param node the entry node
     */
    private void setFirstOfChar(char ch, Node node) {
        // compute index for character ch (using lower case letters only)
        int i = convertCharToIndex(ch);
        if (i < 0 || i >= NUM_CHARS) {
            return;
        }
        index[i] = node;
    }

    private static int convertCharToIndex(char ch) {
        return Character.toLowerCase(ch) - 'a';
    }

}
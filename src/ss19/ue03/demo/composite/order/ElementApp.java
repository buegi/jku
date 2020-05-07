package ss19.ue03.demo.composite.order;

public class ElementApp {

    public static void main(String[] args) {
        Element a = new Leaf("A");
        Element c = new Leaf("C");
        Element e = new Leaf("E");
        Element d = new Node(c, e, "D");
        Element b = new Node(a, d, "B");

        Element h = new Leaf("H");
        Element i = new Node(h, null, "I");
        Element g = new Node(null, i, "G");

        Element f = new Node(b, g, "F");

        // Tree:
        // https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sorted_binary_tree_preorder.svg/1199px-Sorted_binary_tree_preorder.svg.png

        // Pre-order: F, B, A, D, C, E, G, I, H.
        // f.preorder();

        // In-order: A, B, C, D, E, F, G, H, I.
        // f.inorder();

        // Post-order: A, C, E, D, B, H, I, G, F.
        f.postorder();

    }

}

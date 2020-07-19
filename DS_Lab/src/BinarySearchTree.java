public class BinarySearchTree {
    Node root;

    private class Node {
        int val;
        Node l_child;
        Node r_child;
        public Node(int d) {
            val = d;
        }
    }

    public BinarySearchTree() {
        root = null;
    }
    public BinarySearchTree(Node r) {
        root = r;
    }

    public void insert(int val) {
        root = insert(root, val);
    }

    private Node insert(Node root, int val) {
        if(root == null) {
            root = new Node(val);
        }
        else if(root.val > val) {
            root.l_child = insert(root.l_child, val);
        }
        else if(root.val < val) {
            root.r_child = insert(root.r_child, val);
        }
        return root;
    }

    public void deleteKey(int val) {
        root = deleteKey(root, val);
    }

    private Node getFirst(Node root) {
        if(root.l_child == null) {
            return root;
        }
        return getFirst(root.l_child);
    }

    private Node deleteKey(Node root, int val) {
        if (root == null) {
            return null;
        }
        else if (root.val > val)
            root.l_child = deleteKey(root.l_child, val);
        else if (root.val < val)
            root.r_child = deleteKey(root.r_child, val);
        else {
            if (root.r_child == null) {
                return root.l_child;
            } else if (root.l_child == null) {
                return root.r_child;
            } else {
                root.val = getFirst(root.r_child).val;
                root.r_child = deleteKey(root.r_child, root.val);
            }
        }
        return root;
    }

    public void inorder() {
        if(root == null)
            return;
        if(root.l_child != null) {
            BinarySearchTree l = new BinarySearchTree(root.l_child);
            l.inorder();
        }
        System.out.print(root.val + " ");
        if(root.r_child != null) {
            BinarySearchTree r = new BinarySearchTree(root.r_child);
            r.inorder();
        }
    }
    public static void main(String args[]) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal of the given tree");
        tree.inorder();

        System.out.println("\nDelete 20");
        tree.deleteKey(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 30");
        tree.deleteKey(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 50");
        tree.deleteKey(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
    }
}

import java.util.LinkedList;
import java.util.Queue;

public class AVLtree {
    Node root;

    private class Node {
        int val;
        int BF;
        Node l_child;
        Node r_child;

        public Node(int d) {
            val = d;
        }
        public void BF_calculate() {
            BF = height(this.l_child) - height(this.r_child);
        }
    }

    public AVLtree() {
        root = null;
    }
    public AVLtree(Node r) {
        root = r;
    }

    public void insert(int val) {
        root = insert(root, val);
    }

    private Node insert(Node root, int val) {
        if(root == null) {
           return new Node(val);
        }
        else if(root.val > val) {
            root.l_child = insert(root.l_child, val);
        }
        else if(root.val < val) {
            root.r_child = insert(root.r_child, val);
        }
        else {
            return root;
        }

        int balance = root.BF;
        //Left Left Case
        if (balance > 1 && val < root.l_child.val)
            return rightRotate(root);

        // Right Right Case
        if (balance < -1 && val > root.r_child.val)
            return leftRotate(root);

        // Left Right Case
        if (balance > 1 && val > root.l_child.val) {
            root.l_child = leftRotate(root.l_child);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && val < root.r_child.val) {
            root.r_child = rightRotate(root.r_child);
            return leftRotate(root);
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
            } else if (root.l_child == null ) {
                return root.r_child;
            } else {
                root.val = getFirst(root.r_child).val;
                root.r_child = deleteKey(root.r_child, root.val);
            }
        }

        int balance = root.BF;
        if (balance > 1 && val < root.l_child.val)
            return rightRotate(root);

        // Right Right Case
        if (balance < -1 && val > root.r_child.val)
            return leftRotate(root);

        // Left Right Case
        if (balance > 1 && val > root.l_child.val) {
            root.l_child = leftRotate(root.l_child);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && val < root.r_child.val) {
            root.r_child = rightRotate(root.r_child);
            return leftRotate(root);
        }

        return root;
    }

    public int height(Node node) {
        if(node == null) {
            return 0;
        }
        return(max(height(node.l_child), height(node.r_child)) + 1);
    }

    private int max(int h1, int h2) {
        if(h1 > h2)
            return h1;
        else
            return h2;
    }

    public Node rightRotate(Node x) {
        Node y = x.l_child;
        Node z = y.r_child;
        y.r_child = x;
        x.l_child = z;
        return y;
    }

    public Node leftRotate(Node x) {
        Node y = x.r_child;
        Node z = y.l_child;
        y.l_child = x;
        x.r_child = z;
        return y;
    }

    private void BF_update(Node root) {
        root.BF_calculate();
        if(root.l_child != null) {
            BF_update(root.l_child);
        }
        if(root.r_child != null) {
            BF_update(root.r_child);
        }
    }

    public void inorder() {
        if(root == null)
            return;
        if(root.l_child != null) {
            AVLtree l = new AVLtree(root.l_child);
            l.inorder();
        }
        System.out.print(root.val + " ");
        if(root.r_child != null) {
            AVLtree r = new AVLtree(root.r_child);
            r.inorder();
        }
    }

    String levelorder() {
        String s = "";
        Node t = root;
        Queue<Node> q = new LinkedList<>();
        while(t != null) {
            s += t.val + " ";
            if(t.l_child != null)
                q.add(t.l_child);
            if(t.r_child != null)
                q.add(t.r_child);
            if(!q.isEmpty())
                t = q.poll();
            else
                break;
        }
        return s;
    }

    public static void main(String[] args) {
        AVLtree tree = new AVLtree();
        tree.insert(100);
        tree.insert(10);
        tree.insert(20);
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(90);
        tree.deleteKey(50);
        System.out.println(tree.levelorder());
    }
}

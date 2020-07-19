import java.util.LinkedList;
import java.util.Queue;

public class RBtree {
    Node root;

    public RBtree() {
        root = null;
    }

    public void insert(int val) {
        Node x = new Node(val);
        root = insert(root, x);
        balanceColor(x);
    }

    private Node insert(Node root, Node x) {
        if(root == null) {
            root = x;
        }
        else if(root.val > x.val) {
            root.l_child = insert(root.l_child, x);
            root.l_child.parent = root;
        }
        else if(root.val < x.val) {
            root.r_child = insert(root.r_child, x);
            root.r_child.parent = root;
        }
        return root;
    }

    public Node getGrandparent(Node x) {
        if(x.parent != null)
            return x.parent.parent;
        else return null;
    }

    public Node getUncle(Node x) {
        Node g = getGrandparent(x);
        if(x == null)
            return null;
        if(x.parent == g.l_child)
            return g.r_child;
        else
            return g.l_child;
    }

    public void swapColor(Node x, Node y) {
        char tmp = x.color;
        x.setColor(y.color);
        y.setColor(tmp);
    }

    public void balanceColor(Node x) {
        //first, set the newly inserted node's color as "RED",
        x.setColor('R');
        while (x != root && x.color != 'B' && x.parent.color == 'R') {
            Node parent = x.parent;
            Node uncle = getUncle(x);
            Node grandparent = getGrandparent(x);

            if (uncle != null && uncle.color == 'R') {
                grandparent.setColor('R');
                parent.setColor('B');
                uncle.setColor('B');
                x = grandparent;
            }
            else if (parent == grandparent.l_child) {
                if (x == parent.r_child) {
                    leftRotate(parent);
                    x = parent;
                    parent = grandparent;
                }
                rightRotate(grandparent);
                swapColor(parent, grandparent);
                x = parent;
            }
            //parent == grandparent.r_child
            else {
                if (x == parent.l_child) {
                    rightRotate(parent);
                    x = parent;
                    parent = grandparent;
                }
                leftRotate(grandparent);
                swapColor(parent, grandparent);
                x = parent;
            }
        }
        root.color = 'B';
    }
    public void rightRotate(Node x) {
        Node y = x.l_child;
        Node z = y.r_child;
        y.r_child = x;
        x.l_child = z;
        if(z != null) {
            z.parent = x;
        }
        y.parent = x.parent;
        if(x == root) {
            root = y;
        }
        else if(x == x.parent.l_child) {
            x.parent.l_child = y;
        }
        else {
            x.parent.r_child = y;
        }
        x.parent = y;
    }

    public void leftRotate(Node x) {
        Node y = x.r_child;
        Node z = y.l_child;
        y.l_child = x;
        x.r_child = z;
        if(z != null) {
            z.parent = x;
        }
        y.parent = x.parent;
        if(x == root) {
            root = y;
        }
        else if(x == x.parent.l_child) {
            x.parent.l_child = y;
        }
        else {
            x.parent.r_child = y;
        }
        x.parent = y;
    }

    String levelorder() {
        String s = "";
        Node t = root;
        Queue<Node> q = new LinkedList<>();
        while(t != null) {
            s += t.val + "(" + t.color + ")" + " ";
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

    private class Node {
        int val;
        char color;
        Node l_child;
        Node r_child;
        Node parent;

        public Node(int d) {
            val = d;
        }

        public void setColor(char color) {
            this.color = color;
        }
    }

    public static void main(String[] args) {
        RBtree tree = new RBtree();
        tree.insert(7);
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        System.out.println(tree.levelorder());
    }
}

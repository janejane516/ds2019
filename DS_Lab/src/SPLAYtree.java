import java.util.LinkedList;
import java.util.Queue;

public class SPLAYtree {
    Node root;

    public class Node {
        int val;
        Node l_child;
        Node r_child;
        Node parent;
        public Node(int d) {
            val = d;
        }
    }

    public SPLAYtree() {
        root = null;
    }

    public SPLAYtree(Node r) {
        root = r;
    }

    public void insert(int val) {
        Node x = new Node(val);
        root = insert(root, x);
        splay(x);
    }

    private Node insert(Node root, Node x) {
        if(root == null) {
            root = x;
        }
        else if(root.val > x.val) {
            root.l_child = insert(root.l_child, x);
            root.l_child.parent = root;
        }
        else {
            root.r_child = insert(root.r_child, x);
            root.r_child.parent = root;
        }
        return root;
    }

    public Node find(int val) {
        splay(find(root, val));
        return root;
    }

    public void remove(int val) {
        Node x = find(val);
        if(x.l_child == null) {
            root = x.r_child;
        }
        else if(x.r_child == null) {
            root = x.l_child;
        }
        else  {
            SPLAYtree l = new SPLAYtree(x.l_child);
            SPLAYtree r = new SPLAYtree(x.r_child);
            l.root.parent = null;
            r.root.parent = null;
            if(l.root.r_child != null) {
                Node m = findMax(l.root);
                l.splay(m);
                if (m.l_child == null) {
                    m.l_child = r.root;
                    m.l_child.parent = m;
                } else if (m.r_child == null) {
                    m.r_child = r.root;
                    m.r_child.parent = m;
                }
            }
            else {
                l.root.r_child = r.root;
                l.root.r_child.parent = l.root;
            }
            root = l.root;
        }
    }


    public Node findMax(Node x) {
        Node max = x;
        while(max.r_child != null) {
            max = max.r_child;
        }
        return max;
    }

    private Node find(Node root, int val) {
        if(root == null) {
            return null;
        }
        else if(root.val == val)  {
            return root;
        }
        else if(root.val > val) {
            if(root.l_child == null)
                splay(root);
            return find(root.l_child, val);
        }
        else {
            if(root.r_child == null)
                splay(root);
            return find(root.r_child, val);
        }
    }

    public void splay(Node x) {
        while(x.parent != null) {
            if (x.parent.parent == null) {
                if (x == x.parent.l_child) {
                    rightRotate(x.parent);
                } else {
                    leftRotate(x.parent);
                }
            } else if (x == x.parent.l_child && x.parent == x.parent.parent.l_child) {
                rightRotate(x.parent.parent);
                rightRotate(x.parent);
            } else if (x == x.parent.r_child && x.parent == x.parent.parent.r_child) {
                leftRotate(x.parent.parent);
                leftRotate(x.parent);
            } else if (x == x.parent.r_child && x.parent == x.parent.parent.l_child) {
                leftRotate(x.parent);
                rightRotate(x.parent);
            } else {
                rightRotate(x.parent);
                leftRotate(x.parent);
            }
        }
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
        if(x.parent == null) {
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
        if(x.parent == null) {
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

    public String inorder() {
        String s = "";
        if (root == null) {
            return null;
        }
        if (root.l_child != null) {
            SPLAYtree l = new SPLAYtree(root.l_child);
            s += l.inorder();
        }
        s += root.val + " ";
        if (root.r_child != null) {
            SPLAYtree r = new SPLAYtree(root.r_child);
            s += r.inorder();
        }
        return s;
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
        SPLAYtree tree = new SPLAYtree();
        tree.insert(7);
        tree.insert(3);
        tree.insert(5);
        tree.insert(4);
        tree.insert(1);
        tree.insert(2);
        tree.insert(9);
        System.out.println(tree.levelorder());
        tree.remove(2);
        System.out.println(tree.levelorder());
        tree.remove(7);
        System.out.println(tree.levelorder());
    }
}

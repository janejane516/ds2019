import java.util.Random;

public class Treap {
    Random random;
    Node root;

    public Treap() {
        root = null;
        random = new Random();
    }

    public Treap(Node r) {
        root = r;
        random = new Random();
    }

    public class Node {
        Object key;
        int priority;
        Node left;
        Node right;

        public Node(Object K) {
            if(K instanceof Node) {
                key = ((Node) K).key;
                priority = ((Node) K).priority;
            }
            else {
                key = K;
                priority = random.nextInt(1000) % 100;
            }
        }

        public int compareTo(Object k) {
            if(k instanceof Node) {
                return this.compareTo(((Node) k).key);
            }
            else if (key instanceof Integer) {
                return ((Integer)key - (Integer)k);
            }
            else {
                return ((String)key).compareTo((String)k);
            }
        }
    }

    public void insert(Object X) {
        root = insert(root, X);
    }

    private Node insert(Node root, Object X) {
        if (root == null) {
           root = new Node(X);
        }
        else if (root.compareTo(X) >= 0) {
            root.left = insert(root.left, X);
            if(root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        }
        else {
            root.right = insert(root.right, X);
            if(root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    public Node rightRotate(Node x) {
        Node y = x.left;
        Node z = y.right;
        y.right = x;
        x.left = z;
        return y;
    }

    public Node leftRotate(Node x) {
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        x.right = z;
        return y;
    }

    public Node search(Object X) {
        return search(root, X);
    }

    private Node search(Node root, Object X) {
        if(root == null) {
            System.out.println(X + " not found");
            return null;
        }
        if(root.compareTo(X) == 0) {
            return root;
        }
        if(root.compareTo(X) > 0) {
            return search(root.left, X);
        }
        else {
            return search(root.right, X);
        }
    }

    public void remove(Object X) {
        if(!(X instanceof Node))
            System.out.println("Delete " + X);
        root = remove(root, X);
    }

    private Node remove(Node root, Object X) {
        if(root == null) {
            return null;
        }
        if(root.compareTo(X) > 0) {
            root.left = remove(root.left, X);
        }
        else if(root.compareTo(X) < 0) {
            root.right = remove(root.right, X);
        }
        else if(root.left == null) {
            root = root.right;
        }
        else if(root.right == null) {
            root = root.left;
        }
        else if(root.left.priority < root.right.priority) {
            root = leftRotate(root);
            root.left = remove(root.left, X);
        }
        else {
            root = rightRotate(root);
            root.right = remove(root.right, X);
        }
        return root;
    }

    public void heapify(Node x) {
        if(x == null)
            return;
        Node max = x;
        if(x.left != null && x.left.priority > max.priority) {
            max = x.left;
        }
        if(x.right != null && x.right.priority > max.priority) {
            max = x.right;
        }
        if(max != x) {
            Object tmp1 = max.key;
            int tmp2 = max.priority;
            max.key = x.key;
            max.priority = x.priority;
            x.key = tmp1;
            x.priority = tmp2;
            heapify(max);
        }
    }

    public void build(Object[] X) {
        for(int i=0; i<X.length; i++)
            insert(X[i]);
    }


    public Treap union(Treap T1, Treap T2) {
        if(T1.root==null) {
            return T2;
        }
        if(T2.root==null) {
            T2.root = T1.root;
        }
        else if(T1.root.priority > T2.root.priority) {
            T2 = union(T2, T1);
        }
        else {
            if(T1.root.left != null) {
               Treap T1_left = new Treap(T1.root.left);
               union(T1_left, T2);
            }
            if(T1.root.right != null) {
                Treap T1_right = new Treap(T1.root.right);
                union(T1_right, T2);
            }
            T2.insert(T1.root);
            T1.remove(root);
        }
        return T2;
    }

    public void Inorder() {
        if(root == null)
            return;
        if(root.left != null) {
            Treap l = new Treap(root.left);
            l.Inorder();
        }

        System.out.print("(key, priority): ");
        System.out.print("(" + root.key + ", " + root.priority + ")");
        if(root.left != null) {
            System.out.print(" , left child " + root.left.key);
        }
        if(root.right != null) {
            System.out.print(" , right child " + root.right.key);
        }
        System.out.println();

        if(root.right != null) {
            Treap r = new Treap(root.right);
            r.Inorder();
        }

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Sorts {
    public static void main (String[] args)
            throws FileNotFoundException {
        String path = args[0];
        PrintStream out = new PrintStream(new File("src\\output.txt"));
        int n = Integer.parseInt(path);
        int[] arr = new int[n];
        Random random = new Random();
        long getTime1 = 0, getTime2 = 0, getTime3 = 0, getTime4 = 0;

        for(int j=0; j<100; j++) {
            for (int i = 0; i < n; i++) {
                arr[i] = random.nextInt(n * 20) + 1;
            }
            //Creating objects
            myHeap Heap_without_init = new myHeap();
            myHeap Heap_with_init = new myHeap(arr);
            myBST BST = new myBST();
            mySPT SPT = new mySPT();

            //Sorting 1 (Heap without initialization)
            long startTime1 = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                Heap_without_init.insert(arr[i]);
            }
            Heap_without_init.sort();
            for (int i = 1; i <= n; i++) {
                out.print(Heap_without_init.heap[i] + " ");
            }
            out.println();
            getTime1 += System.currentTimeMillis() - startTime1;

            //Sorting 2 (Heap with initialization)
            long startTime2 = System.currentTimeMillis();
            Heap_with_init.initialize();
            Heap_with_init.sort();
            for (int i = 1; i <= n; i++) {
                out.print(Heap_with_init.heap[i] + " ");
            }
            out.println();
            getTime2 += System.currentTimeMillis() - startTime2;

            //Sorting 3 (Binary Search Tree)
            long startTime3 = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                BST.insert(arr[i]);
            }
            String sort3 = BST.inorder();
            getTime3 += System.currentTimeMillis() - startTime3;
            out.println(sort3);

            //Sorting 4 (Splay Tree)
            long startTime4 = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                SPT.insert(arr[i]);
            }
            String sort4 = SPT.inorder();
            getTime4 += System.currentTimeMillis() - startTime4;
            out.println(sort4);

            out.println();
        }
        System.out.println(getTime1 + " ms heapsort (without heap initialization)");
        System.out.println(getTime2 + " ms heapsort");
        System.out.println(getTime3 + " ms BST sort");
        System.out.println(getTime4 + " ms splay tree sort");
        System.out.println("the sorted file is \"output.txt\"");
    }

    public static class myHeap {
        int[] heap;
        int n;
        int capacity = 10000;

        public myHeap(int[] arr) {
            n = arr.length;
            heap = new int[n + 1];
            for(int i = 0; i < n; i++) {
                heap[i+1] = arr[i];
            }
        }

        public myHeap() {
            heap = new int[capacity + 1];
            n = 0;
        }

        public void insert(int elem) {
            n++;
            heap[n] = elem;
            int k = n;
            while(k != 1 && heap[k] > heap[k/2]) {
                int tmp = heap[k];
                heap[k] = heap[k/2];
                heap[k/2] = tmp;
                k = k/2;
            }
        }

        public void heapify(int k, int size) {
            int max = k;
            if(size >= 2*k && heap[2*k] > heap[max]) {
                max = 2 * k;
            }
            if(size >= 2*k+1 && heap[2*k+1] > heap[max]) {
                max = 2 * k + 1;
            }
            if(max != k) {
                int tmp = heap[k];
                heap[k] = heap[max];
                heap[max] = tmp;
                heapify(max, size);
            }
        }

        public void initialize() {
            for(int i=n/2; i>=1; i--) {
                heapify(i, n);
            }
        }

        public void sort() {
            for(int i=n; i>=1; i--) {
                int tmp = heap[1];
                heap[1] = heap[i];
                heap[i] = tmp;
                heapify(1, i-1);
            }
        }
    }

    public static class myBST {
        Node root;

        public class Node {
            int val;
            Node l_child;
            Node r_child;
            public Node(int d) {
                val = d;
            }
        }

        public myBST() {
            root = null;
        }
        public myBST(Node r) {
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
            else {
                root.r_child = insert(root.r_child, val);
            }
            return root;
        }

        public String inorder() {
            String s = "";
            if (root == null) {
                return null;
            }
            if (root.l_child != null) {
                myBST l = new myBST(root.l_child);
                s += l.inorder();
            }
            s += root.val + " ";
            if (root.r_child != null) {
                myBST r = new myBST(root.r_child);
                s += r.inorder();
            }
            return s;
        }
    }

    public static class mySPT {
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

        public mySPT() {
            root = null;
        }

        public mySPT(Node r) {
            root = r;
        }

        public void insert(int val) {
            root = insert(root, val);
        }

        private Node insert(Node root, int val) {
            if(root == null) {
                root = new Node(val);
                splay(root);
            }
            else if(root.val > val) {
                root.l_child = insert(root.l_child, val);
                root.l_child.parent = root;
            }
            else {
                root.r_child = insert(root.r_child, val);
                root.r_child.parent = root;
            }
            return root;
        }


        public void splay(Node x) {
            while(x.parent != null) {
                if(x.parent.parent == null) {
                    if(x == x.parent.l_child) {
                        rightRotate(x.parent);
                    }
                    else {
                        leftRotate(x.parent);
                    }
                }
                else if(x == x.parent.l_child && x.parent == x.parent.parent.l_child) {
                    rightRotate(x.parent.parent);
                    rightRotate(x.parent);
                }
                else if(x == x.parent.r_child && x.parent == x.parent.parent.r_child) {
                    leftRotate(x.parent.parent);
                    leftRotate(x.parent);
                }
                else if(x == x.parent.r_child && x.parent == x.parent.parent.l_child) {
                    leftRotate(x.parent);
                    rightRotate(x.parent);
                }
                else {
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

        public String inorder() {
            String s = "";
            if (root == null) {
                return null;
            }
            if (root.l_child != null) {
                mySPT l = new mySPT(root.l_child);
                s += l.inorder();
            }
            s += root.val + " ";
            if (root.r_child != null) {
                mySPT r = new mySPT(root.r_child);
                s += r.inorder();
            }
            return s;
        }
    }


}

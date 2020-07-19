public class myTree {
    BinaryTreeNode root;

    public myTree() {
        root = null;
    }

    public myTree(BinaryTreeNode r) {
        root = r;
    }

    public static class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int d) {
            val = d;
            left = null;
            right = null;
        }
    }

    public String inOrder() {
        String s = "";
        if(root.left != null) {
            myTree l = new myTree(root.left);
            s += l.inOrder();
        }
        s += root.val + " ";
        if(root.right != null) {
            myTree r = new myTree(root.right);
            s += r.inOrder();
        }
        return s;
    }

    public String preOrder() {
        String s = "";
        s += root.val + " ";
        if(root.left != null) {
            myTree l = new myTree(root.left);
            s += l.preOrder();
        }
        if(root.right != null) {
            myTree r = new myTree(root.right);
            s += r.preOrder();
        }
        return s;
    }

    public String postOrder() {
        String s = "";
        if(root.left != null) {
            myTree l = new myTree(root.left);
            s += l.postOrder();
        }
        if(root.right != null) {
            myTree r = new myTree(root.right);
            s += r.postOrder();
        }
        s += root.val + " ";
        return s;
    }

    public myTree removeLeftSubtree() {
        root.left = null;
        return new myTree(this.root);
    }

    public myTree removeRightSubtree() {
        root.right = null;
        return new myTree(this.root);
    }

    public boolean isEmpty() {
        return (root==null);
    }

    public Object root() {
        return root.val;
    }

    public int size() {
        int n = 0;
        if(root.left != null) {
            myTree l = new myTree(root.left);
            n += l.size();
        }
        if(root.right != null) {
            myTree r = new myTree(root.right);
            n += r.size();
        }
        n += 1;
        return n;
    }

    public int height() {
        if(root == null) {
            return 0;
        }
        myTree l = new myTree(root.left);
        myTree r = new myTree(root.right);
        return(max(l.height(), r.height()) + 1);
    }

    public int max(int h1, int h2) {
        if(h1 > h2)
            return h1;
        else
            return h2;
    }

    public static void main(String[] args) {

        myTree tree = new myTree();
        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(5);
        tree.root.right.left = new BinaryTreeNode(6);
        tree.root.right.right = new BinaryTreeNode(7);
        tree.root.left.left.left = new BinaryTreeNode(8);
        tree.root.left.left.right = new BinaryTreeNode(9);
        tree.root.left.right.left = new BinaryTreeNode(10);
        tree.root.left.right.right = new BinaryTreeNode(11);
        tree.root.left.right.left.left = new BinaryTreeNode(12);
        tree.root.left.right.left.right = new BinaryTreeNode(13);


        System.out.println("Preorder sequence is ");
        System.out.println(tree.preOrder());
        System.out.println();

        System.out.println("Inorder sequence is ");
        System.out.println(tree.inOrder());
        System.out.println();

        System.out.println("Postorder sequence is ");
        System.out.println(tree.postOrder());
        System.out.println();

        System.out.println("Number of nodes = " + tree.size());
        System.out.println();

        System.out.println("Height = " + tree.height());
        System.out.println();

        myTree subTree = new myTree(tree.root.left.right);
        subTree.removeLeftSubtree();

        System.out.println("Preorder sequence is ");
        System.out.println(tree.preOrder());
        System.out.println();

        System.out.println("Number of nodes = " + tree.size());
        System.out.println();

        System.out.println("Height = " + tree.height());
        System.out.println();

        tree.removeLeftSubtree();

        System.out.println("Postorder sequence is ");
        System.out.println(tree.postOrder());
        System.out.println();

        System.out.println("Number of nodes = " + tree.size());
        System.out.println();

        System.out.println("Height = " + tree.height());
        System.out.println();

    }
}

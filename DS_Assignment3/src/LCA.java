//2016-18223 Jane Shin
public class LCA {

    //2-a
    public static class ExtTNode {
        int value;
        ExtTNode left;
        ExtTNode right;
        ExtTNode parent;

        public ExtTNode(int value){
            this.value = value;
        }

        public void addLeft(ExtTNode node){
            this.left = node;
            node.parent = this;
        }

        public void addRight (ExtTNode node){
            this.right = node;
            node.parent = this;
        }

        public String toString() {
            return Integer.toString(this.value);
        }
    }

    //2-b
    public static ExtTNode LCA(ExtTNode n1, ExtTNode n2){
        if(n1==null || n2==null)
            return null;
        while(true) {
            int a = depth(n1);
            int b = depth(n2);
            if (a == b)
                break;
            else if (a > b)
                n1 = n1.parent;
            else
                n2 = n2.parent;
        }
        while(n1 != n2) {
            n1 = n1.parent;
            n2 = n2.parent;
        }
        return n1;
    }

    public static int depth(ExtTNode n) {
        if (n.parent == null)
            return 0;
        return depth(n.parent) + 1;
    }

    //2-c
    public static void PrintCommonPath(ExtTNode n1, ExtTNode n2){
        ExtTNode n = LCA(n1, n2);
        if(n == null) {
            System.out.print("No Common Path");
            return;
        }
        while(true) {
            System.out.print(n);
            if(n.parent == null)
                break;
            else {
                System.out.print("->");
                n = n.parent;
            }
        }
    }

    public static void main(String[] args) {

        /*
         * Tree construction with ExtNode
         *
         *       1
               /   \
              2     3
             / \   /  \
            4   5  6   7
               /    \
              8      9
         */

        ExtTNode root = new ExtTNode(1 );
        ExtTNode node2 =  new ExtTNode(2);
        ExtTNode node3 =  new ExtTNode(3);
        ExtTNode node4 =  new ExtTNode(4);
        ExtTNode node5 =  new ExtTNode(5);
        ExtTNode node6 =  new ExtTNode(6);
        ExtTNode node7 =  new ExtTNode(7);
        ExtTNode node8 =  new ExtTNode(8);
        ExtTNode node9 =  new ExtTNode(9);

        root.addLeft(node2);
        root.addRight(node3);
        root.left.addLeft(node4);
        root.left.addRight (node5);
        root.right.addLeft (node6);
        root.right.addRight (node7);
        root.left.right.addLeft(node8);
        root.right.left.addRight(node9);

        System.out.println("LCA : " + LCA(node4, node8));
        System.out.print("CommonPath : "); PrintCommonPath(node4, node8);

    }


}

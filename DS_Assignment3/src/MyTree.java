//2016-18223 Jane Shin
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;


@SuppressWarnings("Duplicates")
public class MyTree {
    public Node root;
    boolean accpeted = true;

    public class Node {
        public char data;
        public Node left;
        public Node right;

        public Node(char d) {
            data = d;
        }
    }

    MyTree(Node r) {
        root = r;
    }

    // 1 - a
    MyTree(String preorder, String inorder){
        root = Construct(preorder, inorder, 0, preorder.length()-1, 0, inorder.length()-1);
        if(postorder().length() != preorder.length() || levelorder().length() != preorder.length()) {
            accpeted = false;
            System.out.println("Unaccepted input");
        }
        else {
            accpeted  = true;
            System.out.println("Done");
        }
    }

    Node Construct(String preorder, String inorder, int pStart, int pEnd, int iStart, int iEnd) {
        if(pStart>pEnd || iStart>iEnd)
            return null;
        char s = preorder.charAt(pStart);
        Node r = new Node(s);
        if(!inorder.contains(Character.toString(s)))
            return null;
        int i = inorder.indexOf(preorder.charAt(pStart));
        r.left = Construct(preorder, inorder, pStart+1, pStart+(i-iStart), iStart, i-1);
        r.right = Construct(preorder, inorder, pStart+(i-iStart)+1, pEnd, i+1, iEnd);
        return r;
    }

    // 1 - b
    String postorder(){
        if(!accpeted)
            return null;
        String s = "";
        if(root.left != null) {
            MyTree l = new MyTree(root.left);
            s += l.postorder();
        }
        if(root.right != null) {
            MyTree r = new MyTree(root.right);
            s += r.postorder();
        }
        s += root.data;
        return s;
    }

    String levelorder2() {
        if(!accpeted)
            return null;
        String s = "";
        Node t = root;
        Queue<Node> q = new LinkedList<>();
        while(t != null) {
            s += t.data;
            if(t.left != null)
                q.add(t.left);
            if(t.right != null)
                q.add(t.right);
            if(!q.isEmpty())
                t = q.poll();
            else
                break;
        }
        return s;
    }
    //1 - c
    String levelorder(){
        if(!accpeted)
            return null;
        String s = "";
        if(root == null)
            return null;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr == null) {
                if (!q.isEmpty()) {
                    q.add(null);
                }
            }
            else {
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
                s += curr.data;
            }
        }
        return s;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\myTreeInput.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            String[] inputs = input.split(" ");

            System.out.println("Input : " + inputs[0] + ", " + inputs[1]);
            System.out.print("Making tree...  ");
            MyTree myTree = new MyTree(inputs[0], inputs[1]);
            System.out.print("Report tree postorder : ");
            System.out.println(myTree.postorder());
            System.out.print("Report tree level : ");
            System.out.println(myTree.levelorder());
            System.out.println();
        }
    }
}

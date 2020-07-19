import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestTreap {
	public static void main(String[] args)
            throws FileNotFoundException {
		String path = args[0];
		//the input.txt needs reading and processing
        File input = new File(path);
        Scanner console = new Scanner(input);
        int size = 0;
        while(console.hasNext()) {
            size++;
            String tmp = console.next();
        }
		String S [] = new String[size]; // U can use your own method for input file size
        int i = 0;
        console = new Scanner(input);
        while(console.hasNext()) {
            S[i] = console.next();
            i++;
        }

        Treap mytreap = new Treap();
        mytreap.build(S);
        System.out.println("Treap");
        mytreap.Inorder();
        mytreap.remove("C");
        System.out.println("New Treap");
        mytreap.Inorder();
        mytreap.remove("H");
        System.out.println("New Treap");
        mytreap.Inorder();
        mytreap.remove("A");
        System.out.println("New Treap");
        mytreap.Inorder();
        mytreap.search("A");
	}
}
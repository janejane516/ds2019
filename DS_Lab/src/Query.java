import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Query {
    public static void main(String[] args)
    throws FileNotFoundException {
        Scanner console = new Scanner(new File("src\\query_input.txt"));
        int n = console.nextInt();
        String[] S = new String[n];
        for(int i = 0; i < n; i++) {
            S[i] = console.next();
        }
        int q = console.nextInt();
        String[] Q = new String[q];
        for(int i = 0; i < q; i++) {
            Q[i] = console.next();
        }
        for(int i = 0; i < q; i++) {
            int ins = 0;
            for(int j = 0; j < n; j++) {
                if(Q[i].equals(S[j])) {
                    ins++;
                }
            }
            System.out.println(ins);
        }
    }
}

//2016-18223 Jane Shin
import java.util.*;
import java.io.*;
public class Test {
    public static void main(String[] args)
            throws FileNotFoundException{

        Scanner console1 = new Scanner(new File("src\\hash.txt"));
        Scanner console2 = new Scanner(new File("src\\hash.txt"));
        LinearProbingHashTable L = new LinearProbingHashTable(7393);
        Hashingwithchain H = new Hashingwithchain(7393);
        while(console1.hasNext()) {
            String s1 = console1.next();
            L.insert(s1, s1);
        }
        System.out.println("\n< The result of hashing using 'LinearProbingHashTable' >");
        L.printHashTable();
        while(console2.hasNext()) {
            String s2 = console2.next();
            H.insert(s2, s2);
        }
        System.out.println("\n< The result of hashing using 'Hashingwithchain' >");
        H.printHashTable();

        System.out.println("\n< Measuring the running time >\n");
        String X, Y, Z;
        long startTime;
        long L_time, H_time;
        String[] get_ind = {"3036", "1453", "4943", "6562", "8159"};
        String[] insert_ind = {"9113", "4230", "8041", "6198", "7208", "4476"};
        String[] remove_ind = {"4771", "5581", "3666", "7410", "9900", "4986", "6251"};

        Y = "get()";
        for(int i=0; i<get_ind.length; i++) {
            X = get_ind[i];
            Z = "LinearProbingHashTable";
            startTime = System.nanoTime();
            L.get(X);
            L_time = System.nanoTime() - startTime;
            System.out.println("Key is " + X + ", method is " + Y + ", running time for " + Z + " is " + L_time + " nanoseconds.");
            Z = "Hashingwithchain";
            startTime = System.nanoTime();
            H.get(X);
            H_time = System.nanoTime() - startTime;
            System.out.println("Key is " + X + ", method is " + Y + ", running time for " + Z + " is " + H_time + " nanoseconds.");
        }

        Y = "insert()";
        for(int i=0; i<insert_ind.length; i++) {
            X = insert_ind[i];
            Z = "LinearProbingHashTable";
            startTime = System.nanoTime();
            L.insert(X, X);
            L_time = System.nanoTime() - startTime;
            System.out.println("Key is " + X + ", method is " + Y + ", running time for " + Z + " is " + L_time + " nanoseconds.");
            Z = "Hashingwithchain";
            startTime = System.nanoTime();
            H.insert(X, X);
            H_time = System.nanoTime() - startTime;
            System.out.println("Key is " + X + ", method is " + Y + ", running time for " + Z + " is " + H_time + " nanoseconds.");
        }

        Y = "remove()";
        for(int i=0; i<remove_ind.length; i++) {
            X = remove_ind[i];
            Z = "LinearProbingHashTable";
            startTime = System.nanoTime();
            L.remove(X);
            L_time = System.nanoTime() - startTime;
            System.out.println("Key is " + X + ", method is " + Y + ", running time for " + Z + " is " + L_time + " nanoseconds.");
            Z = "Hashingwithchain";
            startTime = System.nanoTime();
            H.remove(X);
            H_time = System.nanoTime() - startTime;
            System.out.println("Key is " + X + ", method is " + Y + ", running time for " + Z + " is " + H_time + " nanoseconds.");
        }
    }
}

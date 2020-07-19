//2016-18223 Jane Shin
import java.util.*;
import java.io.*;

public class SwitchBox {
    public static void main(String[] args)
            throws FileNotFoundException{
        Scanner console = new Scanner(new File("src\\Index1.txt"));
        Scanner console2 = new Scanner(new File("src\\Index2.txt"));
        System.out.println("\nOutput of 'Index1.txt': ");
        isRoutable(console);
        System.out.println();
        System.out.println("\nOutput of 'Index2.txt': ");
        isRoutable(console2);
    }

    public static void isRoutable(Scanner console) {
        int n = 0;
        Pair[] pairs = new Pair[100];
        while (console.hasNext()) {
            String[] str = console.next().split(",");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            Pair tmp = new Pair(a, b);
            for(int i=0; i<n;i++) {
                if(!((tmp.start>=pairs[i].start && tmp.end<=pairs[i].end) || (pairs[i].end <= tmp.start) || (tmp.end <= pairs[i].start))) {
                    System.out.println("(" + pairs[i].start + "," + pairs[i].end + ")");
                    System.out.println("(" + tmp.start + "," + tmp.end + ")");
                    return;
                }
            }
            pairs[n] = tmp;
            n++;
        }
        System.out.println("Routable");
    }

    static class Pair {
        int start;
        int end;

        public Pair(int a, int b) {
            if(b > a) {
                start = a;
                end = b;
            }
            else {
                start = b;
                end = a;
            }
        }
    }
}

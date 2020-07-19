//2016-18223 Jane Shin
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class ds5_step3 {
    static int VERTEX_N;
    static int EDGE_N;
    static int[] d;
    static int[] p;
    static int[][] graph;
    static boolean[] visited;
    static Scanner scanner;
    static PrintStream output;

    public static void main(String[] args)
            throws FileNotFoundException {
        scanner = new Scanner(new File(args[0]));
        output = new PrintStream(new File(args[1]));
        VERTEX_N = scanner.nextInt();
        EDGE_N = scanner.nextInt();
        graph = new int[VERTEX_N+1][VERTEX_N+1];
        for(int i=0; i<EDGE_N; i++) {
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();
            graph[s][d] = w;
        }
        System.out.println("Input " + args[0] + " successfully read.");
        d = new int[VERTEX_N + 1];
        p = new int[VERTEX_N + 1];
        visited = new boolean[VERTEX_N + 1];
        output.println(VERTEX_N);
        System.out.print("Performing Dijkstra's algorithm... ");
        dijkstra(1);
        System.out.println("Done.");
        System.out.println("Output saved as " + args[1] + ".");

    }

    public static void dijkstra(int src) {
        for(int i=1; i<=VERTEX_N; i++) {
            d[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }
        for(int i=1; i<=VERTEX_N; i++) {
            if(graph[src][i] != 0) {
                d[i] = graph[src][i];
                p[i] = src;
            }
        }
        d[src] = 0;
        visited[src] = true;
        output.println(src);
        for(int i=0; i<VERTEX_N; i++) {
            printDij();
            int min = Integer.MAX_VALUE;
            int min_i = 0;
            for(int j=1; j<=VERTEX_N; j++) {
                if(!visited[j] && d[j]<min) {
                    min = d[j];
                    min_i = j;
                }
            }
            if(min_i <= 0)
                break;
            visited[min_i] = true;
            output.println(min_i);
            for(int j=1; j<=VERTEX_N; j++) {
                if(!visited[j] && graph[min_i][j] != 0) {
                    if(d[j]>d[min_i] + graph[min_i][j]) {
                        d[j] = d[min_i] + graph[min_i][j];
                        p[j] = min_i;
                    }
                }
            }
        }
    }

    public static void printDij() {
        output.print("d ");
        for(int i=1; i<=VERTEX_N; i++) {
            if(d[i] == Integer.MAX_VALUE)
                output.print("-");
            else
                output.print(d[i]);
            output.print(" ");
        }
        output.println();
        output.print("p ");
        for(int i=1; i<=VERTEX_N; i++) {
            if(p[i] == -1)
                output.print("-");
            else
                output.print(p[i]);
            output.print(" ");
        }
        output.println();
    }


}

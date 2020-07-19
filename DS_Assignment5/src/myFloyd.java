import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class myFloyd {
    static int VERTEX_N;
    static int EDGE_N;
    static int[][] graph;
    static int[][] kay;
    static Scanner scanner;
    static PrintStream output;

    public static void main(String[] args)
            throws FileNotFoundException {
        scanner = new Scanner(new File(args[0]));
        output = new PrintStream(new File(args[1]));
        VERTEX_N = scanner.nextInt();
        EDGE_N = scanner.nextInt();
        graph = new int[VERTEX_N + 1][VERTEX_N + 1];
        kay = new int[VERTEX_N + 1][VERTEX_N + 1];
        for (int i = 1; i <= VERTEX_N; i++) {
            for (int j = 1; j <= VERTEX_N; j++) {
                graph[i][j] = Integer.MAX_VALUE;
                if(i==j)
                    graph[i][j] = 0;
                kay[i][j] = 0;
            }
        }
        for (int i = 0; i < EDGE_N; i++) {
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();
            graph[s][d] = w;
        }
        floyd();
        print(graph);
        print(kay);
        path(1,5);
    }

    public static void floyd() {
        for(int k = 1; k<=VERTEX_N; k++) {
            for (int i = 1; i <= VERTEX_N; i++) {
                for (int j = 1; j <= VERTEX_N; j++) {
                    if(graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE && graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        kay[i][j] = k;
                    }
                }
            }
        }

    }

    public static void path(int src, int dst) {
        if(src==dst)
            return;
        if(kay[src][dst] == 0)
            output.print(dst + " ");
        else {
            path(src, kay[src][dst]);
            path(kay[src][dst], dst);
        }
    }

    public static void print(int[][] A) {
        for(int i=1; i<=VERTEX_N; i++) {
            for(int j=1; j<=VERTEX_N; j++) {
                if(A[i][j] == Integer.MAX_VALUE)
                    output.print("-");
                else
                    output.print(A[i][j]);
                output.print(" ");
            }
            output.println();
        }
    }
}

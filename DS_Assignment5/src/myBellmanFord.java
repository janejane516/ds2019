import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class myBellmanFord {
    static int VERTEX_N;
    static int EDGE_N;
    static int[][] graph;
    static int[][] d;
    static int[][] p;
    static Scanner scanner;
    static PrintStream output;

    public static void main(String[] args)
            throws FileNotFoundException {
        scanner = new Scanner(new File(args[0]));
        output = new PrintStream(new File(args[1]));
        VERTEX_N = scanner.nextInt();
        EDGE_N = scanner.nextInt();
        graph = new int[VERTEX_N+1][VERTEX_N + 1];
        d = new int[VERTEX_N + 1][VERTEX_N];
        p = new int[VERTEX_N + 1][VERTEX_N];
        for (int i = 1; i <= VERTEX_N; i++) {
            for (int j = 1; j <= VERTEX_N; j++) {
                graph[i][j] = Integer.MAX_VALUE;
                if(i==j)
                    graph[i][j] = 0;
            }
        }
        for (int i = 0; i < EDGE_N; i++) {
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();
            graph[s][d] = w;
        }
        BellManFord(1);
        for(int k=0; k<VERTEX_N; k++) {
            for(int v=1; v<= VERTEX_N; v++) {
                if(d[v][k] == Integer.MAX_VALUE)
                    output.print("- ");
                else
                    output.print(d[v][k] + " ");
            }
            output.println();
        }
        for(int k=0; k<VERTEX_N; k++) {
            for(int v=1; v<= VERTEX_N; v++) {
                if(p[v][k] == Integer.MAX_VALUE)
                    output.print("- ");
                else
                    output.print(p[v][k] + " ");
            }
            output.println();
        }
    }

    public static void BellManFord(int src) {
        for(int i=1; i<=VERTEX_N; i++) {
            if(i != src) {
                d[i][0] = Integer.MAX_VALUE;
            }
            else d[i][0] = 0;
            p[i][0] = Integer.MAX_VALUE;
        }

        for(int k=1; k<VERTEX_N; k++) {
            for(int v=1; v<=VERTEX_N; v++) {
                d[v][k] = d[v][0];
                p[v][k] = p[v][0];
                for (int u = 1; u <= VERTEX_N; u++) {
                    if (u != v && d[u][k-1] != Integer.MAX_VALUE && graph[u][v] != Integer.MAX_VALUE) {
                        if(d[u][k-1] + graph[u][v] < d[v][k]) {
                            d[v][k] = d[u][k - 1] + graph[u][v];
                            p[v][k] = u;
                        }
                    }
                }
            }
        }
    }
}

import java.util.*;

public class floyd {
    static int[][] graph;
    static int VERTEX_N;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        VERTEX_N = scanner.nextInt();
        System.out.println("Enter the Weighted Matrix for the graph");
        graph = new int[VERTEX_N + 1][VERTEX_N + 1];
        for (int i = 1; i <= VERTEX_N; i++) {
            for (int j = 1; j <= VERTEX_N; j++) {
                graph[i][j] = 999;
                if(i==j)
                    graph[i][j] = 0;
            }
        }
        for (int i = 1; i <= VERTEX_N; i++) {
            for (int j = 1; j <= VERTEX_N; j++) {
                int a = scanner.nextInt();
                if(a!=0)
                    graph[i][j] = a;
            }
        }
        floyd();
        print(graph);
    }
    public static void floyd() {
        for (int k = 1; k <= VERTEX_N; k++) {
            for (int i = 1; i <= VERTEX_N; i++) {
                for (int j = 1; j <= VERTEX_N; j++) {
                    if (graph[i][k] != 999 && graph[k][j] != 999 && graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    public static void print(int[][] A) {
        for(int i=1; i<=VERTEX_N; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i=1; i<=VERTEX_N; i++) {
            System.out.print(i + " ");
            for(int j=1; j<=VERTEX_N; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}

//2016-18223 Jane Shin
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class ds5_step2 {
    static int VERTEX_N;
    static int EDGE_N;
    static ArrayList<Edge> edges;
    static Scanner scanner;
    static PrintStream output;
    static int Kruskal_total_weight;
    static int Prim_total_weight;
    static long Kruskal_total_time;
    static long Prim_total_time;
    static ArrayList<Edge> Kruskal_mst;
    static ArrayList<Edge> Prim_mst;

    public static void main(String[] args)
            throws FileNotFoundException {
        scanner = new Scanner(new File(args[0]));
        output = new PrintStream(new File(args[1]));
        VERTEX_N = scanner.nextInt();
        EDGE_N = scanner.nextInt();
        edges = new ArrayList<>();
        for(int i=0; i<EDGE_N; i++) {
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();
            Edge e = new Edge(s, d, w);
            Edge e2 = new Edge(d, s, w);
            edges.add(e);
        }
        System.out.println("Input " + args[0] + " successfully read.");
        //Kruskal
        System.out.print("Trying Kruskal's Algorithm... ");
        long startTime;
        for(int i=0; i<10000; i++) {
            startTime = System.currentTimeMillis();
            Kruskal();
            Kruskal_total_time += (System.currentTimeMillis() - startTime);
        }
        double k = (double)Kruskal_total_time/1000;
        System.out.println("Done.");
        System.out.println("Total measured time is " + String.format("%.2f", k) + "sec.");
        output.println("Kruskal");
        output.println(String.format("%.2f", k));
        output.println(VERTEX_N);
        output.println(EDGE_N);
        output.println(Kruskal_total_weight);
        Sort_vertex sort_vertex = new Sort_vertex();
        Kruskal_mst.sort(sort_vertex);
        for(int i=0; i<Kruskal_mst.size(); i++) {
            Edge e = Kruskal_mst.get(i);
            output.println(e.src + " " + e.dst + " " + e.weight);
        }

        //Prim
        System.out.print("Trying Prim's Algorithm... ");
        for(int i=0; i<10000; i++) {
            startTime = System.currentTimeMillis();
            Prim(1);
            Prim_total_time += (System.currentTimeMillis() - startTime);
        }
        double p = (double)Prim_total_time/1000;
        System.out.println("Done.");
        System.out.println("Total measured time is " + String.format("%.2f", p) + "sec.");
        output.println("Prim");
        output.println(String.format("%.2f", p));
        output.println(VERTEX_N);
        output.println(EDGE_N);
        output.println(Prim_total_weight);
        Prim_mst.sort(sort_vertex);
        for(int i=0; i<Prim_mst.size(); i++) {
            Edge e = Prim_mst.get(i);
            output.println(e.src + " " + e.dst + " " + e.weight);
        }
    }

    public static void Kruskal() {
        Sort_weight sort_weight = new Sort_weight();
        PriorityQueue<Edge> pq = new PriorityQueue<>(edges.size(), sort_weight);
        for(int i=0; i<EDGE_N; i++) {
            pq.add(edges.get(i));
        }
        Kruskal_total_weight = 0;
        int[] root = new int[VERTEX_N+1];
        makeSet(root);
        Kruskal_mst = new ArrayList<>();
        int k = 0;

        while (k < VERTEX_N - 1) {
            Edge e = pq.remove();
            int x = find(root, e.src);
            int y = find(root, e.dst);
            if (x != y) {
                Kruskal_mst.add(e);
                Kruskal_total_weight += e.weight;
                union(root, x, y);
                k++;
            }
        }
    }
    public static void makeSet(int[] root) {
        for(int i=1; i<=VERTEX_N; i++) {
            root[i] = i;
        }
    }
    public static int find(int[] root, int x) {
        if(root[x] == x) {
            return x;
        }
        else {
            return find(root, root[x]);
        }
    }
    public static void union(int[] root, int x, int y) {
        int x_root = find(root, x);
        int y_root = find(root, y);
        root[y_root] = x_root;
    }

    public static void Prim(int start) {
        boolean[] visited = new boolean[VERTEX_N + 1];
        Prim_total_weight = 0;
        Prim_mst = new ArrayList<>();
        Edge e, e_tmp;
        Sort_weight sort_weight = new Sort_weight();
        PriorityQueue<Edge> pq = new PriorityQueue<>(sort_weight);
        visited[start] = true;
        for (int i = 0; i < EDGE_N; i++) {
            e = edges.get(i);
            if(e.src == start)
                pq.add(e);
            else if(e.dst == start)
                pq.add(new Edge(e.dst, e.src, e.weight));
        }
        while (!pq.isEmpty()) {
            e = pq.remove();
            if (!visited[e.dst]) {
                if(e.src < e.dst)
                    Prim_mst.add(e);
                else
                    Prim_mst.add(new Edge(e.dst, e.src, e.weight));
                visited[e.dst] = true;
                Prim_total_weight += e.weight;
                for (int i = 0; i < EDGE_N; i++) {
                    e_tmp = edges.get(i);
                    if(e_tmp.src == e.dst)
                        pq.add(e_tmp);
                    else if(e_tmp.dst == e.dst)
                        pq.add(new Edge(e_tmp.dst, e_tmp.src, e_tmp.weight));
                }
            }
        }
    }

    static class Edge {
        int src;
        int dst;
        int weight;

        public Edge(int s, int d, int w) {
            src = s;
            dst = d;
            weight = w;
        }
    }

    static class Sort_weight implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            if(o1.weight > o2.weight)
                return 1;
            else if(o1.weight == o2.weight)
                return 0;
            else
                return -1;
        }
    }

    static class Sort_vertex implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            if(o1.src > o2.src)
                return 1;
            else if(o1.src == o2.src) {
                if(o1.dst > o2.dst)
                    return 1;
                else if(o1.dst == o2.dst)
                    return 0;
                else
                    return -1;
            }
            else
                return -1;
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class myPrim {
    private PriorityQueue<Node> queue;
    private List<List<Node>> adj;
    public int[] dist;
    private Set<Integer> N;
    int n;

    public static class Node implements Comparator<Node> {
        int node;
        int cost;

        public Node() {}

        public Node(int n, int c) {
            this.node = n;
            this.cost = c;
        }

        @Override
        public int compare(Node n1, Node n2) {
            if (n1.cost < n2.cost)
                return -1;
            if (n1.cost > n2.cost)
                return 1;
            return 0;
        }
    }

    public myPrim(int n) {
        this.n = n;
        this.dist = new int[n];
        this.N = new HashSet<>();
        this.queue = new PriorityQueue<Node>(n, new Node());
    }

    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        queue.add(new Node(src, 0));
        dist[src] = 0;

        while (N.size() != n) {
            if(queue.isEmpty()) {
                for(int i=0; i<n; i++) {
                    if(!N.contains(i)) {
                        dist[i] = 0;
                        N.add(i);
                    }
                }
            }
            else {
                int u = queue.remove().node;
                N.add(u);
                e_Neighbours(u);
            }
        }
    }

    private void e_Neighbours(int u)
    {
        int befD = -1;
        int newD = -1;

        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            if (!N.contains(v.node)) {
                befD = v.cost;
                newD = dist[u] + befD;

                if (newD < dist[v.node])
                    dist[v.node] = newD;

                queue.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    public static void main(String[] args)
            throws FileNotFoundException {
        Scanner console = new Scanner(new File("src\\input.txt"));
        String a = console.nextLine();
        int n = a.length();
        List<List<Node>> adj = new ArrayList<List<Node>>();
        int j = 0;
        int src = 0;
        for(int i = 0; i<n; i++) {
            List<Node> A = new ArrayList<Node>();
            adj.add(A);
        }
        for(int i=0; i<n; i++) {
            if (Integer.parseInt(a.substring(i, i + 1)) != 0) {
                adj.get(j).add(new Node(i, Integer.parseInt(a.substring(i, i + 1))));
            }
        }
        while(console.hasNextLine()) {
            j++;
            a = console.nextLine();
            if(j == n) {
                src = Integer.parseInt(a);
            }
            else {
                for(int i = 0; i<n; i++) {
                    if (Integer.parseInt(a.substring(i, i + 1)) != 0) {
                        adj.get(j).add(new Node(i, Integer.parseInt(a.substring(i, i + 1))));
                    }
                }
            }
        }
        myPrim my = new myPrim(n);
        my.dijkstra(adj, 0);
        System.out.println("The Shortest Path to all nodes are");
        for(int i=0; i<n; i++) {
            System.out.println(src + " to " + (i + 1) + " is " + my.dist[i]);
        }
    }

}

//2016-18223 Jane Shin
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;


public class ds5_step1 {
    public static void main(String[] args)
            throws FileNotFoundException {
        Graph graph = new UGraph();
        int VERTEX_N = Integer.parseInt(args[0]);
        String filename = args[1];
        PrintStream output = new PrintStream(new File(filename));
        Random random = new Random();
        System.out.println("A random graph having " + VERTEX_N + " vertices is generated.");
        for(int i=1; i<=VERTEX_N; i++) {
            graph.addVertex(i);
        }
        int EDGE_N = 0;
        int v1, v2;
        while(BFTraversal(graph, 1).size() != VERTEX_N) {
            v1 = random.nextInt(VERTEX_N) + 1;
            v2 = random.nextInt(VERTEX_N) + 1;
            if(v1 != v2 && !graph.getAdjVertices(v1).contains(v2)) {
                graph.addEdge(v1, v2);
                EDGE_N++;
            }
        }
        System.out.println("Number of edge is " + EDGE_N + ".");
        output.println(VERTEX_N);
        output.println(EDGE_N);
        int weight;
        for(int i=1; i<=VERTEX_N; i++) {
            List<Integer> A = graph.getAdjVertices(i);
            A.sort(Integer::compareTo);
            for(int j=0; j<A.size(); j++) {
                if(i < A.get(j)) {
                    output.print(i + " " + A.get(j) + " ");
                    weight = random.nextInt(VERTEX_N*2) + 1;
                    output.println(weight);
                }
            }
        }
        System.out.println("Output saved as " + filename + ".");
    }

    interface Graph {
        public List<Integer> getAdjVertices(int v);
        public Set<Integer> getVertices();
        public Map<Integer, List<Integer>> getAdjacencyList();
        public void addVertex(int v);
        public void addEdge(int v1, int v2);
    }


    static class UGraph implements Graph {
        Map<Integer, List<Integer>> adjacencyList;

        public UGraph() {
            adjacencyList = new HashMap<>();
        }
        public List<Integer> getAdjVertices(int v) {
            return adjacencyList.get(v);
        };
        public Set<Integer> getVertices() {
            return adjacencyList.keySet();
        };
        public Map<Integer, List<Integer>> getAdjacencyList() {
            return adjacencyList;
        };
        public void addVertex(int v) {
            if(!adjacencyList.containsKey(v)) {
                adjacencyList.put(v, new ArrayList<>());
            }
        };
        public void addEdge(int v1, int v2) {
            List<Integer> A = adjacencyList.get(v1);
            A.add(A.size(), v2);
            adjacencyList.replace(v1, A);

            List<Integer> B = adjacencyList.get(v2);
            B.add(B.size(), v1);
            adjacencyList.replace(v2, B);
        };
    }

    public static Set<Integer> BFTraversal(Graph graph, int root) {
        LinkedHashSet<Integer> ret = new LinkedHashSet<Integer>();
        Set<Integer> A = new LinkedHashSet<>();
        Queue <Integer> q = new LinkedList<>();
        q.add(root);
        A.add(root);
        while(!q.isEmpty()) {
            int v = q.poll();
            ret.add(v);
            List<Integer> X = graph.getAdjVertices(v);
            if(X == null)
                continue;
            for(int i=0; i<X.size(); i++) {
                if (A.contains(X.get(i)))
                    continue;
                q.add(X.get(i));
                A.add(X.get(i));
            }
        }
        return ret;
    }
}

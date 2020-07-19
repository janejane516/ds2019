import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("Duplicates")
public class Lab11 {

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

    public static Set<Integer> DFTraversal (Graph graph, int root) {
        LinkedHashSet<Integer> ret = new LinkedHashSet<Integer>();
        Set<Integer> A = new LinkedHashSet<>();
        DFS(graph, root, A, ret);
        return ret;
    }

    public static void DFS (Graph graph, int v, Set<Integer> A, LinkedHashSet<Integer> ret) {
        A.add(v);
        ret.add(v);
        List<Integer> X = graph.getAdjVertices(v);
        if(X!=null) {
            for (int i = 0; i < X.size(); i++) {
                if (!A.contains(X.get(i)))
                    DFS(graph, X.get(i), A, ret);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        /**
         *  Test 1
         */
        Graph graph = new DGraph();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.printGraph();
        System.out.print("DFS traversal from 5 : ");
        System.out.println(DFTraversal(graph, 5));
        System.out.println();

        graph = new UGraph();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.printGraph();
        System.out.print("BFS traversal from 2 : ");
        System.out.println(BFTraversal(graph, 2));
        System.out.println();

        /**
         *  Test 2
         */

        File file = new File("src\\input.txt");
        Scanner scanner = new Scanner(file);
        graph = new UGraph();
        // Add code to construct the corresponding Graph from input2
        int n = Integer.parseInt(scanner.nextLine());
        for(int i=0; i<n; i++) {
            String s = scanner.nextLine();
            String[] all = s.split("\t");
            int v = Integer.parseInt(all[0]);
            graph.addVertex(v);
            for(int j=1; j<all.length; j++) {
                graph.addEdge(v, Integer.parseInt(all[j]));
            }
        }
        System.out.println("BFS");
        System.out.println(BFTraversal(graph, 1));
        System.out.println();


        file = new File("src\\input2.txt");
        scanner = new Scanner(file);
        graph = new DGraph();
        // Add code to construct the corresponding Graph from input2
        n = Integer.parseInt(scanner.nextLine());
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] all = s.split(" ");
            int v = Integer.parseInt(all[0]);
            graph.addVertex(v);
            for(int j=1; j<all.length; j++) {
                graph.addEdge(v, Integer.parseInt(all[j]));
            }
        }
        System.out.println("DFS");
        System.out.println(DFTraversal(graph, 1));
        System.out.println();


    }


}


interface Graph {
    public List<Integer> getAdjVertices(int v);
    public Set<Integer> getVertices();
    public  Map<Integer, List<Integer>> getAdjacencyList();
    public void addVertex(int v);
    public void addEdge(int v1, int v2);
    public void printGraph();
}


class UGraph implements Graph {

    Map<Integer, List<Integer>> adjacencyList;

    //implement a constructor and methods from Graph interface
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
        if(!adjacencyList.containsKey(v2)) {
            addVertex(v2);
        }
        List<Integer> B = adjacencyList.get(v2);
        B.add(B.size(), v1);
        adjacencyList.replace(v2, B);
    };
    public void printGraph() {
        Set<Integer> v = getVertices();
        Iterator<Integer> itr = v.iterator();
        while(itr.hasNext()) {
            int a = itr.next();
            System.out.println("Vertex " + a + " - " + getAdjVertices(a));
        }
    };

}

class DGraph implements Graph   {

    Map<Integer, List<Integer>> adjacencyList;

    public DGraph() {
        adjacencyList = new HashMap<>();
    }
    public List<Integer> getAdjVertices(int v) {
        return adjacencyList.get(v);
    };
    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    };
    public  Map<Integer, List<Integer>> getAdjacencyList() {
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
    };
    public void printGraph() {
        Set<Integer> v = getVertices();
        Iterator<Integer> itr = v.iterator();
        while(itr.hasNext()) {
            int a = itr.next();
            System.out.println("Vertex " + a + " - " + getAdjVertices(a));
        }
    };

}


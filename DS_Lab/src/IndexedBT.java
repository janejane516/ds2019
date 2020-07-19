import java.util.ArrayList;

public class IndexedBT {
    ArrayList<Node> tree = new ArrayList<>();

    private class Node {
        int leftSize;
        Node l_child;
        Node r_child;
    }
}

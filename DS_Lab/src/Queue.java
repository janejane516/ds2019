import java.util.Stack;

public class Queue {

    private Stack<Integer> s1 = new Stack<Integer>();
    private Stack<Integer> s2 = new Stack<Integer>();

    public void enQueue(int x) {
        s1.push(x);
    }

    public int deQueue() {
        while(!s1.empty()) {
            s2.push(s1.pop());
        }
        if(s2.empty()) {
            System.out.println("Error");
            System.exit(1);
        }
        int ret = s2.pop();
        while(!s2.empty()) {
            s1.push(s2.pop());
        }
        return ret;
    }
}

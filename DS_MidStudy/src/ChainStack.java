import java.util.EmptyStackException;
import java.util.LinkedList;


public class ChainStack extends LinkedList {
    public ChainStack() {
        super();
    }
    public boolean empty() {
        return isEmpty();
    }
    public Object peek() {
        if(empty())
            throw new EmptyStackException();
        return get(size()-1);
    }

    public void push(Object elem) {
        add(size(), elem);
    }

    public Object pop() {
        if(empty())
            throw new EmptyStackException();
        return remove(size()-1);
    }
}
import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayStack extends ArrayList {
    public ArrayStack(int initialCapacity) {
        super(initialCapacity);
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

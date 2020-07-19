//2016-18223 Jane Shin
public class myArrayList implements LinearList {
    private Object[] element;
    private int size;

    public myArrayList(int initialCapacity) {
        if(initialCapacity < 1) {
            throw new IllegalArgumentException();
        }
        element = new Object[initialCapacity];
    }

    public myArrayList() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else return element[index];
    }

    @Override
    public int indexOf(Object elem) {
        for(int i=0; i<size; i++) {
            if(element[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object removed = element[index];
        for(int i=index; i<size; i++) {
            element[i-1] = element[i];
        }
        element[--size] = null;
        return removed;
    }

    @Override
    public void add(int index, Object obj) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(size == element.length) {
            Object[] tmp = new Object[size*2];
            System.arraycopy(element, 0, tmp, 0, size);
            element = new Object[size*2];
            System.arraycopy(tmp, 0, element, 0, size);
        }
        for(int i=size-1; i>=index; i--) {
            element[i+1] = element[i];
        }
        element[index] = obj;
        size++;
    }
}

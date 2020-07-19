//2016-18223 Jane Shin
import java.util.Iterator;
import java.util.NoSuchElementException;

public class myLinkedList implements LinearList {

    public static class Node {
        Object data;
        Node next;

        public Node() {
            next = null;
        }

        public Node(Object d) {
            data = d;
            next = null;
        }
    }

    Node head;

    public myLinkedList() {
        this.head = null;
    }

    @Override
    public boolean isEmpty() {
        return (this.head == null);
    }

    @Override
    public int size() {
        int n = 0;
        Iterator itr = this.iterator();
        while(itr.hasNext()) {
            itr.next();
            n++;
        }
        return n;
    }

    @Override
    public Object get(int index) {
        int n = 0;
        Object tmp;
        Iterator itr = this.iterator();
        while(itr.hasNext()) {
            tmp = itr.next();
            if(index == n)
                return ((Node)tmp).data;
            n++;
        }
        return null;
    }

    @Override
    public int indexOf(Object elem) {
        int n = 0;
        Object tmp = null;
        Iterator itr = this.iterator();
        while(itr.hasNext()) {
            tmp = itr.next();
            if (tmp.equals(elem)) {
                return n;
            }
            n++;
        }
        return -1;
    }

    @Override
    public Object remove(int index) {
        int n = 0;
        Object before;
        Object tmp;
        Object now = null;
        Iterator itr = this.iterator();
        if(head == null) {
            return null;
        }
        if(index == 0) {
            tmp = head;
            head = head.next;
            return ((Node) tmp).data;
        }
        while(itr.hasNext()) {
            before = now;
            now = itr.next();
            if(index == n) {
                ((Node) before).next = ((Node) now).next;
                return ((Node)now).data;
            }
            n++;
        }
        return null;
    }

    @Override
    public void add(int index, Object obj) {
        int n = 0;
        Node NEW = new Node(obj);
        Node tmp;
        Iterator itr = this.iterator();
        if(head == null) {
            head = NEW;
        }
        else if(index == 0) {
            tmp = head;
            head = NEW;
            NEW.next = tmp;
        }
        else {
            while(itr.hasNext()) {
                tmp = (Node) itr.next();
                if((index-1) == n) {
                    if(tmp.next == null) {
                        tmp.next = NEW;
                    }
                    else {
                        Node tmp2 = tmp.next;
                        tmp.next = NEW;
                        NEW.next = tmp2;
                    }
                    return;
                }
                n++;
            }
        }
    }

    public void Sort() {
        Node tmp1, tmp2;
        String name1, name2;
        Iterator itr1 = this.iterator();
        Iterator itr2;
        if(head == null || head.next == null)
            return;
        else {
            while(itr1.hasNext()) {
                itr1.next();
                itr2 = this.iterator();
                while(itr2.hasNext()) {
                    tmp1 = (Node) itr2.next();
                    tmp2 = tmp1.next;
                    if (tmp2 != null) {
                        name1 = ((ContactEntry) tmp1.data).getName();
                        name2 = ((ContactEntry) tmp2.data).getName();
                        if (name1.compareTo(name2) > 0) {
                            Object swp = tmp1.data;
                            tmp1.data = tmp2.data;
                            tmp2.data = swp;
                        }
                    }
                }
            }
        }
    }

    public boolean RemoveDuplicate() {
        boolean ret = false;
        ContactEntry tmp1, tmp2;
        for(int i=0; i<size(); i++) {
            for(int j=i+1; j<size(); j++) {
                tmp1 = (ContactEntry) get(i);
                tmp2 = (ContactEntry) get(j);
                if (tmp1.equals(tmp2)) {
                    remove(j);
                    ret = true;
                }
            }
        }
        return ret;
    }


    public Iterator iterator() {
        return new myIterator();
    }

    private class myIterator implements Iterator {
        private Node nextNode;
        private boolean H;

        public myIterator() {
            H = true;
        }

        @Override
        public boolean hasNext() {
            if (H)
                return (head != null);
            else
                return (nextNode.next != null);
        }

        @Override
        public Object next() {
            if(!hasNext())
                throw new NoSuchElementException();
            else if (H) {
                nextNode = head;
                H = false;
            }
            else {
                nextNode = nextNode.next;
            }
            return nextNode;
        }
    }
    // add more methods as needed.
}

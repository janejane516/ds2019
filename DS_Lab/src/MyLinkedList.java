public class MyLinkedList implements LinkedList {
    public class Node {
        int data;
        Node next;
        public Node() {
            next = null;
        }
        public Node(int d) {
            data = d;
            next = null;
        }
        public Node(int d, Node n) {
            data = d;
            next = n;
        }
    }

    Node head;
    int size;

    public void MyLinkedList() {
        head = null;
        size = 0;
    }

    public void add(int v) {
        size++;
        Node tmp;
        if(head == null) {
            Node h = new Node(v);
            head = h;
        }
        else if(head.next == null) {
            if(v < head.data) {
                Node n = new Node(v);
                tmp = head;
                head = n;
                head.next = tmp;
            }
            else {
                Node n = new Node(v);
                head.next = n;
            }
        }
        else {
            Node n;
            tmp = head;
            while(tmp.next != null) {
                if(v >= tmp.data && v <= tmp.next.data) {
                    n = new Node(v);
                    Node tmp2 = tmp.next;
                    tmp.next = n;
                    n.next = tmp2;
                    return;
                }
                tmp = tmp.next;
            }
            n = new Node(v);
            tmp.next = n;
        }
    }

    public boolean remove(int v) {
        if(head==null) {
            return false;
        }
        if(v==head.data) {
            size--;
            if(head.next != null) {
                Node tmp = head.next;
                head = tmp;
                return true;
            }
            else {
                head = null;
                return true;
            }
        }
        Node tmp = head;
        while(tmp.next != null) {
            if(tmp.next.data == v) {
                size--;
                if(tmp.next.next != null) {
                    tmp.next = tmp.next.next;
                }
                else {
                    tmp.next = null;
                }
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    public void print() {
        if(head==null) {
            System.out.println("EMPTY");
            return;
        }
        System.out.print(head.data + " ");
        Node tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
            System.out.print(tmp.data + " ");
        }
        System.out.println();
        return;
    }
    public boolean isEmpty() {
        if(head == null)
            return true;
        return false;
    }
    public int size() {
        return size;
    }
    public int indexOf(int v) {
        Node tmp = head;
        int ind = 0;
        if(head == null) {
            return -1;
        }
        if(head.data == v) {
            return 0;
        }
        while(tmp.next != null) {
            ind++;
            if(tmp.next.data == v) {
                return ind;
            }
            tmp = tmp.next;
        }
        return -1;
    }

}
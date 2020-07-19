//2016-18223 Jane Shin

/** Class Hashingwithchain **/
public class Hashingwithchain {
    private int currentSize, maxSize;
    private Node[] Arr;

    private class Node {
        Node next;
        String key;
        String val;
        public Node() {
            next = null;
            key = null;
            val = null;
        }
    }

    /** Constructor **/
    public Hashingwithchain(int capacity)
    {
        maxSize = capacity;
        currentSize = 0;
        Arr = new Node[maxSize];
        for(int i=0; i<maxSize; i++) {
            Arr[i] = new Node();
        }
    }

    /** Function to clear hash table **/
    public void makeEmpty()
    {
        for(int i=0; i<maxSize; i++) {
            Arr[i].key = null;
            Arr[i].val = null;
            Arr[i].next = null;
        }
    }

    /** Function to get size of hash table **/
    public int getSize()
    {
        return currentSize;
    }

    /** Function to check if hash table is empty **/
    public boolean isEmpty()
    {
        return (getSize() == 0);
    }

    /** Function to get hash code of a given key **/
    private int hashCode(String key)
    {
        return (Integer.parseInt(key)) % maxSize;
    }

    /** Function to insert key-value pair **/
    public void insert(String key, String val)
    {
        int position = hashCode(key);
        int KEY = Integer.parseInt(key);
        Node New = new Node();
        New.key = key;
        New.val = val;
        if(Arr[position].next == null) {
            Arr[position].next = New;
        }
        else {
            Node before = Arr[position];
            Node now = Arr[position].next;
            while(true) {
                if(now.next == null) {
                    if(Integer.parseInt(now.key) < KEY) {
                        now.next = New;
                        break;
                    }
                    else {
                        New.next = now;
                        before.next = New;
                        break;
                    }
                }
                else {
                    Node after = now.next;
                    if(Integer.parseInt(after.key) >= KEY && Integer.parseInt(now.key) <= KEY) {
                        now.next = New;
                        New.next = after;
                        break;
                    }
                    else if (Integer.parseInt(now.key) >= KEY){
                        before.next =  New;
                        New.next = now;
                        break;
                    }
                    else {
                        before = now;
                        now = now.next;
                    }
                }
            }
        }
        currentSize++;
    }

    /** Function to get value for a given key **/
    public String get(String key)
    {
        int position = hashCode(key);
        if(Arr[position].next == null)
            return null;
        Node tmp = Arr[position].next;
        while (tmp != null) {
            if ((tmp.key).equals(key))
                return tmp.val;
            tmp = tmp.next;
        }
        return null;
    }

    /** Function to remove key and its value **/
    public void remove(String key)
    {
        int position = hashCode(key);
        if(Arr[position].next == null)
            return;
        Node before = Arr[position];
        Node now = Arr[position].next;
        while(now != null) {
            if(now.key.equals(key)) {
                before.next = now.next;
                return;
            }
            before = now;
            now = now.next;
        }
    }

    /** Function to print HashTable **/
    public void printHashTable()
    {
        System.out.println("\nHash Table: ");
        Node tmp;
        for (int i = 0; i < maxSize; i++) {
            if(Arr[i].next != null) {
                tmp = Arr[i].next;
                while(tmp != null) {
                    System.out.println("(" + tmp.key + "," + tmp.val + ")");
                    tmp = tmp.next;
                }
            }
        }
        System.out.println();
    }
}

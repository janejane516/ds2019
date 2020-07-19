//2016-18223 Jane Shin

/** Class LinearProbingHashTable **/
public class LinearProbingHashTable
{
    private int currentSize, maxSize;       
    private String[] keys;   
    private String[] vals;    
 
    /** Constructor **/
    public LinearProbingHashTable(int capacity) 
    {
        maxSize = capacity;
        currentSize = 0;
        keys = new String[maxSize];
        vals = new String[maxSize];
    }  
 
    /** Function to clear hash table **/
    public void makeEmpty()
    {
         for(int i=0; i<maxSize; i++) {
             keys[i] = null;
             vals[i] = null;
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
        if(currentSize == maxSize) {
            return;
        }
        int position = hashCode(key);
        while(keys[position]!=null) {
            position++;
            if(position==maxSize) {
                position = 0;
            }
        }
        keys[position] = key;
        vals[position] = val;
        currentSize++;
    }
 
    /** Function to get value for a given key **/
    public String get(String key) 
    {
        int position = hashCode(key);
        int tmp = position;
        while(true) {
            if(keys[position].equals(key)) {
                return vals[position];
            }
            position++;
            if(position==maxSize) {
                position = 0;
            }
            if(position==tmp) {
                return null;
            }
        }
    }
 
    /** Function to remove key and its value **/
    public void remove(String key) 
    {
        int position = hashCode(key);
        int tmp = position;
        while (true) {
            if (keys[position].equals(key)) {
                keys[position] = null;
                vals[position] = null;
                currentSize--;
                break;
            }
            position++;
            if (position == maxSize) {
                position = 0;
            }
            if (position == tmp) {
                return;
            }
        }
        tmp = position + 1;
        while (true) {
            while(true) {
                if (keys[tmp] != null && hashCode(keys[tmp]) == position) {
                    keys[position] = keys[tmp];
                    vals[position] = vals[tmp];
                    keys[tmp] = null;
                    vals[tmp] = null;
                    break;
                }
                tmp++;
                if(tmp == maxSize) {
                    tmp = 0;
                }
                if(tmp == position) {
                    return;
                }
            }
            position = tmp;
            tmp = position + 1;
        }
    }       
 
    /** Function to print HashTable **/
    public void printHashTable()
    {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < maxSize; i++) {
            if (keys[i] != null)
                System.out.println("(" + keys[i] + "," + vals[i] + ")");
        }
        System.out.println();
    }   
}

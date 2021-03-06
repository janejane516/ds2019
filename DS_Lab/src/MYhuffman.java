import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class MYhuffman {
    public static class myMinHeap {
        HTree[] heap;
        int size;
        int capacity;

        public myMinHeap(int C) {
            heap = new HTree[C+1];
            heap[0] = new Leaf(Integer.MIN_VALUE, '-');
            capacity = C+1;
            size = 0;
        }

        public void add(HTree h) {
            heap[++size] = h;
            int a = size;
            while(a != 1 && heap[a].freq < heap[a/2].freq) {
                swap(a, a/2);
                a = a/2;
            }
        }

        public void swap(int h1, int h2) {
            HTree tmp = heap[h1];
            heap[h1] = heap[h2];
            heap[h2] = tmp;
        }

        public int size() {
            return size;
        }

        public HTree remove() {
            HTree ret = heap[1];
            heap[1] = heap[size];
            size--;
            findPos(1);
            return ret;
        }

        public void findPos(int i) {
            int min = i;
            if(size >= 2*i && heap[2*i].freq < heap[min].freq) {
                min = 2 * i;
            }
            if(size >= 2*i+1 && heap[2*i+1].freq < heap[min].freq) {
                min = 2 * i + 1;
            }
            if(min != i) {
                swap(i, min);
                findPos(min);
            }
        }
    }

    public static HTree create(int[] freqs, char[] symbols) {

        myMinHeap minHeap = new myMinHeap(freqs.length);

        for (int i = 0; i < freqs.length; i++)
            if (freqs[i] > 0)
                minHeap.add(new Leaf(freqs[i], symbols[i]));

        while (minHeap.size() > 1) {
            HTree x = minHeap.remove();
            HTree y = minHeap.remove();
            minHeap.add(new Node(x, y));
        }

        return minHeap.remove();
    }

    // a tail recursive solution
    public static ArrayList<Pair> makeHuffmanCode(HTree tree, StringBuffer codeSoFar, ArrayList<Pair> output){

        if (tree instanceof Leaf) {
            Pair newLeaf = new Pair();
            newLeaf.x = (Leaf) tree;
            newLeaf.y = new String (codeSoFar);
            output.add(newLeaf);
        }
        else if (tree instanceof Node){
            codeSoFar.append('1');
            makeHuffmanCode( ((Node) tree).r, codeSoFar, output );
            codeSoFar.delete(codeSoFar.length()-1, codeSoFar.length());

            codeSoFar.append('0');
            makeHuffmanCode( ((Node) tree).l, codeSoFar, output );
            codeSoFar.delete(codeSoFar.length()-1, codeSoFar.length());
        }
        return output;
    }

    public static void main(String[] args) {

        // handle input
        String symbol = "12345678";
        char[] symbols = symbol.toCharArray();
        int[] freqs = {36, 18, 12, 9, 7, 6, 5, 4};

        // make Huffman Tree
        HTree answer = create(freqs, symbols);

        // print the output
        StringBuffer codes = new StringBuffer(""); System.out.println("SYMBOL" + "\t" + "FREQ" + "\t" + "HUFFMANCODE");
        ArrayList<Pair> answer2 = makeHuffmanCode(answer, codes, new ArrayList<>());
        answer2.sort(Comparator.comparing(Pair::getLL).reversed());
        for(Pair leaf: answer2){
            System.out.println(leaf.x.value + "\t" + leaf.x.freq + "\t" + leaf.y);
        }

    }
}

// helper class

class Pair{
    Leaf x;
    String y;

    int getLL(){
        return x.getFreq();
    }
}

/**
 *  HuffmanTree classes
 *
 *        HTree
 *         /  \
 *     Leaf   Node
 *
  */

abstract class HTree implements Comparable<HTree> {
    int freq;

    public HTree(int freq){
        this.freq = freq;
    }

    public int compareTo(HTree tree) {
        return freq - tree.freq;
    }
}

class Leaf extends HTree { // Leaf object will have both 'freq' and 'value' data members!
    char value;

    Leaf(int freq, char val) {
        super(freq);
        value = val;
    }
    int getFreq(){
        return super.freq;
    }
}

class Node extends HTree { // Node object will have both 'freq' and 'l' & 'r' data members!
    HTree l, r;

    Node(HTree l, HTree r) {
        super(l.freq + r.freq);
        this.l = l;
        this.r = r;
    }
}

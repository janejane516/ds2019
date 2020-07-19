public class MyMinHeap {
    int[] heap;
    int size;
    int capacity;

    public MyMinHeap() {
        //index starts from 1
        size = 0;
        capacity = 1000;
        heap = new int[capacity];
    }

    public void insert(int k) {
        heap[++size] = k;
        int a = size;
        while(a != 1 && heap[a] < heap[a/2]) {
            int tmp = heap[a/2];
            heap[a/2] = heap[a];
            heap[a] = tmp;
            a = a/2;
        }
    }

    public void remove_Max() {
        int x = heap[size];
        heap[1] = x;
        size--;
        findPos(1);
    }

    public void findPos(int i) {
        int min = i;
        if(size >= 2*i && heap[2*i] < heap[min]) {
            min = 2 * i;
        }
        if(size >= 2*i+1 && heap[2*i+1] < heap[min]) {
            min = 2 * i + 1;
        }
        if(min != i) {
            int tmp = heap[i];
            heap[i] = heap[min];
            heap[min] = tmp;
            findPos(min);
        }
    }

    public void printHeap() {
        for(int i=1; i<=size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyMinHeap myheap = new MyMinHeap();
        int[] arr = {2, 9, 1, 3, 4, 5, 9, 2, 1, 7};
        for(int i=0; i<arr.length; i++) {
            myheap.insert(arr[i]);
        }
        myheap.printHeap();
        myheap.remove_Max();
        myheap.printHeap();
    }
}

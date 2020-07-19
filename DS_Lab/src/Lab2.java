import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab2 {

    int n;
    int[] arr1;  //keep original unsorted array
    int[] arr2;  //you will sort this array

    public Lab2(int n, int[] arr1, int[] arr2) {
        this.n = n;
        this.arr1= arr1;
        this.arr2 = arr2;
    }

    public void reinitializeArray() {
        System.arraycopy(arr1, 0, arr2, 0 ,n);
    }



    /* TODO 1: Implement insertionSort(), which should sort "arr2" */
    public void insertionSort() {
        for(int i=1; i<arr2.length; i++) {
            int x = arr2[i];
            int j;
            for(j=i-1; j>=0 && arr2[j] > x; j--) {
                arr2[j+1] = arr2[j];
            }
            arr2[j+1] = x;
        }
    }


    /* TODO 2: implement printArr1() and printArr2(), which prints arr1 and arr2 respectively */
    public void printArr1() {
        System.out.println("Unsorted array: ");
        for(int i=0; i<arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
    }
    public void printArr2() {
        System.out.println("Sorted array: ");
        for(int i=0; i<arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

    }



    /* TODO 3: - Performance tests in milliseconds for both insertionSort and quickSort.
     *           Hint: keep a counter and run the test for 1000 miliseconds, then divide the elapsed time by the counter
     *           Hint: you should use reinitialize()
     *         - Print in new lines the results in the following format:
     *           Insertion Sort: ___ ms
     *           Quick Sort: ___ ms
     */
    public void performanceTest() {
        long startTime1 = System.currentTimeMillis();
        long counter1 = 0;
        do{
            counter1++;
            reinitializeArray();
            insertionSort();
        } while (System.currentTimeMillis() - startTime1 < 1000);
        long elapsed1 = System.currentTimeMillis() - startTime1;

        long startTime2 = System.currentTimeMillis();
        reinitializeArray();
        long counter2 = 0;
        do{
            counter2++;
            reinitializeArray();
            quickSort(arr2,0,n-1);
        } while (System.currentTimeMillis() - startTime2 < 1000);
        long elapsed2 = System.currentTimeMillis() - startTime2;

        System.out.println("Insertion Sort: " + ((float) elapsed1)/counter1 + "ms");
        System.out.println("Quick Sort: " + ((float) elapsed2)/counter2 + "ms");
    }



    /* TODO 4: - Read the first number in the file: n
     *         - Read the following n numbers and store them in an array: arr1
     *         - Make a copy of the array: arr2
     *         - Use n, arr1, arr2 to initialize Lab2 class
     *         - Then call methods insertionSort(), printArr1(), printArr2(), performanceTest()
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(new File("C:\\Users\\신재인\\IdeaProjects\\Data Structure\\src\\input.txt"));
        int n = console.nextInt();
        int tmp1[] = new int[n];
        int tmp2[] = new int[n];
        for(int i=0; i<n; i++) {
            tmp1[i] = console.nextInt();
            tmp2[i] = tmp1[i];
        }
        Lab2 lab2 = new Lab2(n, tmp1, tmp2);
        System.out.println("2016-18223 Jane Shin");
        lab2.insertionSort();
        lab2.printArr1();
        lab2.printArr2();
        lab2.performanceTest();

    }






    /* Given quickSort implementation.*/

    public static void quickSort(int array[], int first, int last) {
        if (first < last) {
            int splitIndex = split(array, first, last);
            quickSort(array, first, splitIndex - 1);
            quickSort(array, splitIndex + 1, last);
        }
    }
    private static int split(int array[], int first, int last) {

        int pivot = array[last];

        int i = (first - 1);

        for (int j = first; j < last; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[last];
        array[last] = temp;

        return i + 1;
    }
}








import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;

public class InsertionSort {
    public static void main(String[] args) {
        int[] a = {8, 2, 1, 2, 0, 3, 90, 6, 10, 6, 4};
        InsertionSort(a);
        System.out.println(Arrays.toString(a));
    }
    public static void InsertionSort(int[] a) {
        for(int i=0; i<a.length; i++) {
            int t = a[i];
            int j;
            for(j=i-1; j>=0 && t<a[j]; j--) {
                a[j+1] = a[j]; //worst case: i times
            }
            a[j+1] = t;
        }
    }

}

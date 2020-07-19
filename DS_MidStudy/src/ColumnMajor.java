import java.util.Arrays;

public class ColumnMajor {
    public static void main(String[] args) {
        int a[][] = {{0,1,2},{3,4,5},{6,7,8},{9,10,11}};
        int b[] = Convert_to_Column_Major(a);
        System.out.println(Arrays.toString(b));
    }

    public static int[] Convert_to_Column_Major(int[][] a) {
        int r = a.length;
        int ret[] = new int[a.length * a[0].length];
        for(int i=0; i<a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                ret[i + r * j] = a[i][j];
            }
        }
        return ret;
    }
}

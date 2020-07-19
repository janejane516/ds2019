import java.util.*;
public class Matrix_Multiplication {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the number of rows and columns of first matrix");
        Matrix a = new Matrix(console.nextInt(), console.nextInt());
        System.out.println("Enter elements of first matrix");
        for(int i=0; i<a.row; i++) {
            for(int j=0; j<a.col; j++) {
                a.elem[i][j] = console.nextDouble();
            }
        }
        System.out.println("Enter the number of rows and columns of second matrix");
        Matrix b = new Matrix(console.nextInt(), console.nextInt());
        System.out.println("Enter elements of second matrix");
        for(int i=0; i<b.row; i++) {
            for(int j=0; j<b.col; j++) {
                b.elem[i][j] = console.nextDouble();
            }
        }
        a.multiply(b);
    }
}

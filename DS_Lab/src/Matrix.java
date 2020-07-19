public class Matrix {
    int row;
    int col;
    double elem[][];

    public Matrix(int r, int c) {
        row = r;
        col = c;
        elem = new double[r][c];
    }


    public void multiply(Matrix B) {
        if (this.col != B.row) {
            System.out.println("The matrices can't be multiplied with each other.");
        }
        else {
            System.out.println("Product of matrices:");
            Matrix result = new Matrix(this.row, B.col);
            for(int i = 0; i < this.row; i++) {
                for(int j = 0; j < B.col; j++) {
                    for(int k = 0; k < this.col; k++) {
                        result.elem[i][j] = this.elem[i][k] * B.elem[k][j];
                    }
                }
            }
            for(int i = 0; i < result.row; i++) {
                for(int j = 0; j < result.col; j++) {
                    System.out.print(result.elem[i][j] + " ");
                }
                System.out.println();
            }
        }

    }
}

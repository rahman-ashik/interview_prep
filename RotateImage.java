import java.util.Arrays;

public class RotateImage {
    public static void rotate(int[][] matrix) {
        int n = matrix.length;



        System.err.println("Before anything : " +Arrays.deepToString(matrix));

        // transpose matrix
        for (int i = 0; i < n / 2; i++) { 
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
                System.err.println("After "+ i + "." + j +"th iteration: "+  Arrays.deepToString(matrix));
            }
        }
    }




    public static void main(String[] args) {
        int[][] matrix = new int[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][]  matrix2 = new int[][] {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        rotate(matrix);
        System.err.println(Arrays.deepToString(matrix));
        rotate(matrix2);
        System.err.println(Arrays.deepToString(matrix2));
    }
}

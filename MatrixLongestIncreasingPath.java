public class MatrixLongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        int res = 0;

        // early return
        if (matrix == null || matrix.length == 0) {
            return res;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // dp[i][j] = longest path from (i, j)
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, dp, i, j));
            }
        }

        // print the dp matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return res;
    }

    private int dfs(int[][] matrix, int[][] dp, int i, int j) {

        // if (i, j) is already visited, return dp[i][j]
        if (dp[i][j] != 0) {    return dp[i][j]; }

        // m and n are the number of rows and columns
        int m = matrix.length;
        int n = matrix[0].length;

        int res = 1;

        // check the four directions (0, 1), (0, -1), (1, 0), (-1, 0)
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        // check the four directions
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            // if (x, y) is invalid, we don't need to check it
            if (   
                x < 0 || x >= m || 
                y < 0 || y >= n || 
                matrix[x][y] <= matrix[i][j]
                )  continue;
            

            res = Math.max(res, 1 + dfs(matrix, dp, x, y));
        }

        // memoize the result
        dp[i][j] = res;
        return res;
    }





    public static void main(String[] args) {
        MatrixLongestIncreasingPath matrixLongestIncreasingPath = new MatrixLongestIncreasingPath();
        int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(matrixLongestIncreasingPath.longestIncreasingPath(matrix));
        matrix = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
        System.out.println(matrixLongestIncreasingPath.longestIncreasingPath(matrix));
    }
}

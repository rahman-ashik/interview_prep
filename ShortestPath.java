import java.util.*;

public class ShortestPath {
/*
    You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). 
    You can move up, down, left, or right from and to an empty cell in one step.
    Return the minimum number of steps to walk from the upper left corner (0, 0) to 
    the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. 
    If it is not possible to find such walk return -1.

    Example 1:
    Input: grid = [ [0,0,0],
                    [1,1,0],
                    [0,0,0],
                    [0,1,1],
                    [0,0,0]], k = 1
    Output: 6
    Explanation: 
    The shortest path without eliminating any obstacle is 10.
    The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

    Example 2:
    Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
    Output: -1
    Explanation: We need to eliminate at least two obstacles to find such a walk.
*/



    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        int[][][] dists = new int[m][n][k+1];
        for (int[][] dist : dists) 
            for (int[] d : dist)
                Arrays.fill(d, Integer.MAX_VALUE);
        
        Arrays.fill(dists[0][0], 0);
        
        // min-heap storing {i, j, # obstacles eliminated, dist}, sorted by distance to (0,0)
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[3] - b[3]);
        heap.offer(new int[]{0, 0, 0, 0});
        
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            if (curr[0] == m-1 && curr[1] == n-1) continue;
            
            for (int[] dir : DIRECTIONS) {
                int newX = curr[0] + dir[0];
                int newY = curr[1] + dir[1];
                
                // 1). continue if out of bound
                if (newX < 0 || newY < 0 || newX >= m || newY >= n) continue;
                
                // 2). continue if out of elimation
                int newK = curr[2] + grid[newX][newY];
                if (newK > k) continue;
                
                // 3). continue if we have more optimal result
                int newDist = curr[3] + 1;
                if (dists[newX][newY][newK] <= newDist) continue;
                
                dists[newX][newY][newK] = newDist;
                heap.offer(new int[]{newX, newY, newK, newDist});
            }
        }
        
        int res = dists[m-1][n-1][0];
        for (int i = 1; i <= k; i++) res = Math.min(res, dists[m-1][n-1][i]);
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }


    /*
    Given an m x n integer matrix grid, return the maximum score of 
    a path starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal directions.
    The score of a path is the minimum value in that path.


    For example, the score of the path 8 → 4 → 5 → 9 is 4.
    

    Example 1:
    Input: grid = [ [5,4,5],
                    [1,2,6],
                    [7,4,6]]
    Output: 4  because the path 5 → 4 → 5 → 6 → 6 has the minimum value 4.

    Example 2:
    Input: grid = [ [2,2,1,2,2,2],
                    [1,2,2,2,1,2]]
    Output: 2 because the path 2 → 2 → 2 → 2 → 2 → 2 → 2 → 2 → 2 has the minimum value 2.

    Example 3:
    Input: grid = [         [3,4,6,3,4],
                            [0,2,1,1,7],
                            [8,8,3,2,7],
                            [3,2,4,9,8],
                            [4,1,2,0,0],
                            [4,6,5,4,3]     ]
    Output: 3  because the path 3 → 4 → 6 → 3 →  4 → 7 → 7 → 8 → 9 → 4 → 3 → 8 → 8 → 3 → 4 → 6 → 5 → 4 → 3 has the minimum value 3.
    */
    public int maximumMinimumPath(int[][] A) {
        int m = A.length, n = A[0].length;
        
        // socres[x][y] : score of reachng (x,y) from (0,0); initialize as -1
        int[][] scores = new int[m][n];
        for (int[] score : scores) Arrays.fill(score, -1);
        scores[0][0] = A[0][0];
        
        // max-heap storing tripletes {x, y, score of reachng (x,y) from (0,0)}
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> b[2] - a[2]);
        heap.offer(new int[]{0, 0, A[0][0]});
        
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int x = curr[0], y = curr[1], score = curr[2];
            
            if (x == m-1 && y == n-1) return score;
            
            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0], newY = y + dir[1];
                if (newX < 0 || newY < 0 || newX >= m || newY >= n) continue;
                
                int newScore = Math.min(score, A[newX][newY]);
                if (scores[newX][newY] < newScore) {
                    scores[newX][newY] = newScore;
                    heap.offer(new int[]{newX, newY, newScore});
                }
            }
            
        }
        
        return -1;
    }





    public static void main(String[] args) {
        ShortestPath shortestPath = new ShortestPath();

        System.out.println(" =========== shortestPath (with obs elimination) =========== ");
        int[][] grid = {{1,0,0,0},{1,1,1,1},{1,0,0,0},{1,0,0,0}};
        System.out.println(shortestPath.shortestPath(grid, 1));

        System.out.println(" =========== maximumMinimumPath =========== ");
        grid = new int[][]{{2,2,1,2,2,2}, {1,2,2,2,1,2}};
        System.out.println(shortestPath.maximumMinimumPath(grid));

    }
}

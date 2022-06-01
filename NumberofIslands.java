import java.util.*;

public class NumberofIslands {

    public int numIslands(char[][] grid) {
        int count = 0;

        // iterate through the grid and check if the current cell is an island
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // if the current cell is an island, then we need to check if it is surrounded by water
                if (grid[i][j] == '1') {
                    // clear the island checking up, down, left, and right
                    checkAndClear(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void checkAndClear(char[][] grid, int i, int j) {
        if (    i < 0               || 
                j < 0               || 
                i >= grid.length    || 
                j >= grid[i].length || 
                grid[i][j] == '0')      
            return;
        
        grid[i][j] = '0';
        checkAndClear(grid, i+1, j);    // down
        checkAndClear(grid, i-1, j);    // up
        checkAndClear(grid, i, j+1);    // right
        checkAndClear(grid, i, j-1);    // left
        return;
    
    }





    public static void main(String[] args) {
        NumberofIslands numberofIslands = new NumberofIslands();
        char[][] grid = new char[][] {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println(numberofIslands.numIslands(grid));
        grid = new char[][] {
            {'1','1','0','1','1'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println(numberofIslands.numIslands(grid));
    }
}

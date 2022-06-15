import java.util.Arrays;

public class Knapsacks {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] weights = {2, 2, 4, 6, 3};
        int[] values = {3, 4, 5, 7, 1};
        int capacity = 10;
        // System.out.println(knapsack(weights, values, capacity));
        System.out.println(knapsackDP(weights, values, capacity));
        weights = new int[] {2, 3, 1, 4};
        values = new int[] {30, 5, 7, 90};
        capacity = 6;
        System.out.println(knapsackDP(weights, values, capacity));
    }




    // Recursive Approach
    private static int knapsack(int[] weights, int[] values, int capacity) {
        return knapsackRecurse(weights, values, capacity, 0);
    }

    private static int knapsackRecurse(int[] weights, int[] values, int capacity, int i) {
        int n = weights.length;
        if (i>=n || capacity<=0) return 0 ;


        int profit_including_this = 0 ;
        if (weights[i]<=capacity)
            profit_including_this = weights[i] + knapsackRecurse ( weights, values, capacity- weights[i] , i + 1 );


        int profit_excluding_this = knapsackRecurse ( weights, values, capacity , i + 1 );

        int max_profit = Math.max(profit_excluding_this, profit_including_this);

        return max_profit;
    }


    // Approach: Memoized DP
    private static int knapsackDP(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n+1][capacity+1];
        for ( int i = 0; i <= n; i++ ) {
            for ( int j = 0; j <= capacity; j++ ) {
                if ( i == 0 || j == 0 ) {
                    dp[i][j] = 0;
                } else if ( weights[i-1] <= j ) {
                    dp[i][j] = Math.max (   dp[i-1][j], 
                                            values[i-1] + dp[i-1][j-weights[i-1]] );
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][capacity];
    }
    private static int knapsackRecurseDP(Integer[][] dp, int[] weights, int[] values, int capacity, int i) {
        int n = weights.length;
        if (i>=n || capacity<=0) return 0 ;


        // DP mod
        if ( dp[i][capacity]!= null) return dp[i][capacity];

        int profit_including_this = 0 ;
        if (weights[i]<=capacity)
            profit_including_this = weights[i] + knapsackRecurse ( weights, values, capacity- weights[i] , i + 1 );


        int profit_excluding_this = knapsackRecurse ( weights, values, capacity , i + 1 );

        int max_profit = Math.max(profit_excluding_this, profit_including_this);

        // DP mod 
        dp[i][capacity] = max_profit;

                        // print the dp
                        for (int idx = 0; idx < weights.length; idx++) {
                            for (int j = 0; j < capacity+1; j++) {
                                System.out.print(dp[idx][j] + " ");
                            }
                            System.out.println();
                        }



        return max_profit;
    }
}

import java.util.*;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int n = coins.length;
        int numOfCoins = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            int coin = coins[i];
            while (amount >= coin) {
                amount -= coin;
                numOfCoins++;
            }
        }

        return numOfCoins;
    }

    // dp solution ( fastest )
    public int coinChange2(int[] coins, int amount) {

        int[] dp = new int[amount + 1]; // dp[i] = the min number of coins to make up amount i

        for (int i=1 ; i<dp.length ; i++) dp[i] = amount+1; // initialize dp array with amount+1 as a flag for impossible amount

        dp[0] = 0; // 0 coins to make up amount 0

        System.err.print("         Amounts    " );
        for (int i=0 ; i<=amount ; i++) {  System.err.print(i + "  ");}

        System.err.println();


        System.err.println("before loops       " + Arrays.toString(dp));
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                int currentCoinCount = dp[i - coin] + 1; // currentCoinCount is the min number of coins to make up amount i - coin; then + 1
                dp [i] = Math.min( dp[i], currentCoinCount );
                System.err.println("coin: " + coin + "  i: " + i + "  dp[i - coin]: " + dp[i - coin]+ "  dp[i]: " + dp[i]);
            }
            System.err.println("After coin " + coin + " dp is " + Arrays.toString(dp));
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    // HashMap solution
    public int coinChange3(int[] coins, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=1 ; i<amount+1 ; i++) map.put(i, amount+1);
        map.put(0, 0);

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                int temp = Math.min(map.get(i), map.get(i - coin) + 1);
                map.put(i, temp);
            }
        }

        return map.get(amount) > amount ? -1 : map.get(amount);
    }

    // 2D knapsack
    public int coinChange4(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }


    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] coins = {1 , 2, 5};
        int amount = 7;
        System.out.println(cc.coinChange2(coins, amount));
        // coins = new int[]{2};
        // amount = 3;
        // System.out.println(cc.coinChange2(coins, amount));
        // coins = new int[]{1};
        // amount = 0;
        // System.out.println(cc.coinChange2(coins, amount));
        // coins = new int[]{10, 5, 25};
        // amount = 75;
        // System.out.println(cc.coinChange2(coins, amount));
        // coins = new int[]{1, 2, 5};
        // amount = 11;
        // System.out.println(cc.coinChange2(coins, amount));

    }
}

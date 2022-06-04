public class BuySellStock {
    public int maxProfit(int[] prices) {
        //simple base case if we have an array with single element we are making zero profit
        if(prices.length == 1) return 0;
         
         int dp[] = new int[prices.length];  
         dp[0] = prices[0];  //setting the current min price
         int maxProfit = Integer.MIN_VALUE;
         for(int i = 1; i< prices.length; i++) {
             int profit = prices[i] - dp[i -1];
             if(profit > 0){
                dp[i] = dp[i-1];
                if(maxProfit < profit) 
                    maxProfit = profit; //checking if we still have maxProfit
             }
             else dp[i] = prices[i];  //if we have negative value in profit                        
         }
         
         if(maxProfit == Integer.MIN_VALUE ) return 0; //if maxProfit is still equal to that of min_value 
         //this means we have not yet generated any +ve value of profit so we return 0;
         return maxProfit;
     }

     // kadane's algorithm
     public int maxProfit2(int[] prices) {
		//simple base case
		
        if(prices.length == 1) return 0;
        
		int currentLowest = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for(int i = 0; i< prices.length; i++)
        {
            int current = prices[i];
            if(current < currentLowest) currentLowest = current;
            else 
            {
                int profit = current - currentLowest;
                if (profit > maxProfit) maxProfit = profit;
            }
            
        }
        if(maxProfit == Integer.MIN_VALUE ) return 0;
        return maxProfit;
    }

     public static void main(String[] args) {
            BuySellStock buySellStock = new BuySellStock();
            int[] prices = {7,1,5,3,6,4};
            System.out.println(buySellStock.maxProfit(prices));
            prices = new int[]{5,4,3,2,1};
            System.out.println(buySellStock.maxProfit(prices));
     }
}

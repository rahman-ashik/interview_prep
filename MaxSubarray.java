public class MaxSubarray {
    
        // DP approach
        public int maxSubArray(int[] A) {
            int n = A.length;
            int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
            dp[0] = A[0];
            int max = dp[0];
            
            for(int i = 1; i < n; i++){
                int currentSum = 0 ;
                if (dp[i - 1] > 0) {
                    currentSum = dp[i - 1] ;
                }
                dp[i] = A[i] + currentSum;
                max = Math.max(max, dp[i]);
            }
            
            return max;
        }
    
        // faster approach
        public int maxSubArray1(int[] nums) {
            int sum = Integer.MIN_VALUE;
            int prevSum = 0;
            
            // Loop through the array O(n)
            for(int i=0; i<nums.length; i++){
                prevSum += nums[i];
                sum = Integer.max(prevSum, sum);
                
                if(prevSum < 0) prevSum = 0;
            }
            
            return sum;
        }


    public static void main(String[] args) {
        MaxSubarray maxSubarray = new MaxSubarray();
        int[] nums = { -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4 };
        //             -2  -1   -3   4    3   5   6    1   5  
        System.out.println(maxSubarray.maxSubArray(nums));
        nums = new int[]{ 5 , 4 , -1 , 7 , 8 };
        System.out.println(maxSubarray.maxSubArray(nums));
    }
}

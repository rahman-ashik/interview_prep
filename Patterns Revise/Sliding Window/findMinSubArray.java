public class findMinSubArray {
 
    public static void main(String[] args) {
        int[] arr = {2,1,5,1,3,2};
        int minSum = findMinSum(arr, 9);
        System.out.println("Min sum is " + minSum);
    }

    // Given an array of positive numbers and a positive number ‘k’, 
    // find the length of the smallest contiguous subarray whose sum is 
    // greater than or equal to ‘k’. Return 0, if no such subarray exists.
    
    
    
    private static int findMinSum(int[] arr, int k) {

        //earthworm steps: expand until growth met, shrink until the growth is over
            //psuedo: 
            // =>   add-up elements from the beginning of the array until their sum becomes 
            //      greater than or equal to k
            // =>   keep adding right element in the sliding window
            // =>   keep shrinking the window from the left if sum becomes 
            //      greater than or equal to k , update minCount while shrinking

        int minCount = Integer.MAX_VALUE ; 
        int n = arr.length;


        int left = 0 ;
        int right = 0; 
        int windowSum = 0;
        while  ( right < n  ) {

            windowSum += arr[right];

            while (windowSum>= k) {
                minCount = Math.min(minCount, right-left+1);
                windowSum -= arr[left];
                left++;
            }

            right++;
        }

        return minCount;
    }
}

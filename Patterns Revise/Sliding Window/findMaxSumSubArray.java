class findMaxSumSubArray {
    public static void main(String[] args) {
        int[] arr = {2,1,5,1,3,2};
        int maxSum = findMaxSum(arr, 3);
        System.out.println("Max sum is " + maxSum);
    }

    public static int findMaxSum(int[] arr, int k) {
        // in short : we slide a window of size k and keep deleting the leftmost element and adding a new rightmost element
        int maxSum = 0;
        int sum = 0;

        // get kSum from the first window
        for ( int i=0 ; i< k; i++)     sum += arr[i];
        
        System.err.println("first sum is " + sum);

        maxSum = sum ;

        for ( int i=k ; i< arr.length; i++) {
            sum -= arr[i-k]; // delete the leftmost element
            sum += arr[i]; // add the new rightmost element
            System.err.println("delete " + arr[i-k] + " add " + arr[i] + " sum " + sum);
            maxSum = Math.max(maxSum, sum); // update the maxSum
        }
        return maxSum;
    }
}
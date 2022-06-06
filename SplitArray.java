public class SplitArray {
    public int splitArray(int[] nums, int m) {
        int lo = max(nums);  
        int hi = sum(nums);

        System.err.println("lo: " + lo + " hi: " + hi);
        
        while(lo<hi){
            int mid = (lo+hi)/2;
            System.err.print("mid: " + mid);
            System.err.println("   |     Splits Return: " + splits(nums, mid));
            if(splits(nums,mid)>m){
                lo = mid+1;
            }else{
                hi = mid;
            }
        }
        return lo;
    }
    
    //split the array to nums.length subarrays
    int max(int[]nums){
        int max = Integer.MIN_VALUE;
        for(int num:nums)max = Math.max(max,num);
        return max;
    }
    
    //split the array to one subarray with all elements.
    int sum(int[]nums){
        int sum = 0;
        for(int num:nums)sum+=num;
        return sum;
    }
    
    //number of splits to satisfy each subarray is less then mid
    private int splits(int[]nums,int mid) {
        int sum = 0;
        int splits = 1;
        for (int num : nums) {
            sum += num;
            if (sum > mid) {
                splits++;
                sum = num;
            }
        }
        return splits;
    }

    public static void main(String[] args) {
        SplitArray sa = new SplitArray();
        int[] nums = {7,2,5,10,8};
        System.out.println(sa.splitArray(nums,2));
    }
}

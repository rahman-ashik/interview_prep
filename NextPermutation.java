import java.util.Arrays;

public class NextPermutation {
    public static void nextPermutation(int [] nums) {
            int n = nums.length;
            if (n == 1) {
                return;
            }

            // find the first decreasing element
            boolean found = false; // flag to indicate if we found any decreasing element
            int i = n - 2;
            for (; i>=0; i--) {
                if(nums[i] < nums[i+1]) {
                    found = true;
                    break;
                }
            }

            // if no decreasing element, reverse the array
            if (!found) {
                reverse(nums, 0, n-1);
                return;
            }

            // find the first element greater than nums[i]
            int j = n - 1;
            for(; j > i; j--) {
                if (nums[i] < nums[j]) {
                    break;
                }
            }

            //switch 
            swap(nums, i, j);
            // reverse the rest 
            reverse(nums, i+1, n-1);
        }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
        
        public static void reverse(int[] nums, int m, int n) {
            for(int i = 0; m + i < n - i; i++) {
                int temp = nums[m+i];
                nums[m+i] = nums[n-i];
                nums[n-i] = temp;
            }
        }




    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        System.err.println(Arrays.toString(nums));
        nums = new int[] {3, 2, 1};
        nextPermutation(nums);
        System.err.println(Arrays.toString(nums));
        nums = new int[] {1, 1, 5};
        nextPermutation(nums);
        System.err.println(Arrays.toString(nums));
        nums = new int[] {3, 2, 1};
        nextPermutation(nums);
        System.err.println(Arrays.toString(nums));
    }
}

public class ProductExceptSelf {
    /*
    Given numbers [2, 3, 4, 5], regarding the third number 4, 
    the product of array except 4 is 2*3*5 which consists of 
    two parts: left 2*3 and right 5. The product is left*right. 
    
    We can get lefts and rights:

    Numbers:     2    3    4     5
    Lefts:            2  2*3 2*3*4
    Rights:  3*4*5  4*5    5      
    Let’s fill the empty with 1:

    Numbers:     2    3    4     5
    Lefts:       1    2  2*3 2*3*4
    Rights:  3*4*5  4*5    5     1

    We can calculate lefts and rights in 2 loops. The time complexity is O(n).
    */

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // Calculate lefts and store in res.
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0)
                left = left * nums[i - 1];
            res[i] = left;
        }
        // Calculate rights and the product from the end of the array.
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1)
                right = right * nums[i + 1];
            res[i] *= right;
        }
        return res;
    }

    public static void main(String[] args) {
        ProductExceptSelf p = new ProductExceptSelf();
        int[] nums = {1, 2, 3, 4};
        int[] res = p.productExceptSelf(nums);
        for (int i : res) {
            System.out.print(i + " ");
        }

        System.err.println("\n");

        nums = new int[] {-1, 3, 1, 2};
        res = p.productExceptSelf(nums);
        for (int i : res) {
            System.out.print(i + " ");
        }

    }
}


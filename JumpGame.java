public class JumpGame {
    public static boolean canJump(int[] nums) {
        // keep track of the farthest index that can be reached
        int global_max = 0;
        for (int i = 0; i < nums.length; i++) {

            // if we go out of the array, we return false
            if (i > global_max) {
                return false;
            }

            // current index is the farthest index that can be reached
            int current_farthest = i + nums[i];

            // update the global max
            global_max = Math.max(global_max, current_farthest);
            

        }
        return true;   
    }



    public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 1, 1, 4};
        System.err.println(canJump(nums));
        nums = new int[] {3, 2, 1, 0, 4};
        System.err.println(canJump(nums));
        nums = new int[] {0};
        System.err.println(canJump(nums));
    }
}

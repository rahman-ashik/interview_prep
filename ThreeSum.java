import java.util.*;

public class ThreeSum {
    public static List < List < Integer >> threeSum(int[] nums) {
        // Sort the given array
        Arrays.sort(nums);

        List < List < Integer >> result = new ArrayList < > ();
        for (int num1Idx = 0; num1Idx + 2 < nums.length; num1Idx++) {
            // Skip all duplicates from left
            // num1Idx>0 ensures this check is made only from 2nd element onwards
            if (num1Idx > 0 && nums[num1Idx] == nums[num1Idx - 1]) {
                continue;
            }

            int num2Idx = num1Idx + 1;
            int num3Idx = nums.length - 1;
            while (num2Idx < num3Idx) {
                int sum = nums[num2Idx] + nums[num3Idx] + nums[num1Idx];
                if (sum == 0) {
                    // Add triplet to result
                    result.add(Arrays.asList(nums[num1Idx], nums[num2Idx], nums[num3Idx]));

                    num3Idx--;

                    // Skip all duplicates from right
                    while (num2Idx < num3Idx && nums[num3Idx] == nums[num3Idx + 1]) num3Idx--;
                } else if (sum > 0) {
                    // Decrement num3Idx to reduce sum value
                    num3Idx--;
                } else {
                    // Increment num2Idx to increase sum value
                    num2Idx++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.print("{-1, 0, 1, 2, -1, -4} => ");
        System.out.print("\tthreeSum: "+threeSum(nums));
        System.out.print("\ttwoSum:   "+twoSum(nums));
        nums = new int[] {4, 1, 0, -4, -5, 0};
        System.out.print("\n{4, 1, 0, -4, -5, 0} ==>");
        System.out.print("\tthreeSum: "+threeSum(nums));
        System.out.print("\ttwoSum:   "+twoSum(nums));

    }


    public static List<List<Integer>> twoSum(int[] nums) {
        var seen = new HashSet<Integer>();
        var result = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            int remainder = 0 - nums[i];
            if (seen.contains(remainder)) {
                result.add(Arrays.asList(nums[i], remainder));
            }
            seen.add(nums[i]);
        }
        return result;
    }
}

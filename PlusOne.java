import java.util.Arrays;

public class PlusOne {
    public static int[] plusOne(int[] digits) {

        // carry is assumed 1 at the beginning because 
        // we are adding 1 to the last digit
        int carry = 1;


        // iterate through the array backwards
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }

        // if carry is 1, then we need to add a new digit
        // to the front of the array
        // we need to create a new array with one more digit
        if (carry == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                newDigits[i + 1] = digits[i];
            }
            return newDigits;
        }
        return digits;
    }





    public static void main(String[] args) {
        int[] nums = new int[] {9, 9, 9};
        System.err.println(Arrays.toString(plusOne(nums)));
        nums = new int[] {1, 2, 3};
        System.err.println(Arrays.toString(plusOne(nums)));
        nums = new int[] {1, 2, 9};
        System.err.println(Arrays.toString(plusOne(nums)));
        nums = new int[] {1, 9, 9};
        System.err.println(Arrays.toString(plusOne(nums)));
        nums = new int[] {9, 0, 9};
        System.err.println(Arrays.toString(plusOne(nums)));
    }
}

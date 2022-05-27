public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        // convert num1 to int array for easy manipulation
        int[] nums1 = new int[num1.length()];
        for (int i = 0; i < num1.length(); i++) {
            nums1[i] = num1.charAt(i) - '0';
        }

        // convert num2 to int array for easy manipulation
        int[] nums2 = new int[num2.length()];
        for (int i = 0; i < num2.length(); i++) {
            nums2[i] = num2.charAt(i) - '0';
        }

        // multiply nums1 and nums2
        int[] result = new int[num1.length() + num2.length()];
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                result[i + j + 1] += nums1[i] * nums2[j];
            }
        }

        // take care of the carry ; iterating reversely
        int carry = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] += carry;
            carry = result[i] / 10;
            result[i] %= 10;
        }

        // convert result to string
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]);
        }

        // remove the leading 0s
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }






    public static void main(String[] args) {
        System.err.println(multiply("123", "456"));
        System.err.println(multiply("123", "0"));
        System.err.println(multiply("0", "456"));
        System.err.println(multiply("0", "0"));
        System.err.println(multiply("12", "4"));
    }
}

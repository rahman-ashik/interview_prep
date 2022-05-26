class Solution3 {
    public String licenseKeyFormatting(String S, int K) {
        S = S.replaceAll("-", "").toUpperCase();
        StringBuilder sb = new StringBuilder(S);
        // Starting from the end of sb, and going backwards. 
        int i = sb.length() - K;
        while(i > 0) {
            sb.insert(i, '-');
            i = i - K;
        }
        return sb.toString();
    }
}





public class licenseKeyFormatting {
    public static void main(String[] args) {
        Solution3 sol = new Solution3();
        String s = "5F3Z-2e-9-w";
        int k = 4;
        System.out.println(sol.licenseKeyFormatting(s, k)); // "5F3Z-2E9W"
        String s2 = "2-5g-3-J";
        int k2 = 2;
        System.out.println(sol.licenseKeyFormatting(s2, k2)); // "2-5G-3J"
        String s3 = "2-4A0r7-4k";
        int k3 = 4;
        System.out.println(sol.licenseKeyFormatting(s3, k3)); // "24A0-R74K"
    }
}

import java.util.*;

public class LongestSubstringwithAtMostTwoDistinctCharacters {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int res = 0;

        // sliding window
        int left = 0, right = 0;
        // map to store the char and its count
        Map<Character, Integer> map = new HashMap<>();

        // go from left to right
        while (right < s.length()) {

            char c = s.charAt(right);
            // if the char is already in the map, update its count
            map.put(c, map.getOrDefault(c, 0) + 1);
            // expand the window to the right
            right++;

            while (map.size() > 2) { // if map has more than 2 distinct chars
                // we should prep shrinking the window 
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                // remove the distinct char if its count is 0
                if (map.get(leftChar) == 0)  map.remove(leftChar);

                // shrink the window from the left
                left++;
            }
            int windowSize = right - left;

            // update the max
            res = Math.max(res, windowSize);
        }

        return res;
    }






    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
        s = "ccaabbb";
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
        s = "aab";
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
        s = "eceba";
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));

    }
}

import java.util.HashMap;
import java.util.Map;




public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;

        // current index of any character
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int j = 0, i = 0; j < n; j++) {

            // if the starting character is already in the index map,
            // then update the current index of the character
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }

            int currentWindowSize = j - i + 1;
            // update the answer 
            ans = Math.max(ans, currentWindowSize);

            // put the ending character and its index in the map
            map.put(s.charAt(j), j + 1);
        }


        return ans;
    }




    public static void main(String[] args) {
        String s = "abcabcbb";
        LongestSubstring longestSubstring = new LongestSubstring();
        System.out.println(longestSubstring.lengthOfLongestSubstring(s));
        String s2 = "bbbbb";
        System.out.println(longestSubstring.lengthOfLongestSubstring(s2));
        String s3 = "pwwkew";
        System.out.println(longestSubstring.lengthOfLongestSubstring(s3));
        String s4 = "abba";
        System.out.println(longestSubstring.lengthOfLongestSubstring(s4));

    }




}

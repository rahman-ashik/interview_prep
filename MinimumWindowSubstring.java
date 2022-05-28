import java.util.*;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {

        // create a map of characters in t
        int [] map = new int[128];

        // map the count of each character in t
        for (char c : t.toCharArray()) {
          map[c]++;
        }


        int start = 0, 
            end = 0, 
            minStart = 0, 
            minLen = Integer.MAX_VALUE, 
            counter = t.length();


        // iterate through the string
        while (end < s.length()) {

        

          final char c1 = s.charAt(end);
          // if the character is in the map decrement the counter
          if (map[c1] > 0) { counter--;  } 

          // decrement the character count in the map
          map[c1]--;

          // increment the end pointer
          end++;

          // if the counter is 0, then we found a valid window, we increment the start pointer
          while (counter == 0) {
            int currLen = end - start;
            if (minLen > currLen) {
              minLen = currLen;
              minStart = start;
            }

            final char c2 = s.charAt(start);
            map[c2]++;
            if (map[c2] > 0) counter++;
            start++;
          }
        }
    
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
      }






    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));

        s = "a";
        t = "a";
        System.out.println(minWindow(s, t));

        s = "a";
        t = "b";
        System.out.println(minWindow(s, t));

        s = "a";
        t = "aa";
        System.out.println(minWindow(s, t));
    }
}

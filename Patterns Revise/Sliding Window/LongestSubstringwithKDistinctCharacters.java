import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithKDistinctCharacters {
    
    public static int findLongestSubstring(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        HashMap<Character, Integer> charCount = new HashMap<>();

        int left = 0 ; 
        int right = 0 ;

        while ( right < n ) {

            // map the rightmost and update and expand to the right
            Character currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);


            // if more than k chars mapped, squeeze from left and update mapping
            while (charCount.size()>k) {

                // decrement leftmost char in the map
                Character leftMostChar =  s.charAt(left);
                int leftMostCharCount = charCount.get(leftMostChar);
                charCount.put(leftMostChar, leftMostCharCount-1);

                // if any char is zero, we dont need that char in the map,
                // bcz map.size is important to us
                if (charCount.get(leftMostChar)==0) {
                    charCount.remove(leftMostChar);
                }

                // squeeze from left
                left++;
            }

            // we update on right expanding window coz we looking for MAX
            maxLen = Math.max( maxLen , right - left + 1 );

           right++; 
        }

        
        return maxLen;


    }



    public static void main(String[] args) {
        System.out.println(findLongestSubstring("eceba", 2)); // 3
        System.out.println(findLongestSubstring("eceba", 3)); // 4
        System.out.println(findLongestSubstring("araaci", 2)); // 4
        System.out.println(findLongestSubstring("cbbebi", 3));  // 5
    }
}

import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class LongestSubstringWithSameLettersAfterReplacement {
    // Given a string with lowercase letters only, if you are allowed to replace no more than 
    // ‘k’ letters with any letter, find the length of the longest substring having the same 
    // letters after replacement.


    // aabccbb with k=2 => "bccbb" ==> 5 where we replace 2 c into b 
    // after processing aabccbb, we have "aab" mapped as:
        // a = 2 , b = 1 , left = 0 , right = 2, count = 2-0+1= 3
    // when mapping index 3 to char c, we need to check the map if we can replace the c with b
        // a = 2 , b = 1 , c = 1 




    public static void main(String[] args) {
        System.out.println("Tests for countNoRepeatSubstring");
        System.out.println(longestSubstring("aabccbb", 2)); // 5
        // System.out.println(longestSubstring("abbcb", 1)); // 4
        // System.out.println(longestSubstring("abccde", 1)); // 3 
        // test longestOnes
        System.err.println("Tests for longestOnes");
        System.out.println("10101, 2 ==> " + longestOnes("10101", 2)); // 5
        System.out.println("10101, 3 ==> " + longestOnes("10101", 1)); // 3
        System.out.println("1000101, 4 ==> " + longestOnes("1000101", 4)); // 7
        System.out.println("10100101, 2 ==> " + longestOnes("10100101", 2)); // 4

    }




    private static int longestSubstring(String str, int k) {
        int n = str.length();
        int result = 0;
        int maxRepeatLetterCount  = 0; // max number of repeat letters in the substring
        Map<Character,Integer> charCountMap = new HashMap<>();

        int left  = 0 ;
        int right = 0 ;

        while ( right < n ) {

            Character currentChar = str.charAt(right);
            charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1 ) ;
            int currentWindowSize = right - left + 1 ;
            maxRepeatLetterCount  = Math.max( maxRepeatLetterCount , charCountMap.get(currentChar)) ;

            if (currentWindowSize - maxRepeatLetterCount  > k ) {
                Character  leftMostCharacter = str.charAt(left);
                int leftMostCharacterCount = charCountMap.get(leftMostCharacter);
                charCountMap.put(leftMostCharacter, leftMostCharacterCount-1);
                left++ ; 
            }
            result = Math.max(result, right - left + 1 );
            System.err.print("step: "+ right + " : " + str.substring(left, right+1) + " " + result + " :: ");
            System.err.println("charCountMap: " + charCountMap);
            right++ ;
        }
        return result;
    }


    // a variant problem:
    //  Given an array containing 0s and 1s,
    //  if you are allowed to replace no more than ‘k’ 0s with 1s, 
    //  find the length of the longest contiguous subarray having all 1s.
    private static int longestOnes(String s, int k) {
        int max = 0;
        int n = s.length();
        int[] count = new int[2]; // map for keeping count of 0 and 1

        int left = 0;
        int right = 0;
        while (right < n) {
            // update count for current character
            count[s.charAt(right) - '0']++;

            // handle the case when we have more than k 0s
            while (count[0] > k) {
                count[s.charAt(left) - '0']--; // decrement the count of leftmost character
                left++; // move left pointer 
            }

            // what if we wanted to find the longest subarray with all 0s too ?
            // while (count[1] > k) {
            //     count[s.charAt(left) - '0']--;
            //     left++;
            // }
            max = Math.max(max, right - left + 1);
            right++;
        }


        return max;
    }
}

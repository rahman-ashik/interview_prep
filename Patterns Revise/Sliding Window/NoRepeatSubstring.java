import java.util.HashMap;
import java.util.Map;

public class NoRepeatSubstring {

    public static int countNoRepeatSubstring(String str) {
        int n = str.length();
        int count = 0;


        // map the last seen index for each char
        Map<Character, Integer> lastSeen = new HashMap<>();


        // abcafx  =answer=> bcafx

        int left = 0 ;
        int right = 0 ;

        while ( right < n ) {

            // map this char with this index 
            Character currentChar = str.charAt(right);
            int currentCharIndex = right;

            // but see if it was mapped previously, we need to move the left slider 
            if (lastSeen.containsKey(currentChar)) {
                left = lastSeen.get(currentChar)+1;
            } 

            lastSeen.put(currentChar, currentCharIndex);
            // update and expand
            count = Math.max(count, right-left+1);
            right++;
        }
        return count;
    }

    // walkthru for "abcdabcd" 
    // after processing first 4 chars, we have "abcd" mapped as: 
        // a = 0 , b = 1 , c = 2 , d = 3 , left = 0 , right = 3, count =3-0+1= 4
    // when mapping index 4 to char a, we need to move the left slider to lastSeen+1, 
        // so left = 1 , right = 4, count = 4-1+1 = 4 and a = 4 , b = 1 , c = 2 , d = 3
    // when mapping index 5 to char b, we need to move the left slider to lastSeen+1, 
        // so left = 2 , right = 5, count = 5-2+1 = 4 and a = 4 , b = 5 , c = 2 , d = 3
    // when mapping index 6 to char c, we need to move the left slider to lastSeen+1, 
        // so left = 3 , right = 6, count = 6-3+1 = 4 and a = 4 , b = 5 , c = 6 , d = 3
    // when mapping index 7 to char d, we need to move the left slider to lastSeen+1, 
        // so left = 4 , right = 7, count = 7-4+1 = 4 and a = 4 , b = 5 , c = 6 , d = 7
    // finally, we have the answer 4


    public static void main(String[] args) {
        System.out.println(countNoRepeatSubstring("abcd"));     // 4
        System.out.println(countNoRepeatSubstring("abcdabcd")); // 4
        System.out.println(countNoRepeatSubstring("aabccbb"));  // 3
        System.out.println(countNoRepeatSubstring("abbbb"));    // 2
    }
}

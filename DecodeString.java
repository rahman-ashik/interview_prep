import java.util.*;

public class DecodeString {
    public String decodeString(String s) {

        // two stacks : one for string, one for count
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();


        int k = 0; // variable for taking count
        for (char ch : s.toCharArray()) {

            // if char is digit, push to count stack
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } 
            // if char is a '[' , 
            else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
            System.out.println("stringStack: " + stringStack + " countStack: " + countStack + " currentString: " + currentString);
        }
        return currentString.toString();
    }

    public static void main(String[] args) {
        System.err.println("\n============ 3[a]2[bc] ===============");
        DecodeString decodeString = new DecodeString();
        String s = "3[a]2[bc]";
        String result = decodeString.decodeString(s);
        System.out.println(result);
        System.err.println("\n============ 3[a2[c]] ===============");
        s = "3[a2[c]]";
        result = decodeString.decodeString(s);
        System.out.println(result);
        System.err.println("\n============ 2[abc]3[cd]ef ===============");
        s = "2[abc]3[cd]ef";
        result = decodeString.decodeString(s);
        System.out.println(result);
    }
}

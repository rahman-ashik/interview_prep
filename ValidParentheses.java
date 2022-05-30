import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }



    public static void main(String[] args) {
        ValidParentheses f = new ValidParentheses();
        String s = "()";
        System.out.println(f.isValid(s));
        s = "()[]{}";
        System.out.println(f.isValid(s));
        s = "(]";
        System.out.println(f.isValid(s));
        s = "([)]";
        System.out.println(f.isValid(s));
        s = "{[]}";
        System.out.println(f.isValid(s));
        
    }
}

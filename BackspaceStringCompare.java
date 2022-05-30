import java.util.Stack;

public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        return clean(s).compareTo(clean(t)) == 0;
    }

    private String clean(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }






    public static void main(String[] args) {
        BackspaceStringCompare bsc = new BackspaceStringCompare();
        System.out.println(bsc.backspaceCompare("ab#c", "ad#c"));
        bsc = new BackspaceStringCompare();
        System.out.println(bsc.backspaceCompare("ab##", "c#d#"));
        bsc = new BackspaceStringCompare();
        System.out.println(bsc.backspaceCompare("a##c", "#a#c"));
        bsc = new BackspaceStringCompare();
        System.out.println(bsc.backspaceCompare("a#c", "b"));
    }
}

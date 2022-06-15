import java.util.*;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) 
    {
        List<String> result = new LinkedList<String>();
        if (n > 0) {
            // System.out.println("if (n > 0), n: " + n + "							 result: " + result);
            generateParenthesisCore("", n, n, result);
        }
        return result;
    }

    private void generateParenthesisCore(String prefix, int left, int right, List<String> result)
    {
        
        if (left == 0 && right == 0) {
            // System.out.println("if (left == 0 && right == 0), 				 prefix: " + prefix + "	 result: " + result);
            result.add(prefix);
        }
        // Has left Parenthesis    
        if (left > 0) {
            // System.out.println("if (left > 0), 		 left: " + left + "	 right: " + right + "	 prefix: " + prefix + "	 result: " + result);
            generateParenthesisCore(prefix+'(', left-1, right, result);
        }
        // has more right Parenthesis
        if (left < right) {
           //  System.out.println("if (left < right), 	 left: " + left + "	 right: " + right + "	 prefix: " + prefix + "	 result: " + result);
            generateParenthesisCore(prefix+')', left, right-1, result);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        List<String> result = gp.generateParenthesis(2);
        System.out.println(result);
    }
}

import java.util.LinkedList;
import java.util.List;

public class LetterCombination {
    public List<String> letterCombinations(String digits) {
		List<String> ans = new LinkedList<String>();
		if (digits.isEmpty())
			return ans;
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add(0, "");
		for (int i = 0; i < digits.length(); i++) {
			int x = digits.charAt(i) - '0';
			int size = ans.size();     // number of nodes/strings already in the queue
			for (int k = 1; k <= size; k++) {
				String t = ans.remove(0);
				for (char s : mapping[x].toCharArray())
					ans.add(t + s);
			}
		}
		return ans;
	}

    public static void main(String[] args) {
        LetterCombination lc = new LetterCombination();
        List<String> s = lc.letterCombinations("23");
        System.out.println(s);
        s = lc.letterCombinations("");
        System.out.println(s);
        s = lc.letterCombinations("2");
        System.out.println(s);
        s = lc.letterCombinations("79");
        System.out.println(s);

    }
}

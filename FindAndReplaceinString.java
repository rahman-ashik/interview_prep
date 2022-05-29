import java.util.*;

public class FindAndReplaceinString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();

        // map both the source and target against the indexes
        Map<Integer, String> sourceMap = new HashMap<>();
        Map<Integer, String> targetMap = new HashMap<>();
        
        for (int i = 0; i < indexes.length; i++) {
            sourceMap.put(indexes[i], sources[i]);
            targetMap.put(indexes[i], targets[i]);
        }
        
        
        int i = 0;
        while (i < S.length()) {
            if (sourceMap.containsKey(i) && S.startsWith(sourceMap.get(i), i)) {
                    sb.append(targetMap.get(i));
                    i = i + sourceMap.get(i).length();             
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }
        
        return sb.toString();
    }




    public static void main(String[] args) {
        FindAndReplaceinString f = new FindAndReplaceinString();
        String s = "abcd";
        int[] indices = {0,2};
        String[] sources = {"a","cd"};
        String[] targets = {"eee","ffff"};
        System.out.println(f.findReplaceString(s, indices, sources, targets));
    }
}

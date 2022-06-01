import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        // COUNT NUMBER OF WORDS TRANSFORMED
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // FOR ALL WORDS THIS ROUND
            for (int i = 0; i < size; i++) {
                char[] current = queue.poll().toCharArray();
                // TRAVERSE CURRENT WORD
                for (int j = 0; j < current.length; j++) {
                    char tmp = current[j];
                    // CHANGE ONE LETTER AT A TIME 
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);
                        // WHEN NEXT WORD IS IN THE SET
                        if (set.contains(next)) {
                            if (next.equals(endWord)) return count + 1;
                            queue.add(next);
                            set.remove(next);
                        } else {
                            // WHEN NEXT WORD IS NOT IN THE SET
                            System.out.println("Current word: " + next);
                        }
                    }
                    // HAVE TO UNDO FOR NEXT CHANGE OF LETTER
                    current[j] = tmp;
                }
            }
            // THIS ROUND ENDS WITH ONE LETTER CHANGED
            count++;
        }
        return 0;
    }

    int getScore(String word, String endWord) {
        int score = 0;
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == endWord.charAt(i)) {
                score++;
            }
        }
        return score;
    }


    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        // System.out.println(wordLadder.ladderLength("hit", "cog", 
        // Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(wordLadder.ladderLength("be", "ko",
        Arrays.asList("ce", "mo", "ko", "me", "co")));
    }
}

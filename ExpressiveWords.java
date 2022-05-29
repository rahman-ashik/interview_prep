import java.util.Map;

public class ExpressiveWords {
    public int expressiveWords(String s, String[] words) {
        int cnt = 0;
        for(String w: words) {
            if(stretchy(s,w)) cnt++;
        }
        return cnt;
    }
    
	//O(n), n is the length of s.
    boolean stretchy(String s, String w) {
        //two pointers
        int p1 = 0, p2 = 0, n1 = s.length(), n2 = w.length();
        while(p1<n1 && p2<n2) {
            if(s.charAt(p1)!=w.charAt(p2))
                return false;
            
            int s_count = countRepeatedLetters(s,p1);
            int w_count = countRepeatedLetters(w,p2);
            if ((s_count<3 && s_count!=w_count)|| (s_count>=3 && s_count<w_count)) return false;
            p1+= s_count; 
            p2+= w_count;
        }
        return (p1==n1 && p2 ==n2);
    }
    
    int countRepeatedLetters(String s, int i) {
        int cnt =0, n = s.length();
        char c_initial = s.charAt(i);
        while(i<n && c_initial== s.charAt(i)) {
            i++;
            cnt++;   
        }
        return cnt;
    }




    public static void main(String[] args) {
        ExpressiveWords e = new ExpressiveWords();
        String s = "heeellooo";
        String[] words = {"hello", "hi", "helo"};
        System.out.println(e.expressiveWords(s, words));
        
    }
}

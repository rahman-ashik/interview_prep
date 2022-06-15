public class LongestPalindrome {
    /*              *********** Approach - 1 *************
     *      using Dynamic Programming
     *     We can take an 2D boolean array, where we will be storing from position -> to position whether the string is palindrome or not
     *     Both row and column wise we will be keeping the same String like below. But Column wise we want to denote it as END position
     *     and Row wise Start position
     *
     *     Any position will denote the (row, col) starting from position (row number) till position (col number)
     *     Like position (1,3) denotes from position 1 to position 3 whether the string is palindrome or not
     *
     *     So we won't be considering the position below the diagonal line, because for those position
     *     row number > col number meaning start is > end meaning those are not valid position.
     *
     *    Case-1 : For Gap-0, i.e. position [(0,0), (1,1), (2,2)] values are always TRUE.
     *             Because start and end position is same. So, it consists of one character only.
     *
     *    Case-2 : For Gap-1, i.e. position (0,1), (1,2), (2,3) & (3,4) Compare the Start position and End position.
     *             Because there are only two characters are there.
     *
     *    Case-3 : For all rest of the position check the start and end position if true check for middle part of the string.
     *             like for position (1,3) check whether the start and end is same or not, meaning character at position 1
     *             and position 3 is same or not.
     *             If same, check the middle part of the string i.e. check for (start+1) till (End -1) position.
     *             in this case we have to check whether the middle part of the String i.e. (2,2) is
     *             palindrome or not.
     *
     *             To check that middle portions result we can check the left diagonal value i.e. for (i,j) check for (i+1 , j -1)
     *             i.e. for pos (1,3) check the position (2,2)
     *
     *
     *                          END --->
     *               0        1       2         3         4
     *               b        a       b         a         d
     *     -------------------------------------------------------
     *       0  b   T(b)   F(ab)   T(bab)   F(baba)    F(babad)
     *
     *   S   1  a          T(a)     F(ab)   T(aba)     F(abad)
     *   T
     *   A   2  b                   T(b)    F(ba)      F(bad)
     *   R
     *   T   3  a                           T(a)       F(ad)
     *
     *       4  d                                      T(d)
     *
     *
     */
    public String longestPalindrome(String str) {

        boolean[][] dp = new boolean[str.length()][str.length()];
        int start = 0, end = 0;

        /**
         * The loop should be Diagonal wise Starting with gap = 0 till string length
         * First loop it should cover [(0,0), (1,1), (2,2), (3,3), (4,4)]
         * Second Loop - (0,1), (1,2), (2,3) & (3,4)
         *
         * Like this diagonally it should travel and solve the problem.
         */
        for (int gap = 0; gap < str.length(); gap++) {
            for (int i = 0, j = gap; j < dp[0].length; i++, j++) {

                /**
                 *    Case-1 : For Gap-0, i.e. position [(0,0), (1,1), (2,2)] values are always TRUE.
                 *             Because start and end position is same. So, it consists of one character only.
                 */
                if (gap == 0) {
                    dp[i][j] = true;
                    continue;
                }
                /**
                 *    Case-2 : For Gap-1, i.e. position (0,1), (1,2), (2,3) & (3,4) Compare the Start position and End position.
                 *             Because there are only two characters are there.
                 */
                if (gap == 1 && str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = true;
                }
                /**
                 *  Case-3 : For all rest of the position check the start and end position
                 *           If true check for middle part of the string i.e. check for (start+1) till (End -1) position.
                 *           Which can be obtained from left diagonal value i.e. for (i,j) check for (i+1 , j -1)
                 */
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] == true) {
                    dp[i][j] = true;
                }

                /**
                 * Keeping track of the Start end End position of the Palindromic String.
                 * As Gap is increasing we are not doing any comparison with the previous range.
                 * With increasing of the Gap value if the substring is palindrome then it will be
                 * the bigger substring.
                 */
                if (dp[i][j]) {
                    start = i;
                    end = j;
                }
            }
        }

        // print the dp array
        // debug start
        System.out.print("   ");
        for (int i = 0; i < dp.length; i++) System.out.print(" " + str.charAt(i) + " ");
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            System.out.print(" " + str.charAt(i) + " ");
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]? " T " : " F ");
            }
            System.out.println();
        }
        // debug end


        return str.substring(start, end + 1);
    }

    // approach 2: expand around center
    public String longestPalindrome2(String s) {
        if (s.length() == 0) {
            return "";
        }

        char[] sChars = s.toCharArray();

        int start = 0;
        int end = 0;

        for (int i = 0; i < sChars.length; i++) {
            int len = Math.max(expand(sChars, i, i), expand(sChars, i, i + 1));

            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    public int expand(char[] sChars, int i, int j) {
        while (i >= 0 && j < sChars.length && sChars[i] == sChars[j]) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    public String longestCommonSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // dp table show
        // debug start
        System.out.print("  ");
        for (int i = 0; i < dp[0].length-1; i++) System.out.print(" " + s2.charAt(i) );
        System.out.println();
        for (int i = 0; i < dp.length-1; i++) {
            System.out.print(" " + s1.charAt(i) + " ");
            for (int j = 0; j < dp[0].length-1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        // debug end

        int start = 0, end = 0;
        for (int i = 0; i < s1.length(); ++i)
            for (int j = 0; j < s2.length(); ++j) {

                if (s1.charAt(i) == s2.charAt(j)) 
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                else 
                    dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);

                if (dp[i + 1][j + 1] > end - start + 1) {
                    start = i;
                    end = j;
                }

            }


         // dp table show
        // debug start
        System.out.println(" After DP ");
        System.out.print("  ");
        for (int i = 0; i < dp[0].length-1; i++) System.out.print(" " + s2.charAt(i) );
        System.out.println();
        for (int i = 0; i < dp.length-1; i++) {
            System.out.print(" " + s1.charAt(i) + " ");
            for (int j = 0; j < dp[0].length-1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        // debug end

        

        return s1.substring(start, end + 1);
    }



    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        System.out.println(l.longestPalindrome("babad"));
        System.out.println(l.longestPalindrome2("babad"));
        System.out.println(l.longestCommonSubsequence("babad", "dabab"));
        
    }
}

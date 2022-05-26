import java.util.*;
/* 
Every valid email consists of a local name and a domain name, separated by the '@' sign. 
Besides lowercase letters, the email may contain one or more '.' or '+'.

For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
If you add periods '.' between some characters in the local name part of an email address, 
mail sent there will be forwarded to the same address without dots in the local name. 
Note that this rule does not apply to domain names.

For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
If you add a plus '+' in the local name, everything after the first plus sign will be ignored. 
This allows certain emails to be filtered. Note that this rule does not apply to domain names.

For example, "m.y+name@email.com" will be forwarded to "my@email.com".
It is possible to use both of these rules at the same time.

Given an array of strings emails where we send one email to each emails[i], return the number 
of different addresses that actually receive mails.
*/







class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            // split the email into local and domain
            String[] split = email.split("@");
            String local = split[0];
            String domain = split[1];

            // split the local part into name and extension
            String[] localSplit = local.split("\\+");
            
            // remove the '.' in the name
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < localSplit[0].length(); i++) {
                if (localSplit[0].charAt(i) != '.') {
                    sb.append(localSplit[0].charAt(i));
                }
            }
            sb.append("@");
            sb.append(domain);
            set.add(sb.toString());
        }
      
        System.out.println("SET: "+ set);

        return set.size();
    }
}



class Main {
    
    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        Solution solution = new Solution();
        int result = solution.numUniqueEmails(emails);
        System.out.println(result);
    }
} 
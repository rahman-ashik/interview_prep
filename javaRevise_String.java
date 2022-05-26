public class javaRevise_String {
    public static void main(String[] args) {

        // ==== replaceAll ====
        // to replace all occurrences of a substring in a 
        // string with another substring
        String s = "5F3Z-2e-9-w";
        s= s.replaceAll("-", "").toUpperCase();


        // ==== split ====
        // to split a string into an array of strings
        String s2 = "2-5g-3-J";
        String[] split = s2.split("-");

        // ==== substring ====
        // to extract a substring from a string
        String s3 = "abcdefg";
        String sub = s3.substring(0, 2); // "ab"
        sub = s3.substring(2, 4); // "cd"
        sub = s3.substring(4); // "efg"

    }
}

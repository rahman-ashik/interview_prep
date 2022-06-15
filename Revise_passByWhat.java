public class Revise_passByWhat {

    public static void tryChanging(String s) {
        s = "xyz";
    }
    public static void tryChanging(int n) {
        n = 99;
    }

    private static void tryChanging(NumberWrapper nw) {
        nw.n = 99;
    }

    private static void tryChanging(StringBuilder sb) {
        sb.append("xyz");
    }

    private static void tryChanging(Character c) {
        c = 'z';
    }

    private static void tryChanging(char c) {
        c = 'z';
    }




    public static void main(String[] args) {

        String s = "abcd";
        tryChanging(s);
        System.out.println(s);

        int n = 10;
        tryChanging(n);
        System.out.println(n);

        NumberWrapper nw = new NumberWrapper(10);
        tryChanging(nw);
        System.out.println(nw.n);

        StringBuilder sb = new StringBuilder("abcd");
        tryChanging(sb);
        System.out.println(sb);

        Character c = 'a';
        tryChanging(c);
        System.out.println(c);

        char c2 = 'a';
        tryChanging(c2);
        System.out.println(c2);

    }


}

class NumberWrapper {
    int n;
    public NumberWrapper(int n) {
        this.n = n;
    }
}



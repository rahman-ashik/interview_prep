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

    }


}

class NumberWrapper {
    int n;
    public NumberWrapper(int n) {
        this.n = n;
    }
}



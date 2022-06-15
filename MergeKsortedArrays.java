

public class MergeKsortedArrays {
    


    public static void main(String[] args) {
        // merge two sorted arrays
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10};


        int[] c = merge2SortedArrays(a, b);
        // for (int i : c) {
        //     System.out.print(i + " ");
        // }
        int [][] sortedArrays = {{1, 3, 5, 7, 9}, {2, 4, 6, 8, 10} , {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};
        int [] res = mergeKsortedArrays(sortedArrays);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    private static int[] mergeKsortedArrays(int[][] sortedArrays) {
        if (sortedArrays == null || sortedArrays.length == 0) {
            return null;
        }
        else return devideConq(sortedArrays, 0, sortedArrays.length - 1);
    }

    private static int[] devideConq(int[][] sortedArrays, int i, int j) {
        if (i == j) { return sortedArrays[i]; }
        int mid = (i + j) / 2;
        int[] left = devideConq(sortedArrays, i, mid);
        int[] right = devideConq(sortedArrays, mid + 1, j);
        return merge2SortedArrays(left, right);
    }

    private static int[] merge2SortedArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int a_i = 0;
        int b_i = 0;
        int c_i = 0;
        while (c_i < c.length) {
            if (a_i < a.length && b_i < b.length) {
                if (a[a_i] < b[b_i]) {
                    c[c_i] = a[a_i];
                    a_i++;
                } else {
                    c[c_i] = b[b_i];
                    b_i++;
                }
            } else if (a_i < a.length) {
                c[c_i] = a[a_i];
                a_i++;
            } else if (b_i < b.length) {
                c[c_i] = b[b_i];
                b_i++;
            }
            c_i++;
        }
        return c;
    }
}

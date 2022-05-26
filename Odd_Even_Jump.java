import java.util.Map;
import java.util.TreeMap;

class Solution2 {
    public int oddEvenJumps(int[] A) {
        int n  = A.length, res = 1;
        boolean[] higher = new boolean[n], lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; --i) {
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);
            if (hi != null) higher[i] = lower[(int)hi.getValue()];
            if (lo != null) lower[i] = higher[(int)lo.getValue()];
            if (higher[i]) res++;
            map.put(A[i], i);
        }
        return res;
    }  
    
}


class oddEvenJumps {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] arr = {2,3,1,1,4};
        System.out.println(solution.oddEvenJumps(arr));
        int[] arr2 = {10,13,12,14,15};
        System.out.println(solution.oddEvenJumps(arr2));
    }

}


/*
// ** Intuitively looking at the problem statement, top down DP makes the most sense here.
// ** For every index, we maintain 2 states - whether we can reach the end if we had to make an odd jump from that index
// ** and whether we can reach the end if we had to make an even jump from that index.
// ** We also need to find out the next index we can jump to given odd or even.
// ** We maintain a TreeMap to do that.
// ** Now we start from the last index. This is the base case and we mark both cases true - odd and even.
// ** Then for every index we make an odd jump and check if end is reachable by checking it's even state.
// ** If yes, then it is a good state. We also compute the even state for this index to be used later.
class Solution {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        TreeMap<Integer, List<Integer>> tm = new TreeMap<>();
        boolean dp[][] = new boolean[n][2];
        // Last index is always reachable from last whether it is odd or even jump
        dp[n-1][0] = true;
        dp[n-1][1] = true;
        int good = 1;
        tm.put(arr[n-1], new ArrayList<>());
        tm.get(arr[n-1]).add(n-1);
        
        for (int i = n-2; i >= 0; i--) {
            Map.Entry<Integer, List<Integer>> oddEntry = tm.ceilingEntry(arr[i]);
            if (oddEntry == null) {
                dp[i][1] = false;
            } else {
                List<Integer> oddIndexes = oddEntry.getValue();
                // We pick the last index because we start traversing array from the end 
                // so for same value, smaller index will be last
                int oddIndex = oddIndexes.get(oddIndexes.size() - 1);
                dp[i][1] = dp[oddIndex][0];
                if (dp[i][1]) {
                    good++;
                }
            }
            Map.Entry<Integer, List<Integer>> evenEntry = tm.floorEntry(arr[i]);
            if (evenEntry == null) {
                dp[i][0] = false;
            } else {
                List<Integer> evenIndexes = evenEntry.getValue();
                int evenIndex = evenIndexes.get(evenIndexes.size() - 1);
                dp[i][0] = dp[evenIndex][1];
            }
            
            tm.putIfAbsent(arr[i], new ArrayList<>());
            tm.get(arr[i]).add(i);
        }
        
        return good;
    }
}









*/





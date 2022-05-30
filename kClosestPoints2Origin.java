import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class kClosestPoints2Origin {

    // approach 1: sort with comparator
    // time: O(nlogn) - sort and for loop, The advantage of this solution is it can deal with real-time(online) stream data. It does not have to know the size of the data previously.
    class Point {
        int x;
        int y;
        double distance;
        Point() { x = 0; y = 0; distance = 0; }
        Point(int a, int b) { x = a; y = b; distance = Math.sqrt(a * a + b * b); }

    }
    

    public int[][] kClosest(int[][] points, int k) {
        // edge case
        if (points == null || points.length == 0 || points[0].length == 0 || k <= 0) return new int[0][0];
        
        // convert to Point objects and put them in an ArrayList
        Point[] p = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            p[i] = new Point(points[i][0], points[i][1]);
        }

        // sort by distance
        Arrays.sort(p, (a, b) ->  Double.compare(a.distance, b.distance));

        // return the first k elements
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i][0] = p[i].x;
            res[i][1] = p[i].y;
        }
        return res;
    }

    // approach 2: use QuickSelect
    // time: O(n) - for loop , worst case O(n^2)
    public int[][] kClosest2(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k - 1); // O(n)
        int[][] ans = Arrays.copyOf(points, k);
        return ans;
        //Arrays.sort(ans, (int[] a, int[] b) -> Integer.compare(a[0], b[0])); // O(klogk)
        //return ans;
    }
    
    private void quickSelect(int[][] points, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        
        int pivot = start + (end - start) / 2;
        long pio_dis = distance(points[pivot]);
        int left = start, right = end;
        
        while (left <= right) {
            while (left <= right && compare(points[pivot], points[left], pio_dis) > 0) {
                left++;
            }
            
            while (left <= right && compare(points[pivot], points[right], pio_dis) < 0) {
                right--;
            }
            
            if (left <= right) {
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                left++;
                right--;
            }
        }
        
        if (k <= right) {
            quickSelect(points, start, right, k);
        }
        
        if (k >= left) {
            quickSelect(points, left, end, k);
        }
    }
    
    private int compare(int[] pivot, int[] point, long pio_dis) {
        long dis = distance(point);
        if (dis == pio_dis) {
            return pivot[0] == point[0] ? pivot[1] - point[1] : pivot[0] - point[0];
        } else {
            return pio_dis < dis ? -1 : 1;
        }
    }
    
    private long distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1]; 
    }

    // approach 3: use PriorityQueue
    // time: O(NlogK) 
    public int[][] kClosest3(int[][] points, int K) {
        
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((int a[], int b[]) ->  getDistance(a) - getDistance(b));
        
        for (int[] point: points) {
            heap.add(point);
            if (heap.size() > K)
                heap.poll();
        }
        
        int[][] result = new int[K][2];
        while (K > 0)
            result[--K] = heap.poll();
        
        return result;
         
    }
    
    private int getDistance(int [] point) {
        return point[0] * point[0] + point[1] * point[1];
    }





    public static void main(String[] args) {
        kClosestPoints2Origin k = new kClosestPoints2Origin();
        int[][] points = {{1,3},{-2,2}};
        int n = 1;
        int[][] result = k.kClosest(points, n);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + " " + result[i][1]); // [[-2,2]]
        }
        System.err.println("\n===============================\n");
        points = new int[][]{{3,3},{5,-1},{-2,4}};
        n = 2;
        result = k.kClosest(points, n);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + " " + result[i][1]); // [[3,3],[-2,4]]
        }
    }
}

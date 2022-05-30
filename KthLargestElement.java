import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
            System.out.println("After adding " + nums[i] + " pq is " + pq);
            if (pq.size() > k) {
                System.out.print("Full! :: Removing " + pq.peek() + " pq is ");
                pq.poll(); // remove the smallest element
                System.out.println(pq);
            }
        }

        return pq.poll();
    }


    // approach 2:
    // The basic idea is to use Quick Select algorithm to partition the array with pivot:
    // Put numbers < pivot to pivot's left
    // Put numbers > pivot to pivot's right
    // Then
    //      if indexOfPivot == k, return A[k]
    //      else if indexOfPivot < k, keep checking left part to pivot
    //      else if indexOfPivot > k, keep checking right part to pivot
    // Time complexity = average O(n), and O(n^2) in worst case
    // Discard half each time: n+(n/2)+(n/4)..1 = n + (n-1) = O(2n-1) = O(n), because n/2+n/4+n/8+..1=n-1.
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
            
        shuffle(nums);// shuffle the array to avoid worst case O(n^2)
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }    
    
    public int findKthLargest(int[] nums, int start, int end, int k) {// quick select: kth smallest
        if (start > end) return Integer.MAX_VALUE;

        int pivot = nums[end];
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(nums, left++, i);			
        }
        swap(nums, left, end);// Finally, swap A[end] with A[left]
        
        if (left == k)// Found kth smallest number
            return nums[left];
        else if (left < k)// Check right part
            return findKthLargest(nums, left + 1, end, k);
        else // Check left part
            return findKthLargest(nums, start, left - 1, k);
    } 
    
    void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;				
    }
    private void shuffle(int a[]) {

        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            swap(a, ind, r);
        }
    }


    // approach 3: this is the same as quick select, 
    // but we don't need to swap the pivot
    // Time complexity = average O(n), and O(n^2) in worst case
    

    public int findKthLargest3(int[] nums, int k) {
        return quickSort(nums, 0, nums.length-1, k);
    }
    
    private int quickSort(int a[], int start, int end, int k){
        int l = start;
        int r = end;
        
        // if(l == r){
        //     return a[l];
        // }
        
        int pivot = a[start + (end - start) / 2];
        
        while(l <= r){
            
            while(l <= r && a[l] > pivot){
                l++;
            }
            
            while(l <= r && a[r] < pivot){
                r--;
            }
            
            if(l <= r){
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
                l++;
                r--;
            }
        }
        
        if(k-1 <= r){
            quickSort(a, start, r, k);
        }
        
        if(k-1 >= l){
            quickSort(a, l, end, k);
        }
        
        return a[k-1];
    }





    public static void main(String[] args) {
        KthLargestElement f = new KthLargestElement();
        int[] height = {3,2,1,5,6,4};
        System.out.println(f.findKthLargest(height, 2)); // 5
        height = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(f.findKthLargest(height, 4));   // 4
        height = new int[]{3,2,1,5,6,4};
        System.out.println(f.findKthLargest2(height, 2)); // 5
        height = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(f.findKthLargest2(height, 4));   // 4
    }
}

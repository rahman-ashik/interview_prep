import java.util.PriorityQueue;
import java.util.Random;

import javax.lang.model.element.ModuleElement.UsesDirective;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    void print() {
        System.out.print(val);
        if (next != null) {
            next.print();
        }
    }
 }


public class MergeSortedListNodes {



    /*
    Approach: Compare every k nodes (head of every list) and get the smallest node
    Time: O(kN) where k is the number of linked lists. 311 ms
    Space: O(N) creating a new linked list. 
    Or O(1) if we apply an in-place method to
    Connect selected nodes instead of creating new nodes.
    */
    private ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (true) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    minIdx = i;
                }
            }
            if (minIdx == -1) {
                break;
            }
            cur.next = lists[minIdx];
            lists[minIdx] = lists[minIdx].next;
            cur = cur.next;
        }
        return dummy.next;




    }

    // Compare One-By-One (PQ)
    /*
    Time: O(N\log{k}) 34 ms
    Initializing the priority queue takes O(k\log{k})
    Pop N nodes from the priority queue takes O(N\log{k})
    Space: O(k) since priority queue stores k nodes.
    O(1) or O(N) depends on the input N and k and whether we create a new linked list
    */
    public ListNode mergeKListsPQ(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
        return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
    
        PriorityQueue<ListNode> minPQ = new PriorityQueue<>((o1, o2) -> {return o1.val - o2.val;});
    
        // Init PQ
        for (int i = 0; i < lists.length; ++i) {
            if (lists[i] != null) {
                minPQ.offer(lists[i]);
            }
        }
    
        // Play with PQ
        while (minPQ.size() > 0) {
            ListNode curr = minPQ.poll();
            prev.next = curr;
            prev = prev.next; // update
            // you don't need to set curr.next as null since the last node is always be one of the last node of each list. Its next must be null.
            if (curr.next != null) {
                    minPQ.offer(curr.next);
            }
        }
        
        return dummy.next;
    }



    // mergeDivideAndConquer - O(kN)
    // Time: O(N\log{k}) 2 ms
    // Space: O(\log{k}) if we use recursion (depth of the recursion tree).
    public ListNode mergeDivideAndConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
        return null;
        }
        return divideAndConquer(lists, 0, lists.length - 1);
    }
    
    private ListNode divideAndConquer(ListNode[] lists, int lo, int hi) {
        if (lo > hi) { // invalid
            return null;
        }
        if (lo == hi) { // size = 1
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2; // left-leaning
        ListNode left = divideAndConquer(lists, lo, mid);
        ListNode right = divideAndConquer(lists, mid + 1, hi);
        return mergeTwoLists(left, right);
    }

    

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }


    // Best Approach - O(N)
    // Approach: Divide and Conquer (Recursion)
    // Time: O(N\log{k})
    // Space: O(\log{k}) if we use recursion (depth of the recursion tree).
    /*
    Time complexity : O(Nlogk) where k is the number of linked lists.

    We can merge two sorted linked list in O(n) time where n is the 
    total number of nodes in two lists.
    Sum up the merge process and we can get: O(Nlogk)
    Space complexity : O(1)

    We can merge two sorted linked lists in O(1)O(1) space




    */
    public ListNode mergeKListsDQ(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        int n = lists.length;
        
        return merge(lists, 0, n - 1);
    }
    
    ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);
        
        ListNode dummy = new ListNode(), curr = dummy;
        
        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                left = left.next;
                curr = curr.next;
            } else {
                curr.next = right;
                right = right.next;
                curr = curr.next;
            }
        }
        
        curr.next = left == null ? right : left;
        return dummy.next;
    }






    public static void main(String[] args) {
        MergeSortedListNodes f = new MergeSortedListNodes();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);
        ListNode l3 = new ListNode(1)   ;
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(5);
        l3.next.next.next = new ListNode(7);
        ListNode[] lists = new ListNode[]{l1, l2, l3};

        System.out.println("Using: Compare every k nodes ");
        long startTime = java.lang.System.nanoTime();
        ListNode res = f.mergeKLists(lists);
        // l1 = {1,3,5}
        // l2 = {2,4,6}
        // l3 = {1,3,5,7}
        long endTime = java.lang.System.nanoTime();

        while (res != null) {
            System.out.print(res.val + ", ");
            res = res.next;
        }
        System.err.println("\nTime: " + (endTime - startTime) / 1000000.0 + " ms");



         l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
         l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);
         l3 = new ListNode(1)   ;
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(5);
        l3.next.next.next = new ListNode(7);
        lists = new ListNode[]{l1, l2, l3};
        startTime = java.lang.System.nanoTime();
        ListNode res1 = f.mergeKListsPQ(lists);
        endTime = java.lang.System.nanoTime();
        System.out.println("Using: PQ");
        while (res1 != null) {
            System.out.print(res1.val + ", ");
            res1 = res1.next;
        }
        System.err.println("\nTime: " + (endTime - startTime) / 1000000.0 + " ms");


        l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
         l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);
         l3 = new ListNode(1)   ;
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(5);
        l3.next.next.next = new ListNode(7);
        lists = new ListNode[]{l1, l2, l3};
        startTime = java.lang.System.nanoTime();
        ListNode res2 = f.mergeDivideAndConquer(lists);
        endTime = java.lang.System.nanoTime();
        System.out.println("Using: Divide and conquer");
        while (res2 != null) {
            System.out.print(res2.val + ", ");
            res2 = res2.next;
        }
        System.err.println("\nTime: " + (endTime - startTime) / 1000000.0 + " ms");










        System.out.println( "\n=========== Comparison with Randomized 1000 ListNodes ================");
        ListNode randNode1 = createRandomList(1000);
        ListNode randNode2 = createRandomList(1000);
        ListNode randNode3 = createRandomList(1000);
        f = new MergeSortedListNodes();
        lists = new ListNode[]{randNode1, randNode2, randNode3};
        startTime = java.lang.System.nanoTime();
        ListNode res3 = f.mergeKLists(lists);
        endTime = java.lang.System.nanoTime();
        System.out.println("Using: Compare every k nodes ");
        // while (res3 != null) {
        //     System.out.print(res3.val + ", ");
        //     res3 = res3.next;
        // }   
        System.err.println("Time: " + (endTime - startTime) / 1000000.0 + " ms");

        System.out.println( "\n===========================");
         randNode1 = createRandomList(1000);
         randNode2 = createRandomList(1000);
         randNode3 = createRandomList(1000);
         lists = new ListNode[]{randNode1, randNode2, randNode3};
        startTime = java.lang.System.nanoTime();
        ListNode res4 = f.mergeKListsPQ(lists);
        endTime = java.lang.System.nanoTime();
        System.out.println("Using: PQ");
        // while (res4 != null) {
        //     System.out.print(res4.val + ", ");
        //     res4 = res4.next;
        // }
        System.err.println("\nTime: " + (endTime - startTime) / 1000000.0 + " ms");
        System.out.println( "\n===========================");
        randNode1 = createRandomList(1000);
        randNode2 = createRandomList(1000);
        randNode3 = createRandomList(1000);
        lists = new ListNode[]{randNode1, randNode2, randNode3};
       startTime = java.lang.System.nanoTime();
        ListNode res5 = f.mergeDivideAndConquer(lists);
        endTime = java.lang.System.nanoTime();
        System.out.println("Using: Divide and conquer");
        // while (res5 != null) {
        //     System.out.print(res5.val + ", ");
        //     res5 = res5.next;
        // }
        System.err.println("\nTime: " + (endTime - startTime) / 1000000.0 + " ms");



    }

    private static void printList(ListNode randNode) {
        while (randNode != null) {
            System.out.print(randNode.val + ", ");
            randNode = randNode.next;
        }
        System.out.println();
    }

    public static ListNode createRandomList (int size) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            cur.next = new ListNode(r.nextInt(10));
            cur = cur.next;
        }
        return dummy.next;
    } 





}

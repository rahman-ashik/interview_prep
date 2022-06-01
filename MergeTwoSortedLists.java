public class MergeTwoSortedLists {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // dummy node
        ListNode dummy = new ListNode();
        ListNode cur = dummy;


        // iterate through both lists
        while (l1 != null && l2 != null) {

            // check if l1 is smaller than l2, if yes, add l1 to the result list
            if (l1.val < l2.val) {
                cur.next = l1; // attach l1 to the result list
                l1 = l1.next; // move l1 to the next node
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        } // if both of them are same length, we will have reached the end of one of them
        // otherwise, we will have reached the end of one of them, and the other one will have more nodes

        // at this point either l1 or l2 has some nodes left
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }


        return dummy.next;
    }

    private void printList(ListNode head) {
        ListNode dummy = head;
        while (dummy != null) {
            System.err.print(dummy.val + " ");
            dummy = dummy.next;
        }
        System.err.println();
    }

    private ListNode createList(int[] is) {
        ListNode head = new ListNode(is[0]);
        ListNode cur = head;
        for (int i = 1; i < is.length; i++) {
            cur.next = new ListNode(is[i]);
            cur = cur.next;
        }
        return head;
    }
        public static void main(String[] args) {
            MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
            ListNode l1 = mergeTwoSortedLists.createList(new int[] {1, 2, 4});
            ListNode l2 = mergeTwoSortedLists.createList(new int[] {1, 3, 4});


            ListNode result = mergeTwoSortedLists.mergeTwoLists(l1, l2);
            System.out.print("\nresult = ");
            mergeTwoSortedLists.printList(result);
        }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class NodeRemoval {
    

    public static void main(String[] args) {
        NodeRemoval nr = new NodeRemoval();
        ListNode head = nr.createList(new int[]{1,2,3,4,5,6,7,8,9});
        System.err.println("Original list:");
        nr.printList(head);
        head = nr.removeNthFromEnd(head, 2);
        System.err.println("After removing 2nd node from end:");
        nr.printList(head);
        head = nr.removeFromIndex(head, 1);
        System.err.println("After removing 1st index node:");
        nr.printList(head);
        head = nr.removeFromIndex(head, 3);
        System.err.println("After removing 3rd index node:");
        nr.printList(head);
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


    public ListNode removeFromIndex (ListNode head, int n) {

        // early return
        if (head == null) { return null; }


        // dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // two pointers : prev and cur
        ListNode prev = dummy;
        ListNode cur = head;
        // go until nth node, mimicking the nodes before nth node
        for (int i = 0; i < n; i++) {
            prev = cur;
            cur = cur.next;
        }
        // on nth node, skip the nth node
        prev.next = cur.next;
        return dummy.next;

    }



    public ListNode removeNthFromEnd(ListNode head, int n) {
        // early return
        if (head == null) { return null; }

        // dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // slow and fast pointers
        ListNode fast = dummy;
        ListNode slow = dummy;

        // move fast n nodes ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // move fast and slow pointers  until fast reaches the end
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // remove slow.next by skipping it
        slow.next = slow.next.next;
        return dummy.next;
    }


    
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

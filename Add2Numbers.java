public class Add2Numbers {

    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if ( l1 == null && l2 == null )  return null;

        // System.out.print("l1 = ");
        // ListNode l1Ref = l1;
        // while (l1Ref != null) {
        //     System.out.print(l1Ref.val + " ");
        //     l1Ref = l1Ref.next;
        // }
        // System.out.print("\nl2 = ");
        // ListNode l2Ref = l2;
        // while (l2Ref != null) {
        //     System.out.print(l2Ref.val + " ");
        //     l2Ref = l2Ref.next;
        // }


        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
 

    public static void main(String[] args) {
        Add2Numbers add2Numbers = new Add2Numbers();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(6);

        ListNode result = add2Numbers.addTwoNumbers(l1, l2);
        System.out.print("\nresult = ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

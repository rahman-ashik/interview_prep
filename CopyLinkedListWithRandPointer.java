import java.util.HashMap;
import java.util.Map;

public class CopyLinkedListWithRandPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
  
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        // loop 1. copy all the nodes while mapping original nodes to new nodes
        RandomListNode node = head;
        while (node != null) {
          map.put(node, new RandomListNode(node.val));
          node = node.next;
        }
        
        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
          map.get(node).next = map.get(node.next);
          map.get(node).random = map.get(node.random);
          node = node.next;
        }
        
        return map.get(head);
    }
    
    
    
    
    
    private void printList(RandomListNode head) {
        RandomListNode dummy = head;
        while (dummy != null) {
            System.err.print(dummy.val + " ");
            dummy = dummy.next;
        }
        System.err.println();
    }

    public static void main(String[] args) {
        CopyLinkedListWithRandPointer copyLinkedListWithRandPointer = new CopyLinkedListWithRandPointer();
    
        
    }
}

class RandomListNode {
    int val;
    RandomListNode next;
    RandomListNode random;
    RandomListNode() {}
    RandomListNode(int val) { this.val = val; }
    RandomListNode(int val, RandomListNode next) { this.val = val; this.next = next; }
    RandomListNode(int val, RandomListNode next, RandomListNode random) { this.val = val; this.next = next; this.random = random; }
}



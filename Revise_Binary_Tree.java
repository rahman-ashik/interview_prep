import java.util.*;

public class Revise_Binary_Tree {
    public void printTest( TreeNode root) {
        if(root==null)
            return;
        System.out.println(root.val);
        printTest(root.left);
        printTest(root.right);
    }
    public void printLevelOrder(TreeNode root)
    {
        // Base Case
        if(root == null)
            return;
         
        // Create an empty queue for level order traversal
        Queue<TreeNode> q =new LinkedList<TreeNode>();
        Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
         
        // Enqueue Root and initialize height
        q.add(root);
         
        int level = 0;

         
        while(true){
            // nodeCount (queue size) indicates number of nodes at current level.
            int nodeCount = q.size();
            if(nodeCount == 0) break;
             
            System.err.print("Level " + level + ": ");

            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while(nodeCount > 0)
            {
                TreeNode node = q.poll();
                map.putIfAbsent(level, new ArrayList<Integer>());
                map.get(level).add(node.val);
                System.out.print(node.val + " ");

                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);

                nodeCount--;
            }

            System.out.println();
            level++;
        }

        System.err.println("map: " + map);
        
    }
    
    public void verticalOrder(TreeNode root) {
        // final list which will be returned  
        // ArrayList < Integer > res = new ArrayList < > ();
        if (root == null) return;
    
        Queue < TreeNode > q = new LinkedList < > ();
        q.offer(root);
        //map to track nodes for each HD
        Map < Integer, List < Integer >> mp = new HashMap < > ();
        //index of each nodes stored in q(queues)
        Queue < Integer > indices = new LinkedList < > ();
        indices.offer(0);
        int minIndex = 0, maxIndex = 0;
    
        while (!q.isEmpty()) {

          int nodeCount = q.size();
          
          while (nodeCount > 0) {
            TreeNode node = q.poll();
            int index = indices.poll();
    
            mp.putIfAbsent(index, new ArrayList < > ());
            mp.get(index).add(node.val);
    
            if (node.left != null) {
              q.offer(node.left);
              indices.offer(index - 1);
              minIndex = Math.min(minIndex, index - 1);
            }
    
            if (node.right != null) {
              q.offer(node.right);
              indices.offer(index + 1);
              maxIndex = Math.max(maxIndex, index + 1);
            }
            nodeCount--;
          }
    
        }
    
        for (int i = minIndex; i <= maxIndex; i++) {
          // res.addAll(mp.get(i));
          System.out.println(mp.get(i));
        }

        return;
      }

    



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(5);
        left.left = left1;
        left.right = right1;
        TreeNode left2 = new TreeNode(6);
        TreeNode right2 = new TreeNode(7);
        right.left = left2;
        right.right = right2;
        root.print();
        Revise_Binary_Tree revise_Binary_Tree = new Revise_Binary_Tree();
        System.err.println("\n============ printTest ===============");
        revise_Binary_Tree.printTest(root);
        System.err.println("\n============ levelOrder ===============");
        revise_Binary_Tree.printLevelOrder(root);
        System.err.println("\n============ verticalOrder ===============");
        revise_Binary_Tree.verticalOrder(root);
    }
}
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CompleteTreeNodes {
    public int countNodes2(TreeNode root) {
        if(root == null) { return 0;}
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Set <TreeNode> visited = new HashSet<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            //System.err.println("stack: " + stack + " visited: " + visited);
            TreeNode node = stack.pop();
            if(visited.contains(node)) {
                continue;
            } else {
                visited.add(node);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        return visited.size();
    }



    // fast:
    // We are taking help of Full Binary Tree
    // For calculating the height of the full binary tree, the formula is [pow(2,height)-1]

    // First count height of left and right side of a tree and check ->
    // if they both have the same value that mean this is Full Binary Tree then we can calulate total number nodes by formula
    // 2((int)Math.pow(2,value)-1)+1* (for full left tree and full right tree and plus 1 for the root ) and return it!!
    // else recurse it again for its left and right nodes untill it ends!!!

    // Time Complexity
    // O(log(n)log(m))
    // log(n) for calculating the height and log(m) for how many times we are calculating the height since it may reach to leaf nodes in worst case!!!
    public int countNodes(TreeNode root) {
        
        if(root==null)
            return 0;
        int lef=0,rig=0;
        TreeNode tmp=root;
        while(tmp.left!=null) {
		
            tmp=tmp.left;
            lef++;
        }
        tmp=root;
        while(tmp.right!=null){
		
            tmp=tmp.right;
            rig++;
        }
        if(lef==rig)
            return 2*((int)Math.pow(2,lef)-1)+1;
        return countNodes(root.left)+countNodes(root.right)+1;
    }

    public static void main(String[] args) {
        CompleteTreeNodes completeTreeNodes = new CompleteTreeNodes();
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
        right.left = left2;


        root.print();
        System.out.println("complete = " + completeTreeNodes.countNodes(root));

        System.err.println("\n===========================");

        // an array of 4096 nodes
        int[] arr = new int[4096];

        for (int i = 0; i < 4096; i++) {
            arr[i] = i;
        }


        TreeNode root2 = createTree(arr);
        System.out.println("complete = " + completeTreeNodes.countNodes(root2));


        // System.out.println(completeTreeNodes.countNodes(root));
    }

    public static TreeNode createTree (int[] preorder) {
        if(preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        int i = 1;
        while(i < preorder.length) {
            TreeNode node = stack.peek();
            if(node.left == null) {
                node.left = new TreeNode(preorder[i]);
                stack.push(node.left);
            } else {
                node.right = new TreeNode(preorder[i]);
                stack.pop();
            }
            i++;
        }
        return root;
    }
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

        public void print() {
        print("", this, false);
        }

        public void print(String prefix, TreeNode n, boolean isLeft) {
            if (n != null) {
                System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.val);
                print(prefix + (isLeft ? "|   " : "    "), n.left, true);
                print(prefix + (isLeft ? "|   " : "    "), n.right, false);
            }
        }

        @Override
        public String toString() {
            return val + "";
        }
}


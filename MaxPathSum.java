import java.util.Iterator;

public class MaxPathSum {
    int result = Integer.MIN_VALUE;
    
    public int maxSum(TreeNode root) {
        if( root == null )
            return 0;
        int leftMax = Math.max(0, maxSum(root.left));
        int rightMax = Math.max(0, maxSum(root.right));
        result = Math.max(result, root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }

    
    public int maxPathSum(TreeNode root) {
        if( root == null )
            return 0;
        else {
            maxSum(root);
            return result;
        }
    }









    public static void main(String[] args) {
        MaxPathSum maxPathSum = new MaxPathSum();
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);


        root.print();
        System.out.println(maxPathSum.maxPathSum(root));
        
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
  }


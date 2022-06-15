import java.util.*;

public class DiameterBinaryTree {
    // recursive solution
    int max = 0;
    public int getDiameter (TreeNode root) {
        if(root == null) { return 0; }
        getDepth(root);
        return max;
    }

    public int getDepth (TreeNode root) {
        if (root == null) return 0;
        
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        
        max = Math.max(max, left + right);
        
        return Math.max(left, right) + 1;
   
    }

    // iterative solution
    public int getDiameter_Iterative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Map<TreeNode, Integer> map = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int diameter = 0;

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node.left != null && !map.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !map.containsKey(node.right)) {
                stack.push(node.right);
            } else {
                stack.pop();
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);
                map.put(node, 1 + Math.max(leftDepth, rightDepth));
                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }
        return diameter;
    }





    public static void main(String[] args) {
        DiameterBinaryTree diameterBinaryTree = new DiameterBinaryTree();
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

        System.out.println(diameterBinaryTree.getDiameter(root));
        
    }
}

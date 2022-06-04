import java.util.*;

public class StepByStepPath {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder();
        StringBuilder d = new StringBuilder();
        
        findPath(root, startValue, s); // find reversed path of startValue
        findPath(root, destValue, d); // find reversed path of destValue
        
        int i = 0;
        int min_i = Math.min(s.length(), d.length());
        
        // to find the Least Common Ancestor(LCA) we need to cpmpare the strings from backwards
        while (i < min_i && s.charAt(s.length() - i - 1) == d.charAt(d.length() - i - 1)) {
            i++;
        }
        
        // for remaining of the 's' (excluding LCA) replace by 'U' because of upward movement
        // the 'd' has to be reversed in order to go donwards from LCA to destValue
        return "U".repeat(s.length() - i) + d.reverse().toString().substring(i);
    }
    
    // find path of startValue and destValue from the root
    // These paths are from down towards root, so actua;l path from root can be gettable by reversing the sb
    private boolean findPath(TreeNode root, int val, StringBuilder sb) {
        if(root.val == val) {
            return true;
        }
        
        if(root.left != null && findPath(root.left, val, sb)) {
            sb.append("L");
        }
        
        else if(root.right != null && findPath(root.right, val, sb)) {
            sb.append("R");
        }
           
        return sb.length() > 0;
    }

    public static TreeNode preOrderToTree(int[] preorder) {
        if (preorder == null || preorder.length == 0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (i < preorder.length) {
                node.left = new TreeNode(preorder[i++]);
                queue.offer(node.left);
            }
            if (i < preorder.length) {
                node.right = new TreeNode(preorder[i++]);
                queue.offer(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        StepByStepPath s = new StepByStepPath();
        int [] a = {1,2,3,4,5,6,7,8};
        TreeNode root1 = preOrderToTree(a);
        root1.print();
        System.out.println(s.getDirections(root1, 1, 8));
        System.out.println(s.getDirections(root1, 1, 9));

        int [] b = {1,3,2,4,5,7,6,8};
        TreeNode root2 = preOrderToTree(b);
        root2.print();
        System.out.println(s.getDirections(root2, 2, 8));

        int [] c = {1,2,3,4,5,6,7,8,9};
        TreeNode root3 = preOrderToTree(c );
        root3.print();
        System.out.println(s.getDirections(root3, 7, 8));
    }
}

import java.util.*;

public class FlipEquivalentBinaryTrees {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stk1 = new Stack<>(), 
                        stk2 = new Stack<>();
        stk1.push(root1);
        stk2.push(root2);
        while (!stk1.isEmpty() && !stk2.isEmpty()) {
            TreeNode n1 = stk1.pop(), n2 = stk2.pop();
            if (n1 == null && n2 == null) {
                continue;
            }else if (n1 == null || n2 == null || n1.val != n2.val) {
                return false;
            }

            if (n1.left == n2.left || n1.left != null && n2.left != null && n1.left.val == n2.left.val) {
                stk1.addAll(Arrays.asList(n1.left, n1.right));
            }else {
                stk1.addAll(Arrays.asList(n1.right, n1.left));
            }
            stk2.addAll(Arrays.asList(n2.left, n2.right));
        }
        return stk1.isEmpty() && stk2.isEmpty();
    }




    public static void main(String[] args) {
        FlipEquivalentBinaryTrees f = new FlipEquivalentBinaryTrees();
        int [] a = {1,2,3,4,5,6,7,8};
        TreeNode root1 = preOrderToTree(a);
        root1.print();
        int [] b = {1,3,2,4,5,7,6,8};
        TreeNode root2 = preOrderToTree(b);
        root2.print();
        int [] c = {1,2,3,4,5,6,7,8,9};
        TreeNode root3 = preOrderToTree(c );
        System.out.println(f.flipEquiv(root1, root2));
        System.out.println(f.flipEquiv(root1, root3));
        System.out.println(f.flipEquiv(root2, root3));
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
}

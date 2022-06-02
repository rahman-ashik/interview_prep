public class DiameterBinaryTree {
    int max = 0;
    public int getDiameter (TreeNode root) {
        if(root == null) { return 0; }
        maxDepth(root);
        return max;
    }

    public int maxDepth (TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        max = Math.max(max, left + right);
        
        return Math.max(left, right) + 1;
   
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

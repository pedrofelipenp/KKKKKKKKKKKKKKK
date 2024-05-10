public class BST {
    public boolean isAVL(TreeNode root) {
        if (root == null)
            return true;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1)
            return false;

        return isAVL(root.left) && isAVL(root.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null)
            return 0;

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

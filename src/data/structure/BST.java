package data.structure;

public class BST {
	private TreeNode root = null;
	
	public static void main(String[] args) {
		BST bst = new BST(0);
		bst.insert(1);
		bst.insert(2);
		bst.insert(3);
		bst.insert(4);
		bst.display();
		System.out.println(bst.delete(6));
		bst.display();
		
		System.out.println(bst.find(2));
		System.out.println(bst.delete(2));
		bst.display();
		
	}

	public BST(int i) {
		root = new TreeNode(i);
		// root can become a reference only if it point to an real object
	}

	// allow duplicates
	public void insert(int i) {
		insertHelper(root, i);
	}

	public boolean find(int i) {
		return (findHelper(root, i) != null);
	}

	public boolean delete(int i) {
		if (!find(i)) return false;
		root = deleteHelper(root, i);
		return true;
	}

	private TreeNode deleteHelper(TreeNode rt, int i) {
		if (i == rt.v) {
			if (rt.left == null) {
				rt = rt.right;
			} else if (rt.right == null) {
				rt = rt.left;
			} else {
				rt = getMin(rt.right);
				removeMin(rt.right);
			}
		} else if (i < rt.v) {
			rt.left = deleteHelper(rt.left, i);
		} else {
			rt.right = deleteHelper(rt.right, i);
		}
		return rt;
	}
	
	public void display() {
		displayHelper(root);
	}

	private void displayHelper(TreeNode rt) {
		if (rt == null) return;
		if (rt.left != null) displayHelper(rt.left);
		System.out.println(rt.v);
		if (rt.right != null) displayHelper(rt.right);
	}
	
	private TreeNode getMin(TreeNode rt) {
		if (rt == null || rt.left == null)
			return rt;
		return getMin(rt.left);
	}

	private TreeNode removeMin(TreeNode rt) {
		if (rt == null)
			return null;
		if (rt.left == null)
			return rt.right;
		else
			return removeMin(rt.left);
	}

	private TreeNode findHelper(TreeNode rt, int i) {
		if (rt == null)
			return null;

		if (i == rt.v)
			return rt;
		else if (i < rt.v)
			return findHelper(rt.left, i);
		else
			return findHelper(rt.right, i);
	}

	private void insertHelper(TreeNode rt, int i) {
		if (rt == null) {
			rt = new TreeNode(i);
		} else if (rt.left == null && i < rt.v) {
			rt.left = new TreeNode(i);
		} else if (rt.right == null && i >= rt.v) {
			rt.right = new TreeNode(i);
		} else {
			TreeNode subtree = (i < rt.v) ? rt.left : rt.right;
			insertHelper(subtree, i);
		}
	}
}
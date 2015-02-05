package data.structure;

public class TreeNode {
	int v;
	TreeNode left, right;
	
	public TreeNode() {
		this(-1);
	}
	
	public TreeNode(int value) {
		v = value;
		left = null;
		right = null;
	}
}

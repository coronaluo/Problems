package data.structure;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {
	public int id;
	public char letter;
	public List<TrieNode> children;

	public TrieNode(char l) {
		id = PrefixTree.nodeCounter++;
		letter = l;
		children = new ArrayList<TrieNode>();
	}

	public boolean noChild() {
		return children.isEmpty();
	}

	public boolean isValidEndNode() {
		return (!children.isEmpty() && children.get(0).letter == '#');
	}
	
	public void addChild(TrieNode c) {
		children.add(c);
	}

	public void setValidEndNode() {
		TrieNode end = new TrieNode('#');
		if (children.isEmpty()) children.add(end);
		else children.add(0, end);
	}
	
	public boolean isEqual(TrieNode n) {
		return (n.id == id);
	}
}

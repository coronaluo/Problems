package data.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixTree {
	public static int nodeCounter = 0;
	TrieNode root;
	HashMap<Key, TrieNode> hm;

	public static void main (String[] args) {
		TrieNode root = new TrieNode('l');
		PrefixTree pt = new PrefixTree(root);
		System.out.println(pt.insert(""));
		System.out.println(pt.insert("abcd"));
		System.out.println(pt.insert("abc"));
		System.out.println(pt.find("ab"));
		System.out.println(pt.find("abc"));
		System.out.println(pt.find("abcd"));
	}
	
	public PrefixTree(TrieNode rt) {
		root = rt; // starting symbol
		hm = new HashMap<Key, TrieNode>();
	}

	public boolean find(String word) {
		return findHelper(root, word, 0);
	}

	private boolean findHelper(TrieNode parent, String word, int i) {
		if (i == word.length())
			return (parent.noChild() || parent.isValidEndNode());

		Key k = new Key(parent, word.charAt(i));
		if (parent.noChild() || !hm.containsKey(k)) return false;
		return findHelper(hm.get(k), word, i + 1);
	}

	public boolean insert(String word) {
		if (word == null || word.length() == 0)
			return false;
		return insertHelper(root, word, 0);
	}

	private boolean insertHelper(TrieNode parent, String word, int i) {
		if (i == word.length()) {
			if (!parent.isValidEndNode()) parent.setValidEndNode();
			return true;
		}

		Key k = new Key(parent, word.charAt(i));
		if (parent.noChild() || !hm.containsKey(k)) {
			TrieNode child = new TrieNode(word.charAt(i));
			parent.addChild(child);
			hm.put(k, child);
			return insertHelper(child, word, i + 1);
		}
		// hm.containsKey(k)
		return insertHelper(hm.get(k), word, i + 1);
	}

	private class Key {
		TrieNode node;
		char childLetter;

		public Key(TrieNode n, char cl) {
			node = n;
			childLetter = cl;
		}

		@Override
		public int hashCode() {
			int hash = node.id;
	        hash = hash * 31 + (int)(childLetter-'a');
	        return hash;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return (((Key)obj).node.isEqual(node) && ((Key)obj).childLetter == childLetter);
		}
	}

}


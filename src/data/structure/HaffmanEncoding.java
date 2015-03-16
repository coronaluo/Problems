package data.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class HaffmanEncoding<T> {
	public static void main(String args[]) {
		HaffmanEncoding<Character> haffman = new HaffmanEncoding<Character> ();
		Character letters[] = new Character[]{'a', 'b', 'c', 'd'};
		int freqs[] = new int[] {100, 10, 20, 30};
		
		Map<Character, String> rst = haffman.encoding(haffman.buildTree(letters, freqs));
		for (Map.Entry<Character, String> entry : rst.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	public class Node implements Comparable<Node> {
		int freq;
		public Node(int f) {
			freq = f; 
		}
		
		@Override
		public int compareTo(Node node) {
			return (freq - node.freq);
		}
	}
	
	public class Leaf extends Node {
		T value;
		public Leaf(int fq, T v) {
			super(fq);
			value = v;
		}
	}
	
	public class InternalNode extends Node {
		Node left, right;
		public InternalNode (int fq, Node l, Node r) {
			super(fq);
			left = l;
			right = r;
		}
	}
	 
	public Node buildTree(T[] values, int[] freqs) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < freqs.length; i++) {
			pq.offer(new Leaf(freqs[i], values[i]));
		}
		
		while (pq.size() > 1) {
			Node l = pq.poll();
			Node r = pq.poll();
			pq.offer(new InternalNode((l.freq + r.freq), l, r));
		}
		
		return pq.poll();
	}
	
	public Map<T, String> encoding(Node root) {
		Map<T, String> rst = new HashMap<T, String>();
		if (root == null) return rst;
		if (root instanceof HaffmanEncoding.Leaf) {
			rst.put(((Leaf) root).value, "0");
			return rst;
		}
		StringBuilder sb = new StringBuilder();
		encodingHelper(root, sb, rst);
		return rst;
	}
	
	private void encodingHelper(Node node, StringBuilder sb, Map<T, String> rst) {
		if (node == null) return ;
		
		if (node instanceof HaffmanEncoding.Leaf) {
			rst.put(((Leaf)node).value, sb.toString());
			return ;
		}
		
		sb.append('0');
		encodingHelper(((InternalNode)node).left, sb, rst);
		sb.deleteCharAt(sb.length()-1);
		
		sb.append('1');
		encodingHelper(((InternalNode)node).right, sb, rst);
		sb.deleteCharAt(sb.length()-1);
	}

}
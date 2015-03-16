package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class CloneGraph {
	public static void main(String[] args) {
		new CloneGraph().testCloneGraph();
	}
	
	public class Vertex {
		int id;
		List<Vertex> neighbours;
		int degree;
		
		public Vertex(int i) {
			id = i;
			neighbours = new ArrayList<Vertex>();
			degree = 0;
		}
		public Vertex(Vertex v) {
			id = v.id;
			neighbours = new ArrayList<Vertex>();
			degree = v.degree;
		}
		public void addNeighbour(Vertex v) {
			neighbours.add(v);
			v.degree++;
		}
	}

	public void testCloneGraph() {
		Vertex a = new Vertex(1);
		Vertex b = new Vertex(2);
		Vertex c = new Vertex(3);
		Vertex d = new Vertex(4);
		Vertex e = new Vertex(5);
		a.addNeighbour(b);
		a.addNeighbour(d);
		a.addNeighbour(e);
		b.addNeighbour(c);
//		 c.addNeighbour(a);
		Vertex copied = clone(a);
		System.out.println(isSameGraph(a, copied));
	}
	
	private boolean isSameGraph(Vertex g1, Vertex g2) {
		List<Integer> t1 = getTopology(g1);
		List<Integer> t2 = getTopology(g2);
		System.out.println("t1" + t1);
		System.out.println("t2" + t2);
		
		if (t1.size() != t2.size()) return false;
		
		for (int i = 0; i < t1.size(); i++) {
			
			if (t1.get(i) != t2.get(i)) return false;
		}
		return true;
	}
	
	private List<Integer> getTopology(Vertex start) {
		// start with degree = 0
		List<Integer> rst = new ArrayList<Integer>();
		Queue<Vertex> que = new LinkedList<Vertex>();
		que.offer(start);
		while(!que.isEmpty()) {
			Vertex cur = que.poll();
			rst.add(cur.id);
			for (Vertex v : cur.neighbours) {
				v.degree--;
				if (v.degree == 0) que.offer(v);
			}
		}
		return rst;
	}
	
	// clone graph
	private Vertex clone(Vertex start) {
		HashMap<Vertex, Vertex> hm = new HashMap<Vertex, Vertex>();
		Queue<Vertex> que = new LinkedList<Vertex>();
		que.offer(start);
		hm.put(start, new Vertex(start));

		while (!que.isEmpty()) {
			Vertex cur = que.poll();
			Vertex copied = hm.get(cur);
			for (Vertex neighb : cur.neighbours) {
				boolean visited = true;
				if (!hm.containsKey(neighb)) {
					visited = false;
					hm.put(neighb, new Vertex(neighb));
				}
				copied.neighbours.add(hm.get(neighb));
				if (visited) continue;
				que.offer(neighb);
			}
		}

		return hm.get(start);
	}
}

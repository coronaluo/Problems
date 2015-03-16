package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindCycle {
	public static void main(String[] args) {
//		new FindCycle().testFindPath();
//		String[][] table = {{"a", "b", "c"},
//				{"1", "2", "3"},
//				{"10", "20", "30"},
//				{"100", "200", "300"},
//				};
		System.out.println(replace("a", 0, 'f'));
	}
	// give you a graph and two vertices and return if there exists a path between them
	private static String replace(String s, int i, char c) {
        String rst = s.substring(0, i);
        rst += c;
        rst += s.substring(i+1);
        return rst;
    }
	
	public class Vertex {
		int id;
		List<Vertex> neighbours;
		boolean visited = false;
		
		public Vertex(int i) {
			id = i;
			neighbours = new ArrayList<Vertex>();
		}
		public void addNeighbour(Vertex v) {
			neighbours.add(v);
		}
	}

	public void testFindPath() {
		Vertex a = new Vertex(1);
		Vertex b = new Vertex(2);
		Vertex c = new Vertex(3);
		Vertex d = new Vertex(4);
		Vertex e = new Vertex(5);
		a.addNeighbour(b);
		a.addNeighbour(d);
		a.addNeighbour(e);
		b.addNeighbour(c);
//		c.addNeighbour(a);
		
		// undirected
		b.addNeighbour(a);
		d.addNeighbour(a);
		e.addNeighbour(a);
		c.addNeighbour(b);
		
		//System.out.println(findPath(c, e));
		//System.out.println(findPath(a, c));
		//System.out.println(findCycleDAG(a));
		System.out.println(findCycle(a, null));
		
	}
	
	
	
	public boolean findPath(Vertex a, Vertex b) {
		if (a == null || b == null) return false;
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.offer(a);

		while (!q.isEmpty()) {
			Vertex cur = q.poll();
			if (cur.id == b.id) return true;
			for (Vertex v : cur.neighbours) {
				q.offer(v);
			}
		}
		return false;
	}

	// determine if there exists a cycle in the given graph
	// for DAG
//	public boolean findCycle(Vertex start) {
//		 HashMap<Vertex, Integer> hm = new HashMap<Vertex, Integer>();
//		 return findCycleHelper(start, hm);
//	}
//
//	public boolean findCycleHelper(Vertex start, HashMap<Vertex, Integer> hm) {
//		if (start == null) return false;
//
//		if (hm.containsKey(start)) return true;
//		hm.put(start, 1);
//		for (Vertex n : start.neighbours) {
//			if (findCycleHelper(n, hm)) return true;
//		}
//		hm.remove(start);
//		return false;
//	}
	
	// for DAG
	public boolean findCycleDAG(Vertex start) {
		if (start == null) return false;
		start.visited = true;
		for (Vertex n : start.neighbours) {
			if (n.visited || findCycleDAG(n)) return true; 
		}
		return false;
	}
	
	// for undirected
	public boolean findCycle(Vertex start, Vertex prev) {
		if (start == null) return false;
		start.visited = true;
		for (Vertex n : start.neighbours) {
			if (n == prev) continue;
			if (n.visited || findCycle(n, start)) return true;
		}
		return false;
	}
	
}

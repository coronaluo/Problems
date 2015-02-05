package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private static int globeDFS = 0;
	private static int globeTEST = 0;
	
	Vertex[] vertices;

	public static void main(String []args) {
		// TEST for undirected graph
//		int[][] adjMatrix = new int[][]{
//								{0,1,0,1,1,1},
//								{1,0,1,0,0,0},
//								{0,1,0,1,0,0},
//								{1,0,1,0,0,0},
//								{1,0,0,0,0,1},
//								{1,0,0,0,1,0}
//		};
		
		// TEST dag for dijkstra
//		int[][] adjMatrix = new int[][]{
//								{0,4,2,0,0,3},
//								{0,0,3,2,3,0},
//								{0,1,0,4,5,0},
//								{0,0,0,0,0,0},
//								{0,0,0,1,0,0},
//								{0,0,0,0,0,0}
//						};
		
		// TEST dag for bellman-ford
		int[][] adjMatrix = new int[][]{
								{0,10,0,0,0,0,0,8},
								{0,0,0,0,0,2,0,0},
								{0,1,0,1,0,0,0,0},
								{0,0,0,0,3,0,0,0},
								{0,0,0,0,0,-1,0,0},
								{0,0,-2,0,0,0,0,0},
								{0,-4,0,0,0,-1,0,0},
								{0,0,0,0,0,0,1,0}
						};
		
		Graph g = new Graph(adjMatrix[0].length, adjMatrix);
//		g.dfs();
//		g.bfs();
//		System.out.println("**********Dijkastra**********");
//		String path[] = g.dijkstra("A");
//		for (int i = 0; i < path.length; i++) {
//			String prevNode = (path[i] == null) ? "-" : path[i]; 
//			System.out.println("node " + (char)('A'+i) + "'s prev node: " + prevNode);
//		}
		
		System.out.println("**********Bellman-Ford**********");
		String path2[] = g.bellmanFord("A");
		for (int i = 0; i < path2.length; i++) {
			String prevNode = (path2[i] == null) ? "-" : path2[i]; 
			System.out.println("node " + (char)('A'+i) + "'s prev node: " + prevNode);
		}
		
		
	}
	
	public Graph(int vsize, int[][] adjMatrix) {
		// start from A
		vertices = new Vertex[vsize];
		for (int i = 0; i < vsize; i++) {
			vertices[i] = new Vertex(String.valueOf((char)('A' + i)));
		}
		
		for (int r = 0; r < vsize; r++) {
			for (int c = vsize-1; c >= 0; c--) {
				if (r != c && adjMatrix[r][c] != 0) {
					vertices[r].addNeighbour(new Neighbour(String.valueOf((char)('A' + c)), adjMatrix[r][c]));
				}
			}
		}
	}
	
	
	
	private String[] bellmanFord(String sname) {
		String[] prev = new String[vertices.length];
		int[] dist = new int[vertices.length];
		int sIdx = getVertexIdx(sname);
		
		// init
		for (int i = 0; i < vertices.length; i++) {
			prev[i] = null;
			dist[i] = (i == sIdx) ? 0 : Integer.MAX_VALUE;
		}
		
		// bellman-ford
		for (int i = 0; i < vertices.length; i++) {
			boolean updated = false;
			
			// traverse all edges
			for (int v = 0; v < vertices.length; v++) {
				
				// in case of overflow
				if (dist[v] == Integer.MAX_VALUE) continue;
				
				Vertex curv = vertices[v];
				Neighbour ns = curv.neighbours;
				while (ns != null) {
					int nIdx = getVertexIdx(ns.name);
					if (dist[nIdx] > dist[v] + ns.weight) {
						updated = true;
						dist[nIdx] = dist[v] + ns.weight;
						prev[nIdx] = curv.name;
					}
					ns = ns.next;
				}
			}
			
			if (!updated) {
				System.out.println("updated at loop " + i);
				break;
			}
		}
		
		for (int i = 0; i < dist.length; i++) {
			System.out.println("node " + (char)('A'+i) + "'s dist = " + dist[i]);
		}
		
		return prev;
	}
	
	
	/**
	 * dijkastra 
	 */
	private String[] dijkstra(String sname) {
		if (vertices == null || vertices.length == 0) return null;
		
		int sidx = getVertexIdx(sname);
		int[] dist = new int[vertices.length];
		String[] prev = new String[vertices.length];
		Heap<String, Integer> pq = new Heap<String, Integer>(vertices.length);
		
		// init & build heap
		for (int i = 0; i < vertices.length; i++) {
			prev[i] = null;
			if (i == sidx) {
				dist[i] = 0;
				pq.insert(new DijkstraElem(sname, 0));
			} else {
				dist[i] = Integer.MAX_VALUE;
				pq.insert(new DijkstraElem(vertices[i].name, Integer.MAX_VALUE));
			}
		}
		
		while (!pq.isEmpty()) {
			HeapElement<String, Integer> cur = pq.removeMin();
			int curDist = cur.value;
			String curVname = cur.key;
			int curIdx = getVertexIdx(curVname);
			
			Neighbour ns = vertices[curIdx].neighbours;
			while (ns != null) {
				int nIdx = getVertexIdx(ns.name);
				if (pq.getIdx(ns.name) != -1 && dist[nIdx] > (curDist + ns.weight)) {
					dist[nIdx] = curDist + ns.weight;
					pq.valueDecreased(ns.name, dist[nIdx]);
					prev[nIdx] = curVname;
				}
				ns = ns.next;
			}
		}
		
		for (int i = 0; i < dist.length; i++) {
			System.out.println("node " + (char)('A'+i) + "'s dist = " + dist[i]);
		}
		
		return prev;
	}
	
	/**
	 * BFS
	 */
	public void bfs() {
		int dist[] = new int[vertices.length];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(vertices[0]);
		dist[0] = 0;
		while (!queue.isEmpty()) {
			Vertex v = queue.remove();
			int currentDist = dist[getVertexIdx(v.name)];
			
			Neighbour ns = v.neighbours;
			while (ns != null) {
				int vidx = getVertexIdx(ns.name);
				if (dist[vidx] == Integer.MAX_VALUE) {
					queue.add(vertices[vidx]);
					dist[vidx] = currentDist + 1;
				}
				ns = ns.next;
			}
		}

		System.out.println("BFS results:");
		for (int i = 0; i < dist.length; i++) {
			System.out.println("vertex " + vertices[i].name + " with dist = "+dist[i]);
		}
	}
	
	/**
	 * DFS
	 */
	public void dfs() {
		int[] preVisit = new int[vertices.length];
		int[] postVisit = new int[vertices.length];
		boolean[] visited = new boolean[vertices.length];
		
		for (int i = 0; i < vertices.length; i++) {
			if (!visited[i]) {
				explore(vertices[i], visited, preVisit, postVisit);
//				System.out.println("End of Connected Component: "+globeTEST++);
			}
		}
		
		for(int i = 0; i < vertices.length; i++) {
			System.out.println(preVisit[i] + "," + postVisit[i]);
		}
	}
	
	private void explore(Vertex v, boolean[] visited, int[] pre, int[] post) {
		visited[getVertexIdx(v.name)] = true;
		previsit(v, pre);
		Neighbour ns = v.neighbours;
		while(ns != null) {
			int idx = getVertexIdx(ns.name);
			if (!visited[idx]) explore(vertices[idx], visited, pre, post);
			ns = ns.next;
		}
		postvisit(v, post);
	}
	
	/**
	 * helpers 
	 */
	private void previsit(Vertex v, int[] pre) {
		pre[getVertexIdx(v.name)] = globeDFS++;
	}
	
	private void postvisit(Vertex v, int[] post) {
		post[getVertexIdx(v.name)] = globeDFS++;
	}

	private int getVertexIdx(String vname) {
		return (int)(vname.charAt(0) - 'A');
	}
	
	/**
	 * private class
	 */
	private class Vertex {
		public String name;
		public Neighbour neighbours;
		
		public Vertex(String n) {
			name = n;
			neighbours = null;
		}
		
		public void addNeighbour(Neighbour nn) {
			if (neighbours == null) {
				neighbours = nn;
				
			} else {
				nn.next = neighbours;
				neighbours = nn;
			}
		}
	}
	
	private class Neighbour {
		public String name;
		public int weight;
		public Neighbour next;
		
		public Neighbour(String n) {
			name = n;
			weight = 1;
			next = null;
		}
		
		public Neighbour(String n, int w) {
			name = n;
			weight = w;
			next = null;
		}
	}
	
	private class DijkstraElem extends HeapElement<String, Integer> {
		
		public DijkstraElem(String name, Integer dist) {
			super(name, dist);
		}
		
		@Override
		public int compareTo(HeapElement<String, Integer> e) {
			return super.compareTo(e);
		}

		@Override
		public boolean isSameKey(String key) {
			return this.key.equals(key);
		}

	}
	
}

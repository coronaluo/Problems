package algorithm.graph;

public class Heap<K, V extends Comparable<V>> {
	private HeapElement<K, V>[] heap;
	private int size;
	
	/**
	 * TEST
	 */
	public static void main(String args[]) {
		HeapElement<String, Integer>[] elems = new HeapElement[6];
		for (int i = 0; i < elems.length; i++) {
			Integer v = (int)(Math.random() * 100);
			System.out.println("generated value = " + v);
			elems[i] = new HeapElement<String, Integer>(Character.toString((char)('A' + i)), v);
		}
		
		Heap<String, Integer> heap = new Heap<String, Integer>(elems);
		while (!heap.isEmpty()) {
			HeapElement<String, Integer> r = heap.removeMin();
			System.out.println(r.key + "-" + r.value);
		}
	}
	
	/**
	 * construct 
	 */
	public Heap(int s) {
		heap = new HeapElement[s+1];
		size = 0;
	}
	
	public Heap(HeapElement<K, V>[] elems) {
		heap = new HeapElement[elems.length+1];
		for (int i = 0; i < elems.length; i++) {
			insert(elems[i]);
		}
	}
	
	/**
	 * primitive ops: removeMin, insert 
	 */
	public HeapElement<K, V> removeMin() {
		if (isEmpty()) throw new RuntimeException("empty heap no more elem to remove");
		HeapElement<K, V> rtn = heap[1];
		heap[1] = heap[size--];
		valueIncrease(1);
		return rtn;
	}
	
	public void insert(HeapElement<K, V> e) {
		heap[(++size)] = e;
		valueDecreased(size);
	}
	
	/**
	 * helpers 
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	// how to decrease it to logN
	public void valueDecreased(K key, V newValue) {
		int idx = getIdx(key);
		if (idx == -1) throw new RuntimeException("Heap: cant find given key:" + key);
		heap[idx].value = newValue;
		valueDecreased(idx);
	}
	
	public int getIdx(K key) {
		for (int i = 1; i <= size; i++) {
			if (heap[i].isSameKey(key)) return i;
		}
		return -1;
	}
	
	// key decrease -> node upward
	private void valueDecreased(int idx) {
		int curidx = idx;
		HeapElement<K, V> e = heap[idx];
		while (curidx/2 >= 1 && e.compareTo(heap[curidx/2]) < 0) {
			heap[curidx] = heap[curidx/2];
			curidx /= 2;
		}
		heap[curidx] = e;
	}
	
	// key increase -> node moves downward
	private void valueIncrease(int idx) {
		int curIdx = idx;
		HeapElement<K, V> e = heap[idx];
		while (2 * curIdx + 1 <= size || 2 * curIdx <= size) {
			int idxOfSmaller = -1;
			if (2 * curIdx + 1 <= size) {
				if (e.compareTo(heap[curIdx*2]) < 0 && e.compareTo(heap[curIdx*2+1]) < 0) break;
				idxOfSmaller = (heap[curIdx*2].compareTo(heap[curIdx * 2 + 1]) < 0) ? curIdx*2 : (curIdx*2+1);
				
			} else {
				if (e.compareTo(heap[curIdx*2]) < 0) break;
				idxOfSmaller = curIdx*2;
				
			}
			heap[curIdx] = heap[idxOfSmaller];
			curIdx = idxOfSmaller;
		}
		heap[curIdx] = e;
	}
	
}

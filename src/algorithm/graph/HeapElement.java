package algorithm.graph;

public class HeapElement<K, V extends Comparable<V>> {

	public K key;
	public V value;
	
	public HeapElement(K name, V dist) {
		this.key = name;
		this.value = dist;
	}
	
	public int compareTo(HeapElement<K, V> e) {
		return value.compareTo(e.value);
	}
	
	/**
	 * to be override
	 */
	public boolean isSameKey(K key) {
		return false;
	}
}
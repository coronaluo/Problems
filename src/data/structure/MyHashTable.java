package data.structure;

import java.util.Arrays;

public class MyHashTable<K, V> {
	public static void main(String []args) {
		MyHashTable<Integer, Integer> hm = new MyHashTable<Integer, Integer>();
		hm.put(1, 10);
		hm.put(2, 10);
		hm.put(3, 10);
		
		System.out.println(hm.containsKey(1));
		System.out.println(hm.containsKey(2));
		System.out.println(hm.containsKey(3));
		System.out.println(hm.containsKey(4));
		hm.put(2, 20);
		hm.put(3, 30);
		hm.put(4, 40);
		
		System.out.println(hm.get(1));
		System.out.println(hm.get(2));
		System.out.println(hm.get(3));
		System.out.println(hm.get(4));
		
		hm.remove(1);
		hm.remove(2);
		hm.remove(3);
		
		System.out.println(hm.containsKey(1));
		System.out.println(hm.containsKey(2));
		System.out.println(hm.containsKey(3));
		System.out.println(hm.containsKey(4));
	}
	
	public static final int DEFAULT_CAPACITY = 16;
	public static final double DEFAULT_LOAD_FACTOR = 1;
	
	protected int size;
	protected double loadFactor;
	protected Entry<K, V>[] table;
	
	public MyHashTable() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public MyHashTable(double loadFactor) {
		this(DEFAULT_CAPACITY, loadFactor);
	}
	
	public MyHashTable(int size, double loadFactor) {
		this.loadFactor = loadFactor;
		this.size = DEFAULT_CAPACITY;
		table = new Entry[DEFAULT_CAPACITY];
	}
	
	public V get(K k) {
		Entry<K, V> entry = getEntry(k); 
		if (entry == null) throw new RuntimeException("no such key exists");
		return entry.getValue();
	}
	
	public boolean containsKey(K k) {
		return (getEntry(k) != null);
	}

	public Entry<K, V> getEntry(K k) {
		int hash = k.hashCode() % table.length;
		Entry entry = table[hash];
		while(entry != null && entry.getKey() != k) {
			entry = entry.next;
		}
		if (entry == null) return null;
		return entry;
	}
	
	public void put(K k, V v) {
		checkCapacity();
		int hash = k.hashCode() % table.length;
		
		Entry<K, V> entry = table[hash];
		Entry<K, V> pre = null;
		while (entry != null) {
			pre = entry;
			if (entry.key.equals(k)) {
				entry.setValue(v);
				return;
			}
			entry = entry.next;
		}
		Entry<K, V> newEntry = new Entry(k, v);
		if (pre == null) table[hash] = newEntry;
		else pre.next = newEntry;
	}

	public boolean remove(K k) {
		int hash = k.hashCode() % table.length;
		Entry<K, V> entry = table[hash];
		Entry<K, V> preEntry = null;
		while(entry != null && entry.getKey() != k) {
			preEntry = entry;
			entry = entry.next;
		}
		if (entry == null) return false;
		if (preEntry == null) {
			table[hash] = null;
		} else {
			preEntry.next = null;	
		}
		size--;
		return true;
	}
		
	private void checkCapacity() {
		if (size == table.length * loadFactor) {
			table = Arrays.copyOf(table, size * 2);
		}
	}
	
	class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;
		
		public Entry(K k, V v) {
			this.key = k;
			this.value = v;
			next = null;
		}
		
		public K getKey() {return key;}
		public V getValue() {return value;}
		public void setKey(K k) {key = k;}
		public void setValue(V v) {value = v;}
	}
	
	
}

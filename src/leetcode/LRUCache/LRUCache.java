package leetcode.LRUCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class LRUCache {
	private LinkedHashMap<Integer, Integer> cache;
	private int cpt;
	
	public static void main(String []args) {
//		List<Integer> l = new ArrayList<Integer>();
//		l.add(1);
//		l.add(2);
//		
//		List<Integer> l2 = new ArrayList<Integer>();
//		l2.add(0);
//		l.addAll(l2);
//		
//		for (int i : l) {
//			System.out.println(i);
//		}
		
		
		LRUCache lruc = new LRUCache(4);
		lruc.set(1, 10);
		lruc.set(3, 20);
		lruc.set(3, 30);
		lruc.set(4, 40);
		lruc.set(5, 50);
		
		System.out.println("access key: " + lruc.get(3));
		for (int i : lruc.cache.keySet()) {
			System.out.println(i);
		}
	}
	
    public LRUCache(int capacity) {
    	try {
    		cpt = capacity;
    		cache = new LinkedHashMap<Integer, Integer>(cpt, 1f, true);
    	} catch (IllegalArgumentException ae) {
    		ae.printStackTrace();
    	}
    }
    
    public int get(int key) {
    	if (!cache.containsKey(key)) return -1;
    	else return cache.get(key);
    }
    
    public void set(int key, int value) {
    	if (cache.size() == cpt && !cache.containsKey(key)) {
    		removeLRUEntry();
    	}
    	cache.put(key, value);
    }
    
    private void removeLRUEntry() {
    	int first = -1;
    	for (int key : cache.keySet()) {
    		first = key;
    		break;
    	}
    	if (first != -1) //cache.remove(first);
    		System.out.println("remove value: " + cache.remove(first));
    }
    
}

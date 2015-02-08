package leetcode.LRUCache;

import java.util.HashMap;

public class LRUCache2 {
	HashMap<Integer, HashValue> hm;
    int maxlen;
    DLLNode head, tail;
    
    public static void main(String []args) {
		LRUCache2 lruc = new LRUCache2(5);
		lruc.set(1, 10);
		lruc.set(3, 20);
		lruc.set(3, 30);
		lruc.set(4, 40);
		lruc.set(5, 50);
		
		
		lruc.displayCacheOrder();
		
		System.out.println("...");
		lruc.set(6, 60);
		
		lruc.displayCacheOrder();
		
		System.out.println("...");
		System.out.println("access key 3 get value: " + lruc.get(3));
		lruc.displayCacheOrder();
	}
    
    public void displayCacheOrder() {
    	DLLNode p = head;
    	while (p != null) {
    		System.out.println(p.key);
    		p = p.next;
    	}
    }
    
    public LRUCache2(int capacity) {
    	if (capacity < 0) throw new RuntimeException("illegal input: capacity = "+capacity);
        hm = new HashMap<Integer, HashValue>();
        maxlen = capacity;
        head = null;
        tail = null;
    }
    
    public int get(int key) {
        if (!hm.containsKey(key)) return -1;
        HashValue hv = hm.get(key);
        updateCache(hv.node);
        return hv.value;
    }
    
    public void set(int key, int value) {
    	if (maxlen == 0) return;
        if (!hm.containsKey(key)) {
            if (hm.size() == maxlen) removeLRU();
            addToCache(new DLLNode(key));
            HashValue hv = new HashValue(value, tail);
            hm.put(key, hv);
            
        } else {
            HashValue hv = hm.get(key);
            updateCache(hv.node);
            hv.value = value;
            hv.node = tail;
        }
    }
    
    private void updateCache(DLLNode node) {
    	removeFromCache(node);
        addToCache(node);
    }
    
    private void addToCache(DLLNode node) {
    	if (head == null) head = node;
    	if (tail != null) {
    		tail.next = node;
    		node.pre = tail;
    	}
        tail = node;
    }
    
    private void removeFromCache(DLLNode node) {
    	if (node == null) return;
    	
    	DLLNode prenode = node.pre, nextnode = node.next;
    	if (prenode == null) head = nextnode;
    	else prenode.next = nextnode;
    	
    	if (nextnode == null) tail = prenode;
    	else nextnode.pre = prenode;
    	
    	node.pre = null;
    	node.next = null;
    }
    
    private void removeLRU() {
    	hm.remove(head.key);
    	removeFromCache(head);
    }
    
    private class HashValue {
        int value;
        DLLNode node; // index in DLL
        public HashValue(int v, DLLNode n) {
            value = v;
            node = n;
        }
    }
    
    private class DLLNode {
    	int key;
    	DLLNode pre, next;
    	public DLLNode(int k) {
    		key = k;
    		next = null;
    	}
    }
}

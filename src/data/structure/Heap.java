package data.structure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T> {
	static class MaxComparater implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
    	
    }
    
    static class MinComparater implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return -o1.compareTo(o2);
		}
    	
    }
    
	public static void main(String[] args) {
		Heap<Integer> heap = new Heap<Integer>(new MinComparater());
		heap.insert(4);
		heap.display();
		heap.insert(412325);
		heap.display();
		heap.insert(3023);
		heap.display();
		heap.insert(23);
		heap.display();
		heap.remove();
		heap.display();
		heap.remove();
		heap.display();
	}
	
    List<T> list;
    Comparator<T> cmp;
    int len;
    
    public Heap(Comparator<T> c) {
        list = new ArrayList<T>(); // index starts from 1
        cmp = c;
        len = 0;
    }
    
    public void display() {
    	System.out.println("***");
    	for (int i = 0; i < len; i++) {
    		System.out.print(list.get(i+1) + ", ");
    	}
    	System.out.println();
    }
    
    public void insert(T t) {
    	if (list.isEmpty()) list.add(t); // index 0 means nothing
    	len++;
    	if (len+1 > list.size()) list.add(t);
    	else list.set(len, t);
        shiftUp(len);
    }
    
    // delete the top number of heap
    public void remove() {
        if (isEmpty()) return ;
        if (len == 1) {
        	len--; 
        	return;
        }
        list.set(1, list.get(len--));
        shiftDn(1);            
    }
    
    public boolean isEmpty() {
        return (len == 0);
    }
  
  	private void swap(List<T> list, int i, int j) {
  		T tmp = list.get(i);
      	list.set(i, list.get(j));
        list.set(j, tmp);
    }
    
    // element move from bottom up
    private void shiftUp(int idx) {
        for (int i = idx; i > 1; ) {
      		int j = i / 2;
          	if (cmp.compare(list.get(j), list.get(i)) < 0) {
          		swap(list, i, j);
                i = j;
            } else {
                break;
            }
        }
    }
    
    // element move from top down
    private void shiftDn(int idx) {
        for (int i = idx; i <= len / 2; ) {
      		int j = 2 * i;
          	if (j < len && cmp.compare(list.get(j), list.get(j + 1)) < 0) j = j + 1;
          	if (cmp.compare(list.get(i), list.get(j)) < 0) {
          		swap(list, i, j);
                i = j;
            } else {
            	break;
            }
        }
    }
}

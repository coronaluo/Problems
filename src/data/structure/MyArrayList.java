package data.structure;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArrayList<T> {
	
	public static void main(String []args) {
//		MyArrayList<Integer> array = new MyArrayList<Integer>(Integer[].class);
//		array.add(1);
//		array.add(2);
//		array.add(3);
//		array.add(4);
//		array.display();
//		array.remove(2);
//		array.remove(1);
//		array.display();
		
		MyArrayList<MyClass> array = new MyArrayList<MyClass>(MyClass[].class);
		array.add(new MyClass(1));
		array.add(new MyClass(2));
		array.add(new MyClass(3));
		array.add(new MyClass(4));
		array.add(new MyClass(1));
		array.add(new MyClass(2));
		array.add(new MyClass(3));
		array.add(new MyClass(4));
		for (int i = 0; i < array.size(); i++)
			System.out.println(array.get(i).content);
	
		System.out.println("remove: "+array.remove(1).content);
		System.out.println("remove: "+array.remove(2).content);
		System.out.println("remove: "+array.remove(3).content);
		System.out.println("remove: "+array.remove(4).content);
		System.out.println("remove: "+array.remove(0).content);
		System.out.println("remove: "+array.remove(1).content);
		for (int i = 0; i < array.size(); i++)
			System.out.println(array.get(i).content);
	}
	
	public static class MyClass {
		int id;
		String content;
		
		public MyClass(int i) {
			id = i;
			content = String.valueOf(i);
		}
	}
	
	public static final int DEFAULT_SIZE = 2;
	public static final double LOAD_FACTOR = 0.25;
	
	private T[] array;
	private int curSize;
	
	public MyArrayList (Class<T[]> classType) {
		this(classType, DEFAULT_SIZE);
	}
	
	public MyArrayList (Class<T[]> classType, int cap) {
		array = classType.cast(Array.newInstance(classType.getComponentType(), cap));
		curSize = 0;
	}
	
	public int size() {
		return curSize;
	}
	
	public T get(int i) {
		if (!isLegalIndex(i)) throw new ArrayIndexOutOfBoundsException();
		return array[i];
	}
	
	public void add(T t) {
		checkCapacity();
		array[curSize++] = t;
	}
	
	public T remove(int i) {
		if (!isLegalIndex(i)) throw new ArrayIndexOutOfBoundsException();
		
		T removed = array[i];
		int s = i;
		while (s < curSize - 1) {
			// TO CHECK
			array[s] = array[++s];
		}
		curSize--;
		condenseArray();
		return removed;
	}

	private boolean isLegalIndex(int i) {
		return (i >= 0 && i< curSize); 
	}
	
	// TODO
	private void checkCapacity() {
		if (curSize >= array.length) {
			array = Arrays.copyOf(array, curSize*2);
			System.out.println("double array size from " + curSize + " to "+array.length);
		}
	}
	
	// TODO
	private void condenseArray() {
		if (curSize <= array.length * LOAD_FACTOR) {
			array = Arrays.copyOf(array, array.length / 2);
			System.out.println("condes array to size "+array.length);
		}
	}
	
}

package concurrency.practise;

import java.util.Calendar;

public class SynchronizationTest {
	public static void main(String[] args) {
		SharedObj sharedObj = new SharedObj(0,0);
		Thread1 t1 = new Thread1(sharedObj);
		Thread2 t2 = new Thread2(sharedObj);
		t1.run();
		t2.run();
	}
	
	static class SharedObj {
		int a, b;
		public SharedObj (int i, int j) {
			a = i;
			b = j;
		}
		public int getA() {
			return a;
		}
		
		public int getB() {
			return b;
		}
		
		public synchronized int increseA() {
			long sTime = Calendar.getInstance().getTimeInMillis();
			try {
				while (Calendar.getInstance().getTimeInMillis() - sTime < 100) {
					Thread.sleep(10);
					System.out.println("THREAD "+Thread.currentThread().getId() + " is accessing increseA()");
				}
			} catch (InterruptedException e) {
				System.out.println("THREAD "+Thread.currentThread().getId() + "is interrupted");
			}
			
			return (++a);
		}
		
		public synchronized int increseB() {
			long sTime = Calendar.getInstance().getTimeInMillis();
			try {
				while (Calendar.getInstance().getTimeInMillis() - sTime < 50) {
					Thread.sleep(10);
					System.out.println("THREAD "+Thread.currentThread().getId() + " is accessing increseB()");
				}
			} catch (InterruptedException e) {
				System.out.println("THREAD "+Thread.currentThread().getId() + "is interrupted");
			}
			return (++b);
		}
	}
	
	static class Thread1 implements Runnable {
		SharedObj obj;
		
		public Thread1(SharedObj o) {
			obj = o;
		}
		
		@Override
		public void run() {
			obj.increseA();
			System.out.println("a="+obj.getA() + "/ b="+obj.getB());
		}
	}
	
	static class Thread2 implements Runnable {
		SharedObj obj;
		
		public Thread2(SharedObj o) {
			obj = o;
		}
		
		@Override
		public void run() {
			obj.increseB();
			System.out.println("a="+obj.getA() + "/ b="+obj.getB());
		}
	}
	
}

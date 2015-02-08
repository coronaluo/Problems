package concurrency.practise;

import java.util.Calendar;

public class SynchronizationTest {
	public static void main(String[] args) {
		SharedObj sharedObj = new SharedObj(0,0);
		Thread t1 = new Thread(new Runnable1(sharedObj));
		Thread t2 = new Thread(new Runnable2(sharedObj));
		t1.start();
		t2.start();
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
		
		// sychronized methods locks the object instead of the code block
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
		

		// when two threads access a static method and a non-static method (both synchronized) at the same time
		// they will not interfere with each other
		public static synchronized void staticMethod() {
			long sTime = Calendar.getInstance().getTimeInMillis();
			try {
				while (Calendar.getInstance().getTimeInMillis() - sTime < 2000) {
					Thread.sleep(100);
					System.out.println("cur thread="+Thread.currentThread().getId() + " is accessing staticMethod()");
				}
			} catch (InterruptedException e) {
				System.out.println("THREAD "+Thread.currentThread().getId() + "is interrupted");
			}
		}
		
		public synchronized void nonStaticMethod() {
			long sTime = Calendar.getInstance().getTimeInMillis();
			try {
				while (Calendar.getInstance().getTimeInMillis() - sTime < 2000) {
					Thread.sleep(100);
					System.out.println("cur thread="+Thread.currentThread().getId()+"/accessing nonStaticMethod()");
				}
			} catch (InterruptedException e) {
				System.out.println("THREAD #= "+Thread.activeCount() + "is interrupted");
			}
		}
	}
	
	static class Runnable1 implements Runnable {
		SharedObj obj;
		
		public Runnable1(SharedObj o) {
			obj = o;
		}
		
		@Override
		public void run() {
//			 obj.increseA();
//			 System.out.println("a="+obj.getA() + "/ b="+obj.getB());
			obj.nonStaticMethod();
		}
	}
	
	static class Runnable2 implements Runnable {
		SharedObj obj;
		
		public Runnable2(SharedObj o) {
			obj = o;
		}
		
		@Override
		public void run() {
//			obj.increseB();
//			System.out.println("a="+obj.getA() + "/ b="+obj.getB());
			obj.staticMethod();
		}
	}
	
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ObjectWaitNotify {
	
	
	
	public static void print(String args) {
		System.out.println(args);
	}
	
	public static void testCase1() {
		Object lock = new Object();
	
		
		for(int i=0;i<3;i++) {
			String threadName = "T"+i;
			MyThread1 t1 = new MyThread1(10*i,lock);
			new Thread(t1,threadName).start();
		}
		
		
		
	}
	


	public static void main(String[] args) throws InterruptedException {
		print("start..");
		testCase1();
		
		
		
		Thread.currentThread().sleep(50*1000);
		print("end..");
		
		
	}

}
class MyThread1 implements Runnable{
	private int startValue = 0;
	private Object lock = null;
	//private String name = null;
	
	public MyThread1(int startValue, Object lock) {
		this.startValue = startValue;
		this.lock = lock;
	}
	
	public static void print(String args) {
		System.out.println(args);
	}

	@Override
	public void run() {
		synchronized(lock) {
			runLogic();
		}
		
		print(Thread.currentThread().getName()+"- done");
		
	}
	
	public void runLogic() {
		for(int i=0;i<10;i++) {
			print(Thread.currentThread().getName()+"-"+this.startValue);
			startValue++;
			try {	
				Thread.currentThread().sleep(500);
				if(startValue%5==0) {
					this.lock.notify();
					this.lock.wait();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
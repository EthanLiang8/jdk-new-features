import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicObjectTest {
	
	public static void print(String args) {
		System.out.println(args);
	}
	
	public static void increaseInteger(Integer args) {
		args++;
	}

	public static void main(String[] args) throws InterruptedException {
		
		AtomicStampedReference atr = null;
		
		
		
		
		// TODO Auto-generated method stub
		AtomicInteger v = new AtomicInteger(0);
//		Integer v = Integer.valueOf(0);
//		MyInteger myint = new MyInteger(v);
		List<Thread> list = new ArrayList<>();
		for(int i=0;i<10;i++) {
			Thread t = new Thread(new MyAtomicIntegerThread(v));
			//Thread t = new Thread(new MyIntegerThread(myint));
			t.start();
			print(i+" launch thread:"+t);
			list.add(t);
		}
		
		
		//t.join();
		
		list.forEach(t->{
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			print("done with join:"+t);
		});
		
		
		
		print("last value:"+v.get());
		//print("last value:"+myint.getValue());

	}

}
class MyAtomicIntegerThread implements Runnable{
	
	AtomicInteger value = null;
	
	public MyAtomicIntegerThread(AtomicInteger value) {
		this.value = value;
	}

	@Override
	public void run() {
	  for(int i=0;i<1000;i++) {
		  //may need a sleep here?
		 try {
			Thread.currentThread().sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  this.value.addAndGet(1);
	  }
	}
	
	
}
class MyInteger{
	Integer value = null;
	public MyInteger(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public void increase(int inc) {
		this.value = this.value+inc;
	}
	
}
class MyIntegerThread implements Runnable{
	
	MyInteger value = null;
	
	public MyIntegerThread(MyInteger value) {
		this.value = value;
	}

	@Override
	public void run() {
	  for(int i=0;i<1000;i++) {
		  //may need a sleep here?
		 try {
			Thread.currentThread().sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  this.value.increase(1);
	  }
	}
	
	
}

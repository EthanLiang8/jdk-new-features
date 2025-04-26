import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest extends BaseClass{
	
	public static void test() {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		lock.unlock();
		
	}
	
	public static void test1() {
		ReentrantLock lock = new ReentrantLock();
		
		List<Thread> tList = new ArrayList<>();
		for(int i=0;i<10;i++) {
			Thread t = new Thread(new ThreadForReentrantLockTest(lock));
			tList.add(t);
			t.start();
		}
		
		tList.forEach(t->{
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		print("all done, sumCount:"+ThreadForReentrantLockTest.sumCount);
	}
	
	

	public static void main(String[] args) {
		print("ReentrantLockTest start....");
		test1();
	}

}
class ThreadForReentrantLockTest extends BaseClass implements Runnable{
    public static int sumCount = 0;
    public static volatile String lasttimeThreadName = "";
    int selfCount = 0;
    ReentrantLock lock = null;
    
    
    public ThreadForReentrantLockTest(ReentrantLock lock){
    	this.lock = lock;
    }
    
    
	@Override
	public void run() {
		
		int countinueLock = 0;
		for(int i=0;i<1000;i++) {
			try {
				lock.lock();
				sleep(5);
				sumCount++;
				selfCount++;
				String cname = Thread.currentThread().getName();
				if(!cname.equals(lasttimeThreadName)) {
					print(lasttimeThreadName+" -> "+cname+" selfCount:"+selfCount+" sumCount:"+sumCount);
				}
				lasttimeThreadName = cname;
				
				//print(Thread.currentThread().getName()+" working,selfCount:"+selfCount+" sumCount:"+sumCount);
			} finally{
				lock.unlock();
				countinueLock = 0;
			}
		}
		
		print(Thread.currentThread().getName()+" done,selfCount:"+selfCount+" sumCount:"+sumCount);
		
	}
	
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest extends BaseClass {

	public static void test1() {

//		ThreadPoolExecutor executor1 = (ThreadPoolExecutor)Executors.newFixedThreadPool
//				(2, new ThreadPoolRunnableThreadFactory("TD-"));

		//LinkedBlockingQueue lbq = new LinkedBlockingQueue();
		ArrayBlockingQueue lbq = new ArrayBlockingQueue(2);
		ThreadPoolRunnableThreadFactory tfactpry = new ThreadPoolRunnableThreadFactory("TD-");
		RejectedExecutionHandler handler = new ThreadPoolRejectedHandler();
		//handler = new ThreadPoolExecutor.AbortPolicy();
		ThreadPoolExecutor executor1 = new ThreadPoolExecutor(2, 6, 1, TimeUnit.SECONDS, lbq, tfactpry,handler);

		List<Thread> tList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			String name = "RB-" + i;
			ThreadPoolRunnable runable = new ThreadPoolRunnable(name);

			// Thread t = new Thread(new ThreadPoolRunnable(),"TD-"+i);
			// tList.add(t);
			// t.start();

			executor1.submit(runable);
			print("submit--"+name);
		}

//		tList.forEach(t->{
//			try {
//				t.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});

		
		
		//executor1.shutdownNow();
		
		print("main end");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

}

class ThreadPoolRunnableThreadFactory extends BaseClass implements ThreadFactory {

	private String namePrefix = null;
	private int count = 0;

	public ThreadPoolRunnableThreadFactory(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	@Override
	public Thread newThread(Runnable r) {
		String name = this.namePrefix + count;
		Thread t = new Thread(r, name);
		count++;
		print("created thread:" + name);
		return t;
	}

}

class ThreadPoolRunnable extends BaseClass implements Runnable {

	private String name = null;
	// private int count = 0;

	public ThreadPoolRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		String tname = Thread.currentThread().getName();
		for (int i = 0; i < 10; i++) {
			print(tname + "   " + name + "  count:" + i);
			sleep(500);
		}

	}

}

class ThreadPoolRejectedHandler extends BaseClass implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		String msg = "Task " + r.toString() + " rejected from " + executor.toString();
        print(msg);
	}

}

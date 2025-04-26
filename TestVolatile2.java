import java.util.concurrent.TimeUnit;

public class TestVolatile2 {
	//private static volatile boolean stop = false;
	private static boolean stop = false;

	static void setStop(boolean stop) {
		TestVolatile2.stop = stop;
	}
	
	public static void main(String[] args) {
		// Thread-A
		new Thread("Thread A") {
			int count = 0;
			@Override
			public void run() {
				while (!TestVolatile2.stop) {
					if(count%9999999 == 1) {
						//System.out.println("still running,"+count+" "+stop);
					}
					count++;
				}
				System.out.println(Thread.currentThread() + " stopped");
			}
		}.start();
		
		
	

		// Thread-main
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread() + " after 1 seconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Thread-B
		new Thread("Thread B") {
			@Override
			public void run() {
				while (!TestVolatile2.stop) {
					TestVolatile2.stop = true;
				}
				System.out.println(Thread.currentThread() + " stopped");
			}
		}.start();
	

		
		System.out.println("main done...");
	}
}

import java.util.concurrent.TimeUnit;

public class TestVolatile3 {
	//private static volatile boolean stop = false;
	private static volatile int stop = 1;

	
	
	public static void main(String[] args) {
		// Thread-A
		new Thread("Thread A") {
			int count = 0;
			@Override
			public void run() {
				while (stop==1) {
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
		for(int i=0;i<100;i++) {
			stop = i+2;
			//setStop(true);
		}

		
		System.out.println("main done...");
	}
}


public class BaseClass {

	public static void print(String args) {
		System.out.println(args);
	}

	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

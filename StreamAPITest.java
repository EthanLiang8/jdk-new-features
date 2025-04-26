import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamAPITest extends BaseClass{
	
	

	
	
	public static void main(String[] args) {
		    int[] v = new int[] { 1,2,3,4,5,6,7 };
			//String s = Arrays.stream(v).boxed().map(v1->v1.toString()).collect(Collectors.joining(","));
			
		    String s = Arrays.stream(v).boxed().map(v1->v1.toString()).collect(
		    		new MySupplier(),
		    		new MyBiConsumer(),
		    		new MyCombiner());
			
			print(s);

	}

}
class MySupplier  extends BaseClass implements Supplier<String>{

	@Override
	public String get() {
		
		return "MySupplier-";
	}
	
}
class MyBiConsumer  extends BaseClass implements BiConsumer<String,String>{

	@Override
	public void accept(String t, String u) {
		print("MyBiConsumer-"+t+"  "+u);
		
	}
	
}

class MyCombiner  extends BaseClass implements BiConsumer<String,String>{

	@Override
	public void accept(String t, String u) {
		print("MyCombiner-"+t+"  "+u);
		
	}
	
}

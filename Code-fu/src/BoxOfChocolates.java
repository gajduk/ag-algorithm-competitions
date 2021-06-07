import java.util.HashMap;


public class BoxOfChocolates {
	
	public static void main(String[] args) {
		BoxOfChocolates b = new BoxOfChocolates();
		b.takeChocolates(156811385);
	}
	
	private HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
	
	 public int takeChocolates(int n) {
		 if ( n == 0 ) return 0;
		 if ( ! map.containsKey(n) ) {
			 int c = Math.max(n,takeChocolates(n/2)+takeChocolates(n/4)+takeChocolates(n/6)+takeChocolates(n/8));
			 map.put(n, c);
		 }
		 return map.get(n);
     }

}

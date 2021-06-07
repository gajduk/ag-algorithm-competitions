import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;



public class Cuts {
	
	
	HashMap<Map.Entry<Integer, Integer>,BigInteger> cache = new HashMap<Map.Entry<Integer, Integer>,BigInteger>();
	
	public static void main(String[] args) {
		Cuts cuts = new Cuts();
		System.out.println(cuts.  getMax("0849", 0));
	}
	
	  public int getMax(String numbers, int k) {
		  return getMax(numbers,0,k+1).intValue();
	  }

	private BigInteger getMax(String numbers, int start, int k) {
		if ( k == 0 && start == numbers.length() ) return BigInteger.ZERO;
		if ( start == numbers.length() || k == 0 ) return new BigInteger("9999999999999");
		Map.Entry<Integer, Integer> e = new AbstractMap.SimpleEntry<Integer,Integer>(start,k);
		if ( cache.containsKey(e) ) return cache.get(e);
		else {
			BigInteger min = new BigInteger("9999999999999");
			for ( int i = start+1 ; i <= numbers.length() ; ++i ) {
				BigInteger bi = new BigInteger(numbers.substring(start,i));
				BigInteger br = getMax(numbers,i,k-1);
				BigInteger max = bi;
				if ( bi.compareTo(br) < 0 ) {
					max = br;
				}
				if ( min.compareTo(max) > 0 ) {
					min = max;
				}
			}
			cache.put(e,min);
			return min;
		}
	}

}

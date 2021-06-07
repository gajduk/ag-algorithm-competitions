import java.util.ArrayList;


public class Cache {
	
	public static void main(String[] args) {
		System.out.println(new Cache().callCache(3,
				"LH?HGIVBXQUXVEFFL!TRIZMYMEBUUYQNZJANKPVLJT?RDR?"));
	}
	
	public String callCache(int m, String sequence) {
		ArrayList<Character> cache = new ArrayList<Character>();
		ArrayList<Integer> cache_lru = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		for ( int i = 0 ; i < sequence.length()-1 ; ++i ) {
			char c = sequence.charAt(i);
			if ( c == '?' ) {
				if ( cache.size() == 0 ) sb.append("*");
				for ( Character cc : cache ) {
					sb.append(cc);
				}
				sb.append(",");
			}
			else if ( c == '!' ) {
				cache = new ArrayList<Character>();
				cache_lru= new ArrayList<Integer>();
			}
			else {
				int idx = cache.indexOf((Character)c);
				if ( idx == -1 ) {
					if ( cache.size() == m ) {
						int min = i;
						int min_idx = 0;
						for ( int ii = 0 ; ii < cache_lru.size() ; ++ii ) {
							if ( cache_lru.get(ii) < min ) {
								min = cache_lru.get(ii);
								min_idx = ii;
							}
						}
						cache.set(min_idx,c);
						cache_lru.set(min_idx,i);
					}
					else {
							cache.add(c);
							cache_lru.add(i);
					}
				}
				else {
					cache_lru.set(idx,i);
				}
			}
		}
		if ( cache.size() == 0 ) sb.append("*");
		for ( Character cc : cache ) {
			sb.append(cc);
		}
		return sb.toString();
	}

}

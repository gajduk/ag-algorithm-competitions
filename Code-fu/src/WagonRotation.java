import java.util.HashSet;


public class WagonRotation {
	
	public static void main(String[] args) {
		WagonRotation w = new WagonRotation();
		System.out.println(w.minRotations("DBAC"));
	}
	/*
	public int minRotations(String train) {
		int len = train.length();
		int counter = 0;
		int ind = 0;
		while ( ind < len ) {
			System.out.println(train);
			if ( train.charAt(ind) != 'A'+ind ) {
				if ( train.indexOf('A'+ind) == len-1 ) {
					counter += 1;
					train = train.substring(0,ind)+reverse(train.substring(ind));
				}
				else {
					counter += 2;
					train = train.substring(0,train.indexOf('A'+ind))+reverse(train.substring(train.indexOf('A'+ind)));
					System.out.println(train);
					train = train.substring(0,ind)+reverse(train.substring(ind));
				}
			}
			++ind;
		}
	    return counter;
	  }
*/
	private String reverse(String substring) {
		return new StringBuffer(substring).reverse().toString();
	}

	public int minRotations(String train) {
		HashSet<String> past = new HashSet<String>();
		HashSet<String> current = new HashSet<String>();
		HashSet<String> next = new HashSet<String>();
		current.add(train);
		String res = "ABCDEFGH".substring(0,train.length());
		int counter = 0;
		while ( counter <2 && ! current.contains(res) ) {
			++counter;
			for ( String s : current ) {
				for ( int i = 1 ; i < s.length() ; ++i ) {
					String n = s.substring(0,i)+reverse(s.substring(i));
					if ( !past.contains(n) ) {
						next.add(s.substring(0,i)+reverse(s.substring(i)));
					}
				}
			}

			current.clear();
			for ( String s : next ) {
				current.add(s);
				past.add(s);
			}
			next.clear();

		}
		return counter;
	}
	
}

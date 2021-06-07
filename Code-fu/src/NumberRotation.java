import java.util.ArrayList;


public class NumberRotation {
	
	public static void main(String[] args) {
		NumberRotation n = new NumberRotation();
		System.out.println(n.count(3));
	}
	
	public int lastOne ( int N ) {
		int counter = -1;
		while ( N > 0 ) { N = N>>1; ++counter; }
		return counter;
	}
	
	public int rotateRight ( int N , int pos) {
		
		if ( (N&1) == 1 ) {
//			System.out.println(N+" "+((N>>1)+(1<<pos)));
			return ((N>>1)+(1<<pos));
		}
		return (N>>1);
	}
	
	public int count(int N) {
		ArrayList<Integer> combos = new ArrayList<Integer>();
		int l = lastOne(N);
//		System.out.println(l);
		for ( int i = 0 ; i <= l ; ++i ) {
			if ( !combos.contains(N) ) combos.add(N);
			
			N = rotateRight(N,l);
		}
//		System.out.println(combos);
		return combos.size();
	}

}

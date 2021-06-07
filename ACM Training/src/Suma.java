import java.util.ArrayList;
import java.util.Scanner;

class Pair {
	public long a;
	public long b;
	
	public Pair(long a, long b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String toString() {
		return a+" "+b;
	}
}

public class Suma {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		long n = in.nextLong();
		printPairs(n);
	}
	
	static void printPairs(long n ) {
		long sum = 0;
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		long sqrt_n = (long)Math.sqrt(n*2);
		for ( long k = 1 ; k <= sqrt_n ; ++k ) {
			if ( (n-sum) % k == 0 ) {
				pairs.add(new Pair((n-sum)/k,(n-sum)/k+k-1));
			}
			sum += k;
		}
		int size = pairs.size()-1;
		if ( pairs.get(size).a == 0 ) {
			System.out.println(size);
		}
		else {
			System.out.println(size+1);
		}
		for ( int i = size ; i >= 0  ; --i ) {
			if ( pairs.get(i).a == 0 ) continue;
			System.out.println(pairs.get(i));
		}
	}

}

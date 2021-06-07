import java.util.ArrayList;
import java.util.Arrays;


public class DearNumbers {
	
	public static void main(String[] args) {
		DearNumbers dn = new DearNumbers();
		dn.dearestNumber(1,11,2);
	}
	
	private boolean is_primes[];
	
	 public int dearestNumber(int b, int e, int m) {
		 if ( b < 2 && e < 3 ) return b;
		 if ( b < 2 ) b = 2;
		generatePrimes2(e);
		 is_primes[0] = true;
		 is_primes[1] = true;
		
		int dearness_level[] = new int[e];
		dearness_level[b] = 0;
		for ( int i = b ; i > b-m && i > 2 ; --i ) {
			if ( ! is_primes[i-1] ) ++dearness_level[b];
		}
		for ( int i = b+1 ; i < e ; ++i ) {
			dearness_level[i] = dearness_level[i-1];
			if ( ! is_primes[i-1] ) ++dearness_level[i];
			if ( i-m-1 > 1 && ! is_primes[i-m-1] ) --dearness_level[i];
		}
		int min = b;
		for ( int i = b+1 ; i < e ; ++i ) {
			if ( dearness_level[i] > dearness_level[min] ) {
				min = i;
			}
		}
		return min;
	 }
	 
	 public void generatePrimes2 ( int n ) {
			is_primes = new boolean[n];
			for ( int i = 2 ; i < n ; ++i ) {
				if (! is_primes[i] ) {
					int k = i+i;
					while ( k < n ) {
						is_primes[k] = true;k += i;
					}
				}
			}
		}
}

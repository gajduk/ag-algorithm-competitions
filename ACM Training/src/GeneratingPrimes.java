
public class GeneratingPrimes {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		generatePrimes1(100000000);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		start = System.currentTimeMillis();
		generatePrimes2(100000000);
		end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	public static void generatePrimes1 ( int n ) {
		boolean is_primes[] = new boolean[n];
		for ( int i = 2 ; i < n ; ++i ) {
			if (! is_primes[i] ) {
				for ( int k = 2 ; k*i < n ; ++k ) {
					
					is_primes[k*i] = true;
				}
			}
		}
	}
	
	public static void generatePrimes2 ( int n ) {
		boolean is_primes[] = new boolean[n];
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

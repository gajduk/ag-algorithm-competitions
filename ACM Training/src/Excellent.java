import java.util.Arrays;
import java.util.Scanner;


public class Excellent {
	
	private static boolean is_prime[] = new boolean[100000];
	private static int primes[] = new int[10001];
	
	public static void main(String[] args) {
		fillPrimes();
		Scanner in = new Scanner(System.in);
		System.out.println(primes[in.nextInt()-1]);
	}

	private static void fillPrimes() {
		Arrays.fill(is_prime, true);
		for ( int i = 2 ; i < is_prime.length/2 ; ++i ) {
			if ( is_prime[i] ) {
				for ( int k = 2 ; true ; ++k ) {
					int m = i*k;
					if ( m >= is_prime.length ) break;
					else is_prime[m] = false;
				}
			}
		}
		int c = 0;
		for ( int i = 2 ; i < is_prime.length ; ++i ) {
			if ( is_prime[i] ) {
				int r = reverse(i);
				if ( r != i ) {
					if ( is_prime[r] ) {
						primes[c++] = i;
					//	System.out.println(i);
					}
				}
			}

		}
		
	}

	private static int reverse(int i) {
		int r = 0;
		while ( i > 0 ) {
			r += i%10;
			i /= 10;
			r *= 10;
		}
		return r/10;
	}

}

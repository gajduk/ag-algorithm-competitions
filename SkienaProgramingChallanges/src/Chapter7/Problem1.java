package Chapter7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem1 {
	
	public static ArrayList<Long> prime_factor_occurance = new ArrayList<Long>();
	public static long prime_factor_occurance_1[] = new long[20];
	public static long prime_factor[] = new long[20];
	public static int num_prime_factors = 0;
	
	public static int getIndex ( long prime ) {
		for ( int k = 0 ; k < num_prime_factors ; ++k ) {
			if ( prime_factor[k] == prime ) {
				return k;
			}
		}
		System.out.println("No such element.");
		return -1;
	}
	
	public static void primeFactorization ( long n ) {
		long divisor;
		long prev_divisor = 0;
		if ( n == 0 )
			return;
	//	int boundry = (int) (n/2+1);
		prime_factor_occurance = new ArrayList<Long>();
		long boundry = (int) (Math.sqrt(n)+1);
		while ( n % 2 == 0 ) {
			n /= 2;
			if ( prime_factor_occurance.isEmpty() )
				prime_factor_occurance.add(1L);
			long temp = prime_factor_occurance.remove(prime_factor_occurance.size()-1);
			prime_factor_occurance.add(temp+1);
			prev_divisor = 2;
			//System.out.print("2,");
		}
		divisor = 3;
		while ( divisor <= boundry ) {
			if ( n % divisor == 0 ) {
				if ( prev_divisor == divisor ) {
					long temp = prime_factor_occurance.remove(prime_factor_occurance.size()-1);
					prime_factor_occurance.add(temp+1);
				}
				else {
					prime_factor_occurance.add(2L);
				}
				n /= divisor;
				boundry = (int) (Math.sqrt(n)+1);
				//boundry =  (int) (n/2+1);
				prev_divisor = divisor;
			}
			else
				divisor += 2;
		}
		if ( n > 1 ) {
			//System.out.print(n+",");
			if ( prev_divisor == n ) {
				long temp = prime_factor_occurance.remove(prime_factor_occurance.size()-1);
				prime_factor_occurance.add(temp+1);
			}
			else {
				prime_factor_occurance.add(2L);
			}
		}
	//	System.out.println();
	}
	
	public static void primeFactorizationWithoutList ( long n ) {
		long divisor;
		long prev_divisor = 0;
		if ( n == 0 )
			return;
	//	int boundry = (int) (n/2+1);
		prime_factor_occurance_1 = new long[20];
		prime_factor = new long[20];
		num_prime_factors = 0;
		long boundry = (int) (Math.sqrt(n)+1);
		while ( n % 2 == 0 ) {
			n /= 2;
			if ( num_prime_factors == 0 ) {
				prime_factor[num_prime_factors] = 2;
				prime_factor_occurance_1[num_prime_factors++] = 1L;
			}
			prime_factor_occurance_1[getIndex(2)]++;
			prev_divisor = 2;
			//System.out.print("2,");
		}
		divisor = 3;
		while ( divisor <= boundry ) {
			if ( n % divisor == 0 ) {
				if ( prev_divisor == divisor ) {
					prime_factor_occurance_1[getIndex(divisor)]++;
				}
				else {
					prime_factor[num_prime_factors] = divisor;
					prime_factor_occurance_1[num_prime_factors++] = 2L;
				}
				n /= divisor;
				boundry = (int) (Math.sqrt(n)+1);
				//boundry =  (int) (n/2+1);
				prev_divisor = divisor;
			}
			else
				divisor += 2;
		}
		if ( n > 1 ) {
			//System.out.print(n+",");
			if ( prev_divisor == n ) {
				prime_factor_occurance_1[getIndex(divisor)]++;
			}
			else {
				prime_factor_occurance_1[num_prime_factors++] = 2L;
			}
		}
	//	System.out.println();
	}
	
	public static long countDivisorsWithoutList ( long n ) {
		primeFactorizationWithoutList(n);
		long count = 1;
		for ( int k = 0 ; k < num_prime_factors ; ++k ) {
			count *= prime_factor_occurance_1[k];
		}
		return count;
	}
	
	public static long countDivisors ( long n ) {
		primeFactorization(n);
		long count = 1;
		while (!  prime_factor_occurance.isEmpty() ) {
			count *= prime_factor_occurance.remove(0);
		}
		return count;
	}
	
	public static boolean isLightbulbOnBetter ( long n ) {
		return (int) Math.sqrt(n) < Math.sqrt(n) ? false : true;
	}

	public static void main ( String args[] )  {
		
		//test_file();
		//test_all();
		//test_countDivisors();
		test_judge();
	}
	
	public static void test_all () {
		int boundry = (int) Math.pow(2,32)-1;
		for ( int k = 0 ; k < boundry ; ++k ) {
			if ( k % 1000000 == 0 )
				System.out.println(k);
			isLightbulbOnBetter(k);
		}
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( true ) {
    		long num_lightbulbs = new Long(in.nextLine());
    		if ( num_lightbulbs == 0 )
    			break;
    		if ( isLightbulbOnBetter(num_lightbulbs) ) {
    			System.out.println("yes");
    		}
    		else {
    			System.out.println("no");
    		}
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		long num_lightbulbs = new Long(in.nextLine());
    		if ( num_lightbulbs == 0 )
    			break;
    		if ( isLightbulbOn(num_lightbulbs) ) {
    			System.out.println("yes");
    		}
    		else {
    			System.out.println("no");
    		}
    	}
    	
	}
	
	public static boolean isLightbulbOn ( long n ) {
		return countDivisorsWithoutList(n)%2 == 1;
	}

	public static void test_countDivisors() {
		System.out.println(countDivisors((int) Math.pow(2,32)-1));
		System.out.println(countDivisors(10));
		System.out.println(countDivisors(4));
		System.out.println(countDivisors(20));
		System.out.println(countDivisors(2));
		System.out.println(countDivisors(1));
		System.out.println(countDivisors(8));
		System.out.println(countDivisors(20580));
		System.out.println(countDivisors(3));
		System.out.println(countDivisors(6241));
		System.out.println(countDivisors(8191));
		System.out.println(countDivisors(2000000000));
		System.out.println(countDivisors(17));
	}

}
 
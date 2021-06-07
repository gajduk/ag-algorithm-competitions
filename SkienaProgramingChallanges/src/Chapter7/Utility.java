package Chapter7;

public class Utility {
	
	public static int x = 0;
	public static int y = 0 ;
	public static int x1 = 0;
	public static int y1 = 0;
	/* find gcd(a,b) and x,y  such that a*x + b*y = gcd(a,b) */
	
	public static long greatestCommonDivisorEquationSolve(long p, long q, long x[], long y[] ) {
		long x1[] = new long[1];
		long y1[] = new long[1]; /* previous coefficients */
		long g; /* value of gcd(p,q) */
		if (q > p) return(greatestCommonDivisorEquationSolve(q,p,y,x));
		if (q == 0) {
			x[0] = 1;
			y[0] = 0;
			return(p);
		}
		g = greatestCommonDivisorEquationSolve(q, p%q, x1, y1);
		x[0] = y1[0];
		y[0] = (x1[0] - (int) (p/q)*y1[0]);
		return(g);
	}
	

	
	public static int greatestCommonDivisorEuclidsAlgorithm ( int number1 , int number2 ) {
		if ( number1 > number2 ) {
			if ( number2 == 0 ) 
				return number1;
			return greatestCommonDivisorEuclidsAlgorithm(number1%number2, number2);
		}
		else {
			if ( number1 == 0 ) 
				return number2;
			return greatestCommonDivisorEuclidsAlgorithm(number2%number1, number1); 
		}
	}
	
	public static int greatestCommonDivisorEuclidsAlgorithm_1 ( int number1 , int number2 ) {
		if ( number2 > number1 ) 
			return greatestCommonDivisorEuclidsAlgorithm_1(number2,number1);
		if ( number2 == 0 ) 
				return number1;
		return greatestCommonDivisorEuclidsAlgorithm_1(number2,number1%number2);
	}
	
	public static boolean isPrime ( int n ) {
		if ( n % 2 == 0 )
			if ( n == 2 ) 
				return true;
			else
				return false;
		int divisor = 3;
		int boundry = (int) (Math.sqrt(n)+1);
		while ( divisor <= boundry ) {
			if ( n % divisor == 0 )
				return false;
			divisor += 2;
		}
		return true;
	}
	
	public static void primeFactorization ( int n ) {
		int divisor;
		if ( n == 0 )
			return;
		while ( n % 2 == 0 ) {
			n /= 2;
			System.out.print("2,");
		}
		divisor = 3;
		int boundry = (int) (Math.sqrt(n)+1);
		while ( divisor <= boundry ) {
			if ( n % divisor == 0 ) {
				System.out.print(divisor+",");
				n /= divisor;
				boundry = (int) (Math.sqrt(n)+1);
			}
			else
				divisor += 2;
		}
		if ( n > 1 )
			System.out.print(n+",");
		System.out.println();
	}
	
	public static void main ( String args[] ) {
		//System.out.println(Math.sqrt(Integer.MAX_VALUE));
		//test_isPrime();
		//test_primeFactorization();
//		test_gcdEquation();
		//test_gcdEuclid();
		primeFactorization(81461);
	}

	private static void test_gcdEquation() {
		int a = 34398;
		int b = 2132;
		long x[] = new long[1];
		long y[] = new long[1];
		long gcd = greatestCommonDivisorEquationSolve(a,b,x,y);
		System.out.println("gcd("+a+","+b+") = "+gcd+" with x = "+x[0]+" and y = "+y[0]);
		long u = b / gcd;
		long v = a / gcd;
		System.out.println((u+x[0])+"  "+(-v+y[0]));
		System.out.println((u*2+x[0])+"  "+(-v*2+y[0]));
		System.out.println((u*3+x[0])+"  "+(-v*3+y[0]));
	}

	private static void test_gcdEuclid() {
		System.out.println(greatestCommonDivisorEuclidsAlgorithm(1,2));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm(2147483647,71));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm(30,45));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm(0,2));
		//System.out.print(greatestCommonDivisorEuclidsAlgorithm(-5,2));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm(4,4));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm(7,7));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm_1(1,2));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm_1(2147483647,71));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm_1(30,45));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm_1(0,2));
		//System.out.print(greatestCommonDivisorEuclidsAlgorithm_1(-5,2));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm_1(4,4));
		System.out.println(greatestCommonDivisorEuclidsAlgorithm_1(7,7));
		
	}

	private static void test_primeFactorization() {
		primeFactorization(1);
		primeFactorization(2);
		primeFactorization(3);
		primeFactorization(10);
		primeFactorization(1968);
		primeFactorization(9846251);
		primeFactorization(-561);
		primeFactorization(0);
		primeFactorization(2147483647);
	}

	private static void test_isPrime() {
		System.out.println(isPrime(214748647));
		System.out.println(isPrime(71));
		System.out.println(isPrime(193627));
		System.out.println(isPrime(1));
		System.out.println(isPrime(2));
		System.out.println(isPrime(3));
		System.out.println(isPrime(0));
		System.out.println(isPrime(-153));
		System.out.println(isPrime(-17));
		
	}

}

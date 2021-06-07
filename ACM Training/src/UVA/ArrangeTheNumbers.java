package UVA;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class ArrangeTheNumbers {
	/*
	private static final long mod = 1000000007L;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int i = 0 ; i < t ; ++i ) {
			int n = in.nextInt();int m = in.nextInt();int k = in.nextInt();
			System.out.println("Case "+(i+1)+": "+f(n,m,k));
		}
	}
	
	
	private static long f ( int n , int m , int k ) {
		return combs(m, k)*fullDearangementOfFirstMElements(n-k, m-k);		
	}
	
	private static long fullDearangementOfFirstMElements ( int n , int m ) {
		long res = fact(n);
		for ( int i = 1 ; i <= m ; ++i ) {
			if ( (i&1) == 0 ) {
				res += combs(m,i)*fact(n-i);
			}
			else {
				res -= combs(m,i)*fact(n-i);
			}
		}
		return res;
	}
	
	//tested
	private static long partialDearengements ( int n , int k ) {
		long c = combs(n,k);
		long subf = subf(n-k);
		return c*subf;
	}
		
	//tested
	private static long subf(int n) {
		if ( n == 0 ) return 1;
		if ( n == 1 ) return 0;
		if ( n == 2 ) return 1;
		return (n-1)*(subf(n-1)+subf(n-2));
	}

	//tested
	private static long combs(int n, int k) {
		return fact(n)/(fact(n-k)*fact(k));
	}
	
	
	//tested
	private static long fact(int i) {
		if ( i == 0 ) return 1;
		return i*fact(i-1);
	}

	*/
	
	private static final int MAX = 1001;
	private static long binoms[][] = new long[MAX][MAX];
	private static long facts[] = new long[MAX];
	private static final long mod = 1000000007L;
	
	public static void main(String[] args) {
		for ( int i = 0 ; i < binoms.length ; ++i ) {
			binoms[i][0] = 0;
		}
		for ( int n = 1 ; n < MAX ; ++n ) {
			for ( int m = 1 ; m < n ; ++m ) {
				binoms[n][m] =  binoms[n-1][m]+binoms[n-1][m-1];
				binoms[n][m] %= mod;
			}
			binoms[n][n] = 1;
			//System.out.println(Arrays.toString(binoms[n]));
		}
		facts[0] = 1;
		for ( int i = 1 ; i < MAX ; ++i ) {
			facts[i] = (facts[i-1]*i)%mod;
		}
		/*
		System.out.println(combs(4, 2));
		System.out.println(combs(5, 2));
		System.out.println(combs(5, 1));
		*/
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int i = 0 ; i < t ; ++i ) {
			int n = in.nextInt();int m = in.nextInt();int k = in.nextInt();
			long res = f(n,m,k);
			res = (res+mod)% mod;
			System.out.println("Case "+(i+1)+": "+res);
		}
	}
	
	
	private static long f ( int n , int m , int k ) {
		return (combs(m, k)*fullDearangementOfFirstMElements(n-k, m-k))%mod;		
	}
	
	private static long fullDearangementOfFirstMElements ( int n , int m ) {
		long res = fact(n);
		for ( int i = 1 ; i <= m ; ++i ) {
			if ( (i&1) == 0 ) {
				res += combs(m,i)*fact(n-i);
			}
			else {
				res -= combs(m,i)*fact(n-i);
			}
			res %= mod;
		}
		return res;
	}

	//tested
	private static long combs(int n, int k) {
		return binoms[n+1][k+1];
	}
	
	
	//tested
	private static long fact(int n) {
		return facts[n];
	}


}

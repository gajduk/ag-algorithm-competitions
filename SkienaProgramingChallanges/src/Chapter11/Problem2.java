package Chapter11;

import java.math.BigInteger;
import java.util.Scanner;

public class Problem2 {
	
	public static String getDistinctSubsequencesBigInteger ( char x[] , char z[] ) {
		BigInteger t[] = new BigInteger[z.length+1];
		t[0] = new BigInteger("1");
		for ( int k = z.length ; k > 0 ; --k ) {
			t[k] = new BigInteger("0");
		}
		for ( int i = 0 ; i < x.length ; ++i ) {
			for ( int k = z.length-1 ; k >= 0 ; --k ) {
				if ( z[k] == x[i] ) {
					t[k+1]  = t[k+1].add(t[k]);
				}
			}
		}
		return t[z.length].toString();
	}
	
	public static long getDistinctSubsequences ( char x[] , char z[] ) {
		long t[] = new long[z.length+1];
		t[0] = 1;
		for ( int i = 0 ; i < x.length ; ++i ) {
			for ( int k = z.length-1 ; k >= 0 ; --k ) {
				if ( z[k] == x[i] ) {
					t[k+1] += t[k];
				}
			}
		}
		return t[z.length];
	}
	
	public static void main ( String args [] ) {
		test_judge();
//		System.out.println(getDistinctSubsequencesBigInteger(new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").toCharArray(), new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").toCharArray()));
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		for ( int i = 0 ; i < num_test_cases ; ++i ) {
			String x = in.nextLine();
			String z = in.nextLine();
			System.out.println(getDistinctSubsequencesBigInteger(x.toCharArray(), z.toCharArray()));
			
		}
	}
	
	
	
	

}

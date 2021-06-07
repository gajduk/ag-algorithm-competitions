package Chapter6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem3 {
	public static BigInteger t[][] = new BigInteger[1010][1010];
	
	public static void main ( String args[] ) {
		test_judge();
//		test_file();
	}

	private static void test_file() {
		FileInputStream input = null;
		try {
			input = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem3.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in = new Scanner(input);
		populateMatrix();
		while ( in.hasNext() ) {
			 int n = new Integer(in.nextLine());
			 System.out.println(t[n][n]);
		 
		}
	}
	
	public static void populateMatrix ( ) {
		BigInteger two = new BigInteger("2");
		for ( int i = 0 ; i < t.length ; ++i ) {
			t[0][i] = new BigInteger("0");
		}
		for ( int i = 0 ; i < t.length ; ++i ) {
			t[i][0] = new BigInteger("1");
		}
		for ( int i = 1 ; i < t.length ; ++i ) {
			for ( int j = 1 ; j <= i ; ++j ) {
				BigInteger result = new BigInteger("0");
				if ( j-1 >= 0 ) {
					result = two.multiply(result.add(t[i-1][j-1]));
				}
				if ( j-2 >= 0 ) {
					result = result.add(t[i-1][j-2]);
				}
				if ( j-3 >= 0 ) {
					result = result.add(t[i-1][j-3]);
				}
				t[i][j] = result;
			}
		}
	}
	
	public static long  countWays ( int n , int k ) {
		if ( n == 0 ) {
			return 1;
		}
		if ( n > k*3 ) {
			return 0;
		}
		if ( n < 0 ) {
			return 0;
		}
		return 2*countWays(n-1, k-1)+countWays(n-2, k-1)+countWays(n-3, k-1);
		
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		populateMatrix();
//		System.out.println(end-start+" ms");
		while ( in.hasNext() ) {
			 int n = new Integer(in.nextLine());
			 System.out.println(t[n][n]);		 
		}
	}

}

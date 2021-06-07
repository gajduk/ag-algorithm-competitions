package Chapter6;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem4 {
	
	public static final int NSIZE = 301;
	public static final int  DSIZE = 151;
	public static BigInteger numbers[][] = new BigInteger[NSIZE][DSIZE];
	public static BigInteger results[][] = new BigInteger[NSIZE][DSIZE];
	
	public static void generateNumbers () {
		  for ( int n = 0 ; n < NSIZE ; ++n ) {
			  for ( int d = 0 ; d < DSIZE ; ++d ) {
				  numbers[n][d] = new BigInteger("0");
			  }
		  }
		  for ( int d = 0 ; d < DSIZE ; ++d )
			  numbers[0][d] = new BigInteger("1");
		  for ( int n = 2 ; n < NSIZE; n += 2 )
			  for ( int d = 1 ; d < DSIZE ; ++d )
				  for ( int i = 2 ; i <= n; i += 2 )
					  numbers[n][d] = numbers[n][d].add(numbers[i-2][d-1].multiply(numbers[n-i][d]));
		  for ( int n = 1 ; n < NSIZE; ++n )
			  for ( int d = 1 ;
					  d < DSIZE; ++d )
				  results[n][d] = numbers[n][d].subtract(numbers[n][d-1]);
	}
	
	public static void main ( String args[] ) {
		generateNumbers();
		test_judge();
		//test_file();
	}
	
	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem4.txt");
	    }
		catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
		Scanner in = new Scanner(inputFile);
		while ( in.hasNext() ) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers);
			if ( numbers.countTokens() == 2 ) {
				int n = new Integer(numbers.nextToken());
				int d = new Integer(numbers.nextToken());
				System.out.println(results[n][d]);
			}
		}
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers);
			if ( numbers.countTokens() == 2 ) {
				int n = new Integer(numbers.nextToken());
				int d = new Integer(numbers.nextToken());
				System.out.println(results[n][d]);
			}
		}
	}
	
	
	

}

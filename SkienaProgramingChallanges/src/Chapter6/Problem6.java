package Chapter6;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem6 {
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			int n = new Integer(in.nextLine());
			computeOptimalNumberOfMoves(n);
		}
	}
	
	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem6.txt");
	    }
		catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
		Scanner in = new Scanner(inputFile);
		while ( in.hasNext() ) {
			int n = new Integer(in.nextLine());
			computeOptimalNumberOfMoves(n);
		}
	}

	public static void computeOptimalNumberOfMoves(int n) {
		int x = ((int) (Math.sqrt(8*n-7) -1)/2);
		//System.out.println(x);
		//s_n=1+[n-1/2x(x-1)-1]2^x. 
		int base = (int) (n-1.0/2*x*(x-1)-1);
		BigInteger base_b = new BigInteger(Integer.toString(base));
		BigInteger two = new BigInteger("2");
		BigInteger one = new BigInteger("1");
		BigInteger power = new BigInteger("1");
		for ( int k = 0 ; k < x ; ++k ) {
			power = power.multiply(two);
		}
		BigInteger result = one.add(power.multiply(base_b));
		System.out.println(result.toString());
	}

}

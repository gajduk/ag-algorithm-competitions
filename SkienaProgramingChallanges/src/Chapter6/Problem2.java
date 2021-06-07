package Chapter6;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem2 {
	
	public static void main ( String args[] ) {
		//System.out.println(powerOf2(40000).toString());
		//test_file();
		test_judge();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem2.txt");
	    }
		catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
		Scanner in = new Scanner(inputFile);
		int num_test_cases = new Integer(in.nextLine());
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			int n = new Integer(in.nextLine());
			calculus(n);
		}
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			int n = new Integer(in.nextLine());
			calculus(n);
		}
	}
	
	private static int formulae(int n) {
		int sum = 0;
		for ( int m = 0 ; m <= n-2 ; ++m ) {
			sum += m*(m-1)/2;
		}
		return n+(n-2)+(n-2)*(n-3)*(n-4)-sum;
	}
	
	public static void calculus ( int n ) {
			BigInteger bg1=new BigInteger (Integer.toString(n));
	    	//System.out.println("n= "+bg1);		    	
	    	BigInteger  bg2=new BigInteger ("0");
	    	bg2=bg2.add(bg1);
	    	bg2=bg2.multiply(bg2);
	    	//System.out.println("n*n= "+bg2);		    	
	    	BigInteger  bg3=new BigInteger ("5");
	    	bg3=bg3.multiply(bg1);
	    	//System.out.println("5*n= "+bg3);		    	
	    	BigInteger  bg4=new BigInteger ("18");		    	
	    	bg2=bg2.subtract(bg3);
	    	//System.out.println("n*n-5*n= "+bg2);		    	
	    	bg2=bg2.add(bg4);
	    	//System.out.println("n*n-5*n+18= "+bg2);
	    	BigInteger  bg5=new BigInteger ("24");
	    	BigInteger bg6=new BigInteger("0");
	    	bg6=bg6.add(bg1);
	    	//System.out.println("n= "+bg6);		    	
	    	BigInteger bg7=new BigInteger("1");
	    	bg1=bg1.subtract(bg7);
	    	//System.out.println("(n-1)= "+bg1);
	    	
	    	bg2=bg2.multiply(bg6);
	    	bg2=bg2.multiply(bg1);
	    	bg2=bg2.divide(bg5);
	    	bg2=bg2.add(bg7);
	    	System.out.println(bg2);    	
	}

	
	public static String formula ( int n ) {
		BigInteger b_n = new BigInteger(Integer.toString(n));
		BigInteger b_1 = new BigInteger("1");
		BigInteger b_5 = new BigInteger("1");
		BigInteger b_18 = new BigInteger("1");
		BigInteger b_24 = new BigInteger("1");
		BigInteger b_n1 = b_n.subtract(b_1);
		BigInteger b_n_sq = b_n.multiply(b_n);
		BigInteger b_n_5 = b_n.multiply(b_5);
		BigInteger sum = b_n_sq.subtract(b_5.multiply(b_n));
		sum = sum.add(b_18);
		sum = sum.divide(b_24);
		BigInteger result = b_1.add(b_n1.multiply(b_n.multiply(sum)));
		return result.toString();
	}

	public static BigInteger powerOf2( long power ) {
		if ( power == 0 || power == -1 )
			return new BigInteger("1");
		//BigInteger one = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		return two.pow((int)power);
		/*
		BigInteger result = new BigInteger("1");
		long  i = 1;
		BigInteger k = two;
		while ( true ) {
			if ( power == 0 ) 
				return result;
			if ( power >= ( i*2 ) ) {
				i *= 2;
				k = k.multiply(k);
			}
			else {
				result = result.multiply(k);
				power -= i;
				k = two;
				i = 1;		
			}
		 }
		 */
	}

}

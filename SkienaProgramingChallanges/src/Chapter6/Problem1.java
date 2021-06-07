package Chapter6;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;
// 0000000000 
public class Problem1 {
	public static int fibonaci_length = 0;
	public static BigInteger fibonaci[] = new BigInteger[501];
	
	
	public static void generateFibonacci ( ) {
		BigInteger f_n_2 = new BigInteger("1");
		BigInteger f_n_1 = new BigInteger("1");
		fibonaci[fibonaci_length++] = f_n_2;
		//fibonaci[fibonaci_length++] = f_n_1;
		BigInteger f_n;
		while ( fibonaci_length < 500 ) {
			f_n = f_n_1.add(f_n_2);
			fibonaci[fibonaci_length++] = f_n;
			f_n_2 = f_n_1;
			f_n_1 = f_n;
		}
		
	}
	
	public static void main ( String args[] ) {
		//System.out.println(new BigInteger("3").compareTo(new BigInteger("2")));
		//test_file();
		test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		generateFibonacci();
		BigInteger zero = new BigInteger("0");
		while ( true ) {
			String test_case = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(test_case);
			BigInteger start = new BigInteger(numbers.nextToken());
			BigInteger end = new BigInteger(numbers.nextToken());
			if ( start.equals(zero) && end.equals(zero) )
				break;
			int num_fibonaci = 0;//the result ( number of fibonacci numbers in the interval[star,end]
			int walker = 0;
			if ( start.compareTo(end) > 0 ) {
				String temp = start.toString();
				start = end;
				end = new BigInteger(temp);
			}
			while ( start.compareTo(fibonaci[walker]) > 0  ) {
				++walker;
			}
			while ( end.compareTo(fibonaci[walker]) >= 0  ) {
				++walker;
				++num_fibonaci;
			}
			System.out.println(num_fibonaci);
		}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	     
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem1.txt");
	    }
		catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
		Scanner in = new Scanner(inputFile);
		generateFibonacci();
		BigInteger zero = new BigInteger("0");
		while ( true ) {
			String test_case = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(test_case);
			BigInteger start = new BigInteger(numbers.nextToken());
			BigInteger end = new BigInteger(numbers.nextToken());
			if ( start.equals(zero) && end.equals(zero) )
				break;
			int num_fibonaci = 0;//the result ( number of fibonacci numbers in the interval[star,end]
			int walker = 0;
			while ( start.compareTo(fibonaci[walker]) > 0  ) {
				++walker;
			}
			while ( end.compareTo(fibonaci[walker]) > 0  ) {
				++walker;
				++num_fibonaci;
			}
			System.out.println(num_fibonaci);
		}
	}
		
	
	
	

}

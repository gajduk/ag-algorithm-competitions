package Chapter5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem1 {
	
	public static void main ( String args[] ) {
	//	test_countCaries();
		test_file();
		test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( true ) {
    		String numbers = in.nextLine();
    		StringTokenizer tkr = new StringTokenizer(numbers," ");
    		long n1 = new Long(tkr.nextToken());
    		long n2 = new Long(tkr.nextToken());
    		if ( n1 ==0 && n2 == 0 )
    			break;
    		int num_carries = countCarries(n1, n2);
    		String result = "";
    		if ( num_carries == 0 )
    			result += "No" + " carry operation.";
    		else {
    			if ( num_carries != 1 ) {
    				result += num_carries + " carry operations.";
    			}
    			else {
    				result += num_carries + " carry operation.";
    			}
    		}
    		result += "";
    		System.out.println(result);
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter5 problem1.txt");
	    }
    	catch ( FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		String numbers = in.nextLine();
    		StringTokenizer tkr = new StringTokenizer(numbers," ");
    		long n1 = new Long(tkr.nextToken());
    		long n2 = new Long(tkr.nextToken());
    		if ( n1 ==0 && n2 == 0 )
    			break;
    		int num_carries = countCarries(n1, n2);
    		String result = "";
    		if ( num_carries == 0 )
    			result += "No" + " carry operation.";
    		else {
    			if ( num_carries != 1 ) {
    				result += num_carries + " carry operations.";
    			}
    			else {
    				result += num_carries + " carry operation.";
    			}
    		}
    		result += "";
    		System.out.println(result);
    	}
	}
    	
	public static void test_countCaries() {

	}

	public static int countCarries ( long n1 , long n2 ) {
		int num_caries = 0;//the result
		int carry = 0;//what we have from adding the previous digits
		while ( n1 > 0 && n2 > 0 ) {
			long d1 = n1 % 10;
			n1 /= 10;
			long d2 = n2 % 10;
			n2 /= 10;
			carry += d1 + d2;
			carry /= 10;
			if ( carry == 1 )
				++num_caries;
		}
		if ( carry == 1 ) {
			while ( n1 > 0 ) {
				if ( n1 % 10 == 9 )
					++num_caries;
				else
					break;
				n1 /= 10;
			}
			while ( n2 > 0 ) {
				if ( n2 % 10 == 9 )
					++num_caries;
				else
					break;
				n2 /= 10;
			}
		}
		return num_caries;
	}

}

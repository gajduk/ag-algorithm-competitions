package Chapter5;

import java.io.FileInputStream;
import java.util.Scanner;
import java.lang.StringBuffer;
public class Problem2 {
	
	public static boolean checkPalindrome ( long number ) {
		String original = Long.toString(number);
		String reversed = Long.toString(reverse(number));
		return original.equals(reversed);
	}
	
	public static long reverse ( long number ) {
		String reversed = new StringBuffer(new Long(number).toString()).reverse().toString();
		return new Long(reversed);
	}
	
	public static int iterations = 0;
	
	public static long  makePalindrome ( long number ) {
		while ( ! checkPalindrome(number) ) {
			number += reverse(number);
			++iterations;
		}
		return number;
	}
	
	public static void test_all () {
		for ( int i = 0 ; i < Integer.MAX_VALUE ; ++i ) {
			
		}
	}
	
	public static void main( String args[] ) {
		test_file();
		test_judge();
	}
	
	public static void test_judge() {
    	Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	//System.out.println(num_test_cases);
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		long number = new Long(in.nextLine());
    		iterations = 0;
    		number = makePalindrome(number);
    		System.out.println(iterations+" "+number);
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;    
	    try {   
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter5 problem2.txt");
	    }
    	catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	//System.out.println(num_test_cases);
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		long number = new Long(in.nextLine());
    		iterations = 0;
    		number = makePalindrome(number);
    		System.out.println(iterations+" "+number);
    	}
	}
	
	
}



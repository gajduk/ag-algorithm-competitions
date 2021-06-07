/*
ID: gajduk_01
LANG: JAVA
TASK: dualpal
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class dualpal {
	
	public static boolean checkPalindroms ( String a ) {
		return a.equals(new StringBuffer(a).reverse().toString());
	}
	
	public static boolean checkPalindrome ( int number , int base ) {
		return checkPalindroms(Integer.toString(number, base));
	}
	
	public static boolean isDualPalindrome ( int number ) {
		int counter = 0;
		for ( int i = 2 ; i <= 10 ; ++i ) {
			counter += checkPalindrome(number,i) ? 1 : 0;
			if ( counter >= 2 )
				return true;
		}
		if ( counter >= 2 )
			return true;
		return false;	
	}
	
	public static int[] findDualPalindromes ( int start , int n ) {
		int counter = 0;
		int result[] = new int[n];
		for ( int i = start+1 ; counter < n ; ++i ) {
			if ( isDualPalindrome(i) ) {
				result[counter++] = i;
			}
		}
		return result;
	}
	
	public static void main (String [] args) throws IOException {
		//test_populate();
		
	    BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
	    String s_times = f.readLine();
	    StringTokenizer tkr_times = new StringTokenizer(s_times);
	    int n = new Integer(tkr_times.nextToken());
	    int s = new Integer(tkr_times.nextToken());
	    int result[] = findDualPalindromes(s,n);
	    for ( int i = 0 ; i < result.length ; ++i ) {
	    	out.println(result[i]);
	    }
	    out.close();
	    System.exit(0);
	}

	
	
	

}

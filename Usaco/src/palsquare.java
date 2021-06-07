/*
ID: gajduk_01
LANG: JAVA
TASK: palsquare
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class palsquare {
	
	public static boolean checkPalindroms ( String a ) {
		return a.equals(new StringBuffer(a).reverse().toString());
	}
	
	public static ArrayList<String> findAllSquarePalindromes ( int base ) {
		ArrayList<String> result = new ArrayList<String>();
		for ( int i = 1 ; i <= 300 ; ++i ) {
			BigInteger a = new BigInteger(Integer.toString(i,base),base);
			BigInteger b = a.multiply(a);
			if ( checkPalindroms(b.toString(base)) ) {
				result.add(a.toString(base).toUpperCase()+" "+b.toString(base).toUpperCase());
			}
		}
		return result;
	}
	
	public static void testAll () {
		for ( int i = 2 ; i <= 20 ; ++i ) {
			ArrayList<String> t = findAllSquarePalindromes(i);
			for (String string : t) {
				System.out.println(string);
			}
			System.out.println();
		}
	}
	
	public static void main ( String args[] ) throws IOException {
		 	BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		    int base = new Integer(f.readLine());
		    ArrayList<String> t = findAllSquarePalindromes(base);
			for (String string : t) {
				out.println(string);
			}
		    out.close();
		    System.exit(0);
	}

}

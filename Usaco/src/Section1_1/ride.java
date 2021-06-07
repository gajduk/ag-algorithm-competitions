package Section1_1;
/*
ID: gajduk_01
LANG: JAVA
TASK: ride
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ride {
	
	public static boolean areMatch ( String a , String b ) {
		return getStringValue(a) == getStringValue(b);
	}
	
	public static int getStringValue ( String in_string ) {
		int result = 1;
		for ( int i = 0 ; i < in_string.length() ; ++i ) {
			result = (result * (in_string.charAt(i)-'A'+1)) % 47;
		}
		return result;
	}
	
	public static void main (String [] args) throws IOException {
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
	                                                  // input file name goes above
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
	    // Use StringTokenizer vs. readLine/split -- lots faster
	    
	    out.println(areMatch(f.readLine(), f.readLine()) ? "GO" : "STAY" );                           // output result
	    out.close();                                  // close the output file
	    System.exit(0);                               // don't omit this!
	  }

}

package Section1_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class namenum {
	
	public static char valid[][] = {    { } ,
									{ } ,
									{ 'A' , 'B' , 'C' } ,
									{ 'D' , 'E' , 'F' } ,
									{ 'G' , 'H' , 'I' } ,
									{ 'J' , 'K' , 'L' } ,
									{ 'M' , 'N' , 'O' } ,
									{ 'T' , 'U' , 'V' } ,
									{ 'W' , 'X' , 'Y' } };
	
	public static boolean checkPossible ( String word , String number ) {
		if ( word.length() != number.length() ) {
			return false;
		}
		for ( int i = 0 ; i < word.length() ; ++i ) {
			boolean flag = false;
			for ( int j = 0 ; j < valid[number.charAt(i)-'1'+1].length ; ++j ) {
				if ( word.charAt(i) == valid[number.charAt(i)-'1'+1][j] ) {
					flag = true;
					break;
				}
			}
			if ( ! flag ) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main ( String args[] ) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		BufferedReader dictionary = new BufferedReader(new FileReader("dict.txt"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
	    String n = f.readLine();
	    boolean none = true;
	    while ( dictionary.ready() ) {
	    	String temp = dictionary.readLine();
	    	if ( checkPossible(temp, n) ) {
	    		none  = false;
	    		out.println(temp);
	    	}
	    }
	    if ( none ) {
	    	out.println("NONE");
	    }
	    out.close();
	    System.exit(0);
	}

}

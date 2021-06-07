/*
ID: gajduk_01
LANG: JAVA
TASK: namenum
*/

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
									{ 'P' , 'R' , 'S' } ,
									{ 'T' , 'U' , 'V' } ,
									{ 'W' , 'X' , 'Y' } };
	
	public static void test () {
		System.out.println(checkPossible("GREG","4734"));
	}
	
	public static boolean checkPossible ( String word , String number ) {
		if ( word.length() != number.length() ) {
			return false;
		}
		for ( int i = 0 ; i < word.length() ; ++i ) {
			boolean flag = false;
			for ( int j = 0 ; j < valid[number.charAt(i)-'1'+1].length ; ++j ) {
				System.out.println(valid[number.charAt(i)-'1'+1][j]+" "+number.charAt(i));
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
		//test();
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader dictionary = new BufferedReader(new FileReader("dict.txt"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
	    String n = f.readLine();
	    boolean none = true;
	    while ( dictionary.ready() ) {
	    	
	    	String temp = dictionary.readLine();
	    	//System.out.println(temp);
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

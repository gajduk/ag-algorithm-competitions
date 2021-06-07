package Chapter3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Problem1 {
			//       a   b   c   d   e   f  g  h  i  j  k  l m  n  o   p   q   r   s    t   u   v  x  w   y  z
	public static char table[] = { 'S','N','V','F','R','G','H','J','O','K','L',';',',','M','P','[','W','T','D','Y','I','B','E','C','U','X' };
	
	public static void main ( String args[] ) {
		//test_judge();
		test_file();
	}
	
	
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		String temp = in.nextLine();
    		System.out.println(transform(temp));
    	}
	}



	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("F:/Програмирање/UVA judge test/chapter3 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		System.out.println(transform(in.nextLine()));
    	}
		
	}
	
	public static int getIndex ( char key ) {
		for ( int i = 0 ; i < table.length ; ++i ) {
			if ( table[i] == key )
				return i;
		}
		return -1;
	}


	public static String transform ( String text ) {
		char result[] = new char[text.length()];
		for ( int i = 0 ; i < text.length() ; ++i ) {
			int indeks = getIndex(text.charAt(i));
			if ( indeks != -1 ) {
				result[i] = (char) (indeks+'A');
			//	System.out.println(indeks);
			}

			if ( text.charAt(i) >= '2' && text.charAt(i) <= '9' ) {
				result[i] = (char) (text.charAt(i)-1);
			}
			if ( text.charAt(i) == '1' ) {
				result[i] = '`';
			}
			if ( text.charAt(i) == ']' ) {
				result[i] = '[';
			}
			if ( text.charAt(i) == '\\' ) {
				result[i] = ']';
			}
			if ( text.charAt(i) == '\'' ) {
				result[i] = ';';
			}
			if ( text.charAt(i) == '.' ) {
				result[i] = ',';
			}
			if ( text.charAt(i) == '/' ) {
				result[i] = '.';
			}
			if ( text.charAt(i) == '-' ) {
				result[i] = '0';
			}
			if ( text.charAt(i) == '=' ) {
				result[i] = '-';
			}
			if ( text.charAt(i) == '0' ) {
				result[i] = '9';
			}
			if ( text.charAt(i) == ' ' ) {
				result[i] = ' ';
			}
		}
		return new String(result).toUpperCase();
	}
}

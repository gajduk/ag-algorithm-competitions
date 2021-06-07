package Chapter12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem1 {
	
	public static void determineCurrentPosition ( int n ) {
		int r = (int) Math.sqrt(n);
//		System.out.println("Current encirlcement "+r);
		int start_i = 0;
		int start_j = 0;
		if ( r % 2 == 0 ) {
			start_i = 0;
			start_j = r;
		}
		else {
			start_i = r;
			start_j = 0;
		}
//		System.out.println("Starting at "+start_i+" "+start_j);
		int delta = n - r*r;
		if ( delta > r ) {
			if ( start_i == 0 ) {
				System.out.println(((r-(delta-r))+1)+" "+(r+1));
			}
			else {
				System.out.println((r+1)+" "+((r-(delta-r))+1));
			}
		}
		else {
			if ( start_i == 0 ) {
				System.out.println((r+1)+" "+(delta+1));
			}
			else {
				System.out.println((delta+1)+" "+(r+1));
			}
		}
	}
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}
	
	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( true ) {
    		int n = new Integer(in.nextLine());
    		if ( n == 0 ) 
    			break;
    		--n;
    		determineCurrentPosition(n);
    	}
	}

	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		int n = new Integer(in.nextLine());
    		if ( n == 0 ) 
    			break;
    		--n;
    		determineCurrentPosition(n);
    	}
	}

}

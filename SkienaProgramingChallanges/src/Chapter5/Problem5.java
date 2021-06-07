package Chapter5;

import java.io.FileInputStream;
import java.util.Scanner;

public class Problem5 {
	
	public static void main( String args[] ) {
		test_judge();
		//test_file();
	}
	
	public static void test_judge() {
    	Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		long number = new Long(in.nextLine());
    		int winner = calculateWinner(number);
    		if ( winner == 0 )
    			System.out.println("Stan wins.");
    		else
    			System.out.println("Ollie wins.");
    	}
	}
	
	public static int calculateWinner(long number) {
		long p = 1;//to where the players are now
		while ( true ) {
			p *= 9;
			if ( p >= number ) {
				return 0;
			}
			p *= 2;
			if ( p >= number ) {
				return 1;
			}
		}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	     
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter5 problem5.txt");
	    }
    	catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		long number = new Long(in.nextLine());
    		int winner = calculateWinner(number);
    		if ( winner == 0 )
    			System.out.println("Stan wins.");
    		else
    			System.out.println("Ollie wins.");
    	}
	}
	
}

package Chapter7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem4 {
	
	public static boolean isFactorialDivisible ( int n, int m )  {
	   if ( m == 0 ) return false;
	   if ( n == 0 && m == 1 ) return true;
	   for ( int f = 2 , mf = (int) (Math.sqrt(m) + 1) ; m > n && f < mf ; ++f ) {
		    int c = 0;
		    for ( ; m % f == 0; m /= f, ++c ) { }
		    for (int nf = f; c > 0 && nf <= n; c -= n / nf, nf *= f) { }
		    if ( c > 0 ) return false;
	   }
	   return m <= n;
	}
	
	public static boolean isDivisible ( int number , int factoriel ) {
		int mod = 1;
		int i = 2;
		while ( mod != 0 ) {
			if ( i > factoriel ) 
				return false;
			mod = ( mod * (i % number) ) % number;
			i++;
		}
		return true;
	}
	
	public static void test_mod () {
		System.out.println(5%10);
	}
	
	public static void main ( String args[] ) {
	//	test_mod();
		//test_file();
		test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		int factoriel = in.nextInt();
    		int number = in.nextInt();
    		//in.nextLine();
    		if ( isFactorialDivisible(factoriel,number) ) {
    			System.out.println(number+" divides "+factoriel+"!");
    		}
    		else {
    			System.out.println(number+" does not divide "+factoriel+"!");
    		}
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem4.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		int factoriel = in.nextInt();
    		int number = in.nextInt();
    		//in.nextLine();
    		if ( isFactorialDivisible(factoriel,number) ) {
    			System.out.println(number+" divides "+factoriel+"!");
    		}
    		else {
    			System.out.println(number+" does not divide "+factoriel+"!");
    		}
    	}
	}
    
}

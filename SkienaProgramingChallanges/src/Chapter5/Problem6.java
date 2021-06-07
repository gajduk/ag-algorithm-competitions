package Chapter5;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem6 {
	
	public static long factoriel ( int number ) {
		long result = 1;
		for ( int i = 1 ; i <= number ; ++i ) {
			result *= i;
		}
		return result;
	}
	
	public static void main( String args[] ) {
		test_judge();
		//test_file();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	     
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter5 problem6.txt");
	    }
    	catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		int power = in.nextInt();
    		int k = new Integer(in.nextLine().substring(1));
    		int k_i[] = new int[k];
    		String s_k_i = in.nextLine();
    		StringTokenizer tkr = new StringTokenizer(s_k_i," ");
    		for ( int i = 0 ; i < k ; ++i ) {
    			k_i[i] = new Integer(tkr.nextToken());
    		}
    		System.out.println(getCoeficient(power, k_i));
    	}
	}
	
	public static void test_judge() {
    	Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		int power = in.nextInt();
    		int k = new Integer(in.nextLine().substring(1));
    		int k_i[] = new int[k];
    		String s_k_i = in.nextLine();
    		StringTokenizer tkr = new StringTokenizer(s_k_i," ");
    		for ( int i = 0 ; i < k ; ++i ) {
    			k_i[i] = new Integer(tkr.nextToken());
    		}
    		System.out.println(getCoeficient(power, k_i));
    	}
	}
	
	public static long getCoeficient ( int power/*n*/ , int k_i[] ) {
		long result = 1;
		result *= factoriel(power);
		// result = power!
		for ( int i = 0 ; i < k_i.length ; ++i ) {
			result /= factoriel(k_i[i]);
		}
		return result;
	}

}

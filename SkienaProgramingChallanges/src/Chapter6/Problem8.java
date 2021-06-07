package Chapter6;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem8 {
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		for ( int h = 0 ; h < num_test_cases ; ++h ) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers);
			int x = new Integer(numbers.nextToken());
			int y = new Integer(numbers.nextToken());
			System.out.println(countSteps(y-x));
		}
	}

	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem8.txt");
	    }
		catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
		Scanner in = new Scanner(inputFile);
		int num_test_cases = new Integer(in.nextLine());
		for ( int h = 0 ; h < num_test_cases ; ++h ) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers);
			int x = new Integer(numbers.nextToken());
			int y = new Integer(numbers.nextToken());
			System.out.println(countSteps(y-x));
		}
	}

	public static int countSteps( int n ) {
		if ( n == 0 )
			return 0;
		//System.out.println("n="+n);
		return ((int) Math.sqrt(n)) < Math.sqrt(n) ? (int)Math.round(Math.sqrt(n)) == (int)(Math.sqrt(n)) ? (int) Math.sqrt(n)*2 : (int) Math.sqrt(n)*2+1 : (int) Math.sqrt(n)*2-1;
	}
	
	

}

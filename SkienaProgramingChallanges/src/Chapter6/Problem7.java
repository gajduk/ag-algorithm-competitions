package Chapter6;

import java.io.FileInputStream;
import java.util.Scanner;

public class Problem7 {

	public static final int MAX_K = 700000;
	public static int f[] = new int[MAX_K];
	
	
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}

	private static void test_judge() {
		populate_f();
		Scanner in = new Scanner(System.in);
		while ( true ) {
			int n = new Integer(in.nextLine());
			if ( n == 0 )
				break;
			compute_f(n);
		}
	}
	
	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem7.txt");
	    }
		catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
	    populate_f();
		Scanner in = new Scanner(inputFile);
		while ( true ) {
			int n = new Integer(in.nextLine());
			if ( n == 0 )
				break;
			compute_f(n);
		}
	}
	
	public static void populate_f () {
		int k = 3;
		f[1] = 1;
		f[2] = 2;
		f[3] = 2;
		int m = 4;
		while ( m < MAX_K ) {
			for ( int n = 0 ; n < f[k] ; ++n ) {
				if ( m >= MAX_K )
					break;
				f[m++] = k;
			}
			k++;
		}
		
	}

	private static void compute_f(int n) {
		if ( n < MAX_K ) {
			System.out.println(f[n]);
			return;
			
		}
		int m = 1;
		int k = 1;
		while ( k < n ) {
			k += f[m++];
		}
		System.out.println(m-1);
	}
}

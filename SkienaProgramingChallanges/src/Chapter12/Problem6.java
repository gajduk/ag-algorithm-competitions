package Chapter12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem6 {
	
	public static int sqr[] = new int[6001];
	public static long fours[] = new long[101];
	public static int sum_sqr[] = new int[6001];
	
	public static int sum ( int n ) {
		return n*(n+1)/2;
	}
	
	public static void main ( String args[] ) {
		populate();
//		test_file();
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		int n = in.nextInt();
    		System.out.println(numSqr(n)+" "+numRecs(n)+" "+numCubes(n)+" "+numBoxes(n)+" "+numHCubes(n)+" "+numHBoxes(n));
    	}
	}
	
	private static void populate() {
		for ( int i = 0 ; i < sqr.length ; ++i ) {
			sqr[i] = i*i;
			
		}
		for ( int i = 0 ; i < fours.length ; ++i ) {
			fours[i] = i*i*i*i;
		}
		sum_sqr[0] = sqr[0];
		for ( int i = 1 ; i < sqr.length ; ++i ) {
			sum_sqr[i] = sqr[i]+sum_sqr[i-1];
		}
		
	}

	public static void test_file () {
		
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem6.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		int n = in.nextInt();
    		System.out.println(numSqr(n)+" "+numRecs(n)+" "+numCubes(n)+" "+numBoxes(n)+" "+numHCubes(n)+" "+numHBoxes(n));
    	}
	}

	
	
	private static int numCubes(int n) {
		return sqr[sum(n)];
	}

	private static long numBoxes(int n) {
		return ((long)sqr[sum(n)])*(sum(n)-sum(1));
	}

	private static long numHBoxes(int n) {
		long result = 0;
		for ( int i = 0 ; i < n ; ++i ) {
			result += fours[i+1]*(4+sum(2+i-1)-sum(2))*(i);
		}
		return result;
	}

	private static long numHCubes(int n) {
		++n;
		long result = 0;
		for ( int i = 0 ; i < n ; ++i ) {
			result += fours[i];
		}
		return result;
	}

	private static int numSqr(int n) {
		return sum_sqr[n];
	}

	private static int numRecs( int n ) {
		++n;
		int result = 0;
		for ( int i = 0 ; i < n ; ++i ) {
			result += (i-1)*sqr[i];
		}
		return result;
	}

}

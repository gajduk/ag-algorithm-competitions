package Chapter4;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem1 {
	
	public static void main ( String args[] ) {
		test_judges();
		//test_file();
	}
	
	public static void test_judges() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		String test_case = in.nextLine();
    		StringTokenizer tkr= new StringTokenizer(test_case);
    		int relatives[] = new int[tkr.countTokens()-1];
    		tkr.nextToken();
    		int h = 0;
    		while ( tkr.hasMoreTokens() ) {
    			relatives[h++] = new Integer(tkr.nextToken());
    		}
    		System.out.print(getMinimumDistanceWithMedian(relatives));
    		System.out.println();
    	}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		String test_case = in.nextLine();
    		StringTokenizer tkr= new StringTokenizer(test_case);
    		int relatives[] = new int[tkr.countTokens()-1];
    		tkr.nextToken();
    		int h = 0;
    		while ( tkr.hasMoreTokens() ) {
    			relatives[h++] = new Integer(tkr.nextToken());
    		}
    		System.out.print(getMinimumDistanceWithMedian(relatives));
    		System.out.println();
    	}
	}

	public static final int MAX_STREET_NUMBER = 30000;
	
	
	
	public static long getMinimumDistanceWithMedian ( int relatives[] ) {
		Arrays.sort(relatives);
		return sumUpDistance( relatives);
		
	}

	public static long sumUpDistance( int[] relatives) {
		long sum = 0;
		for ( int j = 0 ; j < relatives.length ; ++j ) {
			int t = relatives[relatives.length/2]-relatives[j];
			sum += Math.abs(t);
		}
		return sum;
	}
	
	
	
}

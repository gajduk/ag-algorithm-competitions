package Chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem7 {
	/*
	public static final int MAX_TURTLES = 201;
	
	public static String turtles_ordered[] = new String[MAX_TURTLES];
	public static String turtles[] = new String[MAX_TURTLES];
	public static int num_turtles = 0;
	public static int subsequence_length[] = new int[MAX_TURTLES];
	public static int starting_ordering[] = new int[MAX_TURTLES];
	
	
	public static void main ( String args[] ) {
		test_file();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
		try	 {
     		inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem7.txt");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
			return;
		}
		Scanner in = new Scanner(inputFile);
		int num_test_cases = new Integer(in.nextLine());
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			num_turtles = new Integer(in.nextLine());
			for ( int m = 0 ; m < num_turtles ; ++m ) {
				turtles[m] = in.nextLine();
			}
			for ( int m = 0 ; m < num_turtles ; ++m ) {
				turtles_ordered[m] = in.nextLine();
			}
			order();
			cleanAllData();
		}
	}
	
	public static void cleanAllData() {
		turtles_ordered = new String[MAX_TURTLES];
		turtles = new String[MAX_TURTLES];
		num_turtles = 0;
		subsequence_length = new int[MAX_TURTLES];
		starting_ordering = new int[MAX_TURTLES];
	}
	
	public static void order() {
		for ( int m = 0 ; m < num_turtles ; ++m ) {
			starting_ordering[m] = getIndex(turtles[m]);
		}
		int start = findSmallestNumberOfLongestSubsequentSubsequence();
		for ( int l = start-1 ; l >= 0 ; --l ) {
			System.out.println(turtles_ordered[l]);
		}
		System.out.println();
	}

	public static int findSmallestNumberOfLongestSubsequentSubsequence() {
		for ( int k = 0 ; k < num_turtles ; ++k ) {
			if ( starting_ordering[k] == 0 ) {
				subsequence_length[0] = 1;
			}
			else {
				subsequence_length[starting_ordering[k]] = subsequence_length[starting_ordering[k]-1]+1;
			}
		}
		int max = -1;
		int max_index = -1;
		for ( int k = 0 ; k < num_turtles ; ++k ) {
			if ( subsequence_length[k] > max ) {
				max_index = k;
				max = subsequence_length[k];
			}
		}
		while ( subsequence_length[max_index] > 1 )
			--max_index;
		return max_index;
	}

	public static int getIndex(String string) {
		for (int i = 0; i < num_turtles ; i++) {
			if ( string.equals(turtles_ordered[i]) )
				return i;
		}
		return 0;
	}
	*/
	
	public static final int MAX_TURTLES = 201;
	
	
	public static String turtles_ordered[] = new String[MAX_TURTLES];
	public static String turtles[] = new String[MAX_TURTLES];
	public static int num_turtles = 0;
	public static int starting_ordering[] = new int[MAX_TURTLES];
	
	public static void test_pushBack() {
		//starting_ordering = { 2,4,5,6,7 };
		num_turtles = 5;
		pushBack(2);
		System.out.println();
	}
	
	public static void main ( String args[] ) {
		//test_pushBack();
		//test_file();
		test_judge();
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			num_turtles = new Integer(in.nextLine());
			for ( int m = 0 ; m < num_turtles ; ++m ) {
				turtles[m] = in.nextLine();
			}
			for ( int m = 0 ; m < num_turtles ; ++m ) {
				turtles_ordered[m] = in.nextLine();
			}
			order();
			//if ( k < num_test_cases-1 ) 
				System.out.println();
			cleanAllData();
		}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
		try	 {
     		inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem7.txt");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
			return;
		}
		Scanner in = new Scanner(inputFile);
		int num_test_cases = new Integer(in.nextLine());
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			num_turtles = new Integer(in.nextLine());
			for ( int m = 0 ; m < num_turtles ; ++m ) {
				turtles[m] = in.nextLine();
			}
			for ( int m = 0 ; m < num_turtles ; ++m ) {
				turtles_ordered[m] = in.nextLine();
			}
			order();
			if ( k < num_test_cases-1 ) 
				System.out.println();
			cleanAllData();
		}
	}
	
	private static void cleanAllData() {
		turtles_ordered = new String[MAX_TURTLES];
		turtles = new String[MAX_TURTLES];
		num_turtles = 0;
		starting_ordering = new int[MAX_TURTLES];
		
	}

	public static int getIndex(String string) {
		for (int i = 0; i < num_turtles ; i++) {
			if ( string.equals(turtles_ordered[i]) )
				return i;
		}
		return 0;
	}
	
	public static void order() {
		for ( int m = 0 ; m < num_turtles ; ++m ) {
			starting_ordering[m] = getIndex(turtles[m]);
		}
		while ( true  ) {
			int last = num_turtles;
			int i = num_turtles-1;
			while ( i >= 0 ) {
				if ( starting_ordering[i]+1 == last ) {
					--last;
				}
				--i;
			}
			if ( last == 0 )
				break;
			crawlOut(last-1);
		}
	}
	
	public static void pushBack ( int index ) {
		int temp = starting_ordering[index];
		for ( int k = index ; k < num_turtles-1 ; ++k ) {
			starting_ordering[k] = starting_ordering[k+1];
		}
		starting_ordering[num_turtles-1] = temp;
	}
	
	public static void pushFront ( int index ) {
		int temp = starting_ordering[index];
		for ( int k = index ; k >= 1 ; --k ) {
			starting_ordering[k] = starting_ordering[k-1];
		}
		starting_ordering[0] = temp;
	}

	public static void crawlOut(int possition ) {
		for ( int l = 0 ; l < num_turtles ; ++l ) {
			if ( starting_ordering[l] == possition ) {
				System.out.println(turtles_ordered[starting_ordering[l]]);
				pushFront(l);
				return;
			}	
		}
	}

	public static boolean isOrdered( int[] array ) {
		for ( int i = 0 ; i < array.length-1 ; ++i ) {
			if ( array[i] > array[i+1] ) {
				return false;
			}
		}
		return true;
	}
	
};


	
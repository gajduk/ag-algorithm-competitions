package Chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem5 {
	public static boolean woodstick[];
	public static int mincost[][];
	public static int l;
	public static int current_cuts[] = new int[1000];
	public static int num_current_cuts = 0;
	
	public static void populateWoodstick ( int cuts[] ) {
		woodstick = new boolean[l+1];
		for ( int i = 0 ; i < cuts.length ; ++i ) {
			woodstick[cuts[i]] = true;
		}
	}
		
	public static int getMinimumCost ( int cuts[] ) {
		int t[][] = new int[cuts.length+1][cuts.length];
		for ( int i = 0 ; i < cuts.length ; ++i ) {
			t[i][i] = 0;
		}
		for ( int i = 0 ; i < cuts.length-1 ; ++i ) {
			t[i][i+1] = 0;
		}
		for ( int k = 2 ; k < cuts.length ; ++k ) {
			for ( int i = 0 ; i < cuts.length-k ; ++i ) {
				t[i][i+k] = Integer.MAX_VALUE;
				for ( int s = 1 ; s < k ; ++s ) {
					t[i][i+k] = Math.min(t[i][i+k],t[i][i+s]+t[i+s][i+k]+cuts[i+k]-cuts[i]);
				}
			}
		}
		return t[0][cuts.length-1];
	}
		
	public static int calculateMinCost ( int cuts[] ) {
		mincost = new int[l+1][l+1];
		populateWoodstick(cuts);
		return calculateMinCostRecursive(0,l);
	}

	public static int calculateMinCostRecursive( int start, int end ) {
		//System.out.println(start+" "+end);
		int current_cuts[] = new int[1000];
		int num_current_cuts = 0;
		for ( int i = start ; i <= end ; ++i ) {
			if ( woodstick[i] ) {
				current_cuts[num_current_cuts++] = i;
			}
		}
		/*
		System.out.print("The cuts i need to consider are:");
		for ( int i = 0 ; i < num_current_cuts ; ++i ) {
			System.out.print("  "+current_cuts[i]);
		}
		System.out.println();
		*/
		if ( num_current_cuts == 0 ) {
			return 0;
		}
		if ( num_current_cuts == 1 ) {
			return end-start;
		}
		int min = Integer.MAX_VALUE;
		int min_index = 0;
		for ( int i = 0 ; i < num_current_cuts ; ++i ) {
			woodstick[current_cuts[i]] = false;
			int t = calculateMinCostRecursive(start,current_cuts[i])+calculateMinCostRecursive(current_cuts[i],end)+(end-start);
			woodstick[current_cuts[i]] = true;
			if ( t < min ) {
				min = t;
				min_index = i;
			}
		}
		return min;
	}
	
	public static void main ( String args[] ) {
		test_file();
//		test_judge();
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( true ) {
    		l = in.nextInt();
    		if ( l == 0 )
    			break;
    		int num_cuts = in.nextInt();
    		int cuts[] = new int[num_cuts+2];
    		for ( int i = 1 ; i <= num_cuts ; ++i ) {
    			cuts[i] = in.nextInt();
    		}
    		cuts[0] = 0;
    		cuts[cuts.length-1] = l;
    		System.out.println("The minimum cutting is "+getMinimumCost(cuts)+".");
    	}
	}

	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter11 problem5.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		l = in.nextInt();
    		if ( l == 0 )
    			break;
    		int num_cuts = in.nextInt();
    		int cuts[] = new int[num_cuts+2];
    		for ( int i = 1 ; i <= num_cuts ; ++i ) {
    			cuts[i] = in.nextInt();
    		}
    		cuts[0] = 0;
    		cuts[cuts.length-1] = l;
    		System.out.println("The minimum cutting is "+getMinimumCost(cuts)+".");
    	}
	}

}

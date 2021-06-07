package Section1_5;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class CheckerChallange {
	public static int n;
	public static boolean is_row_free[];
	public static boolean is_col_free[];
	public static boolean is_dia45_free[];
	public static boolean is_dia135_free[];
	public static int num_ways = 0;
	public static final int max_ways = 3;
	//public static LinkedList<String> list=new LinkedList<String>();
	
	
	/*
	public static int findAllPermutations () {
		int col = 0;
		int sum = 0;
		int permutation[] = new int[n];
		for ( int k = 0 ; k < n ; ++k ) {
			is_row_free[k] = true;
			is_col_free[k] = true;
			is_dia45_free[k] = true;
			is_dia135_free[k] = true;
			is_dia45_free[n*2-k-2] = true;
			is_dia135_free[n*2-k-2] = true;
		}
		for ( int k = 0 ; k < n ; ++k ) {
			//System.out.println(col+" "+k);
			if ( is_col_free[col] && is_row_free[k] && is_dia45_free[getDia45(k,col)] && is_dia135_free[getDia135(k,col)] ) {
				is_col_free[col] = false;
				is_row_free[k] = false;
				is_dia45_free[getDia45(k,col)] = false;
				is_dia135_free[getDia135(k,col)] = false;
				permutation[col] = k;
				sum += findAllPermutationsRecursive(col+1,permutation);
				is_col_free[col] = true;
				is_row_free[k] = true;
				is_dia45_free[getDia45(k,col)] = true;
				is_dia135_free[getDia135(k,col)] = true;
			}
		}
		return sum;
	}
	
	
	
	public static int findAllPermutationsRecursive ( int col , int[] permutation ) {
		int sum = 0;
		if ( col == n ) {
			print(permutation);
			return 1;
		}
		for ( int k = 0 ; k < n ; ++k ) {
			//System.out.println(col+" "+k);
			if ( is_col_free[col] && is_row_free[k] && is_dia45_free[getDia45(k,col)] && is_dia135_free[getDia135(k,col)] ) {
				is_col_free[col] = false;
				is_row_free[k] = false;
				is_dia45_free[getDia45(k,col)] = false;
				is_dia135_free[getDia135(k,col)] = false;
				permutation[col] = k;
				sum += findAllPermutationsRecursive(col+1,permutation);
				is_col_free[col] = true;
				is_row_free[k] = true;
				is_dia45_free[getDia45(k,col)] = true;
				is_dia135_free[getDia135(k,col)] = true;
			}
		}
		return sum;
	}
	*/
	
	public static int findAllPermutationsRows () {
		int row = 0;
		int sum = 0;
		int permutation[] = new int[n];
		for ( int k = 0 ; k < n ; ++k ) {
			is_row_free[k] = true;
			is_col_free[k] = true;
			is_dia45_free[k] = true;
			is_dia135_free[k] = true;
			is_dia45_free[n*2-k-2] = true;
			is_dia135_free[n*2-k-2] = true;
		}
		for ( int k = 0 ; k < n ; ++k ) {
			//System.out.println(col+" "+k);
			if ( is_col_free[k] && is_row_free[row] && is_dia45_free[getDia45(row,k)] && is_dia135_free[getDia135(row,k)] ) {
				is_col_free[k] = false;
				is_row_free[row] = false;
				is_dia45_free[getDia45(row,k)] = false;
				is_dia135_free[getDia135(row,k)] = false;
				permutation[row] = k;
				sum += findAllPermutationsRowsRecursive(row+1,permutation);
				is_col_free[k] = true;
				is_row_free[row] = true;
				is_dia45_free[getDia45(row,k)] = true;
				is_dia135_free[getDia135(row,k)] = true;
			}
		}
		return sum;
	}
	
	public static int findAllPermutationsRowsRecursive ( int row , int[] permutation ) {
		int sum = 0;
		if ( row == n ) {
			print(permutation);
			return 1;
		}
		for ( int k = 0 ; k < n ; ++k ) {
			//System.out.println(col+" "+k);
			if ( is_col_free[k] && is_row_free[row] && is_dia45_free[getDia45(row,k)] && is_dia135_free[getDia135(row,k)] ) {
				is_col_free[k] = false;
				is_row_free[row] = false;
				is_dia45_free[getDia45(row,k)] = false;
				is_dia135_free[getDia135(row,k)] = false;
				permutation[row] = k;
				sum += findAllPermutationsRowsRecursive(row+1,permutation);
				is_col_free[k] = true;
				is_row_free[row] = true;
				is_dia45_free[getDia45(row,k)] = true;
				is_dia135_free[getDia135(row,k)] = true;
			}
		}
		return sum;
	}

	private static void print(int[] permutation) {
		num_ways++;
		if ( num_ways > max_ways ) {
			return;
		}
		for ( int k = 0 ; k < permutation.length ; ++k ) {
			System.out.print((permutation[k]+1)+" ");
		}
		System.out.println();
		//if(temp.charAt(0)=='1')list.add(temp.substring(0,temp.length()-1));
	}

	private static int getDia135(int x, int y) {
		return x+y;
	}

	private static int getDia45(int x, int y) {
		int i = Math.abs(x-y);
		if ( y >= x ) {
			return i;
		}
		else {
			return n+i-1;
		}
	}
	
	
	public static void main ( String args[] ) {
		
		test_judge();
		
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		n = new Integer(in.nextLine());
		long start = System.currentTimeMillis();
		is_row_free = new boolean[n];
		is_col_free = new boolean[n];
		is_dia45_free = new boolean[n*2-1];
		is_dia135_free = new boolean[n*2-1];
		int total_num_ways = findAllPermutationsRows();
		System.out.println(total_num_ways);
		long end = System.currentTimeMillis();
		System.out.println("Run time in milisec "+(end-start));
	}

}

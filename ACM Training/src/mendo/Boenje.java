package mendo;

import java.util.Scanner;

public class Boenje {
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int cost[][] = new int[n][3];
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int k = 0 ; k < 3 ; ++k ) {
				cost[i][k] = in.nextInt();
			}
		}
		int min = Integer.MAX_VALUE;
		for ( int i = 0 ; i < 3 ; ++i ) {
			int t = minCostR(cost,n-2,i,i)+cost[n-1][i]; if ( t < min ) min = t;
		}
		System.out.println(min);
	}

	private static int minCostR(int[][] cost, int n, int fi, int f0 ) {
		if ( n == 0 ) {
			int min = 0;
			if ( fi == f0 ) {
				min = Math.min(cost[n][(f0+1)%3], cost[n][(f0+2)%3]);
			}
			else {
				if ( (fi+1)%3 == f0 ) min = cost[n][(fi+2)%3];
				else  min = cost[n][(fi+1)%3];
			}
			return min;
		}
		int min = Integer.MAX_VALUE;
		for ( int i = 0 ; i < 3 ; ++i ) {
			if ( fi != i ) {
				int t = cost[n][i]+minCostR(cost, n-1, i, f0);
				if ( t < min ) min = t;
			}
		}
		return min;
	}
	
	/*
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int cost[][] = new int[n][3];
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int k = 0 ; k < 3 ; ++k ) {
				cost[i][k] = in.nextInt();
			}
		}
		int min_cost[][][] = new int[n][3][3];
		for ( int i = 0 ; i < 3 ; ++i ) {
			for ( int k = 0 ; k < 3 ; ++k ) {
				int min = Integer.MAX_VALUE;
				if ( i == k ) {
					min = Math.min(cost[0][(k+1)%3], cost[0][(k+2)%3]);
				}
				else {
					if ( (i+1)%3 == k ) min = cost[0][(i+2)%3];
					else  min = cost[0][(i+1)%3];
				}
				min_cost[0][i][k] = min;
			}
		}
		for ( int e = 1 ; e < n-1 ; ++e ) {
			for ( int i = 0 ; i < 3 ; ++i ) {
				int min = Integer.MAX_VALUE;
				for ( int k = 0 ; k < 3 ; ++k ) { 
					if ( min_cost[e-1][i][k] < min ) min = min_cost[e-1][i][k];
				}
				for ( int k = 0 ; k < 3 ; ++k ) { 
					min_cost[e][i][k] = min+cost[e][i];
				}
			}
		}
		for ( int i = 0 ; i < 3 ; ++i ) {
			for ( int k = 0 ; k < 3 ; ++k ) {
				if ( i == k )
					min_cost[n-1][i][i] = min_cost[n-2][i][i]+cost[n-1][i];
				else
					min_cost[n-1][i][k] = Integer.MAX_VALUE;
			}
		}
		
		int min = Integer.MAX_VALUE;
		for ( int i = 0 ; i < 3 ; ++i ) {
			for ( int k = 0 ; k < 3 ; ++k ) {
				if ( min > min_cost[n-1][i][k] ) min = min_cost[n-1][i][k];
			}
		}
		System.out.println(min);
	}
	*/

}

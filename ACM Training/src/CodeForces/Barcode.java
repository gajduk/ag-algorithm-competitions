package CodeForces;

import java.util.Arrays;
import java.util.Scanner;

public class Barcode {
	
	static int table[][];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); int m = in.nextInt();
		int x = in.nextInt(); int y = in.nextInt();
		int cost[][] = new int[2][m];
		table = new int[m+1][2];
		for ( int i = 0 ; i < table.length; ++i ) {
			for ( int k = 0 ; k < table[0].length ; ++k ) {
				table[i][k] = -1;
			}
		}
		for ( int i = 0 ; i < n ; ++i ) {
			String line = in.next();
			for ( int k = 0 ; k < m ; ++k ) {
				if ( line.charAt(k) == '#' ) {
					++cost[1][k];
				}
				else {
					++cost[0][k];
				}
			}
		}
		//System.out.println(Arrays.toString(cost[0]));
		//System.out.println(Arrays.toString(cost[1]));
		System.out.println(Math.min(minCost(x,y,cost,0,0),minCost(x, y, cost,0,1)));
	}

	private static int minCost(int x, int y, int[][] cost , int col , int color ) {
		if ( col == cost[0].length ) return 0;
		if ( col > cost[0].length-1 ) return 1000000;
		if ( table[col][color] == -1 ) {
			int sum = 0;
			for ( int i = 0 ; i < x ; ++i ) {
				if ( i+col >= cost[0].length ) return 1000000;
				sum += cost[color][i+col];
			}
			int min = 1000000;
			for ( int i = x ; i <= y ; ++i ) {
				int t = minCost(x, y, cost, i+col, 1-color);
				int total = sum + t;
				if ( total < min ) min = total;
				if ( i+col >= cost[0].length ) break;
				sum += cost[color][i+col];
			}
			table[col][color] =  min;
		}
		return table[col][color];
	}

}

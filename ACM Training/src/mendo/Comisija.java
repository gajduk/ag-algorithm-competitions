package mendo;

import java.util.Scanner;

public class Comisija {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num_people = in.nextInt();
		int num_n = in.nextInt();
		int natprevari[] = new int[num_n];
		for ( int i = 0 ; i < num_n ; ++i ) {
			natprevari[i] = in.nextInt();
		}
		System.out.println(max(natprevari,0,num_people));
		
	}

	private static int max(int[] natprevari, int i , int n ) {
		if ( n == 0 && natprevari.length == i ) return 0;
		if ( n < 0 || i >  natprevari.length ) return Integer.MAX_VALUE;
		int res = Integer.MAX_VALUE;
		int sum = 0;
		for ( int j = i+1 ; j <= natprevari.length ; ++j ) {
			sum += natprevari[j-1];
			int t = max(natprevari,j,n-1);
			int m = Math.max(sum,t);
			if ( m < res ) res = m;
		}
		return res;
	}
	
}

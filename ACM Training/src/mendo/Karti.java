package mendo;

import java.util.Arrays;
import java.util.Scanner;

public class Karti {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int karti[] = new int[n];
		for ( int i = 0 ; i < n ; ++i ) karti[i] = in.nextInt();
		int karti_c[] = Arrays.copyOf(karti, n);
		int min = check(1,karti,k);
		int t = check(0,karti_c,k);
		//System.out.println(t+" "+min);
		if ( t < min ) min = t;
		if ( min == Integer.MAX_VALUE ) min = -1;
		System.out.println(min);
	}

	private static int check(int c, int[] karti, int k) {
		int res = 0;
		for ( int i = 0; i < karti.length-k+1 ; ++i ) {
			if ( karti[i] == c ) {
				for ( int q = i ; q < k+i ; ++q ) 
					karti[q] = (karti[q]+1)&1;
				++res;
			}
		}
		for ( int i = 0 ; i < karti.length ; ++i ) {
			if ( karti[i] == c ) return Integer.MAX_VALUE;
		}
		return res;
	}

}

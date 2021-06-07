package mendo;

import java.util.Scanner;

public class Ocenuvanje {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long k = in.nextLong();
		long s[] = new long[n];
		long max = 0;
		long min = Integer.MAX_VALUE;
		for ( int i = 0 ; i < s.length ; ++i ) {
			s[i] = in.nextLong();
			if ( max < s[i] ) max = s[i];
			if ( min > s[i] ) min = s[i];
		}
		long lo = 0;
		long hi = k*max;
		while ( lo < hi ) {
			long mid = lo+(hi-lo)/2;
			if ( check(mid,s,k) ) lo = mid+1;
			else hi = mid;
		}
		System.out.println(lo+(hi-lo)/2);
	}

	private static boolean check(long mid, long[] s,long k) {
		for ( int i = 0 ; i < s.length ; ++i ) k -= mid/s[i];
		return k > 0;	
	}
}

package mendo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Vino {
	
	static int min = Integer.MAX_VALUE;
	static int k;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int h = in.nextInt();
		k = in.nextInt();
		boolean have[] = new boolean[n];
		for ( int w = 0 ; w < h ; ++w ) {
			have[in.nextInt()-1] = true;
		}
		int prices_now[] = new int[n];
		int prices_2014[] = new int[n];
		for ( int w = 0 ; w < n ; ++w ) {
			prices_now[w] = in.nextInt();
		}
		for ( int w = 0 ; w < n ; ++w ) {
			prices_2014[w] = in.nextInt();
		}
		int sums1[] = new int[1<<(n/2)];
		int min_costs1[] = new int[1<<(n/2)];
		int sums2[] = new int[1<<((n+1)/2)];
		int min_costs2[] = new int[1<<((n+1)/2)];
		int n1 = getPairsKRes(have, prices_now, prices_2014, 0, n/2, sums1, min_costs1);
		int n2 = getPairsKRes(have, prices_now, prices_2014, n/2, n, sums2, min_costs2);
		int idx2 = 0;
		for ( int i = n1-1 ; i >= 0  ; --i ) {
			while ( idx2 < n2 && sums2[idx2]+sums1[i]<k ) ++idx2;
			if ( idx2 == n2 ) break;
			int cost = min_costs1[i]+min_costs2[idx2];
			if ( cost < min ) min = cost;
		}
		int idx1 = 0;
		for ( int i = n2-1 ; i >= 0  ; --i ) {
			while ( idx1 < n1 && sums1[idx1]+sums2[i]<k ) ++idx1;
			if ( idx1 == n1 ) break;
			int cost = min_costs2[i]+min_costs1[idx1];
			if ( cost < min ) min = cost;
		}
//		System.out.println(Arrays.toString(sums1));
//		System.out.println(Arrays.toString(min_costs1));
//		System.out.println(Arrays.toString(sums2));
//		System.out.println(Arrays.toString(min_costs2));
		if ( min < 0 ) min = 0;
		if ( min == Integer.MAX_VALUE ) min = -1;
		System.out.println(min);
		
	}
	
	
	private static int getPairsKRes ( boolean[] have, int[] prices_now,int[] prices_2014 , int lo , int hi , int sums[] , int min_costs[] ) {
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		int d = hi-lo;
		for ( int i = 0 ; i < (1<<d) ; ++i ) {
			int sum = 0;
			int cost = 0;
			for ( int j = 0 ; j < d ; ++j ) {
				if ( ((1<<j)&i) > 0 ) {
					cost += have[lo+j]?0:prices_now[lo+j]; 
					sum += prices_2014[lo+j];
				}
				else {
					cost -= !have[lo+j]?0:prices_now[lo+j]; 
				}
			}
			Integer qwe = m.get(sum);
			if ( qwe == null || qwe > cost )
				m.put(sum, cost);
		}
		int i = 0;
		for ( Integer e : m.keySet() ) {
			sums[i++] = e;
		}
		Arrays.sort(sums,0,i);
		min_costs[i-1] =  m.get(sums[i-1]);
		if ( sums[i-1] >= k && min > m.get(sums[i-1]) ) min = m.get(sums[i-1]);
		for ( int j = i-2 ; j >= 0 ; --j ) {
			if ( sums[j] >= k && min > m.get(sums[j]) ) min = m.get(sums[j]);
			min_costs[j] = Math.min(min_costs[j+1],m.get(sums[j]));
			
		}
		
		return i;
	}

}

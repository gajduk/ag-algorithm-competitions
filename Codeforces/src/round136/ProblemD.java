package round136;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProblemD {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int a[] = new int[n];
		int nums[] = new int[n+1];
		for ( int i = 0 ; i < n ; ++i ) {
			a[i] = in.nextInt();
			if ( a[i] <= n ) {
				++nums[a[i]];
			}
		}
		boolean flags[] = new boolean[n+1];
		for ( int i = 0 ; i <= n ; ++i ) {
			if ( nums[i] >= i ) flags[i] = true; 
		}
		HashMap<Integer,Integer> hm[] = new HashMap[n+1];
		hm[0] = new HashMap<Integer, Integer>();
		for ( int i = 1 ; i <= n ; ++i ) {
			hm[i] = new HashMap<Integer, Integer>(hm[i-1]);
			if ( a[i-1] <= n && flags[a[i-1]] ) {
				Integer c = hm[i].get(a[i-1]);
				if ( c == null ) {
					c = 0;
				}
				++c;
				hm[i].put(a[i-1], c);
			}
		}
		for ( int w = 0 ; w < m ; ++w ) {
			int l = in.nextInt()-1;
			int r = in.nextInt();
			int count = 0;
			for ( Map.Entry<Integer,Integer> e : hm[r].entrySet() ) {
				int rk = e.getValue();
				if ( rk >= e.getKey() ) {
					Integer lk = hm[l].get(e.getKey());
					if ( lk == null ) lk = 0;
					if ( rk-lk == e.getKey() ) ++count;
				}
			}
			System.out.println(count);
		}
	}
	
	/*
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int MAX = 10001;
		int n = in.nextInt();
		int m = in.nextInt();
		int a[] = new int[n];
		int sqrtn = (int) Math.sqrt(n);
		int pre[][] = new int[sqrtn][MAX];
		for ( int i = 0 ; i < n ; ++i ) {
			a[i] = in.nextInt();
			if ( a[i] < MAX ) {
				++pre[i/sqrtn][a[i]];
			}
		}
		for ( int w = 0 ; w < m ; ++w ) {
			int l = in.nextInt()-1;
			int r = in.nextInt();
			int nums[] = new int[MAX];
			int ls = l/sqrtn+1;
			int rs = l/sqrtn;
			for ( int i = l ; i < ls ; ++i ) {
				if ( a[i] < MAX ) nums[a[i]]++;
			}
			for ( int i = rs+1 ; i < r ; ++i ) {
				if ( a[i] < MAX ) nums[a[i]]++;
			}
			for ( int i = l/sqrtn+1 ; i < r/sqrtn+1 ; ++i ) {
				if ( a[i] < MAX ) nums[a[i]]++;
			}
		}
	}
	*/
}

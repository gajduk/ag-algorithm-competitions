package mendo;

import java.util.Arrays;
import java.util.Scanner;

class Pair implements Comparable<Pair>{
	public int idx;
	public long val;
	public Pair(int idx, long val) {
		super();
		this.idx = idx;
		this.val = val-1L;
	}
	@Override
	public int compareTo(Pair o) {
		if ( val == o.val ) return idx-o.idx;
		return val>o.val?1:-1;
	}
	
	@Override
	public String toString() {
		return "("+idx+","+val+")";
	}
}

public class Ocenuvanje2 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long k = in.nextLong();
		Pair a[] = new Pair[n];
		long sum = 0;
		for ( int i = 0 ; i < a.length ; i++ ) {
			a[i] = new Pair(i+1,in.nextLong());
			sum += (a[i].val+1);
		}		
		if ( sum < k ) {
			System.out.println(0);
			return;
		}
		Arrays.sort(a);
		long res = n;
		long total_sofar = a[0].val*res+a[0].idx;
		if ( total_sofar >= k ) {
			System.out.println(res);
			return;
		}
		for ( int i = 1 ; i < a.length ; i++ ) {
			total_sofar += (a[i].val-a[i-1].val)*--res+a[i].idx-a[i-1].idx;
			if ( total_sofar >= k ) {
				System.out.println(res);
				return;
			}
		}
		System.out.println(1);
	}

}

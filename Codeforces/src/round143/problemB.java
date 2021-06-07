package round143;

import java.util.Arrays;
import java.util.Scanner;

public class problemB {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int no = in.nextInt();
		Integer a[] = new Integer[n];
		in.nextLine();int i = 0;
		for ( String s : in.nextLine().split(" ") ) {
			a[i++] = new Integer(s);
		}
		Arrays.sort(a);
		long sum[] = new long[n+1];
		sum[0] = 0;
		for ( int k = 1 ; k <= n ; ++k ) sum[k] = sum[k-1]+a[k-1];
		int max = 1; int idx = 0;
		for ( int k = 0 ; k < a.length ; ++k ) {
			int occ = bs(a,sum,k,no);
			//System.out.println(a[k]+" "+occ);
			if ( occ > max ) {
				max = occ;
				idx = k;
			}
		}
		System.out.println(max+" "+a[idx]);
	}

	private static int bs(Integer[] a, long[] sum, int k,int no) {
		//System.out.println();
		//System.out.println(a[k]);
		//System.out.println();
		int hi = k+1;
		int lo = 1;
		while ( lo < hi ) {
			int mid = lo+(hi-lo+1)/2;
			//System.out.println(lo+" "+mid+" "+hi);
			//System.out.println((mid*a[k])+" "+(sum[k+1]-sum[k-mid+1]));
			if ( f(a,sum,k,no,mid) ) hi = mid-1;
			else lo = mid;
			//System.out.println(lo+" "+mid+" "+hi);
		}
		return lo;
	}

	private static boolean f(Integer[] a, long[] sum, int k, int no, int mid) {
		return ((((long)mid)*a[k]) - (sum[k+1]-sum[k-mid+1])) > ((long)no);
	}
}

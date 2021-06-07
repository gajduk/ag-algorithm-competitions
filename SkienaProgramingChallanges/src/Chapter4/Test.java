package Chapter4;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
	
	public static void main ( String args[] ) {
		int a[];
		Scanner in = new Scanner(System.in);
		StringTokenizer tkr = new StringTokenizer(in.nextLine());
		a = new int[tkr.countTokens()];
		for ( int i = 0 ; i < a.length ; ++i ) {
			a[i] = new Integer(tkr.nextToken());
		}
		while ( true ) {
			int n = in.nextInt();
			if ( n == 0 ) {
				break;
			}
			reverse(a,n);
			System.out.println(Arrays.toString(a));
		}
		
	}

	private static void reverse(int[] a, int n) {
		--n;
		for ( int i = n ; i < n+(a.length-n)/2 ; ++i ) {
			int t = a[i];
			a[i] = a[a.length-(i-n)-1];
			a[a.length-(i-n)-1] = t;
		}
	}

}

package mendo;
import java.util.Arrays;
import java.util.Scanner;


public class Oro {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		int m = in.nextInt();
		++m;
		boolean pos[][] = new boolean[2][m];
		int n = in.nextInt();
		int idx = 0;
		pos[idx][p] = true;
		for ( int w = 0 ; w < n ; ++w ) {
			int k = in.nextInt();
			int ot = (idx+1)%2;
			Arrays.fill(pos[ot], false);
//			print(pos[idx]);
			for ( int i = 0 ; i < m ; ++i ) {
				if ( i >= k )
					pos[ot][i-k] = pos[ot][i-k]||pos[idx][i];
				if ( i+k < m ) 
					pos[ot][i+k] = pos[ot][i+k]||pos[idx][i];
			}
			idx = ot;
		}
		for ( int i = m-1 ; i >= 0 ; --i ) {
			if ( pos[idx][i] )  {
				System.out.println(i); return;
			}
		}
		System.out.println(-1);
	}

	private static void print(boolean[] bs) {
		for ( int i = 0 ; i < bs.length ; ++i ) {
			System.out.print(bs[i]?"1":"0");
		}
		System.out.println();
	}

}

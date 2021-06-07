package round142;

import java.util.Scanner;

public class ProblemA {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int n = in.nextInt();
		int ds[] = new int[n];
		int is[] = new int[n];
		boolean killed[] = new boolean[n];
		for ( int i = 0 ; i < n ; ++i ) {
			ds[i] = in.nextInt();
			is[i] = in.nextInt();
		}
		int c = 0;
		while ( true ) {
			int k = 0;
			while ( k < n ) {
				if ( !killed[k] && ds[k] < s ) break;
				++k;
			}
			if ( k == n ) {
				if ( c == n ) System.out.println("YES");
				else System.out.println("NO");
				return; }
			killed[k] = true;
			s += is[k];
			++c;
		}
		
	}

}

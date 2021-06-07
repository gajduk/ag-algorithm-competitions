package round142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemC {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String nums[] = in.readLine().split(" ");
		int n = Integer.parseInt(nums[0]);
		int m = Integer.parseInt(nums[1]);
		String table[] = new String[n];
		for ( int i = 0 ; i < n ; ++i ) {
			table[i] = in.readLine();
		}
		int min_dist[][] = new int[n][m];
		for ( int i = 0 ; i < n ; ++i ) {
			int last = -(m+2);
			for ( int k = 0 ; k < m ; ++k ) {
				if ( table[i].charAt(k) == '1' ) last = k;
				min_dist[i][k] = k-last;
			}
		}
		for ( int i = 0 ; i < n ; ++i ) {
			int last = (m+m+1);
			for ( int k = m-1 ; k >= 0 ; --k ) {
				if ( table[i].charAt(k) == '1' ) last = k;
				min_dist[i][k] = min_dist[i][k]<(last-k)?min_dist[i][k]:(last-k);
			}
		}
		/*
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int k = 0 ; k < m ; ++k ) {
				System.out.print(min_dist[i][k]+" ");
			}
			System.out.println();
		}
		*/
		int min = m*m+1;
		for ( int k = 0 ; k < m ; ++k ) {
			int cost = 0;
			for ( int i = 0 ; i < n ; ++i ) {
				if ( min_dist[i][k] >= m ) {
					cost += m*m+1; break;
				}
				cost += min_dist[i][k];
			}
			min = min<cost?min:cost;
		}
		if ( min < m*m+1 ) {
			System.out.println(min);
		}
		else {
			System.out.println(-1);
		}
	}

}

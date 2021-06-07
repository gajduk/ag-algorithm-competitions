import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class AL {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m[][] = new int[5][n];
		for ( int i = 0 ; i < 5 ; ++i ) {
			for ( int k = 0 ; k < n ; ++k ) {
				m[i][k] = in.nextInt();
			}
		}
		int next[][] = new int[n+1][5];
		int count[] = new int[n+1];
		for ( int i = 0 ; i < 5 ; ++i ) {
			for ( int k = 0 ; k < n-1 ; ++k ) {
				next[m[i][k]][count[m[i][k]]++] = m[i][k+1];
			}
		}
		int prev[] = new int[n+1];
		for ( int k = 1 ; k <= n ; ++k ) {
			for ( int i = 0 ; i < count[k] ; ++i ) {
				prev[next[k][i]]++;
			}
		}
		int min = 1;
		for ( int k = 1 ; k <= n ; ++k ) {
			if ( prev[min] > prev[k] ) min = k;
		}
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for ( int i = 0 ; i < n ; ++i ) {
			int w = mostNext(next,min);
			queue.add(w);
			min = w;
		}
		
	}

	private static int mostNext(int[][] next, int min) {
		int count[] = new int[5];
		for ( int i = 0 ; i < 5 ; ++i ) {
			for ( int j = i+1 ; j < 5 ; ++j ) {
				if ( next[min][i] == next[min][j] ) {
					
				}
			}
		}
		return 0;
	}

}

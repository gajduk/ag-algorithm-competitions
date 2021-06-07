package CodeForces;

import java.util.Scanner;

public class RoadsInBerland {
	
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long dist[][] = new long[n][n];
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int k = 0 ; k < n ; ++k ) {
				dist[i][k] = in.nextLong();
			}
		}
		int t = in.nextInt();
		for ( int w = 0 ; w < t ; ++w ) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			long c = in.nextInt();
			for ( int i = 0 ; i < n ; ++i ) {
				for ( int j = 0 ; j < n ; ++j ) {
					dist[i][j] = Math.min(dist[i][j],Math.min(dist[i][b]+dist[a][j]+c,dist[i][a]+dist[b][j]+c));
				}
			}
		//	print(dist);
			long sum = 0;
			for ( int i = 0 ; i < n ; ++i ) {
				for ( int j = i+1 ; j < n ; ++j ) {
					sum += dist[i][j]; 
				}
			}
			System.out.println(sum);
		}
	}

	private static void print(int[][] dist) {
		for ( int i = 0 ; i < dist.length ; ++i ) {
			for ( int j = 0 ; j < dist.length ; ++j ) {
				System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
	}

}

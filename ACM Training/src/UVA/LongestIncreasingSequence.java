		package UVA;
	
	import java.util.Scanner;
	
	public class LongestIncreasingSequence {
		
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			while ( in.hasNextInt() ) {
				int m = in.nextInt();
				int n = in.nextInt();
				if ( m==0 || n == 0 ) return;
				int matrix[][] = new int[m][n];
				int lis[][] = new int[m][n];
				for ( int i = 0 ; i < m ; ++i ) {
					for ( int k = 0 ; k < n ; ++k ) {
						matrix[i][k] = in.nextInt();
					}
				}
				for ( int i = 0 ; i < m ; ++i ) {
					for ( int k = 0 ; k < n ; ++k ) {
						int l = k+1;
						while ( l < n && matrix[i][l]>matrix[i][l-1] ) ++l;
						lis[i][k] = l-k;
					}
				}
				int max = 0;
				for ( int k1 = 0 ; k1 < n ; ++k1 ) {
					for ( int k2 = k1 ; k2 < n ; ++k2 ) {
						int l =  lis[0][k1] > k2-k1?1:0;int max_rows = l;
						for ( int i = 1 ; i < m ; ++i ) {
							if ( l > 0 && matrix[i][k1] > matrix[i-1][k2] && lis[i][k1] > k2-k1 ) {
								++l;
							}
							else {
								l = lis[i][k1] > k2-k1?1:0;
							}
							if ( l > max_rows ) max_rows = l;
						}
						if ( max_rows*(k2-k1+1) > max ) max = max_rows*(k2-k1+1);
	//					System.out.println(k1+" "+k2+" "+max_rows);
					}
				}
				System.out.println(max);
				
			}
		}
	
	}

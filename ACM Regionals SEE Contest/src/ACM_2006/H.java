package ACM_2006;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class H {
	public static int above_count[][];
	public static int bellow_count[][];
	
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int num_test_cases = in.nextInt();

		above_count = new int[1000][1000];
		bellow_count = new int[1000][1000];
		for ( int t = 0 ; t < num_test_cases ; ++t ) {
			int n = in.nextInt();
			int m = in.nextInt();
			for ( int i = 0 ; i < n ; ++i ) {
				for ( int k = 0 ; k < m ; ++k ) {
					above_count[i][k] = bellow_count[i][k] = 0;
				}
			}
			int num_edges = in.nextInt();
			long count = 0;
			for ( int i = 0 ; i < num_edges ; ++i ) {
				int left = in.nextInt()-1;
				int right = in.nextInt()-1;
				for ( int k = left-1 ; k >= 0 ; --k ) {
					count += above_count[k][right];
				}
				for ( int k = left+1 ; k < n ; ++k ) {
					count += bellow_count[k][right];
				}
				for ( int k = 0 ; k < right ; ++k ) {
					++above_count[left][k];
				}
				for ( int k = right+1 ; k < n ; ++k ) {
					++bellow_count[left][k];
				}
//				neighbours[(in.nextInt()-1)].add((in.nextInt()-1));
			}
//			System.out.println(Arrays.toString(neighbours));
			
			System.out.println("Test case "+(t+1)+": "+count);
		}
	}

}

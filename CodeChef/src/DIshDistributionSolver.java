import java.util.Scanner;

/*
17
0 5
0 1
0 4
0 3
0 2
0 5
1 5
0 1
0 4
0 3
0 2
0 5
2 5
0 1
0 4
0 3
0 2
0 5
3 5
0 1
0 4
0 3
0 2
0 5
4 5
0 1
0 4
0 3
0 2
0 5
5 5
0 1
0 4
0 3
0 2
0 5
6 5
0 1
0 4
0 3
0 2
0 5
7 5
0 1
0 4
0 3
0 2
0 5
8 5
0 1
0 4
0 3
0 2
0 5
9 5
0 1
0 4
0 3
0 2
0 5
10 5
0 1
0 4
0 3
0 2
0 5
11 5
0 1
0 4
0 3
0 2
0 5
12 5
0 1
0 4
0 3
0 2
0 5
13 5
0 1
0 4
0 3
0 2
0 5
14 5
0 1
0 4
0 3
0 2
0 5
15 5
0 1
0 4
0 3
0 2
0 5
16 5
0 1
0 4
0 3
0 2
0 5
 
 
 */

public class DIshDistributionSolver {
	
	public static long num_ways = 0;
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int num_test_cases = in.nextInt();
		for ( int t = 0 ; t < num_test_cases ; ++t ) {
			int num_dishes = in.nextInt();
			int num_cooks = in.nextInt();
			int available[] = new int[num_cooks];
			num_ways = 0;
			for ( int i = 0 ; i < num_cooks ; ++i ) {
				int must = in.nextInt();
				
				int capable = in.nextInt();
				available[i] = capable-must;
				num_dishes -= must;
			}
			if ( num_dishes < 0 ) {
				System.out.println(0);
				continue;
			}
			if ( num_dishes == 0 ) {
				System.out.println(1);
				continue;
			}
			solveRecursive( 0 , available , num_dishes);
			System.out.println(num_ways);
		}
		
	}

	private static void solveRecursive( int chef_index , int[] available, int num_dishes) {
		if ( num_dishes < 0 ) {
			return;
		}
		if ( num_dishes == 0 ) {
			++num_ways;
			num_ways %= 1000000007;
			return;
		}
		if ( chef_index >= available.length ) {
			return;
		}
		for ( int i = 0 ; i <= available[chef_index] ; ++i ) {
			solveRecursive(chef_index+1, available, num_dishes-i);
		}
		
	}
}

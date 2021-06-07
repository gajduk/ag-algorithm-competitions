import java.util.Arrays;
import java.util.Scanner;


/*
1
10 9
0 15
2 2
1 2
1 2
1 2
1 2
0 2
0 3
0 3
 */

public class DishDistribution {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int num_test_cases = in.nextInt();
		for ( int t = 0 ; t < num_test_cases ; ++t ) {
			int num_dishes = in.nextInt();
			int num_cooks = in.nextInt();
			int available[] = new int[num_cooks];
			boolean exit = false;
			for ( int i = 0 ; i < num_cooks ; ++i ) {
				int must = in.nextInt();
				
				int capable = in.nextInt();
				if ( capable < must ) {
					System.out.println(0);
					exit = true;
					break;
				}
				if ( num_dishes - must < 0 ) {
					System.out.println(0);
					exit = true;
					break;
				}
				available[i] = capable-must;
				num_dishes -= must;
			}
			if ( exit ) {
				continue;
			}
			if ( num_dishes == 0 ) {
				System.out.println(1);
				continue;
			}
			
//			System.out.println(Arrays.toString(available));
			int table[][] = new int[101][101];
			table[0][num_dishes] = 1;
			for ( int i = 1 ; i <= num_cooks ; ++i ) {
				for ( int w = 0 ; w <= num_dishes ; ++w ) {
					for ( int k = 0 ; w-k >= 0 && k <= available[i-1] ; ++k ) {
						table[i][w-k] += table[i-1][w]; 
						table[i][w-k] %= 1000000007;
					}
				}
			}
			long sum = table[num_cooks][0];
			/*
			for ( int i = 0 ; i <= num_cooks ; ++i ) {
				sum += table[i][0];
				sum %= 1000000007;
			}
			*/
			/*
			for ( int i = 0 ; i <= num_dishes ; ++i ) {
				System.out.println(Arrays.toString(table[i]));
			}
			*/
			System.out.println(sum);
		}
		
	}

}

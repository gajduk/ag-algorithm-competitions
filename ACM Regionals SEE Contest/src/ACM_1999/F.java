package ACM_1999;

import java.util.HashSet;
import java.util.Scanner;

public class F {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			int num_coins = in.nextInt();
			if ( num_coins == 0 ) {
				break;
			}
			int coins[] = new int[num_coins];
			for ( int i = 0 ; i < num_coins ; ++i ) {
				coins[i] = in.nextInt();
			}
			boolean sums[] = new boolean[num_coins*1005];
			sums[0] = true;
			for ( int j = 0 ; j < coins.length ; ++j ) {
				for ( int i = sums.length-1 ; i >= 0 ; --i ) {
					if ( sums[i] ) {
						sums[i+coins[j]] = true;
					}
				}
			}
			int count = 0;
			for ( int i = sums.length-1 ; i >= 0 ; --i ) {
				if ( sums[i] ) {
					++count;
				}
			}
			System.out.println(count);
		}
	}
	
	
}

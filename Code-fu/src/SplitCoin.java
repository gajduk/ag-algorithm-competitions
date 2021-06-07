import java.util.Arrays;


public class SplitCoin {
	
	public static void main(String[] args) {
		int coins [] = new int[30];
		for ( int i = 0 ; i < coins.length ; ++i ) {
			coins[i] = 100000;
		}
		coins[29] = 99999;
		SplitCoin s = new SplitCoin();
		System.out.println(Arrays.toString(coins));
		System.out.println(s.split(coins));
	}
	
	public int split(int[] coins) {
		int min_diff = Integer.MAX_VALUE;
		int total_sum = 0;
		for ( int k = 0 ; k < coins.length ; ++k ) {
			total_sum += coins[k];
		}
		boolean can_sum[][] = new boolean[2][30*100000+1];
		can_sum[0][0] = true;
		for ( int i = 0 ; i < coins.length ; ++i ) {
			int current = i%2;
			int next = 1-current;
			for ( int k = 0 ; k < can_sum[current].length ; ++k ) {
				if (can_sum[current][k] ) {
					can_sum[next][coins[i]+k] = true;
					can_sum[next][k] = true;
					can_sum[current][k] = false;
				}
			}
		} 
		int current = coins.length%2;
		for ( int k = 0 ; k < can_sum[current].length ; ++k ) {
			if ( can_sum[current][k] ) {
				int temp = Math.abs(k-(total_sum-k));
				if ( min_diff > temp ) min_diff = temp;
			}
		}
		return min_diff;
	}
}

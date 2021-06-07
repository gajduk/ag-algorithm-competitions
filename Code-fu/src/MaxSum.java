import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;


public class MaxSum {
	
	int primes[];
	
	public void generatePrimes ( int n ) {
		int count = 0;
		primes = new int[n+1];
		boolean is_not_prime[] = new boolean[100000000];
		for ( int i = 2 ; i < is_not_prime.length ; ++i ) {
			if ( is_not_prime[i] ) continue;
			int k = 2;
			while ( true ) {
				int t = k*i;
				if ( t < is_not_prime.length ) {
					is_not_prime[t] = true;
				}
				else {
					break;
				}
				++k;
			}
			primes[count++] = i;
			if ( count > n ) return;
		}
		System.out.println("Not all were generated");
		return;
	}
	
	public static void main(String[] args) {
		
		
		
		MaxSum m = new MaxSum();
		System.out.println(m.max(200, 200, 1000000, 1000000));
	}
	
	public int max(int cols, int rows, int k, int m) {
		long nums[][] =  new long[rows][cols];
		generatePrimes(cols*rows);
		for ( int i = 0 ; i < rows ; ++i ) {
			for ( int j = 0 ; j < cols ; ++j ) {
				long q = (k%m)*(primes[i*cols+j]%m);
				nums[i][j] = (long)(q%m) - (m/2);
			}
		}
		long sums[][] =  new long[rows][cols];
		sums[0][0] = nums[0][0];
		for ( int i1 = 1 ; i1 < rows ; ++i1 ) {
			sums[i1][0] = sums[i1-1][0]+nums[i1][0];
		}
		for ( int i2 = 1 ; i2 < cols ; ++i2 ) {
			sums[0][i2] = sums[0][i2-1]+nums[0][i2];
		}
		for ( int i1 = 1 ; i1 < rows ; ++i1 ) {
			for ( int i2 = 1 ; i2 < cols ; ++i2 ) {
				sums[i1][i2] = sums[i1][i2-1]+sums[i1-1][i2]-sums[i1-1][i2-1]+nums[i1][i2];
			}
		}
		//print(nums);
		//print(sums);
		int max = Integer.MIN_VALUE;
		for ( int i1 = 0 ; i1 < rows ; ++i1 ) {
			for ( int i2 = 0 ; i2 < cols ; ++i2 ) {
				for ( int i3 = i1 ; i3 < rows ; ++i3 ) {
					for ( int i4 = i2 ; i4 < cols ; ++i4 ) {
						long t = get(sums,i3,i4)-get(sums,i3,i2-1)-get(sums,i1-1,i4)+get(sums,i1-1,i2-1);
						if ( t > max ) max = (int)t;
					}	
				}	
			}
		}
		return max;
	  }

	private long get(long[][] sums, int i1, int i2) {
		if ( i1 < 0 || i2 < 0 )return 0;
		return sums[i1][i2];
	}

	private void print(int[][] nums) {
		for ( int i = 0 ; i < nums.length ; ++i ) {

		System.out.println(Arrays.toString(nums[i]));
		}
	}

}

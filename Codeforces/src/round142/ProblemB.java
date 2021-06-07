package round142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		String nums[] = in.readLine().split(" ");
		for ( int i = 0 ; i < n ; ++i ) {
			long k = Long.parseLong(nums[i]);
			if ( isSquare(k) && isPrime(k)  ) System.out.println("YES");
			else System.out.println("NO");
		}
	}

	private static boolean isSquare(long k) {
		long koren = (long) Math.sqrt(k);
		return koren*koren == k;
	}

	private static boolean isPrime(long k) {
		if ( k < 2  ) return false;
		if ( k == 2 ) return false;
		long koren = (long) Math.sqrt(k);
		for ( long i = 3 ; i < koren ; i += 2 ) {
			if ( k % i == 0 ) return  false;
		}
		return true;
	}

}

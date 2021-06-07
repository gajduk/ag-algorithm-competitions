package round136;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int count = 0;
		if ( x > 1 ) {
			int sqrt_x = (int) Math.sqrt(x);
			sqrt_x++;
			for ( int i = 1 ; i < sqrt_x ; ++i ) {
				if ( x % i == 0 ) {
					if ( sameDigit(i,x) ) {
						++count;
					}
					if ( x/i != i ) {
						if ( sameDigit(x/i,x) ) {
							++count;
						}
					}
				}
			}
			System.out.println(count);
		}
		else {
			System.out.println(1);
		}
	}

	
	private static boolean sameDigit(int a, int b) {
		int digits1[] = digits(a);
		int digits2[] = digits(b);
		for ( int i = 0 ; i < digits1.length ; ++i ) {
			if ( digits1[i] > 0 && digits2[i] > 0 ) return true;
		}
		return false;
	}

	private static int[] digits(int a) {
		int res[] = new int[10];
		while ( a > 0 ) {
			++res[a%10];
			a /= 10;
		}
		return res;
	}

}

package round148;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {
	
	public static void main(String[] args) {

		Scanner jin = new Scanner(System.in);
		int n = jin.nextInt();
		int q = jin.nextInt();
		String s = jin.next();
		for ( int w = 0 ; w < q ; ++w ) {
			int begin = jin.nextInt();
			int end = jin.nextInt();
			int seq[] = new int[end-begin+1];
			for ( int i = begin-1 ; i < end ; ++i ) {
				seq[i-begin+1] = 10;
				if ( Character.isDigit(s.charAt(i)) ) {
					seq[i-begin+1] = s.charAt(i)-'0';
				}
				else {
					if ( s.charAt(i) == '<')
						seq[i-begin+1] = 11;
				}
			}
			int count[] = new int[10];
//			System.out.println(Arrays.toString(seq));
			runProgram(seq,count);
			for ( int i = 0 ; i< 10 ; ++i ) {
				System.out.print(count[i]);
				if ( i < 9 ) System.out.print(" ");
			}
			System.out.println();
		}
	}

	private static void runProgram(int[] seq, int[] count) {
		int cp = 0;
		int dp = 1;
		int last = -1;
//		Scanner jin = new Scanner(System.in);
		try {
			while ( true ) {
//				System.out.println(Arrays.toString(seq));
//				jin.next();
				if ( seq[cp] == -1 ) {
					cp += dp;continue;
				}
				if ( seq[cp] == 10 ) {
					try {
					if ( seq[last] >= 10 )
						seq[last] = -1;
					}
					catch ( Exception e ) {}
					last = cp;
					dp = 1;cp += dp;continue;
				}
				if ( seq[cp] == 11 ) {
					try {
						if ( seq[last] >= 10 )
							seq[last] = -1;
						}
					catch ( Exception e ) {}
					last = cp;
					dp = -1;cp += dp;continue;
				}
				++count[seq[cp]];
				last = cp;
				--seq[cp];cp += dp;
			}
		}
		catch (Exception e) {
			
		}
	}

}

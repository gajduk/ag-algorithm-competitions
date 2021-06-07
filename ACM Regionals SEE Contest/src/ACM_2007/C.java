package ACM_2007;

import java.util.Scanner;

public class C {
	public static int map[] = new int[300];
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			if ( !in.hasNext() ) {
				break;
			}
			char code[] = in.next().toCharArray();
			if ( !in.hasNext() ) {
				break;
			}
			while ( true ) {
				char number[] = in.next().toCharArray();
				if ( number.length == 0 ) 
					break;
				int max = -1;
				for ( int i = 0 ; i < number.length ; ++i ) {
					for ( int j = code.length-1 ; j > max ; --j ) {
						if ( number[i] == code[j] ) {
							max = j;
							break;
						}
					}
				}
				long sum = 0;
				for ( int radix = max+1 ; radix <= code.length ; ++radix ) { 
					sum += toDecimal(number,code,radix);
				}
				System.out.println(sum);
			}
		}
	}

	private static long toDecimal(char[] number, char[] code, int radix) {
		for ( int i = 0 ; i < radix ; ++i ) {
			map[code[i]] = i;
		}
		long result = 0;
		long offset = 1;
		for ( int i = number.length-1 ; i >= 0 ; --i ) {
			result += map[number[i]]*offset;
			offset *= radix;
		}
		return result;
	}

}

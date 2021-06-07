package round148;

import java.util.Scanner;

public class ProblemA {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int y = jin.nextInt();
		int k = jin.nextInt();
		int n = jin.nextInt();
		StringBuilder sb = new StringBuilder();
		for ( int i = k ; i <= n ; i += k ) {
			if ( i-y >= 1 ) {
				sb.append((i-y)+" ");
			}
		}
		if ( sb.length() == 0 ) {
			System.out.println(-1);
		}
		else {
			System.out.println(sb.substring(0,sb.length()-1));
		}
	}

}

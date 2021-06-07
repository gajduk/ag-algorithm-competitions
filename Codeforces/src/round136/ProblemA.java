package round136;

import java.util.Scanner;

public class ProblemA {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for ( int i = 0 ; i < n ; ++i ) {
			System.out.print((i+n-1)%n+1);
			System.out.print(i == n-1?"":" ");
		}
	}

}

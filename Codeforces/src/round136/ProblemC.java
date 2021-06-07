package round136;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemC {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int array[] = new int[n];
		for ( int i = 0 ; i < n ; ++i ) {
			array[i] = in.nextInt();
		}
		int copy[] = Arrays.copyOf(array, n);
		Arrays.sort(copy);
		int c = 0;
		for ( int i = 0 ; i < n ; ++i ) {
			if ( array[i] != copy[i] ) ++c;
		}
		if ( c <= 2 ) System.out.println("YES");
		else System.out.println("NO");
	}

}

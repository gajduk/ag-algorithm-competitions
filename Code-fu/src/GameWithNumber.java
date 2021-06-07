import java.awt.font.NumericShaper;
import java.util.Arrays;


public class GameWithNumber {
	
	public static void main ( String args[] ) {
		System.out.println(solve(1));
	}
	
	public static int solve( int number ) {
		int optimal[] = new int[number+1];
		optimal[0] = 1;
		optimal[1] = 0;
		for ( int i = 2 ; i <= number ; ++i ) {
			int temp = number+1;	
			if ( i % 2 == 0 ) {
				temp = Math.min(temp,optimal[i/2]);
			}
			if ( i % 3 == 0 ) {
				temp = Math.min(temp,optimal[i/3]);
			}
			if ( i % 5 == 0 ) {
				temp = Math.min(temp,optimal[i/5]);
			}
			if ( i % 7 == 0 ) {
				temp = Math.min(temp,optimal[i/7]);
			}	
			temp = Math.min(temp,optimal[i-1]+1);
			optimal[i] = temp;
		}
		System.out.println(Arrays.toString(optimal));
		return optimal[number];
	}

}

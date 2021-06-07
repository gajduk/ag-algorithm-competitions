package mendo;
import java.util.Scanner;


public class NumberAnagrams {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int counter_anagrams = 0;
		for ( int i = 0 ; i < n ; ++i ) {
			int a = in.nextInt(); int b = in.nextInt();
			if ( areAnagrams(a,b) ) ++counter_anagrams;
		}
		System.out.println(counter_anagrams);
	}

	private static boolean areAnagrams(int a, int b) {
		int counter_digits[] = new int[10];
		while ( a > 0 ) {
			++counter_digits[a%10];
			a /= 10;
		}
		while ( b > 0 ) {
			--counter_digits[b%10];
			b /= 10;
		}
		for ( int i = 0 ; i < 10 ; ++i ) 
			if ( counter_digits[i] != 0 ) return false;
		return true;
	}

}

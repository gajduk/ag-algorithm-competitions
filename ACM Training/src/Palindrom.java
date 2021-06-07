import java.util.Scanner;

import javax.sound.sampled.ReverbType;


public class Palindrom {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String n = in.next();
		System.out.println(nextPalindrom(n));
		System.out.println(nextPalindromeStupid(Long.parseLong(n)));
	}

	private static long nextPalindromeStupid(long n) {
		for ( long i = n ; true ; ++i ) if ( isPalindrome(i) ) return i;
	}

	private static boolean isPalindrome(long n) {
		return Long.toString(n).equals(reverse(Long.toString(n)));
	}

	private static String nextPalindrom(String n) {
		int l = n.length();
		long r = Long.parseLong(reverse(n,0,l/2));
		long q = Long.parseLong(n.substring((l+1)/2,l));
		if ( q > r ) {
			long rr = Long.parseLong(n.substring(0,(l+1)/2));
			++rr;
			String sr = Long.toString(rr);
			String sq = reverse(sr);
			String m = sq.substring(0,l%2);
			sr = sr.substring(0,l/2);
			sq = sq.substring(l%2);
			return sr+m+sq;
		}
		else {
			return reverse(r+"")+n.substring(l/2,(l+1)/2)+r;
		}
	}
	
	private static String reverse( String n ) {
		return reverse(n,0,n.length());
	}
	
	private static String reverse( String n , int start , int end ) {
		StringBuffer sb = new StringBuffer(n.substring(start,end));
		return sb.reverse().toString();
	}
	
}

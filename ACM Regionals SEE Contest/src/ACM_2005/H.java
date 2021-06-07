package ACM_2005;

import java.util.Scanner;

public class H {

	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int num_cases = Integer.parseInt(in.nextLine());
		for ( int t = 0 ; t < num_cases ; ++t ) {
			String a = in.nextLine();
			String b = in.nextLine();
			int count_a[] = new int[26];
			int count_b[] = new int[26];
			for ( int i = 0 ; i < a.length() ; ++i ) {
				++count_a[a.charAt(i)-'a']; 
			}
			for ( int i = 0 ; i < b.length() ; ++i ) {
				++count_b[b.charAt(i)-'a']; 
			}
			int result = 0;
			for ( int i = 0 ; i < 26 ; ++i ) {
				result += Math.abs(count_a[i]-count_b[i]);
			}
			System.out.println(result);
		}
	}
	
}

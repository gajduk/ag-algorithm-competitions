package UVA;

import java.util.Scanner;

public class ScrollingSign {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int q = 0 ; q < t; ++q ) {
			int k = in.nextInt();
			int n = in.nextInt();
			String m = in.next();
			for ( int w = 1 ; w < n ; ++w ) {
				String word = in.next();
				boolean flag = false;
				for ( int i = k ; i > 0 ; --i ) {
					if ( m.length()-i >= 0 ) {
						if ( m.substring(m.length()-i).equals(word.substring(0, i)) ) {
							m = m+word.substring(i); flag = true;
							break;
						}
					}
				}
				if ( ! flag ) {
					m = m+word;
				}
			}
			System.out.println(m.length());
		}
	}
	
}

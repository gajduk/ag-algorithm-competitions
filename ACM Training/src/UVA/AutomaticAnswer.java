package UVA;

import java.util.Scanner;

public class AutomaticAnswer {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int i = 0 ; i < t ; ++i ) {
			int n = in.nextInt();
			n *= 567;
			n /= 9;
			n += 7492;
			n *= 235;
			n /= 47;
			n -= 498;
			n %= 100;
			System.out.println(Integer.toString(n/10).replace("-",""));
		}
	}

}

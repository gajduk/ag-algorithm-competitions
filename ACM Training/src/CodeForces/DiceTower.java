package CodeForces;

import java.util.Scanner;

public class DiceTower {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int t = 7-in.nextInt();
		for ( int i = 0 ; i < n ; ++i ) {
			int a = in.nextInt();
			int aa = 7-a;
			int b = in.nextInt();
			int bb = 7-b;
			if ( t == a || t == aa || t == b || t == bb ) {
				System.out.println("NO");return;
			}
		}
		System.out.println("YES");
	}

}

package ACM_2008;

import java.util.Scanner;

public class I {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			int n = in.nextInt();
			int c = in.nextInt();
			if ( n == 0 ) {
				System.out.println(0);
				continue;
			}
			int t_optimum = c/(2*n);
//			System.out.println(t_optimum);
			int f_t = t_optimum*(c-t_optimum*n);
			++t_optimum;
			int f_t_1 = t_optimum*(c-t_optimum*n);
//			System.out.println(f_t+" "+f_t_1);
			if ( f_t >= f_t_1 ) {
				System.out.println(t_optimum-1);
			}
			else {
				System.out.println(t_optimum);
			}
		}
	}

}

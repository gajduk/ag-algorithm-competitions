package Chapter7;

import java.util.Scanner;

public class Problem3 {

	public static long a1;
	public static long b1;
	public static long q;
	public static long r;
	public static long s;
	public static long t;

	public static long greatestCommonDivisorEquationSolve(long p, long q, long x[], long y[] ) {
		long x1[] = new long[1];
		long y1[] = new long[1]; /* previous coefficients */
		long g; /* value of gcd(p,q) */
		if (q > p) return(greatestCommonDivisorEquationSolve(q,p,y,x));
		if (q == 0) {
			x[0] = 1;
			y[0] = 0;
			return(p);
		}
		g = greatestCommonDivisorEquationSolve(q, p%q, x1, y1);
		x[0] = y1[0];
		y[0] = (x1[0] - (int) (p/q)*y1[0]);
		return(g);
	}

	public static void main(String args[]) {
		test_judge();

	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			long a = in.nextInt();
			long b = in.nextInt();
			long x[] = new long[1];
			long y[] = new long[1];
			long gcd = greatestCommonDivisorEquationSolve(a,b,x,y);
			s = b/gcd;
			t = -a/gcd;
//			System.out.println(x[0] + " " + y[0] + " " + s + " " + t);
			a1 = x[0];
			b1 = y[0];
			if (a1 > b1) {
				if (s < 0) {
					while (a1 > b1) {
						a1 += s;
						b1 += t;
					}
				} else {
					while (a1 > b1) {
						a1 -= s;
						b1 -= t;
					}
				}
			} else {
				if (s > 0) {
					while (a1 + s <= b1 + t) {
						a1 += s;
						b1 += t;
					}
				} else {
					while (a1 - s <= b1 - t) {
						a1 -= s;
						b1 -= t;
					}
				}
			}
			long min_sum = Math.abs(a1) + Math.abs(b1);
			long temp_a = a1;
			long temp_b = b1;
			if (s > 0) {
				s *= -1;
				t *= -1;
			}
			while (true) {
				long sum = Math.abs(a1 + s) + Math.abs(b1 + t);
				if (sum < min_sum) {
					min_sum = sum;
					temp_a = a1;
					temp_b = b1;
				} else {
					break;
				}
				a1 += s;
				b1 += t;
			}
			System.out.println(temp_a + " " + temp_b + " " +gcd);
		}
	}

}

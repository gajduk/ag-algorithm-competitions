package Chapter7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem7 {
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( true ) {
    		long n = new Long(in.nextLine());
    		if ( n == 0 )
    			break;
    		String s_box = in.nextLine();
    		StringTokenizer box_parametars = new StringTokenizer(s_box);
    		long c1 = new Long(box_parametars.nextToken());
    		long n1 = new Long(box_parametars.nextToken());
    		s_box = in.nextLine();
    		box_parametars = new StringTokenizer(s_box);
    		long c2 = new Long(box_parametars.nextToken());
    		long n2 = new Long(box_parametars.nextToken());
    		calculateMinBoxes_1(n,n1,c1,n2,c2);
    	}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem7.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		long n = new Long(in.nextLine());
    		if ( n == 0 )
    			break;
    		String s_box = in.nextLine();
    		StringTokenizer box_parametars = new StringTokenizer(s_box);
    		long c1 = new Long(box_parametars.nextToken());
    		long n1 = new Long(box_parametars.nextToken());
    		s_box = in.nextLine();
    		box_parametars = new StringTokenizer(s_box);
    		long c2 = new Long(box_parametars.nextToken());
    		long n2 = new Long(box_parametars.nextToken());
    		calculateMinBoxes_1(n,n1,c1,n2,c2);
    	}
	}
	
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

	public static void calculateMinBoxes( long n , long n1 , long c1 , long n2 , long c2 ) {
		long a = n1;
		long b = n2;
		long x[] = new long[1];
		long y[] = new long[1];
		long gcd = greatestCommonDivisorEquationSolve(a,b,x,y);
		if ( n % gcd != 0 ) {
			System.out.println("failed");
			return;
		}
		double cost1 = (double) c1 / n1;
		double cost2 = (double) c2 / n2;
		x[0] *= n / gcd;
		y[0] *= n / gcd;
		if ( cost1 < cost2 ) {
			long u = b / gcd;
			long v = a / gcd;
			//u *= n / gcd;
			//v *= n / gcd;
			while ( y[0] >= 0 ) {
				x[0] += u;
				y[0] -= v;
			}
			while ( y[0] < 0 ) {
				x[0] -= u;
				y[0] += v;
			}
			if ( x[0] >= 0 && y[0] >= 0 ) {
				System.out.println(x[0]+" "+y[0]);
			}
		}
		else {
			long u = b / gcd;
			long v = a / gcd;
			while ( x[0] >= 0 ) {
				x[0] -= u;
				y[0] += v;
			}
			while ( x[0] < 0 ) {
				x[0] += u;
				y[0] -= v;
			}
			if ( x[0] >= 0 && y[0] >= 0 ) {
				System.out.println(x[0]+" "+y[0]);
			}
		}
	}

	public static void calculateMinBoxes_1( long n , long n1 , long c1 , long n2 , long c2 ) {
		if ( n < n1 && n < n2 ) {
			System.out.println("failed");
			return;
		}
		long a = n1;
		long b = n2;
		long x[] = new long[1];
		long y[] = new long[1];
		long g = greatestCommonDivisorEquationSolve(a,b,x,y);
		if ( n % g != 0 ) {
			System.out.println("failed");
			return;
		}
		/*
		 double cost1 = (double) c1 / n1;
		double cost2 = (double) c2 / n2;
		x[0] *= n / gcd;
		y[0] *= n / gcd;
		if ( cost1 < cost2 ) {
			long u = b / gcd;
			long v = a / gcd;
			long t = (long) ((double) y[0] / v) < ((double) y[0] / v) ? (long) ((double) y[0] / v) : (long) ((double) y[0] / v) ;
			x[0] += t*u;
			y[0] -= t*v;
			if ( y[0] < 0 ) {
				x[0] -= u;
				y[0] += v;
			}
			System.out.println(x[0]+" "+y[0]);
		}
		else {
			long u = b / gcd;
			long v = a / gcd;
			long t = (long) ((double) x[0] / u) < ((double) x[0] / u) ? (long) ((double) x[0] / u) : (long) ((double) x[0] / u) ;
			x[0] -= t*u;
			if ( x[0] < 0 ) {
				x[0] += u;
				t--;
			}
			y[0] += t*v;
			System.out.println(x[0]+" "+y[0]);
		}
		*/
		n1 /= g;
		n2 /= g;
		n /= g;
		long m1 = x[0];
		long m2 = y[0];
		  m1 *= n;
		  m2 *= n;  
		  long l = m1 >= 0 ? -m1 / n2 : (-m1 + n2 - 1) / n2;  
		  long h = m2 >= 0 ? m2 / n1 : (m2 - n1 + 1) / n1;  
		  if (l > h) {
			  System.out.println("failed");
			  return; 
		  }
		  long cf = c1 * n2 - c2 * n1;  
		  if (cf * l <= cf *h)  {
			  m1 = m1 + n2 * l;
			  m2 = m2 - n1 * l;  
		  }
		  else {
			  m1 = m1 + n2 * h;
			  m2 = m2 - n1 * h;  
		  }
		  System.out.println(m1+" "+m2);

	}
	
	
}

package Chapter7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Chapter3 {
	
	public static int index = 0;
	public static int g = 0;
	public static int U[] = new int[40];
	public static int V[] = new int[40];
	public static  int R[] = new int[40];
	public static int Q[] = new int[40];
	public static boolean k;

    

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
	
	public static int greatestCommonDivisorEuclidsAlgorithm ( int number1 , int number2 ) {
		if ( number1 > number2 ) {
			if ( number2 == 0 ) 
				return number1;
			return greatestCommonDivisorEuclidsAlgorithm(number1%number2, number2);
		}
		else {
			if ( number1 == 0 ) 
				return number2;
			return greatestCommonDivisorEuclidsAlgorithm(number2%number1, number1); 
		}
	}
	
	
	public static void solveEquation ( int a , int b ) {
	    	k = true;
		    if( a == 0 && b == 0 )
		        k = false;
		    if( k ) {
	            index = 0;
		        U[0] = 0;   U[1] = 1;
		        V[0] = 1;   V[1] = 0;
		        R[0] = a;   R[1] = b;
		 
		        g = greatestCommonDivisorEuclidsAlgorithm(a,b);
		 
		        if(a % b != 0 && b % a != 0 && a != 0 && b != 0) {
	                for(int i = 1; ; i++) {
		               Q[i+1] = R[i-1] / R[i];
	                   R[i+1] = R[i-1] % R[i];
	                   U[i+1] = U[i-1] - (Q[i+1] * U[i]);
		               V[i+1] = V[i-1] - (Q[i+1] * V[i]);
		               if( R[i+1] == g ) {  
		                	index = i+1;  
		                	break;  
		               }
		            }
		        }
		     }
		     if( a % b == 0 || a == 0)
		                System.out.println(0+" "+1+" "+g);
		     else if( (b & a) == 0 || b == 0 )
		    	 	System.out.println(1+" "+0+" "+g);
		     else
		    	 System.out.println(V[index]+" "+U[index]+" "+g);
	}
	
	public static void test() {
		
	}
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}
	
	

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		solveEquation(new Integer(numbers.nextToken()),new Integer(numbers.nextToken()));
    	}
	}

	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		solveEquation(new Integer(numbers.nextToken()),new Integer(numbers.nextToken()));
    	}
	}
	
}

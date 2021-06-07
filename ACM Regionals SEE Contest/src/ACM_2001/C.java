package ACM_2001;

import java.util.Scanner;

public class C {
	
	public static void main ( String args[] ) {
		 Scanner in = new Scanner(System.in);
		 while ( in.hasNext() ) {
			 int x = in.nextInt();
			 int y = in.nextInt();
			 for ( int a = x ; a < y ; ++a ) {
				 for ( int b = a+1 ; b < y ; ++b ) {
					 if ( (a+b)*(a+b) == 4*a*b ) {
						 System.out.print(a+","+b+";");
					 }
				 }
			 }
			 System.out.println(" no more pairs.");
		 }
		 
	}

}

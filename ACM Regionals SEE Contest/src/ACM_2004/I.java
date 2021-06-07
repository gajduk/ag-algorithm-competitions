package ACM_2004;

import java.util.Scanner;

public class I {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			int term = in.nextInt();
			System.out.println("TERM "+term+" IS "+getTerm(term-1));
		}
	}

	private static String getTerm(int term) {
		int diagonal = (int) ((-1+Math.sqrt(1+8*term))/2.0);
		int index = term-(diagonal*(diagonal+1))/2;
		if ( diagonal % 2 == 0 ) {
			return (diagonal-index+1)+"/"+(index+1);
		}
		else {			
			return (index+1)+"/"+(diagonal-index+1);
		}
	}

}

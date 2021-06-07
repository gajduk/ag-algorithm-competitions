package Chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem3 {

	public static double determinateRadius ( double a , double b , double c ) {
		double s = ( a + b + c ) / 2;
		double p = Math.sqrt(s*(s-a)*(s-b)*(s-c));
		return p / s;
	}
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}
	
	public static void test_judge ( ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			StringTokenizer tkr = new StringTokenizer(in.nextLine());
			double result = determinateRadius(new Double(tkr.nextToken()), new Double(tkr.nextToken()), new Double(tkr.nextToken()));
			if ( Double.isNaN(result) ) 
				result = 0.000;
			System.out.printf("The radius of the round table is: %.3f",result);
			System.out.println();
		}
	}
	
	public static void test_file() {
		FileInputStream input = null;
		try {
			input= new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter13 problem3.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(input);
		while ( in.hasNext() ) {
			StringTokenizer tkr = new StringTokenizer(in.nextLine());
			double result = determinateRadius(new Double(tkr.nextToken()), new Double(tkr.nextToken()), new Double(tkr.nextToken()));
			if ( Double.isNaN(result) ) 
				result = 0.000;
			System.out.printf("The radius of the round table is: %.3f",result);
			System.out.println();
		}
	}

}

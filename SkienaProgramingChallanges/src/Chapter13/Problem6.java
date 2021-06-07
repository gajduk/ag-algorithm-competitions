package Chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem6 {
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}
	
	public static void test_file () {
		FileInputStream input = null;
		try {
			input= new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter13 problem6.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in = new Scanner(input);
		while ( in.hasNext() ) {
			StringTokenizer numbers = new StringTokenizer(in.nextLine());
			double l = new Double(numbers.nextToken());
			double w = new Double(numbers.nextToken());
			System.out.printf("%.3f 0.000 %.3f",getMin(l,w),getMax(l,w));
			System.out.println();
		}
	}
	
	private static double getMin(double l, double w) {
		double m_b = 4 * ( l + w );
		double sq_b = m_b*m_b;
		double a_c_4 = 48*l*w;
		double root = Math.sqrt(sq_b-a_c_4);
		double x_1 = (m_b-root) / 24;
		double x_2 = (m_b+root) / 24;
		return x_1*l*w>x_2*l*w?x_2:x_1;
	}

	private static double getMax(double l, double w) {
		return l<w?l/2:w/2;
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			StringTokenizer numbers = new StringTokenizer(in.nextLine());
			double l = new Double(numbers.nextToken());
			double w = new Double(numbers.nextToken());
			System.out.printf("%.3f 0.000 %.3f",getMin(l,w),getMax(l,w));
			System.out.println();
		}
	}

}

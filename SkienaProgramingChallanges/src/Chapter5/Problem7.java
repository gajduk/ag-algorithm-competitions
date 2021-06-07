package Chapter5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem7 {
	
	public static void main ( String args[] ) {
	//	test_file();
		test_judge();
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			if ( in.hasNext() ) {
				String s_numbers = in.nextLine();
				StringTokenizer numbers = new StringTokenizer(s_numbers);
				int r = new Integer(numbers.nextToken());
				int l = new Integer(numbers.nextToken());
				if ( r == 1 && l == 1 ) {
					break;
				}
				System.out.println(findPath(l,r));
			}
		}
	}

	private static void test_file() {
		FileInputStream input = null;
		try {
			input = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter5 problem7.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in = new Scanner(input);
		while ( true ) {
			if ( in.hasNext() ) {
				String s_numbers = in.nextLine();
				StringTokenizer numbers = new StringTokenizer(s_numbers);
				int r = new Integer(numbers.nextToken());
				int l = new Integer(numbers.nextToken());
				if ( r == 1 && l == 1 ) {
					break;
				}
				System.out.println(findPath(l,r));
			}
		}
		
	}

	private static String findPath(int l, int r) {
		String path = "";
		while ( l != 1 || r != 1 ) {
			if ( l > r ) {
				path += "L";
				l -= r;
			}
			else {
				path += "R";
				r -= l;
			}
		}
		return path;
	}

}

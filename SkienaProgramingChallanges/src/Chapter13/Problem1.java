package Chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

class HoleLocation {
	double x;
	double y;
	
	public HoleLocation() {
	}
	
	public HoleLocation(double a , double b ) {
		x=a;y=b;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}

public class Problem1 {
	public static HoleLocation dog;
	public static HoleLocation gopher;
	
	public static double distance ( HoleLocation a , HoleLocation b ) {
		double dx = a.x-b.x;
		double dy = a.y-b.y;
		double sq = dx*dx+dy*dy;
		if ( sq < 0 ) {
			return 0;
		}
		return Math.sqrt(sq);
	}
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge1();
	}
	
	private static void test_judge1() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			int num_holes = in.nextInt();
			gopher = new HoleLocation(in.nextDouble(),in.nextDouble());
			dog = new HoleLocation(in.nextDouble(),in.nextDouble());
			int i = 0;
			boolean flag = false;
			for ( i = 0 ; i < num_holes ; ++i ) {
				HoleLocation current_hole = new HoleLocation(in.nextDouble(),in.nextDouble());
				if ( !flag && distance(gopher, current_hole)*2 <= distance(dog, current_hole) ) {
					System.out.printf("The gopher can escape through the hole at (%.3f,%.3f).",current_hole.x,current_hole.y);
					System.out.println();
					flag = true;
//					break;
				}
			}
			if ( !flag ) {
				System.out.println("The gopher cannot escape.");
			}
		}
	}
	
	private static void test_file1() {
		FileInputStream input = null;
		try {
			input= new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter13 problem1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(input);
		while ( in.hasNext() ) {
			int num_holes = in.nextInt();
			gopher = new HoleLocation(in.nextDouble(),in.nextDouble());
			dog = new HoleLocation(in.nextDouble(),in.nextDouble());
			int i = 0;
			boolean flag = false;
			for ( i = 0 ; i < num_holes ; ++i ) {
				HoleLocation current_hole = new HoleLocation(in.nextDouble(),in.nextDouble());
				if ( !flag && distance(gopher, current_hole)*2 <= distance(dog, current_hole) ) {
					System.out.printf("The gopher can escape through the hole at (%.3f,%.3f).",current_hole.x,current_hole.y);
					System.out.println();
					flag = true;
//					break;
				}
			}
			if ( i == num_holes ) {
				System.out.println("The gopher cannot escape.");
			}
		}
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			StringTokenizer tkr = new StringTokenizer(in.nextLine());
			int num_holes = 0;
			try {
				num_holes = new Integer(tkr.nextToken());
				gopher = new HoleLocation(new Double(tkr.nextToken()),new Double(tkr.nextToken()));
				dog = new HoleLocation(new Double(tkr.nextToken()),new Double(tkr.nextToken()));
			}
			catch (Exception e) {
				
			}
			int i = 0;
			for ( i = 0 ; i < num_holes ; ++i ) {
				StringTokenizer qwe = new StringTokenizer(in.nextLine());
				HoleLocation current_hole = new HoleLocation(new Double(qwe.nextToken()),new Double(qwe.nextToken()));
				if ( distance(gopher, current_hole)*2 <= distance(dog, current_hole) ) {
					System.out.printf("The gopher can escape through the hole at (%.3f,%.3f).",current_hole.x,current_hole.y);
					System.out.println();
					break;
				}
			}
			if ( i == num_holes ) {
				System.out.println("The gopher cannot escape.");
			}
			if ( in.hasNext() ) {
				in.nextLine();
			}
		}
	}

	public static void test_file() {
		FileInputStream input = null;
		try {
			input= new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter13 problem1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(input);
		while ( in.hasNext() ) {
			StringTokenizer tkr = new StringTokenizer(in.nextLine());
			int num_holes = new Integer(tkr.nextToken());
			gopher = new HoleLocation(new Double(tkr.nextToken()),new Double(tkr.nextToken()));
			dog = new HoleLocation(new Double(tkr.nextToken()),new Double(tkr.nextToken()));
			int i = 0;
			for ( i = 0 ; i < num_holes ; ++i ) {
				String s_hole = in.nextLine();
				StringTokenizer qwe = new StringTokenizer(s_hole);
				HoleLocation current_hole = new HoleLocation(new Double(qwe.nextToken()),new Double(qwe.nextToken()));
				if ( distance(gopher, current_hole)*2 < distance(dog, current_hole) ) {
					System.out.printf("The gopher can escape through the hole at (%.3f,%.3f).",current_hole.x,current_hole.y);
					System.out.println();
					break;
				}
			}
			if ( i == num_holes ) {
				System.out.println("The gopher cannot escape.");
			}
			if ( in.hasNext() ) {
				in.nextLine();
			}
		}
	}

}

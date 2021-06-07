package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem8 {
	
	public static int solutions[] = {0,1,3,7,11,17,23,31,39,49,59,71,83,97,111,127,143,161,179,199,219,241,263,287,311,337,363,391,419,449,479,511,543,577,611,647,683,721,759,799,839,881,923,967,1011,1057,1103,1151,1199,1249,1299 };
	
//	public static ArrayList<Integer> perfect_squares = new ArrayList<Integer>();
//	public static ArrayList<Integer> pegs = new ArrayList<Integer>();
/*	
	public static void populatePerfectSquares () {
		for ( int k = 1 ; k < 60 ; ++k ) {
			perfect_squares.add(k*k);
		}
	}

	public static int getLastSquare ( int number , int smaller_than ) {
		for ( int k = smaller_than-1 ; k > 0 ; --k ) {
			if ( perfect_squares.contains(k+number) ) {
				return k;
			}
		}
		return 0;
	}

	public static int calculateNumberOfBalls ( int num_pegs ) {
		int num_pegs_used = 0;
		int current_ball_id = 1;
		pegs = new ArrayList<Integer>();
		while ( num_pegs_used <= num_pegs ) {
			int temp = getLastSquare(current_ball_id, current_ball_id);
			//System.out.println(current_ball_id+" "+temp);
			while ( temp != 0 ) {
				if ( pegs.contains(temp) ) {
					pegs.remove(pegs.indexOf(temp));
					pegs.add(current_ball_id);
					break;
				}
				temp = getLastSquare(current_ball_id, temp);
			}
			if ( temp == 0 ) {
				if ( num_pegs_used == num_pegs )
					break;
				++num_pegs_used;
				pegs.add(current_ball_id);
			}
			++current_ball_id;
		}
		return current_ball_id-1;
	}
	*/
	public static void main ( String args[] ) {
	//	populatePerfectSquares();
		//test_all();
	//	test_file();
		test_judge();
	}
	/*
	public  static void test_all() {
		System.out.println(calculateNumberOfBalls(1));
		System.out.println(calculateNumberOfBalls(2));
		System.out.println(calculateNumberOfBalls(3));
		System.out.println(calculateNumberOfBalls(4));
		System.out.println(calculateNumberOfBalls(49));
		System.out.println(calculateNumberOfBalls(50));
	}
*/
	public static void test_file ( ) {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem8.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int m = 0 ; m < num_test_cases ; ++m ) {
    		int num_pegs = new Integer(in.nextLine());
   // 		System.out.print(calculateNumberOfBalls(num_pegs)+",");
    		System.out.println(solutions[num_pegs]);
    	}
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
    	
		for ( int m = 0 ; m < num_test_cases ; ++m ) {
    		int num_pegs = new Integer(in.nextLine());
    	//	System.out.println(calculateNumberOfBalls(num_pegs));
    		System.out.println(solutions[num_pegs]);
    	}
	}

}

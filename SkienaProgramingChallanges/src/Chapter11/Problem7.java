package Chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem7 {
	
	public static final int NCHOPSTICKS = 5000;
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter11 problem7.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	int num_test_cases;
		int num_guests;
		int num_chopsticks;
		int difference[] = new int[NCHOPSTICKS+1];
		int chopsticks[] = new int[NCHOPSTICKS+1];
		long badness[] = new long[NCHOPSTICKS+1];
		Scanner in = new Scanner(inputFile);
		num_test_cases = in.nextInt();
		for ( int t = 0 ; t < num_test_cases ; ++t  ) {
			//reading in the data
			
			num_guests = in.nextInt();
			num_chopsticks = in.nextInt();
			num_guests += 8;
			
			for ( int i = 0 ; i < num_chopsticks ; ++i ) {
				chopsticks[i+1] = in.nextInt();
				//initializing differences at 0 and badness as (A-B)^2
				difference[i+1] = 0;
				badness[i+1] = (int) Math.pow(chopsticks[i+1]-chopsticks[i],2);
			}
			//for all the guests
			for ( int i = 1 ; i <= num_guests ; ++i ) {
				//starting from the chop stick (num_chopsticks-3*(num_guests-i)-1) ??
				//going down to chop stick (2*i) <=> just enough chop sticks for everyone
				
				for ( int j = (num_chopsticks-3*(num_guests-i)-1) ; j >= 2*i ; --j ) {
					difference[j] = (int) (difference[j-2]+badness[j]);
				}
				
				for ( int j = (2*i)+1 ; j <= (num_chopsticks-3*(num_guests-i)-1) ; ++j ) {
					if ( difference[j-1] < difference[j] ) {
						difference[j] = difference[j-1];
					}
				}
				
				difference[num_chopsticks-3*(num_guests-i)] = difference[num_chopsticks-3*(num_guests-i)-1];
			}
			System.out.println(difference[num_chopsticks]);
		}
	}
	
	public static void test_judge() {
		int num_test_cases;
		int num_guests;
		int num_chopsticks;
		int difference[] = new int[NCHOPSTICKS+1];
		int chopsticks[] = new int[NCHOPSTICKS+1];
		long badness[] = new long[NCHOPSTICKS+1];
		Scanner in = new Scanner(System.in);
		num_test_cases = in.nextInt();
		for ( int t = 0 ; t < num_test_cases ; ++t  ) {
			//reading in the data
			
			num_guests = in.nextInt();
			num_chopsticks = in.nextInt();
			num_guests += 8;
			
			for ( int i = 0 ; i < num_chopsticks ; ++i ) {
				chopsticks[i+1] = in.nextInt();
				//initializing differences at 0 and badness as (A-B)^2
				difference[i+1] = 0;
				badness[i+1] = (int) Math.pow(chopsticks[i+1]-chopsticks[i],2);
			}
			//for all the guests
			for ( int i = 1 ; i <= num_guests ; ++i ) {
				//starting from the chop stick (num_chopsticks-3*(num_guests-i)-1) ??
				//going down to chop stick (2*i) <=> just enough chop sticks for everyone
				
				for ( int j = (num_chopsticks-3*(num_guests-i)-1) ; j >= 2*i ; --j ) {
					difference[j] = (int) (difference[j-2]+badness[j]);
				}
				
				for ( int j = (2*i)+1 ; j <= (num_chopsticks-3*(num_guests-i)-1) ; ++j ) {
					if ( difference[j-1] < difference[j] ) {
						difference[j] = difference[j-1];
					}
				}
				
				difference[num_chopsticks-3*(num_guests-i)] = difference[num_chopsticks-3*(num_guests-i)-1];
			}
			System.out.println(difference[num_chopsticks]);
		}
	}

}

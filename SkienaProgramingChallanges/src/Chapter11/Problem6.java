package Chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

	
public class Problem6 {
	
	public static int sum[];
	public static int max;
	public static int t[][];
	public static int path[][];
	public static final int NONE = -1;
	public static int cars[] = new int[2000];
	public static int num_cars = 0;
	
	
	public static void determinatePath ( int current_car , int capacity1 ) {
		if ( capacity1 < 0 )
			return;
		if ( current_car >= num_cars )
			return;
		if ( path[current_car][capacity1] == 1 ) {		
			System.out.println("port");
		}
		if ( path[current_car][capacity1] == 2 ) {
			capacity1 -= cars[current_car];
			System.out.println("starboard");
		}
		determinatePath(current_car+1,capacity1);
	}
	
	
	public static int maxCars ( int current_car , int capacity1  ) {
		int capacity2 = max-(sum[current_car]-(max-capacity1));
		if ( current_car >= num_cars ) {
			t[current_car][capacity1] = current_car;
			return current_car;
		}
		if ( cars[current_car] > capacity1 && cars[current_car] > capacity2 ) {
			t[current_car][capacity1] = current_car;
			return current_car;
		}
		if ( cars[current_car] > capacity1 ) {
			if ( t[current_car+1][capacity1] == NONE )
				t[current_car+1][capacity1] = maxCars(current_car+1,capacity1);
			path[current_car][capacity1] = 1;
			return t[current_car+1][capacity1];
		}
		if ( cars[current_car] > capacity2 ) {
			if ( t[current_car+1][capacity1-cars[current_car]] == NONE )
				t[current_car+1][capacity1-cars[current_car]] = maxCars(current_car+1,capacity1-cars[current_car]);
			path[current_car][capacity1] = 2;
			return t[current_car+1][capacity1-cars[current_car]];
		}
		if ( t[current_car+1][capacity1] == NONE ) {
			t[current_car+1][capacity1] = maxCars(current_car+1,capacity1);
		}
		if ( t[current_car+1][capacity1-cars[current_car]] == NONE ) {
			t[current_car+1][capacity1-cars[current_car]] = maxCars(current_car+1,capacity1-cars[current_car]);
		}
		t[current_car][capacity1] = max(t[current_car+1][capacity1],t[current_car+1][capacity1-cars[current_car]]);
		if ( t[current_car][capacity1] == t[current_car+1][capacity1] ) {
			path[current_car][capacity1] = 1;
		}
		else {
			path[current_car][capacity1] = 2;
		}
		return t[current_car][capacity1];
	}

	
	
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = in.nextInt();
		for ( int h = 0 ; h < num_test_cases ; ++h ) {
			max = in.nextInt()*100;
			int car_counter = 0;
			while ( true ) {
				int car = in.nextInt();
				if ( car == 0 ) {
					break;
				}
				cars[car_counter++] = car;
			}
			num_cars = car_counter;
			t = new int[num_cars+1][max+1];
			path = new int[num_cars+1][max+1];
			
			for ( int i = 0 ; i < t.length ; ++i ) {
				for ( int w = 0 ; w < t[0].length ; ++w ) {
					t[i][w] = NONE;
				}
			}
			sum = new int[num_cars+1];
			sum[0] = 0;
			for ( int i = 1 ; i < sum.length ; ++i ) {
				sum[i] = sum[i-1] + cars[i-1];
			}
			int r = maxCars(0,max);
			System.out.println(r);
			determinatePath(0,max);
			if ( h < num_test_cases-1 ) {
				System.out.println();
			}
		}	
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter11 problem6.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = in.nextInt();
		for ( int h = 0 ; h < num_test_cases ; ++h ) {
			max = in.nextInt()*100;
			int car_counter = 0;
			while ( true ) {
				int car = in.nextInt();
				if ( car == 0 ) {
					break;
				}
				cars[car_counter++] = car;
			}
			num_cars = car_counter;
			t = new int[num_cars+1][max+1];
			path = new int[num_cars+1][max+1];
			
			for ( int i = 0 ; i < t.length ; ++i ) {
				for ( int w = 0 ; w < t[0].length ; ++w ) {
					t[i][w] = NONE;
				}
			}
			sum = new int[num_cars+1];
			sum[0] = 0;
			for ( int i = 1 ; i < sum.length ; ++i ) {
				sum[i] = sum[i-1] + cars[i-1];
			}
			int r = maxCars(0,max);
			System.out.println(r);
			determinatePath(0,max);
			if ( h < num_test_cases-1 ) {
				System.out.println();
			}
		}	
	}
	
	public static void printT () {
		for ( int i = 0 ; i < t.length ; i++ ) {
			for ( int w = 0 ; w < t[0].length ; w += 100 ) {
				System.out.print(t[i][w]);
			}
			System.out.println();
		}
	}

	public static int max(int a , int b ) {
		return a>b?a:b;
	}
	
	

	public static int maxCars ( int cars[] , int current_car , int capacity1 , int capacity2 ) {
		if ( current_car >= cars.length )
			return current_car;
		if ( cars[current_car] > capacity1 && cars[current_car] > capacity2 ) {
			return current_car;
		}
		if ( cars[current_car] > capacity1 ) {
			return maxCars(cars,current_car+1,capacity1 ,capacity2-cars[current_car]);
		}
		if ( cars[current_car] > capacity2 ) {
			return maxCars(cars,current_car+1,capacity1-cars[current_car],capacity2);
		}
		return max(maxCars(cars,current_car+1,capacity1 ,capacity2-cars[current_car]),maxCars(cars,current_car+1,capacity1-cars[current_car],capacity2));
	}

}

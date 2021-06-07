package Chapter4;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Problem3 {

	/*
	public static void main( String args[] ) {
		//test_file();
		test_judges();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		in.nextLine();
    		int num_people = new Integer(in.nextLine());
    		int times[] = new int[num_people];
    		for ( int h = 0 ; h < num_people ; ++h ) {
    			times[h] = new Integer(in.nextLine());
    		}
    		calculateMinTime(times);
    		if ( i < num_test_cases-1 ) 
    			System.out.println();
    	}
	}
	
	public static void test_judges() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		in.nextLine();
    		int num_people = new Integer(in.nextLine());
    		int times[] = new int[num_people];
    		for ( int h = 0 ; h < num_people ; ++h ) {
    			times[h] = new Integer(in.nextLine());
    		}
    		calculateMinTime(times);
    		if ( i < num_test_cases-1 ) 
    			System.out.println();
    	}
	}
	
	
	public static void calculateMinTime( int times[] ) {
		if ( times.length == 1 ) {
			System.out.println(times[0]);
			System.out.println(times[0]);
			return;
		}
		if ( times.length == 2 ) {
			System.out.println((times[0]+times[1]));
			System.out.println(times[0]+" "+times[1]);
			return;
		}
		int step = 0;// 0 = get the messiahs across the river
		//1 - return the first messiah back
		//2 - you have two cases
		// there are only 2 people on point A the meessiah and person X take them to the other side
		// there are more than two people on point A . Get any two without the meesiah across
		//3 bring back messiah 2
		int point[] = new int[times.length];//1 if the person is on point A , 2 if the preson is on point B
		for ( int k = 0 ; k < times.length ; ++k ) {
			point[k] = 1;
		}
		Arrays.sort(times);
		int messiah_1 = 0;
		int messiah_2 = 1;
		int total_time = 0;
		String output = "";
		while ( true ) {
			if ( step == 0 ) {
				point[messiah_1] = 2;
				point[messiah_2] = 2;
				step = 1;
				total_time += times[messiah_2];
				output+= ":"+Integer.toString(times[messiah_1])+" "+Integer.toString(times[messiah_2]);
				if ( checkAll(point) )
					break;
				continue;
			}
			
			if ( step == 1 ) {
				point[messiah_1] = 1;
				step = 2;
				total_time += times[messiah_1];
				output+= ":"+Integer.toString(times[messiah_1]);
				
				continue;
			}
			if ( step == 2 ) {
				if ( checkNumber(point) ) {
					int i = times.length-1;
					while ( point[i] == 2 ) --i;
					int man_1 = i--;
					while ( point[i] == 2 ) --i;
					int man_2 = i;
					if ( times[man_2]+times[messiah_1] >= 2*times[messiah_2] ) {
						point[man_1] = 2;
						point[man_2] = 2;
						total_time += times[man_1];
						output+= ":"+Integer.toString(times[man_2])+" "+Integer.toString(times[man_1]);
					}
					else {
						point[man_1] = 2;
						point[messiah_1] = 2;
						total_time += times[man_1];
						output+= ":"+Integer.toString(times[messiah_1])+" "+Integer.toString(times[man_1]);
					}
				}
				else {
					int i = times.length-1;
					while ( point[i] == 2 ) --i;
					int man_1 = i;
					point[man_1] = 2;
					point[messiah_1] = 2;
					total_time += times[man_1];
					output += ":"+Integer.toString(times[messiah_1])+" "+Integer.toString(times[man_1]);
					break;
				}
				step = 3;
				continue;
			}
			if ( step == 3 ) {
				if ( point[messiah_1] == 1 ) {
					point[messiah_2] = 1;
					step = 0;
					total_time += times[messiah_2];
					output+= ":"+Integer.toString(times[messiah_2]);
					continue;
				}
				else {
					point[messiah_1] = 1;
					step = 2;
					total_time += times[messiah_1];
					output+= ":"+Integer.toString(times[messiah_1]);
					continue;
				}
			}
		}
		System.out.println(total_time);
		StringTokenizer tkr = new StringTokenizer(output,":");
		while ( tkr.hasMoreTokens() ) {
			System.out.println(tkr.nextToken());
		}
		//System.out.println(output);
		/*
		step = 0;
		for ( int k = 0 ; k < times.length ; ++k ) {
			point[k] = 1;
		}
		while ( true ) {
			if ( step == 0 ) {
				point[messiah_1] = 2;
				point[messiah_2] = 2;
				step = 1;
				total_time += times[messiah_2];
				System.out.println(times[messiah_1]+" "+times[messiah_2]);
				if ( checkAll(point) )
					break;
				continue;
			}
			
			if ( step == 1 ) {
				point[messiah_1] = 1;
				step = 2;
				total_time += times[messiah_1];
				System.out.println(times[messiah_1]);
				continue;
			}
			if ( step == 2 ) {
				if ( checkNumber(point) ) {
					int i = times.length-1;
					while ( point[i] == 2 ) --i;
					int man_1 = i--;
					if ( times[man_1]+times[messiah_1] >= 2*times[messiah_2] ) {
						while ( point[i] == 2 ) --i;
						int man_2 = i;
						point[man_1] = 2;
						point[man_2] = 2;
						total_time += times[man_2];
						System.out.println(times[man_1]+" "+times[man_2]);
					}
					else {
						point[man_1] = 2;
						point[messiah_1] = 2;
						total_time += times[man_1];
						System.out.println(times[man_1]+" "+times[messiah_1]);
					}
					
				}
				else {
					int i = times.length-1;
					while ( point[i] == 2 ) --i;
					int man_1 = i;
					point[man_1] = 2;
					point[messiah_1] = 2;
					total_time += times[man_1];
					System.out.println(times[messiah_1]+" "+times[man_1]);
					break;
				}
				step = 3;
				continue;
			}
			if ( step == 3 ) {
				if ( point[messiah_1] == 1 ) {
					point[messiah_2] = 1;
					step = 0;
					total_time += times[messiah_2];
					System.out.println(times[messiah_2]);
					continue;
				}
				else {
					point[messiah_1] = 1;
					step = 2;
					total_time += times[messiah_1];
					System.out.println(times[messiah_1]);
					continue;
				}

			}
		}*/
	//}
	
	/*
	public static boolean checkNumber(int[] point) {
		int count = 0;
		for ( int i = 0 ; i < point.length ; ++i ) {
			if ( point[i] == 1 )
				count++;
		}
		return count > 2;
	}
	
	
	public static boolean checkAll(int[] point) {
		for ( int i = 0 ; i < point.length ; ++i ) {
			if ( point[i] == 1 )
				return false;
		}
		return true;
	}
	*/
	
	public static int people[];
	public static int num_people;
	
	public static void main( String args[] ) {
		test_file();
		//test_judges();
	}
	
	private static void test_judges() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		in.nextLine();
    		int num_people = new Integer(in.nextLine());
    		people = new int[num_people];
    		for (int i = 0; i < people.length; i++) {
    			people[i] = new Integer(in.nextLine());
			}
    		moveAccrosTheBridge();
    		if ( k < num_test_cases-1 ) 
    			System.out.println();
    	}
		
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		in.nextLine();
    		int num_people = new Integer(in.nextLine());
    		people = new int[num_people];
    		for (int i = 0; i < people.length; i++) {
    			people[i] = new Integer(in.nextLine());
			}
    		moveAccrosTheBridge();
    		if ( k < num_test_cases-1 ) 
    			System.out.println();
    	}
	}

	public static void moveAccrosTheBridge( ) {
		Arrays.sort(people);
		num_people = people.length;
		System.out.println(sumMoveAccrosTheBridgeRecursive());
		num_people = people.length;
		moveAccrosTheBridgeRecursive();
		
		
		
	}

	private static int sumMoveAccrosTheBridgeRecursive() {
		if ( num_people >= 4 ) {
			int a = people[0];
			int b = people[1];
			int c = people[num_people-2];
			int d = people[num_people-1];
			if ( 2*b < a+c ) {
				num_people -= 2;
				return sumMoveAccrosTheBridgeRecursive()+a+2*b+d;
			}
			else {
				num_people -= 2;
				return sumMoveAccrosTheBridgeRecursive()+2*a+c+d;
			}
		}
		else {
			if ( num_people == 3 ) {
				int a = people[0];
				int b = people[1];
				int c = people[2];
				return c + a + b;
			}
			if ( num_people == 2 ) {
				int a = people[0];
				int b = people[1];
				return b;
			}
			if ( num_people == 1 ) {
				int a = people[0];
				return a;
			}
			if ( num_people == 0 ) {
				return 0;
			}
		}
		return 0;
	}

	public static void moveAccrosTheBridgeRecursive() {
		if ( num_people >= 4 ) {
			int a = people[0];
			int b = people[1];
			int c = people[num_people-2];
			int d = people[num_people-1];
			if ( 2*b < a+c ) {
				System.out.println(a+" "+b);
				System.out.println(a);
				System.out.println(c+" "+d);
				System.out.println(b);
				num_people -= 2;
				moveAccrosTheBridgeRecursive();
				return;
			}
			else {
				System.out.println(a+" "+d);
				System.out.println(a);
				System.out.println(a+" "+c);
				System.out.println(a);
				num_people -= 2;
				moveAccrosTheBridgeRecursive();
				return;
			}
		}
		else {
			if ( num_people == 3 ) {
				int a = people[0];
				int b = people[1];
				int c = people[2];
				System.out.println(a+" "+c);
				System.out.println(a);
				System.out.println(a+" "+b);
				return;
			}
			if ( num_people == 2 ) {
				int a = people[0];
				int b = people[1];
				System.out.println(a+" "+b);
				return;
			}
			if ( num_people == 1 ) {
				int a = people[0];
				System.out.println(a);
				return;
			}
			if ( num_people == 0 ) {
				return;
			}
		}
		
	}
	
}

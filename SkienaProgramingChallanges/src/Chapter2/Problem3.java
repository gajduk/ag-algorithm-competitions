package Chapter2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Problem3 {

	
	public static void main( String args[] ) {
    	Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());//the number of test cases
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int num_days = new Integer(in.nextLine());//the number of days over which the simulation should be run
    		int num_hartals = new Integer(in.nextLine());//the number of political parties
    		int hartals[] = new int[num_hartals];
    		for ( int j = 0 ; j < hartals.length ; ++j ) {
    			hartals[j] = new Integer(in.nextLine());
    		}
    		System.out.println(hartalSimulation_1(num_days,hartals));
    	}
    }
	
	
	/*
	public static void main( String args[] ) {
		
        FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("F:/Програмирање/UVA judge test/chapter2 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());//the number of test cases
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int num_days = new Integer(in.nextLine());//the number of days over which the simulation should be run
    		int num_hartals = new Integer(in.nextLine());//the number of political parties
    		int hartals[] = new int[num_hartals];
    		for ( int j = 0 ; j < hartals.length ; ++j ) {
    			hartals[j] = new Integer(in.nextLine());
    		}
    		System.out.println(hartalSimulation_1(num_days,hartals));
    	}
    }
	*/
	
    public static int hartalSimulation ( int days , int hartals[] ) {
        int mark[] = new int[days];
        for ( int i = 0 ; i < mark.length ; ++i ) {
            mark[i] = 0;
        }
        // 0+x*7 - Sundays
        // 1+x*7 - Mondays
        // 2+x*7 - Tuesdays
	    // 3+x*7 - Wednesdays
	    // 4+x*7 - Thursdays
	    // 5+x*7 - Fridays
	    // 6+x*7 - Saturdays
        for ( int i = 1 ; i < mark.length ; ++i ) {
            for ( int j = 0 ; j < hartals.length ; ++j ) {
                if ( hartals[j]*i < mark.length ) {
                    if ( !(hartals[j]*i % 7 == 6) && !(hartals[j]*i % 7 == 5) )
                       mark[hartals[j]*i] = 1;
                }
            }
        }
        int counter = 0;
        for ( int i = 0 ; i < mark.length ; ++i ) {
            if ( mark[i] == 1 ) ++counter;
        }
        return counter;
    }
    
    public static int hartalSimulation_1 ( int days , int hartals[] ) {
    	 int mark[] = new int[days];
    	 for ( int i = 1 ; i < mark.length ; ++i ) {
             for ( int j = 0 ; j < hartals.length ; ++j ) {
                 if ( hartals[j]*i-1 < mark.length ) {
                        mark[hartals[j]*i-1] = 1;
                 }
             }
         }
         int counter = 0;
         for ( int i = 0 ; i < mark.length ; ++i ) {
             if ( mark[i] == 1 )
            	 if ( ( i % 7 != 6 ) && ( i % 7 != 5) ) 
            	 	++counter;
         }
         return counter;
    }
   
}


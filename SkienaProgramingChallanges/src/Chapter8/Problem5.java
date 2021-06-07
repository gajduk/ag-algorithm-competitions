package Chapter8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem5 {
	
	public static final int MAX_WEIGHT = 455;
	public static final int MAX_PEOPLE = 100;
	public static boolean is_sum_weight_possible[] = new boolean[MAX_WEIGHT*MAX_PEOPLE];
	public static int time_stamp[] = new int[MAX_WEIGHT*MAX_PEOPLE];
	public static int num_adders[] = new int[MAX_WEIGHT*MAX_PEOPLE];
	public static int num_people;
	public static int weights[];
	
	
	public static void printSums () {
		for ( int k = 0 ; k < is_sum_weight_possible.length ; ++k  ) {
			if ( is_sum_weight_possible[k] ) {
				System.out.println(k);
			}
		}
	}
	
	public static void constructSums ( ) {
		is_sum_weight_possible[0] = true;
		time_stamp[0] = -1;
		num_adders[0] = 0;
		for ( int i = 0 ; i < weights.length ; ++i ) {
			//printSums();
			for ( int m = 0 ; m < is_sum_weight_possible.length ; ++m )  {
				if ( time_stamp[m] < i ) {
					int temp = num_adders[m]+1 > num_adders[m+weights[i]] ? num_adders[m+weights[i]]: num_adders[m]+1;
					if ( temp > (num_people+1) / 2) {
						continue;
					}
					is_sum_weight_possible[m+weights[i]] = true;
					num_adders[m+weights[i]] = temp;
					time_stamp[m+weights[i]] = i;
					
				}
			}
		}
	}
	
	public static int totalWeight (  ) {
		int sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += weights[i];
		}
		return sum;
	}
	
	public static void cleanAllData () {
		is_sum_weight_possible = new boolean[MAX_WEIGHT*MAX_PEOPLE];
		time_stamp = new int[MAX_WEIGHT*MAX_PEOPLE];
		num_adders = new int[MAX_WEIGHT*MAX_PEOPLE];
		num_people = 0;
	}
	
	public static void makeTeams ( ) {
		constructSums();
		//printSums();
		int total = totalWeight();
		if ( num_people % 2 == 0 ) {
			int team_size = num_people / 2;
			int k = (total / 2);
			while ( true ) {
				if ( /* k < 0 || */ is_sum_weight_possible[k] && num_adders[k] == team_size ) {
					break;
				}
				--k;
			}
			System.out.println(k+" "+(total-k));
			return;
		}
		if ( num_people % 2 == 1 ) {
			int team_size = num_people / 2;
			int k = (total / 2);
			while ( true ) {
				if ( /* k < 0 || */ is_sum_weight_possible[k] && ( num_adders[k] == team_size+1 || num_adders[k] == team_size ) ) {
					break;
				}
				--k;
			}
			System.out.println(k+" "+(total-k));
		}
			
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		in.nextLine();
    		num_people = new Integer(in.nextLine());
    		weights = new int[num_people];
    		for ( int m = 0 ; m < num_people ; ++m ) {
    			weights[m] = new Integer(in.nextLine());
    		}
    		makeTeams();
    		cleanAllData();
    		if ( k < num_test_cases-1 )
    			System.out.println();
    	}
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter8 problem5.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		in.nextLine();
    		num_people = new Integer(in.nextLine());
    		weights = new int[num_people];
    		for ( int m = 0 ; m < num_people ; ++m ) {
    			weights[m] = new Integer(in.nextLine());
    		}
    		makeTeams();
    		cleanAllData();
    		if ( k < num_test_cases-1 )
    			System.out.println();
    	}
	}
	
	public static void main ( String args[] ) {
		//test_judge();
		test_file();
	}
	
}

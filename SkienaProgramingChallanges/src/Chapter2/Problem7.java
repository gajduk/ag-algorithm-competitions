package Chapter2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem7 {
	
	public static final char INCORRECT = 'I';
	public static final char CORRECT = 'C';
	public static final int MAX_CONTESTANTS = 101;
	public static final int NUM_PROBLEMS = 10;
	public static boolean correct_submision[][] = new boolean[MAX_CONTESTANTS][NUM_PROBLEMS];
	public static int penalty_time[] = new int[MAX_CONTESTANTS];
	public static int incorrect_submissions[][] = new int[MAX_CONTESTANTS][NUM_PROBLEMS];
	public static boolean is_contestant_active[] = new boolean[MAX_CONTESTANTS];
	
	public static int countCorrectSubmisions ( int contestant_id ) {
		int result = 0;
		for (int i = 0; i < correct_submision[contestant_id].length; i++) {
			if ( correct_submision[contestant_id][i] )
				++result;
		}
		return result;
	}
	
	public static void populate ( ArrayList<String> subbmisions_list ) {
		for (int i = 0; i < subbmisions_list.size() ; i++) {
			StringTokenizer tkr = new StringTokenizer(subbmisions_list.get(i) );
			int contestant_id = new Integer(tkr.nextToken());
			int problem_id = new Integer(tkr.nextToken());
			int time_stamp = new Integer(tkr.nextToken());
			char status = tkr.nextToken().charAt(0);
			if ( status == CORRECT ) {
				is_contestant_active[contestant_id] = true;
				if ( ! correct_submision[contestant_id][problem_id] ) {
					penalty_time[contestant_id] += time_stamp;
				}
				correct_submision[contestant_id][problem_id] = true;
			}
			else {
				if ( status == INCORRECT ) {
					is_contestant_active[contestant_id] = true;
					if (! correct_submision[contestant_id][problem_id] ) {
						++incorrect_submissions[contestant_id][problem_id]; 
					}
				}
				is_contestant_active[contestant_id] = true;
			}
		}
		for ( int i = 0 ; i < incorrect_submissions.length ; ++i ) {
			for ( int j = 0 ; j < incorrect_submissions[i].length ; ++j ) {
				if ( correct_submision[i][j] ) {
					penalty_time[i] += 20 * incorrect_submissions[i][j];
				}
			}
		}
	}
	
	public static void printLeaderBoard( ArrayList<String> subbmisions_list  ) {
		populate(subbmisions_list);
		int indeces[] = new int[MAX_CONTESTANTS];
		int num_contestants = 0;
		for (int i = 0; i < is_contestant_active.length; i++) {
			if ( is_contestant_active[i] ) {
				indeces[num_contestants++] = i; 
			}
		}
		//System.out.println(num_contestants);
		for ( int i = 0  ; i < num_contestants ; ++i ) {
			for ( int m = 0 ; m < num_contestants-1 ; ++m ) {
				if ( penalty_time[indeces[m]] > penalty_time[indeces[m+1]] ) {
					int temp = indeces[m];
					indeces[m] = indeces[m+1];
					indeces[m+1] = temp;
				}
			}
		}
		
		for ( int i = 0  ; i < num_contestants ; ++i ) {
			for ( int m = 0 ; m < num_contestants-1 ; ++m ) {
				if ( countCorrectSubmisions(indeces[m]) < countCorrectSubmisions(indeces[m+1]) ) {
					int temp = indeces[m];
					indeces[m] = indeces[m+1];
					indeces[m+1] = temp;
				}
			}
		}
		
		for (int i = 0; i < num_contestants; i++) {
			System.out.println(indeces[i]+" "+countCorrectSubmisions(indeces[i])+" "+penalty_time[indeces[i]]);
		}
	}
	
	public static void main ( String args[] ) {
	//	test_file();
		test_judge();
	}
	
	public static void cleanAllData() {
		correct_submision = new boolean[MAX_CONTESTANTS][NUM_PROBLEMS];
		penalty_time = new int[MAX_CONTESTANTS];
		is_contestant_active = new boolean[MAX_CONTESTANTS];
		incorrect_submissions = new int[MAX_CONTESTANTS][NUM_PROBLEMS];
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
		int test_case_id = 0;
		ArrayList<String> submissions_list = new ArrayList<String>();
		while ( true ) {
			if (! in.hasNext() ) {
				cleanAllData();
				printLeaderBoard(submissions_list);
				break;
			}
			String line = in.nextLine();
			if ( line.equals("") ) {
			//	System.out.println(test_case_id);
				if ( test_case_id != 0 ) {
					cleanAllData();
					printLeaderBoard(submissions_list);
					submissions_list = new ArrayList<String>();
					System.out.println();
				}
				if ( test_case_id == num_test_cases ) {
					break;
				}
				test_case_id++;
				
			}
			else {
				submissions_list.add(line);
			}
		}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter2 problem7.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
		int test_case_id = 0;
		ArrayList<String> submissions_list = new ArrayList<String>();
		while ( true ) {
			if (! in.hasNext() ) {
				cleanAllData();
				printLeaderBoard(submissions_list);
				break;
			}
			String line = in.nextLine();
			if ( line.equals("") ) {
			//	System.out.println(test_case_id);
				if ( test_case_id != 0 ) {
					cleanAllData();
					printLeaderBoard(submissions_list);
					submissions_list = new ArrayList<String>();
					System.out.println();
				}
				if ( test_case_id == num_test_cases ) {
					break;
				}
				test_case_id++;
				
			}
			else {
				submissions_list.add(line);
			}
		}
	}
	

}

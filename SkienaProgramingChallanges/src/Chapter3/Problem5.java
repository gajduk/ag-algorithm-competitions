package Chapter3;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Problem5 {
	
	
	/*
	//compares two string arrays to see if they are completely identical
	public static boolean compareEqual ( String correct_solution[] , String submited_solution[] ) {
		String corect = "";//the order in the correct solution
		String submited = "";//the order in the submitted solution
		
		for ( int i = 0 ; i < correct_solution.length  ; ++i ) {
			for ( int j = 0 ; j < correct_solution[i].length() ; ++j ) {
				corect += correct_solution[i].substring(j,j+1);
			}
		}
		
		for ( int i = 0 ; i < submited_solution.length  ; ++i ) {
			for ( int j = 0 ; j < submited_solution[i].length() ; ++j ) {
				submited += submited_solution[i].substring(j,j+1);
			}
		}
		//System.out.println(submited_numerical+"  "+corect_numberical);
		return corect.compareTo(submited) == 0;
	}
	*/
	
	//compares two string arrays to see if they are completely identical
		public static boolean compareEqual ( String correct_solution[] , String submited_solution[] ) {
			String corect = "";//the order in the correct solution
			String submited = "";//the order in the submitted solution
			if ( correct_solution.length != submited_solution.length )
				return false;
			for ( int i = 0 ; i < correct_solution.length  ; ++i ) {
				if ( ! correct_solution[i].equals(submited_solution[i]) )
					return false;
			}
			//System.out.println(submited_numerical+"  "+corect_numberical);
			return true;
		}
	
	//compares two strings based on their numerical order
	public static boolean compareNumericalEqual ( String correct_solution[] , String submited_solution[] ) {
		String corect_numberical = "";//the numerical order in the correct solution
		String submited_numerical = "";//the numerical order in the submitted solution
		
		for ( int i = 0 ; i < correct_solution.length  ; ++i ) {
			for ( int j = 0 ; j < correct_solution[i].length() ; ++j ) {
				if ( ( correct_solution[i].charAt(j) >= '1' && correct_solution[i].charAt(j) <= '9' ) || correct_solution[i].charAt(j) == '0' )
					corect_numberical += correct_solution[i].substring(j,j+1);
			}
		}
		
		for ( int i = 0 ; i < submited_solution.length  ; ++i ) {
			for ( int j = 0 ; j < submited_solution[i].length() ; ++j ) {
				if ( ( submited_solution[i].charAt(j) >= '1' && submited_solution[i].charAt(j) <= '9') || submited_solution[i].charAt(j) == '0' )
					submited_numerical += submited_solution[i].substring(j,j+1);
			}
		}
		
		//System.out.println(submited_numerical+"  "+corect_numberical);
		return corect_numberical.compareTo(submited_numerical) == 0;
	}
	
	public static void main ( String args[] ) {
		test_judges();
		test_file();
	}
	
	public static void test_judges() {
		Scanner in = new Scanner(System.in);
		int test_case_id = 0;//the enumeration of test cases
		while ( true ) {
			int correct_solution_length = new Integer(in.nextLine());//the length in lines of the correct solution
			if ( correct_solution_length == 0 )
				break;
			++test_case_id;
			String correct_solution[] = new String[correct_solution_length];
			for ( int i = 0 ; i < correct_solution_length ; ++i ) {
				correct_solution[i] = in.nextLine();
			}
			int submited_solution_length = new Integer(in.nextLine());
			String submited_solution[] = new String[submited_solution_length];
			for ( int i = 0 ; i < submited_solution_length ; ++i ) {
				submited_solution[i] = in.nextLine();
			}
			if ( compareEqual(correct_solution, submited_solution) ) {
				System.out.println("Run #"+test_case_id+": Accepted");
				continue;
			}
			if ( compareNumericalEqual(correct_solution, submited_solution) ) {
				System.out.println("Run #"+test_case_id+": Presentation Error");
				continue;
			}
			System.out.println("Run #"+test_case_id+": Wrong Answer");
		}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter3 problem5.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int test_case_id = 0;//the enumeration of test cases
		while ( true ) {
			int correct_solution_length = new Integer(in.nextLine());//the length in lines of the correct solution
			if ( correct_solution_length == 0 )
				break;
			++test_case_id;
			String correct_solution[] = new String[correct_solution_length];
			for ( int i = 0 ; i < correct_solution_length ; ++i ) {
				correct_solution[i] = in.nextLine();
			}
			int submited_solution_length = new Integer(in.nextLine());
			String submited_solution[] = new String[submited_solution_length];
			for ( int i = 0 ; i < submited_solution_length ; ++i ) {
				submited_solution[i] = in.nextLine();
			}
			if ( compareEqual(correct_solution, submited_solution) ) {
				System.out.println("Run #"+test_case_id+": Accepted");
				continue;
			}
			if ( compareNumericalEqual(correct_solution, submited_solution) ) {
				System.out.println("Run #"+test_case_id+": Presentation Error");
				continue;
			}
			System.out.println("Run #"+test_case_id+": Wrong Answer");
		}
	}
    
	

}

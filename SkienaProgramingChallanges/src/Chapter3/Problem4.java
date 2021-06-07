package Chapter3;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem4 {
	
	public static final String key_sentence = "the quick brown fox jumps over the lazy dog";
	public static final int words_length_key_sentence[] = { 3,5,5,3,5,4,3,4,3 };
	public static boolean is_replaced[] = new boolean[26];
	public static char replacement[] = new char[26];
	public static final int MAX_LINES = 101;
	public static int num_lines = 0;
	public static String lines[]= new String[MAX_LINES];
	public static boolean possible_key[] = new boolean[MAX_LINES];
	
	public static void cleanAllData() {
		is_replaced = new boolean[26];
		replacement = new char[26];
		lines = new String[MAX_LINES];
	}
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		cleanAllData();
			num_lines = 0;
    		String current_line = in.nextLine();
    		while ( ! current_line.equals("")) {
    			lines[num_lines++] = current_line;
    			if ( !  in.hasNext() )
    				break;
    			current_line = in.nextLine();
    		}
			decode();
			if ( h < num_test_cases-1 ) {
				System.out.println();
			}
    	}
	}

	private static void test_file() {
		FileInputStream inputFile = null;    
	    try {   
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter3 problem4.txt");
	    }
    	catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		cleanAllData();
			num_lines = 0;
    		String current_line = in.nextLine();
    		while ( ! current_line.equals("")) {
    			lines[num_lines++] = current_line;
    			if ( !  in.hasNext() )
    				break;
    			current_line = in.nextLine();
    		}
			decode();
			if ( h < num_test_cases-1 ) {
				System.out.println();
			}
    	}
	}
	
	public static void validatePossbileKays () {
		StringTokenizer tkr_key = new StringTokenizer(key_sentence);
		
		for ( int k = 0 ; k < num_lines ; ++k ) {
			StringTokenizer tkr = new StringTokenizer(lines[k]);
			if ( tkr.countTokens() == tkr_key.countTokens() ) {
				int m;
				for ( m = 0 ; m < words_length_key_sentence.length ; ++m ) {
					if ( words_length_key_sentence[m] != tkr.nextToken().length() ) {
						possible_key[k] = false;
						break;
					}
				}
				if ( m == words_length_key_sentence.length ) {
					possible_key[k] = true;
				}
			}
			else {
				possible_key[k] = false;
				continue;
			}
			
		}
		
	}
	
	public static void decode() {
		validatePossbileKays();
		for ( int k = 0 ; k < num_lines ; ++k ) {
			if ( possible_key[k] ) {
				char temp_replacemt[] = new char[26];
				boolean temp_replaced[] = new boolean[26];
				copy(temp_replacemt,temp_replaced);
				if ( translateWords(lines[k],key_sentence) ) {
					if ( ! checkReplacement() ) {
						copyBack(temp_replacemt, temp_replaced);
					}
					else {
						translate();
						return;
					}
				}
				else {
					copyBack(temp_replacemt, temp_replaced);
				}
			}
		}
		System.out.println("No solution.");
	}

	public static boolean checkReplacement() {
		return true;
		/*
		 for ( int k = 0 ; k < num_lines ; ++k ) {
		 
			if ( tryTranslateWords(lines[k]) ) ) {
				
			}
		}
		return false;
		/*/
	}

	public static boolean tryTranslateWords(String string) {
		return true;
	}

	public static void translate() {
		for ( int k = 0 ; k < num_lines ; ++k ) {
			System.out.println(translateSentence(lines[k]));
		}
	}

	public static String translateSentence ( String string ) {
		String result = "";
		for ( int k = 0 ; k < string.length() ; ++k ) {
			if ( string.charAt(k) == ' ' ) {
				result += " ";
			}
			else {
				result += replacement[string.charAt(k)-'a'];
			}
		}
		return result;
	}

	public static boolean translateWords(String current_word, String word) {
		
		for ( int k = 0 ; k < current_word.length() ; ++k ) {
			if ( current_word.charAt(k) == ' ' )
				continue;
			if (  is_replaced[current_word.charAt(k)-'a'] && replacement[current_word.charAt(k)-'a'] != word.charAt(k) ) {
		//		System.out.println(current_word);
				return false;
			}
			else {
				is_replaced[current_word.charAt(k)-'a'] = true;
				replacement[current_word.charAt(k)-'a'] = word.charAt(k);
			}
		}
		//System.out.println(current_word);
		return true;
	}
	
	private static void copy(char[] temp_replacemt, boolean[] temp_replaced) {
		for ( int l = 0 ; l < 26 ; ++l ) {
			temp_replacemt[l] = replacement[l];
			temp_replaced[l] = is_replaced[l];
		}
	}

	private static void copyBack(char[] temp_replacemt, boolean[] temp_replaced) {
		for ( int l = 0 ; l < 26 ; ++l ) {
			replacement[l] =  temp_replacemt[l];
			is_replaced[l] = temp_replaced[l];	
		}
	}

	

}

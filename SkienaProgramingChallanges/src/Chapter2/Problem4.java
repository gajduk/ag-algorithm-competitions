package Chapter2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem4 {
	/*
	    public static void main( String args[] ) {
	        String dict[] = {"qwertyuiopasdfghjklzxcvbnm"};
	        System.out.println(decrypt(dict,"zxcvbnmasdfghjklqwertyuiop"));
	        //test();
	        
	    }

	    static char replacement[] = new char[26];
	    static boolean used[] = new boolean[26];

	    public static void test() {
	        
	        System.out.println("Testing posibleMathc function");
	        System.out.println("input: andrej , andrej ; output:"+possibleMatch("andrej", "andrej"));
	        System.out.println("input: andrej , a..... ; output:"+possibleMatch("andrej", "a....."));
	        System.out.println("input: andrej , ...... ; output:"+possibleMatch("andrej", "......"));
	        System.out.println("input: andrej , .....j ; output:"+possibleMatch("andrej", ".....j"));
	        System.out.println("input: andrej , qwerty ; output:"+possibleMatch("andrej", "qwerty"));
	        System.out.println("input: andrej , and.qo ; output:"+possibleMatch("andrej", "and.qj"));
	         
	         
	        System.out.println("Testing checkPermutation function");
	        for ( int i = 0 ; i < 26 ; ++i ) {
	            replacement[i] = '.';
	            used[i] = true;
	        }
	        String dict[] = { "and", "cvbcx", "poin"};
	        
	        System.out.println(checkPermutation_forTest(dict,"qwe rtyfg uiop"));
	        
	    }
	    
	    public static String decrypt( String dictionary[] , String crypted_text ) {
	        for ( int i = 0 ; i < 26 ; ++i ) {
	            replacement[i] = '.';
	            used[i] = false;
	        }
	        findCryptingAlgorithm(dictionary,crypted_text,0);
	        
	        return decrypt(crypted_text);
	    }

	    public static boolean findCryptingAlgorithm ( String dictionary[] , String crypted_text , int letter ) {
	        if ( letter == 26) return true;
	        for ( int i = 0 ; i < 26 ; ++i ) {
	            
	            if ( !used[i] ) {
	                if ( letter == 0 ) {
	                    System.out.println("Checking varitants for "+(char)( letter+'a')+" = " +(char)( i+'a'));
	                }
	                replacement[letter] = (char) (i + 'a');
	                used[i] = true;
	                if ( checkPermutation(dictionary,crypted_text) )
	                    if ( findCryptingAlgorithm(dictionary,crypted_text,letter+1) )
	                        return true;
	                    else {
	                        used[i] = false;
	                    }
	                else {
	                    //replacement[letter] = (char) (i + 'a');
	                    used[i] = false;
	                }
	            }
	        }
	        return false;
	    }

	    public static boolean checkPermutation_forTest(String[] dictionary, String crypted_text) {
	        StringTokenizer tkr = new StringTokenizer(crypted_text," ");
	        while ( tkr.hasMoreTokens() ) {
	            String crypted_word = tkr.nextToken();
	            String decrypted_word = decrypt(crypted_word);
	            boolean flag = false;
	            
	            for ( int i = 0 ; i < dictionary.length ; ++i ) {
	                System.out.print("Trying to match words "+decrypted_word+" = "+crypted_word+" & "+dictionary[i]);
	                if ( possibleMatch(dictionary[i],decrypted_word) ) {
	                    flag = true;
	                    System.out.println("  good match.");
	                    break;
	                }
	                System.out.println();
	            }
	            if ( ! flag ) {
	                System.out.println("Not a valid permutation");
	                return false;
	            }
	        }
	        System.out.println("Valid permutation");
	        return true;
	    }

	    public static boolean checkPermutation(String[] dictionary, String crypted_text) {
	        StringTokenizer tkr = new StringTokenizer(crypted_text," ");
	        while ( tkr.hasMoreTokens() ) {
	            String crypted_word = tkr.nextToken();
	            String decrypted_word = decrypt(crypted_word);
	            boolean flag = false;
	            for ( int i = 0 ; i < dictionary.length ; ++i ) {
	                if ( possibleMatch(dictionary[i],decrypted_word) ) {
	                    flag = true;
	                }
	            }
	            if ( ! flag ) return false;
	        }
	        return true;
	    }

	    public static String decrypt(String crypted_word) {
	        char word[] = new char[crypted_word.length()];
	        for ( int i = 0 ; i < word.length ; ++i ) {
	            if ( crypted_word.charAt(i) >= 'a' && crypted_word.charAt(i) <= 'z') {
	                if ( used[crypted_word.charAt(i)-'a'] ) {
	                    word[i] = replacement[crypted_word.charAt(i)-'a'];
	                }
	                else {
	                    word[i] = '.';
	                }
	            }
	             else {
	                    word[i] = crypted_word.charAt(i);
	             }
	        }
	        return new String(word);
	    }

	    public static boolean possibleMatch(String string, String decrypted_word) {
	        if ( string.length() != decrypted_word.length() ) return false;
	      //  boolean flag = true;
	        for ( int i = 0 ; i < string.length() ; ++i ) {
	           if ( decrypted_word.charAt(i) != '.' )
	               if ( decrypted_word.charAt(i) != string.charAt(i) )
	                 return false;
	        }
	        return true;
	    }
	*/
	
	public static final int MAX_WORDS = 1001;
	public static final int MAX_WORD_LENGTH = 1001;
	
	public static boolean is_replaced[] = new boolean[26];
	public static boolean is_used[] = new boolean[26];
	public static char replacement[] = new char[26];
	public static char  replacement_back[] = new char[26];
	public static String dictionary[][] = new String[MAX_WORD_LENGTH][MAX_WORDS];
	public static int num_words[] = new int[MAX_WORD_LENGTH];
	public static ArrayList<String> words;
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}

	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter2 problem4.txt");
	    }
    	catch ( FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_total_words = in.nextInt();
    	in.nextLine();
    	for ( int m = 0 ; m < num_total_words ; ++m ) {
    		String word = in.nextLine();
    		dictionary[word.length()][num_words[word.length()]++] = word;
    	}
    	//printDictionary();
    	while ( in.hasNext() ) {
    		System.out.println(decode(in.nextLine()));
    		cleanAllData();
    	}
	}
	
	public static void cleanAllData() {
		is_used = new boolean[26];
		is_replaced = new boolean[26];
		replacement = new char[26];
		replacement_back = new char[26];
	}
	
	public static String decode( String coded_sentance ) {
		ArrayList<String> words_1 = new ArrayList<String>();
		StringTokenizer tkr = new StringTokenizer(coded_sentance);
		while ( tkr.hasMoreTokens() ) {
			words_1.add(tkr.nextToken());
		}
		words = new ArrayList<String>();
		while ( ! words_1.isEmpty() ) {
			int max = 0;
			int max_index = 0;
			for ( int m = 0 ; m < words_1.size() ; ++m ) {
				if ( max < words_1.get(m).length() ) {
					max = words_1.get(m).length();
					max_index = m;
				}
			}
			words.add(words_1.remove(max_index));
		}
		if ( decodeRecursively(0) )
			return translate(coded_sentance);
		else
			return asterix(coded_sentance);
	}
		
	public static String asterix(String coded_sentance) {
		String result = "";
		StringTokenizer tkr = new StringTokenizer( coded_sentance );
		while ( tkr.hasMoreTokens() ) {
			String word = tkr.nextToken();
			for (int i = 0; i < word.length() ; i++) {
				if ( word.charAt(i) == ' ' )
					result += ' ';
				else {
					result += '*';
				}
			}
			if ( tkr.hasMoreTokens() ) {
				result += " ";
			}
		}
		return result;
	}

	public static String translate( String coded_sentance ) {
		String result = "";
		StringTokenizer tkr = new StringTokenizer( coded_sentance );
		while ( tkr.hasMoreTokens() ) {
			String word = tkr.nextToken();
			for (int i = 0; i < word.length() ; i++) {
				if ( word.charAt(i) == ' ' )
					result += ' ';
				else {
					result += replacement[word.charAt(i)-'a'];
				}
			}
			if ( tkr.hasMoreTokens() ) {
				result += " ";
			}
		}
		return result;
	}

	public static boolean decodeRecursively( int current_word_index ) {
		String current_word = words.get(current_word_index);
		for ( int m = 0 ; m < num_words[current_word.length()] ; ++m ) {
			char temp_replacemt[] = new char[26];
			boolean temp_replaced[] = new boolean[26];
			char temp_replacement_back[] = new char[26];
			boolean temp_used[] = new boolean[26];
			copy(temp_replacemt,temp_replaced,temp_replacement_back,temp_used);
			//System.out.println(current_word+"  "+""+dictionary[current_word.length()][m]);
			if ( translateWords(current_word,dictionary[current_word.length()][m]) ) {
			//	System.out.println(current_word+"  "+""+dictionary[current_word.length()][m]);
				if ( current_word_index == words.size()-1 ) {
					//System.out.println(current_word+"  "+""+dictionary[current_word.length()][m]);
					return true;
				}
				if ( decodeRecursively(current_word_index+1) ) {
					//System.out.println(current_word+"  "+""+dictionary[current_word.length()][m]);
					return true;
				}
				else {
					copyBack(temp_replacemt,temp_replaced,temp_replacement_back,temp_used);
				}
			}
			else {
				copyBack(temp_replacemt,temp_replaced,temp_replacement_back,temp_used);
			}
		}
		return false;
	}

	public static boolean translateWords(String current_word, String word) {
		for ( int k = 0 ; k < current_word.length() ; ++k ) {
			if (  ( is_used[word.charAt(k)-'a'] && replacement_back[word.charAt(k)-'a'] != current_word.charAt(k) ) || ( is_replaced[current_word.charAt(k)-'a'] ) && replacement[current_word.charAt(k)-'a'] != word.charAt(k) ) {
				return false;
			}
			else {
				is_used[word.charAt(k)-'a'] = true;
				replacement_back[word.charAt(k)-'a'] = current_word.charAt(k);
				is_replaced[current_word.charAt(k)-'a'] = true;
				replacement[current_word.charAt(k)-'a'] = word.charAt(k);
			}
		}
		return true;
	}

	private static void copy(char[] temp_replacemt, boolean[] temp_replaced , char[] temp_replacement_back, boolean[] temp_used ) {
		for ( int l = 0 ; l < 26 ; ++l ) {
			temp_replacemt[l] = replacement[l];
			temp_replaced[l] = is_replaced[l];
			temp_replacement_back[l] = replacement_back[l];
			temp_used[l] = is_used[l];
		}
	}

	private static void copyBack(char[] temp_replacemt, boolean[] temp_replaced , char[] temp_replacement_back, boolean[] temp_used ) {
		for ( int l = 0 ; l < 26 ; ++l ) {
			replacement[l] =  temp_replacemt[l];
			is_replaced[l] = temp_replaced[l];	
			replacement_back[l] = temp_replacement_back[l];
			is_used[l] = temp_used[l];
		}
	}

	public static void printDictionary ( ) {
		for ( int i = 0 ; i < dictionary.length ; ++i ) {
			if ( num_words[i] != 0 ) {
				System.out.print("Words of length "+i+": ");
				for ( int k = 0 ; k < num_words[i] ; ++k ) {
					System.out.print(dictionary[i][k]+" ,");
				}
				System.out.println("");
			}
		}
	}
	
	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_total_words = in.nextInt();
    	in.nextLine();
    	for ( int m = 0 ; m < num_total_words ; ++m ) {
    		String word = in.nextLine();
    		dictionary[word.length()][num_words[word.length()]++] = word;
    	}
    	//printDictionary();
    	while ( in.hasNext() ) {
    		System.out.println(decode(in.nextLine()));
    		cleanAllData();
    	}
	}
	
	
	
}

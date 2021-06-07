package Section1_3;
/*
ID: gajduk_01
LANG: JAVA
TASK: calfflac
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class calfflac {
	
	public static char in_string[] = new char[40001];
	public static char in_letters[] = new char[40001];
	public static int locations[] = new int[40001];
	public static int length;
	public static int length_letters;
	public static int temp;
	
	public static boolean isAPallindrome ( String a ) {
		
		for ( int i = 0 , k = a.length()-1 ;  ;) {
			while ( i < a.length() && !Character.isLetter(a.charAt(i)) ) {
				++i;
			}
			while ( k >= 0 && !Character.isLetter(a.charAt(k)) ) {
				--k;
			}
			if ( i == a.length() || k == -1 ) {
				return true;
			}
			if ( Character.toLowerCase(a.charAt(i)) != Character.toLowerCase(a.charAt(k)) ) {
				return false;
			}
			
			++i;
			--k;
		}
	}
	
	public static boolean isAPallindrome ( int start , int end  ) {
		temp = 0;
		for ( int i = start , k = end ;  ;) {
			while ( i <= end && !Character.isLetter(in_string[i]) ) {
				++i;
				
			}
			++temp;
			while ( k >= start && !Character.isLetter(in_string[k]) ) {
				--k;
			}
			++temp;
			if ( Character.toLowerCase(in_string[i]) != Character.toLowerCase(in_string[k]) ) {
				return false;
			}
			if ( i >= k ) {
				if ( i == k )
					--temp;
				return true;
			}
			++i;
			--k;
		}
	}
	
	public static void main ( String args[] ) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
	    StringBuffer text = new StringBuffer("");
	    length = f.read(in_string, 0, 40000);
	   // int counter_letters = 0;
	   // for ( int i = 0 ; i < length ; ++i ) {
	   // 	if ( Character.isLetter(in_string[i]) ) {
	   // 		locations[counter_letters] = i;
	   // 		in_letters[counter_letters++] = Character.toLowerCase(in_string[i]);
	   // 	}
	   // }
	   // length_letters = counter_letters;
	   // print(in_letters);
	   //  System.out.println("read the file");
	    String result = findLongestPalindromeSubstring();
	    int temp = getLength(result);
	    System.out.println(temp);
	    out.println(temp);
	    out.println(result);
	    out.close();
	    System.exit(0);
	}
	
	private static void print(char[] array) {
		for ( int i = 0 ; i < length_letters ; ++i ) {
			System.out.print(array[i]);
		}
		System.out.println();
	}

	private static void print(char[] array , int start , int end) {
		for ( int i = start ; i < end ; ++i ) {
			System.out.print(array[i]);
		}
		System.out.println();
	}

	private static String findLongestPalindromeSubstringTransformed() {
		int start = 0;
		int end = 0;
		int max = 0;
		for ( int i = 0 ; i < length_letters-max ; ++i ) {
			for ( int j = i+1922 > length_letters-1 ? length_letters-1 : i+1922 ; j > i+max ; --j ) {
					//print(i,j);
					if ( isAPallindromeLetters(i, j) ) {
						
						int temp = j-i;
						if ( temp > max ) {
							max = temp;
							start = i;
							end = j;
						}
					}
			}
		}
		return new String(in_string,locations[start],locations[end]-locations[start]+1);
	}
	
	private static boolean isAPallindromeLetters(int start, int end) {
		while (  start < end && in_letters[start] == in_letters[end] ) {
			--end;
			++start;
		}
		if ( start >= end )
			return true;
		return false;
	}

	private static int getLength( String result ) {
		int counter = 0;
		for ( int i = 0 ; i < result.length(); ++i ) {
			if ( Character.isLetter(result.charAt(i)) ) {
				++counter;
			}
		}
		return counter;
	}

	public static String findLongestPalindromeSubstring (  ) {
		int start = 0;
		int end = 0;
		int max = 0;
		for ( int i = 0 ; i < length-max ; ++i ) {
			if ( Character.isLetter(in_string[i]) ) {
				for ( int j = i+1922 > length-1 ? length-1 : i+1922 ; j > i+max ; --j ) {
					if ( Character.isLetter(in_string[j]) ) {
						//System.out.println(in_string.substring(i, j));
						if ( isAPallindrome(i, j) ) {
							//temp = getLength(i,j);
							if ( temp > max ) {
								max = temp;
								start = i;
								end = j;
							}
						}
					}
				}
			}	
		}
		return new String(in_string,start,end-start+1);
	}

	private static int getLength(int start, int end) {
		int counter = 0;
		for ( int i = start ; i <= end ; ++i ) {
			if ( Character.isLetter(in_string[i]) ) {
				++counter;
			}
		}
		return counter;
	}
	
	
	/*
	public static String generateLongestPalindromeSubstring (  ) {
		int max = 0;
		for ( int i = 0 ; i < length ; ++i ) {
			int temp = generateLongestPalindrome(i);
			if ( max < temp ) {
				max = temp;
			}
		}
		System.out.println(max);
		return "asdasd";
	}
	
	public static int generateLongestPalindrome( int center ) {
		int start = center;
		int end = center;
		int length = 0;
		while ( in_string.charAt(start) == in_string.charAt(end) ) {
			++length;
			while ( start >= 0 && Character.isLetter(in_string.charAt(start)) ) {
				--start;
			}
			while ( end < in_string.length() && Character.isLetter(in_string.charAt(end)) ) {
				++end;
			}
			
		}
		int result =  length;
		start = center;
		end = center;
		length = 0;
		while ( end < in_string.length() && Character.isLetter(in_string.charAt(end)) ) {
			++end;
		}
		while ( in_string.charAt(start) == in_string.charAt(end) ) {
			++length;
			while ( start >= 0 && Character.isLetter(in_string.charAt(start)) ) {
				--start;
			}
			while ( end < in_string.length() && Character.isLetter(in_string.charAt(end)) ) {
				++end;
			}
		}
		if ( length > result ) {
			result = length;
		}
		start = center;
		end = center;
		length = 0;
		while ( start >= 0 && Character.isLetter(in_string.charAt(start)) ) {
			--start;
		}
		while ( in_string.charAt(start) == in_string.charAt(end) ) {
			++length;
			while ( start >= 0 && Character.isLetter(in_string.charAt(start)) ) {
				--start;
			}
			while ( end < in_string.length() && Character.isLetter(in_string.charAt(end)) ) {
				++end;
			}
		}
		if ( length > result ) {
			result = length;
		}
		return result;
	}
	*/
	
}

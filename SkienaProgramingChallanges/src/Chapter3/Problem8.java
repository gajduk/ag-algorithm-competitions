package Chapter3;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem8 {
	
	public static final int LINE_SIZE = 72;
	public static final int BUFF_SIZE = 2*1024*1024;
	
	
	public static int fmt( char src[], int isize, char dest[] ) {
		  int osize = 0;
		  for (int first = 0, last = first; first < isize; first = last) {
			    for (int next = last, nonblank = 0, word = 0; last < isize; last = next) {
				      // search for the next word
				      for (; next < isize && src[next] != ' ' && src[next] != '\n'; ++next) { 
				           ++nonblank;
				      }
				      ++word;
				      // keep line break
				      if (next < isize && src[next] == '\n' &&  (src[next+1] == ' ' || src[next+1] == '\n' || nonblank == 0 || next + 1 == isize)) {
					        last = next + 1;
					        break;
				      }
				      // break the line
				      if (next - first > LINE_SIZE) {
					        // exhaust all blanks
					        for (last = word > 1 ? last : next + 1; src[last] == ' '; ++last) {}
					        src[last-1] = '\n';
					        break;
				      }
				      src[next++] = ' '; // eliminate newline
				}
				copy(src,first,last,dest,osize);
				osize += last - first;
				if ( dest[osize-1] == '\n' || dest[osize-1] == ' ' ) { // eliminate trailing spaces
					for ( ; osize > 1 && dest[osize-2] == ' '; --osize) {}
				    dest[osize-1] = '\n';
				}
		  }
		  return osize;
	}
	
	public static void copy(char[] src, int first, int last, char[] dest, int start ) {
		for ( int k = first ; k < last ; ++k ) {
			dest[start+k-first] = src [k];
		}
	}

	public static final int MAX_LINE_LENGT = 72;
	
	public static String fmtTransform ( String text ) {
		int walker = 0;
		int current_line_length = 0;
		String result = "";
		while ( walker < text.length() ) {
			int word_start = -1;
			int word_end = -1;
			if (text.charAt(walker) == '\n' ) {
				if ( walker+1 >= text.length() || text.charAt(walker+1) == '\n' || text.charAt(walker+1) == ' '  ) {
					result += "\n";
					current_line_length = 0;
				}
				else {
					if ( result.length() == 0 || result.charAt(result.length()-1) == '\n' || checkBlankLine(result) ) {
						result += "\n";
						current_line_length = 0;
					}
					else {
						result += " ";
						++current_line_length;
					}
				}
				++walker;

			}
			boolean new_line_inserted = false;
			while ( walker < text.length() && text.charAt(walker) == ' ' ) {
				if ( current_line_length < MAX_LINE_LENGT ) {
					if ( result.length() == 0 ) {
						result += text.charAt(walker);
						++current_line_length;
					}
					else {
						if ( result.charAt(result.length()-1) != '\n' || !new_line_inserted ) {
							result += text.charAt(walker);
							++current_line_length;
						}
					}
					++walker;
				}
				else {
					new_line_inserted = true;
					int spaces = result.length()-1;
					while ( spaces >= 0 && result.charAt(spaces) == ' ' ) {
						--spaces;
					}
					result = result.substring(0,spaces+1);
					current_line_length = 0;
					result += '\n';
				}
			}
			word_start = walker;
			word_end = word_start;
			while ( walker < text.length() && text.charAt(walker) != ' ' && text.charAt(walker) != '\n' ) {
				++walker;
			}
			word_end = walker;
			int word_length = word_end - word_start;
			if ( current_line_length + word_length < MAX_LINE_LENGT ) {
				result += text.substring(word_start,word_end);
				current_line_length += word_length;
			}
			else {
				if ( result.length() == 0 || result.charAt(result.length()-1) == '\n' ) {
					result += text.substring(word_start,word_end);
					current_line_length = word_length;
				}
				else {
					int spaces = result.length()-1;
					while ( spaces >= 0 && result.charAt(spaces) == ' ' ) {
						--spaces;
					}
					result = result.substring(0,spaces+1);
					result += '\n';
					result += text.substring(word_start,word_end);
					current_line_length = word_length;
				}
			}
		}
		int spaces = result.length()-1;
		while ( spaces >= 0 && result.charAt(spaces) == ' ' ) {
			--spaces;
		}
		result = result.substring(0,spaces+1);
		return result;
	}
	
	
	
	private static boolean checkBlankLine( String rext) {
		for ( int i = rext.lastIndexOf('\n') ; i < rext.length() ; ++i ) {
			if ( rext.charAt(i) != ' ' ) {
				return false;
			}
		}
		return true;
	}



	public static void main ( String args[] ) {
		 test_file();
		 //test_judge();
	}
	
	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	String text = "";
    	while ( in.hasNext() ) {
    		text += in.nextLine();
    		if ( in.hasNext() ) {
    			text += "\n";
    		}
    	}
    	char src[] = new char[BUFF_SIZE];
    	char dest[] = new char[BUFF_SIZE];
    	char temp[] = text.toCharArray();
    	//System.out.println(text);
    	copy(temp,0,temp.length,src,0);
    	int dest_length = fmt(src,temp.length,dest);
    	print(dest,dest_length);
	}



	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter3 problem8.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	String text = "";
    	while ( in.hasNext() ) {
    		text += in.nextLine();
    		if ( in.hasNext() ) {
    			text += "\n";
    		}
    	}
    	char src[] = new char[BUFF_SIZE];
    	char dest[] = new char[BUFF_SIZE];
    	char temp[] = text.toCharArray();
    	//System.out.println(text);
    	copy(temp,0,temp.length,src,0);
    	int dest_length = fmt(src,temp.length,dest);
    	print(dest,dest_length);
	}

	public static void print(char[] text , int length) {
		for ( int k = 0 ; k < length ; ++k ) {
			System.out.print(text[k]);
		}
		//System.out.println();
	}

}

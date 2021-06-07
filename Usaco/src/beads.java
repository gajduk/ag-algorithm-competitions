/*
ID: gajduk_01
LANG: JAVA
TASK: beads
*/



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class beads {
	
	
	public static void main (String [] args) throws IOException {
		//test_count();
		
	    BufferedReader f = new BufferedReader(new FileReader("beads.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
	    int num_beads = new Integer(f.readLine());
	    String beads = f.readLine();
	   // int length = countLongestSequenceBonus(beads+new StringBuffer(beads).reverse().toString());
	    int length = 0;
	    for ( int i = 0 ; i < beads.length() ; ++i ) {
	    	int temp = countLongestSequenceBonus(beads.substring(i)+beads.substring(0,i));
	    	if ( temp > length ) {
	    		length = temp;
	    	}
	    }
	    out.println(length);
	    out.close();
	   
	}
	
	public static void test_count() {
		String test = "rrr";
		System.out.println(max(countLongestSequenceBonus(test),countLongestSequenceBonus(test.substring(test.length()/2)+test.substring(0,test.length()/2))));
	}

	private static int max( int a, int b ) {
		return a > b ? a : b;
	}

	private static int countLongestSequenceBonus ( String beads ) {
		int max = 0;
		for ( int i = 0 ; i < beads.length() ; ++i ) {
			int changes = 0;
			int current_length = 0;
			int current_color = beads.charAt(i);
			for ( int k = i+1 ; k < beads.length() ; ++k ) {
				//System.out.print((char)current_color);
				if ( beads.charAt(k) == 'r' ) {
					if ( current_color == 'b' ) {
						++changes;
						
					}
					current_color = 'r';
				}
				else {
					if ( beads.charAt(k) == 'b' ) {
						if ( current_color == 'r' ) {
							++changes;
							
						}
						current_color = 'b';
					}
				}
				current_length = k - i;
				if ( changes == 2 ) {
					break;
				}
				++current_length;
			}
			//System.out.println();
			if ( max < current_length ) {
				 max = current_length;
			}
		}
		return max;
	}

	private static int countLongestSequence( String beads ) {
		ArrayList<Integer> sequences = new ArrayList<Integer>();
		int current_number = 0;
		int current_color = beads.charAt(0);
		for ( int i = 1 ; i < beads.length() ; ++i ) {
			if ( beads.charAt(i) == current_color || beads.charAt(i) == 'w' ) {
				++current_number;
			}
			else {
				sequences.add(current_number);
				current_number = 0;
			}
		}
		if ( beads.charAt(0) == beads.charAt(beads.length()-1) ) {
			sequences.add(sequences.remove(0)+sequences.remove(sequences.size()-1));
		}
		int max = 0;
		for ( int i = 0 ; i < sequences.size()-1 ; ++i ) {
			if ( max < sequences.get(i)+sequences.get(i+1) ) {
				max = sequences.get(i)+sequences.get(i+1);
			}
		}
		return max;
	}
	

}

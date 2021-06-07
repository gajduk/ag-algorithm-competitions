/*
ID: gajduk_01
LANG: JAVA
TASK: beads
*/

package Section1_1;

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
	    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
	    int num_beads = new Integer(f.readLine());
	    String beads = f.readLine();
	    int length = countLongestSequenceBonus(beads+new StringBuffer(beads).reverse().toString());
	    out.println(length);
	    out.close();
	}
	
	private static int countLongestSequenceBonus ( String beads ) {
		int max = 0;
		for ( int i = 0 ; i < beads.length() ; ++i ) {
			int changes = 0;
			int current_length = 0;
			int current_color = beads.charAt(i);
			for ( int k = i+1 ; k < beads.length() ; ++k ) {
				if ( beads.charAt(k) == 'r' ) {
					if ( current_color == 'b' ) {
						++changes;
						
						if ( changes == 2 ) {
							break;
						}
					}
					current_color = 'r';
				}
				if ( beads.charAt(k) == 'b' ) {
					if ( current_color == 'r' ) {
						++changes;
						
						if ( changes == 2 ) {
							break;
						}
					}
					current_color = 'b';
				}
				current_length = k - i;
			}
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

package August_30_2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MaximumCraziness {
	
	public static void main ( String args[] ) {
		int test[] = {5,10,25,25,40};
		System.out.println(getMaximimCraziness(test));
	}
	
	public static int getMaximimCraziness( int h[] ) {
		Arrays.sort(h);
		ArrayList<Integer> resulting_sequence = new ArrayList<Integer>();
		resulting_sequence.add(h[h.length-1]);
		boolean used[] = new boolean[h.length];
		used[h.length-1] = true;
		int total_dif = 0;
		for ( int i = 0 ; i < used.length-1 ; ++i ) {
			int max_difference = -1;
			int index = 0;
			boolean begin = false;
			for ( int k = 0 ; k < h.length ; ++k ) {
				if ( !used[k] ) {
					int dif1 = Math.abs(h[k]-resulting_sequence.get(0));
					int dif2 = Math.abs(h[k]-resulting_sequence.get(resulting_sequence.size()-1));
					if ( dif1 > max_difference ) {
						max_difference = dif1;
						index = k;
						begin = true;
					}
					if ( dif2 > max_difference ) {
						max_difference = dif2;
						index = k;
						begin = false;
					}
				}
			}
			used[index] = true;
			total_dif += max_difference;
			if ( begin ) {
				resulting_sequence.add(0,h[index]);
			}
			else {
				resulting_sequence.add(resulting_sequence.size(),h[index]);
			}
		}
		return total_dif;
	}

}

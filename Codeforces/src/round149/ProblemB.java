package round149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class ProblemB {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader jin = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(jin.readLine());
		String nums[] = jin.readLine().split(" ");

		int l = Integer.parseInt(nums[0]);
		int m = Integer.parseInt(nums[1]);
		TreeSet<Integer> smallest = new TreeSet<Integer>();
		TreeSet<Integer> largest = new TreeSet<Integer>();
		smallest.add(0);
		largest.add(0);
		int least = l;
		int most = m;
		for ( int i = 1 ; i < n ; ++i ) {
			nums = jin.readLine().split(" ");
			l = Integer.parseInt(nums[0]);
			m = Integer.parseInt(nums[1]);
			if ( l < least ) {
				smallest.clear();
				least = l;
			}
			if ( l == least ) {
				smallest.add(i);
			}
			if ( m > most ) {
				largest.clear();
				most = m;
			}
			if ( m == most ) {
				largest.add(i);
			}
		}
		for ( Integer i : smallest ) {
			if ( largest.contains(i) ) {
				System.out.println((i+1));return;
			}
		}
		System.out.println(-1);
	}

}

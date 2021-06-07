package round150;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;


/*
 
9 8 3 4
1 4
4 2
3 4
4 5
5 6
5 7
5 8
5 9


5 5 1 1
1 2
1 3
1 4
2 5
2 4
 
 */

public class ProblemD {
	
	public static void main(String[] args) throws IOException {
		BufferedReader jin = new BufferedReader(new InputStreamReader(System.in));
		String ints[] = jin.readLine().split(" ");
		int n = Integer.parseInt(ints[0]);
		int m = Integer.parseInt(ints[1]);
		int h = Integer.parseInt(ints[2]);
		int t = Integer.parseInt(ints[3]);
		TreeSet<Integer> possible_heads = new TreeSet<Integer>();
		PrintWriter out = new PrintWriter(System.out);
		LinkedList<Integer> possible_tails = new LinkedList<Integer>();
		ArrayList<TreeSet<Integer>> neigbouhrs = new ArrayList<TreeSet<Integer>>(n);
		for ( int i = 0 ; i < n ; ++i ) {
			neigbouhrs.add(new TreeSet<Integer>());
		}
		for ( int i = 0 ; i < m ; ++i ) {
			ints = jin.readLine().split(" ");
			int a = Integer.parseInt(ints[0])-1;
			int b = Integer.parseInt(ints[1])-1;
			neigbouhrs.get(a).add(b);
			neigbouhrs.get(b).add(a);
		}
		for ( int i = 0 ; i < n ; ++i ) {
			if ( neigbouhrs.get(i).size() >= h+1 ) 
				possible_heads.add(i);
			if ( neigbouhrs.get(i).size() >= t+1 ) 
				possible_tails.add(i);
		}
		for ( int tail : possible_tails ) {
			for ( int ph : neigbouhrs.get(tail) ) {
				if ( possible_heads.contains(ph) ) {
					boolean flag = false;
					LinkedList<Integer> distinct_neig_tail = new LinkedList<Integer>();
					LinkedList<Integer> distinct_neig_head = new LinkedList<Integer>();
					int counter = 0;
					for ( Integer i : neigbouhrs.get(ph) ) {
						if ( i == tail ) continue;
						if ( ! neigbouhrs.get(tail).contains(i) ) {
							++counter;
							distinct_neig_head.add(i);
						}
						if ( counter >= h ) {
							flag = true;break;
						}
					}
					if ( !flag ) continue;
					flag = false;
					counter = 0;
					for ( Integer i : neigbouhrs.get(tail) ) {
						if ( i == ph ) continue;
						if ( ! neigbouhrs.get(ph).contains(i) ) {
							++counter;
							distinct_neig_tail.add(i);
						}
						if ( counter >= t ) {
							flag = true;break;
						}
					}
					
					if ( ! flag ) continue;
					out.println("YES");
					out.println((ph+1)+" "+(tail+1));
					flag = false;
					for ( Integer i : distinct_neig_head ) {
						if ( i == tail ) continue;
						if ( flag ) out.print(" ");
						out.print((i+1));
						flag = true;
					}
					out.println();
					flag = false;
					for ( Integer i : distinct_neig_tail ) {
						if ( i == ph ) continue;
						if ( flag ) out.print(" ");
						out.print((i+1));
						flag = true;
					}
					
					out.println(" ");

					out.close();
					return;
				}
			}
		}
		System.out.println("NO");
	}
	

}

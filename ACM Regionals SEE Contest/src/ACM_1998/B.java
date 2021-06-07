package ACM_1998;

import java.util.HashMap;
import java.util.Scanner;

public class B {
	
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			long sequence_length = in.nextLong();
			HashMap<Long,Long> map = new HashMap<Long,Long>();
			long max = 0;
			long max_num = -1;
			for ( int i = 0 ; i < sequence_length ; ++i ) {
				long t = in.nextLong();
				if ( map.containsKey(t) ) {
					long o = map.get(t);
					if ( o > max ) {
						max = o;
						max_num = t;
					}
					map.put(t,o+1);
				}
				else {
					map.put(t,1L);
					if ( 1 > max ) {
						max = 1;
						max_num = t;
					}
				}
			}
			if ( max >= sequence_length/2 ) {
				System.out.println(max_num);
			}
			else {
				System.out.print("No majority number");
			}
		}
	}

}

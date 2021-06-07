package ACM_1998;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

class MyList {
	MyList link;
	long info;
	
	public MyList( long i , MyList next ) {
		link = next; info = i;
	}
	
	@Override
	public String toString() {
//		return info+"";
		if ( link == null ) {
			return ""+info;
		}
		return info+" "+link.toString();
	}
	
}

public class E {

	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			long sequence_length = in.nextLong();
			if ( sequence_length == 0 )
				break;
			long t = in.nextLong();
			MyList queue = new MyList(t,null);
//			System.out.println(queue.toString());
			int total_inversion_count = 0;
			for ( int i = 1 ; i < sequence_length ; ++i ) {
				t = in.nextLong();
				int inversion_count = 0;
				MyList walker = queue;
				if ( walker.info > t ) {
					queue = new MyList(t,walker);
//					System.out.println(queue.toString());
					total_inversion_count += i-inversion_count;
					continue;
				}
				while ( walker.link != null && walker.link.info <= t ) {
					++inversion_count;
					walker = walker.link;
				}
				if ( walker.link == null ) {
					walker.link = new MyList(t,null);
				}
				else {
					MyList tmp = walker.link;
					walker.link = new MyList(t,tmp);
					total_inversion_count += i-inversion_count;
				}
//				System.out.println(queue.toString());
			}
			System.out.println(total_inversion_count);
		}
	}
	
}

package ACM_2007;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class B {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		TreeMap<Integer,Integer> queue = new TreeMap<Integer,Integer>();
		while ( in.hasNext() ) {
			int command = in.nextInt();
			if ( command == 0 ) {
				break;
			}
			if ( command == 1 ) {
				int cline_id = in.nextInt();
				int client_priority = in.nextInt();
				queue.put(client_priority, cline_id);
			}
			if ( command == 2 ) {
				if ( queue.isEmpty() ) {
					System.out.println("0");
				}
				else {
					Entry<Integer, Integer> t = queue.lastEntry();
					int t_value = t.getValue();
					int t_key = t.getKey();
					queue.remove(t_key);
					System.out.println(t_value);
				}
			}
			if ( command == 3 ) {
				if ( queue.isEmpty() ) {
					System.out.println("0");
				}
				else {
					Entry<Integer, Integer> t = queue.firstEntry();
					int t_value = t.getValue();
					int t_key = t.getKey();
					queue.remove(t_key);
					System.out.println(t_value);
				}
			}
		}
	}

}

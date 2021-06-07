package ACM_2002;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class F {
	
	public static void main ( String args[] ) {
		TreeMap<Integer,Integer> queue = new TreeMap<Integer,Integer>();
		Scanner in = new Scanner(System.in);
		int max_cost = in.nextInt();
		int removal_length = in.nextInt();
		int removal[] = new int[removal_length];
		for ( int i = 0 ; i < removal_length ; ++i ) {
			removal[i] = in.nextInt();
		}
		int current_policy = 1;
		int remove_count = 1;
		int removal_next_index = 0;
		while ( true ) {
			String command = in.next();
			if ( command.charAt(0) == 'a' ) {
				int parametar = in.nextInt();
				int value = 0;
				if ( queue.containsKey(parametar) ) {
					value = queue.get(parametar);
				}
				queue.put(parametar, value+1);
			}
			if ( command.charAt(0) == 'r' ) {
				if ( queue.isEmpty() ) {
					System.out.println("-1");
					continue;
				}
				Entry<Integer, Integer> temp = null;
				if ( current_policy == 1 ) {
					temp = queue.firstEntry();
				}
				else {
					temp = queue.lastEntry();
				}
				int key = temp.getKey();
				int value = temp.getValue();
				if ( remove_count == removal[removal_next_index] ) {
					System.out.println(key);
					++removal_next_index;
				}
				if ( value > 1 ) {
					queue.put(key,value-1);
				}
				else {
					queue.remove(key);
				}
				++remove_count;
			}
			if ( command.charAt(0) == 'p' ) {
				int parametar = in.nextInt();
				if ( parametar >= 1 && parametar <= 2 )
					current_policy = parametar;
				else
					System.out.println("Unable to aplly policy because: no such policy exists");
			}
			if ( command.charAt(0) == 'e' ) {
				break;
			}
		}
		
		
		
		
	}

}

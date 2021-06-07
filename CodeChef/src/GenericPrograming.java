import java.util.ArrayList;
import java.util.Collections;


public class GenericPrograming {
	
	public static void main ( String args[] ) {
		ArrayList<Integer> my_list = new ArrayList<Integer>();
		for ( int i = 100000 ; i >= 0 ; --i )
			my_list.add(i);
		System.out.println(my_list);
		Collections.sort(my_list);
		System.out.println(my_list);
		long start = System.currentTimeMillis();
		for ( int i = 0 ; i < 1000000 ; ++i ) {
			search(my_list,(int)(Math.random()*my_list.size()));
//			Collections.binarySearch(my_list,(int)(Math.random()*my_list.size()));
//			System.out.println(Collections.binarySearch(my_list,(int)(Math.random()*my_list.size())));
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start+" ms");
		start = System.currentTimeMillis();
		for ( int i = 0 ; i < 1000000 ; ++i ) {
//			search(my_list,(int)(Math.random()*my_list.size()));
			Collections.binarySearch(my_list,(int)(Math.random()*my_list.size()));
//			System.out.println(Collections.binarySearch(my_list,(int)(Math.random()*my_list.size())));
		}
		end = System.currentTimeMillis();
		System.out.println(end-start+" ms");
	}
	
	public static int search ( ArrayList<Integer> t , int e ) {
		for ( int i = 0 ; i < t.size() ; ++i ) {
			if ( t.get(i) == e ) {
				return i;
			}
		}
		return -1;
	}

}

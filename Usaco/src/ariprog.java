/*
ID: gajduk_01
LANG: JAVA
TASK: ariprog
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class Sequence implements Comparable {
	int a;
	int d;
	
	public Sequence() {
		
	}
	
	public Sequence( int c , int b) {
		a = c;
		d = b;
	}
	
	@Override
	public int compareTo(Object o) {
		Sequence s = (Sequence) o;
		if ( d != s.d ) {
			return d-s.d;
		}
		else {
			return a-s.a;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return a+" "+d;
	}

}

public class ariprog {
	public static final int MAX_VALUE = 130001;
	public static int bisquares[] = new int[40000];
	public static boolean in_set[] = new boolean[MAX_VALUE];
	public static ArrayList<Integer> bisquares_list = new ArrayList<Integer>();
	public static int num_bisquares = 0;
	public static int max_length;
	
	public static int checkSequenceDifference ( int start_value , int difference ) {
		if ( start_value == 5 && difference == 4 ) {
			int debug;
			debug = 5;
		}
		int length = 2;
		while ( true ) {
			if ( start_value+difference >= MAX_VALUE ) {
				return length;
			}
			if ( in_set[start_value+difference] ) {
				++length;
				if ( length > max_length ) {
					return max_length;
				}
				start_value = start_value+difference;
			}
			else {
				return length;
			}
		}
	}
	
	public static int checkSequence ( int start_index , int next_index ) {
		int diference = bisquares[next_index]-bisquares[start_index];
		int length = 2;
		for ( int i = next_index+1 ; i < bisquares.length ; ++i ) {
			if ( bisquares[next_index]+diference == bisquares[i] ) {
				++length;
				next_index = i;
			}
		}
		return length;
	}
	
	public static int checkSequenceList ( int start_index , int next_index ) {
		int diference = bisquares_list.get(next_index)-bisquares_list.get(start_index);
		int length = 2;
		for ( int i = next_index+1 ; i < bisquares_list.size() ; ++i ) {
			if ( bisquares_list.get(next_index)+diference == bisquares_list.get(i) ) {
				++length;
				next_index = i;
				if ( length > max_length ) {
					return max_length+1;
				}
			}
			if ( bisquares_list.get(next_index)+diference < bisquares_list.get(i) ) {
				return length;
			}
		}
		return length;
	}
	
	public static ArrayList<Sequence> findAllSequencesList ( int length ) {
		ArrayList<Sequence> result = new ArrayList<Sequence>();
		for ( int i = 0 ; i < bisquares_list.size() ; ++i ) {
			for ( int j = i+1 ; j < bisquares_list.size() ; ++j ) {
				int temp = checkSequenceList(i, j);
				if ( temp == length ) {
					result.add(new Sequence(bisquares_list.get(i), bisquares_list.get(j)-bisquares_list.get(i)));
				}
			}
		}
		return result;
	}
	
	public static ArrayList<Sequence> findAllSequencesBetter ( int length ) {
		ArrayList<Sequence> result = new ArrayList<Sequence>();
		for ( int i = bisquares.length-num_bisquares ; i < bisquares.length ; ++i ) {
			for ( int j = i+1 ; j < bisquares.length ; ++j ) {
//				System.out.println(bisquares[j]+" "+(bisquares[j]-bisquares[i]));
				int temp = checkSequenceDifference(bisquares[j], bisquares[j]-bisquares[i]);
//				System.out.println(temp);
				if ( temp == length ) {
					result.add(new Sequence(bisquares[i], bisquares[j]-bisquares[i]));
				}
			}
		}
		return result;
	}
	
	public static ArrayList<Sequence> findAllSequences ( int length ) {
		ArrayList<Sequence> result = new ArrayList<Sequence>();
		for ( int i = bisquares.length-num_bisquares ; i < bisquares.length ; ++i ) {
			for ( int j = i+1 ; j < bisquares.length ; ++j ) {
				int temp = checkSequence(i, j);
				if ( temp == length ) {
					result.add(new Sequence(bisquares[i], bisquares[j]-bisquares[i]));
				}
			}
		}
		return result;
	}
	
	public static void populateBisquaresList ( int m ) {
//		long start = System.currentTimeMillis();
		int temp = 0;
		for ( int i = 0 ; i <= m ; ++i ) {
			for ( int k = i ; k <= m ; ++k ) {
				temp = i*i+k*k;
				if ( ! bisquares_list.contains(temp) ) {
					bisquares_list.add(temp);
				}
			}
		}
		Collections.sort(bisquares_list);
//		long end = System.currentTimeMillis();
//		System.out.println("Time to populate the list:"+(end-start)/1000+"."+(end-start)%1000);
	}
	
	public static void populateBisquares ( int m ) {
//		long start = System.currentTimeMillis();
		int temp = 0;
		for ( int i = 0 ; i <= m ; ++i ) {
			for ( int k = i ; k <= m ; ++k ) {
				temp = i*i+k*k;
				if ( ! in_set[temp] ) {
					in_set[temp] = true;
					bisquares[num_bisquares++] = temp;
				}
				
			}
		}
		Arrays.sort(bisquares);
//		long end = System.currentTimeMillis();
//		System.out.println("Time to populate the array:"+(end-start)/1000+"."+(end-start)%1000);
	}
	
	public static void printBisquaers ( ) {
		for ( int i = bisquares.length-num_bisquares ; i < bisquares.length ; ++i ) {
			//if ( bisquares[i] != 0 ) {
				System.out.println(bisquares[i]+" ");
			//}
		}
		System.out.println();
	}
	
	public static void testPopulate () {
		populateBisquares(8);
		printBisquaers();
	}
		
	public static void test_all () {
		populateBisquares(7);
		//populateBisquaresList(250);
		max_length = 4;
		//System.out.println(num_bisquares);
		//System.out.println(bisquares_list.size());
	    ArrayList<Sequence> t = findAllSequencesBetter(5);
		Collections.sort(t);
		for (Sequence sequence : t ) {
			System.out.println(sequence);
		}
	}
	
	public static void print(ArrayList<Sequence> r) {
		for ( Sequence sequence : r ) {
			System.out.println(sequence);
		}
	}

	public static void main ( String args[] ) throws IOException {
//		test_all();
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
	    int n = new Integer(f.readLine());
	    int m = new Integer(f.readLine());
	    max_length = n;
	    populateBisquares(m);
//	    System.out.println(Arrays.toString(bisquares));
	    ArrayList<Sequence> t = findAllSequencesBetter(n);
		Collections.sort(t);
		for (Sequence sequence : t ) {
			out.println(sequence);
		}
		if ( t.isEmpty() ) {
			out.println("NONE");
		}
		out.close();
	}
	
}

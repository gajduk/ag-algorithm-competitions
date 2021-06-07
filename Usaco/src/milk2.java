/*
ID: gajduk_01
LANG: JAVA
TASK: milk2
*/





import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class milk2 {
	
	public static final int MAX_TIME = 1000000;
	public static boolean is_milking[] = new boolean[MAX_TIME];
	public static int start = MAX_TIME;
	public static int end = 0;
	
	public static void test_populate() {
		
	}
	
	
	
	public static void main (String [] args) throws IOException {
		//test_populate();
		
	    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
	    int num_farmers = new Integer(f.readLine());
	    for ( int k = 0 ; k < num_farmers ; ++k ) {
	    	String s_times = f.readLine();
	    	StringTokenizer tkr_times = new StringTokenizer(s_times);
	    	populateTimes( new Integer(tkr_times.nextToken()),new Integer(tkr_times.nextToken()));
	    }
	    out.print(getLongestTime(true)+" ");
	    out.println(getLongestTime(false));
	    out.close();
	   
	}

	public static int getLongestTime( boolean b ) {
		int max = 0;
		for ( int i = start ; i < end ; ++i ) {
			int current_length = 0;
			while ( i < end && is_milking[i] == b ) {
				++i;
				++current_length;
			}
			if ( max < current_length ) {
				max = current_length;
			}
		}
		return max;
	}

	public static void populateTimes( int a, int b) {
		for ( int i = a ; i < b ; ++i ) {
			is_milking[i] = true;
		}
		if ( a < start ) {
			start = a;
		}
		if ( b > end ) {
			end = b;
		}
	}

}

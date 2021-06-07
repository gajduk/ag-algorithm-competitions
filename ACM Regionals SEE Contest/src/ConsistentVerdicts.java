import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

class InputScanner3 {
	 BufferedReader br;
	 StringTokenizer strtok = null;

	 public InputScanner3(InputStream stream) {
		 br = new BufferedReader(new InputStreamReader(stream));
	 }

	 public String next() {
		  if (strtok == null || !strtok.hasMoreTokens()) {
		   try {
		    strtok = new StringTokenizer(br.readLine());
		   } catch (IOException e) {
		    throw new RuntimeException(e);
		   }
		  }
		  return strtok.nextToken();
	 }

	 public int nextInt() {
		  int i;
		  try {
		   i = Integer.parseInt(next());
		  } catch (NumberFormatException e) {
		   throw new RuntimeException(e);
		  }
		  return i;
	 }

	 public String nextLine() {
		  if (strtok == null || !strtok.hasMoreTokens()) {
		   try {
		    strtok = new StringTokenizer(br.readLine());
		   } catch (IOException e) {
		    throw new RuntimeException(e);
		   }
		  }
		  return strtok.nextToken("\n").trim();
		 }
}

public class ConsistentVerdicts {
	
	public static boolean f( int i , int n ) {
	    for ( int k = 1 ; k <= n ; ++k ) {
	        if ( i*k % n == 1 ) return true;
	    }
	    return false;
	}
	
	public static void main(String[] args) {
		for ( int w = 0 ; w < 10000 ; ++w ) {
			int counter = 0;
			for ( int i = 0 ; i < w ; ++i ) {
                if ( f(i,w) ) ++counter;
            }
			System.out.print(counter+",");
		}
		/*
		InputScanner3 in = new InputScanner3(System.in);
		int num_test_cases = new Integer(in.nextInt());
		for ( int i = 1 ; i <= num_test_cases ; ++i ) {
			int num_people = in.nextInt();
			long x[] = new long[num_people];
			long y[] = new long[num_people];
			for ( int k = 0 ; k < num_people ; ++k ) {
				x[k] = in.nextInt(); y[k] = in.nextInt();
			}
			System.out.println("Case "+i+": "+numVerdicts(x,y));
		}
		*/
	}

	private static int numVerdicts(long[] x, long[] y) {
		HashSet<Long> set = new HashSet<Long>();
		for ( int i = 0 ; i < x.length ; ++i ) {
			for ( int k = i+1 ; k < x.length ; ++k ) {
				long d = (x[i]-x[k])*(x[i]-x[k])+(y[i]-y[k])*(y[i]-y[k]);
				set.add(d);
			}
		}
		return 1+set.size();
	}
}

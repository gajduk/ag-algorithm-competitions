/*
ID: gajduk_01
LANG: JAVA
TASK: transform
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class transform {
	
	public static String[] reflect ( String pattern[] ) {
		String result[] = new String[pattern.length];
		for ( int j = pattern.length-1 ; j >= 0 ; --j ) {
			result[j] = new StringBuffer(pattern[j]).reverse().toString();
		}
		return result;
	}
	
	public static String[] rotate180 ( String pattern[] ) {
		return rotate90(rotate90(pattern));
	}
	
	public static String[] rotate270 ( String pattern[] ) {
		return rotate90(rotate90(rotate90(pattern)));
	}
	
	public static String[] rotate90 ( String pattern[] ) {
		String result[] = new String[pattern.length];
		for ( int j = pattern.length-1 ; j >= 0 ; --j ) {
			result[j] = new String("");
		}
		for ( int j = pattern.length-1 ; j >= 0 ; --j ) {
			for ( int i = 0 ; i < pattern.length ; ++i ) {
				result[i] += pattern[j].charAt(i);
			}
		//	print(result);
		}
		return result;
	}
	
	public static void print ( String pattern[] ) {
		for ( int i = 0 ; i < pattern.length ; ++i ) {
			System.out.println(pattern[i]);
		}
	}
	
	public static boolean isEqual ( String a[] , String b[] ) {
		for ( int i = 0 ; i < a.length ; ++i ) {
			if ( ! a[i].equals(b[i]) ) {
				return false;
			}
		}
		return true;
	}
	
	public static void main ( String args[] ) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
	    int n = new Integer(f.readLine());
	    String original[] = new String[n];
	    for ( int i = 0 ; i < n ; ++i ) {
	    	original[i] = f.readLine();
	    }
	    String transformed[] = new String[n];
	    for ( int i = 0 ; i < n ; ++i ) {
	    	transformed[i] = f.readLine();
	    }
	    if ( isEqual(transformed, rotate90(original)) ) {
	    	out.println(1);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    if ( isEqual(transformed, rotate180(original)) ) {
	    	out.println(2);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    if ( isEqual(transformed, rotate270(original)) ) {
	    	out.println(3);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    if ( isEqual(transformed, reflect(original)) ) {
	    	out.println(4);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    if ( isEqual(transformed, rotate90(reflect(original))) ) {
	    	out.println(5);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    if ( isEqual(transformed, rotate180(reflect(original))) ) {
	    	out.println(5);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    if ( isEqual(transformed, rotate270(reflect(original))) ) {
	    	out.println(5);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    if ( isEqual(transformed, original) ) {
	    	out.println(6);
	    	out.close();
	    	System.exit(0);
	    	return;
	    }
	    out.println(7);
    	out.close();
    	System.exit(0);
    	return;
	}

}

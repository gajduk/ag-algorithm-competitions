package Chapter3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Problem3 {
	
	public static void main ( String args[] ) {
		//test_judges();
		test_file();
	}
	
	public static void test_judges() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
    		String a = in.nextLine();
    		String b = in.nextLine();
    		System.out.println(longestCommonPermutation(a, b));
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("F:/Програмирање/UVA judge test/chapter3 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		String a = in.nextLine();
    		String b = in.nextLine();
    		System.out.println(longestCommonPermutation(a, b));
    	}
	}
    
	
	//calculates the longest string such thtat its permutation is present in a and the same/another permutation is present in b
	public static String longestCommonPermutation ( String a , String b ) {
		char occurance_a[] = new char[26];//how often does each charactes appear in String a
		char occurance_b[] = new char [26];//how often does every charactes appear in string b
		for ( int i = 0 ; i < a.length(); ++i ) {
			++occurance_a[a.charAt(i)-'a'];
		}
		for ( int i = 0 ; i < b.length(); ++i ) {
			++occurance_b[b.charAt(i)-'a'];
		}
		String result = "";
		for ( int i = 0 ; i < occurance_a.length ; ++i ) {
			int min = min ( occurance_a[i] , occurance_b[i]);
			for ( int h = 0 ; h < min ; ++h ) {
				char temp[] = new char[1];
				temp[0] = (char) ((char) i+'a'); 
				result += new String(temp);
			}
		}
		return result;
	}
	
	public static int min ( int a , int b ) {
		if ( a < b ) return a;
		return b;
	}

}

/*
ID: gajduk_01
LANG: JAVA
TASK: crypt1
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crypt1 {
	
	public static int available_digits[];
	public static int counter = 0;
	
	public static boolean checkNumber ( int number ) {
		if ( number == 0 )
			return true;
		if ( contains(number%10) )
			return checkNumber(number/10);
		return false;
	}
	
	private static boolean contains(int i) {
		for ( int k = 0 ; k < available_digits.length ; ++k ) {
			if ( available_digits[k] == i )
				return true;
		}
		return false;
	}

	public static void checkAllSubsets () {
		checkAllSubsetsRecursive (5,0,0);
	}
	
	private static void checkAllSubsetsRecursive(int level , int number1, int digit1) {
		if ( level > 2 ) {
			for ( int i = 0 ; i < available_digits.length ; ++i ) {
				checkAllSubsetsRecursive(level-1,number1*10+available_digits[i],0);
			}
			return;
		}
		if ( level == 2 ) {
			for ( int i = 0 ; i < available_digits.length ; ++i ) {
				if ( checkProduct1(number1,available_digits[i]) ) {
					checkAllSubsetsRecursive(level-1,number1,available_digits[i]);
				}
			}
			return;
		}
		if ( level == 1 ) {
			for ( int i = 0 ; i < available_digits.length ; ++i ) {
				if ( checkProduct2(number1,available_digits[i]) ) {
					if ( checkProduct(number1, digit1*10+available_digits[i])) {
						System.out.println(number1+" "+(digit1*10+available_digits[i]));
						++counter;
					}
				}
			}
		}
	}

	public static boolean checkProduct1 ( int number1 , int digit1 ) {
		return Integer.toString(number1*digit1).length()==3&&checkNumber(number1*digit1);
	}
	
	public static boolean checkProduct2 ( int number1 , int digit2 ) {
		return Integer.toString(number1*digit2).length()==3&&checkNumber(number1*digit2);
	}

	public static boolean checkProduct ( int number1 , int number2 ) {
		return Integer.toString(number1*number2).length()==4&&checkNumber(number1*number2);
	}
	
	public static void main ( String args[] ) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
	    int num_available_digits = new Integer(f.readLine());
	    available_digits = new int[num_available_digits];
	    StringTokenizer tkr = new StringTokenizer(f.readLine());
	    for ( int i = 0 ; i < num_available_digits ; ++i ) {
	    	available_digits[i] = new Integer(tkr.nextToken());
	    }
	    checkAllSubsets();
	    out.println(counter);
	    out.close();
	    System.exit(0);
	}
	
	
	
	

}

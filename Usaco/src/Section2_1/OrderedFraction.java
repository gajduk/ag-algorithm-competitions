package Section2_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Fraction implements Comparable {
	int a;
	int b;
	public Fraction( int a1 , int b1 ) {
		a = a1; b = b1;
	}
	@Override
	public int compareTo(Object arg0) {
		Fraction t = (Fraction) arg0;
		return (((double)a)/b - ((double) t.a)/t.b ) > 0 ? 1 : -1;
	}
	@Override
	public String toString() {
		return a+"/"+b;
	}
}

public class OrderedFraction {
	
	public static void main ( String args[] ) {
		long start = System.currentTimeMillis();
		generateAllPossbibleFractions();
		for ( int i = 1 ; i < 160 ; ++i ) {
			printAllPosibleFractions(i);
			//System.out.println();
		}
		long end = System.currentTimeMillis();
		System.out.println("Run time in milisec "+(end-start));
	}
	
	public static void printAllPosibleFractions(int i ) { 
		for (Fraction f : values_list) {
			if ( f.a <= i && f.b <= i ) {
				//System.out.print(f+" ");
			}
		}
	}

	
	public static int gcd ( int a , int b ) {
		if ( b == 0 )
			return a;
		if ( b > a ) {
			return gcd(b,a);
		}
		return gcd(a%b,b);
	}
	
	public static boolean arePrimes ( int a , int b ) {
		return gcd(a,b) == 1;
	}
	
	public static final int max_number = 160;
	public static ArrayList<Fraction> values_list = new ArrayList<Fraction>();
	
	public static void generateAllPossbibleFractions (  ) {
		double values[] = new double[max_number*max_number+1000];
		
		int num_values = 0;
		for ( int i = 1 ; i <= max_number ; ++i ) {
			for ( int k = i ; k <= max_number ; ++k ) {
				if ( arePrimes(i, k) ) {
					//System.out.print(i+"/"+k+" ");
					values_list.add(new Fraction(i, k));
					//values[num_values++] = ((double)i)/k;
				}
			}
		}
		//System.out.println();
		Arrays.sort(values);
		Collections.sort(values_list);
	}
	
}

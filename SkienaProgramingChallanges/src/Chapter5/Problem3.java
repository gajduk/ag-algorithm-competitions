package Chapter5;

import java.math.BigDecimal;
import java.util.Scanner;

public class Problem3 {

	public static void main ( String args[] ) {
//		test_all();
		test_judge();
//		test_file();
	}
	
	public static void test_judge () {
	 	Scanner in = new Scanner(System.in);
	 //	System.out.println(Long.MAX_VALUE);
	 //	System.out.println((long)Double.MAX_VALUE);
	 	while ( in.hasNext() ) {
			System.out.println(Long.toString(getExponentPrecise(new Integer(in.nextLine()))));
		}
	}
	
	public static void test_all () {
		for ( int i = 100000000 ; i < Integer.MAX_VALUE && i > 0; i += 100000000 ) {
			System.out.println(i+" "+Long.toString(getExponent(i)));
		}
	}
	
	public static long getExponentPrecise ( int number ) {
		if ( number == 0 )
			return 0;
		int length = Integer.toString(number).length();
		length += 1;
		BigDecimal number_d = new BigDecimal((double) number);
		BigDecimal log_2_10 = new BigDecimal(log2(10));
		BigDecimal log_2_n = new BigDecimal(log2(number));
		BigDecimal log_2_n_1 = new BigDecimal(log2(number+1));
		BigDecimal e_min = log_2_n;
		BigDecimal e_max = log_2_n_1;
		for ( int i = 0 ; i < length ; ++i ) {
			e_min = e_min.add(log_2_10);e_max = e_max.add(log_2_10);
		}
		
		for ( int k = length ; ; ++k ) {
			
			//System.out.println(e_min);
			//System.out.println(e_max);
			/*
			long e = ((long) e_min) +1;
			if ( e < e_max ) {
				return e;
			}
			*/
			if ( e_min.longValue() != e_max.longValue() ) {
				return e_max.longValue();
			}
			e_min = e_min.add(log_2_10);
			e_max = e_max.add(log_2_10);
		}
	}
	
	public static long getExponent ( int number ) {
		if ( number == 0 )
			return 0;
		int length = Integer.toString(number).length();
		length += 1;
		double number_d = (double) number;
		double log_2_10 = log2(10);
		double log_2_n = log2(number);
		double log_2_n_1 = log2(number+1);
		double e_min = 0;
		double e_max = 0;
		for ( int k = length ; ; ++k ) {
			e_min = log_2_n+k*log_2_10;
			e_max = log_2_n_1+k*log_2_10;
			//System.out.println(e_min);
			//System.out.println(e_max);
			/*
			long e = ((long) e_min) +1;
			if ( e < e_max ) {
				return e;
			}
			*/
			if ( (long) e_min != (long) e_max ) {
				return (long) e_max;
			}
		}
	}

	public static double log2(double number_d) {
		return Math.log(number_d)/Math.log(2);
	}
	
	public static long getExponentDouble ( int number ) {
		if ( number == 0 )
			return 0;
		if ( number == 1 )
			return 7;
		int length = Integer.toString(number).length();
		length *= 2;
		Double number_d = (double) number;
		Double log_2_10 = log2(10);
		for ( int k = length ; ; ++k ) {
			Double e_d = log2(number_d)+k*log_2_10;
			Double b = log2(number_d+1)+k*log_2_10;
			long e = e_d.longValue();	
			if ( e < b ) {
				return e;
			}
		}
	}
	
	public static Double log2D( Double number_d) {
		return Math.log(number_d)/Math.log(2);
	}
	
}

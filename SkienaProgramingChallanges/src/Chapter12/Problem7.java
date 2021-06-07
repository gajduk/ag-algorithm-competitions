package Chapter12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

class Location {
	double x;
	double y;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}
	
	public Location( double x1 , double y1 ) {
		x = x1; y = y1;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
}

public class Problem7 {
	
	public static double h = Math.sin(Math.PI/3);
	public static double w = 1;
	
	private static String transform(String a) {
		int index = a.indexOf('.');
		if ( index == - 1 ) {
			return a+".000";
		}
		else {
			String result = a.substring(index+1);
			String start = a.substring(0,index+1);
			while ( result.length() < 3 ) {
				result = result + "0";
			}
			return start+result;
		}
	}
	
	public static double distance ( Location a , Location b ) {
//		System.out.println(a+" "+b);
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public static Location locateOnGrid ( int n ) {
		int k = (int) Math.sqrt(n);
		double start = -(k*w/2);
		double offset = (n-k*k)*w/2;
		double x = start+offset;
		start = (k) * h;
		int is_not_upside_down = 0;
		is_not_upside_down = ( k%2 == n%2 ) ? 1 : 0;
//		System.out.println(is_not_upside_down);
		offset = (h/3)*(1+is_not_upside_down);
		double y = start+offset;
		return new Location(x,y);
	}
	
	public static double getDistance ( int n1 , int n2 ) {
		return distance(locateOnGrid(n1), locateOnGrid(n2));
	}
	
	public static void main ( String args[] ) {
//		test_getDisance();
//		test_file();
		test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem7.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		int a = new Integer(numbers.nextToken());
    		int b = new Integer(numbers.nextToken());
    		double d = getDistance(a,b);
    		
    		// TO DO: round up d to 3 decimal places
    		
    		/*
    		d *= 1000000000;
    		Math.round(d);
    		d /= 1000000;
    		int int_value = ((int) d)/1000;
    		int dec_value = ((int) d)%1000;
    		
    		System.out.println(int_value+"."+transform(dec_value));
    		

    		*/	
    	//	System.out.println(Round(d,3));
    		/*
    		DecimalFormat df = new DecimalFormat("#.###");
            System.out.println(transform(df.format(d)));
            */
    		System.out.printf("$.3f", d);
    		System.out.println();
    	}
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		int a = new Integer(numbers.nextToken());
    		int b = new Integer(numbers.nextToken());
    		double d = getDistance(a,b);
    		DecimalFormat df = new DecimalFormat("#.###");
            System.out.println(transform(df.format(d)));
    	}
	}
	
	public static double Round(double Rval, int Rpl) {
		  double p = (double)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  double tmp = Math.round(Rval);
		  return (double)tmp/p;
	}

	public static void test_getDisance() {
		System.out.println(getDistance(0,7));
	}

}

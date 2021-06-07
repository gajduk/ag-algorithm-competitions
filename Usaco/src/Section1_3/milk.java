package Section1_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Offer implements Comparable {
	int price;
	int gallons;
	
	public Offer( int a , int b) {
		price = a;
		gallons = b;
	}

	@Override
	public int compareTo(Object arg0) {
		Offer t = (Offer) arg0;
		return price-t.price;
	}
	
}

public class milk {
	
	public static void main ( String args[] ) throws IOException {
	 	BufferedReader f = new BufferedReader(new FileReader("milk.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
	    String s_numbers = f.readLine();
	    StringTokenizer tkr_numbers = new StringTokenizer(s_numbers);
	    int n = new Integer(tkr_numbers.nextToken());
	    int m = new Integer(tkr_numbers.nextToken());
	    Offer offers[] = new Offer[m];
	    for ( int i = 0 ; i < m ; ++i ) {
	    	String s_numbers1 = f.readLine();
	 	    StringTokenizer tkr_numbers1 = new StringTokenizer(s_numbers);
	 	    offers[i] = new Offer(new Integer(tkr_numbers1.nextToken()),new Integer(tkr_numbers1.nextToken()));
	    }
	    Arrays.sort(offers);
	    int total_cost = 0;
	    int current_milk = 0;
	    int walker = 0;
	    while ( current_milk < n ) {
	    	if ( n-current_milk >= offers[walker].gallons ) {
	    		current_milk += offers[walker].gallons;
	    		total_cost += offers[walker].price*offers[walker].gallons;
	    	}
	    	else {
	    		current_milk += n-current_milk;
	    		total_cost += offers[walker].price*(n-current_milk);
	    	}
	    	++walker;
	    }
	    out.println(total_cost);
	    out.close();
	    System.exit(0);
}

}

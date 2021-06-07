/*
ID: gajduk_01
LANG: JAVA
TASK: gift1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class gift1 {
	public static String people[];
	public static int initial_money[];
	public static int final_money[];
	
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
	    int num_people = new Integer(f.readLine());
	    people = new String[num_people];
	    initial_money = new int[num_people];
	    final_money = new int[num_people];
	    for ( int k = 0 ; k < num_people ; ++k ) {
	    	people[k] = f.readLine();
	    }
	    int k = 0;
	    while ( f.ready() ) {
	    	String s_giver = f.readLine();
	    	int giver = getIndex(s_giver);
	    	StringTokenizer tkr_money = new StringTokenizer(f.readLine());
	    	initial_money[giver] = new Integer(tkr_money.nextToken());
	    	int num_gift_receivers = new Integer(tkr_money.nextToken());
	    	for ( int i = 0 ; i < num_gift_receivers ; ++i ) {
	    		String s_receiver = f.readLine();
		    	int receiver = getIndex(s_receiver);
		    	final_money[receiver] += initial_money[giver] / num_gift_receivers;
	    	}
	    	if ( num_gift_receivers != 0 )
	    		final_money[giver] += initial_money[giver] % num_gift_receivers;
	    	else
	    		final_money[giver] += initial_money[giver];
	    }
	    for ( int i = 0 ; i < people.length ; ++i ) {
	    	out.println(people[i]+" "+(final_money[i]-initial_money[i]));
	    }
	    out.close();                                  
	    System.exit(0);                               
	}


	public static int getIndex(String s_giver) {
		for ( int i = 0 ; i < people.length ; ++i ) {
			if ( people[i].equals(s_giver) ) {
				return i;
			}
		}
		return 0;
	}
	
	

}

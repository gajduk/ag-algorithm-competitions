/*
ID: gajduk_01
LANG: JAVA
TASK: friday
*/



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class friday {
	
	public static int days[] = { 31 , 28 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31 };
	public static int ocurance[] = { 0 , 0 , 0 , 0 , 0 , 0 , 0 };
	
	public static boolean isLeapYear ( int year ) {
		return ( year % 4 == 0 && year % 100 != 0  ) || ( year % 400 == 0 ) ;
	}
	
	public static int getTotalNumDays ( int start_year , int end_year ) {
		int result = 0;
		for ( int i = start_year ; i < end_year ; ++i ) {
			result += isLeapYear(i) ? 366 : 365 ; 
		}
		return result;
	}
	
	public static void populateOccurances ( int start_year , int end_year ) {
		int total_days = getTotalNumDays(start_year, end_year);
		int current_year = start_year;
		int current_month = 0;//January
		int current_day = 0;//Monday
		current_day += 12;
		//System.out.println(current_day%7);
		while ( current_day <= total_days ) {
			++ocurance[current_day%7];  
			current_day += days[current_month];
			current_year += ++current_month / 12;
			current_month = current_month % 12;
			if ( isLeapYear(current_year) ) {
				days[1] = 29;
			}
			else {
				days[1] = 28;
			}
		}	
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("friday.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
	    int num_years = new Integer(f.readLine());
	    populateOccurances(1900, num_years+1900);
	    out.print(ocurance[5]+" ");
		out.print(ocurance[6]+" ");
		out.print(ocurance[0]+" ");
		out.print(ocurance[1]+" ");
		out.print(ocurance[2]+" ");
		out.print(ocurance[3]+" ");
		out.println(ocurance[4]);
		out.close();
		System.exit(0);
	    
	}
	
	/*
	public static void main ( String args[] ) {
		populateOccurances(1900, 1920);
		printOccurances();
	}
    */

	private static void printOccurances() {
		System.out.print(ocurance[5]+" ");
		System.out.print(ocurance[6]+" ");
		System.out.print(ocurance[0]+" ");
		System.out.print(ocurance[1]+" ");
		System.out.print(ocurance[2]+" ");
		System.out.print(ocurance[3]+" ");
		System.out.print(ocurance[4]);
	}
	

	
	
	
}

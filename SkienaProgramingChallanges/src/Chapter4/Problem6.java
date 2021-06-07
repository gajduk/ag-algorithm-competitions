package Chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Bill implements Comparable {
	public static int fares[] = new int[25];
	public static final int COST_PER_TRIP = 100;//in cents
	public static final int BILL_COST = 200;//in cents
	Entry last_entered;
	boolean is_last_entered_valid;
	int num_trips;
	String license;
	int cost_cents;
	
	public Bill ( Entry t ) { 
		license = t.licence;
		last_entered = t;
		is_last_entered_valid = true;
		cost_cents = 0;
		num_trips = 0;
	}
	
	public void exit ( Entry t ) {
		if ( is_last_entered_valid ) {
			is_last_entered_valid = false;
			cost_cents += Math.abs( t.km - last_entered.km ) * fares[last_entered.hour];
			num_trips++;
		}
	}
	
	public void enter ( Entry t ) {
		is_last_entered_valid = true;
		last_entered = t;
	}
	
	public int calculateTotalCost () {
		return cost_cents+num_trips*COST_PER_TRIP+BILL_COST;
	}
	
	public String toString () {
		if ( num_trips  != 0 ) {
			int total_cost = calculateTotalCost();
			if ( calculateTotalCost()%100 < 10 ) {
				return license+" $"+calculateTotalCost()/100+".0"+calculateTotalCost()%100+"\n";
			}
			else {
				return license+" $"+calculateTotalCost()/100+"."+calculateTotalCost()%100+"\n";
			}
		}
		return "";
	}

	@Override
	public int compareTo(Object o) {
		Bill t = (Bill) o;
		return license.compareTo(t.license);
	}
	
};

class Entry implements Comparable {
	String licence;
	int month;
	int day;
	int hour;
	int minute;
	boolean is_exit;
	int km;
	
	public Entry ( String s_entry ) { 
		StringTokenizer tkr = new StringTokenizer(s_entry," :");
		this.create(tkr.nextToken(),new Integer(tkr.nextToken()),new Integer(tkr.nextToken()),new Integer(tkr.nextToken()),new Integer(tkr.nextToken()),tkr.nextToken(),new Integer(tkr.nextToken()));
		
	}
	
	public void create( String lic , int m , int d , int h , int mm , String status , int km ) {
		licence = lic;
		month = m;
		day = d;
		minute = mm;
		hour = h;
		is_exit = (status.charAt(1) == 'x' || status.charAt(1) == 'X' );
		this.km = km;
	}

	@Override
	public int compareTo( Object a ) {
		Entry t = (Entry) a;
		if ( month != t.month ) {
			return month-t.month;
		}
		if ( day != t.day ) {
			return day-t.day;
		}
		if ( hour != t.hour ) {
			return hour-t.hour;
		}
		if ( minute != t.minute ) {
			return minute-t.minute;
		}
		return 0;
	}
	
	public String toString () {
		return licence+" "+month+":"+day+":"+hour+":"+minute+" "+is_exit+" "+km;
	}
}
	
public class Problem6 {
	
	public static Entry list[];
	public static Entry list_t[] = new Entry[1000];
	public static int num_entries = 0; 
	public static ArrayList<Bill> bills = new ArrayList<Bill>();
	public static Bill bills_arrray[];
	
	public static void main ( String argsp[ ]) {
		//test_file();
		test_judge();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
		try	 {
     		inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem6.txt");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
			return;
		}
		Scanner in = new Scanner(inputFile);
		int num_test_cases = new Integer(in.nextLine());
		in.nextLine();
		
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			if ( ! in.hasNext() )
				break;
			String s_fares = in.nextLine();
			StringTokenizer tkr_fares = new StringTokenizer(s_fares);
			for ( int m = 0 ; m < 24 ; ++m ) {
				Bill.fares[m] = new Integer(tkr_fares.nextToken());
			}
			String temp = "";
			if ( in.hasNext() )
				temp = in.nextLine();
			while ( temp.length() > 0 ) {
				list_t[num_entries++] = new Entry(temp);
				if ( ! in.hasNext() )
					break;
				temp = in.nextLine();
				
			}
			//if ( num_entries != 0 ) {
				copy();
				Arrays.sort(list);
				calculateBills();
				if ( k < num_test_cases-1 )
					System.out.println();
				cleanAllData();
			//}
		}
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		in.nextLine();
		
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			if ( ! in.hasNext() )
				break;
			String s_fares = in.nextLine();
			StringTokenizer tkr_fares = new StringTokenizer(s_fares);
			for ( int m = 0 ; m < 24 ; ++m ) {
				Bill.fares[m] = new Integer(tkr_fares.nextToken());
			}
			String temp = "";
			if ( in.hasNext() )
				temp = in.nextLine();
			while ( temp.length() > 0 ) {
				list_t[num_entries++] = new Entry(temp);
				if ( ! in.hasNext() )
					break;
				temp = in.nextLine();
				
			}
			//if ( num_entries != 0 ) {
				copy();
				Arrays.sort(list);
				calculateBills();
				if ( k < num_test_cases-1 )
					System.out.println();
				cleanAllData();
		//	}
		}
	}
	
	public static void cleanAllData () {
		list_t = new Entry[1000];
		num_entries = 0; 
		bills = new ArrayList<Bill>();
	}
	
	public static int getIndex ( String license ) {
		for ( int k = 0 ; k < bills.size() ; ++k ) {
			if ( bills.get(k).license.equals(license) )
				return k;
		}
		return -1;
	}

	public static void calculateBills() {
		for ( int k = 0 ; k < list.length ; ++k ) {
			int index = getIndex(list[k].licence);
			if ( index == -1 ) {
				if ( ! list[k].is_exit ) {
					bills.add(new Bill(list[k]));
				}
			}
			else {
				if ( list[k].is_exit ) {
					bills.get(index).exit(list[k]);
				}
				else {
					bills.get(index).enter(list[k]);
				}
			}
		}
		copyBillsAndSort();
		for (Bill bill : bills_arrray) {
			System.out.print(bill.toString());
		}
	}

	private static void copyBillsAndSort() {
		bills_arrray = new Bill[bills.size()];
		for ( int k = 0 ; k < bills.size() ; ++k ) {
			bills_arrray[k] = bills.get(k);
		}
		Arrays.sort(bills_arrray);
	}

	private static void copy() {
		list = new Entry[num_entries];
		for ( int i = 0 ; i < num_entries ; ++i ) {
			list[i] = list_t[i];
		}
	}
	
	
	

}

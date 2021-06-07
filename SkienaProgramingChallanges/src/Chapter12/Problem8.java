package Chapter12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class City {
	String name;
	double latitude;
	double longitde;
	
	public City() {
		// TODO Auto-generated constructor stub
	}
	
	public City( String a , double b , double c ) {
		name = a; latitude = b; longitde = c;
	}
	
	@Override
	public String toString() {
		return name+" ("+latitude+","+longitde+") ";
	}
	
}

public class Problem8 {
	public static final double PI = 3.141592653589793;
	public static final double R = 6378.0;
	public static final double FULL_CIRCLE = 360.0;
	public static City cities[];
	public static int graph[][];
	public static int distance[][];
	public static int n;
	public static final int IMMPOSIBLE = 1234567;
	
	
	public static int calculateMinimumDistance ( double x1 , double y1 , double x2 , double y2 ) {
		return 0;
	}
	
	public static void print ( int matrix[][] ) {
		for ( int i = 0 ; i < matrix.length ; ++i ) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
	
	/*
	public static int calculateDistance ( double x1 , double y1 , double x2 , double y2 ) {
		double dx = x1-x2;
		double dy = y1-y2;
		double alpha = Math.sqrt(dx*dx+dy*dy);
		double l = 2 * PI * R * alpha / FULL_CIRCLE; 
		return (int) l;
	}
	*/
	
	public static int calculateDistance ( City a, City  b ) {
		double x1 = a.latitude;	double y1 = a.longitde;
		double x2 = b.latitude;	double y2 = b.longitde;
		x1 = Math.toRadians(x1); y1 = Math.toRadians(y1);	
		x2 = Math.toRadians(x2); y2 = Math.toRadians(y2);	
		double dLat = (x2-x1);
		double dLon = (y2-y1);
		double af = Math.sin(dLat/2) * Math.sin(dLat/2) +
		        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(x1) * Math.cos(x2); 
		double c = 2 * Math.atan2(Math.sqrt(af), Math.sqrt(1-af)); 
		double d = R * c;
		// TO DO: calculate the distance with trigonometry 
		return (int) Math.round(d);
	}
	
	public static int calculateDistance ( double x1 , double y1 , double x2 , double y2 ) {
		x1 = Math.toRadians(x1);
		x2 = Math.toRadians(x2);
		y1 = Math.toRadians(y1);
		y2 = Math.toRadians(y2);
//		System.out.println("("+x1+","+y1+")"+" "+"("+x2+","+y2+")");
		double sins = Math.sin(x1)*Math.sin(x2);
		double cos1 = Math.cos(x1)*Math.cos(x2);
		double cos2 = Math.cos(y1-y2);
		double d = (sins+cos1*cos2)*R;
		return (int) d;
	}
	
	public static void test_calculateDistance () {
		System.out.println(calculateDistance(0,0,0,0));
	}
	
	public static void main ( String args[] ) {
//		test_calculateDistance();
//		test_file();
		test_judge();
	}
		
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	int test_case_id = 1;
    	while ( true ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		n =new Integer(numbers.nextToken());
    		int m =new Integer(numbers.nextToken());
    		int q =new Integer(numbers.nextToken());
    		if ( n == 0 && m == 0 && q == 0 ) {
    			break;
    		}
    		if ( test_case_id != 1 ) {
    			System.out.println();
    		}
    		System.out.println("Case #"+test_case_id);
    		++test_case_id;
    		cities = new City[n];
    		for ( int i = 0 ; i < n ; ++i ) {
    			StringTokenizer tkr_city = new StringTokenizer(in.nextLine());
    			String name = tkr_city.nextToken();
        		double lat = new Double(tkr_city.nextToken());
        		double lon = new Double(tkr_city.nextToken());
        		cities[i] = new City(name,lat,lon);
    		}
    		graph = new int[n][n];
    		for ( int i = 0 ; i < m ; ++i ) {
    			StringTokenizer tkr_flight = new StringTokenizer(in.nextLine());
    			addDirectFlight(tkr_flight.nextToken(),tkr_flight.nextToken());
    		}
//    		System.out.println("graph");
//    		print(graph);
    		distance = new int[n][n];
    		copy(distance,graph);
    		calculateAllDistances();
//    		System.out.println("distances");
//    		print(distance);
    		for ( int i = 0 ; i < q ; ++i ) {
    			StringTokenizer tkr_flight = new StringTokenizer(in.nextLine());
    			getMinimumDistance(tkr_flight.nextToken(),tkr_flight.nextToken());
    		}
    	}
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem8.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int test_case_id = 1;
    	while ( true ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		n =new Integer(numbers.nextToken());
    		int m =new Integer(numbers.nextToken());
    		int q =new Integer(numbers.nextToken());
    		if ( n == 0 && m == 0 && q == 0 ) {
    			break;
    		}
    		if ( test_case_id != 1 ) {
    			System.out.println();
    		}
    		System.out.println("Case #"+test_case_id);
    		++test_case_id;
    		cities = new City[n];
    		for ( int i = 0 ; i < n ; ++i ) {
    			StringTokenizer tkr_city = new StringTokenizer(in.nextLine());
    			String name = tkr_city.nextToken();
        		double lat = new Double(tkr_city.nextToken());
        		double lon = new Double(tkr_city.nextToken());
        		cities[i] = new City(name,lat,lon);
    		}
    		graph = new int[n][n];
    		for ( int i = 0 ; i < m ; ++i ) {
    			StringTokenizer tkr_flight = new StringTokenizer(in.nextLine());
    			addDirectFlight(tkr_flight.nextToken(),tkr_flight.nextToken());
    		}
//    		System.out.println("graph");
//    		print(graph);
    		distance = new int[n][n];
    		copy(distance,graph);
    		calculateAllDistances();
//    		System.out.println("distances");
//    		print(distance);
    		for ( int i = 0 ; i < q ; ++i ) {
    			StringTokenizer tkr_flight = new StringTokenizer(in.nextLine());
    			getMinimumDistance(tkr_flight.nextToken(),tkr_flight.nextToken());
    		}
    	}
	}

	private static void copy(int[][] a, int[][] b) {
		for ( int i = 0 ; i < a.length; ++i ) {
			for ( int j = 0 ; j < a[0].length ; ++j ) {
				if ( b[i][j] != 0 ) {
					a[i][j] = b[i][j];
				}
				else {
					a[i][j] = IMMPOSIBLE;
				}
			}
		}
	}

	private static void calculateAllDistances() {
		for ( int k = 0; k < n ; k++) {
			for ( int i = 0; i < n ; i++) {
				for ( int j = 0; j < n ; j++) {
					int through_k = distance[i][k]+distance[k][j];
					if (through_k < distance[i][j])
						distance[i][j] = through_k;
				}
			}
		}
	}

	public static void getMinimumDistance(String a, String b) {
		int ai = getIndex(a); int bi = getIndex(b);
		if ( distance[ai][bi] == IMMPOSIBLE ) {
			System.out.println("no route exists");
		}
		else {
			System.out.println(distance[ai][bi]+" km");
		}
	}
	
	public static int getIndex ( String city_name ) {
		for ( int i = 0 ; i < cities.length ; ++i ) {
			if ( city_name.equals(cities[i].name) ) {
				return i;
			}
		}
		System.out.println("The city "+city_name+" is not in the list!");
		return -1;
	}

	public static void addDirectFlight(String a, String b) {
		int ai = getIndex(a); int bi = getIndex(b);
		graph[ai][bi] = calculateDistance(cities[ai], cities[bi]);
	}
		

}
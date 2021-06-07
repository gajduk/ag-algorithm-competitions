package Chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class Edge3 {
	int arrival_time;
	int departure_time;
	int city;
	
	public Edge3( int a , int b , int c) {
		city = a;
		arrival_time = b;
		departure_time = c;
	}
	
	
}

public class Problem4 {
	
	public static void main ( String args[] ) {
		test_file();
		//test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ;k < num_test_cases ; ++k ) {
    		int num_cities = new Integer(in.nextLine());
    		String cities[] = new String[num_cities];
    		for ( int m = 0 ; m < num_cities ; ++m ) {
    			cities[m] = in.nextLine();
    		}
    		int num_train_routes = new Integer(in.nextLine());
    		String routes[][] = new String[num_train_routes][];
    		for ( int m = 0 ; m < num_train_routes ; ++m ) {
    			int num_stations = new Integer(in.nextLine());
    			routes[m] = new String[num_stations];
    			for ( int i = 0 ; i < num_stations ; ++i ) {
    				routes[m][i] = in.nextLine();
    			}
    		}
    		int earlieast_starting_time = new Integer(in.nextLine());
        	String start = in.nextLine();
        	String destination = in.nextLine();
        	System.out.println("Scenario "+(k+1));
        	findBestRoute(cities,routes,earlieast_starting_time,start,destination);
        	System.out.println();
    	}
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem4.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ;k < num_test_cases ; ++k ) {
    		int num_cities = new Integer(in.nextLine());
    		String cities[] = new String[num_cities];
    		for ( int m = 0 ; m < num_cities ; ++m ) {
    			cities[m] = in.nextLine();
    		}
    		int num_train_routes = new Integer(in.nextLine());
    		String routes[][] = new String[num_train_routes][];
    		for ( int m = 0 ; m < num_train_routes ; ++m ) {
    			int num_stations = new Integer(in.nextLine());
    			routes[m] = new String[num_stations];
    			for ( int i = 0 ; i < num_stations ; ++i ) {
    				routes[m][i] = in.nextLine();
    			}
    		}
    		int earlieast_starting_time = new Integer(in.nextLine());
        	String start = in.nextLine();
        	String destination = in.nextLine();
        	System.out.println("Scenario "+(k+1));
        	findBestRoute(cities,routes,earlieast_starting_time,start,destination);
        	System.out.println();
    	}
    	
	}
	
	public static ArrayList<Edge3> graph[];
	public static final int IMPOSSIBLE_TIME = 2600;
	
	private static void findBestRoute(String[] cities , String[][] routes , int earlieast_starting_time , String start , String destination ) {
		populateGraph(cities,routes);
		findShortestPath(earlieast_starting_time,getIndex(cities, start),getIndex(cities, destination),cities);
	}

	public static void findShortestPath( int earliest_starting_time , int start , int destination , String cities[] ) {
		if ( start == -1 || destination == -1 ) {
			System.out.println("No connection");
			return;
		}
		int prev[] = new int[graph.length];
		boolean is_in_set[] = new boolean[graph.length];
		int latest_departure_time[] = new int[graph.length];
		int earliest_time[] = new int[graph.length];
		for ( int k = 0 ; k < is_in_set.length ; ++k ) {
			earliest_time[k] = IMPOSSIBLE_TIME;
		}
		earliest_time[start] = earliest_starting_time;
		latest_departure_time[start] = IMPOSSIBLE_TIME;
		prev[start] = -1;
		for ( int m = 0 ; m < graph.length ; ++m ) {
			//print(latest_departure_time);
			int current_vertex = getMiniminIndex(earliest_time,is_in_set);
			is_in_set[current_vertex] = true;
			for ( Edge3 e : graph[current_vertex] ) {
				if ( e.departure_time >= earliest_time[current_vertex] ) {
					if ( e.arrival_time <= earliest_time[e.city] ) {
						//print(latest_departure_time);
						if ( e.arrival_time == earliest_time[e.city] ) {
							if ( latest_departure_time[current_vertex] > latest_departure_time[e.city] ) {
								 latest_departure_time[current_vertex] = latest_departure_time[e.city];
							}
						}
						else {
								earliest_time[e.city] = e.arrival_time;
								prev[e.city] = current_vertex;
								if ( current_vertex == start ) {
									latest_departure_time[e.city] = e.departure_time  ;
								}
								else {
									latest_departure_time[e.city] = latest_departure_time[current_vertex];
								}
						}
						
					}
				}
			}
		}
		int city = destination;
		//while ( city != -1 ) {
		//	System.out.println(city);
		//	city = prev[city];
		//}
	
		if ( earliest_time[destination] < IMPOSSIBLE_TIME ) {
			String time1 = addZeros(latest_departure_time[destination]);
			String time2 = addZeros(earliest_time[destination]);
			System.out.println("Departure "+time1+" "+cities[start]);
			System.out.println("Arrival   "+time2+" "+cities[destination]);
		}
		else {
			System.out.println("No connection");
		}
		
	}

	public static String addZeros(int i) {
		String result = Integer.toString(i);
		while ( result.length() < 4 ) {
			result = "0"+result;
		}
		return result;
	}

	private static void print(int[] latest_departure_time) {
		for (int i = 0; i < latest_departure_time.length; i++) {
			System.out.print(latest_departure_time[i]+" ");
		}
		System.out.println();
	}

	public static int getMiniminIndex(int[] earliest_time , boolean []is_not_eligable) {
		int min = 0;
		int min_index = 0;
		boolean is_initialzed = false;
		for ( int k = 0 ; k < earliest_time.length ; ++k ) {
			if ( ! is_not_eligable[k] ) {
				if ( is_initialzed ) {
					if ( min > earliest_time[k] ) {
						min = earliest_time[k];
						min_index = k;
					}
				}
				else {
					min = earliest_time[k];
					min_index = k;
					is_initialzed = true;
				}
			}
		}
		if ( ! is_initialzed ) {
			System.out.println("THERE ARE NO VERTICES FROM WITCH TO CHOOSE FROM");
			//return -1;
		}
		return min_index;
	}

	public static void populateGraph( String[] cities , String[][] routes ) {
			graph = new ArrayList[cities.length];
			for ( int k = 0 ; k < cities.length ; ++k ) {
				graph[k] = new ArrayList<Edge3>();
			}
			for ( int k = 0 ; k < routes.length ; ++k ) {
				int previous_time = new Integer(routes[k][0].substring(0,routes[k][0].indexOf(' ')));
				int previous_city = getIndex(cities, routes[k][0].substring(routes[k][0].indexOf(' ')+1));
				for ( int m = 1 ; m < routes[k].length ; ++m ) {
					int current_time = new Integer(routes[k][m].substring(0,routes[k][m].indexOf(' ')));
					int current_city = getIndex(cities, routes[k][m].substring(routes[k][m].indexOf(' ')+1));
					if ( previous_city == -1 || current_city == -1 ) {
						break;
					}
					graph[previous_city].add(new Edge3(current_city,current_time,previous_time));
					previous_time = current_time;
					previous_city = current_city;
				}
			}
	}

	public static int getIndex ( String cities[] , String city ) {
		for (int i = 0; i < cities.length; i++) {
			if ( cities[i].equals(city) ) {
				return i;
			}
		}
		//System.out.println("There is no city wiht the name "+city+" in my database.");
		return -1;
	}

}

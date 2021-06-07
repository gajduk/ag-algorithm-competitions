package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Route {
	int departure;
	int arrival;
	int vertex;
	
	public Route ( int a , int b , int c ) {
		departure = a;
		arrival = b;
		vertex = c;
	}
	
	@Override
	public String toString() {
		return departure+" "+arrival+" "+vertex;
	}
}

public class Problem7 {
	
	public static String edges[];
	public static String cities[] = new String[100];
	public static ArrayList<Route> neighbours[] = new ArrayList[100]; 
	public static int num_cities;
	
	public static void calculateMinimumPath(String start, String end ) {
		populateGraph();
//		System.out.println(Arrays.toString(neighbours));
		System.out.println(Arrays.toString(cities));
		int index1 = getIndex(start);
		int index2 = getIndex(end);
		if ( index1 == -1 || index2 == -1 ) {
			System.out.println("There is no route Vladimir can take.");
			return;
		}
		int num_days = search(index1,index2);
		System.out.println("Vladimir needs "+num_days+" litre(s) of blood.");
	}
	
	public static int search ( int start , int end ) {
		ArrayList<Route> queue = new ArrayList<Route>();
		boolean u[] = new boolean[num_cities];
		int days[] = new int[num_cities];
		queue.add(new Route(18,18,start));
		for ( int i = 0 ; i < days.length ; ++i ) {
			days[i] = Integer.MAX_VALUE/5;
		}
		days[start] = 0;
		System.out.println(Arrays.toString(neighbours));
		while ( true ) {
			Route current = queue.remove(0);
			int current_vertex = current.vertex;
			int current_time = current.arrival;
//			System.out.println(cities[current_vertex]);
			for ( Route e : neighbours[current_vertex] ) {
				if ( !u[e.vertex] ) {
					boolean ok = false;
					if ( current_time < 7 ) {
						if ( e.departure < 7 && e.departure >= current_time ) {
							ok = true;
						}
					}
					else {
						if ( e.departure < 7 ) {
							ok = true;
						}
						else {
							if ( e.departure >= current_time ) {
								ok = true;
							}
						}
					}
					if ( ok ) {
						days[e.vertex] = Math.min(days[e.vertex],days[current_vertex]);
					}
					else {
						days[e.vertex] = Math.min(days[e.vertex],days[current_vertex]+1);
					}
				}
			}
			System.out.println(Arrays.toString(days));
			u[current_vertex] = true;
			int min = Integer.MAX_VALUE/5;
			Route min_route = new Route(18,6,-1);
			for ( Route e : neighbours[current_vertex] ) {
				if ( !u[e.vertex] ) {
					if ( days[e.vertex] <= min ) {
						min = days[e.vertex];
						if ( days[e.vertex] == min ) {
							boolean new_min = false;
							if ( min_route.arrival < 7 ) {
								if ( e.arrival < 7 && e.arrival <= current_time ) {
									new_min = true;
								}
							}
							else {
								if ( e.arrival < 7 ) {
									new_min = true;
								}
								else {
									if ( e.arrival <= current_time ) {
										new_min = true;
									}
								}
							}
							if ( new_min ) {
								min_route = e;
							}
						}
						else {
							min_route = e;
						}
					}
				}
			}
			if ( min_route.vertex == -1 ) {
				break;
			}
			queue.add(min_route);
		}
		return days[end] == Integer.MAX_VALUE/5 ? -1 : days[end];
	}
	
	public static void populateGraph () {
		for ( int i = 0 ; i < edges.length ; ++i ) {
			StringTokenizer tkr = new StringTokenizer(edges[i]);
			String city1 = tkr.nextToken();
			String city2 = tkr.nextToken();
			int departure = Integer.parseInt(tkr.nextToken());
			int arrival = Integer.parseInt(tkr.nextToken());
			departure %= 24;
			arrival %= 24;
			if ( departure >= 7 && departure <= 17 || arrival >= 7 && arrival <= 17 ) {
				continue;
			}
			if ( departure < 7 && arrival < 7 ) {
				if ( departure > arrival ) {
					continue;
				}
			}
			if ( departure > 17 && arrival > 17 ) {
				if ( departure > arrival ) {
					continue;
				}
			}
			if ( departure < 7 && arrival > 17 ) {
				continue;
			}
			int index1 = getIndex(city1);
			if ( index1 == -1 ) {
				index1 = num_cities;
				neighbours[num_cities] = new ArrayList<Route>();
				cities[num_cities++] = city1;
			}
			int index2 = getIndex(city2);
			if ( index2 == -1 ) {
				neighbours[num_cities] = new ArrayList<Route>();
				index2 = num_cities;
				cities[num_cities++] = city2;
			}
			neighbours[index1].add(new Route(departure,arrival,index2));
		}
	}
	
	public static int getIndex( String city ) {
		for ( int i = 0 ; i < num_cities ; ++i ) {
			if ( cities[i].equals(city) ) {
				return i;
			}
		}
		return -1;
	}

	public static void main ( String args[] ) {
		test_file();
		test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem7.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		int num_edges1 = new Integer(in.nextLine());
    		//String edges[] = new String[ num_edges1];
    		edges = new String[ num_edges1];
    		for ( int m = 0 ; m < num_edges1 ; ++m ) {
    			edges[m] = in.nextLine();
    		}
    		String start = in.next();
    		String end = in.nextLine().replace(" ","");
    		System.out.println("Test Case "+(k+1)+".");
    		calculateMinimumPath(start,end);
    	}
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		int num_edges1 = new Integer(in.nextLine());
    		//String edges[] = new String[ num_edges1];
    		edges = new String[num_edges1];
    		for ( int m = 0 ; m < num_edges1 ; ++m ) {
    			edges[m] = in.nextLine();
    		}
    		String start = in.next();
    		String end = in.nextLine().replace(" ","");
    		System.out.println("Test Case "+(k+1)+".");
    		calculateMinimumPath(start,end);
    	}
	}
	
	
	
	
	
}

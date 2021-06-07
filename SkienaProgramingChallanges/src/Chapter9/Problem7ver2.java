package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Edge1 {
	public String a;
	public String b;
	public Edge1( String a1 , String b1 ) {
		a = a1;
		b = b1;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return a+" "+b;
	}
}

public class Problem7ver2 {

	public static String edges[];
	public static int num_edges;
	public static ArrayList<Edge1> routes;
	
	public static void main ( String args[] ) {
		test_file();
		//test_judge();
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
	
	private static void calculateMinimumPath(String start, String end) {
		if ( start == end ) {
			System.out.println("Vladimir needs 0 litre(s) of blood.");
			return;
		}
			
		routes = new ArrayList<Edge1>();
		removeInvalidEdges();
		populateRoutes();
		int result = bfs(start, end);
		if ( result == -1 ) {
			System.out.println("There is no route Vladimir can take.");
		}
		else {
			System.out.println("Vladimir needs "+result+" litre(s) of blood.");
		}
	}

	public static void populateRoutes() {
		for ( int k = 0 ; k < num_edges ; ++k ) {
			StringTokenizer tkr = new StringTokenizer(edges[k]);
			String a = tkr.nextToken();
			String b = tkr.nextToken();
			int time_departure = new Integer(tkr.nextToken());
			int time_duration = new Integer(tkr.nextToken());
			routes.add(new Edge1(a,b));
			advanceRoutes(a,b,(time_departure+time_duration)%24);
		}
		
	}

	private static void advanceRoutes(String a, String b, int time) {
		if ( time > 6 && time < 18 )
			return;
		for ( int k = 0 ; k < num_edges ; ++k ) {
			if ( edges[k].indexOf(b) == 0 ) {
				StringTokenizer tkr = new StringTokenizer(edges[k]);
				if ( tkr.countTokens() != 4 )
					continue;
				String a1 = tkr.nextToken();
				String b1 = tkr.nextToken();
				int time_departure = -7;
				int time_duration = 1;
				time_departure = new Integer(tkr.nextToken());
				time_duration = new Integer(tkr.nextToken());
				if ( (time+6)%24 <= (time_departure+6)%24 ) {
					Edge1 temp = new Edge1(a, b1);
					if ( ! routes.contains(temp) ) {
						routes.add(temp);
					}
					advanceRoutes(a,b1,(time_departure+time_duration)%24);
				}
				/*
				try {
					time_departure = new Integer(tkr.nextToken());
				}
				catch ( Exception e ) {
					
				}
				try {
					time_duration = new Integer(tkr.nextToken());
				}
				catch ( Exception e ) {
					
				}
				if ( (time+6)%24 <= (time_departure+6)%24 ) {
					Edge1 temp = new Edge1(a, b1);
					if ( ! routes.contains(temp) ) {
						routes.add(temp);
					}
					try {
							advanceRoutes(a,b1,(time_departure+time_duration)%24);
					}
					catch ( Exception e ) {
						
					}
				
				}
				*/
			}
		}
	}
	
	public static int bfs ( String start , String end ) {
		ArrayList<String> queue = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		queue.add(start);
		visited.add(start);
		int level = -1;
		int border = 1;
		int front = 1;
		int rear = 0;
		while ( ! queue.isEmpty() ) {
			String current_city = queue.remove(0);
			rear++;
			if ( current_city.equals(end) ) {
				return level;
			}
			for ( Edge1 edge : routes ) {
				if ( edge.a.equals(current_city) ) {
					if ( ! visited.contains(edge.b) ) {
						visited.add(edge.b);
						queue.add(edge.b);
						front++;
					}
				}
			}
			if ( rear == border ) {
				border = front;
				level++;
			}
		}
		return -1;
	}

	public static void removeInvalidEdges() {
		num_edges = 0;
		for ( int k = 0 ; k < edges.length ; ++k ) {
			if ( isInvalid(edges[k]) ) {
				for ( int m = k ; m < edges.length-1 ; ++m ) {
					edges[m] = edges[m+1];
				}
			}
			else {
				num_edges++;
			}
		}
	}

	public static boolean isInvalid(String string) {
		StringTokenizer tkr = new StringTokenizer(string);
		tkr.nextToken();
		tkr.nextToken();
		int time_departure = new Integer(tkr.nextToken());
		int time_duration = new Integer(tkr.nextToken());
		if ( time_duration > 12 )
			return true;
		if ( time_departure > 6 && time_departure < 18 )
			return true;
		int time_arrival = (time_departure+time_duration) % 24;
		if ( time_arrival > 6 && time_arrival < 18 )
			return true;
		return false;
	}

	
	
	
}

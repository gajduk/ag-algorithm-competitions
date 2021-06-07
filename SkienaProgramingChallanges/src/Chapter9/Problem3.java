package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem3 {
	
	public static final int MAX_CITITES = 101;
	public static int num_cities;
	public static int max_passangers[] = new int[MAX_CITITES];
	public static boolean is_visited[] = new boolean[MAX_CITITES];
	public static int graph[][] = new int[MAX_CITITES][MAX_CITITES];
	
	public static int getNextBestVertex() {
		int max = 0;
		int max_index = 0;
		for ( int i = 0 ; i < num_cities; ++i ) {
			if ( ! is_visited[i] ) {
				if ( max < max_passangers[i] ) {
					max = max_passangers[i];
					max_index = i;
				}
			}
		}
		return max_index;
	}
	
	public static int passengerCapacity ( int start_vertex , int end_vertex ) {
		max_passangers[start_vertex] = Integer.MAX_VALUE;
		while ( true ) {
			int current_vertex = getNextBestVertex();
			if ( current_vertex == end_vertex )
				return max_passangers[end_vertex];
			is_visited[current_vertex] = true;
			for ( int i = 0 ; i < num_cities ; ++i ) {
				if ( ! is_visited[i] && graph[current_vertex][i] > 0 ) {
					if ( min(graph[current_vertex][i],max_passangers[current_vertex]) > max_passangers[i] ) {
						max_passangers[i] = min(graph[current_vertex][i],max_passangers[current_vertex]);
					}
				}
			}
		}
	}
	
	public static void populateGraph( int num_vertices , String edges[] ) {
		num_cities = num_vertices;
		for ( int i = 0 ; i < edges.length ; ++i ) {
			StringTokenizer edge_parametars = new StringTokenizer(edges[i]);
			int vertex_1 = new Integer(edge_parametars.nextToken())-1;
			int vertex_2 = new Integer(edge_parametars.nextToken())-1;
			int edge_weight = new Integer(edge_parametars.nextToken());
			graph[vertex_1][vertex_2] = edge_weight;
			graph[vertex_2][vertex_1] = edge_weight;
		}
	}
	
	public static void cleanAllData ( ) {
		max_passangers = new int[MAX_CITITES];
		is_visited = new boolean[MAX_CITITES];
		graph = new int[MAX_CITITES][MAX_CITITES];
	}
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}
	
	public static void Floyed_Warshall()	{
			
		     for(int i = 1; i <= num_cities; i++)
		    	 graph[i][i] = 0;
		     
		    for(int k = 1; k <= num_cities; k++)
		    	for(int i = 1; i <= num_cities; i++)
		             for (int j = 1; j <= num_cities; j++)
		            	 graph[i][j] = max(graph[i][j], min(graph[i][k], graph[k][j]));
	}
	
	public static int max(int a, int b) {
		if ( a > b )
			return a;
		return b;
	}

	public static void test_judge ( ) {
		Scanner in = new Scanner(System.in);
		int test_case = 1;
    	while ( true ){
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		if (! numbers.hasMoreTokens() )
    			return;
    		int num_vertices = new Integer(numbers.nextToken());
    		int num_edges = new Integer(numbers.nextToken());
    		if ( test_case != 1 ) {
    			System.out.println();
    		}
    		if ( num_edges == 0 && num_vertices == 0 )
    			break;
    		
    		String edges[] = new String[num_edges];
    		for ( int k = 0 ; k < num_edges ; ++k ) {
    			edges[k] = in.nextLine();
    		}
    		numbers = new StringTokenizer(in.nextLine());
    		/*
    		populateGraph(num_vertices, edges);
    		numbers = new StringTokenizer(in.nextLine());
    		int start_vertex = new Integer(numbers.nextToken())-1;
    		int end_vertex = new Integer(numbers.nextToken())-1;
    		int passenger_capacity = passengerCapacity(start_vertex, end_vertex)-1;
    		int num_passengers = new Integer(numbers.nextToken());
    		if ( passenger_capacity == 0 ) {
    			System.out.println("Minimum Number of Trips = "+num_passengers);
    			continue;
    		}
    		System.out.println("Scenario #"+test_case);
    		if ( num_passengers % passenger_capacity == 0 ) {
    			System.out.println("Minimum Number of Trips = "+num_passengers / passenger_capacity);
    		}
    		else {
    			System.out.println("Minimum Number of Trips = "+(num_passengers / passenger_capacity + 1));
    		}
    		++test_case;
    		cleanAllData();
    		*/
    	}
	}
	
	public static void test_file ( ) {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int test_case = 1;
    	while ( true ){
    		StringTokenizer numbers = new StringTokenizer(in.nextLine()," ");
    		int num_vertices = new Integer(numbers.nextToken());
    		int num_edges = new Integer(numbers.nextToken());
    		if ( test_case != 1 ) {
    			System.out.println();
    		}
    		if ( num_edges == 0 && num_vertices == 0 )
    			break;
    		
    		String edges[] = new String[num_edges];
    		for ( int k = 0 ; k < num_edges ; ++k ) {
    			edges[k] = in.nextLine();
    		}
    		populateGraph(num_vertices, edges);
    		numbers = new StringTokenizer(in.nextLine());
    		int start_vertex = new Integer(numbers.nextToken())-1;
    		int end_vertex = new Integer(numbers.nextToken())-1;
    		int passenger_capacity = passengerCapacity(start_vertex, end_vertex)-1;
    		int num_passengers = new Integer(numbers.nextToken());
    		if ( passenger_capacity == 0 ) {
    			System.out.println("Minimum Number of Trips = "+num_passengers);
    			continue;
    		}
    		System.out.println("Scenario #"+test_case);
    		if ( num_passengers % passenger_capacity == 0 ) {
    			System.out.println("Minimum Number of Trips = "+num_passengers / passenger_capacity);
    		}
    		else {
    			System.out.println("Minimum Number of Trips = "+(num_passengers / passenger_capacity + 1));
    		}
    		++test_case;
    		cleanAllData();
    	}
	}
	
	public static int min ( int number1 , int number2 ) {
		if ( number1 > number2 )
			return number2;
		return number1;
	}

}

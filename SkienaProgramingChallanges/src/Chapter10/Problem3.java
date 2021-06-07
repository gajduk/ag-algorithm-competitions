package Chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem3 {

	public static final int MAX_NUM_EDGES = 21;
	public static final int MAX_NUM_VERTICES = 502;
	
	public static void main ( String args [] ) {
		//test_calculateMaximimDistanceMultipleSources();
	//	test_file();
		test_judge();
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		String s_numbers = in.nextLine();
    		StringTokenizer numbers = new StringTokenizer(s_numbers);
    		int num_fire_stations = new Integer(numbers.nextToken());
    		int num_vertices = new Integer(numbers.nextToken());
    		int counter = 0;
    		boolean is_a_fire_station[] = new boolean[MAX_NUM_VERTICES];
    		for ( int m = 0 ; m < num_fire_stations ; ++m ) {
    			int fire_station_location = new Integer(in.nextLine())-1;
    			if ( ! is_a_fire_station[fire_station_location] ) {
    				is_a_fire_station[fire_station_location] = true;
    				counter++;
    			}
    		}
    		int fire_stations[] = new int[counter+1];
    		for ( int m = 0 , i = 0 ; m < MAX_NUM_VERTICES ; ++m ) {
    			if (  is_a_fire_station[m] ) {
    				fire_stations[i++] = m;
    			}
    		}
    		int graph[][] = new int[num_vertices][MAX_NUM_EDGES+1];
    		int weights[][] = new int[num_vertices][MAX_NUM_EDGES+1];
   			while ( in.hasNext() ) {
    				String edge = in.nextLine();
    				if ( edge.length() == 0 ) {
    					break;
    				}
    				StringTokenizer edge_parametars = new StringTokenizer(edge);
    				int v1 = new Integer(edge_parametars.nextToken())-1;
    				int v2 = new Integer(edge_parametars.nextToken())-1;
    				int weight = new Integer(edge_parametars.nextToken());
    				graph[v1][graph[v1][MAX_NUM_EDGES]++] = v2;
    				graph[v2][graph[v2][MAX_NUM_EDGES]++] = v1;
    				weights[v1][weights[v1][MAX_NUM_EDGES]++] = weight;
    				weights[v2][weights[v2][MAX_NUM_EDGES]++] = weight;
    		}
   			//print(graph);
   			//print(weights);
   			System.out.println((findBestLocation(graph,weights,fire_stations,is_a_fire_station)+1));
   			if ( num_test_cases-1 > k ) {
   				System.out.println();
   			}
    	}
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		String s_numbers = in.nextLine();
    		StringTokenizer numbers = new StringTokenizer(s_numbers);
    		int num_fire_stations = new Integer(numbers.nextToken());
    		int num_vertices = new Integer(numbers.nextToken());
    		int counter = 0;
    		boolean is_a_fire_station[] = new boolean[MAX_NUM_VERTICES];
    		for ( int m = 0 ; m < num_fire_stations ; ++m ) {
    			int fire_station_location = new Integer(in.nextLine())-1;
    			if ( ! is_a_fire_station[fire_station_location] ) {
    				is_a_fire_station[fire_station_location] = true;
    				counter++;
    			}
    		}
    		int fire_stations[] = new int[counter+1];
    		for ( int m = 0 , i = 0 ; m < MAX_NUM_VERTICES ; ++m ) {
    			if ( is_a_fire_station[m] ) {
    				fire_stations[i++] = m;
    			}
    		}
    		int graph[][] = new int[num_vertices][MAX_NUM_EDGES+1];
    		int weights[][] = new int[num_vertices][MAX_NUM_EDGES+1];
   			while ( in.hasNext() ) {
    				String edge = in.nextLine();
    				if ( edge.length() == 0 ) {
    					break;
    				}
    				StringTokenizer edge_parametars = new StringTokenizer(edge);
    				int v1 = new Integer(edge_parametars.nextToken())-1;
    				int v2 = new Integer(edge_parametars.nextToken())-1;
    				int weight = new Integer(edge_parametars.nextToken());
    				graph[v1][graph[v1][MAX_NUM_EDGES]++] = v2;
    				graph[v2][graph[v2][MAX_NUM_EDGES]++] = v1;
    				weights[v1][weights[v1][MAX_NUM_EDGES]++] = weight;
    				weights[v2][weights[v2][MAX_NUM_EDGES]++] = weight;
    		}
   			//print(graph);
   			//print(weights);
   			System.out.println((findBestLocation(graph,weights,fire_stations,is_a_fire_station)+1));
   			if ( num_test_cases-1 > k ) {
   				System.out.println();
   			}
    	}
	}
	
	public static void print ( int []array ) {
		for ( int k = 0 ; k < array.length ; ++k ) {
			System.out.print(array[k]+" ");
		}
		System.out.println();
	}
	
	public static void print ( int matrix[][] ) {
		for ( int k = 0 ; k < matrix.length ; ++k ) {
			for ( int m = 0 ; m < matrix[0].length ; ++m ) {
				System.out.print(matrix[k][m]+" ");
			}
			System.out.println();
		}
	}
	
	
	private static int findBestLocation(int[][] graph, int[][] weights,	int[] fire_stations, boolean[] is_a_fire_station) {
		int min = Integer.MAX_VALUE;
		int min_vertex = -1;
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( ! is_a_fire_station[k] ) {
				fire_stations[fire_stations.length-1] = k;
				int temp = calculateMaximimDistanceMultipleSources(graph,weights,fire_stations);
				if ( temp < min ) {
					min_vertex = k;
					min = temp;
				}
			}
		}
		if ( min_vertex == - 1 ) {
			//System.out.println("I can place not such a station.");
			min_vertex = 0;
		}
		return min_vertex;
	}

	private static void test_calculateMaximimDistanceMultipleSources() {
					  //    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0
		int graph[][] = {     { 1,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3 },
							  { 0,2,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4 },
							  { 0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2 },
						      { 0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2 },
						 	  { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 },
						      { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }  };
		int weigths[][] = {   { 4,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3 },
							  { 4,2,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4 },
							  { 2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3 },
						      { 1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2 },
						 	  { 3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 },
						 	  { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }  };
		int sources[] = { 0 , 4 };
		System.out.println(calculateMaximimDistanceMultipleSources(graph,weigths,sources));
	}

	public static int calculateMaximimDistanceMultipleSources ( int graph[][] , int weights[][] , int sources[] ) {
		if ( sources.length == 0 ) {
			System.out.println("I HAVE NO SOURCES FROM WITCH TO CALCULATE MY DISTANCE");
			return 1337;
		}
		int distance[] = new int[graph.length];
		boolean is_in_set[] = new boolean[graph.length];
		
		for ( int k = 0 ; k < graph.length ; ++k ) {
			distance[k] = Integer.MAX_VALUE/2;
		}
		for ( int m = 0 ; m < sources.length ; ++m  ) {
			for ( int k = 0 ; k < graph[sources[m]][MAX_NUM_EDGES] ; ++k ) {
				if ( weights[sources[m]][k] < distance[graph[sources[m]][k]] ) {
						distance[graph[sources[m]][k]] = weights[sources[m]][k];
				}
			}
		}
		for ( int m = 0 ; m < sources.length ; ++m  ) {
			distance[sources[m]] = 0;
			is_in_set[sources[m]] = true;
		}
		/*
		System.out.println(sources[sources.length-1]+" "+"On Start");
		print(distance);
		System.out.println();
		*/
		for ( int k = sources.length ; k < graph.length ; ++k ) {
			int current_vertex = getMinimumIndex(distance,is_in_set);
			is_in_set[current_vertex] = true;
			/*
			System.out.println(sources[sources.length-1]+" "+"during is chosen "+current_vertex);
			print(distance);
			System.out.println();
			*/
			for ( int m = 0 ; m < graph[current_vertex][MAX_NUM_EDGES] ; ++m ) {
				if ( weights[current_vertex][m]+distance[current_vertex] < distance[graph[current_vertex][m]] ) {
						distance[graph[current_vertex][m]] = weights[current_vertex][m]+distance[current_vertex];
				}
			}
		}
		int max = 0;
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( distance[k] > max ) {
				max = distance[k];
			}
		}
		/*
		System.out.println(sources[sources.length-1]+" "+"On FINISH");
		print(distance);
		System.out.println();
		**/
		return max;
	}
	
	/*
	public static int calculateMaximimDistanceMultipleSources ( int graph[][] , int sources[] ) {
		if ( sources.length == 0 ) {
			System.out.println("I HAVE NO SOURCES FROM WITCH TO CALCULATE MY DISTANCE");
			return 1337;
		}
		int distance[] = new int[graph.length];
		boolean is_in_set[] = new boolean[graph.length];
		
		for ( int k = 0 ; k < graph.length ; ++k ) {
			distance[k] = Integer.MAX_VALUE/2;
		}
		for ( int k = 0 ; k < graph.length ; ++k ) {
			for ( int m = 0 ; m < sources.length ; ++m  ) {
				if ( graph[sources[m]][k] != 0 ) {
					if ( graph[sources[m]][k] < distance[k] ) {
						distance[k] = graph[sources[m]][k];
					}
				}
			}
		}
		for ( int m = 0 ; m < sources.length ; ++m  ) {
			distance[sources[m]] = 0;
			is_in_set[sources[m]] = true;
		}
		for ( int k = sources.length ; k < graph.length ; ++k ) {
			int current_vertex = getMinimumIndex(distance,is_in_set);
			is_in_set[current_vertex] = true;
			for ( int m = 0 ; m < graph.length ; ++m ) {
				if ( graph[current_vertex][m] != 0 ) {
					if ( graph[current_vertex][m]+distance[current_vertex] < distance[m] ) {
						distance[m] = graph[current_vertex][m]+distance[current_vertex];
					}
				}
			}
		}
		int max = Integer.MAX_VALUE;
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( distance[k] < max ) {
				max = Integer.MAX_VALUE;
			}
		}
		return max;
	}
	*/

	private static int getMinimumIndex(int[] distance , boolean []is_in_set) {
		int min = distance[0];
		int min_index = 0;
		boolean not_initialized = true;
		for ( int k = 0 ; k < distance.length ; ++k ) {
			if ( !is_in_set[k] ) {
				if ( not_initialized ) {
					min = distance[k];
					min_index = k;
					not_initialized = false;
				}
				else {
					if ( distance[k] < min ) {
						min = distance[k];
						min_index = k;
					}
				}
			}
		}
		if ( not_initialized ) {
			System.out.println("There is no vertex that is not already in the set");
			return -1;
		}
		return min_index;
	}
	
}

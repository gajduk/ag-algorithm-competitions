package Chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem6 {
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem6.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int test_case_id = 1;
    	while ( true ) {
    		int num_places = new Integer(in.nextLine());
    		if ( num_places == 0 ) {
    			break;
    		}
    		String places[] = new String[num_places];
    		for (int i = 0; i < places.length; i++) {
    			places[i] = in.nextLine();				
			}
    		Arrays.sort(places);
    		int graph[][] = new int[num_places][num_places];
    		int num_routes = new Integer(in.nextLine());
    		for ( int m = 0 ; m < num_routes ; ++m ) {
    			StringTokenizer route_parametars = new StringTokenizer(in.nextLine());
    			int v1 = getIndex(places,route_parametars.nextToken());
    			int v2 = getIndex(places,route_parametars.nextToken());
    			graph[v1][v2] = 1;
    			graph[v2][v1] = 1;
    		}
    		int articulation_vertices[] = findArticulationVerticesInDisconectedGraphs(graph);
    		Arrays.sort(articulation_vertices);
    		if ( test_case_id != 1 )
    			System.out.println();
    		System.out.println("City map #"+(test_case_id++)+": "+articulation_vertices.length+" camera(s) found");
    		for ( int k = 0 ; k < articulation_vertices.length ; ++k ) {
    			System.out.println(places[articulation_vertices[k]]);
    		}
    	}
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
		int test_case_id = 1;
    	while ( true ) {
    		int num_places = new Integer(in.nextLine());
    		if ( num_places == 0 ) {
    			break;
    		}
    		String places[] = new String[num_places];
    		for (int i = 0; i < places.length; i++) {
    			places[i] = in.nextLine();				
			}
    		Arrays.sort(places);
    		int graph[][] = new int[num_places][num_places];
    		int num_routes = new Integer(in.nextLine());
    		for ( int m = 0 ; m < num_routes ; ++m ) {
    			StringTokenizer route_parametars = new StringTokenizer(in.nextLine());
    			int v1 = getIndex(places,route_parametars.nextToken());
    			int v2 = getIndex(places,route_parametars.nextToken());
    			graph[v1][v2] = 1;
    			graph[v2][v1] = 1;
    		}
    		int articulation_vertices[] = findArticulationVerticesInDisconectedGraphs(graph);
    		Arrays.sort(articulation_vertices);
    		if ( test_case_id != 1 )
    			System.out.println();
    		System.out.println("City map #"+(test_case_id++)+": "+articulation_vertices.length+" camera(s) found");
    		for ( int k = 0 ; k < articulation_vertices.length ; ++k ) {
    			System.out.println(places[articulation_vertices[k]]);
    		}
    	}
	}
	
	public static int getIndex ( String places[] , String place ) {
		for ( int k = 0 ; k < places.length ; ++k ) {
			if ( places[k].equals(place) )
				return k;
		}
		return -1;
	}
	
	public static boolean is_deleted[];
	public static boolean is_visited[];
	
	public static int[] findArticulationVertices ( int graph[][] ) {
		is_deleted = new boolean[graph.length];
		if ( ! isBiconected(graph) ) {
			return new int[0];
		}
		ArrayList<Integer> list_result = new ArrayList<Integer>();
		for ( int m = 0 ; m < graph.length ; ++m ) {
			if ( isArticulationVertex(graph, m) )  {
				list_result.add(m);
			}
		}
		int result[] = new int[list_result.size()];
		for ( int m = 0 ; m < result.length ; ++m ) {
			result[m] = list_result.get(m);
		}
		return result;
	}
	
	public static boolean isArticulationVertex ( int graph[][] , int vertex_id ) {
		if ( is_deleted == null ) {
			is_deleted = new boolean[graph.length];
		}
		is_deleted[vertex_id] = true;
		boolean result = !isBiconected(graph);
		is_deleted[vertex_id] = false;
		return result;
	}
	
	public static boolean isBiconected ( int graph[][] ) {
		depthFirstSearch(graph);
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( ! is_visited[k] && ! is_deleted[k] ) {
				return false;
			}
		}
		return true;
	}
	
	public static void depthFirstSearch ( int graph[][] ) {
		is_visited = new boolean[graph.length];
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( ! is_visited[k] && ! is_deleted[k] ) {
				depthFirstSearchRecursive(graph,k);
				break;
			}
		}
	}

	private static void depthFirstSearchRecursive( int[][] graph , int current_vertex ) {
		is_visited[current_vertex] = true;
		for ( int k = 0 ; k < graph[current_vertex].length ; ++k ) {
			if ( graph[current_vertex][k] != 0 && ! is_visited[k] && ! is_deleted[k]  ) {
				depthFirstSearchRecursive(graph,k);
			}
		}	
	}
	

	public static int set_id[];
	public static int num_sets;
	
	public static void constructSets ( int graph[][] ) {
		is_visited = new boolean[graph.length];
		set_id = new int[graph.length];
		num_sets = 0;
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( ! is_visited[k] ) {
				constructSetsRecursive(graph,k,num_sets++);
			}
		}
	}
	
	private static void constructSetsRecursive(int[][] graph, int current_vertex, int current_set_id ) {
		is_visited[current_vertex] = true;
		set_id[current_vertex] = current_set_id;
		for ( int k = 0 ; k < graph[current_vertex].length ; ++k ) {
			if ( graph[current_vertex][k] != 0 && ! is_visited[k] ) {
				constructSetsRecursive(graph,k,current_set_id);
			}
		}	
	}
		
	public static boolean isArticulationVertexInDisconectedGraphs ( int graph[][] , int vertex_id ) {
		if ( is_deleted == null ) {
			is_deleted = new boolean[graph.length];
		}
		if ( set_id == null ) {
			constructSets(graph);
		}
		is_deleted[vertex_id] = true;
		depthFirstSearchWithStartingVertex(graph,vertex_id);
		is_deleted[vertex_id] = false;
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( set_id[k] == set_id[vertex_id] && ! is_visited[k] ) { 
				return true;
			}
		}
		return false;
	}
	
	public static void depthFirstSearchWithStartingVertex ( int graph[][] , int start_vertex ) {
		is_visited = new boolean[graph.length];
		is_visited[start_vertex] = true;
		for ( int m = 0 ; m < graph.length ; ++m ) {
			if ( set_id[m]  == set_id[start_vertex] && m != start_vertex ) {
				depthFirstSearchRecursive(graph,m);
				break;
			}
		}
	}

	public static void depthFirstSearchWithStartingVertexRecursive( int[][] graph , int current_vertex ) {
		is_visited[current_vertex] = true;
		for ( int k = 0 ; k < graph[current_vertex].length ; ++k ) {
			if ( graph[current_vertex][k] != 0 && ! is_visited[k] && ! is_deleted[k]  ) {
				depthFirstSearchWithStartingVertexRecursive(graph,k);
			}
		}	
	}
		
	public static int[] findArticulationVerticesInDisconectedGraphs ( int graph[][] ) {
		is_deleted = new boolean[graph.length];
		constructSets(graph);
		ArrayList<Integer> list_result = new ArrayList<Integer>();
		for ( int m = 0 ; m < graph.length ; ++m ) {
			if ( isArticulationVertexInDisconectedGraphs(graph, m) )  {
				list_result.add(m);
			}
		}
		int result[] = new int[list_result.size()];
		for ( int m = 0 ; m < result.length ; ++m ) {
			result[m] = list_result.get(m);
		}
		return result;
	}
	
	

}

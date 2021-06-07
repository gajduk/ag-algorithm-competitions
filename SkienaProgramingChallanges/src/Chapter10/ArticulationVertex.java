package Chapter10;

import java.util.ArrayList;

public class ArticulationVertex {

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
		num_sets = 0;
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( ! is_visited[k] ) {
				constructSetsRecursive(graph,k,num_sets++);
				break;
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
		depthFirstSearchRecursive(graph,start_vertex);
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

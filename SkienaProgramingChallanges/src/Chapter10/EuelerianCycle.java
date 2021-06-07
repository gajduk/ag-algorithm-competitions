package Chapter10;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class EuelerianCycle {
	
	public static void print ( int matrix[][] ) {
		for ( int i = 0 ; i < matrix.length ; ++i ) {
			for ( int k = 0 ; k < matrix[i].length ; ++k ) {
				System.out.print(matrix[i][k]+" ");
			}
			System.out.println();
		}
	}
	
	public static int graph[][];
	
	public void populateGraph(String[] edges) throws Exception {
		for ( int m = 0 ; m < edges.length ; ++m ) {
			String s_v1 = edges[m].substring(0,edges[m].indexOf(' '));
			String s_v2 = edges[m].substring(edges[m].indexOf(' ')+1,edges[m].lastIndexOf(' '));
			int weight = 1;
			try {
				weight = new Integer(edges[m].substring(edges[m].lastIndexOf(' ')+1));
			} catch (Exception e) {
				//DO NOTHING
			}
			try {
				int v1 = new Integer(s_v1);
				int v2 = new Integer(s_v2);
				graph[v1][v2] = weight;
			} catch (Exception e) {
				throw new Exception("The edge "+edges[m]+" could not be added because:\n"+e.getMessage());
			}
			
		}
	}
	
	public static boolean isBridge ( int start , int  end , int graph[][] , int degree[] ) {
		ArrayList<Integer> queue = new ArrayList<Integer>();
		--graph[start][end];
		--graph[end][start];
		//print(graph);
		//System.out.println();
		boolean is_visited[] = new boolean[graph.length];
		for ( int i = 0 ; i < degree.length ; ++i ) {
			is_visited[i] = degree[i] == 0;
		}
		queue.add(start);
		is_visited[start] = true;
		while ( ! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			for ( int j = 0 ; j < graph.length ; ++j ) {
				if ( graph[current_vertex][j] > 0 && ! is_visited[j] ) {
					queue.add(j);
					is_visited[j] = true;
				}
			}
		}
		++graph[end][start];
		++graph[start][end];
		//print(is_visited);
		for ( int j = 0 ; j < graph.length ; ++j ) {
			if ( ! is_visited[j] ) {
				return true;
			}
		}
		
		return false;
	}
	
	private static void print(boolean[] is_visited) {
		for ( int i = 0 ; i < is_visited.length ; ++i ) {
			System.out.print(is_visited[i]?"1":"0");
		}
		System.out.println();
	}

	public static ArrayList<String> findCycle ( int graph[][]  ) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int prev[] = new int[graph.length];
		boolean is_visited[] = new boolean[graph.length];
		int start = -1;
		for ( int i = 0 ; i < graph.length ; ++i ) {
			boolean isolated = true;
			for ( int j = 0 ; j < graph.length ; ++j ) {
				if ( graph[i][j] > 0 ) {
					isolated = false;
					break;
				}
			}	
			if ( !isolated ) {
				start = i;
			}
		}
		if ( start == - 1 ) {
			System.out.println("THE GRAPH IS COMPLETELY ISOLATED");
		}
		is_visited[start] = true;
		prev[start] = -1;
		queue.add(start);
		int vertex = -1;
		boolean cycle_found = false;
		while ( ! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			//System.out.println(current_vertex);
			for ( int i = 0 ; i < graph.length ; ++i ) {
				if ( graph[current_vertex][i] > 0 ) {
					if ( is_visited[i] ) {
						vertex = current_vertex;
						cycle_found = true;
						break;
					}
					else {
						is_visited[i] = true;
						prev[i] = current_vertex;
						queue.add(i);
					}
				}
			}
			if ( cycle_found ) {
				break;
			}
		}
		if ( ! cycle_found ) {
			System.out.println("The graph contains no more cycles");
		}
		int temp = vertex;
		while ( prev[vertex] != -1 ) {
			System.out.println(prev[vertex]+" "+vertex);
			result.add(prev[vertex]+" "+vertex);
			vertex = prev[vertex];
		}
		System.out.println(temp+" "+vertex);
		result.add(temp+" "+vertex);
		return result;
	}
	
	public static ArrayList<String> findEulerianCycleHierholzer ( int graph[][] ) {
		ArrayList<String> result = new ArrayList<String>();
		while ( hasEdges(graph) ) {
			removeEdges(findCycle(graph),graph);
		}
		return result;
	}
	
	private static void removeEdges(ArrayList<String> findCycle , int graph[][] ) {
		for (String string : findCycle) {
			StringTokenizer edge = new StringTokenizer(string);
			int v1 = new Integer(edge.nextToken());
			int v2 = new Integer(edge.nextToken());
			--graph[v1][v2];
		//	--graph[v2][v1];
		}
	}

	private static boolean hasEdges(int[][] graph) {
		for ( int i = 0 ; i < graph.length ; ++i ) {
			for ( int j = 0 ; j < graph.length ; ++j ) {
				if ( graph[i][j] != 0)
					return true;
			}
		}
		return false;
	}

	public static String[] findEulerianCycle( int graph[][] /*the graph adjacency matrix with parralel ribs denoted as integers larger than 1*/ ) {
		int degree[] = new int[graph.length];
		int num_total_edges = 0;
		for ( int k = 0 ; k < degree.length ; ++k ) {
			for ( int m = 0 ; m < graph[0].length ; ++m ) {
				degree[k] += graph[k][m];
			}
			if ( /* degree[k] == 0  || */ degree[k] % 2 != 0 ) {
				System.out.println("THERE IS NO EUELERIAN CYCLE");
				return new String[0];
			}
			num_total_edges += degree[k];
		}	
		String result[] = new String[num_total_edges];
		int start_vertex = 0;
		while ( degree[start_vertex] == 0 ) {
			++start_vertex;
			if ( start_vertex > degree.length ) {
				System.out.println("THERE IS NO EUELERIAN CYCLE");
				return new String[0];
			}
		}
		for ( int k = 1 ; k < num_total_edges-1 ; ++k ) {
			int next_vertex = 0;
			while ( graph[start_vertex][next_vertex] == 0 || degree[next_vertex] < 2 || ( degree[start_vertex] == 0 &&  graph[next_vertex][start_vertex] < 2 ) ) {
				++next_vertex;
			}
			System.out.println(next_vertex);
			result[k] = start_vertex+" "+next_vertex;
			--graph[start_vertex][next_vertex];
			--graph[next_vertex][start_vertex];
			--degree[next_vertex];
			start_vertex = next_vertex;
		}
		int next_vertex = 0;
		while ( graph[start_vertex][next_vertex] == 0 ) {
					++next_vertex;
		}
		result[num_total_edges-1] = start_vertex+" "+next_vertex;
		return result;
	}
	
	public static void findEulerianCycle1 ( int graph[][] ) {
		int degree[] = new int[graph.length];
		for ( int i = 0 ; i < graph.length ; ++i ) {
			for ( int j = 0 ; j < graph.length ; ++j ) {
				if ( graph[i][j] > 0 ) {
					degree[i] += graph[i][j];
				}
			}
		}
		int start = -1;
		for ( int i = 0 ; i < graph.length ; ++i ) {
			
			System.out.println();
			if ( degree[i] % 2 != 0 ) {
				System.out.println("NO EUELERIAN CIRCUIT");
				return;
			}
			if ( degree[i] > 0 ) {
				start = i;
			}
		}
		if ( start == -1 ) {
			System.out.println("THE GRAPH IS ISOLATED");
			return;
		}
		while ( true ) {
			boolean found_one = false;
			
			int next = -1;
			for ( int i = 0 ; i < graph.length ; ++i ) {
				if ( graph[start][i] > 0 && ! isBridge(start, i, graph,degree) ) {
					next = i;
					found_one = true;
					break;
				}
			}
			if ( !found_one ) {
				for ( int i = 0 ; i < graph.length ; ++i ) {
					if ( graph[start][i] > 0 ) {
						next = i;
					}
				}
			}
			System.out.println(start+" "+next);
			--graph[start][next];
			--graph[next][start];
			--degree[start];
			--degree[next];
			start = next;
			//print(degree);
			if ( degree[start] == 0 ) {
				break;
			}
		}
		
	}
	
	public static void main ( String args[] ) {
		int graph[][] = {    { 0,1,1,0,0,2 } ,
							 { 1,0,2,1,0,0 } ,
							 { 1,2,0,0,3,0 } ,
							 { 0,1,0,0,1,0 } ,
							 { 0,0,3,1,0,0 } ,
							 { 2,0,0,0,0,0 } };
		int graph1[][] = {       { 0,1,1,1,0 } ,
								 { 1,0,1,1,0 } ,
								 { 1,1,0,1,1 } ,
								 { 1,1,1,0,1 } ,
								 { 0,0,1,1,0 } };
		int graph2[][] = {       { 0,0,1,0,0 } ,
								 { 1,0,0,0,0} ,
								 { 0,1,0,1,0 } ,
								 { 0,0,0,0,1 } ,
								 { 0,0,1,0,0 } 			  };
		int graph3[][] = {       { 0,1,1,0,0 } ,
								 { 1,0,1,0,0 } ,
								 { 1,1,0,1,1 } ,
								 { 0,0,1,0,1 } ,
								 { 0,0,1,1,0 } };
		int graph4[][] = {       { 0,1,1,0,0,1 } ,
								 { 1,0,1,0,0,0 } ,
								 { 1,1,0,1,1,0 } ,
								 { 0,0,1,0,1,0 } ,
								 { 0,0,1,1,0,1 } ,
								 { 1,0,0,0,1,0 } };
		int graph5[][] = {       { 0,1,1,0,0,1,1 } ,
								 { 1,0,1,0,0,0,0 } ,
								 { 1,1,0,1,1,0,0 } ,
								 { 0,0,1,0,1,0,0 } ,
								 { 0,0,1,1,0,1,1 } ,
								 { 1,0,0,0,1,0,0 } ,
								 { 1,0,0,0,1,0,0 } };
		findEulerianCycle1(graph5);
	}

	private static void print(int[] degree) {
		for ( int k = 0 ; k < degree.length ; ++k ) {
			System.out.print(degree[k]+" -> ");
		}
		System.out.println();
	}
	
	

}

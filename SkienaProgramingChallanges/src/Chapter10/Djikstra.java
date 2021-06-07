package Chapter10;

public class Djikstra {
	
	public static void main ( String args[] ) {
		int graph[][] = {   { 1,4,2,1,0,0 },
							{ 4,1,2,3,3,0 },
							{ 2,2,1,0,0,0 },
							{ 1,3,0,1,0,0 },
							{ 0,3,0,0,1,0 },
							{ 0,0,0,0,0,1 } };
		for ( int m = 0 ; m < graph.length ; ++m ) {
			
			//print(calculateDistanceSingleSource(graph,m));
			int sources[] = new int[1];
			sources[0] = m;
			//print(calculateDistanceMultipleSources(graph, sources));
		}
		
		int sources[] = new int[2];
		sources[0] = 0;
		sources[1] = 4;
		print(calculateDistanceMultipleSources(graph, sources));
	}
	
	public static void print ( int []array ) {
		for ( int k = 0 ; k < array.length ; ++k ) {
			System.out.print(array[k]+" ");
		}
		System.out.println();
	}
	
	public static int[] calculateDistanceMultipleSources ( int graph[][] , int sources[] ) {
		if ( sources.length == 0 ) {
			System.out.println("I HAVE NO SOURCES FROM WITCH TO CALCULATE MY DISTANCE");
			return new int[0];
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
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( distance[k] >= Integer.MAX_VALUE/2 ) {
				distance[k] = -1;
			}
		}
		return distance;
	}
	
	public static int[] calculateDistanceSingleSource ( int graph[][] , int source ) {
		int distance[] = new int[graph.length];
		boolean is_in_set[] = new boolean[graph.length];
		distance[source] = 0;
		is_in_set[source] = true;
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( graph[source][k] != 0 ) {
				distance[k] = graph[source][k];
			}
			else {
				distance[k] = Integer.MAX_VALUE/2;
			}
		}
		for ( int k = 1 ; k < graph.length ; ++k ) {
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
		for ( int k = 0 ; k < graph.length ; ++k ) {
			if ( distance[k] >= Integer.MAX_VALUE/2 ) {
				distance[k] = -1;
			}
		}
		return distance;
	}

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

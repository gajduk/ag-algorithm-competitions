import java.util.ArrayList;


public class GraphAdjacencyMatrix extends Graph {
	
	int[][] graph;
	public static final int MAX_VERTICES = 1000;
	public static final int NON_EXISTENT = 0;
	
	public GraphAdjacencyMatrix( String[] vertices , String[] edges ) throws Exception {
		super(vertices);
		if ( vertices.length > MAX_VERTICES )
			throw new Exception("Too many vertices for an adjacancy matrix represantation, try a different one.");
		graph = new int[vertices.length][vertices.length];
		try {
			populateGraph(edges);
		} catch (Exception e) {
			throw new Exception("The graph was not created successfully because:\n"+e.getMessage());
		}
	}
	
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
				int v1 = getIndex(s_v1);
				int v2 = getIndex(s_v2);
				graph[v1][v2] = weight;
			} catch (Exception e) {
				throw new Exception("The edge "+edges[m]+" could not be added because:\n"+e.getMessage());
			}
			
		}
	}

	public int getIndex ( String vertex ) throws Exception {
		for ( int k = 0 ; k < vertices.length ; ++k ) {
			if ( vertex.equals(vertices[k]) ) {
				return k;
			}
		}
		throw new Exception("The vertex "+vertex+" which you are looking for does not exist or does not belong to this graph.");
	}

	@Override
	public void addEdge(String edge) throws Exception {
		String s_v1 = edge.substring(0,edge.indexOf(' '));
		String s_v2 = edge.substring(edge.indexOf(' ')+1,edge.lastIndexOf(' '));
		int weight = new Integer(edge.substring(edge.lastIndexOf(' ')+1));
		try {
			int v1 = getIndex(s_v1);
			int v2 = getIndex(s_v2);
			graph[v1][v2] = weight;
		} catch (Exception e) {
			throw new Exception("The edge "+edge+" could not be added because:/n"+e.getMessage());
		}
	}
	
	private void print ( int matrix[][] ) {
		for ( int k = 0 ; k < matrix.length ; ++k ) {
			for ( int m = 0 ; m < matrix.length ; ++m ) {
				System.out.print(matrix[k][m]+" ");
			}
			System.out.println();
		}
	}

	public void printAdjacancyMatrix () {
		System.out.println();
		print(graph);
		System.out.println();
	}
	
	@Override
	public void printEdges() {
		System.out.println();
		for ( int k = 0 ; k < graph.length ; ++k ) {
			for ( int m = 0 ; m < graph.length ; ++m ) {
				if ( graph[k][m] != NON_EXISTENT ) {
					System.out.println(vertices[k]+" "+vertices[m]+" "+graph[k][m]);
				}
			}
		}
		System.out.println();
	}

	@Override
	public String[] breadthFirstSearch(String start, String end) {
		
		return null;
	}

	@Override
	public String[] depthFirstSearch(String start, String end) {
		
		return null;
	}

	@Override
	public String[] breadthFirstSearch(String start , boolean is_visited[] ) throws Exception {
		if ( is_visited == null ) {
			is_visited = new boolean[graph.length];
		}
		ArrayList<Integer> queue = new ArrayList<Integer>();
		ArrayList<String> result_list = new ArrayList<String>();
		is_visited[getIndex(start)] = true;
		queue.add(getIndex(start));
		result_list.add(start);
		while ( ! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			for ( int i = 0 ; i < graph.length ; ++i ) {
				if ( graph[current_vertex][i] != NON_EXISTENT && ! is_visited[i]  ) {
					is_visited[i] = true;
					queue.add(i);
					result_list.add(vertices[i]);
				}
			}
		}
		String result[] = new String[result_list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = result_list.get(i);
		}
		return result;
	}

	@Override
	public String[] depthFirstSearch(String start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] breadthFirstSearch() throws Exception {
		boolean is_visited[] = new boolean[graph.length];
		String result[] = new String[graph.length];
		int walker = 0;
		for ( int i = 0 ; i < graph.length ; ++i ) {
			if ( !is_visited[i] ) {
				String temp[] = breadthFirstSearch(vertices[i],is_visited);
				for ( int k = 0 ; k < temp.length ; ++k ) {
					result[walker++] = temp[k];
				}
			}
		}
		return result;
	}

	@Override
	public String[] depthFirstSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getMinimalDistanceSingleSourceDjikstra(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getMinimalDistanceDjikstra(String[] sources) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getMinimumSpanningTreeKruskal() {
		
		return null;
	}

	@Override
	public String[] getMinimumSpanningTreePrim(String start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getMinimalDistanceAllPairsFloyd(String edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] articulationVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isArticulationVertex(String edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] brdiges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBridge(String edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAcyclic() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ArrayList<String> prorgesiveDeepeningDepthFirstSearch ( String start , int maximum_allowed_depth ) throws Exception {
		boolean is_visited[] = new boolean[graph.length];
		ArrayList<String> result = new ArrayList<String>();
		prorgesiveDeepeningDepthFirstSearchRecursive(start, maximum_allowed_depth , 0 , is_visited , result );
		return result;
	}
	
	public void prorgesiveDeepeningDepthFirstSearchRecursive ( String start , int maximum_allowed_depth , int current_depth , boolean is_visited[], ArrayList<String> result  ) throws Exception {
		int current_vertex = getIndex(start);
		is_visited[getIndex(start)] = true;
		if ( current_depth >= maximum_allowed_depth ) {
			return;
		}
		for ( int i = 0 ; i < graph.length ; ++i ) {
//			if ( GraphAdjacencyMatrix[)
		}
	}

	@Override
	public String[] breadthFirstSearch(String start) throws Exception {
		return null;
	}
	
	

}

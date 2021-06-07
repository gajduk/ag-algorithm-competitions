
public abstract class Graph {
	
	public String vertices[];
	
	public Graph( String vertices[] ) {
		this.vertices = vertices;
	}
	
	public abstract void addEdge ( String edge ) throws Exception;
	
	public abstract void printEdges ( );
	
	public abstract String[] breadthFirstSearch ( String start , String end );

	public abstract String[] depthFirstSearch ( String start , String end );

	public abstract String[] breadthFirstSearch ( String start ) throws Exception;

	public abstract String[] depthFirstSearch ( String start  );

	public abstract String[] breadthFirstSearch ( ) throws Exception;

	public abstract String[] depthFirstSearch (  );
	
	public abstract int[] getMinimalDistanceSingleSourceDjikstra ( String source );
	
	public abstract int[] getMinimalDistanceDjikstra ( String[] sources );
	
	public abstract String[] getMinimumSpanningTreeKruskal ( );
	
	public abstract String[] getMinimumSpanningTreePrim ( String start );
	
	public abstract int[][] getMinimalDistanceAllPairsFloyd ( String edge );
	
	public abstract String[] articulationVertices ( );
	
	public abstract boolean isArticulationVertex ( String edge );
	
	public abstract String[] brdiges ( );
	
	public abstract boolean isBridge ( String edge );
	
	public abstract boolean isConnected();
	
	public abstract boolean isAcyclic();

	public String[] breadthFirstSearch(String start, boolean[] is_visited) 	throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}

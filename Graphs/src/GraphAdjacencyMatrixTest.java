



public class GraphAdjacencyMatrixTest {
	
	public static void main ( String args[] ) {
		GraphAdjacencyMatrix g = null;
		String vertices[] = { "a" , "b" , "c" , "d" , "e" };
		String edges[] = { "a b 5" , "b c 6" , "c a 2" , "d a 2" , "e b 3" };
		try {
			g = new GraphAdjacencyMatrix(vertices,edges);
			g.printEdges();
			g.printAdjacancyMatrix();
			g.addEdge("a c 3");
			g.printEdges();
			g.printAdjacancyMatrix();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}

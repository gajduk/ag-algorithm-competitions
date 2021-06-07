package Chapter10;

public class FloydWarshall {
	
	public static void main ( String args [] ) {
		int graph[][] = {   { 1,1,2,1,0,0 },
							{ 1,0,2,1,3,0 },
							{ 2,2,0,0,0,0 },
							{ 1,1,0,0,0,0 },
							{ 0,3,0,0,0,0 },
							{ 0,0,0,0,0,0 } };
		print(calculateAllPairsDistances(graph));
	}

	public static void print ( int matrix[][] ) {
		for ( int k = 0 ; k < matrix.length ; ++k ) {
			for ( int m = 0 ; m < matrix[0].length ; ++m ) {
				System.out.print(matrix[k][m]+" ");
			}
			System.out.println();
		}
	}
	
	public static int[][] calculateAllPairsDistances ( int graph[][] ) {
		int graph1[][] = new int[graph.length][graph.length];
		for ( int k = 0 ; k < graph.length ; ++k ) {
			for ( int m = 0  ; m < graph.length ; ++m ) {
				graph1[k][m] = graph[k][m];
			}
		}
		int i,j; 
		int k; 
		int through_k; 
		for ( k = 0 ; k < graph.length ; k++ ) {
			for ( i = 0 ; i < graph.length ; i++ ) {
				for ( j = 0 ; j < graph.length ; j++ ) {
					if ( graph1[i][k] !=  0 && graph1[k][j] != 0 ) {
						through_k = graph1[i][k]+graph1[k][j];
						if ( graph1[i][j] != 0 ) {
							if (through_k < graph1[i][j]) {
								graph1[i][j] = through_k;
							}
						}
						else {
							graph1[i][j] = through_k;
						}
					}
				}
			}
		}
		return graph1;
	}
}

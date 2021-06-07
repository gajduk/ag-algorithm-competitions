import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;


public class Frog {
	
	 public static void main(String[] args) {
		 Frog f = new Frog();
		 int leaves[] = {10,8,18,14,16,24,34};
		 int jumps[] = {10,2};
		 int distance = 44;
		 System.out.println(f.minJumps(leaves, jumps, distance));
	 }
	
	 public int minJumps(int[] leaves, int[] jumps, int distance) {
		 int graph[][] = new int[leaves.length+2][leaves.length+2];
		 int end = leaves.length+1;
		 for ( int k = 0 ; k < jumps.length ; ++k ) {
			 if ( distance <= jumps[k] ) return 1;
		 }
		 for ( int i = 0 ; i < leaves.length ; ++i ) {
			 for ( int k = 0 ; k < jumps.length ; ++k ) {
				 if ( leaves[i] == jumps[k] ) graph[0][i+1] = 1;
			 }
			 for ( int k = 0 ; k < jumps.length ; ++k ) {
				 if ( leaves[i]+jumps[k]>=distance ) graph[end][i+1] = graph[i+1][end] = 1;
			 }
			 for ( int j = i+1 ; j < leaves.length ; ++j ) {
				 for ( int k = 0 ; k < jumps.length ; ++k ) {
					 if ( leaves[i]+jumps[k] == leaves[j] || leaves[i]-jumps[k] == leaves[j] ) {
						 graph[i+1][j+1] =  graph[j+1][i+1] = 1;
					 }
				 }
			 }
		 }
//		 print(graph);
		 ArrayList<Integer> queue = new ArrayList<Integer>();
		 boolean visited[] = new boolean[graph.length];
		 queue.add(0);
		 visited[0] = true;
		 int front = 1;
		 int rear = 0;
		 int level = 0;
		 int border = 1;
		 while ( ! queue.isEmpty() ) {
			 int current = queue.remove(0); ++rear;
			 if ( current == end ) return level;
			 for ( int i = 0 ; i < graph.length ; ++i ) {
				 if ( ! visited[i] && graph[current][i] > 0 ) {
					 visited[i] = true;
					 queue.add(i); ++front;
				 }
			 }
			 if ( border == rear ) {
				 border = front;
				 ++level;
			 }
		 }
		 return 0;
	 }

	private void print(int[][] graph) {
		for ( int i = 0 ; i < graph.length ; ++i ) {
			for ( int k = 0 ; k < graph.length ; ++k ) {
				System.out.print(graph[i][k]);
			}
			System.out.println();
		}
	}

	 
	 
}

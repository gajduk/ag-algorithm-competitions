import java.util.ArrayList;

class E {
	int len;
	int state;
	public E(int len, int state) {
		super();
		this.len = len;
		this.state = state;
	}
	
	
}

public class GivesYouWings {
	
	  public int solve(String[] maze) {
		  int start_i = 0, start_k = 0;
		  int end_i = 0, end_k = 0;
		  ArrayList<Integer> location_i = new ArrayList<Integer>();
		  ArrayList<Integer> location_k = new ArrayList<Integer>();
		  for ( int i = 0 ; i < maze.length ; ++i ) {
			  for ( int k = 0 ; k < maze.length ; ++k ) {
				  if ( maze[i].charAt(k) == 'S' ) {
					  start_i = i;
					  start_k = k;
				  }
				  if ( maze[i].charAt(k) == 'T' ) {
					  end_i = i;
					  end_k = k;
				  }
				  if ( maze[i].charAt(k) == '*' ) {
					  location_i.add(i);
					  location_k.add(k);
				  }
			  }
		  }
		  location_i.add(0,start_i);location_k.add(0,start_k);
		  location_i.add(location_i.size()-1,end_i);location_k.add(location_k.size()-1,end_k);
		  int d[][] = new int[location_i.size()][location_i.size()];
		  for ( int i = 0 ; i < d.length ; ++i ) {
			  calculateDistance(maze,location_i,location_k,i,d);
		  }
		  return minDistance(0,d.length-1,d);
	  }

	private int minDistance(int start, int end,	int[][] d) {
		int dist[][] = new int[d.length][257];
		dist[start][0] = 0;
		ArrayList<E> queue = new ArrayList<E>();
		queue.add(new E(start,0));
		while ( ! queue.isEmpty() ) {
		
		}
		return dist[d.length][0];
	}

	private void calculateDistance(String[] maze, ArrayList<Integer> location_i, ArrayList<Integer> location_k, int i,int d[][]) {
		ArrayList<Integer> queue_i = new ArrayList<Integer>();
		ArrayList<Integer> queue_k = new ArrayList<Integer>();
		
	}

	

}

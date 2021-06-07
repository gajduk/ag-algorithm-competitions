import java.sql.Time;


public class TimeMaze {
	
	 public static void main(String[] args) {
		TimeMaze t = new TimeMaze();
		String maze[] = {"S111111",
						 "9#####1",
						 "89E1111"};
		System.out.println(t.minTime(maze, 2, 2));
	}
	
	  public int minTime( String[] maze, int htime, int vtime ) {
		  int dist[][] = new int[maze.length][maze[0].length()];
		  boolean u[][] = new boolean[maze.length][maze[0].length()];
		  int start_i = -1, start_k = -1;
		  for ( int i = 0 ; i < maze.length ; ++i ) {
			  for ( int k = 0 ; k < maze[0].length() ; ++k ) {
				  dist[i][k] = -1;
				  if ( maze[i].charAt(k) == 'S' ) {
					  dist[i][k] = 0;
					  u[i][k] = true;
					  start_i = i; start_k = k;
				  }
			  }
		  }
		  //System.out.println(start_i+" "+start_k);
		  int di[] = { -1 , 1 , 0 , 0 };
		  int dk[] = { 0 , 0 , -1 , 1 };
		  int d[] = { vtime , vtime  , htime , htime };
		  updateDist(u,maze,di,dk,d,dist,start_i,start_k);
		  
		  boolean flag = true;
		  while ( flag ) {
			  print(dist);
			  System.out.println();
			  flag = false;
			  int min = Integer.MAX_VALUE; int min_i = -1; int min_k = -1;
			  for ( int i = 0 ; i < maze.length ; ++i ) {
				  for ( int k = 0 ; k < maze[0].length() ; ++k ) {
					  if ( ! u[i][k] && dist[i][k] != -1 ) {
						  if ( dist[i][k] < min ) {
							  min = dist[i][k]; 
							  min_i = i; min_k = k;
						  }
					  }
				  }
			  }
			  if ( min_i != -1 ) {
				  flag = true;
				  updateDist(u,maze, di, dk, d, dist, min_i, min_k);
				  u[min_i][min_k] = true;
				  if ( maze[min_i].charAt(min_k) == 'E' ) {
					  return dist[min_i][min_k];
				  }
			  }
		  }
		  return -1;
	  }

	private void updateDist( boolean u[][] ,String []maze , int[] di, int[] dk, int[] d, int[][] dist,
			int start_i, int start_k) {
			for ( int w = 0 ; w < di.length ; ++w ) {
				if ( check(maze,start_i+di[w],start_k+dk[w]) ) {
					int temp = dist[start_i+di[w]][start_k+dk[w]];
					dist[start_i+di[w]][start_k+dk[w]] = dist[start_i][start_k]+d[w];
					if ( maze[start_i].charAt(start_k) != 'S' 
					 &&  maze[start_i].charAt(start_k) != 'E'  
					 &&  maze[start_i].charAt(start_k) != '#' ) {
						dist[start_i+di[w]][start_k+dk[w]] += new Integer(maze[start_i].substring(start_k,start_k+1));
					}
					if ( temp != -1 && dist[start_i+di[w]][start_k+dk[w]] > temp ) dist[start_i+di[w]][start_k+dk[w]] = temp;
				}
			}
	}

	private void print(int[][] dist) {
		for ( int i = 0 ; i < dist.length ; ++i ) {
			  for ( int k = 0 ; k < dist[0].length ; ++k ) {
				  System.out.print(dist[i][k]+" ");
			  }
			  System.out.println();
		}
	}

	private boolean check(String[] maze, int i, int j) {
		return i >= 0 && j >= 0 && i < maze.length && j < maze[0].length() && maze[i].charAt(j) != '#';
	}

}

package mendo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * mendo.mk
 * @author Andrej Gajduk
 *
 */

class Coords {
	int i;
	int j;
	
	public Coords( int m , int n ) {
		i = m; j = n;
	}
	
}

public class Prison {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		String maze[] = new String[n];
		for ( int i = 0 ; i < n ; ++i ) {
			maze[i] = in.next();
		}
//		System.out.println(Arrays.toString(maze));
		Coords start = null , end = null;

		ArrayList<Coords> queue = new ArrayList<Coords>();
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int j = 0 ; j < m ; ++j ) {
				if ( maze[i].charAt(j) == 'G' ) {
					queue.add(new Coords(i,j));
				}
				if ( maze[i].charAt(j) == 'R' ) {
					start = new Coords(i,j);
				}
				if ( maze[i].charAt(j) == 'Z' ) {
					end = new Coords(i,j);
				}
			}
		}
		
		int distance[][] = new int[n][m];
		for ( int i = 0 ; i < n ; ++i ) { for ( int j = 0 ; j < m ; ++j ) {
				distance[i][j] = -1;
		} }
		int level = 1;
		int front = queue.size();
		int rear = 0;
		int border = front;
		for ( Coords c : queue ) {
			distance[c.i][c.j]= 0; 
		}
		int dx[] = { -1 , 1 , 0 , 0 };
		int dy[] = { 0 , 0 , -1 , 1 };
		while ( ! queue.isEmpty() ) {
			Coords c = queue.remove(0);
			rear++;
			for ( int w = 0 ; w < dx.length ; ++w ) {
				int x = dx[w]+c.i;
				int y = dy[w]+c.j;
				if ( check(x,y,distance) && distance[x][y] == -1 && maze[x].charAt(y) != '*' ) {
					queue.add(new Coords(x,y));
					++front;
					distance[x][y] = level;
				}
			}
//			print(distance);
			if ( rear == border ) {
				border = front;
				++level;
			}	
		}
//		print(distance);
//		System.out.println(distance[start.i][start.j]);
		int hi = Math.min(level,distance[start.i][start.j]);
		int lo = 0;
		int mid = 0;
		while ( lo < hi ) {
//			System.out.println(lo+" "+hi);  
			mid = lo + (hi-lo)/2;
			queue.clear();
			queue.add(start);
			boolean visited[][] = new boolean[n][m];
			visited[start.i][start.j] = true;
			boolean flag = false;
			while ( ! queue.isEmpty() ) {
				Coords current = queue.remove(0);
				if ( current.i == end.i && current.j == end.j ) {
					flag = true;
					break;
				}
				for ( int w = 0 ; w < dx.length ; ++w ) {
					int x = dx[w]+current.i;
					int y = dy[w]+current.j;
					if ( check(x,y,distance) && distance[x][y] > mid && maze[x].charAt(y) != '*' && ! visited[x][y] ) {
						queue.add(new Coords(x,y));
						visited[x][y] = true;
					}
				}
			}
			if ( flag ) {
				lo = mid+1;
			}
			else {         
				hi = mid;
			}
		}
		System.out.println(lo);
//		print(distance);
		
	}

	private static boolean check(int x, int y, int[][] distance) {
		return x >= 0 && y >= 0 && x < distance.length && y < distance[0].length;
	}

	public static void print(int[][] distance) {
		for ( int i = 0 ; i < distance.length ; ++i ) {
			for ( int j = 0 ; j < distance[0].length ; ++j ) {
				System.out.print(distance[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}

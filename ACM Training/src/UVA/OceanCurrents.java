package UVA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class P {
	
	static int dr[] = { -1 , -1 , 0 , 1 , 1 , 1 , 0 , -1 };
	static int dc[] = { 0 , 1 , 1 , 1 , 0 , -1 , -1 , -1 };
	
	public int ir;
	public int ic;
	
	public P(int ir, int ic) {
		super();
		this.ir = ir;
		this.ic = ic;
	}
	
	public int get(int ocean[][] ) {
		return ocean[ir][ic];
	}
	
	public P next( int dirr ) {
		return new P(ir+dr[dirr],ic+dc[dirr]);
	}
		
	@Override
	public String toString() {
		return "("+ir+","+ic+")";
	}
}

public class OceanCurrents {
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int r = in.nextInt();
		int c = in.nextInt();
		int ocean[][] = new int[r][c];
		int dr[] = { -1 , -1 , 0 , 1 , 1 , 1 , 0 , -1 };
		int dc[] = { 0 , 1 , 1 , 1 , 0 , -1 , -1 , -1 };
		for ( int i = 0 ; i < r ; ++i ) {
			String l = in.next();
			for ( int k = 0 ; k < c ; ++k ) {
				ocean[i][k] = Integer.parseInt(l.substring(k,k+1));
			}
		}
		int n = in.nextInt();
		for ( int q = 0 ; q < n ; ++q ) {
			int sr = in.nextInt()-1;
			int sc = in.nextInt()-1; 
			int er = in.nextInt()-1;
			int ec = in.nextInt()-1;
			LinkedList<P> queue = new LinkedList<P>();
			queue.add(new P(sr,sc));
			boolean visited[][] = new boolean[r][c];
			visited[sr][sc] = true;
			int level = 0;
			int front = 1;
			int rear = 0;
			P walker = new P(sr,sc);
			front += traverse(walker,ocean,visited,queue);
			int border = front;
			while ( ! queue.isEmpty() ) {
				//print(visited);
				++rear;
				P curr = queue.removeFirst();
				if ( curr.ir == er && curr.ic == ec ) { System.out.println(level); break;}
				for ( int w = 0 ; w < dr.length ; ++w ) {
					int nr = curr.ir+dr[w];int nc = curr.ic+dc[w];
					if ( check(nr,nc,ocean) ) {
						if ( !visited[nr][nc] ) {
							visited[nr][nc] = true;
							queue.add(new P(nr,nc));
							walker = new P(nr,nc);
							++front;
							front += traverse(walker,ocean,visited,queue);
						}
					}
				}
				if ( border == rear ) {
					border = front; ++level;
				}
			}
		}
		
	}

	private static int traverse(P walker, int[][] ocean, boolean[][] visited,
			LinkedList<P> queue) {
		int front = 0;
		while ( true ) {
			P next = walker.next(walker.get(ocean));
			if ( check(next,ocean) ) {
				if ( ! visited[next.ir][next.ic]  ) {
					visited[next.ir][next.ic] = true;
					queue.add(next);
					++front;
				}
				else {
					break;
				}
			}
			else {
				break;
			}
			walker = next;
		}
		return front;
	}

	private static void print(boolean[][] visited) {
		for ( int i = 0 ; i < visited.length ; ++i ) {
			for ( int k = 0 ; k < visited[0].length ; ++k ) {
				System.out.print(visited[i][k]?"1":"0");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean check(int nr, int nc, int[][] ocean) {
		return nr >= 0 && nc >= 0 && nr < ocean.length && nc < ocean[0].length;
	}
	
	private static boolean check( P p, int[][] ocean) {
		return check(p.ir,p.ic,ocean);
	}
	

}

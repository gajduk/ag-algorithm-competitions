package mendo;

import java.util.ArrayList;
import java.util.Scanner;

class Coord {
	public int i;
	public int k;
	int steps;
	
	public Coord(int i, int k , int steps ) {
		super();
		this.i = i;
		this.k = k;
		this.steps = steps;
	}
	
	@Override
	public String toString() {
		return "("+i+","+k+")"+">"+steps;
	}
	
	
}




public class Lavirint {
	static final int WALL = 1;
	static final int ROOM = 0;
	
	
	public static void main(String[] args) {
		int di[] = { -1,1,0,0 };
		int dk[] = { 0,0,-1,1 };
		Scanner in = new Scanner(System.in);
		int r = in.nextInt();
		int c = in.nextInt();
		int map[][] = new int[r][c];
		boolean visited[][][] = new boolean[r][c][3];
		Coord start = null,end = null;
		in.nextLine();
		for ( int i = 0 ; i < r ; ++i ) {
			String line = in.nextLine();
			for ( int k = 0 ; k < c ; ++k ) {
				if ( line.charAt(k) == '.' ) {
					map[i][k] = ROOM;
				}
				if ( line.charAt(k) == '#' ) {
					map[i][k] = WALL;
				}
				if ( line.charAt(k) == 'P' ) {
					start = new Coord(i, k, 0);
					map[i][k] = ROOM;
				}
				if ( line.charAt(k) == 'K' ) {
					end = new Coord(i, k, -1);
					map[i][k] = ROOM;
				}
			}
		}
		ArrayList<Coord> queue = new ArrayList<Coord>(300);
		queue.add(start);
		int front = 1;
		int rear = 0;
		int level = 0;
		int border = 1;
		while ( ! queue.isEmpty() ) {
			Coord curr = queue.remove(0);
			++rear;
			if ( curr.i == end.i && curr.k == end.k ) {
				System.out.println(level);return;
			}
			for ( int w = 0 ; w < di.length ; ++w ) {
				boolean flag = true;
				for ( int steps = 1 ; steps < curr.steps+2 ; ++ steps ) {
					int ni = curr.i+di[w]*steps;int nk = curr.k+dk[w]*steps;
					if (! check(ni,nk,map) ) {
						flag = false;
					}
				}
				if ( flag ) {
					int ni = curr.i+di[w]*(curr.steps+1);int nk = curr.k+dk[w]*(curr.steps+1);int nsteps = (curr.steps+1)%3;
					if ( ! visited[ni][nk][nsteps] ) {
						++front;visited[ni][nk][nsteps] = true;
						queue.add(new Coord(ni,nk,nsteps));
					}
				}
			}
			if ( border == rear ) {
				++level; border = front;
			}
		}
		System.out.println("Nema resenie");
	}


	private static boolean check(int ni, int nk, int[][] map) {
		return ni >= 0 && ni < map.length && nk >= 0 && nk < map[0].length && map[ni][nk] != WALL;
	}

}

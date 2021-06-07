import java.util.LinkedList;
import java.util.Queue;


public class TrampolineMaze {
	
	public static void main(String[] args) {
		TrampolineMaze tm = new TrampolineMaze();
		System.out.println(tm.solve(new String[]{"#####.T#.##.T#.#.T#....###.E.#","S..T#.##....##...####TT#..T#.#"}));
	}
	
	public int solve(String[] maze) {
		int movesx[] = {-1,1,0,0};
		int movesy[] = {0,0,-1,1};
		boolean visited[][][] = new boolean[4][maze.length][maze[0].length()];
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		Queue<Integer> qm = new LinkedList<Integer>();
		int sx = -1;
		int sy = -1;
		for ( int x = 0 ; x < maze.length ; x++ ) {
			for ( int y = 0 ; y < maze[0].length() ; y++ ) {
				if ( maze[x].charAt(y) == 'S' ) {
					sx = x;
					sy = y;
				}
			}
		}
		qx.add(sx);
		qy.add(sy);
		qm.add(0);
		qx.add(sx);
		qy.add(sy);
		qm.add(1);
		qx.add(sx);
		qy.add(sy);
		qm.add(2);
		qx.add(sx);
		qy.add(sy);
		qm.add(3);
		int moves = 0;
		int added_total = 4;
		int added_last = 4;
		int polled = 0;
		while ( ! qx.isEmpty() ) {
			int px = qx.poll();
			int py = qy.poll();
			int pm = qm.poll();
			++polled;
			if ( maze[px].charAt(py) == 'E' ) return moves;
			for ( int i = 0 ; i < movesx.length ; i++ ) {
				int xx = px+movesx[i];
				int yy = py+movesy[i];
				if ( xx >= 0 && yy >= 0 && xx < maze.length && yy < maze[0].length()) { 
				   if ( maze[xx].charAt(yy) != '#' ) {
					   if ( ! visited[i][xx][yy] ) {
						   visited[i][xx][yy] = true;
						   qx.add(xx);
						   qy.add(yy);
						   qm.add(i);
						   added_total++;
					   }
				   }
				}	   
			}
			if ( maze[px].charAt(py) == 'T' ) {
				int xx = px+movesx[pm]*2;
				int yy = py+movesy[pm]*2;
				if ( xx >= 0 && yy >= 0 && xx < maze.length && yy < maze[0].length()) { 
					 if ( maze[xx].charAt(yy) != '#' ) {
						 if ( ! visited[pm][xx][yy] ) {
							 visited[pm][xx][yy] = true;
							   qx.add(xx);
							   qy.add(yy);
							   qm.add(pm);
							   added_total++;
						 }
					 }
				}
			}
			if ( added_last == polled ) {
				added_last = added_total;
				moves++;
			}
		}
		return -1;
	}

}

import java.util.Arrays;


class MovingWall implements Comparable<MovingWall>{
	int x;
	int y;
	int time;
	
	public MovingWall(int a, int b , int c) {
		x = a;
		y = b;
		time = c;
	}
	
	@Override
	public int compareTo(MovingWall o) {
		return time-o.time;
	}
}

public class MovingWallsLabyrinth {

	
	
	public static int smallest( String[] maze , int[] erectx , int[] erecty , int[] erecttime ) {
		MovingWall moving_walls_array[] = new MovingWall[erecttime.length];
		for ( int i = 0 ; i < erecttime.length ; ++i ) {
			moving_walls_array[i] = new MovingWall(erectx[i],erecty[i],erecttime[i]);
		}
		Arrays.sort(moving_walls_array);
		long walls[] = new long[maze.length];
		int moving_walls_index = 0;
		for ( int i = 0 ; i < maze.length ; ++i ) {
			walls[i] = (1<<63)-1;
			for ( int j = 0 ; j < maze[i].length() ; ++j ) {
				if ( maze[i].charAt(j) == '#' ) {
					walls[i] -= 1<<(maze[i].length()-1-j);
				}
			}
			walls[i] -= 1<<maze[i].length();
			
			System.out.println(Long.toBinaryString(walls[i]));
		}
		int time = 0;
		int max_time = 3000;
		if ( moving_walls_array.length > 0 ) {
			max_time += moving_walls_array[moving_walls_array.length-1].time;
		}
		long current[] = new long[maze.length];
		long next[] = new long[maze.length];
		long moving_walls[] = new long[maze.length];
		for ( int i = 0 ; i < moving_walls.length ; ++i ) {
			moving_walls[i] = (1<<63)-1;
		}
		current[0] += 1<<(maze[0].length()-1);
		while ( time <= max_time ) {
			System.out.println("The possible locations "+time);
			for ( int i = 0 ; i < current.length ; ++i ) {
				System.out.println(Long.toBinaryString(current[i]));
			}
			if ( (current[current.length-1] & 1) > 0 ) {
				return time;
			}
			while ( moving_walls_index < moving_walls_array.length && moving_walls_array[moving_walls_index].time == time ) {
				int row = moving_walls_array[moving_walls_index].x;
				int col = moving_walls_array[moving_walls_index].y;
				if ( (moving_walls[row] & (1<<(maze[0].length()-1-col))) > 0 ) {
					moving_walls[row] -= 1<<(maze[0].length()-1-col);
				}
				++moving_walls_index;
			}
			System.out.println("The moving walls are at locations "+time);
			for ( int i = 0 ; i < current.length ; ++i ) {
				System.out.println(Long.toBinaryString(moving_walls[i]));
			}
			for ( int i = 0 ; i < maze.length-1 ; ++i ) {
				next[i+1] = next[i+1] | ( current[i] & walls[i] & moving_walls[i] );
			}
			for ( int i = 1 ; i < maze.length ; ++i ) {
				next[i-1] = next[i-1] | ( current[i] & walls[i] & moving_walls[i] );
			}
			for ( int i = 0 ; i < maze.length ; ++i ) {
				next[i] = next[i] | ( ( current[i] | current[i]<<1 | current[i]>>1 ) & walls[i] & moving_walls[i] );
			}
			for ( int i = 0 ; i < current.length ; ++i ) {
				current[i] = next[i];
				next[i] = 0;
				moving_walls[i] = (1<<63)-1;
			}
			++time;
		}
		return -1;
	}

	public static void main ( String args[] ) {
		int x[] = {};
		int y[] = {};
		int time[] = {};
		String maze[] = { "  #",
						"# #",
						"#  " };
		System.out.println(smallest(maze, x, y, time));
	}
	
}

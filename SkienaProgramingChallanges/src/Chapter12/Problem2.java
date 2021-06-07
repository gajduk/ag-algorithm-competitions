package Chapter12;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

class State {
	int x;
	int y;
	int direction;
	int color;
	
	public State() {
	}
	
	public State( int x1 , int y1 , int a , int b ) {
		x = x1; direction = a;
		y = y1; color = b;
	}
	
	public State add ( State d ) {
		State result = new State(x+d.x,y+d.y,(direction+d.direction+4)%4,(color+d.color)%5);
		return result;
	}
	
	@Override
	public String toString() {
		return x+" "+y+" "+direction+" "+color;
	}
	
}

public class Problem2 {
	
	public static boolean maze[][];
	public static int best_times[][][][]; // location_x , location_y , bicycle position , color
	public static final int GREEN = 0;
	public static final int BLACK = 1;
	public static final int RED = 2;
	public static final int BLUE = 3;
	public static final int WHITE = 4;
	public static final int NORTH = 0;
	public static final int SOUTH = 2;
	public static final int EAST = 1;
	public static final int WEST = 3;
	public static int start_x;
	public static int start_y;
	public static int target_x;
	public static int target_y;
	// turning left: 
	//              direction = (direction-1+4) % 4
	// turning right: 
	//               direction = (direction+1) % 4
	// moving:
	//        color = (color+1) % 5  
	
	
	public static int getBestTime ( State a ) {
		return best_times[a.x][a.y][a.direction][a.color];
	}
	
	private static void populateMaze(String[] maze1) {
		maze = new boolean[maze1.length][maze1[0].length()];
		for ( int i = 0 ; i < maze1.length ; ++i ) {
			for ( int k = 0 ; k < maze[0].length ; ++k ) {
				if ( maze1[i].charAt(k) == 'S' ) {
					start_x = i;
					start_y = k;
				}
				if ( maze1[i].charAt(k) == 'T' ) {
					target_x = i;
					target_y = k;
				}
				maze[i][k] = ! (maze1[i].charAt(k) == '#');
			}
		}
//		print(maze);
	}

	public static void explore ( ) {
		best_times = new int[maze.length][maze[0].length][4][5];
		State ds[] = {  new State(-1, 0, 0, 1),
						new State( 0, 1, 0, 1),
						new State( 1, 0, 0, 1),
						new State( 0,-1, 0, 1),
						new State( 0, 0,-1, 0),
						new State( 0, 0, 1, 0) };
		for ( int i1 = 0 ; i1 < best_times.length ; ++i1 ) {
			for ( int i2 = 0 ; i2 < best_times[0].length ; ++i2 ) {
				for ( int i3 = 0 ; i3 < best_times[0][0].length ; ++i3 ) {
					for ( int i4 = 0 ; i4 < best_times[0][0][0].length ; ++i4 ) {
						best_times[i1][i2][i3][i4] = Integer.MAX_VALUE/3;
					}	
				}	
			}
		}
		State final_state = new State(target_x,target_y,-1,GREEN);// -1 means it doesn't matter
		best_times[start_x][start_y][NORTH][GREEN] = 0;
		LinkedList<State> queue = new LinkedList<State>();
		State current = new State(start_x,start_y,NORTH,GREEN);
		queue.add(current);
//		System.out.println("Start: "+current+"  Finish: "+final_state);
		while ( ! queue.isEmpty() ) {
			current = queue.remove(0);
//			System.out.println(current);
			if ( areEqual(current,final_state) )
				break;
			for ( int i = 4; i < ds.length ; ++i ) {
				State next_state = current.add(ds[i]);
				if ( check(next_state.x, next_state.y) ) {
					int new_best_time = getBestTime(current)+1;
					int old_best_time = getBestTime(next_state);
					if ( new_best_time < old_best_time ) {
						setBestTime(next_state,new_best_time);
						queue.add(next_state);
					}
				}
			}
			State next_state = current.add(ds[current.direction]);
			if ( check(next_state.x, next_state.y) ) {
				int new_best_time = getBestTime(current)+1;
				int old_best_time = getBestTime(next_state);
				if ( new_best_time < old_best_time ) {
					setBestTime(next_state,new_best_time);
					queue.add(next_state);
				}
			}
		}
		System.out.println(areEqual(current,final_state) ? "minimum time = "+getBestTime(current)+" sec": "destination not reachable");
	}
	
	private static boolean areEqual(State a, State b) {
		return a.x == b.x && a.y == b.y && a.color == b.color;
	}

	public static void setBestTime(State a, int new_best_time ) {
		 best_times[a.x][a.y][a.direction][a.color] = new_best_time;
	}

	public static boolean check ( int x , int y ) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[x].length && maze[x][y];
	}
	
	public static void main ( String args[] ) {
//		test_exploration();
//		test_file();
		test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int test_case_id = 1;
    	while ( true ) {
    		StringTokenizer dimension = new StringTokenizer(in.nextLine());
    		int m = new Integer(dimension.nextToken());
    		int n = new Integer(dimension.nextToken());
    		if ( m == 0 && n == 0 )
    			break;
    		if ( test_case_id != 1 ) {
    			System.out.println();
    		}
    		String maze1[] = new String[m];
    		for ( int i = 0 ; i < m ; ++i ) {
    			maze1[i] = in.nextLine();
    		}
    		populateMaze(maze1);
    		System.out.println("Case #"+test_case_id);
    		explore();
    		++test_case_id;
    	}
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem2.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int test_case_id = 1;
    	while ( true ) {
    		StringTokenizer dimension = new StringTokenizer(in.nextLine());
    		int m = new Integer(dimension.nextToken());
    		int n = new Integer(dimension.nextToken());
    		if ( m == 0 && n == 0 )
    			break;
    		if ( test_case_id != 1 ) {
    			System.out.println();
    		}
    		String maze1[] = new String[m];
    		for ( int i = 0 ; i < m ; ++i ) {
    			maze1[i] = in.nextLine();
    		}
    		populateMaze(maze1);
    		System.out.println("Case #"+test_case_id);
    		explore();
    		++test_case_id;
    	}
	}
    

	private static void test_exploration() {
		String maze1[] = {  "#S.......#",
							"#..#.##.##",
							"#.##.##.##",
							".#....##.#",
							"##.##..#.#",
							"#..#.##...",
							"#......##.",
							"..##.##...",
							"#.###...#.",
							"#.....###T" };
		populateMaze(maze1);
		explore();
	}

	private static void print(boolean[][] matrix) {
		for ( int i = 0 ; i < matrix.length; ++i ) {
			for ( int k = 0 ; k < matrix[0].length ; ++k ) {
				System.out.print(matrix[i][k]?"1":"0");
			}
			System.out.println();
		}
	}
	
}

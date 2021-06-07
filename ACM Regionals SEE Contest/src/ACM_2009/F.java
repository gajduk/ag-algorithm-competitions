package ACM_2009;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class F {
	
	public static int total_path_length = 0;
	public static int vertical_segments = 0;
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}
	
	public static void test_file() {
		FileInputStream input_file = null;
		try {
			input_file = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/Desktop/ACM Regionals Problem Sets SouthEast Europe/input/i.in");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(input_file);
		int num_test_cases = new Integer(in.nextInt());
		for ( int i = 0 ; i < num_test_cases ; ++i ) {
			double path_length = in.nextFloat();
			int m = in.nextInt();
			in.nextInt();
			String maze[] = new String[m];
			for ( int k = 0 ; k < m ; ++k ) {
				maze[k] = in.nextLine();
			}
			findBestPath(maze);
			System.out.println((path_length-total_path_length+vertical_segments)/vertical_segments);
		}
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextInt());
		for ( int i = 0 ; i < num_test_cases ; ++i ) {
			double path_length = in.nextFloat();
			int m = in.nextInt();
			in.nextLine();
			String maze[] = new String[m];
			for ( int k = 0 ; k < m ; ++k ) {
				maze[k] = in.nextLine();
			}
			findBestPath(maze);
			System.out.println((path_length-total_path_length+vertical_segments)/vertical_segments);
		}
	}
	
	public static void findBestPath( String maze[] ) {
		int start_x = 0;
		int start_y = 0;
		int end_x = 0;
		int end_y = 0;
		for ( int i = 0 ; i < maze.length ; ++i ) {
			for ( int k = 0 ; k < maze[0].length() ; ++k ) {
				if ( maze[i].charAt(k) == 'S' ) {
					start_x = i;
					start_y = k;
				}
				if ( maze[i].charAt(k) == 'E' ) {
					start_x = i;
					start_y = k;
				}
			}
		}
		ArrayList<Integer> queue_x = new ArrayList<Integer>();
		ArrayList<Integer> queue_y = new ArrayList<Integer>();
		int dx[] = { 0,0,-1,1 };
		int dy[] = { -1,1,0,0 };
		boolean is_visited[][] = new boolean[maze.length][maze[0].length()];
		int prev_x[][] = new int[maze.length][maze[0].length()];
		int prev_y[][] = new int[maze.length][maze[0].length()];
		is_visited[start_x][start_y] = true;
		queue_x.add(start_x);
		queue_y.add(start_y);
		while ( ! queue_x.isEmpty() ) {
			int current_x = queue_x.remove(0);
			int current_y = queue_y.remove(0);
			if ( current_x == start_x && current_y == start_y ) {
				end_x = current_x;
				end_y = current_y;
				break;
			}
			for ( int w = 0 ; w < dx.length ; ++w ) {
				if ( check(maze,current_x+dx[w],current_y+dy[w]) && ! is_visited[current_x+dx[w]][current_y+dy[w]]  ) {
					is_visited[current_x+dx[w]][current_y+dy[w]] = true;
					queue_x.add(current_x+dx[w]);
					queue_y.add(current_x+dy[w]);
					prev_x[current_x+dx[w]][current_y+dy[w]] = current_x;
					prev_y[current_x+dx[w]][current_y+dy[w]] = current_y;
				}
			}
		}
		int current_x = end_x;
		int current_y = end_y;
		while ( current_x != start_x || current_x != start_y ) {
			if ( prev_y[current_x][current_y] != current_y ) {
				++vertical_segments; 
			}
			++total_path_length;
			int temp_x = current_x;
			current_x = prev_x[current_x][current_y];
			current_y = prev_y[temp_x][current_y];
		}
	}

	private static boolean check(String[] maze, int i, int j) {
		return i >= 0 && j >= 0 && i < maze.length && j < maze[0].length() && maze[i].charAt(j) != '#';
	}
	
}

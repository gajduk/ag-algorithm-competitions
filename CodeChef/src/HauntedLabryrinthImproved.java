import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class HauntedLabryrinthImproved {
	
	public static int dx[] = { -1 , 1 , 0 , 0 , 0 };
	public static int dy[] = { 0 , 0 , -1 , 1 , 0 };
	public static boolean current_start[][] = new boolean[31][31];
	public static boolean next_start[][] = new boolean[31][31];
	public static char labryrinth[][] = new char[31][31];
	public static int ghost_paths_x[][] = new int[31][901];
	public static int ghost_paths_y[][] = new int[31][901];
	public static int ghost_path_counter[] = new int[31];
	public static int start_x = 0;
	public static int start_y = 0;
	public static int end_x = 0;
	public static int end_y = 0;
	public static int m;
	public static int n;
	public static int num_ghosts;
	public static int time;
		
	public static void main ( String args[] ) {
		test_file();
//		test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/HauntedMaze.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		m = in.nextInt();
    		n = in.nextInt();
    		num_ghosts = in.nextInt();
    		time = in.nextInt();
    		in.nextLine();
    		for ( int k = 0 ; k < num_ghosts ; ++k ) {
    			StringTokenizer patrol = new StringTokenizer(in.nextLine());
    			int num_waypoints = new Integer(patrol.nextToken());
    			int prev_x = new Integer(patrol.nextToken());
    			int prev_y = new Integer(patrol.nextToken());
    			int first_x = prev_x;
    			int first_y = prev_y;
				ghost_path_counter[k] = 0;
    			for ( int i = 1 ; i < num_waypoints ; ++i ) {
    				int next_x = new Integer(patrol.nextToken());
    				int next_y = new Integer(patrol.nextToken());
    				determineShortestPath(k,prev_x,prev_y,next_x,next_y);
    				prev_x = next_x;
    				prev_y = next_y;
    			}
    			determineShortestPath(k,prev_x,prev_y,first_x,first_y);
    		}
    		String t;
    		for ( int i = 0 ; i < m ; ++i ) {
    			t =  in.nextLine();
    			for ( int k = 0 ; k < n ; ++k ) {
    				labryrinth[i][k] = t.charAt(k);
    				if ( labryrinth[i][k] =='@' ) {
    					start_x = i;
    					start_y = k;
    				}
    				if ( labryrinth[i][k] =='$' ) {
    					end_x = i;
    					end_y = k;
    				}
    			}
    		}
//    		printGhostPaths();
    		System.out.println(findShortestPath());
    	}
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		m = in.nextInt();
    		n = in.nextInt();
    		num_ghosts = in.nextInt();
    		time = in.nextInt();
    		in.nextLine();
    		for ( int k = 0 ; k < num_ghosts ; ++k ) {
    			StringTokenizer patrol = new StringTokenizer(in.nextLine());
    			int num_waypoints = new Integer(patrol.nextToken());
    			int prev_x = new Integer(patrol.nextToken());
    			int prev_y = new Integer(patrol.nextToken());
    			int first_x = prev_x;
    			int first_y = prev_y;
				ghost_path_counter[k] = 0;
    			for ( int i = 1 ; i < num_waypoints ; ++i ) {
    				int next_x = new Integer(patrol.nextToken());
    				int next_y = new Integer(patrol.nextToken());
    				determineShortestPath(k,prev_x,prev_y,next_x,next_y);
    				prev_x = next_x;
    				prev_y = next_y;
    			}
    			determineShortestPath(k,prev_x,prev_y,first_x,first_y);
    		}
	    	String t;
	    	for ( int i = 0 ; i < m ; ++i ) {
	    		t =  in.nextLine();
	    		for ( int k = 0 ; k < n ; ++k ) {
	    			labryrinth[i][k] = t.charAt(k);
	    			if ( labryrinth[i][k] =='@' ) {
	    				start_x = i;
	    				start_y = k;
	    			}
	    			if ( labryrinth[i][k] =='$' ) {
	    				end_x = i;
	    				end_y = k;
	    			}
	    		}
	    	}
	    	System.out.println(findShortestPath());
    	}
	}
	
	public static boolean isGhost ( int x , int y , int timer ) {
		for ( int i = 0 ; i < num_ghosts ; ++i ) {
			int index = timer % ghost_path_counter[i];
			if ( ghost_paths_x[i][index] == y && ghost_paths_y[i][index] == x ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isGhost1 ( int x , int y , int timer ) {
		for ( int i = 0 ; i < num_ghosts ; ++i ) {
			int index = timer % ghost_path_counter[i];
			int index1 = (timer+1)% ghost_path_counter[i];
			if ( ghost_paths_x[i][index] == y && ghost_paths_y[i][index] == x || ghost_paths_x[i][index1] == y && ghost_paths_y[i][index1] == x  ) {
				return true;
			}
		}
		return false;
	}
	
	private static void printGhostPaths() {
		for ( int i = 0 ; i < num_ghosts ; ++i ) {
			for ( int k = 0 ; k < ghost_path_counter[i] ; ++k ) {
				System.out.print(" ("+ghost_paths_x[i][k]+","+ghost_paths_y[i][k]+")  -");
			}
			System.out.println();
		}
	}

	public static void determineShortestPath( int i, int current_x, int current_y, int goal_x, int goal_y ) {
		while ( current_x != goal_x || current_y != goal_y ) {
			ghost_paths_x[i][ghost_path_counter[i]] = current_x;
			ghost_paths_y[i][ghost_path_counter[i]++] = current_y;
			if ( current_x < goal_x )
				++current_x;
			if ( current_y < goal_y )
				++current_y;
			if ( current_x > goal_x )
				--current_x;
			if ( current_y > goal_y )
				--current_y;
		}
	}

	public static boolean check ( int i , int j ) {
		return i >= 0 && j >= 0 && i < m && j < n && labryrinth[i][j] != '#';
	}
		
	public static int findShortestPath ( ) {	
		for ( int i = 0 ; i < m ; ++i ) {
			for ( int k = 0 ; k < n ; ++k ) {
				current_start[i][k] = false;
			}
		}
		current_start[start_x][start_y] = true;
		for ( int timer = 0 ; timer <= time ; ++timer ) {
			if ( current_start[end_x][end_y] ) {
				return timer;
			}
			
			System.out.println("The possible locations at time "+timer);
			for ( int i = 0 ; i < m ; ++i ) {
				for ( int k = 0 ; k < n ; ++k ) {
					System.out.print(current_start[i][k]?"1":"0");
				}
				System.out.println();
			}
			System.out.println();
			printGhostLocations(timer);
			printGhostLocations(timer+1);
			
			for ( int i = 0 ; i < m ; ++i ) {
				for ( int k = 0 ; k < n ; ++k ) {
					if ( current_start[i][k] ) {
						for ( int w = 0 ; w < dx.length ; ++w ) {
							if ( check(i+dx[w],k+dy[w]) && !next_start[i+dx[w]][k+dy[w]]   && ! isGhost1(i+dx[w],k+dy[w], timer) ) {
//								System.out.println((i+dx[w])+" "+(k+dy[w]));
								next_start[i+dx[w]][k+dy[w]] = true;
							}
						}
					}
				}
			}
			for ( int i = 0 ; i < m ; ++i ) {
				for ( int k = 0 ; k < n ; ++k ) {
					current_start[i][k] = next_start[i][k];
					next_start[i][k] = false;
				}
			}
		}
		return -1;
	}
	
	private static void printGhostLocations(int timer) {
		System.out.print("At time "+timer+" there will be ghosts at:");
		for ( int k = 0 ; k < num_ghosts ; ++k ) {
			int index = timer%ghost_path_counter[k];
			System.out.print("("+ghost_paths_y[k][index]+","+ghost_paths_x[k][index]+") ;" );
		}
		System.out.println();
	}

}

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class HauntedMazeBitmasks {
	/*
	 * 

	public static int dx[] = { -1 , 1 , 0 , 0 , 0 };
	public static int dy[] = { 0 , 0 , -1 , 1 , 0 };
	public static boolean is_ghost[][] = new boolean[31][31];
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
		
	public static void main ( String args[] ) throws EOFException {
		test_judge();
	}
	
	public static void test_judge() throws EOFException {
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
	    	if ( h == 3 ) {
	    		int qwe = findShortestPath();
	    		if ( qwe == -1 ) {
	    			throw new EOFException();
	    		}
	    		
	    	}
	    	System.out.println("0");
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
			for ( int k = 0 ; k < num_ghosts ; ++k ) {
				int index = timer%ghost_path_counter[k];
				is_ghost[ghost_paths_y[k][index]][ghost_paths_x[k][index]] = true;
				index = (timer+1)%ghost_path_counter[k];
				is_ghost[ghost_paths_y[k][index]][ghost_paths_x[k][index]] = true;
			}
			for ( int i = 0 ; i < m ; ++i ) {
				for ( int k = 0 ; k < n ; ++k ) {
					if ( current_start[i][k] ) {
						for ( int w = 0 ; w < dx.length ; ++w ) {
							if ( check(i+dx[w],k+dy[w]) && !next_start[i+dx[w]][k+dy[w]] && ! is_ghost[i+dx[w]][k+dy[w]] ) {
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
					is_ghost[i][k] = false;
				}
			}
		}
		return -1;
	}
	
	 */
	public static String labryrinth[];
	public static int ghost_waypoints_x[][];
	public static int ghost_waypoints_y[][];
	public static int ghost_paths_x[][];
	public static int ghost_paths_y[][];
	public static int ghost_path_counter[];
	public static int start_x = 0;
	public static int start_y = 0;
	public static int end_x = 0;
	public static int end_y = 0;
	public static int m;
	public static int n;
	public static int num_ghosts;
	public static int time;
	public static int walls_bitmask[];
	public static int current_bitmask[];
	public static int next_bitmask[];
	public static int ghosts_bitmask[];
	
	public static void main ( String args[] ) {
		test_file();
		test_judge();
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
			ghost_waypoints_x = new int[num_ghosts][];
			ghost_waypoints_y = new int[num_ghosts][];
			for ( int k = 0 ; k < num_ghosts ; ++k ) {
				StringTokenizer patrol = new StringTokenizer(in.nextLine());
				int num_waypoints = new Integer(patrol.nextToken());
				ghost_waypoints_x[k] = new int[num_waypoints];
				ghost_waypoints_y[k] = new int[num_waypoints];
				for ( int i = 0 ; i < num_waypoints ; ++i ) {
					ghost_waypoints_x[k][i] = new Integer(patrol.nextToken());
					ghost_waypoints_y[k][i] = new Integer(patrol.nextToken());
				}
			}
			labryrinth = new String[m];
			for ( int i = 0 ; i < m ; ++i ) {
				labryrinth[i] = in.nextLine();
			}
			walls_bitmask = new int[m];
			for ( int i = 0 ; i < m ; ++i ) {
				walls_bitmask[i] = (1<<31)-1;
				for ( int j = 0 ; j < labryrinth[i].length() ; ++j ) {
					if ( labryrinth[i].charAt(j) == '#' ) {
						walls_bitmask[i] -= (1<<(n-j-1));
					}
					if ( labryrinth[i].charAt(j) == '@' ) {
						start_x = i;
						start_y = j;
					}
					if ( labryrinth[i].charAt(j) == '$' ) {
						end_x = i;
						end_y = j;
					}
				}
				walls_bitmask[i] -= 1<<n;
//				walls_bitmask[i] += 1<<31;
//				System.out.println(Integer.toBinaryString(walls_bitmask[i]));
//				walls_bitmask[i] -= 1<<31;
			}
			System.out.println(findPath());
		}
	}
	
	public static int findPath ( ) {
		ghosts_bitmask = new int[m];
		for ( int i = 0 ; i < m ; ++i ) {
			ghosts_bitmask[i] = (1<<31)-1;
		}
		current_bitmask = new int[m];
		next_bitmask = new int[m];
		current_bitmask[start_x] += 1<<(n-start_y-1);
		populateGhostPaths();
		for ( int timer = 0 ; timer <= time ; ++timer ) {
			if ( (current_bitmask[end_x] & (1<<(n-end_y-1))) > 0  ) {
				return timer;
			}
			populateGhostBitmask(timer);
			//try to move down
			for ( int i = 0 ; i < m-1 ; ++i ) {
				next_bitmask[i+1] = next_bitmask[i+1] | ( current_bitmask[i] & ghosts_bitmask[i+1] & walls_bitmask[i+1] );
			}
			//try to move up
			for ( int i = 0 ; i < m-1 ; ++i ) {
				next_bitmask[i] = next_bitmask[i] | ( current_bitmask[i+1] & ghosts_bitmask[i] & walls_bitmask[i] );
			}
			//try to move left,right or stand still
			for ( int i = 0 ; i < m ; ++i ) {
				next_bitmask[i] = next_bitmask[i] | ( (current_bitmask[i] |(current_bitmask[i]>>1) | (current_bitmask[i]<<1)) & ghosts_bitmask[i] & walls_bitmask[i] );
			}
//			System.out.println("The current possible locations at time "+timer);
//			for ( int i = 0 ; i < m ; ++i ) {
//				current_bitmask[i] += 1<<21;
//				System.out.println(Integer.toBinaryString(current_bitmask[i]));
//				current_bitmask[i] -= 1<<21;
//			}
//			System.out.println();
			for ( int i = 0 ; i < m ; ++i ) {
				current_bitmask[i] = next_bitmask[i];
				next_bitmask[i] = 0;
				ghosts_bitmask[i] = (1<<31)-1;
			}
		}
		return -1;
	}
	
	public static int determineShortestPath( int counter_s , int i, int current_x, int current_y, int goal_x, int goal_y ) {
		while ( current_x != goal_x || current_y != goal_y ) {
			ghost_paths_x[i][counter_s] = current_x;
			ghost_paths_y[i][counter_s++] = current_y;
			if ( current_x < goal_x )
				++current_x;
			if ( current_y < goal_y )
				++current_y;
			if ( current_x > goal_x )
				--current_x;
			if ( current_y > goal_y )
				--current_y;
		}
		return counter_s;
	}
	
	public static void populateGhostPaths () {
		ghost_paths_x = new int[ghost_waypoints_x.length][1000];
		ghost_paths_y = new int[ghost_waypoints_x.length][1000];
		ghost_path_counter = new int[num_ghosts];
		for ( int i = 0 ; i < ghost_waypoints_x.length ; ++i ) {
			ghost_path_counter[i] = 0;
			for ( int h = 0 ; h < ghost_waypoints_x[i].length-1 ; ++h ) { 
				ghost_path_counter[i] = determineShortestPath(ghost_path_counter[i],i,ghost_waypoints_x[i][h],ghost_waypoints_y[i][h],ghost_waypoints_x[i][h+1],ghost_waypoints_y[i][h+1]);
			}
			ghost_path_counter[i] = determineShortestPath(ghost_path_counter[i],i,ghost_waypoints_x[i][ghost_waypoints_x[i].length-1],ghost_waypoints_y[i][ghost_waypoints_x[i].length-1],ghost_waypoints_x[i][0],ghost_waypoints_y[i][0]);
		}
		for ( int i = 0 ; i < ghost_paths_x.length ; ++i ) {
			for ( int j = 0 ; j < ghost_path_counter[i] ; ++j ) {
				System.out.print(ghost_paths_x[i][j]+" "+ghost_paths_y[i][j]+" -> ");
			}
			System.out.println();
		}
	}
	
	public static void populateGhostBitmask ( int time ) {
		for ( int i = 0 ; i < num_ghosts ; ++i ) {
			int index = time%ghost_path_counter[i];
			int row = ghost_paths_y[i][index];
			int col = ghost_paths_x[i][index];
			if ( (ghosts_bitmask[row] & (1<<(n-col-1))) > 0 ) {
				ghosts_bitmask[row] -= (1<<(n-col-1));
			}
		}
		++time;
		for ( int i = 0 ; i < num_ghosts ; ++i ) {
			int index = time%ghost_path_counter[i];
			int row = ghost_paths_y[i][index];
			int col = ghost_paths_x[i][index];
			if ( (ghosts_bitmask[row] & (1<<(n-col-1))) > 0 ) {
				ghosts_bitmask[row] -= (1<<(n-col-1));
			}
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
			ghost_waypoints_x = new int[num_ghosts][];
			ghost_waypoints_y = new int[num_ghosts][];
			for ( int k = 0 ; k < num_ghosts ; ++k ) {
				StringTokenizer patrol = new StringTokenizer(in.nextLine());
				int num_waypoints = new Integer(patrol.nextToken());
				ghost_waypoints_x[k] = new int[num_waypoints];
				ghost_waypoints_y[k] = new int[num_waypoints];
				for ( int i = 0 ; i < num_waypoints ; ++i ) {
					ghost_waypoints_x[k][i] = new Integer(patrol.nextToken());
					ghost_waypoints_y[k][i] = new Integer(patrol.nextToken());
				}
			}
			labryrinth = new String[m];
			for ( int i = 0 ; i < m ; ++i ) {
				labryrinth[i] = in.nextLine();
			}
			walls_bitmask = new int[m];
			for ( int i = 0 ; i < m ; ++i ) {
				walls_bitmask[i] = (1<<31)-1;
				for ( int j = 0 ; j < labryrinth[i].length() ; ++j ) {
					if ( labryrinth[i].charAt(j) == '#' ) {
						walls_bitmask[i] -= (1<<(n-j-1));
					}
					if ( labryrinth[i].charAt(j) == '@' ) {
						start_x = i;
						start_y = j;
					}
					if ( labryrinth[i].charAt(j) == '$' ) {
						end_x = i;
						end_y = j;
					}
				}
				walls_bitmask[i] -= 1<<n;
//				walls_bitmask[i] += 1<<31;
//				System.out.println(Integer.toBinaryString(walls_bitmask[i]));
//				walls_bitmask[i] -= 1<<31;
			}
			System.out.println(findPath());
		}
	}
	
}
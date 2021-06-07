import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class HauntedLabryrinth {
	public static String labryrinth[];
	public static int ghost_waypoints_x[][];
	public static int ghost_waypoints_y[][];
	public static int ghost_paths_x[][];
	public static int ghost_paths_y[][];
	public static int start_x = 0;
	public static int start_y = 0;
	public static int end_x = 0;
	public static int end_y = 0;
	public static int m;
	public static int n;
	public static int num_ghosts;
	public static int time;
	
	
	public static void main ( String args[] ) {
//		test_file();
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
    		populateGhostPaths();
//    		printGhostWays();
//    		printGhostPaths();
    		System.out.println(findShortestPath2());
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
    		populateGhostPaths();
//    		printGhostWays();
//    		printGhostPaths();
    		System.out.println(findShortestPath2());
    	}
	}
	
	private static void printGhostPaths() {
		for ( int i = 0 ; i < ghost_paths_x.length ; ++i ) {
			for ( int k = 0 ; k < ghost_paths_x[i].length ; ++k ) {
				System.out.print(" ("+ghost_paths_x[i][k]+","+ghost_paths_y[i][k]+")  -");
			}
			System.out.println();
		}
	}

	private static void printGhostWays() {
		for ( int i = 0 ; i < ghost_waypoints_x.length ; ++i ) {
			for ( int k = 0 ; k < ghost_waypoints_x[i].length ; ++k ) {
				System.out.print(" ("+ghost_waypoints_x[i][k]+","+ghost_waypoints_y[i][k]+")  -");
			}
			System.out.println();
		}
	}

	public static void populateGhostPaths () {
		ghost_paths_x = new int[ghost_waypoints_x.length][];
		ghost_paths_y = new int[ghost_waypoints_x.length][];
		for ( int i = 0 ; i < ghost_waypoints_x.length ; ++i ) {
			int counter = 0;
			for ( int k = 0 ; k < ghost_waypoints_x[i].length-1 ; ++k ) {
				counter += Math.abs(ghost_waypoints_x[i][k]-ghost_waypoints_x[i][k+1]);
				counter += Math.abs(ghost_waypoints_y[i][k]-ghost_waypoints_y[i][k+1]);
			}
			counter += Math.abs(ghost_waypoints_x[i][0]-ghost_waypoints_x[i][ghost_waypoints_x[i].length-1]);
			counter += Math.abs(ghost_waypoints_y[i][0]-ghost_waypoints_y[i][ghost_waypoints_y[i].length-1]);
			ghost_paths_x[i] = new int[counter];
			ghost_paths_y[i] = new int[counter];
			int counter_s = 0;
			for ( int h = 0 ; h < ghost_waypoints_x[i].length-1 ; ++h ) { 
				counter_s = determineShortestPath(counter_s,i,ghost_waypoints_x[i][h],ghost_waypoints_y[i][h],ghost_waypoints_x[i][h+1],ghost_waypoints_y[i][h+1]);
			}
			determineShortestPath(counter_s,i,ghost_waypoints_x[i][ghost_waypoints_x[i].length-1],ghost_waypoints_y[i][ghost_waypoints_x[i].length-1],ghost_waypoints_x[i][0],ghost_waypoints_y[i][0]);
		}
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

	public static boolean check ( int i , int j ) {
		return i >= 0 && j >= 0 && i < labryrinth.length && j < labryrinth[0].length() && labryrinth[i].charAt(j) != '#';
	}
	
	public static boolean current_start[][];
	public static boolean next_start[][];
	
	public static int findShortestPath2 ( ) {
		int dx[] = { -1 , 1 , 0 , 0 , 0 };
		int dy[] = { 0 , 0 , -1 , 1 , 0 };
		determineStartEnd();
		current_start = new boolean[labryrinth.length][labryrinth[0].length()];
		current_start[start_x][start_y] = true;
		next_start = new boolean[labryrinth.length][labryrinth[0].length()];
		for ( int timer = 0 ; timer <= time ; ++timer ) {
			if ( current_start[end_x][end_y] ) {
				return timer;
			}
			/*
			System.out.println("The possible locations at time "+timer);
			for ( int i = 0 ; i < labryrinth.length ; ++i ) {
				for ( int k = 0 ; k < labryrinth[0].length() ; ++k ) {
					System.out.print(current_start[i][k]?"1":"0");
				}
				System.out.println();
			}
			System.out.println();
			printGhostLocations(timer);
			printGhostLocations(timer+1);
			*/
			for ( int i = 0 ; i < labryrinth.length ; ++i ) {
				for ( int k = 0 ; k < labryrinth[0].length() ; ++k ) {
					if ( current_start[i][k] == true ) {
						for ( int w = 0 ; w < dx.length ; ++w ) {
							if ( check(i+dx[w],k+dy[w]) && !next_start[i+dx[w]][k+dy[w]]   && !is_ghost(i+dx[w],k+dy[w], timer) && ! is_ghost(i+dx[w],k+dy[w], timer+1) ) {
//								System.out.println((i+dx[w])+" "+(k+dy[w]));
								next_start[i+dx[w]][k+dy[w]] = true;
							}
						}
					}
				}
			}
			for ( int i = 0 ; i < labryrinth.length ; ++i ) {
				for ( int k = 0 ; k < labryrinth[0].length() ; ++k ) {
					current_start[i][k] = next_start[i][k];
					next_start[i][k] = false;
				}
			}
		}
		return -1;
	}
	
	private static void printGhostLocations(int timer) {
		System.out.print("At time "+timer+" there will be ghosts at:");
		for ( int k = 0 ; k < ghost_paths_x.length ; ++k ) {
			int index = timer%ghost_paths_x[k].length;
			System.out.print("("+ghost_paths_y[k][index]+","+ghost_paths_x[k][index]+") ;" );
		}
		System.out.println();
	}

	public static int findShortestPath ( ) {
		int dx[] = { -1 , 1 , 0 , 0 };
		int dy[] = { 0 , 0 , -1 , 1 };
		boolean is_visited[][] = new boolean[labryrinth.length][labryrinth[0].length()];
		determineStartEnd();
		LinkedList<Integer> queue_x = new LinkedList<Integer>();
		LinkedList<Integer> queue_y = new LinkedList<Integer>();
		queue_x.add(start_x);
		queue_y.add(start_y);
		is_visited[start_x][start_y] = true;
		boolean exit = false;
		int timer = 0;
		int front = 1;
		int rear = 0;
		int border = front;
		while ( ! queue_x.isEmpty() ) {
			int current_x = queue_x.remove();
			int current_y = queue_y.remove();
			++rear;
//			System.out.println(current_x+" "+current_y+" "+timer);
			if ( current_x == end_x && current_y == end_y ) {
				System.out.println("exit found");
				exit = true;
				break;
			}
			boolean to_stand_still = false;
			for ( int i = 0 ; i < dx.length ; ++i ) {
				if ( check(current_x+dx[i], current_y+dy[i]) && ! is_visited[current_x+dx[i]][current_y+dy[i]] ) {
					if ( is_ghost(current_x+dx[i], current_y+dy[i],timer+1) ) {
						to_stand_still = true;
					}
					else {
						queue_x.add(current_x+dx[i]);
						queue_y.add(current_y+dy[i]);
						++front;
						is_visited[current_x+dx[i]][current_y+dy[i]] = true;
					}
				}
			}
			if ( to_stand_still ) {
				if ( !is_ghost(current_x , current_y,timer+1) ) {
					queue_x.add(current_x);
					queue_y.add(current_y);
					++front;
				}
			}
			if ( rear == border ) {
				if ( timer == time ) {
					break;
				}
				++timer;
				border = front;
			}
		}
		if ( ! exit ) {
			System.out.println("exit NOT found");
			return -1;
		}
		return timer;
	}

	private static boolean is_ghost(int x, int y, int timer) {
		for ( int k = 0 ; k < ghost_paths_x.length ; ++k ) {
			int index = timer%ghost_paths_x[k].length;
			if ( ghost_paths_x[k][index] == y && ghost_paths_y[k][index] == x ) {
				return true;
			}
		}
		return false;
	}

	private static void determineStartEnd() {
		for ( int i = 0 ; i < labryrinth.length ; ++i ) {
			for ( int k = 0 ; k < labryrinth[0].length() ; ++k ) {
				if ( labryrinth[i].charAt(k) =='@' ) {
					start_x = i;
					start_y = k;
				}
				if ( labryrinth[i].charAt(k) =='$' ) {
					end_x = i;
					end_y = k;
				}
			}
		}
	}

}

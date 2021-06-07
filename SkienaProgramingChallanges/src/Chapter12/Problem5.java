package Chapter12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class Sighting implements Comparable<Sighting>{
	int time;
	int x1,x2,y1,y2;
	//x1,y1 top-left corner , x2,y2 bottom-right corner
	
	public Sighting ( int a , int b , int c , int d ,int f) {
		time = a;
		x1 = b;
		y1 = c;
		x2 = d;
		y2 = f;
	}
	@Override
	public int compareTo(Sighting o) {
		return time - o.time;
	}
	
	
}

public class Problem5 {
	public static int w;
	public static int h;
	public static int t;
	public static int num_sightings;
	public static Sighting sightings[];
	public static LinkedList<String> output = new LinkedList<String>();
	public static boolean found_robber[];
	
	public static void main ( String args[] ) {
		test_file();
		test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem5.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int test_case_id = 1;
    	while ( true ) {
    		w = in.nextInt();
    		h = in.nextInt();
    		t = in.nextInt();
    		found_robber = new boolean[t+2];
    		if ( w == 0 && h == 0 && t == 0 ) {
    			break;
    		}
    		num_sightings = in.nextInt();
    		sightings = new Sighting[num_sightings];
    		for ( int i = 0 ; i < num_sightings ; ++i ) {
    			sightings[i] = new Sighting(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
    		}
    		Arrays.sort(sightings);
    		System.out.println("Robbery #"+test_case_id+":");
    		determineRobberPosition();	
    		System.out.println();
    		++test_case_id;
    		output = new LinkedList<String>();
    	}
	}
	
	public static boolean check ( int i , int j ) {
		return i >= 0 && j >=0 && i < w && j < h;
	}
	
	public static void print ( boolean a[][] ) {
		System.out.println();
		for ( int i = 0 ; i < a.length ; ++i ) {
			for ( int k = 0 ; k < a[0].length ; ++k ) {
				System.out.print(a[i][k]?"1":"0");
			}
			System.out.println();
		}
	}
	
	private static void determineRobberPosition() {
		int dx[] = {1,-1,0,0,0};
		int dy[] = {0,0,1,-1,0};
		boolean current[][] = new boolean[w][h];
		boolean next[][] = new boolean[w][h];
		boolean path_determinable[][] = new boolean[w][h];
		int robery_index = 0;
		boolean is_anything_known = false;
		for ( int i = 0 ; i < w ; ++i ) {
			for ( int k = 0 ; k < h ; ++k ) {
				current[i][k] = true;
			}
		}
		for ( int z = 0 ; z <= t ; ++z ) {
			
			int counter = 0;
			int x = 0,y = 0;
			for ( int i = 0 ; i < w ; ++i ) {
				for ( int k = 0 ; k < h ; ++k ) {
					if ( current[i][k] ) {
						++counter;
						x = i;
						y = k;
					}
				}
			}
			if ( counter == 0 ) {
				is_anything_known = true;
				System.out.println("The robber has escaped.");
				return;
			}
			if ( counter == 1 ) {
				is_anything_known = true;
				//System.out.println("The robber was certainly at "+x+","+y+" at time "+z);
				if ( path_determinable[x][y] ) {
					//System.out.println("There is only one way for that robber to have gotten there");
					determinePath(x,y,z);
				}
				if ( ! found_robber[z-1] ) {
					found_robber[z-1] = true;
					output.add("Time step "+(z-1)+":"+" The robber has been at "+(x+1)+","+(y+1)+".");
				}
				
			}
			
			
			/*
			System.out.println("Currently possible positions");
			print(current);
			System.out.println("Path can be found");
			print(path_determinable);
			System.out.println("Next");
			print(next);
			*/
			
			for ( int i = 0 ; i < w ; ++i ) {
				for ( int k = 0 ; k < h ; ++k ) {
					for ( int q = 0 ; q < dx.length ; ++q ) {
						if ( check(i+dx[q],k+dy[q]) && current[i][k] ) {
							if ( ! path_determinable[i+dx[q]][k+dy[q]] ) {
								if ( ! current[i+dx[q]][k+dy[q]] ) {
									path_determinable[i+dx[q]][k+dy[q]] = true;
								}
							}
							else {
								path_determinable[i+dx[q]][k+dy[q]] = false;
							}
							next[i+dx[q]][k+dy[q]] = true;
						}
					}
				}
			}
			int temp_robbery_index = robery_index;
			while ( robery_index < sightings.length && sightings[robery_index].time == z) {
				for ( int i = sightings[robery_index].x1 ; i <= sightings[robery_index].x2 ; ++i ) {
					for ( int k = sightings[robery_index].y1 ; k <= sightings[robery_index].y2 ; ++k ) {
						next[i-1][k-1] = false;
						path_determinable[i-1][k-1] = false;
//						prev_x[i-1][k-1] = -1;
					}
				}
				++robery_index;
			}
			
			for ( int i = 0 ; i < w ; ++i ) {
				for ( int k = 0 ; k < h ; ++k ) {
					current[i][k] = next[i][k];
					next[i][k] = false;
				}
			}
		}
		if ( ! is_anything_known ) {
			System.out.println("Nothing known.");
		}
		else {
			for (String s : output) {
				System.out.println(s);
			}
		}
	}
	
	public static void determinePath ( int end_x , int end_y , int time ) {
		int dx[] = {1,-1,0,0,0};
		int dy[] = {0,0,1,-1,0};
		boolean current[][] = new boolean[w][h];
		current[end_x][end_y] = true;
		boolean next[][] = new boolean[w][h];
		int robery_index = sightings.length-1;
		while ( robery_index >= 0 && sightings[robery_index].time >= time-1 ) {
			--robery_index;
		}
		for ( int z = time ; z >= 0 ; --z ) {
			
			for ( int i = 0 ; i < w ; ++i ) {
				for ( int k = 0 ; k < h ; ++k ) {
					for ( int q = 0 ; q < dx.length ; ++q ) {
						if ( check(i+dx[q],k+dy[q]) && current[i][k] ) {
							next[i+dx[q]][k+dy[q]] = true;
						}
					}
				}
			}
			while ( robery_index >= 0 && sightings[robery_index].time == z-2 ) {
				for ( int i = sightings[robery_index].x1 ; i <= sightings[robery_index].x2 ; ++i ) {
					for ( int k = sightings[robery_index].y1 ; k <= sightings[robery_index].y2 ; ++k ) {
						next[i-1][k-1] = false;
					}
				}
				--robery_index;
			}
			
			for ( int i = 0 ; i < w ; ++i ) {
				for ( int k = 0 ; k < h ; ++k ) {
					current[i][k] = next[i][k];
					next[i][k] = false;
				}
			}
			int counter = 0;
			int x = 0,y = 0;
			for ( int i = 0 ; i < w ; ++i ) {
				for ( int k = 0 ; k < h ; ++k ) {
					if ( current[i][k] ) {
						++counter;
						x = i;
						y = k;
					}
				}
			}
			if ( counter == 1 ) {
//				System.out.println("This is the begining of the path "+x+","+y+" at time "+z);
//				System.out.println("This is the full path: ");
				int d_x = x-end_x > 0 ? -1 : x-end_x < 0 ? 1 : 0;
				int d_y = y-end_y > 0 ? -1 : y-end_y < 0 ? 1 : 0;
				int t_x = x;
				int t_y = y;
				while ( t_x != end_x || t_y != end_y ) {
					if ( ! found_robber[z-2] ) {
						found_robber[z-2] = true;
						output.add("Time step "+(z-2)+":"+" The robber has been at "+(t_x+1)+","+(t_y+1)+".");
					}
					t_x += d_x;
					t_y += d_y;
					++z;
				}
				return;
			}
			
		}
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int test_case_id = 1;
    	while ( true ) {
    		w = in.nextInt();
    		h = in.nextInt();
    		t = in.nextInt();
    		found_robber = new boolean[t+2];
    		if ( w == 0 && h == 0 && t == 0 ) {
    			break;
    		}
    		num_sightings = in.nextInt();
    		sightings = new Sighting[num_sightings];
    		for ( int i = 0 ; i < num_sightings ; ++i ) {
    			sightings[i] = new Sighting(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
    		}
    		Arrays.sort(sightings);
    		System.out.println("Robbery #"+test_case_id+":");
    		determineRobberPosition();	
    		System.out.println();
    		++test_case_id;
    		output = new LinkedList<String>();
    	}
	}

}

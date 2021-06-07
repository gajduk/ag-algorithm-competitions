package Chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem4 {
	
	public static int findMInPathDP ( int map[][] ) {
		if ( map.length == 1 ) {
			int result = 0;
			for ( int i = 0 ; i < map[0].length ; ++i ) {
				System.out.print("1");
				result += map[0][i];
				if ( i != map[0].length-1 ) {
					System.out.print(" ");
				}
			}
			System.out.println();
			return result;
		}
		int min[][] = new int[map.length][map[0].length];
		int prev[][] = new int[map.length][map[0].length];
		for ( int i = 0 ; i < map.length ; ++i ) {
			min[i][0] = map[i][0];
		}
		for ( int k = 1 ; k < map[0].length ; ++k ) {
			for ( int i = 0 ; i < map.length ; ++i ) {
				if ( i == 0 ) {
					int temp = Math.min(min[i][k-1], Math.min(min[i+1][k-1], min[min.length-1][k-1]));
					min[i][k] = temp;
					if ( min[i][k] == min[i][k-1] ) {
						prev[i][k] = i;
					}
					else {
						if ( min[i][k] == min[i+1][k-1] ) {
							prev[i][k] = i+1;
						}
						else {
							prev[i][k] = min.length-1;
						}
					}
				}
				else {
					if ( i == min.length-1 ) {
						int temp = Math.min(min[i][k-1], Math.min(min[i-1][k-1], min[0][k-1]));
						min[i][k] = temp;
						if ( min[i][k] == min[0][k-1] ) {
							prev[i][k] = 0;
						}
						else {
							if ( min[i][k] == min[i-1][k-1] ) {
								prev[i][k] = i-1;
							}
							else {
								prev[i][k] = i;
							}
						}
					}
					else {
						int temp = Math.min(min[i][k-1], Math.min(min[i-1][k-1], min[i+1][k-1]));
						min[i][k] = temp;
						if ( min[i][k] == min[i-1][k-1] ) {
							prev[i][k] = i-1;
						}
						else {
							if ( min[i][k] == min[i][k-1] ) {
								prev[i][k] = i;
							}
							else {
								prev[i][k] = i+1;
							}
						}
					}
				}
				min[i][k] += map[i][k];
			}
		}
		int min_index = 0;
		for ( int i = 1 ; i < min.length ; ++i ) {
			if ( min[i][min[0].length-1] == min[min_index][min[0].length-1] ) {
				min_index = getPath(min_index,min[0].length-1,prev).compareTo(getPath(i,min[0].length-1,prev)) > 0 ? i : min_index;
			}
			if ( min[i][min[0].length-1] < min[min_index][min[0].length-1] ) {
				min_index = i;
			}
			
		}
		printPaths(prev,min_index,min[0].length-1);
		System.out.println();
		return min[min_index][min[0].length-1];
	}
	
	public static int findMInPathDPBetter ( int map[][] ) {
		if ( map.length == 1 ) {
			int result = 0;
			for ( int i = 0 ; i < map[0].length ; ++i ) {
				System.out.print("1");
				result += map[0][i];
				if ( i != map[0].length-1 ) {
					System.out.print(" ");
				}
			}
			System.out.println();
			return result;
		}
		int min[][] = new int[map.length][map[0].length];
		String path[][] = new String[map.length][map[0].length];
		for ( int i = 0 ; i < map.length ; ++i ) {
			min[i][0] = map[i][0];
			path[i][0] = Integer.toString(i+1);
		}
		for ( int k = 1 ; k < map[0].length ; ++k ) {
			for ( int i = 0 ; i < map.length ; ++i ) {
				if ( i == 0 ) {
					int temp = Math.min(min[i][k-1], Math.min(min[i+1][k-1], min[min.length-1][k-1]));
					min[i][k] = temp;
					String best = "99";
					if ( min[i][k-1] == temp && path[i][k-1].compareTo(best) < 0 ) {
						best = path[i][k-1];
					}
					if ( min[i+1][k-1] == temp && path[i+1][k-1].compareTo(best) < 0 ) {
						best = path[i+1][k-1];
					}
					if ( min[min.length-1][k-1] == temp && path[min.length-1][k-1].compareTo(best) < 0 ) {
						best = path[min.length-1][k-1];
					}
					path[i][k] = best;
				}
				else {
					if ( i == min.length-1 ) {
						int temp = Math.min(min[i][k-1], Math.min(min[i-1][k-1], min[0][k-1]));
						min[i][k] = temp;
						String best = "99";
						if ( min[i][k-1] == temp && path[i][k-1].compareTo(best) < 0 ) {
							best = path[i][k-1];
						}
						if ( min[i-1][k-1] == temp && path[i-1][k-1].compareTo(best) < 0 ) {
							best = path[i-1][k-1];
						}
						if ( min[0][k-1] == temp && path[0][k-1].compareTo(best) < 0 ) {
							best = path[0][k-1];
						}
						path[i][k] = best;
					}
					else {
						int temp = Math.min(min[i][k-1], Math.min(min[i-1][k-1], min[i+1][k-1]));
						min[i][k] = temp;
						String best = "99";
						if ( min[i][k-1] == temp && path[i][k-1].compareTo(best) < 0 ) {
							best = path[i][k-1];
						}
						if ( min[i-1][k-1] == temp && path[i-1][k-1].compareTo(best) < 0 ) {
							best = path[i-1][k-1];
						}
						if ( min[i+1][k-1] == temp && path[i+1][k-1].compareTo(best) < 0 ) {
							best = path[i+1][k-1];
						}
						path[i][k] = best;
					}
				}
				min[i][k] += map[i][k];
				path[i][k] = path[i][k] +" " +Integer.toString(i+1);
			}
		}
		for ( int i = 0 ; i < path.length ; ++i ) {
//			System.out.println(Arrays.toString(path[i]));
//			System.out.println(Arrays.toString(map[i]));
//			System.out.println(Arrays.toString(min[i]));
		}
		int min_index = 0;
		String min_path = path[0][map[0].length-1];
		for ( int i = 1 ; i < min.length ; ++i ) {
			if ( min[i][min[0].length-1] == min[min_index][min[0].length-1] ) {
				if ( path[i][min[0].length-1].compareTo(path[min_index][min[0].length-1]) < 0 ) {
					min_index = i;
				}
			}
			if ( min[i][min[0].length-1] < min[min_index][min[0].length-1] ) {
				min_index = i;
			}
			
		}
		System.out.println(path[min_index][min[0].length-1]);
		return min[min_index][min[0].length-1];
	}
	
	private static String getPath( int i, int k , int path[][] ) {
		if ( k == -1 ) {
			return "";
		}
		return getPath(path[i][k],k-1,path)+Integer.toString((i+1));
	}

	public static void printPaths ( int path[][] , int k , int i) {
		if ( i == -1 ) {
			return;
		}
		printPaths(path, path[k][i],i-1);
		if ( i != 0 ) {
			System.out.print(" ");
		}
		System.out.print((k+1));
	}
	
	public static void printPaths ( int path[][] , int k ) {
		System.out.print((k+1)+" ");
		for ( int i = path[0].length-1 ; i >= 1 ; --i ) {
			System.out.print((path[k][i]+1));
			k = path[k][i];
			if ( i != 1 ) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}
	
	public static void main ( String args [] ) {
		test_file();
		test_judge();
	}
	
	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		int m = in.nextInt();
    		int n = in.nextInt();
    		int map[][] = new int[m][n];
    		for ( int i = 0 ; i < m ; ++i ) {
    			for ( int k = 0 ; k < n ; ++k ) {
    				map[i][k] = in.nextInt();
    			}
    		}
    		System.out.println(findMInPathDPBetter(map));
    	}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter11 problem4.txt");
	    }
    	catch ( FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		int m = in.nextInt();
    		int n = in.nextInt();
    		int map[][] = new int[m][n];
    		for ( int i = 0 ; i < m ; ++i ) {
    			for ( int k = 0 ; k < n ; ++k ) {
    				map[i][k] = in.nextInt();
    			}
    		}
    		System.out.println(findMInPathDPBetter(map));
    	}
 
	}

}

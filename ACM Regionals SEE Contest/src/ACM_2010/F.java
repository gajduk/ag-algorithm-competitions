package ACM_2010;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class F {
	
public static char ints[] = { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' }; 
	
	public static void main ( String args[] ) {
		long start = System.currentTimeMillis();
		long end = 0;
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/acm-final-2010/input/F.in");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	
		Scanner in = new Scanner(inputFile);
		int m;
		int n;
		int best[][][] = new int[1000][1000][10];
		int global_best[][][] = new int[1000][1000][10];
		String map[] = new String[1000000];
		int num_gamers = 0;
		while ( true ) {
			if ( in.hasNext() ) {
				m = in.nextInt();
				n = in.nextInt();
				num_gamers = m*n;
			}
			else {
				break;
			}
			
			//Reading in the whole map
			for ( int i = 0 ; i < m ; ++i  ) {
				map[i] = in.next();
			}
			
			//from TOP LEFT
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[0][0][k] = Integer.MAX_VALUE;
				global_best[0][0][k] = best[0][0][k];
			}
			//first column
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[i][0][k] = map[i-1].charAt(0) == ints[k] ? 1 : best[i-1][0][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[i-1][0][k]+1;
					global_best[i][0][k] = best[i][0][k];
				}
			}
			//first row 
			for ( int j = 1 ; j < n ; ++j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[0][j][k] = map[0].charAt(j-1) == ints[k] ? 1 : best[0][j-1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE :  best[0][j-1][k]+1;
					global_best[0][j][k] = best[0][j][k];
				}
			}
			//all others
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int j = 1 ; j < n ; ++j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp = Math.min(best[i-1][j][k],best[i][j-1][k]);
						best[i][j][k] = map[i].charAt(j-1)== ints[k] || map[i-1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ) {
							best[i][j][k] = temp+1;
						}
						global_best[i][j][k] = best[i][j][k];
					}
				}
			}
			
			
			//from the DOWN LEFT
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[m-1][0][k] = Integer.MAX_VALUE;
				global_best[m-1][0][k] = Math.min(best[m-1][0][k],global_best[m-1][0][k]);
			}
			
			for ( int i = m-2 ; i >= 0 ; --i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[i][0][k] = map[i+1].charAt(0) == ints[k] ? 1 : best[i+1][0][k]  == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[i+1][0][k]+1;
					global_best[i][0][k] = Math.min(best[i][0][k],global_best[i][0][k]);
				}
			}
			
			//first row 
			for ( int j = 1 ; j < n ; ++j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[m-1][j][k] = map[m-1].charAt(j-1) == ints[k] ? 1 : best[m-1][j-1][k]  == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[m-1][j-1][k]+1;
					global_best[m-1][j][k] = Math.min(global_best[m-1][j][k],best[m-1][j][k]);
				}
			}
			//all others
			for ( int i = m-2 ; i >= 0 ; --i ) {
				for ( int j = 1 ; j < n ; ++j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp = Math.min(best[i+1][j][k],best[i][j-1][k]);
						best[i][j][k] = map[i].charAt(j-1)== ints[k] || map[i+1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ) {
							best[i][j][k] = temp+1;
						}
						global_best[i][j][k] = Math.min(best[i][j][k],global_best[i][j][k]);
					}
				}
			}
			
			
			
			//from the TOP RIGHT
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[0][n-1][k] = Integer.MAX_VALUE;
				global_best[0][n-1][k] = Math.min(global_best[0][n-1][k],best[0][n-1][k]);
			}
			//first column
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[i][n-1][k] = map[i-1].charAt(n-1) == ints[k] ? 1 : best[i-1][n-1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[i-1][n-1][k]+1;
					global_best[i][n-1][k] = Math.min(global_best[i][n-1][k],best[i][n-1][k]);
				}
			}
			//first row 
			for ( int j = n-2 ; j >= 0 ; --j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[0][j][k] = map[0].charAt(j+1) == ints[k] ? 1 : best[0][j+1][k]  == Integer.MAX_VALUE ? Integer.MAX_VALUE :  best[0][j+1][k]+1;
					global_best[0][j][k] = Math.min(global_best[0][j][k],best[0][j][k]);
				}
			}
			//all others
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int j = n-2 ; j >= 0 ; --j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp =  Math.min(best[i-1][j][k],best[i][j+1][k]);
						best[i][j][k] = map[i].charAt(j+1)== ints[k] || map[i-1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ) {
							best[i][j][k] = temp+1;
						}
						global_best[i][j][k] = Math.min(global_best[i][j][k],best[i][j][k]);
					}
				}
			}
			
			//from DOWN RIGHT
			long total_sum = 0;
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[m-1][n-1][k] = Integer.MAX_VALUE;
				global_best[m-1][n-1][k] = Math.min(global_best[m-1][n-1][k],best[m-1][n-1][k]);
				total_sum += (global_best[m-1][n-1][k] == Integer.MAX_VALUE) ? 0 : global_best[m-1][n-1][k];
			}
			//first column
			for ( int i = m-2 ; i >= 0 ; --i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[i][n-1][k] = map[i+1].charAt(n-1) == ints[k] ? 1 : best[i+1][n-1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[i+1][n-1][k]+1;
					global_best[i][n-1][k] = Math.min(global_best[i][n-1][k],best[i][n-1][k]);
					total_sum += (global_best[i][n-1][k] == Integer.MAX_VALUE) ? 0 : global_best[i][n-1][k];
				}
			}
			//first row 
			for ( int j = n-2 ; j >= 0 ; --j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[m-1][j][k] = map[m-1].charAt(j+1) == ints[k] ? 1 : best[m-1][j+1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[m-1][j+1][k]+1;
					global_best[m-1][j][k] = Math.min(global_best[m-1][j][k],best[m-1][j][k]);
					total_sum += (global_best[m-1][j][k] == Integer.MAX_VALUE) ? 0 : global_best[m-1][j][k];
				}
			}
			//all others
			for ( int i = m-2 ; i >= 0  ; --i ) {
				for ( int j = n-2 ; j >= 0 ; --j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp = Math.min(best[i+1][j][k],best[i][j+1][k]);
						best[i][j][k] = map[i].charAt(j+1)== ints[k] || map[i+1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ){
							best[i][j][k] = temp+1;
						}
						global_best[i][j][k] = Math.min(best[i][j][k],global_best[i][j][k]);
						total_sum += (global_best[i][j][k] == Integer.MAX_VALUE) ? 0 : global_best[i][j][k];
					}
				}
			}
			/*
			for ( int w = 0 ; w < 4 ; ++w ) {
				for ( int i = 0 ; i < m ; ++i ) {
					for ( int j = 0 ; j < n ; ++j ) {
						for ( int k = 1 ; k < 4 ; ++k ) {
							System.out.print(best[w][i][j][k]==Integer.MAX_VALUE? "*":best[w][i][j][k]+" ");
						}
						System.out.print(" <:> ");
					}		
					System.out.println();
				}
				System.out.println();
			}
			*/
			System.out.println(total_sum*2);
//			System.exit(0);
		}
		end = System.currentTimeMillis();
		System.out.println(end-start+" ms");
	}

	
	
	/*
	public static char ints[] = { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' }; 
	
	public static void main ( String args[] ) {
		long start = System.currentTimeMillis();
		long end = 0;
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/acm-final-2010/input/F.in");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	
		Scanner in = new Scanner(inputFile);
		int m;
		int n;
		int best[][][][] = new int[4][1000][1000][10];
		String map[] = new String[1000000];
		int num_gamers = 0;
		while ( true ) {
			if ( in.hasNext() ) {
				m = in.nextInt();
				n = in.nextInt();
				num_gamers = m*n;
			}
			else {
				break;
			}
			
			//Reading in the whole map
			for ( int i = 0 ; i < m ; ++i  ) {
				map[i] = in.next();
			}
			//if ( m != 0 )
			//	continue;
			//from TOP LEFT
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[0][0][0][k] = Integer.MAX_VALUE;
			}
			//first column
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[0][i][0][k] = map[i-1].charAt(0) == ints[k] ? 1 : best[0][i-1][0][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[0][i-1][0][k]+1;
				}
			}
			//first row 
			for ( int j = 1 ; j < n ; ++j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[0][0][j][k] = map[0].charAt(j-1) == ints[k] ? 1 : best[0][0][j-1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE :  best[0][0][j-1][k]+1;
				}
			}
			//all others
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int j = 1 ; j < n ; ++j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp = Math.min(best[0][i-1][j][k],best[0][i][j-1][k]);
						best[0][i][j][k] = map[i].charAt(j-1)== ints[k] || map[i-1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[0][i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ) {
							best[0][i][j][k] = temp+1;
						}
					}
				}
			}
			
			
			//from the DOWN LEFT
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[1][m-1][0][k] = Integer.MAX_VALUE;
			}
			
			for ( int i = m-2 ; i >= 0 ; --i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[1][i][0][k] = map[i+1].charAt(0) == ints[k] ? 1 : best[1][i+1][0][k]  == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[1][i+1][0][k]+1;
				}
			}
			
			//first row 
			for ( int j = 1 ; j < n ; ++j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[1][m-1][j][k] = map[m-1].charAt(j-1) == ints[k] ? 1 : best[1][m-1][j-1][k]  == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[1][m-1][j-1][k]+1;
				}
			}
			//all others
			for ( int i = m-2 ; i >= 0 ; --i ) {
				for ( int j = 1 ; j < n ; ++j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp = Math.min(best[1][i+1][j][k],best[1][i][j-1][k]);
						best[1][i][j][k] = map[i].charAt(j-1)== ints[k] || map[i+1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[1][i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ) {
							best[1][i][j][k] = temp+1;
						}
					}
				}
			}
			
			
			
			//from the TOP RIGHT
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[2][0][n-1][k] = Integer.MAX_VALUE;
			}
			//first column
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[2][i][n-1][k] = map[i-1].charAt(n-1) == ints[k] ? 1 : best[2][i-1][n-1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[2][i-1][n-1][k]+1;
				}
			}
			//first row 
			for ( int j = n-2 ; j >= 0 ; --j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[2][0][j][k] = map[0].charAt(j+1) == ints[k] ? 1 : best[2][0][j+1][k]  == Integer.MAX_VALUE ? Integer.MAX_VALUE :  best[2][0][j+1][k]+1;
				}
			}
			//all others
			for ( int i = 1 ; i < m ; ++i ) {
				for ( int j = n-2 ; j >= 0 ; --j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp =  Math.min(best[2][i-1][j][k],best[2][i][j+1][k]);
						best[2][i][j][k] = map[i].charAt(j+1)== ints[k] || map[i-1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[2][i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ) {
							best[2][i][j][k] = temp+1;
						}
					}
				}
			}
			
			//from DOWN RIGHT
			for ( int k = 0 ; k < 10 ; ++k ) {
				best[3][m-1][n-1][k] = Integer.MAX_VALUE;
			}
			//first column
			for ( int i = m-2 ; i >= 0 ; --i ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[3][i][n-1][k] = map[i+1].charAt(n-1) == ints[k] ? 1 : best[3][i+1][n-1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[3][i+1][n-1][k]+1;
				}
			}
			//first row 
			for ( int j = n-2 ; j >= 0 ; --j ) {
				for ( int k = 0 ; k < 10 ; ++k ) {
					best[3][m-1][j][k] = map[m-1].charAt(j+1) == ints[k] ? 1 : best[3][m-1][j+1][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : best[3][m-1][j+1][k]+1;
				}
			}
			//all others
			for ( int i = m-2 ; i >= 0  ; --i ) {
				for ( int j = n-2 ; j >= 0 ; --j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int temp = Math.min(best[3][i+1][j][k],best[3][i][j+1][k]);
						best[3][i][j][k] = map[i].charAt(j+1)== ints[k] || map[i+1].charAt(j)== ints[k] ? 1 : Integer.MAX_VALUE;
						if ( best[3][i][j][k] == Integer.MAX_VALUE && temp != Integer.MAX_VALUE ){
							best[3][i][j][k] = temp+1;
						}
					}
				}
			}
			
			for ( int w = 0 ; w < 4 ; ++w ) {
				for ( int i = 0 ; i < m ; ++i ) {
					for ( int j = 0 ; j < n ; ++j ) {
						for ( int k = 1 ; k < 4 ; ++k ) {
							System.out.print(best[w][i][j][k]==Integer.MAX_VALUE? "*":best[w][i][j][k]+" ");
						}
						System.out.print(" <:> ");
					}		
					System.out.println();
				}
				System.out.println();
			}
			
			long total_sum = 0;
			for ( int i = 0 ; i < m ; ++i ) {
				for ( int j = 0 ; j < n ; ++j ) {
					for ( int k = 0 ; k < 10 ; ++k ) {
						int t = Integer.MAX_VALUE;
						for ( int w = 0 ; w < 4 ; ++w ) {
							t = Math.min(t, best[w][i][j][k]);
						}
//						System.out.println("Position: ("+i+","+j+")"+" game_id: "+k+" min_distance "+t);
						total_sum += (t == Integer.MAX_VALUE) ? 0 : t;
					}
				}
			}
			System.out.println(total_sum*2);
//			System.exit(0);
		}
		end = System.currentTimeMillis();
		System.out.println(end-start+" ms");
	}

	
	*/
}

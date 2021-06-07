import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Zivko {
	
	static int done[][];
	static int amap[][];
	
	public static void main(String[] args) throws Exception {
		
		ArrayList<String> result = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		done = new int[n][m];
		boolean has_line[][][][] = new boolean[n][m][n][m]; 
		String str = n+" " + m +" ##### ";
		int map[][] = new int[n][m];
		for (int i = 0; i < n; ++i) {
			for (int k = 0; k < m / 8; ++k) {
				int q = in.nextInt();
				str += " "+q;
				for (int p = 7; p >= 0; --p) {
					map[i][k * 8 + 7 - p] = (q & (1 << p)) > 0 ? 1 : 0;
				}
			}
		}
		amap = map;
//		print(map);
		/*
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		*/
//		if(true)
//			throw new Exception(str);
		populateHas_line(has_line,map);
		
		for (int i = 0; i < n ; i++) {
			for (int k = 0; k < m ; k++) {
				
				for (int a = 2; a <= n ; ++a ) {
					try {
					if ( has_line[i][k][i+a][k] &&
						 has_line[i][k][i][k+a] &&
						 has_line[i+a][k][i+a][k+a] &&
						 has_line[i][k+a][i+a][k+a] ) {
						result.add("Square");
						dfs(i,k,n,m);
					}
					}catch ( Exception e ) {}
				}
				
			}
		}
		
		// Rectangle
		for (int i = 0; i < n ; i++) {
			for (int k = 0; k < m ; k++) {
				
				for (int width = 2; width <= n ; width++) {
					for (int height = 2; height <= m ; height++) {
						if ( height == width ) continue;
							try {
							if ( has_line[i][k][i+width][k] &&
								 has_line[i][k][i][k+height] &&
								 has_line[i+width][k][i+width][k+height] &&
								 has_line[i][k+height][i+width][k+height] ) {
								result.add("Rectangle");
								dfs(i,k,n,m);
							}
							}catch ( Exception e ) {}
						
					}
				}
				
			}
		}
		
		
		// Paralelogram
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < m; k++) {
				for ( int width = 2 ; width <= n ; ++width ) {
					for ( int height = 2 ; height <= m ; ++height ) {
						try {
						if ( has_line[i][k][i+width][k] &&
							 has_line[i][k][i-height][ k+height] &&
							 has_line[i-height][k+height][i-height][k+height+width] &&
							 has_line[i+width][k][i-height][k+height+width] ) {
								result.add("Parallelogram");
								dfs(i,k,n,m);
						}
						}catch(Exception e) {}
						try {
						if ( has_line[i][k][i+width][k] &&
							 has_line[i][k][i+height][k+height] &&
							 has_line[i+height][k+height][i+width+height][k+height] &&
							 has_line[i+width][k][i+width+height][k+height]  ) {
								result.add("Parallelogram");
								dfs(i,k,n,m);
						}
						}catch(Exception e) {}
						try {
						if ( has_line[i][k][i][k+height] &&
							 has_line[i][k][i+width][k-width] &&
							 has_line[i][k+height][i+width][k+height-width] &&
							 has_line[i+width][k-width][i+width][k+height-width] ) {
								result.add("Parallelogram");
								dfs(i,k,n,m);
						}
						}catch(Exception e) {}
						try {
						if ( has_line[i][k][i][k+height] &&
							 has_line[i][k][i+width][k+width] &&
							 has_line[i][k+height][i+width][k+height+width] &&
							 has_line[i+width][k+width][i+width][k+height+width] ) {
									result.add("Parallelogram");
									dfs(i,k,n,m);
						}
						}catch(Exception e) {}
					}
				}
			}
		}
		//Triagolnici
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < m; k++) {
				for ( int a = 2 ; a < n ; ++a ) {
						/**
						 
						   1 
						   1 1
						   1   1
						   1     1
						   1 1 1 1 1
						   
						 */
						try {
						if ( has_line[i][k][i+a][k]   &&
							 has_line[i][k][i+a][k+a] &&
							 has_line[i+a][k][i+a][k+a] ) {
							dfs(i,k,n,m);
								result.add("Triangle");
								continue;
						}
						}catch(Exception e) {}
					 
						/**
						 
						   1 1 1 1 1
						   1     1
						   1   1
						   1 1
						   1
						 
						 */
						try {
							if ( has_line[i][k][i+a][k] &&
								 has_line[i][k][i][k+a] &&
								 has_line[i+a][k][i][k+a] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						/**
						  
						  	       1
						  	     1   1
						  	   1       1
						  	 1           1
						   1 1 1 1 1 1 1 1 1  
						  
						 */
						try {
							if ( has_line[i][k][i+a][k-a] &&
								 has_line[i][k][i+a][k+a] &&
								 has_line[i+a][k-a][i+a][k+a] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/**
						  
						   1 1 1 1 1 1 1 1 1  
						  	 1           1
						  	   1       1
						  	     1   1
						  	       1
						  
						 */
						
						try {
							if ( has_line[i][k][i+a][k+a]   &&
								 has_line[i][k][i][k+a+a]  &&
								 has_line[i+a][k+a][i][k+a+a] ) {
									dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/** 
						           1
						         1 1
						       1   1
						     1     1
						   1       1
						     1     1
						       1   1
						         1 1
						           1
						           
						 */
						try {
							if ( has_line[i][k][i-a][k+a] &&
								 has_line[i][k][i+a][k+a] &&
								 has_line[i+a][k+a][i-a][k+a] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/** 
						 
						     1
						     1 1
						     1   1
						     1     1
						     1       1
						     1     1
						     1   1
						     1 1
						     1
						           
						 */
						try {
							if ( has_line[i][k][i+a+a][k]   &&
								 has_line[i][k][i][k+a] &&
								 has_line[i][k+a][i+a+a][k] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/**
						 
						              1
						            1 1
						          1   1
						        1     1
						      1       1
						    1 1 1 1 1 1
						  
						 */
						
						try {
							if ( has_line[i][k][i+a][k] &&
								 has_line[i][k][i+a][k-a] &&
								 has_line[i+a][k][i+a][k-a] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/**
						 
						      1 1 1 1 1 1
						        1       1
						          1     1
						            1   1
						              1 1
						                1
						  
						 */
						try {
							if ( has_line[i][k][i+a][k]   &&
								 has_line[i][k][i+a][k+a] &&
								 has_line[i+a][k+a][i+a][k] ) {
									dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
				}
			}
		}
		
		
		
		
		
		
		
		
		
//		print(done);
//		System.out.println(result);
		
		///Za mali triagolnici
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < m; k++) {
				if ( done[i][k] == 1 ) continue;
				 int a = 1;
						/**
						 
						   1 
						   1 1
						   1   1
						   1     1
						   1 1 1 1 1
						   
						 */
						try {
						if ( has_line[i][k][i+a][k]   &&
							 has_line[i][k][i+a][k+a] &&
							 has_line[i+a][k][i+a][k+a] ) {
							dfs(i,k,n,m);
								result.add("Triangle");
								continue;
						}
						}catch(Exception e) {}
					 
						/**
						 
						   1 1 1 1 1
						   1     1
						   1   1
						   1 1
						   1
						 
						 */
						try {
							if ( has_line[i][k][i+a][k] &&
								 has_line[i][k][i][k+a] &&
								 has_line[i+a][k][i][k+a] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						/**
						  
						  	       1
						  	     1   1
						  	   1       1
						  	 1           1
						   1 1 1 1 1 1 1 1 1  
						  
						 */
						try {
							if ( has_line[i][k][i+a][k-a] &&
								 has_line[i][k][i+a][k+a] &&
								 has_line[i+a][k-a][i+a][k+a] ) {
//								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/**
						  
						   1 1 1 1 1 1 1 1 1  
						  	 1           1
						  	   1       1
						  	     1   1
						  	       1
						  
						 */
						
						try {
							if ( has_line[i][k][i+a][k+a]   &&
								 has_line[i][k][i][k+a+a]  &&
								 has_line[i+a][k+a][i][k+a+a] ) {
									dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/** 
						           1
						         1 1
						       1   1
						     1     1
						   1       1
						     1     1
						       1   1
						         1 1
						           1
						           
						 */
						try {
							if ( has_line[i][k][i-a][k+a] &&
								 has_line[i][k][i+a][k+a] &&
								 has_line[i+a][k+a][i-a][k+a] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/** 
						 
						     1
						     1 1
						     1   1
						     1     1
						     1       1
						     1     1
						     1   1
						     1 1
						     1
						           
						 */
						try {
							if ( has_line[i][k][i+a+a][k]   &&
								 has_line[i][k][i][k+a] &&
								 has_line[i][k+a][i+a+a][k] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/**
						 
						              1
						            1 1
						          1   1
						        1     1
						      1       1
						    1 1 1 1 1 1
						  
						 */
						
						try {
							if ( has_line[i][k][i+a][k] &&
								 has_line[i][k][i+a][k-a] &&
								 has_line[i+a][k][i+a][k-a] ) {
								dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
						
						/**
						 
						      1 1 1 1 1 1
						        1       1
						          1     1
						            1   1
						              1 1
						                1
						  
						 */
						try {
							if ( has_line[i][k][i+a][k]   &&
								 has_line[i][k][i+a][k+a] &&
								 has_line[i+a][k+a][i+a][k] ) {
									dfs(i,k,n,m);
										result.add("Triangle");
										continue;
								}
						}catch(Exception e) {}
				
			}
		}
		Collections.sort(result);
		if(result.size() > 0)
			System.out.print(result.get(0));
		for (int i = 1; i < result.size(); i++) {
			System.out.print(", "+result.get(i));
		}
		System.out.println();
	}

	private static void populateHas_line(boolean[][][][] has_line, int[][] map) {
		for ( int i = 0 ; i < has_line.length ; ++i ) {
			for ( int k = 0 ; k < has_line[0].length ; ++k ) {
				int di[] = { -1,-1,0,1,1, 1, 0,-1};
				int dk[] = { 0,  1,1,1,0,-1,-1,-1};
				for ( int w = 0 ; w < di.length ; ++w ) {
					for ( int ii = i,kk = k ; ii < map.length && ii >= 0 && kk < map[0].length && kk >= 0 ;  ) {
//						if ( i == 2 && k == 13  && w == 5 ) {System.out.println(ii+" ,"+kk);
//						System.out.println(map[ii][kk]);
//						print(map);
//						}
						if ( map[ii][kk] == 0 ) break;

						has_line[i][k][ii][kk] = true;
						ii+=di[w];kk+=dk[w];
					}
				}
			}
		}
		/*
		print(has_line[2][7]);
		print(has_line[2][13]);

		print(has_line[5][10]);
		*/
	}

	private static void print(int[][] map) {
		System.out.println();
		for ( int i = 0 ; i < map.length; ++i ) {
			for ( int k = 0 ; k < map[0].length ; ++k ) {
				System.out.print(map[i][k]>0?1:" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void print(boolean[][] bs) {
		System.out.println();
		for ( int i = 0 ; i < bs.length; ++i ) {
			for ( int k = 0 ; k < bs[0].length ; ++k ) {
				System.out.print(bs[i][k]?"1":"0");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	static int di[] = { -1,-1,0,1,1, 1, 0,-1};
	static int dj[] = { 0,  1,1,1,0,-1,-1,-1};
	
	public static void dfs(int i, int j, int n, int m){
		done[i][j] = 1;
		for (int j2 = 0; j2 < 8; j2++) {
			int ni = i + di[j2];
			int nj = j + dj[j2];
			if(ni >= 0 && ni < n && nj >= 0 && nj < m && done[ni][nj] == 0&& amap[ni][nj]==1){
				dfs(ni, nj, n, m);
			}
			
		}
		
	}
	
	
	

}

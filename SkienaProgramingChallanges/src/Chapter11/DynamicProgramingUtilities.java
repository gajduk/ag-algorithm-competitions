package Chapter11;

import java.util.Arrays;

public class DynamicProgramingUtilities {
	
	public static void main ( String args[] ) {
		/*
		int test[] = { 1,5,3,3,5,1,2,3,4,2,5,3,6,2,1,3,4,2,1,3 };
		long start = System.currentTimeMillis();
		System.out.println(chainedMatricesMultiplication1(test));
		long end = System.currentTimeMillis();
		System.out.println((end-start)+" ms for non tabled");
		start = System.currentTimeMillis(); 
		chainedMatricesMultiplication2(test);
		end = System.currentTimeMillis();
		System.out.println((end-start)+" ms for top-down tabled");
		start = System.currentTimeMillis();
		chainedMatricesMultiplication3(test);
		end = System.currentTimeMillis();
		System.out.println((end-start)+" ms for bottom-up tabled");
		*/
//		System.out.println(LongestCommonSubsequence("abcde","acfdqwe"));
		/*
		int a[] = { 6,27,1,26,2,25,24,23,3,22,21,4,20,19,18,17,5,10,18,20 };
		System.out.println(longestAscendingSubsequence(a));
		*/
		travaleingSalesmanProblem();
	}
	
	public static int chainedMatricesMultiplication3 ( int d[] ) {
		int t[][] = new int[d.length][d.length];
		for ( int i = 0 ; i < d.length-1 ; ++i ) {
			t[i][i] = 0;
		}
		for ( int i = 0 ; i < d.length-2 ; ++i ) {
			t[i][i+1] = d[i]*d[i+1]*d[i+2];
		}
		for ( int k = 2 ; k <= d.length-2 ; ++k ) {
			for ( int i = 0 ; i < d.length-k-1 ; ++i ) {
				int min = Integer.MAX_VALUE;
				for ( int s = 0 ; s < k ; ++s ) {
					int temp = t[i][i+s]+t[i+1+s][i+k];
					temp += d[i]*d[i+s+1]*d[i+k+1];
					if ( temp < min ) 
						min = temp;
				}
				t[i][i+k] = min;
			}
		}
		return t[0][d.length-2];
	}
	
	public static int chainedMatricesMultiplication1 ( int d[] ) {
		return chainedMatricesMultiplication1Recursive(d,0,d.length-2);
	}

	private static int chainedMatricesMultiplication1Recursive(int[] d, int i,	int j) {
		if ( j - i == 0 ) {
			return 0;
		}
		if ( j - i == 1 ) {
			return d[j-1]*d[j]*d[j+1];
		}
		int min = Integer.MAX_VALUE;
		for ( int s = i ; s < j ; ++s ) {
			int temp = d[i]*d[s+1]*d[j+1];
			temp += chainedMatricesMultiplication1Recursive(d,i,s);
			temp += chainedMatricesMultiplication1Recursive(d,s+1,j);
			if ( temp < min ) {
				min = temp;
			}
		}
		return min;
	}
	
	public static int table[][];
	
	public static int chainedMatricesMultiplication2 ( int d[] ) {
		table = new int[d.length][d.length];
		for ( int i = 0 ; i < d.length ; ++i ) {
			for ( int k = 0 ; k < d.length ; ++k ) {
				table[i][k] = -1;
			}
		}
		return chainedMatricesMultiplication2Recursive(d,0,d.length-2);
	}

	private static int chainedMatricesMultiplication2Recursive(int[] d, int i,	int j) {
		if ( j - i == 0 ) {
			table[i][j] = 0;
			return 0;
		}
		if ( j - i == 1 ) {
			table[i][j] = d[j-1]*d[j]*d[j+1];
			return table[i][j];
		}
		int min = Integer.MAX_VALUE;
		for ( int s = i ; s < j ; ++s ) {
			int temp = d[i]*d[s+1]*d[j+1];
			if ( table[i][s] != -1 ) {
				temp += table[i][s];
			}
			else {
				temp += chainedMatricesMultiplication2Recursive(d,i,s);
			}
			if ( table[s+1][j] != -1 ) {
				temp += table[s+1][j];
			}
			else {
				temp += chainedMatricesMultiplication2Recursive(d,s+1,j);
			}
			if ( temp < min ) {
				min = temp;
			}
		}
		table[i][j] = min;
		return min;
	}
	
	public static int longestCommonSubsequence ( String a , String b ) {
		int t[][] = new int[a.length()+1][b.length()+1];
		for ( int i = 0 ; i < a.length()+1 ; ++i ) {
			t[i][0] = 0;
		}
		for ( int i = 0 ; i < b.length()+1 ; ++i ) {
			t[0][i] = 0;
		}
		for ( int i = 1 ; i <= a.length() ; ++i ) {
			for ( int k = 1 ; k <= b.length() ; ++k ) {
				if ( a.charAt(i-1) == b.charAt(k-1) ) {
					t[i][k] = t[i-1][k-1]+1;
				}
				else {
					t[i][k] = t[i-1][k] > t[i][k-1] ? t[i-1][k] : t[i][k-1];
				}
			}
		}
		return t[a.length()][b.length()];
	}
	
	public static int longestCommonSubsequence ( int a[] , int b[] ) {
		int t[][] = new int[a.length+1][b.length+1];
		for ( int i = 0 ; i < a.length+1 ; ++i ) {
			t[i][0] = 0;
		}
		for ( int i = 0 ; i < b.length+1 ; ++i ) {
			t[0][i] = 0;
		}
		for ( int i = 1 ; i <= a.length ; ++i ) {
			for ( int k = 1 ; k <= b.length ; ++k ) {
				if ( a[i-1] == b[k-1] ) {
					t[i][k] = t[i-1][k-1]+1;
				}
				else {
					t[i][k] = t[i-1][k] > t[i][k-1] ? t[i-1][k] : t[i][k-1];
				}
			}
		}
		return t[a.length][b.length];
	}
	
	public static int longestAscendingSubsequence ( int a[] ) {
		int b[] = new int[a.length];
		for ( int i = 0 ; i < a.length ; ++i ) {
			b[i] = a[i];
		}
		Arrays.sort(b);
		return longestCommonSubsequence(a, b);
	}
	
	public static int l[][];
	public static int graph[][] = { {0,10,15,20},{5,0,9,10},{6,13,0,12},{8,8,9,0} };
	
	public static int travaleingSalesmanProblem ( ) {
		l = new int[graph.length][1<<graph.length];
		for ( int i = 0 ; i < graph.length ; ++i ) {
			l[i][0] = graph[i][0];
		}
		used = new boolean[graph.length];
//		for ( int i = 0 ; i < graph.length ; ++i ) {
			for ( int k = 1 ; k <= graph.length ; ++k ) {
				int subset[] = new int[k];
				findAllSubsets(k,graph.length,0,subset,0,0);
			}
//		}
		for ( int k = 0 ; k < graph.length ; ++k ) {
			System.out.println(Arrays.toString(l[k]));
		}
		return 0;
	}
	
	public static boolean used[];

	private static void findAllSubsets(int k, int n , int level , int subset[] , int start , int i ) {
		if ( level >= k ) {
			// TO DO: process the current subset
//			System.out.println(Arrays.toString(subset));
			
			int index = 0;
			for ( int w = 0 ; w < subset.length ; ++w ) {
				index += 1<<subset[w];
				used[subset[w]] = true;
			}
			
			int temp_index = index;
			int min = Integer.MAX_VALUE;
			for ( int j = 0; j < graph.length ; ++j ) {
				temp_index = index;
				if ( used[j] ) {
					temp_index -= 1<<j;
				}
				used[j] = false;
//				System.out.println(temp_index+" "+j);
				int temp = l[j][temp_index]+graph[i][j];
				if ( l[j][temp_index] != Integer.MAX_VALUE ) {
					l[j][index] = temp;
					if ( temp < min ) {
						min = temp;
					}
				}
				
			}
//			System.out.println(index);
			
			l[i][index] = min;
			return;
		}
		
		if ( start >= n ) {
			return;
		}
		for ( int w = start ; w < n ; ++w ) {
			subset[level] = w;
			
			findAllSubsets(k,n,level+1,subset,w+1,i);
		}
	}
	

}

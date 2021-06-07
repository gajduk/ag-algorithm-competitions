package Chapter10;

public class LongestCommonSubsequence {
	
	public static void main ( String args[] )  {
		test_LCS();
	}
	
	public static void test_LCS() {
		int a[] = { 1 , 5 , 2 , 3 , 2 , 3 , 4 , 6 , 1 };
		int b[] = { 1 , 1 , 2 , 2 , 3 , 3 , 4 , 5 , 6 }; 
		System.out.println(longestCommonSubsequence(a,b));
	}

	public static int longestCommonSubsequence ( int[] array1 , int[] array2 ) {
		int table[][] = new int[array1.length+1][array2.length+1];
		for ( int k = 0 ; k < array1.length ; ++k ) {
			table[k][0] = 0;
		}
		for ( int k = 0 ; k < array2.length ; ++k ) {
			table[0][k] = 0;
		}
		for ( int m = 1 ; m <= array1.length ; ++m ) {
			for ( int k = 1 ; k <= array2.length ; ++k ) {
				if ( array1[m-1] == array2[k-1] ) {
					table[m][k] = table[m-1][k-1]+1;
				}
				else {
					table[m][k] = table[m-1][k] > table[m][k-1] ? table[m-1][k] : table[m][k-1];
				}
			}
		}
	//	print(table);
		/*
		int k = array2.length;
		int m = array1.length;
		while ( true ) {
			if ( m > 0 && k > 0 ) {
				if ( table[m][k] == table[m-1][k-1]+1 ) {
					System.out.println(array1[k-1]);
					--m;
					--k;
				}
				else {
					if ( table[m][k] == table[m-1][k] ) {
						--m;
					}
					else {
						--k;
					}
				}
			}
			else {
				if ( array1[m] == array2[k] ) {
					System.out.println(array1[m]);
					break;
				}
				if ( m == 0 ) {
					--k;
				}
				else {
					--m;
				}
			}
			if ( m == 0 && k == 0 )
				break;
		}
		*/
		return table[array1.length][array2.length];	
	}

	public static void print(int[][] table) {
		for ( int i = 0 ; i < table.length ; ++i ) {
			for ( int k = 0 ; k < table.length ; ++k ) {
				System.out.print(table[i][k]+" ");
			}
			System.out.println();
		}
	}

}

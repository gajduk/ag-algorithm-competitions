package UVA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Recycling {
	
	static int table[][];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int w = 0 ; w < t ; ++w ) {
			int n = in.nextInt();
			int item_counter = 1;
			HashMap<String,Integer> items = new HashMap<String,Integer>();
			int a[] = new int[n];
			
			for ( int i = 0 ; i < n ; ++i ) {
				String item = in.next();
				if ( recyclable(item) ) {
					Integer  idx = items.get(item);
					if ( idx == null ) {
						idx = item_counter++;
						items.put(item, idx);
					}
					a[i] = idx;
				}
			}
			int cost = 0;
			int last_after_recycable = 0;
			for ( int i = 0 ; i < n ; ++i ) {
				while ( i < n && a[i] == 0 ) ++i;
				int start = i;
				while ( i < n && a[i] != 0 ) ++i;
				int end = i;
				int aa[] = Arrays.copyOfRange(a, start, end);
				last_after_recycable = i+1;
				table = new int[aa.length+1][aa.length+1];
				for ( int j = 0 ; j < table.length ; ++j ) {
					for ( int k = 0 ; k < table.length ; ++k ) {
						table[j][k] = -1;
					}
				}
				cost += min(aa, 0,aa.length);
			}
			System.out.println("Case "+(w+1)+": "+cost);
		}
	}

	private static int min(int[] a, int i, int j) {
		if ( i >= j  ) return 0;
		if ( table[i][j] == -1 )  {
			int min = 1+min(a,i+1,j);
			for ( int k = i+1 ; k < j ;++k ) {
				if ( a[i] == a[k] ) {
					int t = min(a,i+1,k)+min(a,k,j);
					if ( t < min ) 	min = t;
				}
			}
			table[i][j] = min;
		}
		return table[i][j];
	}

	private static boolean recyclable(String item) {
		return item.length()!=0&&Character.isLowerCase(item.charAt(0));
	}

}

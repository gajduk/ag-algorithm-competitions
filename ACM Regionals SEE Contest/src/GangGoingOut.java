import java.util.Arrays;

import ACM_2002.G;


public class GangGoingOut {
	
	public static void main ( String args[] ) {
		GangGoingOut g = new GangGoingOut();
		int t[] =  {10,9,8,7,6,5,4,3,2,1};
		System.out.println(g.solve(10, 1,t));
	}
	
	public int solve(int N, int K, int[] heights) {
		Arrays.sort(heights);
		for ( int i = 0 ; i < t.length ; ++i ) {
			for ( int k = 0 ; k < t[i].length ; ++k ) {
				t[i][k] = -1;
			}
		}
		return optimal(N,K,heights,1,0);
	}
	
	int t[][] = new int[101][2001];

	private int optimal(int n, int k, int[] heights, int group, int start) {
		//System.out.println(group+" "+start+" "+k);
		if ( t[group][start] != -1 ) {
			return t[group][start];
		}
		if ( group > k ) return 0;
		if ( group == k ) return (int) Math.sqrt(heights[heights.length-1]-heights[start]);
		int min = Integer.MAX_VALUE;
		for ( int i = start+1 ; i < n-(k-group)*2 ; ++i ) {
			int t = optimal(n, k, heights, group+1, i+1) + (int) Math.sqrt(heights[i]-heights[start]);
			if ( t < min ) min = t;
		}
		//System.out.println(min);
		t[group][start] = min;
		return min;
	}

}

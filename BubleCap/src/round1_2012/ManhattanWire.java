package round1_2012;

import java.util.ArrayList;
import java.util.Scanner;
/*
 3 3
2 0 3
0 0 0
3 0 2
6 7
2 0 0 0 0 0 3
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
3 0 0 0 0 0 2
0 0 
 */

public class ManhattanWire {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			best = 100;
			int n = in.nextInt();
			int m = in.nextInt();
			if ( n == 0 && m == 0 ) return;
			int map[][] = new int[n][m];
			for (int i = 0; i < map.length; i++) {
				for (int k = 0; k < map[0].length; k++) {
					map[i][k] = in.nextInt();
					if ( map[i][k] == 3 ) {
						s_i = i; s_k = k;
					}
				}
			}
			System.out.println(determineShortestWire(map));
		}
	}

	public static int determineShortestWire(int[][] map) {
		boolean flag = false;
		int start_i = 0 , start_k =0; 
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map[0].length; k++) {
				if ( map[i][k] == 2 ) {
					if ( ! flag ) {
						start_i = i; start_k = k;
						flag = true;
					}
					else {
						e_i = i; e_k = k;
					}
				}
			}
		}
		map[start_i][start_k] = 0;
		map[e_i][e_k] = 0;
		dfs(map,start_i,start_k);
		if ( best == 100 )
			return 0;
		return best;
	}

	public static int[] di = { -1 , 1 , 0 , 0 };
	public static int[] dk = { 0 , 0 , -1 , 1 };
	public static int s_i, s_k;
	public static int e_i, e_k;
	public static int best = 100;
	
	
	public static void dfs(int[][] map, int cur_i, int cur_k) {
		if ( cur_i == e_i && cur_k == e_k ) {
			best = Math.min(best,count(map)+bfs(map));
			return;
		}
		map[cur_i][cur_k] = 2;
//		print(map);
		for ( int w = 0 ; w < di.length ; ++w ) {
			int t_i = di[w]+cur_i;
			int t_k = dk[w]+cur_k;
			if ( check(t_i,t_k,map) ) {
				dfs(map,t_i,t_k);
			}
		}
		map[cur_i][cur_k] = 0;
	}

	public static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map[0].length; k++) {
				System.out.print(map[i][k]==2?"1":"0");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int count(int[][] map) {
		int res = 0;
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map[0].length; k++) {
				res += map[i][k] == 2 ? 1 : 0;
			}
		}
		return res-1;
	}

	public static int bfs(int[][] map) {
		ArrayList<Integer> queue_i = new ArrayList<Integer>();
		ArrayList<Integer> queue_k = new ArrayList<Integer>();
		queue_i.add(s_i); queue_k.add(s_k);
		boolean visited[][] = new boolean[map.length][map[0].length];
		int front = 1;
		int rear = 0;
		int border = front;
		int level = 1;
		visited[s_i][s_k] = true;
		while ( ! queue_i.isEmpty() ) {
			int cur_i = queue_i.remove(0);
			int cur_k = queue_k.remove(0);
			++rear;
			if ( !(cur_i == s_i && cur_k == s_k) && map[cur_i][cur_k] == 3 ) {
//				System.out.println(cur_i+" "+cur_k);
				return level;
			}
			for (int w = 0; w < di.length; w++) {
				int t_i = di[w]+cur_i;
				int t_k = dk[w]+cur_k;
				if ( checkbfs(t_i,t_k,map) && ! visited[t_i][t_k] ) {
					visited[t_i][t_k] = true;
					queue_i.add(t_i); queue_k.add(t_k);
					++front;
				}
			}
			if ( rear == border ) {
				border = front; ++level;
			}
		}
		return 200;
	}
	
	public static boolean checkbfs(int i, int k , int map[][] ) {
		return i >= 0 && k >= 0 && i < map.length && k < map[0].length && map[i][k] != 1 && map[i][k] != 2;
	}

	public static boolean check(int i, int k , int map[][] ) {
		return i >= 0 && k >= 0 && i < map.length && k < map[0].length && map[i][k] != 1 && map[i][k] !=3 && map[i][k] != 2;
	}

}

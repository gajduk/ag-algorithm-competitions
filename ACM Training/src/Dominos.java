import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dominos {
	
	public static void main(String[] args) {
		  Scanner s = new Scanner(System.in);
		  
		  int n = s.nextInt();
		  int m = s.nextInt();
		  
		  boolean visited[] = new boolean[n];
		  ArrayList<Integer> next[] = new ArrayList[n];
		  ArrayList<Integer> prev[] = new ArrayList[n];
		  int deg[] = new int[n];
		  for (int i = 0; i < n; i++) {
			  next[i] = new ArrayList<Integer>();
			  prev[i] = new ArrayList<Integer>();
		  }
		  for (int i = 0; i < m; i++) {
			   int a = s.nextInt() - 1;
			   int b = s.nextInt() - 1;
			   deg[b]++;
			   next[a].add(b);
			   prev[b].add(a);
		  }
		  int ans = 0;
		  for (int i = 0; i < n; i++) {
			  if(deg[i] == 0) {
				  ans++;
				  dfs(visited,i,next);
			  }
		  }
		  for (int i = 0; i < n; i++) {
				if ( !visited[i]) {
					  ans++;
					  dfs(visited,i,next,prev);
				}
		  }
		  System.out.println(ans);
	}

	private static void dfs(boolean[] visited, int i,
			ArrayList<Integer>[] next, ArrayList<Integer>[] prev) {
		visited[i] = true;
		for ( Integer n : next[i] ) {
			if ( !visited[n] ) {
				dfs(visited,n,next,prev);
			}
		}
		for ( Integer n : prev[i] ) {
			if ( !visited[n] ) {
				dfs(visited,n,next,prev);
			}
		}
	}

	private static void dfs(boolean[] visited, int i , ArrayList<Integer> next[] ) {
		visited[i] = true;
		for ( Integer n : next[i] ) {
			if ( !visited[n] ) {
				dfs(visited,n,next);
			}
		}
	}
}

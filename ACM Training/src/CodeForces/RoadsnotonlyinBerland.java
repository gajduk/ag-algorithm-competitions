package CodeForces;

import java.util.ArrayList;
import java.util.Scanner;



public class RoadsnotonlyinBerland {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Edge> edges = new ArrayList<Edge>(n+1);
		int root[] = new int[n];
		Edge connected[][] = new Edge[n][n];
		root[n-1] = n-1;
		for ( int i = 0 ; i < n-1 ; ++i ) {
			Edge e = new Edge(in.nextInt()-1,in.nextInt()-1);
			connected[e.a][e.b] = e;
			connected[e.b][e.a] = e;
			edges.add(e);
			root[i] = i;
		}
		boolean visited[] = new boolean[n];
		for ( int i = 0 ; i < n ; ++i ) {
			if ( !visited[i] ) dfs(connected,root,root[i],i,visited);
		}
		ArrayList<Edge> edges_for_deletion = new ArrayList<Edge>(n+1);
		for ( Edge e : edges ) {
			if ( ! e.visited ) edges_for_deletion.add(e);
		}
		System.out.println(edges_for_deletion.size());
		int rs = root[0];
		int idx = 0;
		for ( int i = 0 ; i < n ; ++i ) {
			if ( getRoot(root,i) != rs ) {
				Edge e = edges_for_deletion.get(idx++);
				System.out.println((e.a+1)+" "+(e.b+1)+" "+(rs+1)+" "+(i+1));
				root[getRoot(root,i)] = rs;
			}
		}
		
	}

	private static int getRoot(int[] root, int i) {
		while ( root[i] != i ) i = root[i];
		return i;
	}

	private static void dfs(Edge[][] connected, int[] root, int ri, int s,
			boolean[] visited) {
		visited[s] = true;
		for ( int i = 0 ; i < connected.length ; ++i ) {
			if ( connected[s][i] != null && ! visited[i] ) {
				dfs(connected,root,ri,i,visited);
				connected[s][i].visited = true;
			}
			
		}
		root[s] = ri;
		
	}

}

class Edge {
	public int a;
	public int b;
	public boolean visited = false;
	
	public Edge(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	
}

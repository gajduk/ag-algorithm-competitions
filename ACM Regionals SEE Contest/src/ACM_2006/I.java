package ACM_2006;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.ReverbType;

class Edge {
	int from;
	int to;
	int weight;
	boolean is_visited;
	
	public Edge ( int a , int b , int c ) {
		from = a;
		to = b;
		weight = c;
		is_visited = false;
	}
	
	@Override
	public String toString() {
		return from+" "+to+" "+weight+" ";
	}
}

public class I {
	public static ArrayList<Edge> graph[];
	public static ArrayList<Edge> reverse_graph[];
	
	public static void main ( String args[] ) {
		FileInputStream input_file = null;
		try {
			input_file = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/Desktop/ACM Regionals Problem Sets SouthEast Europe/input/i.in");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(input_file);
		while ( true ) {
			int num_vertices = in.nextInt();
			int num_edges = in.nextInt();
			if ( num_edges == 0 && num_vertices == 0 ) {
				break;
			}
			System.out.println(num_edges);
			graph = new ArrayList[num_vertices];
			reverse_graph = new ArrayList[num_vertices];
			for ( int i = 0 ; i < graph.length ; ++i ) {
				graph[i] = new ArrayList<Edge>();
				reverse_graph[i] = new ArrayList<Edge>();
			}
			for ( int i = 0 ; i < num_edges ; ++i ) {
				int s = in.nextInt();
				int w = in.nextInt();
				int cost = in.nextInt();
				Edge e = new Edge(s,w,cost);
				graph[s].add(e);
				reverse_graph[w].add(e);
			}
			System.out.println(dfs(0,num_vertices-1));
		}
		
	}

	public static boolean visited[];
	
	public static int dfs( int start, int finish) {
//		visited = new boolean[graph.length];
		return dfsRecursive(start,finish);
	}
	
	public static boolean ok = false;

	private static int dfsRecursive( int current ,int finish ) {
//		visited[current] = true;
		int rez = Integer.MAX_VALUE;
		if ( current == finish ) {
			ok = true;
//			return;
		}
		if ( !ok ) {
			int min = Integer.MAX_VALUE;
			for (Edge e : graph[current] )  {
				if ( ! e.is_visited ) {
					e.is_visited = true;
					int z = dfsRecursive(e.to, finish);
					if (z < Integer.MAX_VALUE) {
						min = Math.min(min,z+e.weight);
					}
					e.is_visited = false;
				}
			}
			rez = min;
		}
		else {
			if ( current == 0 ) {
				return 0;
			}
			int min = Integer.MAX_VALUE;
			for (Edge e : reverse_graph[current] )  {
				if ( ! e.is_visited ) {
					e.is_visited = true;
					int z = dfsRecursive(e.from, finish);
					if (z < Integer.MAX_VALUE) {
						min = Math.min(min,z+e.weight);
					}
					e.is_visited = false;
				}
			}
			rez = min;
		}
		if ( current == finish ) {
			ok = false;
//			return;
		}
		return rez;
	}
		
}

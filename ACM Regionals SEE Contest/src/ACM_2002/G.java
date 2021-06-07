package ACM_2002;

import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
	int a;
	int b;
	int w;
	
	public Edge ( int q , int e , int r ) {
		a = q; b = e; w = r;
	}

	@Override
	public int compareTo(Edge e) {
		return w-e.w;
	}
	
}

public class G {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		
		while ( true ) {
			int num_vertices = in.nextInt();
			if ( num_vertices == 0 ) {
				break;
			}
			int num_edges = in.nextInt();
			Edge edges[] = new Edge[num_edges];
			for ( int i = 0 ; i < num_edges ; ++i ) {
				edges[i] = new Edge(in.nextInt()-1,in.nextInt()-1,in.nextInt());
			}
			Arrays.sort(edges);
			int set[] = new int[num_vertices];
			for ( int i = 0 ; i < set.length ; ++i ) {
				set[i] = i;
			}
			int edge_index = 0;
			int min_cost = 0;
			for ( int t = 0 ; t < num_vertices-1 ; ) {
				if ( set[edges[edge_index].a] != set[edges[edge_index].b] ) {
					min_cost += edges[edge_index].w;
					int set2 = set[edges[edge_index].b];
					int set1 = set[edges[edge_index].a];
					for ( int i = 0 ; i < set.length ; ++i ) {
						if ( set[i] == set2 ) {
							set[i] = set1;
						}
					}
					 ++t;
				}
				++edge_index;
			}
			System.out.println(min_cost);
		}
		
	}

}

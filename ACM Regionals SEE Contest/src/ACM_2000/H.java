package ACM_2000;

import java.util.Scanner;

public class H {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int num_vertices = in.nextInt();
		int imediate_ancestor[] = new int[num_vertices];
		boolean succsesors[][] = new boolean[num_vertices][num_vertices];
		for ( int i = 0 ; i < num_vertices ; ++i ) {
			int vertex_id = in.nextInt()-1;
			int num_succsesors = in.nextInt();
			for ( int k = 0 ; k < num_succsesors ; ++k ) {
				succsesors[vertex_id][in.nextInt()-1] = true;
			}
		}
		//populate(succsesors);
		//try using Floyd-Warshall
	}

}

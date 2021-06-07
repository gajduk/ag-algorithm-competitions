package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem1 {
	
	public static boolean is_colored[];
	public static boolean is_red[];
	public static int graph[][];
	
	public static void main ( String args[] ) {
		test_file();
		//test_judge();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
			int num_vertices = new Integer(in.nextLine());
			if ( num_vertices == 0 )
				break;
			int num_edges = new Integer(in.nextLine());
			String edges[] = new String[num_edges];
			for ( int h = 0 ; h < num_edges ; ++h ) {
				edges[h] = in.nextLine();
			}
			populateGraph(num_vertices,edges);
			traverseGraph();
			if ( isBicolor() ) {
				System.out.println("BICOLORABLE.");
			}
			else {
				System.out.println("NOT BICOLORABLE.");
			}
		}
	}
	
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			int num_vertices = new Integer(in.nextLine());
			if ( num_vertices == 0 )
				break;
			int num_edges = new Integer(in.nextLine());
			String edges[] = new String[num_edges];
			for ( int h = 0 ; h < num_edges ; ++h ) {
				edges[h] = in.nextLine();
			}
			populateGraph(num_vertices,edges);
			traverseGraph();
			if ( isBicolor() ) {
				System.out.println("BICOLORABLE.");
			}
			else {
				System.out.println("NOT BICOLORABLE.");
			}
		}
	}
	
	
	public static void populateGraph ( int num_vertices , String edges[] ) {
		graph = new int[num_vertices][num_vertices];
		for ( int i = 0 ; i < edges.length ; ++i ) {
			StringTokenizer numbers = new StringTokenizer(edges[i]);
			int vertex_1 = new Integer(numbers.nextToken());
			int vertex_2 = new Integer(numbers.nextToken());
			graph[vertex_1][vertex_2] = 1;
			graph[vertex_2][vertex_1] = 1;
		}
	}
	
	public static void traverseGraph () {
		is_red = new boolean[graph.length];
		is_colored = new boolean[graph.length];
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(0);
		is_colored[0] = true;
		is_red[0] = true;
		while ( ! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			for ( int i = 0 ; i < graph.length ; ++i ) {
				if ( ! is_colored[i] && graph[current_vertex][i] > 0 ) {
					is_colored[i] = true;
					is_red[i] = !is_red[current_vertex];
					queue.add(i);
				}
			}
		}
	}
	
	public static boolean isBicolor () {
		for ( int i = 0 ; i < graph.length ; ++i ) {
			for ( int j = i+1 ; j < graph.length ; ++j ) {
				if ( graph[i][j] > 0 && ((is_red[i] && is_red[j])  ||   (!is_red[i] && !is_red[j]))  ) {
					return false;
				}
			}
		}
		return true;
	}
	

}

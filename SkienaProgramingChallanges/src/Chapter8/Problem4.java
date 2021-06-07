package Chapter8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem4 {
	
	public static void main ( String args[] ) {
		//test_DominatingSet();
		test_file();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter8 problem4.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		StringTokenizer tkr = new StringTokenizer(in.nextLine());
			int n = new Integer(tkr.nextToken());
    		int m = new Integer(tkr.nextToken());
    		if ( n == 0 && m == 0 ) {
    			break;
    		}
    		String edges[] = new String[m];
    		for ( int h = 0 ; h < m ; ++h ) {
    			edges[h] = in.nextLine();
    		}
    		populateGraph(n,edges);
    		System.out.println(calculateMinimunSizeOfDominatingSet());
    		System.out.println();
    	}
	}
	
	private static void test_DominatingSet( ) {

	}
	
	public static void populateGraph ( int n , String edges[]) {
		num_vertices = n;
		graph = new boolean[num_vertices][num_vertices];
		for ( int k = 0 ; k < edges.length ; ++k ) {
			StringTokenizer vertices = new StringTokenizer(edges[k]);
			int vertex1 = new Integer(vertices.nextToken())-1;
			int vertex2 = new Integer(vertices.nextToken())-1;
			graph[vertex1][vertex2] = true;
			graph[vertex2][vertex1] = true;
		}
	}
	
	public static int calculateMinimunSizeOfDominatingSet ( ) {
		for ( int k = 16 ; k < num_vertices-1 ; ++k ) {
			System.out.println(k);
			if ( checkDominatingSet(k) )
				return k;
		}
		return num_vertices;
	}
	
	public static boolean checkDominatingSet ( int num_vertices_in_dominating ) {
		color = new int[num_vertices];
		return checkDominatingSetRecursive(0,num_vertices_in_dominating);
	}

	public static boolean graph[][];
	public static int num_vertices;
	public static int color[]; //0 no color , 1 dominant vertex , 1 dominated vertex
	
	
	public static boolean checkDominatingSetRecursive ( int current_vertex , int num_vertices_in_dominating ) {
		if ( num_vertices_in_dominating <= 0 )
			return false;
		for ( int k = current_vertex ; k < num_vertices ; ++k ) {
			int old_color[] = new int[color.length]; 
			copy(old_color);
			color[k] = 2;
			for ( int m = 0 ; m < graph[k].length ; ++m ) {
				if ( graph[k][m] ) {
					if ( color[m] == 0 ) {
						color[m] = 1;
					}
				}
			}
			if ( checkVerticesColoring() || checkDominatingSetRecursive(k+1,num_vertices_in_dominating-1) ) {
				return true;
			}
			else {
				copyBack(old_color);
			}
		}
		return false;
	}

	private static boolean checkVerticesColoring() {
		for (int i = 0; i < color.length; i++) {
			if ( color[i] == 0 )
				return false;
		}
		return true;
	}

	private static void copyBack( int old_color[] ) {
		for (int i = 0; i < color.length; i++) {
			color[i] = old_color[i];
		}
	}

	private static void copy( int old_color[]  ) {
		for (int i = 0; i < color.length; i++) {
			old_color[i] = color[i];
		}
	}

}

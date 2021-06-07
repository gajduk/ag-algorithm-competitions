package Chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Problem2 {
	
	public static int graph[][] = new int[51][51];
	
	public static void populateGraph(String[] edges) throws Exception {
		graph = new int[51][51];
		for ( int m = 0 ; m < edges.length ; ++m ) {
			String s_v1 = edges[m].substring(0,edges[m].indexOf(' '));
			String s_v2 = edges[m].substring(edges[m].indexOf(' ')+1);
			int weight = 1;
			
			try {
				int v1 = new Integer(s_v1);
				int v2 = new Integer(s_v2);
				graph[v1][v2] += weight;
				graph[v2][v1] += weight;
			} catch (Exception e) {
				throw new Exception("The edge "+edges[m]+" could not be added because:\n"+e.getMessage());
			}
			
		}
	}
	
	public static void test_large() throws Exception {
		long start = System.currentTimeMillis();
		String edges[] = new String[1000];
		for ( int i = 0 ; i < 500 ; i += 1 ) {
			int v1 = (int) (Math.random()*50)+1;
			int v2 = (int) (Math.random()*50)+1;
			edges[i] = v1+" "+v2;
			edges[i+500] = v2+" "+v1;
		}
		populateGraph(edges);
		findEulerianCycle1(graph);
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000+"."+transform((end-start)%1000)+" s");
	}
	
	private static String transform(long l) {
		String result = "";
		while ( result.length()+Long.toString(l).length() < 3 ) 
			result += "0";
		result += Long.toString(l);
		return result;
	}

	public static boolean isBridge ( int start , int  end , int graph[][] , int degree[] ) {
		ArrayList<Integer> queue = new ArrayList<Integer>();
		--graph[start][end];
		--graph[end][start];
		//print(graph);
		//System.out.println();
		boolean is_visited[] = new boolean[graph.length];
		for ( int i = 0 ; i < degree.length ; ++i ) {
			is_visited[i] = degree[i] == 0;
		}
		queue.add(start);
		is_visited[start] = true;
		while ( ! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			for ( int j = 0 ; j < graph.length ; ++j ) {
				if ( graph[current_vertex][j] > 0 && ! is_visited[j] ) {
					queue.add(j);
					is_visited[j] = true;
				}
			}
		}
		++graph[end][start];
		++graph[start][end];
		//print(is_visited);
		for ( int j = 0 ; j < graph.length ; ++j ) {
			if ( ! is_visited[j] ) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void findEulerianCycle1 ( int graph[][] ) {
		int degree[] = new int[graph.length];
		for ( int i = 0 ; i < graph.length ; ++i ) {
			for ( int j = 0 ; j < graph.length ; ++j ) {
				if ( graph[i][j] > 0 ) {
					degree[i] += graph[i][j];
					if ( i == j ) {
	//					degree[i] += graph[i][j];
					}
				}
				
			}
		}
		//print(degree);
		int start = -1;
		for ( int i = 0 ; i < graph.length ; ++i ) {
			
		//	System.out.println();
			if ( degree[i] % 2 != 0 ) {
				System.out.println("some beads may be lost");
				return;
			}
			if ( degree[i] > 0 ) {
				start = i;
			}
		}
		if ( start == -1 ) {
		//	System.out.println("THE GRAPH IS ISOLATED");
			return;
		}
	//	print(degree);
	//	print(graph);
		while ( graph[start][start] > 0 ) {
			System.out.println(start+" "+start);
			--graph[start][start];
			--graph[start][start];
			--degree[start];
			--degree[start];
		}
		if ( degree[start] == 0 ) {
			return;
		}
		
		while ( true ) {
			boolean found_one = false;
			
			int next = -1;
			
			for ( int i = 0 ; i < graph.length ; ++i ) {
				if ( start != i && graph[start][i] > 0 && ! isBridge(start, i, graph,degree) ) {
					next = i;
					found_one = true;
					break;
				}
			}
			if ( !found_one ) {
				for ( int i = 0 ; i < graph.length ; ++i ) {
					if ( start != i && graph[start][i] > 0 ) {
						next = i;
					}
				}
			}
//			print(degree);
//			print(graph);
			while ( graph[start][start] > 0 ) {
				System.out.println(start+" "+start);
				--graph[start][start];
				--graph[start][start];
				--degree[start];
				--degree[start];
			}
//			print(degree);
//			print(graph);
			System.out.println(start+" "+next);
			--graph[start][next];
			--graph[next][start];
			--degree[start];
			--degree[next];
			start = next;
//			print(degree);
//			print(graph);
			
			if ( degree[start] == 0 ) {
				break;
			}
		}
		
	}

	public static void main ( String args[] ) throws Exception  {
//		test_file();
		test_judge();
	}
	
	public static void test_file () throws Exception {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem2.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		int num_beads = new Integer(in.nextLine());
//    		System.out.println(num_beads);
    		String edges[] = new String[num_beads];
    		for ( int m = 0 ; m < num_beads ; ++m ) {
    			edges[m] = in.nextLine();
    			
    		}
    		populateGraph(edges);
    		System.out.println("Case #"+(k+1));
    		findEulerianCycle1(graph);
    		if ( k+1 != num_test_cases ) {
    			System.out.println();
    		}
    	}
	}
	
	public static void test_judge() throws Exception {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int k = 0 ; k < num_test_cases ; ++k ) {
    		int num_beads = new Integer(in.nextLine());
//    		System.out.println(num_beads);
    		String edges[] = new String[num_beads];
    		for ( int m = 0 ; m < num_beads ; ++m ) {
    			edges[m] = in.nextLine();
    			
    		}
    		populateGraph(edges);
    		System.out.println("Case #"+(k+1));
    		findEulerianCycle1(graph);
    		if ( k+1 != num_test_cases ) {
    			System.out.println();
    		}
    	}
	}
    
	private static void print(int[] degree) {
		System.out.println("Printing the contents of an integer array");
		for ( int k = 0 ; k < degree.length ; ++k ) {
			System.out.print(degree[k]+" ");
		}
		System.out.println();
	}
	
	public static void print ( int matrix[][] ) {
		System.out.println("Printing the contents of an integer matrix");
		for ( int i = 0 ; i < matrix.length ; ++i ) {
			for ( int k = 0 ; k < matrix[i].length ; ++k ) {
				System.out.print(matrix[i][k]+" ");
			}
			System.out.println();
		}
	}

}

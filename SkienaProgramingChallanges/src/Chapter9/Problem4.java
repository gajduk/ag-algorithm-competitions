package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem4 {
	
	public static String maze[];
	public static int num_vertices;
	public static boolean[] is_visited;
	public static int num_cucles;
	
	public static boolean isValidVertex ( int id ) {
		return ( id >= 0 ) && ( id < num_vertices );
	}
	
	public static int[] getAdjacentVertices ( int vertex ) {
		int counter = 0;
		boolean n,w,s,e;
		n = w = s = e = false;
		//for north
		if ( vertex % (maze[0].length()*2-1) != maze[0].length()-1  && vertex / (maze[0].length()-1) != 0 ) {
				int row = vertex/(maze[0].length()*2-1);
				int index = (vertex % (maze[0].length()*2-1));
				if ( index >= (maze[0].length()-1) ) {
					//row++;
					index -= maze[0].length()-1;
				}
				else { 
					//index = vertex % (maze[0].length()*2-1)%(maze[0].length()-1)+1;
				}
				if ( maze[row].charAt(index) == '\\'  ) {
					n = true;
					++counter;
				}
		}
		//for south
		if ( vertex % (maze[0].length()*2-1) != (maze[0].length()*2-1-1)  && (vertex) / (maze[0].length()*2-1) != maze.length-1 ) {
					int row = vertex/(maze[0].length()*2-1);
					int index = (vertex % (maze[0].length()*2-1));
					if ( index >= (maze[0].length()-1) ) {
						index -= maze[0].length()-1;
						++row;
					}
					else { 
						++index;
					}
					if ( maze[row].charAt(index) == '\\'  ) {
						s = true;
						++counter;
					}
		}
		//for west
		if ( vertex % (maze[0].length()*2-1) != maze[0].length()-1  && (vertex) / (maze[0].length()*2-1) != maze.length-1 ) {
			int row = vertex/(maze[0].length()*2-1);
			int index = (vertex % (maze[0].length()*2-1));
			if ( index >= (maze[0].length()-1) ) {
				row++;
				index -= maze[0].length()-1;
			}
			else { 
				//index = vertex % (maze[0].length()*2-1)%(maze[0].length()-1)+1;
			}
			if ( maze[row].charAt(index) == '/'  ) {
				w = true;
				++counter;
			}
		}
		//for east
		if ( vertex % (maze[0].length()*2-1) != (maze[0].length()*2-1-1)  && vertex / (maze[0].length()-1) != 0 ) {
				int row = vertex/(maze[0].length()*2-1);
				int index = (vertex % (maze[0].length()*2-1));
				if ( index >= (maze[0].length()-1) ) {
					index -= maze[0].length()-1;
				}
				else { 
					++index;
				}
				if ( maze[row].charAt(index) == '/'  ) {
					e = true;
					++counter;
				}
		}
		int result[] = new int[counter];
		counter = 0;
		if ( n ) {
			if ( isValidVertex(vertex-maze[0].length()) )
				result[counter++] = vertex-maze[0].length();
		}
		if ( e ) {
			if ( isValidVertex(vertex-maze[0].length()+1) )
				result[counter++] = vertex-maze[0].length()+1;
		}
		if ( s ) {
			if ( isValidVertex(vertex+maze[0].length()) )
				result[counter++] = vertex+maze[0].length();
		}
		if ( w ) {
			if ( isValidVertex(vertex+maze[0].length()-1) )
				result[counter++] = vertex+maze[0].length()-1;
		}
		//System.out.println(counter);
		return result;
	}
	
	public static void main ( String args[] ) {
	//	test_getAdjacentVertices();
	//	test_findRoute();
	//	test_file();
		test_judge();
	}
	
	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int test_case_id = 1;
    	while ( true ) {
    		int m = in.nextInt();
    		int n = in.nextInt();
    		if ( m == 0 && n == 0 ) 
    			break;
    		in.nextLine();
    		maze = new String[n];
    		for ( int k = 0 ; k < maze.length ; ++k ) {
    			maze[k] = in.nextLine();
    		}
    		int result = findCycleMaxLength();
    		System.out.println("Maze #"+test_case_id+":");
    		++test_case_id;
    		if ( result <= 0 ) {
    			System.out.println("There are no cycles.");
    		}
    		else {
    			System.out.println(num_cucles+" Cycles; the longest has length "+result+".");
    		}
    		System.out.println();
    	}
	}

	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem4.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int test_case_id = 1;
    	while ( true ) {
    		int m = in.nextInt();
    		int n = in.nextInt();
    		if ( m == 0 && n == 0 ) 
    			break;
    		in.nextLine();
    		maze = new String[n];
    		for ( int k = 0 ; k < maze.length ; ++k ) {
    			maze[k] = in.nextLine();
    		}
    		int result = findCycleMaxLength();
    		System.out.println("Maze #"+test_case_id+":");
    		++test_case_id;
    		if ( result <= 0 ) {
    			System.out.println("There are no cycles.");
    		}
    		else {
    			System.out.println(num_cucles+" Cycles; the longest has length "+result+".");
    		}
    		System.out.println();
    	}
	}
	
	public static void test_findRoute() {
		maze = new String[4];
		maze[0] = "\\//\\\\/";
		maze[1] = "\\///\\/";
		maze[2] = "//\\\\/\\";
		maze[3] = "\\/\\///";
		/*
		num_vertices = 38;
		is_visited = new boolean[num_vertices];
		for ( int k = 0 ; k < num_vertices ; ++k ) {
			if ( ! is_visited[k] ) {
				System.out.println(findRoute(k));
			}
		}
		*/
		System.out.println(findCycleMaxLength());
	}
	
	public static int findCycleMaxLength () {
		num_vertices = maze.length*(maze[0].length()-1)+(maze.length-1)*maze[0].length();
		is_visited = new boolean[num_vertices];
		int max = -1;
		num_cucles = 0;
		for ( int k = 0 ; k < num_vertices ; ++k ) {
			if ( ! is_visited[k] ) {
				int temp = findRoute(k);
				if ( temp > max )
					max = temp;
			}
		}
		return max;
	}

	public static int findRoute ( int start_vertex_id ) {
		is_visited[start_vertex_id] = true;
		return findRouteRecursive(start_vertex_id,-1)+1;
	}

	public static int findRouteRecursive(int current_vertex, int previous_vertex ) {
			//System.out.println(current_vertex);
			int adjacent_vertices[] = getAdjacentVertices(current_vertex);
			//print(adjacent_vertices);
			for ( int k = 0 ; k < adjacent_vertices.length ; ++k ) {
				if ( ! is_visited[adjacent_vertices[k]] ) {
					is_visited[adjacent_vertices[k]] = true;
					return findRouteRecursive(adjacent_vertices[k],current_vertex)+1;
				}
			}
			if ( adjacent_vertices.length < 2 ) {
			//	System.out.println("dead end");
				return -(num_vertices+1);
			}
			else {
			//	System.out.println("end of cycle");
				num_cucles++;
				return 0;
			}
	}

	public static void test_getAdjacentVertices() {
		/*
	    maze = new String[3];
		maze[0] = "///";
		maze[1] = "\\//";
		maze[2] = "\\\\\\";
		\//\\/
		\///\/
		//\\/\
		\/\///
		*/
		maze = new String[4];
		maze[0] = "\\//\\\\/";
		maze[1] = "\\///\\/";
		maze[2] = "//\\\\/\\";
		maze[3] = "\\/\\///";
		num_vertices = 38;
		for ( int k = 0 ; k < num_vertices ; ++k ) {
			print(getAdjacentVertices(k));
		}
	}

	public static void print(int[] adjacentVertices) {
		if ( adjacentVertices.length == 0 )
			System.out.print("No vertices are adjacent to this one.");
		for (int i = 0; i < adjacentVertices.length; i++) {
			System.out.print(adjacentVertices[i]+" ");
		}
		System.out.println();
	}
	
	
}

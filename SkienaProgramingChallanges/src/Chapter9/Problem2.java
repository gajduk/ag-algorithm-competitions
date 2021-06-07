package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem2 {
	
	public static final int MAX = 10000; 
//	public static boolean graph[][] = new boolean[MAX][MAX];
	public static boolean is_forbidden[] = new boolean[MAX];
	public static boolean is_visited[] = new boolean[MAX];
	
	public static char turnClockwise ( char digit ) {
		if ( digit == '0' ) 
			return '1';
		if ( digit == '9' ) {
			return '0';
		}
		return (char) (digit+1);
	}
	
	public static char turnCounterClockwise ( char digit ) {
		if ( digit == '0' ) 
			return '9';
		if ( digit == '1' ) {
			return '0';
		}
		return (char) (digit-1);
	}
	
	public static int[] wheelTurn ( int original_number ) {
		int result[] = new int[8];
		String s_original_number = Integer.toString(original_number);
		char c_original_number[] = new char[4];
		int i = 0;
		while ( i < 4-s_original_number.length() ) {
			c_original_number[i++] = '0';
		}
		int s_i = 0;
		while ( i < 4 ) {
			c_original_number[i++] = s_original_number.charAt(s_i++);
		}
		//System.out.println(c_original_number.length);
		char temp[] = new char[4];

		
		// digit on ones changed
		copy(temp,c_original_number);
		temp[3] = turnClockwise(temp[3]); 
		result[0] = new Integer(new String(temp));
		copy(temp,c_original_number);
		temp[3] = turnCounterClockwise(temp[3]); 
		result[1] = new Integer(new String(temp));
		// digit on tens changed
		copy(temp,c_original_number);
		temp[2] = turnClockwise(temp[2]); 
		result[2] = new Integer(new String(temp));
		copy(temp,c_original_number);
		temp[2] = turnCounterClockwise(temp[2]); 
		result[3] = new Integer(new String(temp));
		// digit on hundreds changed
		copy(temp,c_original_number);
		temp[1] = turnClockwise(temp[1]); 
		result[4] = new Integer(new String(temp));
		copy(temp,c_original_number);
		temp[1] = turnCounterClockwise(temp[1]); 
		result[5] = new Integer(new String(temp));
		// digit on thousands changed
		copy(temp,c_original_number);
		temp[0] = turnClockwise(temp[0]); 
		result[6] = new Integer(new String(temp));
		copy(temp,c_original_number);
		temp[0] = turnCounterClockwise(temp[0]); 
		result[7] = new Integer(new String(temp));
		return result;
	}
	
	
	
	
	
	public static void copy(char[] copy, char[] original ) {
		for ( int m = 0 ; m < copy.length ; ++m ) {
			copy[m] = original[m];
		}
	}

	public static void populateGraph ( String forbidden[] ) {
		/*
		 for ( int i = 0 ; i < MAX ; ++i ) {
		 	for ( int k = 0 ; k < MAX ; ++k ) {
				graph[i][k] = false;
			}
		}
		for ( int i = 0 ; i < MAX ; ++i ) {
			int neighbours[] = wheelTurn(i);
			for ( int k = 0 ; k < neighbours.length ; ++k ) {
				graph[i][neighbours[k]] = true;
				graph[neighbours[k]][i] = true;
			}
		}
		for ( int i = 0 ; i < forbiden.length ; ++i ) {
			String forbiden_no_blanks = forbiden[i].replace(" ","");
			int forbiden_vertex =  new Integer(forbiden_no_blanks);
			int neighbours[] = wheelTurn(forbiden_vertex);
			for ( int k = 0 ; k < neighbours.length ; ++k ) {
				graph[forbiden_vertex][neighbours[k]] = false;
				graph[neighbours[k]][forbiden_vertex] = false;
			}
		}
		*/
		for ( int i = 0 ; i < forbidden.length ; ++i ) {
			String forbiden_no_blanks = forbidden[i].replace(" ","");
			int forbiden_vertex =  new Integer(forbiden_no_blanks);
			is_forbidden[forbiden_vertex] = true;
		}
	}
	
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		in.nextLine();
    		String s_start_vertex = in.nextLine();
    		String s_end_vertex = in.nextLine();
    		int num_forbidden = new Integer(in.nextLine());
    		String forbidden[] = new String[num_forbidden];
    		for ( int m = 0 ; m < num_forbidden ; ++m ) {
    			forbidden[m] = in.nextLine();
    		}
    		populateGraph(forbidden);
    		String start_vertex_no_blanks = s_start_vertex.replace(" ","");
    		String end_vertex_no_blanks = s_end_vertex.replace(" ","");
    		int start_vertex = new Integer(start_vertex_no_blanks);
    		int end_vertex = new Integer(end_vertex_no_blanks);
    		System.out.println(traverseGraph(start_vertex, end_vertex));
    		cleanAllData();
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem2.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		in.nextLine();
    		String s_start_vertex = in.nextLine();
    		String s_end_vertex = in.nextLine();
    		int num_forbidden = new Integer(in.nextLine());
    		String forbidden[] = new String[num_forbidden];
    		for ( int m = 0 ; m < num_forbidden ; ++m ) {
    			forbidden[m] = in.nextLine();
    		}
    		populateGraph(forbidden);
    		String start_vertex_no_blanks = s_start_vertex.replace(" ","");
    		String end_vertex_no_blanks = s_end_vertex.replace(" ","");
    		int start_vertex = new Integer(start_vertex_no_blanks);
    		int end_vertex = new Integer(end_vertex_no_blanks);
    		System.out.println(traverseGraph(start_vertex, end_vertex));
    		cleanAllData();
    	}
	}
	
	public static void cleanAllData ( ) {
		is_forbidden = new boolean[MAX];
		is_visited = new boolean[MAX];
	}
	
	public static int traverseGraph ( int start_vertex , int end_vertex ) {
		//boolean visited[] = new boolean[graph.length];
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int level = 0;
		int border;
		int rear;
		int front;
		queue.add(start_vertex);
		//visited[start_vertex] = true;
		is_visited[start_vertex] = true;
		rear = 0;
		front = 1;
		border = front;	
		while ( ! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			++rear;
			//System.out.println(current_vertex);
			if ( current_vertex == end_vertex )
				return level;
			int neighbours[] = wheelTurn(current_vertex);
			for ( int m = 0 ; m < neighbours.length ; ++m ) {
				if ( ! is_forbidden[neighbours[m]] && ! is_visited[neighbours[m]] ) {
					queue.add(neighbours[m]);
					is_visited[neighbours[m]] = true;
					++front;
				}
			}
			if ( border == rear ) {
				border = front;
				++level;
			}
		}
		return -1;
	}
	
	public static void main ( String args[] ) {
		//test_wheelTurn();
		test_file();
		//test_judge();
	}
	
	public static void print ( int array[] ) {
		for ( int k = 0 ; k < array.length ; ++k ) {
			System.out.print(array[k]+" ");
		}
		System.out.println();
	}
	
	public static void print ( char array[] ) {
		for ( int k = 0 ; k < array.length ; ++k ) {
			System.out.print(array[k]+" ");
		}
		System.out.println();
	}

	private static void test_wheelTurn() {
		print(wheelTurn(0));
		print(wheelTurn(8056));
	}

}

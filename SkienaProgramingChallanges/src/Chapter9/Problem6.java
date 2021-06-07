package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem6 {
	
	public static int num_cubes;
	public static boolean graph[][];
	public static ArrayList<Integer> neighbours[];
	public static int in_degree[];
	public static int cubes[][];
	public static final int NUM_CUBE_SIDES = 6;
	public static boolean is_visited[];
	public static int max_tower_size;
	public static int max_start_cube_id;	
	public static int max_start_cube_side;
	public static int list[];
	
	public static void printGraph () {
		for ( int k = 0 ; k < graph.length ; ++k ) {
			for ( int t = 0 ; t < graph.length ; ++t ) {
				System.out.print(graph[k][t]?"1":"0");
			}
			System.out.println();
		}
	}
	
	public static void print( boolean a[]) {
		for ( int k = 0 ; k < a.length ; ++k ) {
			System.out.print(a[k]?"1":"0");
		}
		System.out.println();
	}
	
	public static int getColorOfOppositeSide ( int cube_id , int cuurent_side ) {
		return cuurent_side % 2 == 0 ? cubes[cube_id][cuurent_side+1] : cubes[cube_id][cuurent_side-1]; 
	}
	
	public static int getOppositeSide ( int cube_id , int cuurent_side ) {
		return cuurent_side % 2 == 0 ? cube_id*6+cuurent_side+1 : cube_id*6+cuurent_side-1; 
	}
	
	public static void recostructTower () {
		int current_vertex = max_start_cube_id*NUM_CUBE_SIDES + max_start_cube_side;
		while ( current_vertex != -1 ) {
			System.out.println(current_vertex/NUM_CUBE_SIDES+" "+getIdentificationString(current_vertex%NUM_CUBE_SIDES));
			current_vertex = list[current_vertex/NUM_CUBE_SIDES];
		}
	}
		
		/*	
	public static void recostructTower () {
		ArrayList<Integer> queue = new ArrayList<Integer>();
		boolean is_visited1[] = new boolean[num_cubes*NUM_CUBE_SIDES];
		int levels[] = new int[num_cubes*NUM_CUBE_SIDES];
		queue.add(max_start_cube_id*NUM_CUBE_SIDES+max_start_cube_side);
		is_visited1[queue.get(0)] = true;
		prev[queue.get(0)] = -1;
		int level = 1;
		int front = 1;
		int rear = 0;
		int border = front;
		while ( ! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			levels[current_vertex] = level;
			rear++;
			for ( int k = ((current_vertex/NUM_CUBE_SIDES)+1)*NUM_CUBE_SIDES ; k < graph.length ; ++k ) {
				if ( graph[current_vertex][k] ) {
					is_visited1[k] = true;
					prev[getOppositeSide(k/NUM_CUBE_SIDES, k%NUM_CUBE_SIDES)] = current_vertex;
					queue.add(getOppositeSide(k/NUM_CUBE_SIDES, k%NUM_CUBE_SIDES));
					front++;
				}
			}
			if ( border == rear ) {
				level++;
				border = front;
			}
		}
		int max = -1;
		int max_index = -1;
		for ( int m = 0 ; m < num_cubes*NUM_CUBE_SIDES ; ++m ) {
			if ( levels[m] > max ) {
				max_index = m;
				max = levels[m];
			}
		}
		Stack<String> result = new Stack<String>();
		while ( max_index != -1 ) {
			result.push(max_index/NUM_CUBE_SIDES+1+" "+getIdentificationString(max_index%NUM_CUBE_SIDES));
			max_index = prev[max_index];
		}
		while ( ! result.isEmpty() ) {
			System.out.println(result.pop());			
		}
	}
	*/
	
	public static String getIdentificationString ( int id ) {
		if ( id == 1 ) {
			return "back";
		}
		if ( id == 2 ) {
			return "left";
		}
		if ( id == 3 ) {
			return "right";
		}
		if ( id == 4 ) {
			return "top";
		}
		if ( id == 5 ) {
			return "bottom";
		}
		if ( id == 0 ) {
			return "front";
		}
		return "";
	}
	
	public static void populateGraph ( ) {
		neighbours = new ArrayList[num_cubes*NUM_CUBE_SIDES];
		for ( int k = 0 ; k < neighbours.length ; ++k ) {
			neighbours[k] = new ArrayList<Integer>(); 
		}
		//graph = new boolean[num_cubes*NUM_CUBE_SIDES][num_cubes*NUM_CUBE_SIDES];
		for ( int k = 0 ; k < cubes.length ; ++k ) {
			for ( int f = 0 ; f < NUM_CUBE_SIDES ; ++f ) {
				for ( int i = k+1 ; i < cubes.length ; ++i ) {
					for ( int m = 0 ; m < NUM_CUBE_SIDES ; ++m ) {
						if ( cubes[k][f] == cubes[i][m] ) {
							//graph[k*NUM_CUBE_SIDES+f][i*NUM_CUBE_SIDES+m] = true;
							neighbours[k*NUM_CUBE_SIDES+f].add(getOppositeSide(i,m));
						}
					}
				}
			}
		}
		//printNeigbours();
	}
	
	private static void printNeigbours() {
		for ( int i = 0 ; i < neighbours.length ; ++i ) {
			System.out.print(i+":");
			for (Integer k : neighbours[i] ) {
				System.out.print(k+" ");				
			}
			System.out.println();
		}
	}

	public static void makeTowers () {
		list = new int[num_cubes];
		for ( int k = 0 ; k < list.length ; ++k ) {
			list[k] = -1;
		}
		max_tower_size = 0;
		max_start_cube_id = 0;
		max_start_cube_side = 0;
		in_degree = new int[num_cubes*NUM_CUBE_SIDES];
		for ( int i = 0 ; i < num_cubes*NUM_CUBE_SIDES-max_tower_size*NUM_CUBE_SIDES ; ++i ) {
				is_visited = new boolean[num_cubes*NUM_CUBE_SIDES];
				int temp = traverseGraphInDepth(i);
				if ( temp > max_tower_size ) {
					max_tower_size = temp;
					max_start_cube_id = i/NUM_CUBE_SIDES;
					max_start_cube_side = i%NUM_CUBE_SIDES;
				}
			
		}
		System.out.println(max_tower_size);
	}

	public static int traverseGraphInDepth ( int start_vertex ) {
		is_visited[start_vertex] = true;
		return traverseGraphInDepthRecursive(start_vertex);
	}
	
	public static int traverseGraphInDepthRecursive( int current_vertex ) {
		//print(is_visited);
		int max = 0;
		int max_cube = -1;
		for ( int k : neighbours[current_vertex] ) {
			if ( ! is_visited[k] ) {
				is_visited[k] = true;
				int temp = traverseGraphInDepthRecursive(getOppositeSide(k/NUM_CUBE_SIDES, k%NUM_CUBE_SIDES)); 
				if ( temp > max ) {
					max = temp;
					max_cube = k; 
				}
			}
		}
		list[current_vertex/NUM_CUBE_SIDES] = max_cube;
		return max+1;
	}
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem6.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	boolean flag = true;
    	int test_case_id = 1;
    	while ( true ) {
    		num_cubes = new Integer(in.nextLine());
    		if ( num_cubes == 0 ) {
    			break;
    		}
    		
    		cubes = new int[num_cubes][NUM_CUBE_SIDES];
    		for ( int k = 0 ; k < num_cubes ; ++k ) {
    			StringTokenizer side_colors = new StringTokenizer(in.nextLine());
    			for ( int m = 0 ; m < NUM_CUBE_SIDES ; ++m ) {
    				cubes[k][m] = new Integer(side_colors.nextToken());
    			}
    		}
    		if ( ! flag ) {
				System.out.println();
			}
    		flag = false;
    		System.out.println("Case #"+test_case_id++);
    		populateGraph();
    		findLongestPath();
    		//FloyedWarshall();
    		//printGraph();
    		//makeTowers();
    		//recostructTower();
    	}
	}
	
	public static void test_judge()  {
		Scanner in = new Scanner(System.in);
    	boolean flag = true;
    	int test_case_id = 1;
    	while ( true ) {
    		num_cubes = new Integer(in.nextLine());
    		if ( num_cubes == 0 ) {
    			break;
    		}
    		
    		cubes = new int[num_cubes][NUM_CUBE_SIDES];
    		for ( int k = 0 ; k < num_cubes ; ++k ) {
    			StringTokenizer side_colors = new StringTokenizer(in.nextLine());
    			for ( int m = 0 ; m < NUM_CUBE_SIDES ; ++m ) {
    				cubes[k][m] = new Integer(side_colors.nextToken());
    			}
    		}
    		if ( ! flag ) {
				System.out.println();
			}
    		flag = false;
    		System.out.println("Case #"+test_case_id++);
    		populateGraph();
    		findLongestPath();
    	}
	}
	
	public static void FloyedWarshall()	{
		int graph1[][] = new int[num_cubes*NUM_CUBE_SIDES][num_cubes*NUM_CUBE_SIDES];
		for ( int k = 0 ; k < cubes.length ; ++k ) {
			for ( int i = k+1 ; i < cubes.length ; ++i ) {
				for ( int f = 0 ; f < NUM_CUBE_SIDES ; ++f ) {
					for ( int m = 0 ; m < NUM_CUBE_SIDES ; ++m ) {
						if ( cubes[k][f] == cubes[i][m] ) {
							graph1[k*NUM_CUBE_SIDES+f][i*NUM_CUBE_SIDES+m] = 1;
							//neighbours[k*NUM_CUBE_SIDES+f].add(i*NUM_CUBE_SIDES+m);
						}
					}
				}
			}
		}
		for(int i = 1; i <= NUM_CUBE_SIDES; i++)
	    	 graph1[i][i] = 0;
	    int max = 0;
	    for(int k = 1; k <= NUM_CUBE_SIDES; k++) {
	    	for(int i = 1; i <= NUM_CUBE_SIDES; i++) {
	             for (int j = 1; j <= NUM_CUBE_SIDES; j++) {
	            	 graph1[i][j] = max(graph1[i][j], graph1[i][k]+ graph1[k][j]);
	             }
	    	}
	    }
	    for(int k = 1; k <= NUM_CUBE_SIDES; k++) {
	    	for(int i = 1; i <= NUM_CUBE_SIDES; i++) {
	    		if ( graph1[k][i] > max ) {
	    			max = graph1[k][i];
	    		}
	    	}
	    }
	    System.out.println(max);
	}
	
	public static int max(int a, int b) {
		if ( a > b )
			return a;
		return b;
	}
	
	public static int min ( int number1 , int number2 ) {
		if ( number1 > number2 )
			return number2;
		return number1;
	}

	public static int findLongestPath () {
		int length_to[] = new int[neighbours.length];
		int prev[] =new int[num_cubes*NUM_CUBE_SIDES];
		for ( int k = 0 ; k < neighbours.length ; ++k ) {
			prev[k] = -1;
		}
		for ( int k = 0 ; k < neighbours.length ; ++k ) {
			for ( Integer m : neighbours[k] ) {
				if ( length_to[m] <= length_to[k]+1 ) {
					 length_to[m] = length_to[k]+1;
					 prev[m] = k;
				}
			}
		}
		int max_length = 0;
		int max_vertex = 0;
		for ( int k = 0 ; k < neighbours.length ; ++k ) {
			if ( length_to[k] > max_length ) {
				max_length = length_to[k];
				max_vertex = k;
			}
		}
		//System.out.println(max_vertex/6);
		System.out.println(max_length+1);
		printPath(-1,max_vertex,prev);
		return max_length+1;
	}

	private static void printPath(int start, int end, int[] prev) {
		if ( start == end ) {
			return;
		}
		printPath(start,prev[end],prev);
		//if ( prev[end] != -1 )
			end = getOppositeSide(end/NUM_CUBE_SIDES, end%NUM_CUBE_SIDES);
		System.out.println(end/NUM_CUBE_SIDES+1+" "+getIdentificationString(end%NUM_CUBE_SIDES));
	}

}

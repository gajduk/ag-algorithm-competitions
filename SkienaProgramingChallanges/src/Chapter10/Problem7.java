package Chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


class Element implements Comparable {
	public int vertex;
	public int value;
	
	public Element( int vertex , int value) {
		this.vertex = vertex;
		this.value = value;
	}

	@Override
	public int compareTo(Object a) {
		Element temp = (Element) a;
		if ( temp.value > value ) return 1;
		return -1;
	}
	
	public String toString() {
		return /*"Vertex:"+*/vertex+" "+value;
	}

}


public class Problem7 {
	
	public static int m;
	public static int n;

	public static void main ( String args[] ) throws Exception {
		test_file();
		test_judge();
	}
	
	public static void test_file () throws Exception {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem7.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		m = new Integer(numbers.nextToken());
    		n = new Integer(numbers.nextToken());
    		if ( m == 0 && n == 0 ) {
    			break;
    		}
    		makeSeatingArrangement(in.nextLine(),in.nextLine());	
    	}
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
		while ( true ) {
    		StringTokenizer numbers = new StringTokenizer(in.nextLine());
    		m = new Integer(numbers.nextToken());
    		n = new Integer(numbers.nextToken());
    		if ( m == 0 && n == 0 ) {
    			break;
    		}
    		makeSeatingArrangement(in.nextLine(),in.nextLine());	
    	}
	}

	private static void makeSeatingArrangement(String s_teams, String s_tables) {
		int graph[][] = new int[2+m+n][2+m+n];	
		LinkedList neighbours[] = new LinkedList[2+m+n]; 
		for ( int i = 0 ; i < neighbours.length ; ++i ) {
			neighbours[i] = new LinkedList<Element>();
		}
		StringTokenizer teams = new StringTokenizer(s_teams);
		StringTokenizer tables = new StringTokenizer(s_tables);
		int total_num_people = 0;
		for ( int i = 0 ; i < m ; ++i ) {
//			graph[0][i+1] = new Integer(teams.nextToken());
			int num_people = Integer.parseInt(teams.nextToken());
			neighbours[0].add(new Element(i+1,num_people));
			total_num_people += num_people;
		}
		for ( int i = 0 ; i < n ; ++i ) {
			int t = new Integer(tables.nextToken());
			neighbours[i+m+1].add(new Element(m+n+1,t));
		}
		for ( int i = 0 ; i < m ; ++i ) {
			for ( int j = 0 ; j < n ; ++j ) {
//				graph[i+1][j+m+1] = 1;
				neighbours[i+1].add(new Element(j+m+1,1));
			}
		}
		//MaximumFlow.print(graph);
		int max_flow = maximumFlow(neighbours, 0, m+n+1);
		if ( max_flow == total_num_people ) {
			System.out.println("1");
			ArrayList<Integer> tables_count[] = new ArrayList[m];
			for ( int i = 0 ; i < m ; ++i ) {
				tables_count[i] = new ArrayList<Integer>();
			}
			for ( int j = 0 ; j < n ; ++j ) {
				for ( Object o : neighbours[j+m+1] ) {
					Element e = (Element) o;
					if ( e.vertex > 0 && e.vertex < m+1 ) {
						tables_count[e.vertex-1].add(j);
					}
				}
			}
			for ( int i = 0 ; i < m ; ++i ) {
				Collections.sort(tables_count[i],new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1-o2;
					}
					
				});
				String result = "";
				for ( Integer id : tables_count[i] ) {
					result = result+" "+(id+1);
				}
				System.out.println(result.substring(1));
			}
			
		}
		else {
			System.out.println("0");
		}
	}
	
	public static int max_flow = 0;

	private static int maximumFlow(LinkedList[] neighbours, int start , int end ) {
		int result = 0;
//		System.out.println(Arrays.toString(neighbours));
		while ( maximumFlowPathProcces(neighbours,start,end) ) {
			result += max_flow;
//			System.out.println(Arrays.toString(neighbours));
//			System.out.println(result);
		}
		return result;
	}

	public static boolean maximumFlowPathProcces(LinkedList[] neighbours,	int start, int end) {
		boolean is_visited[] = new boolean[neighbours.length];
		int prev[] = new int[neighbours.length];
		int max_flow_t[] = new int[neighbours.length];
		prev[start] = -1;
		max_flow_t[start] = Integer.MAX_VALUE;
		rucna = false;
		boolean result =  dfs(neighbours,start,end,is_visited,prev,max_flow_t);
		if ( rucna ) {
			max_flow = max_flow_t[end];
			while ( prev[end] != -1 ) {
				int temp = prev[end];
				for ( Object i : neighbours[temp] ) {
					Element e = (Element) i;
					if ( e.vertex == end ) {
						if ( e.value == max_flow ) {
							neighbours[temp].remove(i);
							
						}
						else {
							e.value -= max_flow;
						}
						boolean flag = false;
						for ( Object ii : neighbours[end] ) {
							Element ee = (Element) ii;
							if ( ee.vertex == temp ) {
								ee.value += max_flow;
								flag = true;
								break;
							}
						}
						if ( ! flag ) {
							neighbours[end].add(new Element(temp,max_flow));
						}
						break;
					}
				}
				end = temp;
			}
		}
		return rucna;
	}
	
	public static boolean rucna = false;

	private static boolean dfs(LinkedList[] neighbours, int current, int end,	boolean[] is_visited, int[] prev , int max_flow_t[] ) {
//		System.out.println(current);
		is_visited[current] = true;
		if ( end == current ) {
			rucna = true;
			return true;
		}
		for ( Object e : neighbours[current] ) {
			if ( rucna ) {
				return true;
			}
			Element ee = (Element) e;
			if ( ! is_visited[ee.vertex] ) {
				prev[ee.vertex] = current;
				max_flow_t[ee.vertex] = Math.min(max_flow_t[current],ee.value);
				dfs(neighbours,ee.vertex,end,is_visited,prev,max_flow_t);
			}
		}
		return false;
	}

	
	
}

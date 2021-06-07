package Chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Problem8 {

	public static int num_categories;
	public static int num_problems;
	
	public static void main ( String args [] ) throws Exception {
//		test_file();
		test_judge();
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			StringTokenizer numbers = new StringTokenizer(in.nextLine());
			num_categories = new Integer(numbers.nextToken());
			num_problems = new Integer(numbers.nextToken());
			if ( num_categories == 0 && num_problems == 0 ) {
				break;
			}
			int categories[] = new int[num_categories];
			StringTokenizer tkr_categories = new StringTokenizer(in.nextLine());
			for ( int i = 0 ; i < categories.length ; ++i ) {
				categories[i] = new Integer(tkr_categories.nextToken());
			}
			int problems[][] = new int[num_problems][];
			for ( int i = 0 ; i < problems.length ; ++i ) {
				StringTokenizer tkr_problem = new StringTokenizer(in.nextLine());
				problems[i] = new int[new Integer(tkr_problem.nextToken())];
				for ( int w = 0 ; w < problems[i].length ; ++w ) {
					problems[i][w] = new Integer(tkr_problem.nextToken());
				}
			}
			makeProblemArrangement(categories,problems);
		}
	}
	
	public static void test_file () throws Exception {
			FileInputStream inputFile = null;
		    try {
		    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem8.txt");
		    }
			catch (FileNotFoundException e) {
		      e.printStackTrace(System.err);
		      return;
		    }
			Scanner in = new Scanner(inputFile);
			while ( true ) {
				StringTokenizer numbers = new StringTokenizer(in.nextLine());
				num_categories = new Integer(numbers.nextToken());
				num_problems = new Integer(numbers.nextToken());
				if ( num_categories == 0 && num_problems == 0 ) {
					break;
				}
				int categories[] = new int[num_categories];
				StringTokenizer tkr_categories = new StringTokenizer(in.nextLine());
				for ( int i = 0 ; i < categories.length ; ++i ) {
					categories[i] = new Integer(tkr_categories.nextToken());
				}
				int problems[][] = new int[num_problems][];
				for ( int i = 0 ; i < problems.length ; ++i ) {
					StringTokenizer tkr_problem = new StringTokenizer(in.nextLine());
					problems[i] = new int[new Integer(tkr_problem.nextToken())];
					for ( int w = 0 ; w < problems[i].length ; ++w ) {
						problems[i][w] = new Integer(tkr_problem.nextToken());
					}
				}
				makeProblemArrangement(categories,problems);
			}
			
	}

	public static void makeProblemArrangement(int[] categories,	int[][] problems) {
		int graph[][] = new int[2+num_categories+num_problems][2+num_categories+num_problems];
//		int graph[][] = new int[2+m+n][2+m+n];	
		LinkedList neighbours[] = new LinkedList[2+num_categories+num_problems]; 
		for ( int i = 0 ; i < neighbours.length ; ++i ) {
			neighbours[i] = new LinkedList<Element>();
		}
		int total_num_problems_required = 0;
		for ( int i = 0 ; i < categories.length ; ++i ) {
//			graph[0][i+1] = categories[i];
			
			neighbours[0].add(new Element(i+1,categories[i]));
			
			total_num_problems_required += categories[i];
		}
		for ( int i = 0 ; i < problems.length ; ++i ) {
//			graph[i+num_categories+1][num_categories+num_problems+1] = 1;
			neighbours[i+num_categories+1].add(new Element(num_categories+num_problems+1,1));
			
			
			for ( int k = 0 ; k < problems[i].length ; ++k ) {
				neighbours[problems[i][k]].add(new Element(i+num_categories+1,1));
				
//				graph[problems[i][k]][i+num_categories+1] = 1;
			}
		}
		//MaximumFlow.print(graph);
		int max_flow = maximumFlow(neighbours, 0, num_categories+num_problems+1);
		if ( max_flow == total_num_problems_required ) {
			System.out.println("1");
			ArrayList<Integer> tables_count[] = new ArrayList[num_categories];
			for ( int i = 0 ; i < num_categories ; ++i ) {
				tables_count[i] = new ArrayList<Integer>();
			}
			for ( int j = 0 ; j < num_problems ; ++j ) {
				for ( Object o : neighbours[j+num_categories+1] ) {
					Element e = (Element) o;
					if ( e.vertex > 0 && e.vertex < num_categories+1 ) {
						tables_count[e.vertex-1].add(j);
					}
				}
			}
			for ( int i = 0 ; i < num_categories ; ++i ) {
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

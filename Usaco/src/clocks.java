/*
ID: gajduk_01
LANG: JAVA
TASK: clocks
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class clocks {

	public static int best = 1000;
	public static ArrayList<String> results;
	public static String[] moves = {    "0134",
										"012",
										"1245",
										"036",
										"13457",
										"258",
										"3467",
										"678",
										"4578"  };
	
	public static void executeMove ( int move_id , int map[] ) {
		String move = moves[move_id];
		for (int i = 0; i < move.length() ; i++) {
			int clock_id = new Integer(moves[move_id].substring(i, i+1));
			map[clock_id] += 3;
			map[clock_id] %= 12;
		}
	}
	
	public static void test_executeMove() {
		int m[] = {9,9,0,6,6,6,6,3,6 };
		for ( int i = 0 ; i < 9 ; ++i ) {
			executeMove(i,m);
			print(m);
			executeReverseMove(i, m);
			print(m);
		}
	}
	
	public static String findSequence ( int clocks[][] ) {
		int map[] = new int[clocks.length*clocks.length];
		for ( int i = 0 ; i < clocks.length ; ++i ) {
			for ( int j = 0 ; j < clocks.length ; ++j ) {
				map[i*3+j] = clocks[i][j]%12;
			}
		}
		//print(map);
		ArrayList<String> result = findAllSolutions(map);
		//print(result);
		String best_combo = "";
		for ( int i = 0 ; i < best ; ++i ) {
			best_combo += "z";
		}
		for (String string : result) {
			if ( string.compareTo(best_combo) < 0 ) {
				best_combo = string;
			}
		}
		String best_sequence = "";
		for ( int i = 0 ; i < best_combo.length() ; ++i ) {
			best_sequence += best_combo.charAt(i);
			if ( i < best_combo.length()-1 ) {
				best_sequence += " ";
			}
		}
		return best_sequence;	
	}
	
	public static void print(int[] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.print(map[i]);
		}
		System.out.println();
	}

	private static void print(ArrayList<String> result) {
		for (String string : result) {
			System.out.println(string);
		}
	}

	private static ArrayList<String> findAllSolutions(int[] map) {
		results = new ArrayList<String>();
		findAllSolutionsRecursive(0,"",map);
		return results;
	}

	private static void findAllSolutionsRecursive( int current_move, String combination , int map[] ) {
		if ( current_move >= 9 ) {
			return;
		}
		if ( combination.length() > best  ) {
			return;
		}
		for ( int i = 0 ; i < 4 ; ++i ) {
			for ( int j = 0 ; j < i ; ++j ) {
				executeMove(current_move, map);
				combination += (current_move+1);
			}
			if ( combination.length() < best  ) {
				if ( checkSolution(map) ) {
					results.add(combination);
					best = combination.length();
				}
				
			}
			if ( combination.length() == best  ) {
				if ( checkSolution(map) ) {
					results.add(combination);
				}
			}
			findAllSolutionsRecursive(current_move+1,combination,map);
			for ( int j = 0 ; j < i ; ++j ) {
				executeReverseMove(current_move, map);
				combination = combination.substring(0,combination.length()-1);
			}
			//System.out.println(combination);
		}
		
	}

	private static boolean checkSolution(int[] map) {
		for ( int i = 0 ; i < map.length ; ++i ) {
			if ( map[i] != 0 ) {
				return false;
			}
		}
		return true;
	}

	private static void executeReverseMove(int move_id, int[] map) {
		String move = moves[move_id];
		for (int i = 0; i < move.length() ; i++) {
			int clock_id = new Integer(moves[move_id].substring(i, i+1));
			map[clock_id] += 9;
			map[clock_id] %= 12;
		}
	}
	
	public static void main ( String args[] ) throws IOException {
	//	test_all();
		BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
	    int clocks[][] = new int[3][3];
	    for ( int i = 0 ; i < 3 ; ++i ) {
	    	StringTokenizer tkr_numbers = new StringTokenizer(f.readLine());
	    	for ( int j = 0 ; j < 3 ; ++j ) {
	    		clocks[i][j] = new Integer(tkr_numbers.nextToken());
	    	}
	    }
	    out.println(findSequence(clocks));
	    out.close();
	}

	private static void test_all() {
		int clocks[][] = { {6,9,3} , {3,3,9} , {12,12,12} };
		System.out.println(findSequence(clocks));
		
	}


}

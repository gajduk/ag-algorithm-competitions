package Chapter8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem1 {
	
	public static  int map[][];/*
	 								   = {  { 8 },
										 { 9 , 7 },
										 { 10 , 8 , 6 },
										 { 11 , 9 , 7 , 5 },
										 { 12 , 10 , 8 , 6 , 4 },
										 { 13 , 11 , 9 , 7 , 5 , 3 },
										 { 14 , 12 , 10 , 8 , 6 , 4 , 2 },
										 { 15 , 13 , 11 , 9 , 7 , 5 , 3 , 1 },
										 { 14 , 12 , 10 , 8 , 6 , 4 , 2 },
										 { 13 , 11 , 9 , 7 , 5 , 3 },
										 { 12 , 10 , 8 , 6 , 4 },
										 { 11 , 9 , 7 , 5 },
										 { 10 , 8 , 6 },
										 { 9 , 7},
										 { 8 } };
									  */
	public static boolean is_under_attack[] = new boolean[16];
	public static int num_ways = 0;
	public static int n;
	
	public static void generateMap ( int l ) {
		n = l*2-1;
		map = new int[n][];
		for ( int m = 0 ; m < map.length ; ++m ) {
			//System.out.println(m+" "+l);
			if ( m >= l ) {
				map[m] = new int[n-m];
			}
			else {
				map[m] = new int[m+1];
			}
			int s = l-map[m].length+1;
			for ( int k = 0 ; k < map[m].length ; ++k ) {
				map[m][k] = s;
				s += 2;
			}
				
		}
	//	System.out.println("Map has been generated");
	}
	
	public static void genereateAllPosibleCombinations ( int num_total_elements , int num_subset_elements ) {
		generateCombinationsRecursive (0,num_subset_elements,new LinkedList<Integer>());
	}

	private static void generateCombinationsRecursive(int current_element , int k , LinkedList<Integer> combination_recursive ) {
		for ( int i = current_element ; i < n ; ++i ) {
			if ( k == 1 ) {
				combination_recursive.add(i);
				combination = combination_recursive;
				countWays(0);
				combination_recursive.remove(combination_recursive.size()-1);
			}
			else {
				combination_recursive.add(i);
				generateCombinationsRecursive(i+1,k-1,combination_recursive);
				combination_recursive.remove(combination_recursive.size()-1);
			}
		}
	}
	
	public static void main ( String args[] ) {
		//test_all();
		test_judge();
		//test_file();
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	while ( true ) {
    		String s_numbers = in.nextLine();
    		StringTokenizer numbers = new StringTokenizer(s_numbers);
    		int board_size = new Integer(numbers.nextToken());
    		int num_bishops = new Integer(numbers.nextToken());
    		if ( board_size == 0 && num_bishops == 0 ) {
    			break;
    		}
    		System.out.println(solutions[board_size-1][num_bishops]);
    		//countAllWays(board_size,num_bishops);
    		//long start = System.currentTimeMillis();
    		//System.out.println(findAllPermutationsVer2 (board_size,num_bishops));
    		//long end = System.currentTimeMillis();
    	//	System.out.println((end-start)/1000+"."+(end-start)%1000+"sec");
    		//System.out.println(n);
    		resetAllData();    	
    	}
	}
	
	public static void test_all () {
		for ( int n = 1 ; n <= 8 ; ++n ) {
			for ( int k = 0 ; k <= 64 ; ++k ) {
				System.out.print(findAllPermutationsVer2 (n,k)+",");
			}
			System.out.println();
		}
	}
	
	public static int[][] solutions = { {   1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 } ,
										{	1,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 } ,
										{	1,9,26,26,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
										{	1,16,92,232,260,112,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
										{	1,25,240,1124,2728,3368,1960,440,32,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
										{	1,36,520,3896,16428,39680,53744,38368,12944,1600,64,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
										{	1,49,994,10894,70792,282248,692320,1022320,867328,389312,81184,5792,128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
										{	1,64,1736,26192,242856,1444928,5599888,14082528,22522960,22057472,12448832,3672448,489536,20224,256,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} };

	
	public static int findAllPermutations (int board_size, int num_bishops) {
		n = board_size;
		generateMap(board_size);
		//print(map);
		int result = findAllPermutationsRecursive(1,num_bishops/*,""*/);
		is_under_attack[map[0][0]] = true;
		
		return result+findAllPermutationsRecursive(1,num_bishops-1/*,"0 0 ; "*/);
	}
	
	public static int findAllPermutationsRecursive( int current_diagonal, int num_bishops /*, String combination */) {
		
		if ( num_bishops == 0 ) {
			//System.out.println(combination);
			return 1;
		}
		if ( current_diagonal > n-num_bishops  ) {
			//System.out.println(n);
			return 0;
		}
		int sum = 0;
		//System.out.println(is_under_attack[map[0][0]]+" "+map[0][0]);
		for ( int i = 0 ; i < map[current_diagonal].length ; ++i ) {
			
			if ( ! is_under_attack[map[current_diagonal][i]] ) {
				//int temp = combination.length();
				//combination += current_diagonal+" "+i+" ; ";
				is_under_attack[map[current_diagonal][i]] = true;
				sum += findAllPermutationsRecursive(current_diagonal+1, num_bishops-1/*,combination*/);
				is_under_attack[map[current_diagonal][i]] = false;
				//combination = combination.substring(0,temp);
			}
		}
		sum += findAllPermutationsRecursive(current_diagonal+1, num_bishops/*,combination*/);
		return sum;
	}

	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter8 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		String s_numbers = in.nextLine();
    		StringTokenizer numbers = new StringTokenizer(s_numbers);
    		int board_size = new Integer(numbers.nextToken());
    		int num_bishops = new Integer(numbers.nextToken());
    		if ( board_size == 0 && num_bishops == 0 ) {
    			break;
    		}
    		
    		//countAllWays(board_size,num_bishops);
    		long start = System.currentTimeMillis();
    		System.out.println(findAllPermutationsVer2 (board_size,num_bishops));
    		long end = System.currentTimeMillis();
    		//System.out.println((end-start)/1000+"."+(end-start)%1000+"sec");
    		//System.out.println(n);
    		resetAllData();   
    	}
	}
	
	private static void resetAllData() {
		num_ways = 0;
		is_under_attack = new boolean[16];
		map = null;
	}

	public static void countAllWays ( int board_size , int num_bishops ) {
		generateMap(board_size);
		genereateAllPosibleCombinations(n,num_bishops);
	}
	
	public static LinkedList<Integer> combination;
	 
	public static void countWays ( int index  ) {
		int g_s = combination.get(index);
		int comination_size = combination.size();
		for ( int i = 0 ; i < map[g_s].length ; ++i ) {
			
			if (  ! is_under_attack[map[g_s][i]] ) {
				if ( index == comination_size-1 ) {
					++num_ways;
					continue;
				}
				else {
					is_under_attack[map[g_s][i]] = true;
					countWays(index+1);
					is_under_attack[map[g_s][i]] = false;
				}
			}
		}
	}
	
	public static void print ( int matrix[][]  ) {
		for ( int i = 0 ; i < matrix.length ; ++i ) {
			for ( int k = 0 ; k < matrix[i].length ; ++k ) {
				System.out.print(matrix[i][k]+" ");
			}
			System.out.println();
		}
	}
	
	public static int findAllPermutationsVer2 (int board_size, int num_bishops ) {
		n = board_size;
		generateMap(board_size);
		//print(map);
		int total_combinations = 0;
		if ( num_bishops == 0 ) {
			return 1;
		}
		for ( int i = 0 ; i <= num_bishops ; ++i ) {
			// white parni
			// black neparnite dijagonali
			int white_combinations = getAllWhiteBishopsPermutations(i);
			int black_combinations = getAllBlackBishopsPermutations(num_bishops-i);
			total_combinations += white_combinations*black_combinations;
		}
		if ( num_bishops == 1 ) {
			//return total_combinations*2+((n+1)/2)%2;
		}
		return total_combinations;
	}
	
	private static int getAllBlackBishopsPermutations(int num_bishops) {
		if ( num_bishops == 0 ) {
			return 1;
		}
		if ( num_bishops > n/2 ) {
			return 0;
		}
		int rez = 0;
		rez = findAllPermutationsRecursivVer2(3,num_bishops);
		is_under_attack[map[1][0]] = true;
		rez += findAllPermutationsRecursivVer2(3,num_bishops-1);
		is_under_attack[map[1][0]] = false;
		is_under_attack[map[1][1]] = true;
		rez += findAllPermutationsRecursivVer2(3,num_bishops-1);
		is_under_attack[map[1][1]] = false;
		return rez;
	}

	private static int getAllWhiteBishopsPermutations(int num_bishops) {
		if ( num_bishops == 0 ) {
			return 1;
		}
		if ( num_bishops > (n+1)/2 ) {
			return 0;
		}
		int rez = 0;
		rez = findAllPermutationsRecursivVer2(2,num_bishops);
		is_under_attack[map[0][0]] = true;
		rez += findAllPermutationsRecursivVer2(2,num_bishops-1);
		is_under_attack[map[0][0]] = false;
		return rez;
	}

	public static int findAllPermutationsRecursivVer2( int current_diagonal, int num_bishops /*, String combination */) {
		
		if ( num_bishops == 0 ) {
			//System.out.println(combination);
			return 1;
		}
		if ( current_diagonal > n-num_bishops  ) {
			//System.out.println(n);
			return 0;
		}
		int sum = 0;
		//System.out.println(is_under_attack[map[0][0]]+" "+map[0][0]);
		for ( int i = 0 ; i < map[current_diagonal].length ; ++i ) {
			
			if ( ! is_under_attack[map[current_diagonal][i]] ) {
				//int temp = combination.length();
				//combination += current_diagonal+" "+i+" ; ";
				is_under_attack[map[current_diagonal][i]] = true;
				sum += findAllPermutationsRecursivVer2(current_diagonal+2, num_bishops-1/*,combination*/);
				is_under_attack[map[current_diagonal][i]] = false;
				//combination = combination.substring(0,temp);
			}
		}
		sum += findAllPermutationsRecursivVer2(current_diagonal+2, num_bishops/*,combination*/);
		return sum;
	}

	
}

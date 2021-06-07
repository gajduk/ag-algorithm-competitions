package Chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem8 {

	public static final int MAX_GAS = 201;
	public static final int IMMPOSIBLE = 12345678;
	
	public static int getMinimumCostWrong ( int length , int gas_locations[] , int gas_prices[] , int n) {
		int cost[] = new int[length+100];
		for ( int i = 0 ; i < cost.length ; ++i ) {
			cost[i] = IMMPOSIBLE;
		}
		for ( int k = 0 ; k <= 100 ; ++k ) {
			cost[k] = 0; 
		}
		for ( int i =  0 ; i < n ; ++i ) {
			if ( cost[gas_locations[i]] == IMMPOSIBLE ) {
				break;
			}
			for ( int k = gas_locations[i] ; k <= gas_locations[i]+200 ; ++k ) {
				if ( k >= cost.length ) 
					break;
				if ( cost[k] == IMMPOSIBLE || cost[k] > cost[gas_locations[i]]+gas_prices[i] ) {
					cost[k] = cost[gas_locations[i]]+gas_prices[i]*(k-gas_locations[i]);
				}
			}
		}
		System.out.println(Arrays.toString(cost));
		return cost[cost.length-1];
	}
	
	public static int getMinimumCost ( int length , int gas_locations[] , int gas_prices[] , int n) {
		int t[][] = new int[length+101][MAX_GAS];
		for ( int i = 0 ; i < t.length ; ++i ) {
			for ( int k = 0 ; k < t[0].length ; ++k ) {
				t[i][k] = IMMPOSIBLE;
			}
		}
		t[0][100] = 0;
		int gas_station_index = 0;
		
		for ( int i = 1 ; i < t.length ; ++i ) {
			for ( int k = 0 ; k < MAX_GAS-1 ; ++k ) {
				t[i][k] = t[i-1][k+1];
			}
			while ( gas_station_index < gas_locations.length && gas_locations[gas_station_index] == i ) {
//				System.out.println("We are at gas station "+gas_station_index+" at location "+i);
				int w = 0;
				
//				System.out.println();
				for ( w = 0 ; w < MAX_GAS && t[i][w] == IMMPOSIBLE ; ++w );
//				System.out.println("The car has enough gas for another "+w+" miles");
				while ( w < MAX_GAS ) {
					int start = w;
					++w;
					while ( w < MAX_GAS && t[i][w] >= t[i][start]+gas_prices[gas_station_index]*(w-start) ) {
						t[i][w] = t[i][start]+gas_prices[gas_station_index]*(w-start);
						++w;
					}						
				}
				++gas_station_index;
			}
		}
		/*
		for ( int i = 0 ; i < t.length ; ++i ) {
			System.out.print("at the "+i+" km mark:");
			for ( int j = 0 ; j < MAX_GAS ; ++j ) {
				if ( t[i][j] == IMMPOSIBLE ) {
					System.out.print(" *");
				}
				else {
					System.out.print(" "+t[i][j]);
				}
			}
			System.out.println();
		}
		*/
		return t[length+100][0];
	}
	
	/*
	public static int getMinimumCost ( int length , int gas_locations[] , int gas_prices[] , int n) {
		int t[][] = new int[length+5][MAX_GAS];
		for ( int i = 0 ; i < t.length ; ++i ) {
			for ( int k = 0 ; k < t[0].length ; ++k ) {
				t[i][k] = IMMPOSIBLE;
			}
		}
		t[0][100] = 0;
		int w = 0;
		for ( int i = 0 ; i < length ; ++i ) {
			if ( gas_locations[w] == i ) {
				int k = 0;
				for (  ; k < MAX_GAS && t[i][k] == IMMPOSIBLE ; ++k );
				for ( int p = k ; p < MAX_GAS ; ++p ) {
					if ( t[i][p] == IMMPOSIBLE ) {
						t[i][p] = t[i][k]+gas_prices[w]*(p-k);
						for ( int j = k+1 ; j < p ; ++j ) {
							t[i][p] = Math.min(t[i][p],t[i][j]+gas_prices[w]*(p-j));
						}
					}
					else {
						for ( int j = k ; j < p ; ++j ) {
							t[i][p] = Math.min(t[i][p],t[i][j]+gas_prices[w]*(p-j));
						}
					}
				}
				++w;
			}
			for ( int k = 1 ; k < MAX_GAS ; ++k ) {
				
				if ( t[i][k] != IMMPOSIBLE ) {
					t[i+1][k-1] = Math.min(t[i+1][k-1],t[i][k]);
				}
			}
		}
		for ( int i = 0 ; i < length ; ++i ) {
//			System.out.println(Arrays.toString(t[i]));
		}
		return t[length][MAX_GAS/2];
	}
	*/
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}
	
	private static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int gas_locations[] = new int[105];
    		int gas_prices[] = new int[105];
    		int k = 0;
    		int l = new Integer(in.nextLine());
    		while ( true ) {
    			if ( ! in.hasNext() )
    				break;
    			String s_gas_station = in.nextLine();
    			if ( s_gas_station.equals("") ) {
    				break;
    			}
    			StringTokenizer tkr_gas_station = new StringTokenizer(s_gas_station);
    			gas_locations[k] =  new Integer(tkr_gas_station.nextToken());
    			gas_prices[k++] = new Integer(tkr_gas_station.nextToken());
    		}
    		int temp = getMinimumCost(l, gas_locations, gas_prices, k);
    		if ( temp == IMMPOSIBLE ) {
    			System.out.println("Impossible");
    		}
    		else {
    			System.out.println(temp);
    		}
    		if ( i < num_test_cases-1 ) 
    			System.out.println();
    	}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter11 problem8.txt");
	    }
    	catch ( FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int gas_locations[] = new int[105];
    		int gas_prices[] = new int[105];
    		int k = 0;
    		int l = new Integer(in.nextLine());
    		while ( true ) {
    			if ( ! in.hasNext() )
    				break;
    			String s_gas_station = in.nextLine();
    			if ( s_gas_station.equals("") ) {
    				break;
    			}
    			StringTokenizer tkr_gas_station = new StringTokenizer(s_gas_station);
    			gas_locations[k] =  new Integer(tkr_gas_station.nextToken());
    			gas_prices[k++] = new Integer(tkr_gas_station.nextToken());
    		}
    		int temp = getMinimumCost(l, gas_locations, gas_prices, k);
    		if ( temp == IMMPOSIBLE ) {
    			System.out.println("Impossible");
    		}
    		else {
    			System.out.println(temp);
    		}
    		if ( i < num_test_cases-1 ) 
    			System.out.println();
    	}
    	
	}
  
}

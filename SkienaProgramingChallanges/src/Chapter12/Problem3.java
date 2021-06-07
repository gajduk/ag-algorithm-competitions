package Chapter12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem3 {
	
	public static int map[][] = {   {5,6,7,8,9,10,11,12,13,14,15},
									{16,17,18,19,20,21,22,23,24},
									{25,26,27,28,29,30,31,32,33},
									{34,35,36,37,38,39,40,41,42,43,44},
									{34,35,25,26,17,18,8,9,2,3,1},
									{36,37,27,28,19,20,10,11,4},
									{45,38,39,29,30,21,22,12,13},
									{48,46,47,40,41,31,32,23,24,14,15},
									{48,46,45,37,38,26,27,17,16,5,6},
									{47,39,40,28,29,19,18,7,8,},
									{41,42,30,31,20,21,9,10,2},
									{43,44,32,33,22,23,12,11,4,3,1}	};
	
	public static int star[] = { -1,6,3,2,1,1,3,0,2,4,1,0,2,5,0,0,7,6,1,0,1,1,6,0,0,4,5,0,1,8,4,0,0,5,3,2,1,1,9,3,0,0,3,1,2,8,0,0,0 };
	public static int reverse_map[][];
	public static int map_counter[];
	public static int intersect[][] = { {4,5,6,7,8,9,10,11},
										{4,5,6,7,8,9,10,11},
										{4,5,6,7,8,9,10,11},
										{4,5,6,7,8,9,10,11},
										{0,1,2,3,8,9,10,11},
										{0,1,2,3,8,9,10,11},
										{0,1,2,3,8,9,10,11},
										{0,1,2,3,8,9,10,11},
										{4,5,6,7,0,1,2,3},
										{4,5,6,7,0,1,2,3},
										{4,5,6,7,0,1,2,3},
										{4,5,6,7,0,1,2,3}  };
	
	public static void generateReverseMap () {
		reverse_map = new int[48][3];
		map_counter = new int[48];
		for ( int i = 0 ; i < map.length ; ++i ) {
			for ( int k = 0 ; k < map[i].length ; ++k ) {
				reverse_map[map[i][k]-1][map_counter[map[i][k]-1]++] = i;
			}
		}
	}
	
	public static void printRows () {
		for ( int i = 0 ; i < map.length ; ++i ) {
			for ( int k = 0 ; k < map[i].length ; ++k ) {
				System.out.print(star[map[i][k]]+" ");
			}
			System.out.println();
		}
	}
	
	public static void printReverseMap () {
		for ( int i = 0 ; i < reverse_map.length ; ++i ) {
			for ( int k = 0 ; k < map_counter[i] ; ++k ) {
				System.out.print(reverse_map[i][k]+" ");
			}
			System.out.println();
		}
	}
	
	public static int getSum () {
		int sum = 0 ;
		for ( int i = 1 ; i < star.length ; ++i ) {
			sum += star[i];
		}
		return sum;
	}
	
	public static void populateForMax( int max[] ) {
		for ( int i = 1 ; i < star.length ; ++i ) {
			star[i] = 9;
		}
		for ( int i = 0 ; i < map.length ; ++i ) {
			for ( int k = 0 ; k < map[i].length ; ++k ) {
				if( star[map[i][k]] > max[i] ) {
					star[map[i][k]] = max[i];
				}
			}
		}
//		printRows();
	}
	
	public static int populateForMin( int max[] ) {
		for ( int i = 1 ; i < star.length ; ++i ) {
			star[i] = 0;
		}
		int total = 0;
		int count[] = new int[10];
		ArrayList counts[] = new ArrayList[10];
		boolean achieved[] = new boolean[max.length];
		for ( int i = 0 ; i < count.length ; ++i ) {
			counts[i] = new ArrayList<Integer>();
		}
		for ( int i = 0 ; i < max.length ; ++i ) {
			++count[max[i]];
			counts[max[i]].add(i);
			total += max[i];
		}
		for ( int k = count.length-1  ; k >= 0 ; --k ) {
//			System.out.println(k+" "+count[k]+" "+counts[k].size());
			int num_2 = 0;
			int num_3 = 0;
			if ( count[k] == 1 ) {
				num_2 = 0;
				num_3 = 0;
			}
			if ( count[k] == 2 ) {
				num_2 = 1;
				num_3 = 0;
			}
			if ( count[k] == 3 ) {
				num_2 = 0;
				num_3 = 1;
			}
			if ( count[k] == 4 ) {
				num_2 = 2;
				num_3 = 0;
			}
			if ( count[k] == 5 ) {
				num_2 = 1;
				num_3 = 1;
			}
			if ( count[k] == 6 ) {
				num_2 = 0;
				num_3 = 2;
			}
			if ( count[k] == 7 ) {
				num_2 = 2;
				num_3 = 1;
			}
			if ( count[k] == 8 ) {
				num_2 = 1;
				num_3 = 2;
			}
			if ( count[k] == 9 ) {
				num_2 = 0;
				num_3 = 3;
			}
			if ( count[k] == 10 ) {
				num_2 = 2;
				num_3 = 2;
			}
			if ( count[k] == 11 ) {
				num_2 = 1;
				num_3 = 3;
			}
			if ( count[k] == 12 ) {
				num_2 = 0;
				num_3 = 4;
			}
			if ( count[k] == 0 ) {
				num_2 = 0;
				num_3 = 0;
			}
			for ( int q = 0 ; q < num_3 ; ++ q ) {
				count[k] -= 3;
				achieved[(Integer)counts[k].remove(0)] = true;
				achieved[(Integer)counts[k].remove(0)] = true;
				achieved[(Integer)counts[k].remove(0)] = true;
				total -= 2 * k;
			}
//			System.out.println(count[k]+" "+counts[k].size());
			for ( int q = 0 ; q < num_2 ; ++ q ) {
				count[k] -= 2;
				achieved[(Integer)counts[k].remove(0)] = true;
				achieved[(Integer)counts[k].remove(0)] = true;
				total -= k;
			}
//			System.out.println(k+" "+count[k]+" "+counts[k].size());
			if ( count[k] != 0 ) {
//				System.out.println(count[k]+" "+counts[k].size());
//				System.out.println(k);
				int temp = (Integer)counts[k].remove(0);
//				System.out.println(temp);
				int i = 0 ; 
				for ( i = 0 ; i < map[temp].length ; ++i ) {
					int w = 0;
//					System.out.print("Cell index:"+map[temp][i]+" rows: ");
					for ( w = 0 ; w < map_counter[map[temp][i]-1] ; ++w ) {
//						System.out.print(reverse_map[map[temp][i]-1][w]+" with max ");
//						System.out.print(max[reverse_map[map[temp][i]-1][w]]+" '' ");
						if ( reverse_map[map[temp][i]-1][w] != temp && max[reverse_map[map[temp][i]-1][w]]  >= k ) {
//							System.out.println("asd");
							break;
						}
					}
					if ( w != map_counter[map[temp][i]-1] )
						break;
//					System.out.println("\n"+w);

				}
				if ( i == map[temp].length ) {
					return -1;
				}
				achieved[temp] = true;
				/*
				System.out.println("IMPOSIBLE");
				break;
				*/
			}
		}
		for ( int i = 0 ; i < achieved.length ; ++i ) {
			if ( ! achieved[i] ) {
				return -1;
			}
		}
		return total;
//		printRows();
	}
	
	public static void main ( String args[] ) {
//		printRows();
//		testMin();
//		testMax();
		test_file();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	
    	while ( in.hasNext() ) {
    		int max[] = new int[12];
    		StringTokenizer maxes = new StringTokenizer(in.nextLine());
    		for ( int i = 0 ; i < max.length ; ++i ) {
    			max[i] = new Integer(maxes.nextToken());
    		}
    		calculateSolution(max);
    		
    	}
	}

	private static void calculateSolution(int[] max) {
		generateReverseMap();
		int temp = populateForMin(max);
		if ( temp == -1 ) {
			System.out.println("NO SOLUTION");
			return;
		}
		populateForMax(max);
		System.out.println(temp+" "+getSum());
	}

	private static void testMin() {
		int max[] = { 5,7,8,9,6,1,9,0,9,8,4,6 };
		generateReverseMap();
//		printReverseMap ();
		System.out.println(populateForMin(max));
	}

	private static void testMax() {
		int max[] = { 5,7,8,9,6,1,9,0,9,8,4,6 };
		populateForMax(max);
		System.out.println(getSum());
	}
	
}

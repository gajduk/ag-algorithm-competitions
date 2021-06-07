package Chapter5;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem8 {

	public static final int MAX_ELEMENTS = 10;
	public static final int MAX_SUMS = 45;
	public static int elements[];
	public static boolean generated[];
	public static int sums[];
	public static int num_elements;
	public static int num_sums;
	
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		String s_numbers = in.nextLine();
    		StringTokenizer tkr = new StringTokenizer(s_numbers);
    		num_elements = new Integer(tkr.nextToken());
    		num_sums = num_elements*(num_elements-1)/2;
    		elements = new int[num_elements];
    		sums = new int[num_sums];
    		generated = new boolean[num_sums];
    		for ( int k = 0 ; k < num_sums ; ++k ) {
    			sums[k] = new Integer(tkr.nextToken());
    		}
    		calculateElements();
    	}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	     
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter5 problem8.txt");
	    }
    	catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		String s_numbers = in.nextLine();
    		StringTokenizer tkr = new StringTokenizer(s_numbers);
    		num_elements = new Integer(tkr.nextToken());
    		num_sums = num_elements*(num_elements-1)/2;
    		elements = new int[num_elements];
    		sums = new int[num_sums];
    		generated = new boolean[num_sums];
    		for ( int k = 0 ; k < num_sums ; ++k ) {
    			sums[k] = new Integer(tkr.nextToken());
    		}
    		calculateElements();
    	}

	}

	public static void calculateElements() {
		Arrays.sort(sums);
		if ( num_elements == 2 ) {
			System.out.println(0+" "+sums[0]);
			return;
		}
		int k = sums[0];
		if (k > 0) 
			++k;
		else
			--k;
		for (  ; k != 0 ;  ) {
			if (k > 0) 
				--k;
			else
				++k;
			boolean temp_sums[] = new boolean[generated.length];
			copySums(temp_sums);
			elements[0] = k;
			elements[1] = sums[0]-elements[0];
			elements[2] = sums[1]-elements[0];
			if ( num_elements == 3 ) {
				/*
				for (int i = 0; i < elements.length; i++) {
							System.out.print(elements[i]+" ");
				}
				System.out.println();
				*/
				if ( sums[2] != elements[1] + elements[2] ) {
					continue;
				}
				else {
					printElements();
					return;
				}
			}
			else {
				generated[0] = generated[1] = true;
				if ( ! cancelOffAllCombinations(2) ) 
					continue;
				boolean is_possible = true;
				for ( int m = 3 ; m < num_elements ; ++m ) {
					elements[m] = sums[getNextSmallestSum()] - elements[0];
					generated[getNextSmallestSum()] = true;
					if ( ! cancelOffAllCombinations(m) ) {
						is_possible = false;
						break;
					}
				}
				if ( ! is_possible   ) {
					copyBackSums(temp_sums);
					continue;
				}
				else {
					printElements();
					return;
				}
			}
			
		}
		System.out.println("Impossible");
	}

	private static void printElements() {
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i]);
			if ( i < elements.length -1 )
				System.out.print(" ");
		}
		System.out.println();
	}

	private static boolean cancelOffAllCombinations(int m) {
		for ( int k = 1 ; k < m ; ++k ) {
			if ( ! cancelSum(elements[k]+elements[m]) ) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean cancelSum ( int sum ) {
		for ( int m = 0 ; m < sums.length && sum >= sums[m] ; ++m ) {
			if ( ! generated[m] ) {
				if ( sums[m] == sum ) {
					generated[m] = true;
					return true;
				}
			}
		}
		return false;
	}

	private static int getNextSmallestSum() {
		for ( int m = 0 ; m < sums.length ; ++m ) {
			if ( ! generated[m] ) {
				return m;
			}
		}
		return 0;
	}

	private static void copyBackSums(boolean[] temp_sums) {
		for ( int k = 0 ; k < temp_sums.length ; ++k ) {
			generated[k] = temp_sums[k];
		}
	}

	private static void copySums(boolean[] temp_sums) {
		for ( int k = 0 ; k < temp_sums.length ; ++k ) {
			temp_sums[k] = generated[k];
		}
	}
	
	
	
	
}

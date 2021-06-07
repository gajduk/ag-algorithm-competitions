package Chapter6;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem5 {
	
	public static BigInteger f_k_d[][] = new BigInteger[22][22];
	public static BigInteger n_k_d[][] = new BigInteger[22][22];
	
	
	public static void main ( String args[] ) {
		generateNumbers();
		test_judge();
		//test_file();
	}
	
	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter6 problem5.txt");
	    }
		catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
		Scanner in = new Scanner(inputFile);
		while ( in.hasNext() ) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers);
			int k = new Integer(numbers.nextToken());
			int d = new Integer(numbers.nextToken());
			System.out.println(f_k_d[k][d]);
		}
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers);
			int k = new Integer(numbers.nextToken());
			int d = new Integer(numbers.nextToken());
			System.out.println(f_k_d[k][d]);
		}
	}

	public static void generateNumbers ()	{
		  int powers[][] = new int[22][22];
		  for ( int k = 1 ; k <= 21 ; ++k ) {
		      powers[k][0] = 1;
		      for ( int d = 1 ; d * k <= 21 ; ++d ) {
		           powers[k][d] = powers[k][d-1] * k;
		      }
		  }
		  int nodes[][] = new int[22][22];
		  int max_fac = 0;
		  for ( int k = 1 ; k <= 21 ; ++k ) {
		        nodes[k][0] = 1;
		        for ( int d = 1 ; d * k <= 21 ; ++d ) {
		              if ( (nodes[k][d] = nodes[k][d-1] + powers[k][d]) > max_fac ) {
		            	    max_fac = nodes[k][d];
		              }
		        }
		  }
		  ArrayList<BigInteger> facs = new ArrayList();
		  facs.add(new BigInteger("1"));
		  for ( int i = 1 ; i < max_fac ; ++i ) {
			    facs.add(facs.get(i-1).multiply(new BigInteger(Integer.toString(i))));
		  }
		  for ( int k = 1 ; k <= 21 ; ++k ) {
			  	f_k_d[k][0] = new BigInteger("1");
			  	for ( int d = 1 ; d * k <= 21 ; ++d ) {
			  		  f_k_d[k][d] = f_k_d[k][d-1];
			  		  for ( int i = 1 ; i != k ; ++i ) 
			  			  f_k_d[k][d] = f_k_d[k][d].multiply(f_k_d[k][d-1]);
			  		  f_k_d[k][d] = f_k_d[k][d].multiply(facs.get(nodes[k][d]-1));
			  		  for ( int i = 0 ; i < k ; ++i ) 
			  			  f_k_d[k][d] = f_k_d[k][d].divide(facs.get(nodes[k][d-1]));
		    }
		  }
	}  
	
	public static void print ( Object matrix[][] ) {
		for ( int i = 1 ; i < matrix.length ; ++i ) {
			for ( int m = 0 ; m < matrix[0].length ; ++m ) {
				System.out.print(matrix[i][m].toString()+"  ");
			}
			System.out.println();
		}
	}
	
	public static void populate () {
		for ( int k = 1 ; k < 22 ; ++k ) {
			n_k_d[k][0] = new BigInteger("0");
		}
		for ( int k = 1 ; k < 22 ; ++k ) {
			for ( int d = 1 ; d < 22 ; ++d ) {
				BigInteger b_k = new BigInteger(Integer.toString(k));
				BigInteger b_k_na_d = new BigInteger(Integer.toString(k));
				b_k_na_d = b_k.pow(d-1);
				n_k_d[k][d] = n_k_d[k][d-1].add(b_k_na_d);
			}
		}
		//print(n_k_d);
		for ( int k = 0 ; k < 22 ; ++k ) {
			f_k_d[k][0] = new BigInteger("1");
		}
		for ( int k = 0 ; k < 22 ; ++k ) {
			f_k_d[0][k] = new BigInteger("0");
		}
		
		for ( int k = 1 ; k < 22 ; ++k ) {
			for ( int d = 1 ; d < 22 ; ++d ) {
				if ( k*d <= 21 ) {
					BigInteger up = n_k_d[k][d];
					BigInteger one = new BigInteger("1");
					up = up.subtract(one);
					up = faktoriel(up);
					BigInteger down = n_k_d[k][d-1];
					down = faktoriel(down);
					down = down.pow(k);
					f_k_d[k][d] = up.divide(down);
				}
			}
		}
		
	}
	
	public static BigInteger faktoriel ( BigInteger n ) {
		BigInteger one = new BigInteger("1");
		BigInteger k = new BigInteger("1");
		BigInteger result  = new BigInteger("1");
		while ( k.compareTo(n) < 0 ) {
			result = result.multiply(k);
			k = k.add(one);
		}
		return result;
	}

}

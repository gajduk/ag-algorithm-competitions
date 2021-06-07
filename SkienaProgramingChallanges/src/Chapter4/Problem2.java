package Chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem2 {
	public static  int stack[];
	public static  int sorted[];
	
	public static void main ( String args[] ) {
		//testReverse();
		test_file();
		test_judge();
	}	

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			String s_stack = in.nextLine();
			StringTokenizer pancakes = new StringTokenizer(s_stack);
			stack = new int[pancakes.countTokens()];
			for (int i = 0; i < stack.length; i++) {
				stack[i] = new Integer(pancakes.nextToken());
			}			
			for ( int k = 0 ; k < stack.length ; ++k ) {
				System.out.print(stack[k]);
				if ( k < stack.length -1 )
					System.out.print(" ");
			}
			System.out.println();
			sort(stack);
		}
		
	}

	private static void copy( int src[] , int n ) {
		stack = new int[n];
		for ( int k = 0 ; k < n ; ++k ) {
			stack[k] = src[k];
		}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
		try	 {
     		inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem2.txt");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
			return;
		}
		Scanner in = new Scanner(inputFile);
		while ( in.hasNext() ) {
			String s_stack = in.nextLine();
			StringTokenizer pancakes = new StringTokenizer(s_stack);
			stack = new int[pancakes.countTokens()];
			for (int i = 0; i < stack.length; i++)	 {
				stack[i] = new Integer(pancakes.nextToken());
			}			
			for ( int k = 0 ; k < stack.length ; ++k ) {
				System.out.print(stack[k]);
				if ( k < stack.length -1 )
					System.out.print(" ");
			}
			System.out.println();
			sort(stack);
		}
	}

	public static void flip() {
		/*
		 sorted = new int[stack.length];
		 
		for (int i = 0; i < sorted.length; i++) {
			sorted[i] = stack[i];
		}
		Arrays.sort(sorted);
		*/
		boolean flag = false;
		for ( int k = 0 ; k < stack.length ; ++k ) {
			
			int min = Integer.MAX_VALUE;
			int min_index = 0;
			for ( int m = k ; m < stack.length ; ++m ) {
				if ( min > stack[m] ) {
					min = stack[m];
					min_index = m;
				}
			}
			if ( min_index == k ) {
				continue;
			}
			if ( min_index != stack.length-1 &&  min_index != k ) {
				if ( flag ) {
					System.out.print(" ");
				}
				else {
					flag = true;
				}
				reverse(min_index,stack.length-1);
				if ( k < stack.length-1 ) {
					System.out.print(min_index+1);
					if ( flag ) {
							System.out.print(" ");
					}
					else {
						flag = true;
					}
					reverse(k,stack.length-1);
					System.out.print(k+1);
				}
	
			}
			else {
				if ( min_index == stack.length-1 ) {
					if ( flag ) {
						System.out.print(" ");
					}
					else {
						flag = true;
					}
					reverse(k,stack.length-1);
					System.out.print(k+1);
				}
			}
			
		}
		if ( flag ) {
			System.out.print(" ");
		}
		System.out.println(0);
	}

	public static void flipRecursive( int current_index ) {
		if ( current_index < 0 ) {
			return;
		}
		if ( stack[current_index] == sorted[current_index] ) {
			if ( current_index == 0 ) {
				System.out.println("0");
				return;
			}
			flipRecursive(current_index-1);
			return;
		}
		else {
			if ( stack[0] == sorted[current_index] ) {
				System.out.print(stack.length-current_index+" ");
				reverse(0, current_index);
				flipRecursive(current_index-1);
				return;
			}
			else {
				int t = 0;
				for ( int i = 0 ; i < stack.length ; ++i ) {
					if ( stack[t] == sorted[current_index] ) {
						t = i;
					}
				}
				System.out.print(stack.length-t+" ");
				reverse(t,current_index);
				flipRecursive(current_index-1);
				return;
			}
		}
	}
	
	public static void reverse ( int start , int end  ) {
		for ( int k = start, m = end ; k < m ; ++k , --m ) {
			int temp = stack[k];
			stack[k] = stack[m];
			stack[m] = temp;
		}
	}
	
	public static void sort ( int stack[] ) {
		int sorted[] = new int[stack.length];
		for (int i = 0; i < sorted.length; i++) {
			sorted[i] = stack[i]; 
		}
		Arrays.sort(sorted);
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int current = 0;
		while ( current < stack.length ) {
//			System.out.println("stack");
//			print(stack);
			
//			System.out.println("somewhere else");
			int index = findIndex(sorted[current],stack,current);
			if ( index == current ) {
				++current;
			}
			else {
				if ( index != stack.length-1 ) {
					reverse(stack,index);
					moves.add(1+index);
				}
//				print(stack);
				reverse(stack,current);
				moves.add(1+current);
				++current;
			}
			
		}
//		print(stack);
		for (Integer integer : moves) {
			System.out.print(integer+" ");
		}
		System.out.println("0");
	}

	public static int findIndex(int i , int []array , int start) {
		for ( int k = start ; k < array.length ; ++k ) {
			if ( array[k] == i )
				return k;
		}
		System.out.println("No such element exists. "+i);
		return 0;
	}

	public static void reverse(int[] a, int current) {
		for ( int i = current , k = a.length-1 ; i < k ; ++i , --k ) {
			int temp = a[i];
			a[i] = a[k];
			a[k] = temp;
		}
	}
	
	public static void testReverse ( ) {
		int test[] = { 1 , 2 , 3 , 4 , 5 , 6 };
		print(test);
		reverse(test,4);
		print(test);
	}

	private static void print(int[] test) {
		for (int i = 0; i < test.length; i++) {
			System.out.print(test[i]+" ");
		}
		System.out.println();
	}


}

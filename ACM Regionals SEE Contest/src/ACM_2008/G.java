package ACM_2008;

import java.util.Arrays;
import java.util.Scanner;

public class G {
	public static long lucky_numbers[] = new long[4094];
	public static int lucky_numbers_counts;
	public static long very_lucky_numbers[] = new long[22000000];
	public static int very_lucky_numbers_counts;
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int num_test_cases = in.nextInt();
		populateNumbers();
		for ( int i = 0 ; i < num_test_cases ; ++i ) {
			long a = in.nextInt();
			long b = in.nextInt();
			int start = Arrays.binarySearch(very_lucky_numbers,a);
			int end = Arrays.binarySearch(very_lucky_numbers,b);
			System.out.println((b-a+1));
			
		}
	}

	private static void populateNumbers() {
		populateLucky(0,1);
		Arrays.sort(lucky_numbers);
//		System.out.println(lucky_numbers_counts);
		int temp_prev = -1;
		for ( int i = 0 ; i < lucky_numbers_counts ; ++i ) {
			very_lucky_numbers[very_lucky_numbers_counts++] = lucky_numbers[i];
		}
		for ( int w = 1 ; w < 3 ; ++w ) {
			int temp = very_lucky_numbers_counts;
			for ( int i = 0 ; i < lucky_numbers_counts ; ++i ) {
				for ( int j =  temp ; j > temp_prev ; --j ) {
					if ( (lucky_numbers[i]*very_lucky_numbers[j]) > 0 && (lucky_numbers[i]*very_lucky_numbers[j]) < 1000000000000L ) {
						very_lucky_numbers[very_lucky_numbers_counts++] = lucky_numbers[i]*very_lucky_numbers[j];
					}
				}	
			}
			temp_prev = temp;
			/*
			for ( int i = temp ; i < very_lucky_numbers_counts ; ++i  ) {
				System.out.println(very_lucky_numbers[i]);
			}
			*/
		}
		Arrays.sort(very_lucky_numbers);
		for ( int i = very_lucky_numbers.length-very_lucky_numbers_counts ; i < very_lucky_numbers.length-very_lucky_numbers_counts+100 ; ++i  ) {
			System.out.println(very_lucky_numbers[i]);
		}
	}
	
	public static final int MAX_LEN = 12;
	
	public static void populateLucky ( long t , int length ) {
		if ( length == MAX_LEN )
			return;
		lucky_numbers[lucky_numbers_counts++] = t*10+4;
		populateLucky(t*10+4, length+1);
		lucky_numbers[lucky_numbers_counts++] = t*10+7;
		populateLucky(t*10+7, length+1);
		
	}

}

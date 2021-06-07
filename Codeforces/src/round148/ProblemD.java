package round148;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemD {
	
	public static void main(String[] args) {
		/*
	
		int n = jin.nextInt();
		int h = jin.nextInt();
		jin.nextLine();
		String splited[] =  jin.nextLine().split(" ");
		Integer numbers[] = new Integer[n];
		for ( int i = 0 ; i < n ; ++i ) {
			numbers[i] = Integer.parseInt(splited[i]);
		}
		Arrays.sort(numbers);
		*/
		Scanner jin = new Scanner(System.in);
		int n = jin.nextInt();
		int y = jin.nextInt();
		int k = jin.nextInt();
		for ( int i = (int)((y*1.0)/k+1)*k ; i <= n ; i += k ) {
			int x = i-y;
			if ( x > 0 )
				x = x;
		}
	}

}

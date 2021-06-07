package round150;

import java.util.ArrayList;
import java.util.Scanner;

public class ProblemA {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int n = jin.nextInt();
		int k = jin.nextInt();
		ArrayList<Integer> wishes = new ArrayList<Integer>();
		for ( int i = 0 ; i < k ; ++i ) 
			wishes.add(jin.nextInt());
		int counter = 1;
		for ( int i = 0 ; i < k ; ++i ) {
			System.out.print(wishes.get(i));
			for ( int j = 1 ; j < n ; ++j ) {
				while (wishes.contains(counter)) ++counter;
				System.out.print(" "+counter);
				++counter;
			}
			System.out.println();
		}
		
	}

}

package round149;

import java.util.ArrayList;
import java.util.Scanner;

public class ProblemA {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int x = jin.nextInt();
		int y = jin.nextInt();
		int a = jin.nextInt();
		int b = jin.nextInt();
		ArrayList<String> outcomes = new  ArrayList<String>();
		for ( int i = a ; i <= x ; ++i ) {
			for ( int k = b ; k < i && k <= y ; ++k ) {
				outcomes.add(i+" "+k);
			}
		}
		System.out.println(outcomes.size());
		for ( String o : outcomes ) System.out.println(o);
	}

}

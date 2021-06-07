package round150;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProblemC {
	
	public static void main(String[] args) throws IOException {
		BufferedReader jin = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(jin.readLine());
		String ints[] = jin.readLine().split(" ");
		int a[] = new int[n];
		for ( int i = 0 ; i < a.length ; ++i ) {
			a[i] = Integer.parseInt(ints[i]);
		}
		int ors[] = new int[n+1];
		ors[0] = 0;
	}

}

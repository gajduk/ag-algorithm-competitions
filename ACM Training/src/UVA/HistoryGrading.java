package UVA;

import java.util.Scanner;

public class HistoryGrading {
	
	static int table[][];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int correct[] = new int[n];
		int answers[] = new int[n];
		for ( int i = 0 ; i < n ; ++i ) correct[in.nextInt()-1] = i;
		while ( in.hasNextInt() ) {
			for ( int i = 0 ; i < n ; ++i ) answers[in.nextInt()-1] = i;
			table = new int[n][n];
			for ( int i = 0 ; i < n ; ++i ) for ( int k = 0 ; k < n ; ++k ) table[i][k] = -1;
			int points = lcs(correct,answers,correct.length-1,answers.length-1);
			System.out.println(points);
		}
	}

	private static int lcs(int[] correct, int[] answers, int i, int j) {
		
		if ( i < 0 || j < 0 ) return 0;
		if ( table[i][j] == -1 ) {
			if ( correct[i] == answers[j] ) return lcs(correct,answers,i-1,j-1)+1;
			int p1 = lcs(correct,answers,i,j-1);
			int p2 = lcs(correct,answers,i-1,j);
			table[i][j] = p1<p2?p2:p1;
		}
		return table[i][j];
	}
	
}

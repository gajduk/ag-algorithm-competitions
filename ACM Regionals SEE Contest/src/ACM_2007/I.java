package ACM_2007;

import java.util.Scanner;

public class I {
	
	public static void main ( String args [] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			int num_lines = in.nextInt();
			char table[][] = new char[num_lines][];
			for ( int i = 0 ; i < num_lines ; ++i ) {
				table[i] = in.next().toCharArray();
			}
			char message_trailing_spaces[] = new char[table.length*table[0].length];
			int c = 0;
			for ( int j = table[0].length-1 ; j >= 0 ; --j ) {
				for ( int i = table.length-1 ; i >= 0 ; --i ) {
					message_trailing_spaces[c++] =  table[i][j];
				}
			}
			for ( int i = 0 ; i < message_trailing_spaces.length ; ++i ) {
				if ( message_trailing_spaces[i] == '_' ) {
					message_trailing_spaces[i] = ' ';
				}
				if ( message_trailing_spaces[i] == '\\' ) {
					message_trailing_spaces[i] = '\n';
				}
			}
			--c;
			while ( message_trailing_spaces[c] == ' ' )
				--c;
			for ( int i = 0 ; i <= c ;++i ) {
				System.out.print(message_trailing_spaces[i]);
			}
			
		}
	}

}

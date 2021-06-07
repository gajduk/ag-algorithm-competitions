package Chapter1;

import java.util.Scanner;

/*
	 1.6.2 Minesweeper
	PC/UVa IDs: 110102/10189, Popularity: A, Success rate: high Level: 1
	Have you ever played Minesweeper? This cute little game comes with a certain op-
	erating system whose name we can’t remember. The goal of the game is to find where
	all the mines are located within a M × N field.
	The game shows a number in a square which tells you how many mines there are
	adjacent to that square. Each square has at most eight adjacent squares. The 4×4 field
	on the left contains two mines, each represented by a “*” character. If we represent the
	same field by the hint numbers described above, we end up with the field on the right:
	*...
	....
	.*..
	....
	*100
	2210
	1*10
	1110
	Input
	The input will consist of an arbitrary number of fields. The first line of each field
	contains two integers n and m (0 <n,m <= 100) which stand for the number of lines
	and columns of the field, respectively. Each of the next n lines contains exactly m
	characters, representing the field.
	Safe squares are denoted by “.” and mine squares by “*,” both without the quotes.
	The first field line where n = m = 0 represents the end of input and should not be
	processed.
	Output
	For each field, print the message Field #x: on a line alone, where x stands for the
	number of the field starting from 1. The next n lines should contain the field with the
	“.” characters replaced by the number of mines adjacent to that square. There must
	be an empty line between field outputs.
	Sample Input
	44
	*...
	....
	.*..
	....
	35
	**...
	.....
	.*...
	00
	Sample Output
	Field #1:
	*100
	2210
	1*10
	1110
	Field #2:
	**100
	33200
	1*100
*/


public class Problem2 {
	
	/*
	  public static void main ( String args[] ) {
	 
		Scanner in = new Scanner(System.in);
		int counter = 0;
		while ( in.hasNext() ) {
			int m = in.nextInt();
			int n = in.nextInt();
			in.nextLine();
			if ( m != 0 || n != 0 ) {
				counter++;
				
					if ( counter != 1 ) {
						System.out.println();
						//System.out.println();
					}
					String field[] = new String[m];
					for ( int i = 0 ; i < m ; i++ ) {
						field[i] = in.nextLine();	
					}
					String solved[] = minesweeper(field);
					
					System.out.println("Field #"+counter+":");
					
					for ( int i = 0 ; i < m ; i++ ) {
						//if ( i != 0 )
							
						System.out.print(solved[i]);
						System.out.println();
					}
				
			}
			else {
				break;
			}
			
			
		}
		
		
	}
	*/
	
	public static String[] minesweeper( String[] field )  {
        int[] dx = { 0, 0, -1, 1, -1, -1, 1, 1 };
        int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };
        String[] result = new String[field.length];
        for ( int i = 0 ; i < field.length ; i++ ) {
            char[] current_row = new char[field[0].length()];
            for ( int j = 0 ; j < current_row.length ; j++ ) {
                if ( field[i].charAt(j) == '*' ) {
                    current_row[j] = '*';
                    continue;
                }
                int counter = 0;
                for ( int k = 0 ; k < dx.length ; k++ ) {
                    if ( check(i+dx[k],j+dy[k],field) )
                         if ( field[i+dx[k]].charAt(j+dy[k]) == '*' ) counter++;
                }
                current_row[j] = (char) (counter + '0');
                if ( field[i].charAt(j) != '.' ) current_row[j] = field[i].charAt(j);
            }
            result[i] = new String(current_row);
            //System.out.println(result[i]);
        }
        return result;
    }
    
    public static boolean check( int i , int j , String[] field ) {
        return i >= 0 && j >= 0 && i < field.length  && j < field[0].length();
    }

}

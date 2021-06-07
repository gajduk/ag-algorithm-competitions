package UVA;

import java.util.Scanner;

public class Rooks {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while ( true ) {;
			String l = in.nextLine();
			if ( l.equals("END") ) break;
			boolean[][] tags = new boolean[15][15];
			int[] rows = new int[15];
			int min = 16;
			populate(tags,0,l,rows);
			for ( int i = 1 ; i < 15 ; ++i ) {
				populate(tags, i, in.nextLine(), rows);
			}
			for ( int i = 0 ; i < (1<<15) ; ++i ) {
				int cr = countOnes(i);int cols = 0;
				for ( int j = 0 ; j <  15 ; ++j ) {
					if ( (i&(1<<j)) == 0 ) {
						cols |= rows[j];
					}
				}
				int cc = countOnes(cols);
				int t = cc>cr?cc:cr;
				if ( t < min ) min = t;
			}
			System.out.println(min);
		}
	}
	
	/*
...............
...............
...............
...............
....#..........
.......#.......
......##.......
.......#.......
...............
...............
...............
..........#....
...............
........##.....
..###..........
END


	*/
	
	private static int countOnes(int i) {
		int answer = 0;
		for ( int k = 0 ; k < 30 ; ++k ) {
			if ( (i&(1<<k)) > 0 ) ++answer;
		}
		return answer;
	}

	private static void populate(boolean[][] tags, int i, String l,
			int[] rows) {
		for ( int k = 0 ; k < tags.length ; ++k ) {
			if ( l.charAt(k) == '#' ) {
				tags[i][k] = true;
				rows[i] += 1<<k;
			}
		}
	}

}

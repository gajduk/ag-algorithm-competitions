import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		String grid[] = new String[m];
		in.nextLine();
		for ( int i = 0 ; i < m ; ++i ) {
			grid[i] = in.nextLine();
		}
		int max[] = new int[m];
		int min[] = new int[m];
		int last_row = 0;
		for ( int i = 0 ; i < m ; ++i ) {
			int j = 0;
			while ( j < grid[i].length() && grid[i].charAt(j) != 'W' ) {
				++j;
			}
			if ( j == n ) {
				min[i] = n-1;
				max[i] = 0;
			}
			else {

				last_row = i;
				min[i] = j;
				while ( j < grid[i].length() ) {
					if ( grid[i].charAt(j) == 'W' ) {
						max[i] = j;
					}
					++j;
				}
			}
		}
		int num_moves = 0;
		int current_pos = 0;
		for ( int i = 0 ; i < m-1 ; ++i ) {
			if ( i % 2 == 0 ) {
				num_moves += Math.max(max[i],max[i+1])-current_pos;
				current_pos = Math.max(max[i],max[i+1]);
			}
			else {
				num_moves += current_pos-Math.min(min[i],min[i+1]);
				current_pos = Math.min(min[i],min[i+1]);
			}
		}
		if ( (m-1) % 2 == 0 ) {
			num_moves += max[m-1]-current_pos;
		}
		else {
			num_moves += current_pos-min[m-1];
		}
		System.out.println((num_moves+last_row));
	}
}
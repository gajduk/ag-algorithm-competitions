package Section1_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Board implements Comparable {
	int start;
	int end;
	
	public Board( int a , int b) {
		start = a;
		end = b;
	}
	
	@Override
	public int compareTo(Object o) {
		Board t = (Board) o;
		return start - t.start;
	}
	
	public int getLength () {
		return end-start;
	}
	
}

public class barn1 {
	
	public static ArrayList<Board> boards = new ArrayList<Board>();
	
	public static int getDistance ( Board a , Board b ) {
		return b.start-a.end;
	}
	
	public static void merge ( ) {
		int min = 1234567;
		int index = 0;
		for ( int i = 0 ; i < boards.size()-1 ; ++i ) {
			if ( min > getDistance(boards.get(i), boards.get(i+1)) ) {
				index = i;
				min = getDistance(boards.get(i), boards.get(i+1));
			}
		}
		boards.add(new Board(boards.remove(index).start, boards.remove(index).end));
		Collections.sort(boards);
	}
	
	public static void mergeAll ( int max_boards ) {
		while ( boards.size() > max_boards ) {
			merge();
		}
	}
	
	public static int getMinimumDistance ( int max_boards ) {
		mergeAll(max_boards);
		int total_length = 0;
		for (Board b : boards) {
			total_length += b.getLength();
		}
		return total_length;
	}
	
	public static void main ( String args[] ) throws IOException {
	 	BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
	    String s_numbers = f.readLine();
	    StringTokenizer tkr_numbers = new StringTokenizer(s_numbers);
	    int m = new Integer(tkr_numbers.nextToken());
	    int s = new Integer(tkr_numbers.nextToken());
	    int c = new Integer(tkr_numbers.nextToken());
	    for ( int i = 0 ; i < c ; ++i ) {
	    	int stall = new Integer(f.readLine());
	    	boards.add(new Board(stall,stall+1));
	    }
	    out.println(getMinimumDistance(m));
	    out.close();
	}
	
	
	

}

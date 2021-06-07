import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


class Pos {
	int pos;
	
	public static final int flips[] = { 819200,933888,466944,200704,575488,320512,160256,78080,35968,20032,10016,4880,2240,1248,624,304 };
	
	Pos( String []s){
		pos = 0;
		for ( int i = 0 ; i < s.length ; ++i ) {
			for ( int k = 0 ; k < s[i].length() ; ++k ) {
				pos += s[i].charAt(k)=='b'?(1<<(k+(4-i)*4)):0;
			}
		}
	}
	
	Pos( Pos s , int k ){
		pos = s.pos^flips[k];
	}
	
	@Override
	public boolean equals(Object obj) {
		Pos p = (Pos) obj;
		return pos == p.pos;
	}
	
	@Override
	public String toString() {
		String res = "";
		for ( int i = 0 ; i < 4 ; ++i ) {
			for ( int k = 0 ; k < 4 ; ++k ) {
				if ( ((1<<k+((4-i)*4)) & pos) > 0 ) res += "1";
				else res += "0";
			}
			res += "\n";
		}
		return res;
	}
	
}

public class Flip {
	
	public static void main(String[] args) {
		String start[] = {"bbbb","bbbb","bbbb","bbbb"};
		String end[] =  {"wbwb","bwwb","wbbb","wbbw"};
		Flip f = new Flip();
		System.out.println(f.min(start, end));
	}
	
	public int min(String[] start, String[] end) {
		
		Pos start_pos = new Pos(start);
		Pos end_pos = new Pos(end);
		if ( start_pos.equals(end_pos) ) {
			return 0;
		}
		ArrayList<Pos> queue = new ArrayList<Pos>();
		boolean visited[] = new boolean[10000000];
		queue.add(new Pos(start));
		visited[start_pos.pos] = true;
		int front = 1;
		int rear = 0;
		int level = 1;
		int border = front;
		while ( ! queue.isEmpty() ) {
			Pos current = queue.remove(0);
			++rear;
			for ( int k = 0 ; k < 16 ; ++k ) {
				Pos next = new Pos(current,k);
				if ( ! visited[next.pos] ) {
					if ( next.equals(end_pos) ) return level;
					queue.add(next);
					visited[next.pos] = true;
					++front;
				}
			}
			if ( border ==rear ) {
				System.out.println(level);
				++level;
				border = front;
			}
		}
	    return -1;
	}
	
}

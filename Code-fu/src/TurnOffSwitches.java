import java.awt.print.Book;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;


public class TurnOffSwitches {
	
	public static void main(String[] args) {
		System.out.println(new TurnOffSwitches().countMoves(new String[]{"101","010","101"}));
	}
	
	public int countMoves(String[] board) {
		long b = 0;
		for ( int i = 0 ; i < board.length ; ++i ) {
			for ( int k = 0 ; k < board.length ; ++k ) {
				b <<= 1;
				if ( board[i].charAt(k) == '1' ) {
					b += 1;
				
				}
			
			}
		}
		LinkedList<Long> queue = new LinkedList<Long>();
		HashSet<Long> visited = new HashSet<Long>();
		visited.add(b);
		queue.addLast(b);
		int di[] = { -1,-1,1,1,0};
		int dk[] = { -1,1,-1,1,0};
		int front = 1;
		int rear = 0;
		int border = front;
		int level = 0;
		int n = board.length;
		int nn = n*n;
		while ( ! queue.isEmpty() ) {
			long current = queue.removeFirst();
			++rear;
			if ( current == 0 ) return level;
			for ( int i = 0 ; i < board.length ; ++i ) {
				for ( int k = 0 ; k < board.length ; ++k ) {
					long tw = 0;
					for ( int w = 0 ; w < di.length ; ++w ) {
						int ni = i+di[w];
						int nk = k+dk[w];
						if ( ni >= 0 && ni < n ) {
							if ( nk >= 0 && nk < n ) {
								int t = (nn-1)-(ni*n+nk);
								tw += 1<<t;
							}
						}
					}
					long s = current^tw;
					if ( !visited.contains(s) ) {
						visited.add(s);
						queue.addLast(s);
						++front;
					}
				}
			}
			if ( border == rear ) {
				border = front;
				++level;
			}
		}
		return -1;
	}

}

package round149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.TreeSet;


public class ProblemC {
	
	static class Field implements Comparable<Field> {
		int i;
		int k;
		public Field(int i, int k) {
			this.i = i;
			this.k = k;
		}
		@Override
		public int compareTo(Field o) {
			if ( i == o.i )
				return k-o.k;
			return i-o.i;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader jin = new BufferedReader(new InputStreamReader(System.in));
		String nums[] = jin.readLine().split(" ");
		int start_i = Integer.parseInt(nums[0]);
		int start_k = Integer.parseInt(nums[1]);
		int end_i = Integer.parseInt(nums[2]);
		int end_k = Integer.parseInt(nums[3]);
		int n = Integer.parseInt(jin.readLine());
		TreeSet<Field> allowed = new TreeSet<Field>();
		for ( int i = 0 ; i < n ; ++i ) {
			nums = jin.readLine().split(" ");
			int r = Integer.parseInt(nums[0]);
			int s = Integer.parseInt(nums[1]);
			int e = Integer.parseInt(nums[2]);
			for ( int k = s ; k <= e ; ++k ) {
				allowed.add(new Field(r, k));
			}
		}
		LinkedList<Field> queue = new LinkedList<Field>();
		TreeSet<Field> visited = new TreeSet<Field>();
		Field start = new Field(start_i, start_k);
		queue.add(start);
		visited.add(start);
		int di[] = {-1,1,0,0,-1,1,-1,1};
		int dk[] = {0,0,-1,1,-1,-1,1,1};
		int front = 1;
		int border = 1;
		int rear = 0;
		int level = 0;
		while ( !queue.isEmpty() ) {
			Field current = queue.removeFirst();
			++rear;
			if ( current.i == end_i && current.k == end_k ) {
				System.out.println(level);
				return;
			}
			for ( int w = 0 ; w < di.length ; ++w ){
				Field next = new Field(current.i+di[w],current.k+dk[w]);
				if ( ! visited.contains(next) && allowed.contains(next) ) {
					visited.add(next);
					++front;
					queue.addLast(next);
				}
			}
			if ( border == rear ) {
				border = front;
				++level;
			}
		}
		System.out.println(-1);
	}

}

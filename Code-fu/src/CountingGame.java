import java.util.ArrayList;


public class CountingGame {
	
	public static void main(String[] args) {
		CountingGame c = new CountingGame();
		System.out.println(c.lastPerson(3, 5));
	}
	
	public int lastPerson(int N, int K) {
		ArrayList<Integer> p = new ArrayList<Integer>(N);
		for ( int i = 1 ; i <= N ; ++i ) p.add(i);
		int idx = 0;
		for ( int i = 1 ; i < N ; ++i ) {
			int counter = 0;
			while ( counter < K ) {
				++idx;
				++counter;
				idx %= p.size();
			}
			p.remove(idx);
			System.out.println(idx);
			System.out.println(p);
		}
		/*
		for ( int i = 1 ; i < N ; ++i ) {
			idx = (idx+K)%p.size();
			p.remove(idx);
			System.out.println(p);
		}
		*/
		return p.get(0);
	}


}

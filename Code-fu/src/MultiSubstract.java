import java.util.LinkedList;


public class MultiSubstract {
	
	public static void main(String[] args) {
		MultiSubstract m = new MultiSubstract();
		System.out.println(m.getMinOperations(38, 18, 541513));
	}
	
    boolean cache[] = new boolean[1000005];
	
	public int getMinOperations(int M, int S, int T) {
		LinkedList<Integer> current_0 = new LinkedList<Integer>();
		current_0.add(1);
		LinkedList<Integer> current_1 = new LinkedList<Integer>();
		for ( int i = 0 ; i <= 1000000 ; ++i ) {
			if ( (i&1) == 1 ) {
				for ( Integer number : current_0 ) {
					if ( number == T ) return i-1;
					if ( check(number-S) ) {
						current_1.add(number-S);cache[number-S] = true;
					}
					if ( check(number*M) ) {
						current_1.add(number*M);cache[number*M] = true;
					}
				}
				current_0.clear();
			}
			else {
				for ( Integer number : current_1 ) {
					if ( number == T ) return i-1;
					if ( check(number-S) ) {
						current_0.add(number-S);cache[number-S] = true;
					}
					if ( check(number*M) ) {
						current_0.add(number*M);cache[number*M] = true;
					}
				}
				current_1.clear();
			}
		}
		return -1; 
	}

	private boolean check(int i) {
		return i>0 && i <= 1000000 && !cache[i];
	}

}

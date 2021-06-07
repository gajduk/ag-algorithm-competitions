import java.util.HashSet;
import java.util.LinkedList;

class State {
	Long state;
	int zero_index;
	
	public State(Long state, int zero_index) {
		this.state = state;
		this.zero_index = zero_index;
	}
	
	@Override
	public String toString() {
		return state+"";
	}
	
}


public class CodefuPuzzle {
	
	public static void main(String[] args) {
		CodefuPuzzle c = new CodefuPuzzle();
//		System.out.println(c.swapDigits(513420786L, 8, 9));
		System.out.println(c.minMoves("64785 321"));
	}
	
	long pow[] = { 1L , 
				   10L , 
				   100L , 
				   1000L , 
				   10000L , 
				   100000L , 
				   1000000L , 
				   10000000L , 
				   100000000L , 
				   1000000000L , 
				   10000000000L , 
				   100000000000L , 	};
	long solution = 123456780L;
	
	 public int minMoves(String puzzle) {
		 puzzle = puzzle.replace(" ", "0");
		 if ( new Long(puzzle) == solution ) return 0;
		 int counter = 0;
		 for ( int i = 0 ; i < puzzle.length() ; ++i ) {
			 int ii = new Integer(puzzle.substring(i,i+1));
			 for ( int k = i+1 ; k < puzzle.length() ; ++k ) {
				 int kk = new Integer(puzzle.substring(k,k+1));
				 if ( kk < ii && kk > 0 ) ++counter;
			 }
		 }
		 if ( counter % 2 == 1 ) return -1;
		 HashSet<Long> passed_states = new HashSet<Long>();
		 passed_states.add(new Long(puzzle));
		 LinkedList<State> current = new LinkedList<State>();
		 LinkedList<State> next = new LinkedList<State>();
		 passed_states.add(new Long(puzzle));
		 current.add(new State(new Long(puzzle),8-puzzle.indexOf("0")));
		 int d[] = { -3 , -1 , 1 , 3 };
		 int num_moves = 0;
		 while ( true ) {
			 next.clear();
			 ++num_moves;
			 for ( State s : current ) {
				 for ( int i = 0 ; i < d.length ; ++i ) {
					 if ( check(s.zero_index,s.zero_index+d[i]) ) {
						long new_state = swapDigits(s.state,s.zero_index,s.zero_index+d[i]);
						if ( new_state == solution ) return num_moves;
						if ( new_state > 934520786 ) { System.out.println(s.zero_index+" "+(s.zero_index+d[i])+" "+s.state+" "+new_state);return 0;}
						if ( !passed_states.contains(new_state)) {
							next.add(new State(new_state,s.zero_index+d[i]));
							passed_states.add(new_state);
						}
					 }
				 }
			 }
//			 System.out.println(next);
			 current.clear();
			 ++num_moves;
			 for ( State s : next ) {
				 for ( int i = 0 ; i < d.length ; ++i ) {
					 if ( check(s.zero_index,s.zero_index+d[i]) ) {
						long new_state = swapDigits(s.state,s.zero_index,s.zero_index+d[i]);
						if ( new_state > 934520786 ) { System.out.println(s.zero_index+" "+(s.zero_index+d[i])+" "+s.state+" "+new_state);return 0;}
						if ( new_state == solution ) return num_moves;
						if ( !passed_states.contains(new_state)) {
							current.add(new State(new_state,s.zero_index+d[i]));
							passed_states.add(new_state);
						}
					 }
				 }
			 }
			 next.clear();
		 }
	 }

	private long swapDigits(Long number, int index1, int index2 ) {
		long digit1 = ((number / pow[index1]) % 10);
		long digit2 = ((number / pow[index2]) % 10);	
//		System.out.println(digit1);
//		System.out.println(digit2);
		number -= digit1*pow[index1];number -= digit2*pow[index2];
//		System.out.println(number);
		number += digit1*pow[index2];number += digit2*pow[index1];
//		System.out.println(number);
		return number;
	}

	private boolean check(int i, int d) {
		if ( d >= 0 && d < 9 ) {
			if ( i % 3 == 0 && d == i-1 ) return false; 
			if ( i % 3 == 2 && d == i+1 ) return false; 
			return true;
		}
		return false;
	}

}

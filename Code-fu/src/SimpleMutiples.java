
public class SimpleMutiples {
	
	public int multiple(int n) {
		int m = 1;
		while ( true ) {
			if ( n*m < 0 ) {
				return -1;
			}
			if ( isValid(n*m) ) {
				return n*m;
			}
			++m;
		}
	  }
	
	public boolean isValid( int number ) {
		if ( number == 0 ) return true;
		return ((number%10) == 0 || (number%10) == 1  || (number%10) == 2) && isValid(number/10);
	}

}

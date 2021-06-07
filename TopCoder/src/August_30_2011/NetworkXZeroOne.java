package August_30_2011;

public class NetworkXZeroOne {
	
	public static String reconstruct(String message) {
		String sol1 = "";
		String sol2 = "";
		for ( int i = 0 ; i < message.length() ; ++i ) {
			sol1 += i%2==0 ? 'x' : 'o';
			sol2 += i%2==1 ? 'x' : 'o';
		}
		int index_x = message.indexOf('x');
		if ( index_x == -1 ) {
			int index_o = message.indexOf('o');
			if ( index_o == -1 ) {
				return sol1;
			}
			else  {
				if ( index_o % 2 == 0 ) {
					return sol2;
				}
				else {
					return sol1;
				}
				
			}
		}
		else {
			if ( index_x % 2 == 1 ) {
				return sol2;
			}
			else {
				return sol1;
			}
		}
	}

}

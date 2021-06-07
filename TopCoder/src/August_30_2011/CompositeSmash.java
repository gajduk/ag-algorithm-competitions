package August_30_2011;

import java.util.ArrayList;

public class CompositeSmash {
	
	public boolean isPossible(int N, int target) {
		if ( N == target )
			return true;
		boolean result = true;
		int counter = 0;
		for ( int i = 2 ; i <= (int)Math.sqrt(N) ; ++i ) {
			if ( N % i == 0 ) {
				++counter;
				if ( isPrime(i) && isPrime(N/i) ) {
					if ( i == target || N / i == target ) {
						
					}
					else {
						return false;
					}
				}
				else {
					if ( isPrime(i) ) {
						if ( i == target ) {
							
						}
						else {
							result = result && isPossible(N/i,target);
						}
					}
					else {
						if ( isPrime(N/i) ) {
							if ( N/i == target) {
								
							}
							else {
								result = result && isPossible(i,target);
							}
						}
						else {
							result = result && isPossible(i,target)&&isPossible(N/i,target);
						}
					}
				}
			}
		}
		if ( counter == 0 )
			return false;
		return result;
	}

	private boolean isPrime(int N) {
		if ( N % 2 == 0 && N != 2 ) {
			return false;
		}
		if ( N == 2 ) {
			return true;
		}
		
		for ( int i = 3 ; i <= (int)Math.sqrt(N) ; i+=2 ) {
			if ( N % i == 0  ) {
				return false;
			}
		}
		return true;
	}

	public String thePossible(int N, int target) {
		if ( isPossible(N, target) ) {
			return "Yes";
		}
		return "No";
	}
	
	
	
}

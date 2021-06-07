package srm.round277;

public class UnionOfIntervals {
	
	public static void main(String[] args) {
		int lowerBound[] = { -1500000000 };
		int upperBound[] = { 1500000000  };
		UnionOfIntervals u = new UnionOfIntervals();
		System.out.println(u.nthElement(lowerBound, upperBound, 1500000091));
	}

	int nthElement(int[] lowerBound, int[] upperBound, int n) {
		int lo = Integer.MAX_VALUE;
		int hi = Integer.MIN_VALUE;
		++n;
		for ( int i = 0 ; i < lowerBound.length ; ++i ) {
			if ( lowerBound[i] < lo ) lo = lowerBound[i];
			if ( upperBound[i] > hi ) hi = upperBound[i];
		}
		
		while (true ) {
			int mid = (lo+hi)/2;
			int temp = count(mid,lowerBound,upperBound);
			System.out.println(mid+" "+lo+" "+hi+" "+temp);
			if ( temp == n ) return mid;
			else if ( temp > n ) hi = mid;
				else lo = mid;
		}
	}

	private int count(int mid, int[] lowerBound, int[] upperBound) {
		int count_smaller = 0;
		for ( int i = 0 ; i < lowerBound.length ; ++i ) {
			if ( lowerBound[i] <= mid ) {
				count_smaller += Math.min(upperBound[i],mid)-lowerBound[i]+1;
			}
		}
		return count_smaller;
	}
	
}

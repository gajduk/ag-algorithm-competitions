package srm.round230;

public class SortEstimate {
	
	public static void main(String[] args) {
		SortEstimate s = new SortEstimate();
		System.out.println(s.howMany(37, 12392342));
	}
	
	public double timeRequired ( int c , double n ) {
		return c*n*Math.log(n)/Math.log(2);
	}
	
	public double howMany(int c, int time) {
		double lo = 0;
		double hi = 2000000000;
		for ( int i = 0 ; i < 500 ; ++i ) {
			double mid = (lo+hi)/2;
			double t = timeRequired(c,mid);
			if ( t < time ) lo = mid;
			else hi = mid;
		}
		return (lo+hi)/2;
	}

}

package srm.round267;

import java.util.Arrays;

public class HairCuts {
	
	public static void main(String[] args) {
		HairCuts h = new HairCuts();
		String enter[] =  {"09:00","09:22","09:22"};
		System.out.println(h.maxCut(enter,"10:11"));
	}
	
	public boolean isFeasible ( int enter[] , int last , double min_time ) {
		for ( int i = 0 ; i < enter.length ; ++i ) {
			if ( (last-enter[i]) / (enter.length-i) < min_time+1E-9 ) return false;
		}
		return true;
	}
	
	public double maxCut ( String[] enter , String lastExit ) {
		int enter_seconds[] = new int[enter.length];
		int last_exit_seconds = convertToSec(lastExit);
		for ( int i = 0 ; i < enter.length ; ++i ) {
			enter_seconds[i] = convertToSec(enter[i]);
		}
		Arrays.sort(enter_seconds);
		double hi = last_exit_seconds-enter_seconds[enter_seconds.length-1];
		double lo = 0.0;
		for ( int i = 0 ; i < 10000 ; ++i ) {
			double mid = (hi+lo) / 2;
			if ( isFeasible(enter_seconds, last_exit_seconds, mid) )
					lo = mid;
			else 
					hi = mid;
		}
		double min =  (hi+lo) / 2 / 60;
		if ( min < 5 ) return -1;
		return min;
	}

	public int convertToSec(String string) {
		int hh = Integer.parseInt(string.substring(0,2));
		if ( hh < 9 ) hh += 12;
		hh -= 9;
		int mm = Integer.parseInt(string.substring(3));
		return hh*3600+mm*60;
	}

}

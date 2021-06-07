package srm.round538;

public class LeftOrRight {
	
	public static void main(String[] args) {
		LeftOrRight l = new LeftOrRight();
		System.out.println(l.maxDistance("LL???RRRRRRR???"));
	}
	
	public int maxDistance(String program ) {
		int max = 0;
		for ( int i = 0 ; i <= program.length() ; ++i ) {
			int temp = maxDistanceRec(program.substring(0,i));
			System.out.println(temp);
			if ( temp>max) max = temp;
		}
		return max;
	}

	public int maxDistanceRec ( String program ) {
		int count_l = 0,count_r = 0 ,count_q = 0;
		for ( int i = 0 ; i < program.length() ; ++i ) {
			if ( program.charAt(i) == 'L' ) ++count_l;
			if ( program.charAt(i) == 'R' ) ++count_r;
			if ( program.charAt(i) == '?' ) ++count_q;
		}
		return Math.max(count_l,count_r)-Math.min(count_l,count_r)+count_q;
	}
}
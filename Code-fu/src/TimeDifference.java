import java.util.StringTokenizer;


public class TimeDifference {
	
	public int seconds(String time1, String time2) {
	    return Math.abs(toSec(time2)-toSec(time1));
	}
	
	public int toSec ( String time ) {
		StringTokenizer tkr = new StringTokenizer(time,":");
		int res = 0;
		res += 3600*new Integer(tkr.nextToken());
		res += 60*new Integer(tkr.nextToken());
		res += new Integer(tkr.nextToken());
		return res;
	}

}

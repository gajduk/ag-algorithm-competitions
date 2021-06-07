import java.util.StringTokenizer;


public class FastSki {
	  public String getLast(String[] times) {
		    int max = -1;
		    for ( int i = 0 ; i < times.length ; ++i ) {
		    	int temp = time(times[i]);
		    	if ( temp > max ) max = temp;
		    }
		    return fromSec(max);
	  }
	  
	  private String fromSec(int sec) {
		  int h = sec/3600;
		  int m = (sec%3600)/60;
		  int s = sec%60;
		  return format(h)+":"+format(m)+":"+format(s);
	}

	private String format(int m) {
		if ( m < 10 ) {
			return "0"+m;
		}
		return Integer.toString(m);
	}

	private int time(String time) {
		  StringTokenizer tkr = new StringTokenizer(time,"-");
		  return (toSec(tkr.nextToken())-toSec(tkr.nextToken()))*-1;
		  
	}

	public int toSec( String time ) {
		  StringTokenizer tkr = new StringTokenizer(time,":");
		  int h = Integer.parseInt(tkr.nextToken())*3600;
		  int m = Integer.parseInt(tkr.nextToken())*60;
		  int s = Integer.parseInt(tkr.nextToken());
		  return h+m+s;
	  }
}

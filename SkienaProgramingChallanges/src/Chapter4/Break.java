package Chapter4;

public class Break implements Comparable {
	public int duration;
	public int start_time;
	public int end_time;
	@Override
	public int compareTo(Object arg0) {
		Break t = (Break) arg0;
		if ( t.duration > duration )
			return -1;
		else
			return 1;
	}
	
	public String toString() {
		return ""+start_time+"  "+end_time;
	}
}

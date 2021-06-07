
public class ClockHands {
	
	public String findAngle(String thetime) {
		int hour = Integer.parseInt(thetime.split(":")[0]);
		int min = Integer.parseInt(thetime.split(":")[1]);
		double hangle = (hour%12)*30+min*1.0/2;
		double mangle = min*6;
		double minangle = Math.min(hangle,mangle);
		double maxangle = Math.max(hangle,mangle);
		return String.format("%.2f", (double)Math.min(maxangle-minangle,360-maxangle+minangle));
	}

}


public class RotatedClock {
	
	public static void main ( String args[] ) {
		System.out.println(getEarliest(70,300));
	}
	
	public static String getEarliest(int hourHand, int minuteHand) {
		for ( int x = minuteHand%30 ; x < 359 ; x += 30 ) {
			if ( evaluate(x,hourHand,minuteHand) ) {
				//System.out.println(x);
				return transform(((hourHand-x+360)%360)/30) +":" + transform((minuteHand-x+360)%360/6);
			}
		}
		return "";
	}

	public static String transform(int i) {
		String result = "";
		
		if ( i < 10 ) {
			result += "0";
		}
		result += Integer.toString(i);
		return result;
	}

	public static boolean evaluate(int x, int hourHand, int minuteHand) {
		int left = ((hourHand-x+360)%360)%30;
		int right = ((minuteHand-x+360)%360)/12;
	//	System.out.println(x);
	//	System.out.println((hourHand-x+360)%360+" "+(minuteHand-x+360)%360);
	//	System.out.println(left+" "+right);
	//	System.out.println(transform(((hourHand-x+360)%360)/30) +":" + transform((minuteHand-x+360)%360/6));
	//	System.out.println();
		return left == right;
	}

}

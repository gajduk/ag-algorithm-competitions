import java.math.BigInteger;

//the problem is much complex than originally conceived
public class SmallestPalindorme {

	public static void main(String[] args) {
		System.out.println(nextSmallestPalindomeNumber("12945"));
	}
	
	public static String nextSmallestPalindomeNumber ( String number ) {
		if ( number.length() % 2 == 0 ) {
			
			return "";
		}
		else {
			String next_palin = number.substring(0,number.length()/2)+number.substring(number.length()/2,number.length()/2+1)+new StringBuffer(number.substring(0,number.length()/2)).reverse().toString();
			BigInteger b_next_palin = new BigInteger(next_palin);
			BigInteger b_palin = new BigInteger(number);
			if ( b_next_palin.compareTo(b_palin) < 0 ) {
				b_next_palin = b_next_palin.add(powerOf10(next_palin.length()/2));
			}
			return b_next_palin.toString();
		}
		
	}

	public static BigInteger powerOf10(int exp) {
		BigInteger ten = new BigInteger("10");
		BigInteger res = new BigInteger("1");
		for ( int i = 0 ; i < exp ; ++i ) {
			res = res.multiply(ten);
		}
		return res;
	}
}

import java.math.BigInteger;
import java.security.acl.LastOwnerException;


public class SumOfPowers {


	public static int lastDigit(int N) {
		int result = 0;
		for ( int i = 1 ; i <= N ; ++i ) {
			result += modularPower(i,i+1,10);
			result %= 10;
		}
		return result;
	}
	public static int modularPower ( int base , int exponent , int modulus ) {
    	int result = 1;
    	while ( exponent > 0  ) {
    		if ( (exponent & 1) == 1 ) {
    			result = (result*base)%modulus;
    		}
    		exponent = exponent>>1;
    		base = (base*base)%modulus;
    	}
    	return result;
}
	
	public static void main ( String ags[] ) {
		/*
		for ( int i = 4 ; i < 1000000 ; i += 80000 ) {
			int res1 = lastDigit(i); int res2 = lastDigitTooSlow(i);
			if ( res1 != res2 ) {
				System.out.println(i);
			}
		}
		*/
		System.out.println(lastDigitTooSlow(814280));
	//	System.out.println(lastDigit(420189));
	}
	


	public static int lastDigitTooSlow(int N) {
		BigInteger one = new BigInteger("1");
		BigInteger ten = new BigInteger("10");
		BigInteger n = null;
		BigInteger n_1 = null;
		BigInteger result = new BigInteger("0");
		if ( N >= 500000 ) {
			n = new BigInteger("500000");
			n_1 = new BigInteger("500001");
			for ( int i = 500000 ; i <= N ; ++i ) {
				result = result.add(n.modPow(n_1,ten)).mod(ten);
				n = n.add(one);
				n_1 = n_1.add(one);
			}
			return result.intValue();
		}
		else {
			n = new BigInteger("1");
			n_1 = new BigInteger("2");
			
			for ( int i = 1 ; i <= N ; ++i ) {
				result = result.add(n.modPow(n_1,ten)).mod(ten);
				n = n_1;
				n_1 = n_1.add(one);
			}
			return result.intValue();
		}
		
	}
	
	
}

import java.math.BigInteger;
import java.util.Arrays;


public class Sevens {
	
	public static void main(String[] args) {
		Sevens s = new Sevens();
		int a[]={4,1,4,1};
		System.out.println(s.maxMoney(a));
	}
	
	 public int maxMoney(int[] coins) {
		    return maxMoney(coins,0,coins.length,1);

	 }
	
	 public int maxMoney( int[] coins , int first, int last, int order ) {		
		 	//System.out.println(first+" "+last);
		 if ( order == 1 ) {
		    if ( last - first == 1 ) return coins[first];
		    if ( last - first == 2 ) return Math.max(coins[first],coins[first+1]);
		    int val1 = coins[first]+maxMoney(coins,first+1,last,order*-1);
		    int val2 = coins[last-1]+maxMoney(coins,first,last-1,order*-1);
		    //System.out.println("values:"+val1+" "+val2);
		    return Math.max(val1,val2);  
		 }
		 else {
			 if ( last - first == 1 ) return 0;
			 if ( last - first == 2 ) return Math.min(coins[first],coins[first+1]);
			    int val1 = maxMoney(coins,first+1,last,order*-1);
			    int val2 = maxMoney(coins,first,last-1,order*-1);
			    //System.out.println("values:"+val1+" "+val2);
			    return Math.max(val1,val2);    
		 }
	 }

	
	public static int countSevens( int N ) {
		int m = 1000000007;
		int res = 1;
		for ( int i = 1 ; i < N ; ++i ) {
			res *= 2;
			res %= m;
		}
		res *= N;
		res %= m;
		return res;
	}

	private static void populateFaks(BigInteger[] faks) {
		for ( int i = 0 ; i < 201 ; ++i ) {
			faks[i] = fakt(new BigInteger(i+""));
		}
	}

	private static BigInteger fakt(BigInteger i) {
		if ( i.equals(BigInteger.ZERO) ) return BigInteger.ONE;
		return i.multiply(fakt(i.subtract(BigInteger.ONE)));
	}

}

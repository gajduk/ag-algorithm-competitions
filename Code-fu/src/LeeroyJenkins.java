import java.util.Arrays;
import java.util.TreeMap;


public class LeeroyJenkins {
	
	public static void main(String[] args) {
		LeeroyJenkins lj = new LeeroyJenkins();
		System.out.println(lj.solve(7,4,13));
	}
	
	public int solve(int Start, int N, int M) {
		long coins[] = new long[N];
		coins[0] = Start;
	    for ( int i = 1 ; i < N ; ++i ) {
	    	coins[i] = (((coins[i-1]*1103515245 + 12345)/65536)%M+M)%M;
	    }
		System.out.println(Arrays.toString(coins));
		return 0;
	}

}

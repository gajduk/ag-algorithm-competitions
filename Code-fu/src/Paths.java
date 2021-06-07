import java.util.Arrays;


public class Paths {
	
	public static void main(String[] args) {
		Paths p = new Paths();
		int broken[] = {5};
		System.out.println(p.countPaths(16,broken));
	}
	
	 public int countPaths(int n, int[] broken) {
		 int p[] = new int[n*n];
		 p[1] = 1;
		 boolean broke[] = new boolean[n*n];
		 for ( int i = 0 ; i < broken.length ; ++i ) broke[broken[i]] = true;
		 int idx = 1;
		 for ( int i = 2 ; i <= n ; ++i ) {
			 for ( int k = 1 ; k <=i ; ++k ) {
				 idx++;
				 if ( broke[idx] ) continue;
				 if ( k == 1 ) {
					 p[idx] = p[idx-i+1];
				 }
				 else {
					 if ( k == i ) {
						 p[idx] = p[idx-i]+p[idx-1];
					 }
					 else {
						 p[idx] = p[idx-i]+p[idx-i+1]+p[idx-1];
					 }
				 }
				 p[idx] %= 1000000001;
			 }
		 }
		 return p[idx];
	 }


}

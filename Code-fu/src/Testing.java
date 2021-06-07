
public class Testing {
	
	public static final int N = 1000000;
	
	public static void main(String[] args) {
		long sum1 = 0;
		for ( int i = 0 ; i < 10 ; ++i ) {
			sum1 += (i*(i+1))/2;
			System.out.println(i+"=="+sum1);
		}
		long sum2 = 0;
		int sqr_n = (int) Math.sqrt((double)N);
		for ( int i = 0 ; i < N ; ++i ) {
			for ( int j = i+1 ; j< N ; ++j ) {
				sum2 += sqr_n-i%sqr_n;
				sum2 += j%sqr_n;
				sum2 += (j-i) /sqr_n;
			}
		}
		System.out.println(sum1/(N*(N+1)/2)+" "+sum2/(N*(N+1)/2));
	}
}

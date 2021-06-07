
public class SumOfConsecutiveNumbers {

	public static void main(String[] args) {
		SumOfConsecutiveNumbers s = new SumOfConsecutiveNumbers();
		System.out.println(s.largestN(1000635637));
	}
	
	 public int largestN(int S) {
		 int upper_bound = (int) Math.sqrt((double)(1L+((long)S)*8L));
		 upper_bound = (upper_bound-1)/2+1;
		 long sum = S;
		 for ( long i = upper_bound ; i > 1 ; --i ) {
			 long start = (2*sum-i*i)/(2*i)+1;
			 if ( (i*(2*start+i-1)) == S*2 && start > 0) return (int)i;
		 }
		 return 1;
	 }
	 
}

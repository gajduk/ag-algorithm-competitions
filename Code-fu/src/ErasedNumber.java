public class ErasedNumber {
	
	public static void main(String[] args) {
		ErasedNumber e = new ErasedNumber();
		System.out.println(e.findErased(3, 8));
	}

	public int findErased(int N, int sum) {
		int start = 1;
		int sum1 = 0;
		int del = 0;
		while ( true ) {
			sum1 = (N*(start+start+N-1))/2;
			System.out.println(start+" "+sum1);
			del = sum1-sum;
			if ( del >= start && del < start+N ) {
				return del;
			}
			++start;
		}
	}
}

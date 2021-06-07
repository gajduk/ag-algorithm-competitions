
public class Hamming {

	public int minSumNumber(int[] numbers) {
		int sums[] = new int[numbers.length];
		int min = 100000;
		int ind = -1;
		for ( int i = 0 ; i < numbers.length ; ++i ) {
			for ( int k = 0 ; k < numbers.length ; ++k ) {
				sums[i] += hammingDistance(numbers[i], numbers[k]);
			}
			if ( sums[i] <= min ) {
				if ( sums[i] == min && numbers[i] < numbers[ind] ) {
					ind = i;
				}
				if ( sums[i] < min ) {
					min = sums[i];
					ind = i;
				}
			}
		}
	    return numbers[ind];
	}
	
	public int hammingDistance ( int n1 , int n2 ) {
          int  no = n1^n2;
		  int count =0;
		  while(no!=0){
		      int d = no%2;
		      if(d==1)
		          count++;
		      no = no/2;
		  }
		  return count;
	}
}

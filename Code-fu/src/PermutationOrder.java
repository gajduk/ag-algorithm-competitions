
public class PermutationOrder {
	
	public static void main(String[] args) {
		PermutationOrder p = new PermutationOrder();
		int perm[] = {7,8,6,3,2,1,4,5,12,11,10,9};
		System.out.println(p.getIndex(perm));
	}
	
	 public int getIndex(int[] permutation) {
		  int fak[] = {1,1,2,6,24,120,720,5040,40320,362880,3628800,39916800,479001600};
		  int larger[] = new int[permutation.length];
		  for ( int i = 0 ; i < permutation.length ; ++i ) {
			  for ( int k = i+1 ; k < permutation.length ; ++k ) {
				  larger[i] += permutation[i]>permutation[k] ? 1:0;
			  }
		  }
		  int index = 0;
		  for ( int i = 0 ; i < permutation.length ; ++i ) {
			  index += larger[i]*fak[permutation.length-i-1];
		  }
		  return index+1; 
	 }

}

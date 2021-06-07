import java.util.HashSet;

public class BiggestFactor {
	
	public static void main(String[] args) {
		BiggestFactor b = new BiggestFactor();
		int a[] = { 1,8};
		b.prime(a);
	}
	
	HashSet<Integer> res = new HashSet<Integer>();
	int l[] = new int[8];  
	int[] digits1 = new int[8];
	int len = -1;


	void HeapPermute(int n) {
	  if (n == 1) 
	    AddItem();
	  else {
	    for (int i = 0; i < n; i++)  {
	      HeapPermute(n-1);
	      if (n % 2 == 1)  // if n is odd
	        swap(0, n-1);
	      else            // if n is even
	        swap(i, n-1);
	    }
	  }
	}

	private void swap(int i, int j) {
		int temp = l[i];
		l[i] = l[j];
		l[j] = temp;
	}

	private void AddItem() {
		String q = "";
		for ( int i = 0 ; i < len ; ++i ) {
			q += ""+digits1[l[i]]; 
		}
		res.add(Integer.parseInt(q));
	}

	public int prime(int[] digits) {
		for ( int k = 0 ; k < digits.length ; ++k ) {
			digits1[k] = digits[k];
		}
		for ( int i = 1 ; i < (1<<digits.length) ; ++i ) {
			 int c = 0;
	         for ( int k = 0 ; k < digits.length ; ++k ) {
				if ( (i & (1<<k)) != 0 ) {
					l[c++] = k;
				}
			}
	        len = c;
 			HeapPermute(c);
		}
		System.out.println(res);
		int max = 1;
		for ( Integer num : res ) {
			
			int t =  findMaxPrimeFactor(num);
			System.out.println(t+" "+num);
			if ( t > max ) 
				max = t;
		}
	    return max;
	}
	/*
	int cache[] = new int[10000000];
	
	
    public boolean isPrime(int number){
    	if ( cache[number] == 0 ) {
	    	int q = (int) Math.sqrt(number);
		    for( int i = 2; i <= q ; i++){
			    if((number % i) == 0){
			    	cache[number] = 2;
			    	return false;
			    }
		    }
		    cache[number] = 1;
		    return true;
    	}
    	else {
    		return cache[number] == 1;
    	}
    }
	*/
	public int findMaxPrimeFactor(Integer num) {

		int q = (int) Math.sqrt(num);
		for ( int i = 2 ; i <= q ; ++i ) {
			if ( num%i == 0 ) {
				return Math.max(i,findMaxPrimeFactor(num/i));
			}
		}
		return num;
	}

}

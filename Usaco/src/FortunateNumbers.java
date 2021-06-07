import java.util.ArrayList;


public class FortunateNumbers {
	/*
	public static void main ( String args[] ) {
		int a[] = { 100, 1, 10, 100, 1, 1 };
		int b[] = { 3, 53, 53, 53, 53, 53, 53 };
		int c[] = { 4, 54, 4, 54, 4, 54 };
		System.out.println(getFortunate(a,b,c));
	}
	
	public static boolean all_combinations[] = new boolean[100000];
	
	*/
	
	public int getFortunate(int[] a, int[] b, int[] c) {
		/*
		all_combinations[0] = true;
		addSums(a);
		addSums(b);
		addSums(c);
		int counter = 0;
		for ( int i = 0 ; i < all_combinations.length ; ++i ) {
			if ( all_combinations[i] && isFortunate(i) ) {
				++counter;
			}
		}
		return counter;
		*/
		int counter = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for ( int i = 0 ; i < a.length ; ++i ) {
			for ( int k = 0 ; k < b.length ; ++k ) {
				for ( int w = 0 ; w < c.length ; ++w ) {
					if ( isFortunate(a[i]+b[k]+c[w]) && ! list.contains(a[i]+b[k]+c[w]) ) {
						++counter;
						list.add(a[i]+b[k]+c[w]);
					}
				}
			}
		}
		return counter;
	}

	public boolean isFortunate(int i) {
		while ( i > 0 ) {
			if ( i % 10 != 5 && i % 10 != 8 ) {
				return false;
			}
			i /= 10;
		}
		return true;
	}
/*
	private static void addSums(int[] a) {
		for ( int i = 0 ; i < a.length ; ++i ) {
			for ( int k = all_combinations.length-1 ; k >= 0 ; --k ) {
				if ( all_combinations[k] ) {
					all_combinations[k+a[i]] = true;
				}
			}
		}
	}
	
*/
}

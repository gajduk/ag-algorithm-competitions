import java.util.Arrays;
import java.util.Scanner;


public class Game {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long a[] = new long[n];
		long max[] = new long[n];
		int next_even[] = new int[n];
		int next_odd[] = new int[n];
		int last_even = 0;
		int last_odd = 0;
		for ( int i = 0 ; i < a.length ; ++i ) {
			a[i] = in.nextInt();
			max[i] = Long.MIN_VALUE;
			next_even[i] = -1;
			next_odd[i] = -1;
			if ( a[i]%2 == 0 ) {
				for ( int k = last_even ; k < i ; ++k  ) {
					next_even[k] = i;
				}
				last_even = i;
			}
			else {
				for ( int k = last_odd ; k < i ; ++k  ) {
					next_odd[k] = i;
				}
				last_odd = i;
			}
		}
		max[0] = a[0];
		for ( int m = 0 ; m < a.length-1 ; ++m ) {
//			System.out.println(Arrays.toString(a));
//			System.out.println(Arrays.toString(max));
			if ( max[m] != Long.MIN_VALUE ) {
				if ( next_even[m] != -1 )
					max[next_even[m]] = Math.max(max[m]+a[next_even[m]]-next_even[m]+m,max[next_even[m]]);
				if ( next_odd[m] != -1 )
					max[next_odd[m]] = Math.max(max[m]+a[next_odd[m]]-next_odd[m]+m,max[next_odd[m]]);
			}
		}
//		System.out.println(Arrays.toString(a));
//		System.out.println(Arrays.toString(max));
		String res = "";
		if ( max[a.length-1] == Long.MIN_VALUE ) {
			res = "Impossible";
		}
		else {
			res = max[a.length-1]+"";
		}
		System.out.println(res);
	}

}

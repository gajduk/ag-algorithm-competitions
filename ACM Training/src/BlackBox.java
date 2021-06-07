import java.util.Scanner;


public class BlackBox {
	
	
	static int occ[];
	static int max[]; 
	static int sqrtn;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		sqrtn = 1+(int)Math.sqrt(1000001);
		occ = new int[1000001];
		max = new int[sqrtn];
		int n = in.nextInt();
		for ( int w = 0 ; w < n ; ++w ) {
			String c = in.next();
			int x = in.nextInt();
			if ( c.charAt(0) == '+' ) {
				insert(x);
			}
			else {
				delete(x);
			}
			System.out.println(findMax());
		}
	}

	private static void delete(int x) {
		--occ[x];
		if ( occ[x] == max[getMaxIdx(x)]-1 ) {
			int new_max = 0;
			int group_start = getOccIdx(getMaxIdx(x));
			for ( int i =  group_start; i < group_start+sqrtn&&i<occ.length ; ++i ) {
				if ( occ[i] > new_max ) new_max = occ[i];
			}
			max[getMaxIdx(x)] = new_max;
		}
	}

	private static void insert(int x) {
		++occ[x];
		int max_idx = getMaxIdx(x);
		if ( occ[x] > max[max_idx] ) {
			max[max_idx] = occ[x];
		}
	}
	
	private static int getMaxIdx ( int occ_idx ) {
		return occ_idx/sqrtn;
	}
	
	private static int getOccIdx ( int min_idx ) {
		return min_idx*sqrtn;
	}

	private static int findMax () {
		int max_val = 0;
		int idx = 0;
		for ( int i = 0 ; i < max.length ; ++i ) {
			if ( max_val < max[i] ) {
				max_val = max[i];
				idx = i;
			}
		}
		if ( max_val == 0 ) return 0;
		for ( int i = getOccIdx(idx) ; i < getOccIdx(idx)+sqrtn&&i<occ.length ; ++i ) {
			if ( occ[i] == max_val ) return i;
		}
		return 0;
	}
	
}

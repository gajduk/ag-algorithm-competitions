import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class AV {
	
	public static void main(String[] args) throws Exception {

		try {
//			Scanner in = new Scanner(System.in);
//			
			Scanner in = new Scanner(new FileInputStream("in.txt"));
			int m = Integer.parseInt(in.next());
			ArrayList<Integer> l = new ArrayList<Integer>();
			HashSet<Integer> resenija = new HashSet<Integer>();
			while ( in.hasNext() ) {
				int k = Integer.parseInt(in.next());
				l.add(k);
				if (  k > 10000 ) throw new Exception();
			}
			for ( int i = 0 ; i < (1<<l.size()) ; ++i ) {
				int sum = 0;
				for ( int j = 0 ; j < l.size() ; ++j ) {
					if (  (i&(1<<j)) > 0 ) sum += l.get(j); 
				}
				if ( sum == m ) {
					resenija.add(i);
				}
			}
			int best = (1<<(l.size()+1));
			for ( int i : resenija ) {
				int smallest_set_bit = findSmallestSetBit(i);
				int best_sst = findSmallestSetBit(best);
				if ( smallest_set_bit < best_sst ) {
					best = i;
				}
			}
			if ( resenija.size() > 0 ) {
				System.out.print("0");
				for ( int j = 0 ; j < l.size() ; ++j ) {
					System.out.print(" ");
					if (  (best&(1<<j)) == 0 ) System.out.print(l.get(j));
					else System.out.print("0");
				}
				return;
			}
			ArrayList<Integer> result = new ArrayList<Integer>();
			for ( int i = 0 ; i < l.size() ; ++i ) {
				if ( m == 0 ) result.add(l.get(i));
				else {
					if ( m >= l.get(i) ) {
						result.add(0);
						m -= l.get(i);
					}
					else {
						result.add(l.get(i)-m);
						m = 0;
					}
				}
			}
			System.out.print(m);
			for ( int j = 0 ; j < l.size() ; ++j ) {
				System.out.print(" ");
				System.out.print(result.get(j));
			}
			return;
			
		}
		catch ( Exception e ) {
			System.out.println("ERROR");
		}
	}

	private static int findSmallestSetBit(int n) {
		if ( n == 0 ) return 32;
		int res = 1;
		while ( (n&res) == 0 ) {
			res = res<<1;
		}
		return res;
	}

}

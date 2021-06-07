import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;


public class Ak {
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileInputStream("in.txt"));
//		Scanner in = new Scanner(System.in);
		LinkedList<Integer> neighbours[] = new LinkedList[100];
		for ( int i = 0 ; i < 100 ; ++i ) {
			neighbours[i] = new LinkedList<Integer>();
		}
		while ( in.hasNextInt() ) {
			int a = in.nextInt();
			int b = in.nextInt();
			neighbours[a].add(b);
			neighbours[b].add(a);
		}
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		TreeMap<Integer,Integer> reverse_map = new TreeMap<Integer,Integer>();
		int count = 0;
		for ( int i = 0 ; i < 100 ; ++i ) {
			if ( neighbours[i].size() > 0 ) {
				reverse_map.put(i,count);
				map.put(count++,i);
			}
		}
		if ( count > 35 ) {
			throw new Exception("Number is too big");
		}
		int min = count+1;
		TreeSet<Integer> resenija = new TreeSet<Integer>();
		for ( int i = 0 ; i < (1<<count) ; ++i ) {
			boolean acces_points[] = new boolean[count];
			int c = 0;
			for ( int j = 0 ; j < count ; ++j ) {
				if ( ((1<<j)&i) > 0 ) {
					acces_points[j] = true;++c;
				}
			}
			
			boolean flag = true;
			for ( int k = 0 ; k < count ; ++k ) {
				if ( acces_points[k] ) continue;
				boolean iflag = false;
				for ( Integer n : neighbours[map.get(k)] ) {
						if ( acces_points[reverse_map.get(n)] ) { iflag = true; break; }
				}
				if ( iflag ) continue;
				flag = false;
				break;
			}
			if ( flag ){
				if ( min > c ) {
					resenija = new TreeSet<Integer>();
					min = c;
				}
				if ( min == c )
					resenija.add(i);
			}
		}
		TreeSet<String> resenija_s = new TreeSet<String>();
		for ( Integer i : resenija ) {
			String resenie = "";
			for ( int j = 0 ; j < count ; ++j ) {
				if ( ((1<<j)&i) > 0 ) {
					resenie+=map.get(j)+" ";
				}
			}
			resenija_s.add(resenie.substring(0,resenie.length()-1));
		}
		for ( String s : resenija_s ) System.out.println(s);
	}

}

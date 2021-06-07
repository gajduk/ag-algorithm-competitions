package Chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Elephant {
	int index;
	int weight;
	int iq;
	
	
	public Elephant( int a , int b , int c ) {
		index = a; weight = b; iq = c;
	}
	
	@Override
	public String toString() {
		return index+" "+weight+" "+iq;
	}
}

public class Problem1 {
	
	public static void longestCommonSubsequence ( Elephant a[] , Elephant b[] ) {
		int t[][] = new int[a.length+1][b.length+1];
		for ( int i = 0 ; i < a.length+1 ; ++i ) {
			t[i][0] = 0;
		}
		for ( int i = 0 ; i < b.length+1 ; ++i ) {
			t[0][i] = 0;
		}
		for ( int i = 1 ; i <= a.length ; ++i ) {
			for ( int k = 1 ; k <= b.length ; ++k ) {
				if ( a[i-1] == b[k-1] ) {
					t[i][k] = t[i-1][k-1]+1;
				}
				else {
					t[i][k] = t[i-1][k] > t[i][k-1] ? t[i-1][k] : t[i][k-1];
				}
			}
		}
		System.out.println(t[a.length][b.length]);
		int path[] = new int[t[a.length][b.length]];
		int i = a.length;
		int k = b.length;
		int path_counter = 0;
		while (  i > 0 & k > 0 ) {
			if ( a[i-1] == b[k-1] ) {
				--i;
				--k;
				path[path_counter++] = a[i].index; 
//				System.out.println(a[i]);
			}
			else {
				if (t[i-1][k] > t[i][k-1]) {
					--i;
				}
				else {
					--k;
				}
			}
		}
		for ( i = 0 ; i < path.length ; ++i ) {
			System.out.println(path[path.length-1-i]);
		}
	}
	
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
	}

	private static void test_judge() {
		Scanner in = new Scanner(System.in);
		ArrayList<Elephant> elephants = new ArrayList<Elephant>();
		int counter = 1;
		while ( in.hasNext() ) {
			elephants.add(new Elephant(counter++, in.nextInt(), in.nextInt()));
		}
		Elephant e_iq[] = new Elephant[elephants.size()];
		Elephant e_weight[] = new Elephant[elephants.size()];
		for ( int i = 0 ; i < e_iq.length ; ++i ) {
			e_iq[i] = elephants.get(i);
			e_weight[i] = e_iq[i];
		}
		
		Arrays.sort(e_iq,new Comparator<Elephant>() {

			@Override
			public int compare(Elephant o1, Elephant o2) {
				return o2.iq-o1.iq == 0 ? 1 : o2.iq-o1.iq;
			}
			
		});
		Arrays.sort(e_weight,new Comparator<Elephant>() {
			@Override
			public int compare(Elephant o1, Elephant o2) {
				return o1.weight-o2.weight == 0 ? 1 : o1.weight-o2.weight;
			}
			
		});
		/*
		for ( int i = 0 ; i < e_iq.length ; ++i ) {
			System.out.println(e_iq[i]);
		}
		System.out.println();
		for ( int i = 0 ; i < e_iq.length ; ++i ) {
			System.out.println(e_weight[i]);
		}
		*/
		longestCommonSubsequence(e_iq,e_weight);
	}

	private static void test_file() {
		FileInputStream input_file = null;
		try {
			input_file = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter11 problem1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(input_file);
		ArrayList<Elephant> elephants = new ArrayList<Elephant>();
		int counter = 1;
		while ( in.hasNext() ) {
			elephants.add(new Elephant(counter++, in.nextInt(), in.nextInt()));
		}
		Elephant e_iq[] = new Elephant[elephants.size()];
		Elephant e_weight[] = new Elephant[elephants.size()];
		for ( int i = 0 ; i < e_iq.length ; ++i ) {
			e_iq[i] = elephants.get(i);
			e_weight[i] = e_iq[i];
		}
		
		Arrays.sort(e_iq,new Comparator<Elephant>() {

			@Override
			public int compare(Elephant o1, Elephant o2) {
				return o2.iq-o1.iq == 0 ? 1 : o2.iq-o1.iq;
			}
			
		});
		Arrays.sort(e_weight,new Comparator<Elephant>() {
			@Override
			public int compare(Elephant o1, Elephant o2) {
				return o1.weight-o2.weight == 0 ? 1 : o1.weight-o2.weight;
			}
			
		});
		/*
		for ( int i = 0 ; i < e_iq.length ; ++i ) {
			System.out.println(e_iq[i]);
		}
		System.out.println();
		for ( int i = 0 ; i < e_iq.length ; ++i ) {
			System.out.println(e_weight[i]);
		}
		*/
		longestCommonSubsequence(e_iq,e_weight);
	}

}

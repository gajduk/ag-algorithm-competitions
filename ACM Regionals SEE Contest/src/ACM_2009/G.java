package ACM_2009;

import java.util.Scanner;

public class G {
	public static char pattern[];
	public static char string[];
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			pattern = in.next().toCharArray();
			string = in.next().toCharArray();
			int min = 260001;
//			int min_index = -1;
			for ( int i = 0 ; i < string.length ; ++i ) {
				int temp = getPattern(i,0);
//				System.out.println(i +"  "+temp);
				if ( temp < min ) {
					min = temp;
//					min_index = i;
				}
			}
			if ( min >= 260001 ) {
				min = -1;
			}
			System.out.println(min);
		}
		
	}

	private static int getPattern(int i, int j) {
//		System.out.println(i+" "+j);
		if ( j == pattern.length )
			return 0;
		
		if ( pattern[j] == '*' ) {
			/*
			if ( j == pattern.length-1 ) {
				return 0;
			}
			*/
			if ( i == string.length ) {
				return 260001;
			}
			return Math.min(getPattern(i+1, j),Math.min(getPattern(i, j+1),getPattern(i+1, j+1)));
			/*
			if ( pattern[j+1] == '*' ) {
				return getPattern(i,j+1);
			}
			if ( pattern[j+1] == '?' ) {
				int t_j = j+2;
				int count_q = 1;
				while ( t_j < pattern.length ) {
					if ( pattern[t_j] == '*' ) {
						return getPattern(i,j+1);
					}
					if ( pattern[t_j] != '?' ) {
						int t_i = i;
						while ( t_i < string.length && t_i < count_q && string[t_i] != pattern[t_j] ) {
							++t_i;
						}
						if ( t_i == string.length ) {
							return 260001;
						}
						
					}
					else {
						++count_q;
					}
				}
			}
			if ( pattern[j+1] == string[i] ) {
				return string[i]-'a'+1+getPattern(i+1, j+2);
			}
			return string[i]-'a'+1+getPattern(i+1,j);
			*/
		}
		if ( i == string.length ) {
			return 260001;
		}
		if ( pattern[j] == '?' ) {
			return string[i]-'a'+1+getPattern(i+1, j+1);
		}
		if ( pattern[j] != string[i] )
			return 260001;
		else
			return string[i]-'a'+1+getPattern(i+1, j+1);
	}
	

}

package mendo;

import java.util.Arrays;
import java.util.Scanner;

public class Kompjuteri {
	
	static double table[][];
	static double ucilnici[];
	static double sumi[];
	static double nastavnici[];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		ucilnici = new double[m];
		sumi = new double[m+1];
		nastavnici = new double[n];
		sumi[0] = 0;
		for ( int i = 0 ; i < m ; ++i ) {
			ucilnici[i] = in.nextInt();
			sumi[i+1] = sumi[i]+ucilnici[i];
		}
		for ( int i = 0 ; i < n ; ++i ) {
			nastavnici[i] = in.nextInt();
		}
		table = new double[m][1<<n];
		double min = findMin(m-1,0);
		System.out.println(min);
	}

	private static double findMin(int curr, int used ) {
		if ( curr < 0 ) return 0;
		if ( table[curr][used] == 0  ) {
			double min = sumi[sumi.length-1];
			
			for ( int i = 0 ; i < nastavnici.length ; ++i ) {
				if ( ((1<<i)&used) == 0 ) {
					for ( int k = curr ; k >= 0 ; --k ) {
						double tc = (sumi[curr+1]-sumi[k]) / nastavnici[i];
						if ( tc >= min ) break; 
						double tr = findMin(k-1, used|(1<<i));
						double t = Math.max(tr,tc);
						min = Math.min(min,t);
					}
				}
			}
			table[curr][used] = min;
		}
		return table[curr][used];
	}

}

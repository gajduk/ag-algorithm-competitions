package mendo;

import java.util.Arrays;
import java.util.Scanner;

class Klasa implements Comparable<Klasa> {
	
	public int n;
	public int p;
	
	public Klasa(int n, int p) {
		super();
		this.n = n;
		this.p = p;
	}
	public Klasa() {
		super();
	}
	@Override
	public int compareTo(Klasa k) {
		return p-k.p;
	}
	
}

public class Poplava {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Klasa klasi[] = new Klasa[n];
		for ( int w = 0 ; w < n ; ++w ) klasi[w] = new Klasa(in.nextInt(),in.nextInt());
		Arrays.sort(klasi);
		int sum_people[] = new int[n];
		sum_people[n-1] = klasi[n-1].n;
		for ( int w = n-2 ; w >= 0 ; --w ) sum_people[w] = klasi[w].n+sum_people[w+1];
		int max = 0;
		for ( int i = 0 ; i < n ; ++i ) {
			int t = sum_people[i]*klasi[i].p;
			if ( t > max ) max = t;
		}
		System.out.println(max);
	}

}

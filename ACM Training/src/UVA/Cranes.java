package UVA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class ConstructionSite {
	public List<Crane> cranes;

	public ConstructionSite() {
		cranes = new ArrayList<Crane>(15);
	}
	
	public void addCrane(Crane c ) { cranes.add(c); }	
	
	public boolean hitTest( Crane c ) {
		for ( Crane a : cranes ) {
			if ( a.hitTest(c) ) return true;
		}
		return false;
	}
	
	public int area() {
		int r = 0;
		for ( Crane c : cranes ) r += c.area();
		return r;
	}
	
	@Override
	public String toString() {
		return cranes+"";
	}
	
}

class Crane {
	public int i;
	public int k;
	public int r;
	
	public Crane(int i, int k, int r) {
		super();
		this.i = i;
		this.k = k;
		this.r = r;
	}

	public boolean hitTest( Crane c ) {
		return (i-c.i)*(i-c.i)+(k-c.k)*(k-c.k)<=(r+c.r)*(r+c.r);
	}
	
	public int area() {
		return r*r;
	}
	
	@Override
	public String toString() {
		return "("+i+","+k+") : "+r;
	}

}

public class Cranes {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int w = 0 ; w < t ; ++w ) {
			int n = in.nextInt();
			Crane cranes[] = new Crane[n];
			int max = 0;
			for ( int i = 0 ; i < n ; ++i ) {
				cranes[i] = new Crane(in.nextInt(), in.nextInt(), in.nextInt());
			}
			for ( int i = 0 ; i < (1<<n) ; ++i ) {
				ConstructionSite cs = new ConstructionSite();
				for ( int j = 0 ; j < n ; ++j ) {
					if ( ((1<<j)&i) != 0 ) {
						if ( cs.hitTest(cranes[j]) ) {
							break;
						}
						cs.addCrane(cranes[j]);
					}
					if ( j == n-1 ) {
						if ( max < cs.area() ) max = cs.area();
					}
				}
			}
			System.out.println(max);
		}
	}
	
	

}

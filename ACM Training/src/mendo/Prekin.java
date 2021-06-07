package mendo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

class WeightedQuickUnion  {

	private int parent_ids[];
	private int weight[];
	private int count;
	private int n;

	public WeightedQuickUnion( int n ) {
		this.parent_ids = new int[n];
		this.weight = new int[n];
		Arrays.fill(this.weight, 1);
		for ( int i = 0 ; i < n ; ++i ) this.parent_ids[i] = i;
		this.n = n;
		this.count = n;
	}
	
	public void union(int a, int b) {
		int p = find(a);
		int q = find(b);
		if ( p == q ) return;
		--count;
		if ( weight[q] > weight[p] ) {
			weight[q] += weight[p];
			parent_ids[p] = q;
		}
		else {
			weight[p] += weight[q];
			parent_ids[q] = p;
		}
	}

	public boolean isConneted(int a, int b) {
		return find(a)==find(b);
	}

	public int find(int x) {
		while ( parent_ids[x] != x ) x = parent_ids[x];
		return x;
	}

	public int count() {
		return count;
	}
	
	public int[] get_ids() { return parent_ids; }
	
}

class Vrska implements Comparable<Vrska> {
	public int a;
	public int b;
	public int t;
	public Vrska(int a, int b, int t) {
		super();
		this.a = a;
		this.b = b;
		this.t = t;
	}
	
	@Override
	public String toString() {
		return a+"-"/*+t+"-"*/+b;
	}

	@Override
	public int compareTo(Vrska arg0) {
		return t-arg0.t;
	}
	@Override
	public boolean equals(Object o) {
		Vrska v = (Vrska) o;
		return a==v.a&&b==v.b&&t==v.t;
	}
	
}

public class Prekin {
	
	public static List<Vrska> kruskal ( List<Vrska> vrski , int n ) {
		ArrayList<Vrska> optimal = new ArrayList<Vrska>(n);
		Collections.sort(vrski);
		WeightedQuickUnion uf = new WeightedQuickUnion(n);
		int idx = 0;
		for ( int w = 0 ; w < n-1 ; ++w ) {
			Vrska v = null;
			while ( true ) {
				if ( idx >= vrski.size() ) {
					break; 
				}
				v = vrski.get(idx);
				if ( ! uf.isConneted(v.a,v.b) ) {
					break;
				}
				++idx;
			}
			if ( uf.isConneted(v.a,v.b) ) return null;
			//if ( flag ) return null;
			uf.union(v.a, v.b);
			optimal.add(v);
		}
		return optimal;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<Vrska> vrski = new ArrayList<Vrska>(m);
		for ( int w = 0 ; w < m ; ++w ) {
			vrski.add(new Vrska(in.nextInt()-1,in.nextInt()-1,in.nextInt()));
		}
		List<Vrska> optimal = kruskal(vrski, n);
		int optimal_cost = 0;
		for ( Vrska vs : optimal ) {
			optimal_cost += vs.t;
		}
		System.out.print(optimal_cost);
		int min_sub_optimal = Integer.MAX_VALUE;
		for ( int i = 0 ; i < optimal.size() ; ++i ) {
			vrski.remove(optimal.get(i));
			List<Vrska> sub_optimal = kruskal(vrski, n);
			vrski.add(optimal.get(i));
			if ( sub_optimal == null ) continue;
			int sub_optimal_cost = 0;
			for ( Vrska vs : sub_optimal ) {
				sub_optimal_cost += vs.t;
			}
			min_sub_optimal = min_sub_optimal<sub_optimal_cost?min_sub_optimal:sub_optimal_cost;	
		}
		System.out.println(" "+min_sub_optimal);
	}
	

}

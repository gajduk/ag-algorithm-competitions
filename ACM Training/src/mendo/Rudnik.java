package mendo;

import java.util.Scanner;

import java.util.Arrays;

class WeightedQuickUnionFindWithPathCompression  {


	private int parent_ids[];
	private int weight[];
	private int count;
	private int n;

	public WeightedQuickUnionFindWithPathCompression( int n ) {
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
		while ( parent_ids[x] != x ) {
			int t = x;
			x = parent_ids[x];
			parent_ids[t] = parent_ids[x];
		}
		return x;
	}

	public int count() {
		return count;
	}
	
	public int[] get_ids() { return parent_ids; }
	
}

class DepletionTime implements Comparable<DepletionTime>{
	
	public int ri;
	public int ci;
	public int years;
	
	public DepletionTime(int ri, int ci, int years) {
		super();
		this.ri = ri;
		this.ci = ci;
		this.years = years;
	}

	@Override
	public int compareTo(DepletionTime o) {
		return years-o.years;
	}
	
	@Override
	public String toString() {
		return "("+ri+","+ci+") : "+years;
	}
	
}


public class Rudnik {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		int years[] = new int[k];
		int answers[] = new int[k];
		for ( int i = 0 ; i < k ; ++i ) {
			years[i] = in.nextInt();
		}
		int r = in.nextInt(); int c = in.nextInt();
		int depleted[][] = new int[r][c];
		int mark[][] = new int[r][c];
		DepletionTime depletion_times[] = new DepletionTime[r*c];
		for ( int i = 0 ; i < r ; ++i ) {
			for ( int q = 0 ; q < c ; ++q ) {
				depleted[i][q] = in.nextInt();
				depletion_times[i*c+q] = new DepletionTime(i, q, depleted[i][q]);
				mark[i][q] = -1;
			}
		}
		Arrays.sort(depletion_times);
		//System.out.println(Arrays.toString(depletion_times));
		int dr[] = { -1 , 1 , 0 , 0 };
		int dc[] = { 0 , 0 , 1 , -1 };
		int dt_idx = depletion_times.length-1;
		int blocked = depletion_times.length;
		WeightedQuickUnionFindWithPathCompression uf = new WeightedQuickUnionFindWithPathCompression(r*c);
		for ( int i = k-1 ; i >= 0 ; --i ) {
			while ( dt_idx >= 0 && depletion_times[dt_idx].years > years[i] ) {
				int ri = depletion_times[dt_idx].ri;
				int ci = depletion_times[dt_idx].ci;
				mark[ri][ci] = 1;
				for ( int w = 0 ; w < dr.length ; ++w ) {
					if ( check(ri+dr[w],ci+dc[w],r,c) ) {
						if ( mark[ri+dr[w]][ci+dc[w]] != -1 ) {
							uf.union(c*ri+ci,c*(ri+dr[w])+ci+dc[w]);
						}
					}
				}
				--blocked;
				--dt_idx;
			}
			/*
			for ( int ri = 0 ; ri < r ; ++ri ) {
				for ( int ci = 0 ; ci < c ; ++ci ) {
					System.out.print(uf.get_ids()[ri*c+ci]+" ");
				}
				System.out.println();
			}
			System.out.println();
			*/
			answers[i] = uf.count()-blocked;
		}
		for (int i = 0; i < answers.length; i++) {
			System.out.println(answers[i]);
		}
	}

	private static boolean check(int i, int j, int r, int c) {
		return i >= 0 && i < r && j >= 0 && j < c;
	}

}

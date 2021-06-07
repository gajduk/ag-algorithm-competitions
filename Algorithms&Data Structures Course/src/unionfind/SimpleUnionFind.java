package unionfind;

import java.util.Arrays;
import java.util.HashSet;

public class SimpleUnionFind extends UnionFind {

	private int set_ids[];
	private int count;
	private int n;
	
	public SimpleUnionFind( int n ) {
		set_ids = new int[n];
		for ( int i = 0 ; i < n ; ++i ) set_ids[i] = i;
		this.n = n;
		count = n;
	}
	
	@Override
	public void union(int a, int b) {
		int p = set_ids[a];
		int q = set_ids[b];
		if ( p == q ) return;
		--count;
		for ( int i = 0 ; i < n ; ++i ) {
			if ( set_ids[i] == q ) set_ids[i] = p;
		}
	}

	@Override
	public boolean isConneted(int a, int b) {
		return find(a)==find(b);
	}

	@Override
	public int find(int x) {
		return set_ids[x];
	}

	@Override
	public int count() {
		return count;
	}
	
	public int[] get_ids() { return set_ids; }

	
	
}

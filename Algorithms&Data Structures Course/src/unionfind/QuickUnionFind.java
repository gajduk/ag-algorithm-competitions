package unionfind;

public class QuickUnionFind extends UnionFind {

	private int parent_ids[];
	private int count;
	private int n;

	public QuickUnionFind( int n ) {
		parent_ids = new int[n];
		for ( int i = 0 ; i < n ; ++i ) parent_ids[i] = i;
		this.n = n;
		count = n;
	}
	
	@Override
	public void union(int a, int b) {
		int p = find(a);
		int q = find(b);
		if ( p == q ) return;
		--count;
		parent_ids[p] = q;
	}

	@Override
	public boolean isConneted(int a, int b) {
		return find(a)==find(b);
	}

	@Override
	public int find(int x) {
		while ( parent_ids[x] != x ) x = parent_ids[x];
		return x;
	}

	@Override
	public int count() {
		return count;
	}
	
	public int[] get_ids() { return parent_ids; }
	
	
	
}

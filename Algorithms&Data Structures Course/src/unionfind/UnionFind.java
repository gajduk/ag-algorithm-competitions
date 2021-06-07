package unionfind;

public abstract class UnionFind {

	public abstract void union( int a, int b );
	
	public abstract boolean isConneted( int a , int b );

	public abstract int find(int x);
	
	public abstract int count();
	
	
}

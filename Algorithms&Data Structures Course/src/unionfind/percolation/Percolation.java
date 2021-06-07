package unionfind.percolation;

import unionfind.WeightedQuickUnionFindWithPathCompression;


public class Percolation {
	
	 private int top_side;
	 private int bottom_side;
	 private int n;
	 private boolean is_open[][];
	 private static final int di[] = { 0 , 0  , -1  , 1 }; 
	 private static final int dj[] = { 1 , -1  , 0  , 0 }; 
	 
	 private WeightedQuickUnionFindWithPathCompression uf;

	 /**
	  * create N-by-N grid, with all sites blocked
	  * @param N
	  */
	 public Percolation(int N)  {
		 n = N;
		 top_side = N*N;
		 bottom_side = N*N+1;
		 uf = new WeightedQuickUnionFindWithPathCompression(n*n+2);
		 is_open = new boolean[n][n];
	 }
	 
	 /**
	  * open site (row i, column j) if it is not already
	  * @param i
	  * @param j
	  */
	 public void open(int i, int j)    {
		 if ( !checkBounds(i, j) ) throw new IndexOutOfBoundsException();
		 for ( int w = 0 ; w < di.length ; ++w ) {
			 int ni = i+di[w];
			 int nj = j+dj[w];
			 if ( checkBounds(ni,nj) ) {
				 if ( isOpen(ni, nj) ) {
					 uf.union(getIdx(i, j), getIdx(ni, nj));
				 }
			 }
		 }
		 if ( i == 1 ) {
			 uf.union(getIdx(i, j), top_side);
		 }
		 if ( i == n ) {
			 uf.union(getIdx(i, j), bottom_side);
		 }
		 is_open[i-1][j-1] = true;
	 }

	/**
	  * is site (row i, column j) open?
	  * @param i
	  * @param j
	  * @return
	  */
	 public boolean isOpen(int i, int j)  {
		 if ( !checkBounds(i, j) ) throw new IndexOutOfBoundsException();
		return is_open[i-1][j-1];
	 }
	 
	 /**
	  * is site (row i, column j) full?
	  */
	 public boolean isFull(int i, int j)  {
		 return !isOpen(i-1, j-1);	
	 }
	 
	 /**
	  * does the system percolate?
	  * @return
	  */
	 public boolean percolates()   {
		 return uf.isConneted(top_side, bottom_side);
	 }
	 
	 private boolean checkBounds ( int i , int j ) {
		 return i >= 1 && j >= 1 && i <= n && j <= n;
	 }
	 
	 private int getIdx ( int i , int j ) {
		 return (i-1)*n+(j-1);
	 }
	
}

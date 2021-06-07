package unionfind.percolation;

public class PercolationStats {

	private double std_dev;
	private double mean;
	public int t;
	
	
	/**
	 * perform T independent computational experiments on an N-by-N grid
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T) {
		if ( N <= 0 || T <= 0 ) throw new java.lang.IllegalArgumentException();
		double pt[] = new double[T];
		for ( int q = 0 ; q < T ; ++q ) {
			Percolation p = new Percolation(N);
			double total = N*N;
			double open = 0;
			while ( ! p.percolates() ) {
				int i,j;
				while ( true ) {					
					i = (int)(Math.random()*N)+1;
					j = (int)(Math.random()*N)+1;
					if ( ! p.isOpen(i, j) ) break;
				}
				p.open(i, j);
				++open;
			}
			pt[q] = open/total;
			mean += open/total;
		}
		mean /= T;
		for ( int q = 0 ; q < T ; ++q ) {
			std_dev += (pt[q]-mean)*(pt[q]-mean);
		}
		std_dev /= T-1;
		t = T;
	}
	
	/**
	 * sample mean of percolation threshold
	 * @return
	 */
	public double mean()        {
		return mean;
	}
	
	/**
	 * sample stddev of percolation threshold
	 * @return
	 */
	public double stddev()        {
		return std_dev;
	}
	
	/**
	 * test client, described below
	 * @param args
	 */
	public static void main(String[] args)  {
		if ( args.length != 2 ) {
			System.err.println("Too few arguments");
			return;
		}
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(N, T);
		double k = 1.96*ps.std_dev/Math.sqrt(ps.t);
		System.out.println("mean                    = "+ps.mean());
		System.out.println("stddev                  = "+ps.stddev());
		System.out.println("95% confidence interval = "+(ps.mean()-k)+", "+(ps.mean()+k));
	}
	
}

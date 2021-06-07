package UVA;

import java.util.Scanner;

public class Lightsinsidea3dGrid {
	/*
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int w = 0 ; w < t ; ++w ) {
			int M = in.nextInt();
			int N = in.nextInt();
			int P = in.nextInt();
			double rmnp = 1.0/(M*N*P);
			double sum = 0;
			for ( int m = 1 ; m <= M ; ++m ) {
				for ( int n = 1 ; n <= N ; ++n ) {
					for ( int p = 1 ; p <= P ; ++p ) {
						double togled = (M-m+1)*(N-n+1)*(P-p+1);
						if ( m != 1  ) {
							togled *= 2;
						}
						if ( n != 1  ) {
							togled *= 2;
						}
						if ( p != 1  ) {
							togled *= 2;
						}
						togled *= m*n*p;
						sum += togled;
					}	
				}
			}
			double avg = rmnp*rmnp*rmnp*sum;
			//System.out.println(avg);
			int K = in.nextInt();
			double on = 0;
			double mnp = M*N*P;
			for ( int i = 0 ; i < K ; ++i ) {
				on = (mnp-on)*avg+on*(1-avg);
			}
			System.out.printf("Case %d: %.8f\n",(w+1),on);
		}
	}
*/

	
	/*
	
	3
	2 3 4 1
	2 3 4 2
	2 3 4 3
	
	6
	2 2 2 1
	2 2 2 2
	2 2 2 3
	2 2 2 4
	2 2 2 5
	2 2 2 6
	
	6
	1 2 2 1
	1 2 2 2
	1 2 2 3
	1 2 2 4
	1 2 2 5
	1 2 2 6
	
	6
	1 3 3 1
	1 3 3 2
	1 3 3 3
	1 3 3 4
	1 3 3 5
	1 3 3 6
	
	*/
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for ( int w = 0 ; w < t ; ++w ) {
			double M = in.nextInt();
			double N = in.nextInt();
			double P = in.nextInt();
			int K = in.nextInt();
			if ( K == 0 ) {
				System.out.printf("Case %d: 0.00000000\n",(w+1));
				continue;
			}
			double sum = 0;
			for ( double m = 0 ; m < M ; ++m ) {
				for ( double n = 0 ; n < N ; ++n ) {
					for ( double p = 0 ; p < P ; ++p ) {
						double tp = 1;
						tp *= 1-(((m*m)+((M-m-1)*(M-m-1)))/(M*M));
						tp *= 1-(((n*n)+((N-n-1)*(N-n-1)))/(N*N));
						tp *= 1-(((p*p)+((P-p-1)*(P-p-1)))/(P*P));
						double tq = 1-tp;
						double off = tq;
						double on = tp;
						double non = 0 , noff = 0;
						double ntp = 0 , ntq = 0;
						int k = K-1;
						while( k > 0 ) {
							if ( (k&1) == 1 ) {
								ntp = on*tq+off*tp;
								ntq = on*tp+off*tq;
								tp = ntp; tq = ntq;
							}
							noff = on*on+off*off;
							non = on*off+off*on;
							on = non; off = noff;
							k = k / 2;
						}
						sum += tp;
					}	
				}
			}
			System.out.printf("Case %d: %.8f\n",(w+1),sum);
		}
	}

}

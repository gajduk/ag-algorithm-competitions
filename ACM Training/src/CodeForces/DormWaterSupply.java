package CodeForces;

import java.util.Arrays;
import java.util.Scanner;

public class DormWaterSupply {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int p = in.nextInt();
		int ins[] = new int[n+1];
		int outs[] = new int[n+1];
		int diams[] = new int[n+1];
		for ( int w = 0 ; w < p ; ++w ) {
			int out = in.nextInt();
			outs[out] = in.nextInt(); 
			diams[out] = in.nextInt(); 
			ins[outs[out]] = out;
		}
		int t = 0;
		for ( int i = 1 ; i <= n ; ++i ) {
			if ( ins[i] > 0 && outs[i] == 0 ) ++t;
		}
//		System.out.println(Arrays.toString(ins));
//		System.out.println(Arrays.toString(outs));
//		System.out.println(Arrays.toString(diams));
		System.out.println(t);
		for ( int i = 1 ; i <= n ; ++i ) {
			if ( ins[i] == 0 && outs[i] > 0 ) {
				System.out.print(i+" ");
				int k = i;
				int d = diams[i];
				while ( outs[outs[k]] > 0 ) {
					k = outs[k];
					d = Math.min(diams[k],d);
				}
				System.out.println(outs[k]+" "+d);
			}
		}
	}

}

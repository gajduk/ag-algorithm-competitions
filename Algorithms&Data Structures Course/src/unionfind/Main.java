package unionfind;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int n = 10000;
	static int test_size = 1000000;
	static double p = 0.2;
	
	public static void main(String[] args) {
		//System.out.println("SimpleUnionFind:"+testSpeed(new SimpleUnionFind(n)));
		//System.out.println("QuickUnionFind:"+testSpeed(new QuickUnionFind(n)));
		//System.out.println("WeightedQuickUnion:"+testSpeed(new WeightedQuickUnion(n)));
		System.out.println("WeightedQuickUnionFindWithPathCompression:"+testSpeed(new WeightedQuickUnionFindWithPathCompression(n)));
		System.out.println("WeightedQuickUnionFindWithExtremePathCompression:"+testSpeed(new WeightedQuickUnionFindWithExtremePathCompression(n)));
		
		/*
		System.out.println("SimpleUnionFind:"+(end_time-start_time));
		Scanner s = new Scanner(System.in);
		SimpleUnionFind uf = new SimpleUnionFind(10);
		String line = s.nextLine();
		StringTokenizer tkr = new StringTokenizer(line," -");
		while ( tkr.hasMoreTokens() ) {
			int p = Integer.parseInt(tkr.nextToken());
			int q = Integer.parseInt(tkr.nextToken());
			if ( ! uf.isConneted(p, q) ) {
				uf.union(p, q);
			}
			System.out.println(Arrays.toString(uf.get_ids()));
		}
		System.out.println(Arrays.toString(uf.get_ids()));
		*/
	}

	private static long testSpeed( UnionFind uf ) {
		long avg = 0;
		for ( int w = 0 ; w < 50 ; ++w ) {
			long start_time = System.currentTimeMillis();
			
			for ( int i = 0 ; i < test_size ; ++i ) {
				int p = (int)(Math.random()*n);
				int q = (int)(Math.random()*n);
				if ( Math.random() < p ) uf.union(p, q);
				else uf.isConneted(p, q);
			}
			long end_time = System.currentTimeMillis();
			avg += end_time-start_time;
		}
		avg /= 50;
		return avg;
	}
	
	
	
}

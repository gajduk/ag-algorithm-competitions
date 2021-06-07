package UVA;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class ArrangeTheNumbersTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		/*
		facts[0] = new BigInteger("1");
		for ( int i = 1 ; i < 1001 ; ++i ) {
			facts[i] = new BigInteger(i+"").multiply(facts[i-1]);
		}
		*/
		for ( int i = 0 ; i < t ; ++i ) {
			int n = in.nextInt();
			int m = in.nextInt();
			int k = in.nextInt();
			System.out.println("Case "+(i+1)+": "+f(n,m,k));
		}	
	}

	/*
	private static BigInteger res = new BigInteger("0");
	private static BigInteger one = new BigInteger("1");
	*/
	private static int counter = 0;
	
	private static int c ( int n, int m, int k ) {
		int nums[] = new int[n];
		counter = 0;
		generatePerms(n,0,nums, new boolean[n],m,k);
		return counter;
	}

	private static void generatePerms(int n, int i, int[] nums,boolean[] taken,int m,int k) {
		if ( i == n ) {
			checkPerm(nums,m,k);
		}
		for ( int q = 0 ; q < n ; ++q ) {
			if ( ! taken[q] ) {
				taken[q] = true;
				nums[i] = q;
				generatePerms(n, i+1, nums, taken, m, k);
				taken[q] = false;
			}
			
		}
	}
	
	/*

	7
	10 6 0
	10 6 1
	10 6 2
	10 6 3
	10 6 4
	10 6 5
	10 6 6
	
	7
	7 7 2
	7 6 2
	7 5 2
	7 4 2
	7 3 2
	7 2 2
	7 1 2
	
	3
	4 4 2
	4 3 2
	4 2 2
	
	4
	5 5 2
	5 4 2
	5 3 2
	5 2 2
	
	5
	10 0 0
	7 0 0
	5 0 0
	4 0 0
	2 0 0

	*/

	private static void checkPerm(int[] nums, int m, int k) {
		//System.out.println(Arrays.toString(nums));
		int count = 0;
		for ( int i = 0 ; i < m ; ++i ) {
			if ( nums[i] == i ) ++count;
		}
		if ( count == k ) ++counter;
		//System.out.println(count==k);
	}
	
	private static int f( int n , int m , int k ) {
		if ( m > n || m < 0 || k < 0 || k > m ) {System.out.println("n:"+n+" m:"+m+" k:"+k+" ="+0);return 0;}
		if ( k == m || m == 0 ) {System.out.println("n:"+n+" m:"+m+" k:"+k+" ="+fact(n-m));return fact(n-m);}
		int res =  f(n,m-1,k)+f(n,m-1,k-1)-f(n,m,k+1);
		System.out.println("n:"+n+" m:"+m+" k:"+k+" ="+res);
		return res;
	}

	private static int fact(int i) {
		int res = 1;
		while ( i > 0 ) {
			res *= i;
			--i;
		}
		return res;
	}
}

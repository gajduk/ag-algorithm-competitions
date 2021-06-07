package CodeForces;

import java.util.ArrayList;
import java.util.Scanner;

public class WellknownNumbers {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int k = in.nextInt();
		f(s,k);
	}
	
	public static void f ( int s , int k ) {
		ArrayList<Integer> prev = new ArrayList<Integer>();
		prev.add(1);
		ArrayList<Integer> possible = new ArrayList<Integer>();
		while ( true ) {
			int t = sum(prev);
			if ( t > s ) break;
			possible.add(t);
			if ( prev.size() == k ) {
				shift(prev);
				prev.set(prev.size()-1, t);
			}
			else {
				prev.add(t);
			}
		}
		possible.add(0);
		ArrayList<Integer> solution = new ArrayList<Integer>();
		findSolution(solution,possible,s,possible.size()-1);
	}

	private static boolean findSolution(ArrayList<Integer> solution,
			ArrayList<Integer> possible, int s, int k ) {
		if ( s == 0 ) {
			System.out.println(solution.size());
			for ( Integer i : solution ) {
				System.out.print(i+" ");
			}
			System.out.println();
			return true;
		}
		if ( s < 0 || k < 0 ) return false;
		solution.add(possible.get(k));
		if ( findSolution(solution,possible,s-possible.get(k),k-1) ) return true;
		solution.remove(solution.size()-1);
		if ( findSolution(solution,possible,s,k-1) ) return true;
		return false;
	}

	private static void shift(ArrayList<Integer> prev) {
		for (int i = 1; i < prev.size(); i++) {
			prev.set(i-1,prev.get(i));
		}
	}

	private static int sum(ArrayList<Integer> prev) {
		int res = 0;
		for (int i = 0; i < prev.size() ; i++) {
			res += prev.get(i);
		}
		return res;
	}

}

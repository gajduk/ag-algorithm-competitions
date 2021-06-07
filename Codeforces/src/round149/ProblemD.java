package round149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProblemD {
	
	public static void main(String[] args) throws IOException {
		BufferedReader jin = new BufferedReader(new InputStreamReader(System.in));
		String nums[] = jin.readLine().split(" ");
		int n = Integer.parseInt(nums[0]);
		int m = Integer.parseInt(nums[1]);
		ArrayList<ArrayList<Integer>> connected = new ArrayList<ArrayList<Integer>>(n);
		for ( int i = 0 ; i < n ; ++i ) {
			connected.add(new ArrayList<Integer>());
		}
		for ( int i = 0 ; i < m ; ++i ) {
			nums = jin.readLine().split(" ");
			int a = Integer.parseInt(nums[0])-1;
			int b = Integer.parseInt(nums[1])-1;
			connected.get(a).add(b);connected.get(b).add(a);
		}
		nums = jin.readLine().split(" ");
		for ( int i = 0 ; i < n ; ++i ) {
			connected.get(i).add(i);
			int goal = Integer.parseInt(nums[i]);
			if ( connected.get(i).size() >= goal ) {
				System.out.println((i+1));
				for ( int w = 0 ; w < goal ; ++w ) {
					if ( w > 0 ) System.out.print(" "); {
						System.out.print(connected.get(i).get(w));
						return;
					}
				}
			}
		}
		System.out.println(-1);
	}

}

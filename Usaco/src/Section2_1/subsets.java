package Section2_1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class subsets {
	
	public static int sum;
	public static int ways = 0;
	public static int N;
	
	public static void countWaysRecursive ( int index , int current_sum ) {
		if ( index > N ) {
			return;
		}
		if ( current_sum == sum ) {
			++ways;
			return;
		}
		else {
			if ( current_sum < sum ) {
				countWaysRecursive(index+1,current_sum+index);
				countWaysRecursive(index+1,current_sum);
			}
		}
			
		
	}
	
	public static long countWays ( int n ) {
		int array[] = new int[n];
		for ( int i = 0 ; i < n ; ++i ) {
			array[i] = i+1;
		}
		if ( n % 4 == 1 || n % 4 == 2 ) {
			return 0;
		}
		return ways(array,(n*(n+1))/4);
	}
	
	public static void main ( String args[] ) {
		long start = System.currentTimeMillis();
		System.out.println(waysDynamic(39));
		long end = System.currentTimeMillis();
		
		System.out.println("Time to dynamic:"+(end-start)/1000+"."+(end-start)%1000);
		start = System.currentTimeMillis();
		System.out.println(countWays(39));
		end = System.currentTimeMillis();

		System.out.println("Time to brute force:"+(end-start)/1000+"."+(end-start)%1000);
	}
	
	public static  long waysDynamic ( int n ) {
		int s = n*(n+1);
		if ( s % 4 != 0 ) 
			return 0;
		s /= 4;
		long d[] = new long[s+1];
		d[0] = 1;
		for ( int i = 1 ; i <= n ; ++i ) {
			for ( int j = s ; j >= i ; --j ) {
				d[j] += d[j-i];
			}
		}
		return d[s]/2;
	}
	
	
	public static long ways(int[] rocks, int amount){
		int[]a = new int[rocks.length / 2 + 1];
		int[]b = new int[rocks.length - a.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = rocks[i];
		}
		for (int i = a.length; i < rocks.length; i++) {
			b[i-a.length] = rocks[i];
		}
		
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		for (int i = 1; i < (1 << a.length); i++) {
			int sum = 0;
			boolean ok = true;
			for(int j=0;j < a.length;j++){
				if ((i & (1<<j)) != 0){
					sum+=a[j];
					if (sum > amount){
						ok = false;
						break;
					}
				}
			}
			if (!ok){
				continue;
			}
			if (map1.containsKey(sum)){
				map1.put(sum,map1.get(sum) + 1); 
			} else {
				map1.put(sum,1);
			}
		}
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		for (int i = 1; i < (1 << b.length); i++) {
			int sum = 0;
			boolean ok = true;
			for(int j=0;j < b.length;j++){
				if ((i & (1<<j)) != 0){
					sum+=b[j];
					if (sum > amount){
						ok = false;
						break;
					}
				}
			}
			if (!ok){
				continue;
			}
			if (map2.containsKey(sum)){
				map2.put(sum,map2.get(sum) + 1); 
			} else {
				map2.put(sum,1);
			}
		}
		
		long total = 0;
		if (map1.containsKey(amount)){
			total += map1.get(amount);
		}
		if (map2.containsKey(amount)){
			total += map2.get(amount);
		}
		
		for (Iterator<Integer> iter = map1.keySet().iterator(); iter.hasNext();) {
			Integer element = iter.next();
			if (map2.containsKey(amount - element)){
				total += (long)map1.get(element) * map2.get(amount - element);

			}
		}
		return total/2;
	}

}

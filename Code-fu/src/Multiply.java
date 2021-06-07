import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.text.html.MinimalHTMLWriter;


public class Multiply {
	/*
	public static boolean hand_brake = false;
	
	 public static int minMultiply(int N) {
		 int usable[] = new int[100];
		 usable[0] = 1;
		 int max_steps = Integer.toBinaryString(N).length()-1;
		 System.out.println(max_steps);
		 hand_brake = false;
		 for ( ; max_steps < 15 && ! hand_brake ; ++max_steps ) {
			 recursive(usable,1,max_steps+1,N);
			 
		 }
		 
		 return max_steps-1;
	 }

	 
	 private static void recursive(int[] usable, int current_step, int max_steps, int n) {
		if ( current_step == max_steps ) {
			for ( int i = 0 ; i < max_steps ; ++i ) {
				if ( usable[i] == n ) {
				//	System.out.println("RUCNA");
					hand_brake = true;
				}
			}
			return;
		}
		for ( int i = 0 ; i < current_step ; ++i ) {
			for ( int j = 0 ; j < current_step ; ++j ) {
				usable[current_step] = usable[i]+usable[j];
				recursive(usable,current_step+1,max_steps,n);
				if ( hand_brake )
					return;
			}
		}
	}
	*/
	
	public static boolean hand_brake = false;
	public static HashSet<Integer> set = new HashSet<Integer>();
	
	 public static int minMultiply(int N) {
		 set.add(1);
		 int max_steps = Integer.toBinaryString(N).length()-1;
		 hand_brake = false;
		 for ( ; max_steps < 16 && ! hand_brake ; ++max_steps ) {
			 recursive(set,1,max_steps+1,N);
		 }
		 
		 return max_steps-1;
	 }

	 
	 private static void recursive(HashSet<Integer> set, int current_step, int max_steps, int n) {
		if ( current_step == max_steps ) {
			for ( int i = 0 ; i < max_steps ; ++i ) {
				/*
				for ( Integer w : set) {
					System.out.print(w+" ");
				}
				System.out.println();
				*/
				if ( set.contains(n) ) {
					hand_brake = true;
				}
			}
			return;
		}
		boolean k_used[] = new boolean[201];
		int temp[] = new int[15];
		int length = 0;
		for ( Integer i : set ) {
			temp[length++] = i;
			
		}
		
		for ( int i = 0 ; i < length ; ++i ) {
			for ( int j = i ; j < length ; ++j ) {
				if ( !set.contains(temp[i]+temp[j]) ) {
					int k = temp[i]+temp[j];
					if ( k < 200 && ! k_used[k] ) {
						k_used[k] = true;
						set.add(k);
						recursive(set,current_step+1,max_steps,n);
						set.remove(k);
						if ( hand_brake )
							return;
					}
				}
			}	
		}
	}

	public static void main ( String args[] ) {
		System.out.println(minMultiply(133));

	 }
}

import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class Skylines {
	
	public static void main(String[] args) {
		Skylines s = new Skylines();
		int floors[] = {10,9,8,7,6,5,4,3,2,1};
		System.out.println(s.count(floors,5));
	}
     
	public int count(int[] floors, int L) {
		int count[] = new int[101];
		for ( int i = 0 ; i < floors.length ; ++i ) ++count[floors[i]];
		int sum = 0;
		for ( int i = 0 ; i < count.length ; ++i ) {
			if ( count[i] > 0 ) {
				--count[i];
				sum += countRecursive(count,i,L-1,floors.length-1);
				++count[i];
			}
		}
		return sum;
	}

	private int countRecursive(int[] count , int max , int buidings , int counter ) {	
//		System.out.println(count[2]+" "+count[3]+" "+max+" "+buidings);
		if ( buidings < 0 ) return 0;
		if ( buidings == 0 && counter == 0 ) return 1;
		if ( counter == 0 ) return 0;
		int sum = 0;
		for ( int i = 0 ; i < count.length ; ++i ) {
			if ( count[i] > 0 ) {
				if ( i > max ) {
					--count[i];
					sum += countRecursive(count,i,buidings-1,counter-1);
					++count[i];
				}
				else {
					--count[i];
					sum += countRecursive(count,max,buidings,counter-1);
					++count[i];
				}
				
			}
		}
		return sum;
	}

    

}

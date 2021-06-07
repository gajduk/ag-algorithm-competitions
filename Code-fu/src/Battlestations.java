import java.io.ObjectInputStream.GetField;
import java.util.Arrays;


public class Battlestations {
	
	public static void main(String[] args) {
		Battlestations b = new Battlestations();
		int a[] = {505,522,832,962,901,300,926,235,983,561,14,378,716,494,919,20,69,173,43};
		System.out.println(b.getMaxPilotSkill(a, 7));
		System.out.println(Arrays.toString(b.cache));
	}
	
	int cache[];
	
	public int getMaxPilotSkill(int[] skills, int K) {
		cache = new int[skills.length];
		for ( int i = 0 ; i < skills.length ; ++i ) cache[i] = -1;
		int d[][] = new int[skills.length][skills.length];
		for ( int i = 0 ; i < skills.length ; ++i ) {
			for ( int k = i ; k < skills.length && k < i+K ; ++k ) {
				d[i][k] = +skills[k];
				if ( k  >  0)
					d[i][k] += d[i][k-1];
				
			}
		}
		//print(d);
	    return max(d,0,K);
	  }

	private int max(int[][] d, int i, int K ) {
		if ( i >= d.length ) return 0;
		if ( cache[i] != -1 ) return cache[i];
		int result = max(d,i+1,K);
		for ( int k = i ; k < d.length && k < i+K ; ++k ) {
			int temp = max(d,k+2,K)+d[i][k];
			if ( result < temp )
				result = temp;
		}
		cache[i] = result;
		return result;
	}

	private void print(int[][] d) {
		for ( int i = 0 ; i < d.length ; ++i ) {
			for ( int k = 0 ; k < d.length ; ++k ) {
				System.out.print(d[i][k]+" ");
			}
			System.out.println();
		}
	}
	
	
}


public class Multiplyv2 {
	
	public static void main(String[] args) {
		Multiplyv2 m = new Multiplyv2();
		System.out.println(m.minMultiply(191));
	}
	
	  public int minMultiply(int N) {
		  flag = 13;
		  int array[] = new int[12];
		  array[0] = 1;
		  int count = 1;
		  for ( int i = 1 ; i < 14 ; ++i ) {
			  dfs(array,N,i,count,2);
			  if ( flag < 12 )
				  return i+1;
		  }
		  return -1;
	  }

	  private void dfs(int[] array, int query , int level, int count, int next_number ) {
		  /*
		  	for ( int i = 0 ; i < count ; ++i ) {
		  		System.out.print(array[i]+" ");
		  	}
		  	System.out.println(next_number);
		  	*/
		  	if ( level == 0 ) return;
		  	array[count++] = next_number;
			for ( int i = 0 ; i < count ; ++i ) {
				int t = array[i]+next_number;
				if ( t == query ) { flag = level; return; }
				dfs(array,query,level-1,count,t);
				if ( flag < 12 ) return;
			}
	  }

	  int flag;
	  
}

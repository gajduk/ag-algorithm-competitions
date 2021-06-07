
public class BestLocals {
	
	  public String getLocalBest(int[] points, String[] names, int start, int end) {
		  int max_indx = 0;
		  for ( int i = start ; i <= end ; ++i ) {
			  if ( points[i] > points[max_indx] ) {
				  max_indx = i;
			  }
		  }
		  return names[max_indx];
	  }

}

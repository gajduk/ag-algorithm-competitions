package srm.round169;

public class FairWorkload {


		public int getMostWork(int[] folders, int workers) {
			int lo = 0;
			int hi = Integer.MAX_VALUE;
			for ( int i = 0 ; i < folders.length ; ++i )
					if ( lo < folders[i] ) lo = folders[i];
			int n = folders.length;
			while ( lo < hi ) {
		      int x = lo + (hi-lo)/2;

		      int required = 1, current_load = 0;
		      for ( int i=0; i<n; ++i ) {
		         if ( current_load + folders[i] <= x ) {
		            // the current worker can handle it
		            current_load += folders[i];
		         }
		         else {
		            // assign next worker
		            ++required;
		            current_load = folders[i];               
		         }
		      }

		      if ( required <= workers )
		         hi = x;
		      else
		         lo = x+1;
		   }
		   return lo;
		}
}

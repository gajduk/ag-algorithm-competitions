
public class LostPlanet {
	
	public int countRightTriangles(String[] starmap) {
	    int row_count[] = new int[starmap.length];
	    int coll_count[] = new int[starmap[0].length()];
	    for ( int i = 0 ; i < row_count.length ; ++i ) {
	    	row_count[i] = -1;
	    	for ( int k = 0 ; k < coll_count.length ; ++k ) {
	    		if ( starmap[i].charAt(k) == '*' ) {
	    			++row_count[i];
	    		}
	    	}
	    }
	    for ( int i = 0 ; i < coll_count.length ; ++i ) {
	    	coll_count[i] = -1;
	    	for ( int k = 0 ; k < row_count.length ; ++k ) {
	    		if ( starmap[k].charAt(i) == '*' ) {
	    			++coll_count[i];
	    		}
	    	}
	    }
	    int total = 0;
	    for ( int i = 0 ; i < row_count.length ; ++i ) {
	    	for ( int k = 0 ; k < coll_count.length ; ++k ) {
	    		if ( starmap[i].charAt(k) == '*' ) {
	    			total += row_count[i]*coll_count[k];
	    			total %= 1000007;
	    		}
	    	}
	    }
	    return total;
	}

}

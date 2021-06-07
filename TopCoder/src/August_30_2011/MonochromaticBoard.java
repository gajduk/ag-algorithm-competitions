package August_30_2011;

public class MonochromaticBoard {
	
	public int theMin(String[] board) {
		int result[][] = new int[board.length][board[0].length()];
		int m = board.length;
		int n = board[0].length();
		int counter = 0;
		if ( m < n ) {
			for ( int i = 0 ; i < m ; ++i ) {
				boolean all = true;
				for ( int j = 0 ; j < n ; ++j ) {
					if ( board[i].charAt(j) != 'B' ) {
						all = false;
						break;
					}
					
				}
				if ( all ) {
					boolean all_black = true;
					for ( int j = 0 ; j < n ; ++j ) {
						if ( result[i][j] != 1 ) {
							all_black = false;
							break;
						}
					}
					if ( ! all_black ) {
						++counter;
						for ( int j = 0 ; j < n ; ++j ) {
							result[i][j] = 1;
						}
					}
				}
			}
			for ( int j = 0 ; j < n ; ++j ) {
				boolean all = true;
				for ( int i = 0 ; i < m ; ++i ) {
					if ( board[i].charAt(j) != 'B' ) {
						all = false;
						break;
					}
					
				}
				if ( all ) {
					boolean all_black = true;
					for ( int i = 0 ; i < m ; ++i ) {
						if ( result[i][j] != 1 ) {
							all_black = false;
							break;
						}
					}
					if ( ! all_black ) {
						++counter;
						for ( int i = 0 ; i < m ; ++i ) {
							result[i][j] = 1;
						}
					}
				}
			}
		}
		else {
			for ( int j = 0 ; j < n ; ++j ) {
				boolean all = true;
				for ( int i = 0 ; i < m ; ++i ) {
					if ( board[i].charAt(j) != 'B' ) {
						all = false;
						break;
					}
					
				}
				if ( all ) {
					boolean all_black = true;
					for ( int i = 0 ; i < m ; ++i ) {
						if ( result[i][j] != 1 ) {
							all_black = false;
							break;
						}
					}
					if ( ! all_black ) {
						++counter;
						for ( int i = 0 ; i < m ; ++i ) {
							result[i][j] = 1;
						}
					}
				}
			}
			for ( int i = 0 ; i < m ; ++i ) {
				boolean all = true;
				for ( int j = 0 ; j < n ; ++j ) {
					if ( board[i].charAt(j) != 'B' ) {
						all = false;
						break;
					}
					
				}
				if ( all ) {
					boolean all_black = true;
					for ( int j = 0 ; j < n ; ++j ) {
						if ( result[i][j] != 1 ) {
							all_black = false;
							break;
						}
					}
					if ( ! all_black ) {
						++counter;
						for ( int j = 0 ; j < n ; ++j ) {
							result[i][j] = 1;
						}
					}
				}
			}
		}
		return counter;
	}

}

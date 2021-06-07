
public class BraveChessKnight {
	
	public static void main() {
		BraveChessKnight a = new BraveChessKnight();
		String  q[] = {"........","........","........","....K...","........","........","........","........"};
		System.out.println(a.shortestQuest(q));
	}
	
	public int shortestQuest(String[] chessboard) {
		boolean possible[][] = new boolean[chessboard.length][chessboard[0].length()];
		for ( int i = 0 ; i < possible.length ; ++i ) {
			for ( int j = 0 ; j < possible.length ; ++j ) {
				possible[i][j] = true;
			}
		}
		for ( int i = 0 ; i < chessboard.length ; ++i ) {
			for ( int j = 0 ; j < chessboard.length ; ++j ) {
				if ( chessboard[i].charAt(j) == 'K') {
					populateKnight(i,j,possible);
				}
				if ( chessboard[i].charAt(j) == 'B') {
					populateBishop(i,j,possible);			
				}
				if ( chessboard[i].charAt(j) == 'R') {
					populateRook(i,j,possible);
				}
				if ( chessboard[i].charAt(j) == 'Q') {
					populateQueen(i,j,possible);
				}

			}
		}
	    return shortestMoves(possible, 0, 0, possible.length, possible.length);
	 }
	
	private void populateQueen(int i, int j, boolean[][] possible) {
		populateBishop(i, j, possible);
		populateRook(i, j, possible);
	}

	private void populateRook(int i, int j, boolean[][] possible) {
		int i1 = i;
		int j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			// --i1;
			 --j1;
		}
		i1 = i;
		j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			 --i1;
			// --j1;
		}
		i1 = i;
		j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			 ++i1;
			// --j1;
		}
		i1 = i;
		j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			 //--i1;
			 ++j1;
		}
	}

	private void populateBishop(int i, int j, boolean[][] possible) {
		int i1 = i;
		int j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			 --i1;
			 --j1;
		}
		i1 = i;
		j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			 --i1;
			 ++j1;
		}
		i1 = i;
		j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			 ++i1;
			 --j1;
		}
		i1 = i;
		j1 = j;
		while ( check(i1, j1, possible, 1)) {
			possible[i1][j1] = false;
			 ++i1;
			 ++j1;
		}
	}

	private void populateKnight(int i, int j, boolean[][] possible) {
		int dx[] = { 2 , 1 , -1 , -2 , -2 , -1 , 1 , 2 };
		int dy[] = { -1 , -2 , -2 , -1 , 1 , 2 , 2 , 1 };
		for ( int w = 0 ; w < dx.length ; ++w ) {
			if ( check(i,j,possible,1) ) {
				possible[i][j] = false;
			}
		}
	}

	private boolean check(int i, int j , boolean a[][] , int t ) {
		return i>=0 && j>= 0 && j< a.length &&i<a.length;
	}

	public int shortestMoves( boolean board[][] , int start_x , int start_y , int end_x , int end_y ) {
		int moves[][] = new int[board.length][board[0].length];
		boolean possible[][] = new boolean[board.length][board[0].length];
		for ( int i = 0 ; i < moves.length ; ++i ) {
			for ( int k = 0 ; k < moves[0].length ; ++k ) {
				moves[i][k] = -1;
			}
		}
		moves[start_x][start_y] = 0;
		possible[start_x][start_y] = true;
		int dx[] = { 0 , 0 , 1 , -1 , -1 , -1 , 1 , 1 };
		int dy[] = { -1 , 1 , 0 , 0 , -1 , 1 , -1 , 1 };
		while ( true ) {
			int buffer[][] = new int[moves.length][moves[0].length];
			boolean new_possible[][] = new boolean[board.length][board[0].length];
			copy(moves,buffer);
			for ( int i = 0 ; i < moves.length ; ++i ) {
				for ( int k = 0 ; k < moves[0].length ; ++k ) {
					if ( possible[i][k] ) {
						for ( int w = 0 ; w < dx.length ; ++w ) {
							if ( check(dx[w]+i,dy[w]+k,board) ) {
								new_possible[dx[w]+i][dy[w]+k] = true;
								moves[dx[w]+i][dy[w]+k] = moves[i][k]+1;
							}
						}
					}
				}
			}
			copy(new_possible,possible);	
			if ( possible[end_x][end_y] == true ) break;
			boolean ok = false;
			for ( int i = 0 ; i < moves.length ; ++i ) {
				for ( int k = 0 ; k < moves[0].length ; ++k ) {
					if ( possible[i][k] == true ) ok = true;
				}
			}
			if ( !ok ) break;
		}
		return moves[end_x][end_y];
	}

	private boolean check(int i, int j , boolean a[][]) {
		return i>=0 && j>= 0 && j< a.length &&i<a.length&& a[i][j];
	}
	
	private void copy(boolean[][] moves, boolean[][] buffer) {
		for ( int i = 0 ; i < moves.length ; ++i ) {
			for ( int k = 0 ; k < moves[0].length ; ++k ) {
				buffer[i][k] = moves[i][k];
			}
		}
	}

	private void copy(int[][] moves, int[][] buffer) {
		for ( int i = 0 ; i < moves.length ; ++i ) {
			for ( int k = 0 ; k < moves[0].length ; ++k ) {
				buffer[i][k] = moves[i][k];
			}
		}
	}

}

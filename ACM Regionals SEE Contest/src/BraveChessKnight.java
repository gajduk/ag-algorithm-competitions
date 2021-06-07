
public class BraveChessKnight {
	
	public static void main( String args[] ) {
		//System.out.println("Hello Tose");
		BraveChessKnight a = new BraveChessKnight();
		//System.out.println("Hello Tose");
		String  q[] = {"........","........","........","....Q...","........","........","........","........"};
		System.out.println(a.shortestQuest(q));
	}
	
	
	public int shortestQuest(String[] chessboard) {
		boolean possible[][] = new boolean[chessboard.length][chessboard[0].length()];
		for ( int i = 0 ; i < possible.length ; ++i ) {
			for ( int j = 0 ; j < possible.length ; ++j ) {
				possible[i][j] = true;
			}
		}
		boolean populated[][] = new boolean[chessboard.length][chessboard[0].length()];
		for ( int i = 0 ; i < chessboard.length ; ++i ) {
			for ( int j = 0 ; j < chessboard.length ; ++j ) {
			//	if (chessboard[i].charAt(j)=='.' ) {
					populated[i][j] = true;
			//	}
			}
		}
		for ( int i = 0 ; i < chessboard.length ; ++i ) {
			for ( int j = 0 ; j < chessboard.length ; ++j ) {
				if ( chessboard[i].charAt(j) == 'K') {
					populateKnight(i,j,possible,populated);
				}
				if ( chessboard[i].charAt(j) == 'B') {
					populateBishop(i,j,possible,populated);			
				}
				if ( chessboard[i].charAt(j) == 'R') {
					populateRook(i,j,possible,populated);
				}
				if ( chessboard[i].charAt(j) == 'Q') {
					populateQueen(i,j,possible,populated);
				}

			}
		}
		//print(possible);
		//print(populated);
		possible[possible.length-1][possible.length-1] = true;
	    return shortestMoves(possible, 0, 0, possible.length-1, possible.length-1);
	 }
	
	private void populateQueen(int i, int j, boolean[][] possible , boolean populated[][]) {
		populateBishop(i, j, possible,populated);
		populateRook(i, j, possible,populated);
	}

	private void populateRook(int i, int j, boolean[][] possible , boolean populated[][]) {
		int i1 = i;
		int j1 = j-1;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			// --i1;
			 --j1;
		}
		i1 = i-1;
		j1 = j;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			 --i1;
			// --j1;
		}
		i1 = i+1;
		j1 = j;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			 ++i1;
			// --j1;
		}
		i1 = i;
		j1 = j+1;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			 //--i1;
			 ++j1;
		}
		possible[i][j] = false;
	}

	private void populateBishop(int i, int j, boolean[][] possible , boolean populated[][]) {
		int i1 = i-1;
		int j1 = j-1;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			 --i1;
			 --j1;
		}
		i1 = i-1;
		j1 = j+1;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			 --i1;
			 ++j1;
		}
		i1 = i+1;
		j1 = j-1;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			 ++i1;
			 --j1;
		}
		i1 = i+1;
		j1 = j+1;
		while ( check(i1, j1, possible, 1,populated)) {
			possible[i1][j1] = false;
			 ++i1;
			 ++j1;
		}
		possible[i][j] = false;
	}

	private void populateKnight(int i, int j, boolean[][] possible , boolean populated[][]) {
		int dx[] = { 2 , 1 , -1 , -2 , -2 , -1 , 1 , 2 };
		int dy[] = { -1 , -2 , -2 , -1 , 1 , 2 , 2 , 1 };
		for ( int w = 0 ; w < dx.length ; ++w ) {
			if ( check(i+dx[w],j+dy[w],possible,1,populated) ) {
				possible[i+dx[w]][j+dy[w]] = false;
			}
		}
		possible[i][j] = false;
	}

	private boolean check(int i, int j , boolean a[][] , int t  , boolean populated[][]) {
		return i>=0 && j>= 0 && j< a.length &&i<a.length && populated[i][j];
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
		boolean buffer[][] = new boolean[moves.length][moves[0].length];
		
		buffer[start_x][start_y] = true;
		int dx[] = { 2 , 1 , -1 , -2 , -2 , -1 , 1 , 2 };
		int dy[] = { -1 , -2 , -2 , -1 , 1 , 2 , 2 , 1 };
		while ( true ) {
			boolean new_possible[][] = new boolean[board.length][board[0].length];
			for ( int i = 0 ; i < moves.length ; ++i ) {
				for ( int k = 0 ; k < moves[0].length ; ++k ) {
					if ( possible[i][k] ) {
						for ( int w = 0 ; w < dx.length ; ++w ) {
							if ( check(dx[w]+i,dy[w]+k,board) && ! buffer[dx[w]+i][dy[w]+k] ) {
								new_possible[dx[w]+i][dy[w]+k] = true;
								moves[dx[w]+i][dy[w]+k] = moves[i][k]+1;
								buffer[dx[w]+i][dy[w]+k] = true;
							}
						}
					}
				}
			}
		//	print(possible);
			copy(new_possible,possible);	
		//	print(possible);
		//	print(moves);
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

	private void print(int[][] moves) {
		for ( int i = 0 ; i < moves.length ; ++i ) {
			for ( int j = 0 ; j < moves.length ; ++j ) {
				System.out.print(moves[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private void print(boolean[][] possible) {
		for ( int i = 0 ; i < possible.length ; ++i ) {
			for ( int j = 0 ; j < possible.length ; ++j ) {
				System.out.print(possible[i][j]?"1":"0");
			}
			System.out.println();
		}
		System.out.println();
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

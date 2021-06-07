
public class Minesweeper {
	
	  public String solve(String minefield, int n) {
		  int map[][] = new int[n][n];
		  int dx[] = { -1 , 0 , 1 , -1 , 1 , -1 , 0 , 1 };
		  int dy[] = { -1 , -1 , -1 , 0 , 0 , 1 , 1 , 1 };
		  for ( int i = 0 ; i < n ; ++i ) {
			  for ( int k = 0 ; k < n ; ++k ) {
				  if ( minefield.charAt(i*n+k) == 'x' ) {
					  map[i][k] = -1;
				  }
			  }
		  }
		  for ( int i = 0 ; i < n ; ++i ) {
			  for ( int k = 0 ; k < n ; ++k ) {
				  if ( map[i][k] != -1 ) {
					  int counter = 0;
					  for ( int w = 0 ; w < dx.length ; ++w ) {
						  if ( check(n,i+dx[w],k+dy[w]) && map[i+dx[w]][k+dy[w]] == -1 ) ++counter;
					  }
					  map[i][k] = counter;
				  }
			  }
		  }
		  String result = "";
		  for ( int i = 0 ; i < n ; ++i ) {
			  for ( int k = 0 ; k < n ; ++k ) {
				  if ( map[i][k] ==  -1 ) result += "x";
				  else result += map[i][k];
			  }
		  }
		  return result;
	  }

	private boolean check(int n, int i, int j) {
		return i >= 0 && j >= 0 && i < n && j < n;
	}

}

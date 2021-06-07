import java.util.Arrays;


public class ChainReaction {
	
	public static void main(String[] args) {
		ChainReaction c = new ChainReaction();
		String mines[] =    {"4;1","2;1","7;0","2;2","7;1","2;6","6;2","7;7","8;2","8;4","4;6","2;4","5;6","8;0","7;4","1;7","3;3","2;5","1;3"};
		System.out.println(c.minBombs(9,8,mines,2,3));
	}
	
	  class Mine {
		  int x;
		  int y;
		  
		  public Mine( String q ) {
			  int asd[] = getCoddes(q);
			  x = asd[0];
			  y = asd[1];
		  }
		  
		  public int dist( Mine a ) {
			  return (x-a.x)*(x-a.x)+(y-a.y)*(y-a.y);
		  }
		  
		  public int dist( int x1  ,int y1 ) {
			  return (x-x1)*(x-x1)+(y-y1)*(y-y1);
		  }
		  
	  }
	
	  boolean graph[][];
	  int visited[];
	  
    public boolean canHit( Mine a , Mine b , int blast_radius ) {
		  return a.dist(b) <= blast_radius;
	  }

    public int minBombs(int gridW, int gridH, String[] mines, int mineRadius, int bombRadius) {
		  	 mineRadius *= mineRadius;
		  	 bombRadius *= bombRadius;
		     graph = new boolean[mines.length][mines.length];
		     Mine miness[] = new Mine[mines.length];
		     for ( int i = 0 ; i < mines.length ; ++i ) {
		    	 miness[i] = new Mine(mines[i]);
		     }
		     for ( int i = 0 ; i < mines.length ; ++i ) {
		    	 graph[i][i] = true;
		    	 for ( int k = i+1 ; k < mines.length ; ++k ) {
		    		 graph[i][k] = graph[k][i] = canHit(miness[i],miness[k],mineRadius);
			     }
		     }
		     int min = markComponents(graph);
		     int w = 1;
//		     System.out.println( blowItUp(gridW,gridH,miness,1,new boolean[min],bombRadius));
//		     System.out.println( blowItUp(gridW,gridH,miness,2,new boolean[min],bombRadius));
		     
		     while ( ! blowItUp(gridW,gridH,miness,w++,new boolean[min],bombRadius) ) {
		    	 
		     }
		     
		     return w;
		     
	  }

	private boolean blowItUp(int gridW, int gridH , Mine mines[], int num_bombs  , boolean blown_comps[] , int bomb_radius) {
		if ( blown_comps.length == 0 ) return true;
		boolean flag = true;
		for ( int i = 0 ; i < blown_comps.length ; ++i ) {
			if ( ! blown_comps[i] ) flag = false;
		}
		if ( flag ) return true;
		if ( num_bombs <= 0 ) return false;
		boolean comps[] = new boolean[blown_comps.length];
		for ( int i = 0 ; i < comps.length ; ++i ) {
			comps[i] = blown_comps[i];
		}
		for ( int i = 0 ; i < gridW ; ++i ) {
			for ( int k = 0 ; k < gridH ; ++k ) {
				if ( markHitComps(mines,blown_comps,bomb_radius,i,k) != 0 ) {
					if ( blowItUp(gridW, gridH, mines, num_bombs-1, blown_comps,bomb_radius) ) {
						return true;
					}
					else {
						for ( int w = 0 ; w < comps.length ; ++w ) {
							blown_comps[w] = comps[w];
						}
					}
				}
			}
		}
		return false;
	}

	private int markHitComps(Mine[] mines, boolean[] blown_comps , int bomb_radius , int i , int k ) {
		int res = 0;
		for ( int w = 0 ; w < mines.length ; ++w ) {
			if ( mines[w].dist(i,k) <= bomb_radius ) {
				if ( ! blown_comps[visited[w]-1] ) {
					 blown_comps[visited[w]-1] = true;
					 ++res;
				}
			}
		}
		return res;
	}

	private int markComponents(boolean[][] graph2) {
		visited = new int[graph2.length];
		int res = 0;
		for ( int i = 0 ; i < visited.length ; ++i ) {
			if ( visited[i] == 0 ) {
				++res;
				traverse(i,visited,graph2,res);
			}
		}
		return res;
	}

	private void traverse(int i, int[] visited, boolean[][] graph2 , int set ) {
		visited[i] = set;
		for ( int k = 0 ; k < visited.length ; ++k ) {
			if ( visited[k] == 0 && graph2[i][k] ) {
				traverse(k, visited, graph2,set);
			}
		}
	}

	private int[] getCoddes(String guess) {
		int res[] = new int[2];
		res[0] = Integer.parseInt(guess.substring(0,guess.indexOf(";")));
		res[1] = Integer.parseInt(guess.substring(guess.indexOf(";")+1));
		return res;
	}

}

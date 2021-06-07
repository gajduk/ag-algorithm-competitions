import java.util.ArrayList;

public class PrisonBreak {
	
	
	public static void main(String[] args) {
		System.out.println(new PrisonBreak().escapeTime(new String[]{"####", "P##E", "....", "7###"}));
	}
	
	int di[] = { -1,-1,0,1,1,1,0,-1 };
	int dk[] = { 0,1,1,1,0,-1,-1,-1 };
	
	int ddi[] = { -1,0,0,1,0 };
	int ddk[] = { 0,-1,1,0,0 };
	
	public int escapeTime(String[] yard) {
		
		ArrayList<Integer> wi = new ArrayList<Integer>();
		ArrayList<Integer> wk = new ArrayList<Integer>();
		ArrayList<Integer> wp = new ArrayList<Integer>();
		int pi=0,pk=0,ei=0,ek=0;
		for ( int i = 0 ; i < yard.length ; ++i ) {
			for ( int k = 0 ; k < yard.length ; ++k ) {
				if ( Character.isDigit(yard[i].charAt(k)) ) {
					wi.add(i);
					wk.add(k);
					wp.add(Integer.parseInt(yard[i].charAt(k)+""));
				}
				if ( yard[i].charAt(k) == 'P' ) {
					pi = i;
					pk = k;
				}
				if ( yard[i].charAt(k) == 'E' ) {
					ei = i;
					ek = k;
				}
			}
		}
		boolean possible[][] = new boolean[yard.length][yard.length];
		possible[pi][pk] = true;
		for ( int time = 0 ; time < 10000 ; ++time ) {
			if ( possible[ei][ek] ) return time;
//			print(possible);
			boolean next_possible[][] = new boolean[yard.length][yard.length];
			boolean walkable[][] = getWalkable(wi, wk, wp, yard, time);
			boolean walkable1[][] = getWalkable(wi, wk, wp, yard, time+1);
			for ( int i = 0 ; i < possible.length ; ++i ) {
				for ( int k = 0 ; k < possible.length ; ++k ) {
					if ( possible[i][k] ) {
						for ( int w = 0 ; w < ddi.length ; ++w ) {
							int ni = i+ddi[w];
							int nk = k+ddk[w];
							try {
								if ( walkable[ni][nk] && walkable1[ni][nk] ) {
									next_possible[ni][nk] = true;
								}
							}
							catch (Exception e) {
							}
						}
					}
				}
			}
			
			for ( int i = 0 ; i < possible.length ; ++i ) {
				for ( int k = 0 ; k < possible.length ; ++k ) {
					possible[i][k] = next_possible[i][k];
				}
			}
		}
		/*
		boolean walkable[][] = getWalkable(wi,wk,wp,yard,0);
		print(walkable);
		walkable = getWalkable(wi,wk,wp,yard,1);
		print(walkable);
		walkable = getWalkable(wi,wk,wp,yard,2);
		print(walkable);
		walkable = getWalkable(wi,wk,wp,yard,3);
		print(walkable);
		walkable = getWalkable(wi,wk,wp,yard,4);
		print(walkable);
		walkable = getWalkable(wi,wk,wp,yard,5);
		print(walkable);
		*/
	    return -1;
	}

	private void print(boolean[][] walkable) {
		System.out.println();
		for ( int i = 0 ; i < walkable.length ; ++i ) {
			for ( int k = 0 ; k < walkable.length ; ++k ) {
				System.out.print(walkable[i][k]?"1":"0");
			}
			System.out.println();
		}
		System.out.println();
	}

	private boolean[][] getWalkable(ArrayList<Integer> wi,
			ArrayList<Integer> wk, ArrayList<Integer> wp, String[] yard,int time) {
		boolean result[][] = new boolean[yard.length][yard.length];
		for ( int i = 0 ; i < yard.length ; ++i ) {
			for ( int k = 0 ; k < yard.length ; ++k ) {
				if ( yard[i].charAt(k) != '#' ) result[i][k] = true;
			}
		}
		for ( int i = 0 ; i < wi.size() ; ++i ) {
			int wwi = wi.get(i);
			int wwk = wk.get(i);
			int wwp = wp.get(i);
			result[wwi][wwk] = false;
			try {
				int ni = di[(wwp+time)%8]+wwi;
				int nk = dk[(wwp+time)%8]+wwk;
				result[ni][nk] = false;
			}
			catch (Exception e) {
			}
		}
		return result;
	}

}


public class FootballStickers {
	
	public static void main(String[] args) {
		FootballStickers f = new FootballStickers();
		int stickers[] = {1,2,3,2,1,1,2,3,1,1,1};
		System.out.println(f.getStartPoint(stickers, 4));
	}
	

	public int getStartPoint(int[] stickers, int friends) {
		int best = -1;
		int best_indx = -1;
		for ( int i = 0 ; i < friends ; ++i ) {
			int counter = 0;
			boolean buffer[] = new boolean[30];
			for ( int k = 0 ; k < stickers.length/friends ; ++k ) {
				counter += buffer[stickers[i+k*friends]]?0:1;
				buffer[stickers[i+k*friends]] = true;
			}
			if ( counter > best ) {
				best = counter;
				best_indx = i;
			}
		}
		return best_indx;
	}
}

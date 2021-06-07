import java.util.ArrayList;
import java.util.Arrays;


public class BasketballTraining {
	
	public static void main ( String args[] ) {
		String test[] =
			{
			".......S.......",
			"...............",
			"...............",
			".......B.......",
			"..........B....",
			"...............",
			"...............",
			"...B...........",
			"...............",
			"...............",
			"...............",
			"...............",
			"...............",
			"...........B...",
			"...............",
			"...............",
			"...............",
			"...............",
			"...............",
			"..B............",
			"...............",
			"...............",
			"...............",
			"...........B...",
			"...............",
			"...............",
			"...............",
			"...............",
			"...............",
			"......E........"
			};
		System.out.println(reachTheRim(test, 35));
	}
	
	 public static int reachTheRim(String[] map, int startingStamina) {
		 int best[][] = new int[map.length][map[0].length()];
		 int start_x = 0;
		 int start_y = 0;
		 int end_x = 0;
		 int end_y = 0;
		 for ( int i = 0 ; i < best.length ; ++i ) {
			 for ( int k = 0 ; k < best[0].length ; ++k ) {
				 best[i][k] = -1;
				 if ( map[i].charAt(k) == 'S' ) {
					 best[i][k] = startingStamina;
					 start_x = i;
					 start_y = k;
				 }
				 if ( map[i].charAt(k) == 'E' ) {
					 end_x = i;
					 end_y = k;
				 }
			 }
		 }
		 ArrayList<Integer> queue_x = new ArrayList<Integer>();
		 ArrayList<Integer> queue_y = new ArrayList<Integer>();
		 queue_x.add(start_x);
		 queue_y.add(start_y);
		 int dx[] = { 0,0,-1,1 };
		 int dy[] = { -1,1,0,0 };
		 while ( !queue_x.isEmpty() ) {
//			 for ( int i = 0 ; i < map.length ; ++i ) {
//				 System.out.println(Arrays.toString(best[i]));
//			 }
//			 System.out.println();
			 int current_x = queue_x.remove(0);
			 int current_y = queue_y.remove(0);
			 for ( int w = 0 ; w < dx.length ; ++w ) {
				 if ( check(map,current_x+dx[w],current_y+dy[w]) ) {
//					 System.out.println(current_x+" "+current_y+" "+w);
					 int new_stamina = best[current_x][current_y]+(map[current_x+dx[w]].charAt(current_y+dy[w]) == 'B' ? 0 : -1);
//					 System.out.println(new_stamina);
					 if ( new_stamina > best[current_x+dx[w]][current_y+dy[w]] ) {
						 best[current_x+dx[w]][current_y+dy[w]] = new_stamina;
						 queue_x.add(current_x+dx[w]);
						 queue_y.add(current_y+dy[w]);
						 
					 }
				 }
			 }
		 }
		 
		 return best[end_x][end_y] > startingStamina ? -1 : best[end_x][end_y]; 
	 }

	public static boolean check(String[] map, int i, int j) {
		return i >= 0 && j >= 0 && i < map.length && j < map[0].length() && map[i].charAt(j) != '#';
	}

}

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.StringTokenizer;


class Position implements Comparable<Position> {
	int x;
	int y;
	
	public Position ( int a , int b ) {
		x = a; y = b;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

	@Override
	public int compareTo(Position o) {
		return x-o.x;
	}
	
	
	
}

public class CrateRacing {

	
	 public int getCrates(int trackLength, String crates, String fuel) {
		StringTokenizer tkr = new StringTokenizer(crates," ,");
		Position crates_position[] = new Position[tkr.countTokens()/2];
		int counter = 0;
		while ( tkr.hasMoreTokens() ) {
			crates_position[counter++] = new Position(Integer.parseInt(tkr.nextToken()),Integer.parseInt(tkr.nextToken()));
		}
		tkr = new StringTokenizer(fuel," ,");
		Position fuel_position[] = new Position[tkr.countTokens()/2];
		counter = 0;
		while ( tkr.hasMoreTokens() ) {
			fuel_position[counter++] = new Position(Integer.parseInt(tkr.nextToken()),Integer.parseInt(tkr.nextToken()));
		}
		Arrays.sort(fuel_position);
		for ( int i = 0 ; i < fuel_position.length-1 ; ++i ) {
			if ( Point2D.distance(fuel_position[i].x, fuel_position[i].y, fuel_position[i+1].x, fuel_position[i+1].y) > 40 ) {
				return -1;
			}
		}
		if ( fuel_position.length == 0 ) {
			if ( trackLength > 50 ) {
				return -1;
			}
		}
		else {
			if ( Point2D.distance(fuel_position[0].x, fuel_position[0].y,0,0) > 40 ) {
				return -1;
			}
			if ( Point2D.distance(fuel_position[counter-1].x, fuel_position[counter-1].y,trackLength,0) > 40 ) {
				return -1;
			}
		}
		return crates.length()/3;
	 }
}

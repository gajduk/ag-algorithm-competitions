package ACM_2005;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Scanner;

class Position implements Comparable<Position>{
	int x;
	int y;
	
	public Position ( int a , int b ) {
		x = a; y = b;
	}

	@Override
	public int compareTo(Position o) {
		return x-o.x;
	}
	
	
}

public class D {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			int num_points = in.nextInt();
			Position points[] = new Position[num_points];
			for ( int i = 0 ; i < num_points ; ++i ) {
				points[i] = new Position(in.nextInt(),in.nextInt());
			}
			Arrays.sort(points);
			double length = 0.0;
			for ( int i = 0 ; i < num_points-1 ; ++i ) {
				length += distance(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
			}
			length += distance(points[0].x, points[0].y, points[num_points-1].x, points[num_points-1].y);
			System.out.printf("%.2f\n",length);
		}
		
	}
	
	public static double distance ( int x1 , int y1 , int x2 , int y2 ) {
		int dx = x1-x2;
		int dy = y1-y2;
		return Math.sqrt(dx*dx+dy*dy);
	}

}

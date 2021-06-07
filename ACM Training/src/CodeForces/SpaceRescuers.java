package CodeForces;

import java.util.Arrays;
import java.util.Scanner;

class Planet {
	double x;
	double y;
	double z;
	public Planet(long x, long y, long z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	
	public Planet(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double distance ( Planet a) {
		double dx = x-a.x;double dy = y-a.y;double dz = z-a.z;
		return dx*dx+dy*dy+dz*dz;
	}

	@Override
	public String toString() {
		return "Planet [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
	
	
}

/*

2
-5 0 0
0 0 5

*/

public class SpaceRescuers {
	
	public static final double MIN = -10001;
	public static final double MAX =  10001;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Planet planets[] = new Planet[n];
		for ( int i = 0 ; i < n ; ++i ) {
			planets[i] = new Planet(in.nextLong(),in.nextLong(),in.nextLong());
		}
		Planet station = new Planet(0,0,0);
		double min_cost = ternaryX(planets,MIN,MAX,0.000001,station);
//		System.out.println(Math.sqrt(min_cost));
		System.out.printf("%.8f %.8f %.8f",station.x,station.y,station.z);
	}

	private static double ternaryX(Planet[] planets, double left, double right,
			double precision , Planet station ) {
		if ( right-left < precision ) {
			station.x = (left+right)/2.0;
			return f(planets,station);
		}
		double new_left = (right-left) /3 + left;
		double new_right = (right-left)/3*2 + left;
		station.x = new_left;
		double lv = ternaryY(planets, MIN, MAX, precision, station);
		station.x =  new_right;
		double rv = ternaryY(planets, MIN, MAX, precision, station);
		if ( lv < rv ) {
			return ternaryX(planets,left,new_right,precision,station);
		}
		else {
			return ternaryX(planets,new_left,right,precision,station);
		}
	}

	private static double ternaryY(Planet[] planets, double left, double right,
			double precision, Planet station ) {
		if ( right-left < precision ) {
			station.y = (left+right)/2.0;
			return f(planets,station);
		}
		double new_left = (right-left) /3 + left;
		double new_right = (right-left)/3*2 + left;
		station.y = new_left;
		double lv = ternaryZ(planets, MIN, MAX, precision,station);
		station.y = new_right;
		double rv = ternaryZ(planets, MIN, MAX, precision,station);
		if ( lv < rv ) {
			return ternaryY(planets,left,new_right,precision,station);
		}
		else {
			return ternaryY(planets,new_left,right,precision,station);
		}
	}
	
	private static double ternaryZ(Planet[] planets, double left, double right,
			double precision, Planet station ) {
		if ( right-left < precision ) {
			station.z = (left+right)/2.0;
			return f(planets,station);
		}
		double new_left = (right-left) /3 + left;
		double new_right = (right-left)/3*2 + left;
		station.z = new_left;
		double lv = f(planets,station);
		station.z = new_right;
		double rv = f(planets,station);
		if ( lv < rv ) {
			return ternaryZ(planets,left,new_right,precision,station);
		}
		else {
			return ternaryZ(planets,new_left,right,precision,station);
		}
	}

	private static double f( Planet[] planets, Planet station ) {
		double max_dist = 0.0;
		for ( int i = 0 ; i < planets.length ; ++i ) {
			double dist = station.distance(planets[i]);
			if ( dist > max_dist ) max_dist = dist;
		}
		return max_dist;
	}


}

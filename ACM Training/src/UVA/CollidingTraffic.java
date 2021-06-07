	package UVA;
	
	import java.util.Arrays;
import java.util.Scanner;
	
/*
 1
4 2
-1000 -1000 45 1
1000 1000 225 1
-1000 1000 135 2
1000 -1000 315 2
 */
	class Boat {
		double x;
		double y;
		double dirr;
		double s;
		
		public Boat(double x, double y, double dirr, double s) {
			super();
			this.x = x;
			this.y = y;
			this.dirr = dirr;
			this.s = s;
		//	normalizedirr();
		}
		
		public Boat() {
			// TODO Auto-generated constructor stub
		}
	
		public void normalizedirr() {
			if ( dirr >= 0 && dirr <= 90 ) {
				dirr = 90 - dirr;
			}
			else {
				dirr = 360+90-dirr;
			}
			dirr = Math.toRadians(dirr);
		}
	
		@Override
		public String toString() {
			/*
			return "Boat [x=" + x + ", y=" + y + ", dirr=" + dirr + ", s=" + s
					+ "]";
					*/
			System.out.printf("%.6f %.6f %.6f %.6f\n",x,y,dirr,s);
			return "";
		}
		
	}
	
	public class CollidingTraffic {
		
		public static void main(String[] args) {
			//test();
			Scanner in = new Scanner(System.in);
			int t = in.nextInt();
			for ( int w = 0 ; w < t ; ++w ) {
				int n = in.nextInt();
				double r = in.nextDouble();
				Boat boats[] = new Boat[n];
				for ( int i = 0 ; i < n ; ++i ) {
					boats[i] = new Boat(in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextDouble());
					boats[i].normalizedirr();
				}
				double min = findMin(n,boats,r);
				if ( min == Double.MAX_VALUE ) System.out.println("No collision.");
				else System.out.printf("%.0f\n",min);
			}
		}
		
		public static void test(){
			int t = 10;
			System.out.println(t);
			double answers[] = new double[t];
			for ( int w = 0 ; w < t ; ++w ) {
				int n = 100;
				double r = Math.random()*10;
				System.out.printf("%d %.6f\n",n,r);
				Boat boats[] = new Boat[n];
				for ( int i = 0 ; i < n ; ++i ) {
					boats[i] = new Boat(Math.random()*2000-1000,Math.random()*2000-1000,Math.random()*360,Math.random()*1000);
					boats[i].toString();
					boats[i].normalizedirr();
				}
				answers[w] = findMin(n, boats, r);
			}
			for ( int i = 0 ; i < t ; ++i ) {
				if ( answers[i] == Double.MAX_VALUE ) System.out.println("No collision.");
				else System.out.printf("%.0f",answers[i]);
			}
		}
	
		private static double findMin(int n, Boat[] boats, double r) {
				double min = Double.MAX_VALUE;
				for ( int i = 0 ; i < n ; ++i ) {
					for ( int j = i+1 ; j < n ; ++j ) {
						double collision_time = collisionTime(boats[i],boats[j],r*r);
						if ( collision_time < min ) min = collision_time;
					}
				}
				return min;
		}

		private static double collisionTime(Boat b1, Boat b2 , double r) {
			double dx = b2.x-b1.x;double dy = b2.y-b1.y;
			double sd1 = Math.sin(b1.dirr); double sd2 = Math.sin(b2.dirr);
			double cd1 = Math.cos(b1.dirr); double cd2 = Math.cos(b2.dirr);
			double dsc = b2.s*cd2-b1.s*cd1; double dss = b2.s*sd2-b1.s*sd1;
			double c = dx*dx+dy*dy-r;
			double b = 2*(dx*dsc+dy*dss);
			double a = dsc*dsc+dss*dss;
			double sqrt = b*b-4*a*c;
			if ( sqrt < 0 ) return Double.MAX_VALUE;
			sqrt = Math.sqrt(sqrt);
			double t = (-b-sqrt)/2/a;
			if ( t < 0 ) 
				t = (-b+sqrt)/2/a;
			if ( t < 0 ) 
				t = Double.MAX_VALUE;
			return t;
		}
	
	}

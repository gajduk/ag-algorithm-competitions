package Section1_4;

import java.util.ArrayList;
import java.util.Collections;

class Rectangle implements Comparable {
	public int w;
	public int h;
	
	@Override
	public String toString() {
		return w+" "+h;
	}
	
	public Rectangle( int a, int v) {
		w = a ; h = v;
	}
	
	public Rectangle() {
		
	}

	@Override
	public int compareTo(Object arg0) {
		Rectangle t = (Rectangle) arg0;
		
		return w-t.w;
	}
}

public class packrec {
	
	public static Rectangle recs[] = new Rectangle[4];
	public static int h1[] = { 0,0,0,1,1,2 };
	public static int h2[] = { 1,2,3,2,3,3 };
	public static int h3[] = { 2,1,1,0,0,0 };
	public static int h4[] = { 3,3,2,3,2,1 };
	
	public static ArrayList<Rectangle> layout1 ( ) {
		int total_width = 0;
		for ( int i = 0 ; i < recs.length ; ++i ) {
			total_width += recs[i].w;
			if ( recs[i].h != recs[0].h ) {
				return new ArrayList<Rectangle>();
			}
		}
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		Rectangle to_add = new Rectangle(total_width,recs[0].h);
		if ( ! result.contains(to_add))
			result.add(to_add);
		return result;
	}

	public static ArrayList<Rectangle> layout2 ( ) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for ( int i = 0 ; i < 4 ; ++i ) {
			int total_width = 0;
			boolean flag = true;
			for ( int j = 0 ; j < recs.length ; ++j ) {
				total_width += (i==j) ? 0 : recs[j].w;
				if ( i!=j && recs[j].h != recs[(i+1)%4].h ) {
					flag = false;
				}
			}
			if ( flag ) {
				Rectangle to_add = new Rectangle(total_width,recs[i].h+recs[(i+1)%4].h);
				if ( ! result.contains(to_add))
				result.add(to_add);
			}
		}
		return result;
	}

	public static ArrayList<Rectangle> layout3 ( ) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for ( int i = 0 ; i < h1.length  ; ++i ) {
			if ( recs[h1[i]].h == recs[h2[i]].h+recs[h4[i]].h && recs[h1[i]].h == recs[h3[i]].h+recs[h4[i]].h ) {
				Rectangle to_add = new Rectangle(recs[h1[i]].w+recs[h4[i]].w,recs[h1[i]].h);
				if ( ! result.contains(to_add))
					result.add(to_add);
			}
		}
		return result;
	}
	
	public static ArrayList<Rectangle> layout4 ( ) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for ( int i = 0 ; i < h1.length  ; ++i ) {
			if ( recs[h1[i]].h == recs[h2[i]].h && recs[h1[i]].h == recs[h3[i]].h+recs[h4[i]].h && recs[h3[i]].w == recs[h4[i]].w ) {
				Rectangle to_add = new Rectangle(recs[h1[i]].w+recs[h4[i]].w,recs[h1[i]].h);
				if ( ! result.contains(to_add))
					result.add(to_add);
			}
		}
		return result;
	}
	
	public static ArrayList<Rectangle> layout5 ( ) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for ( int i = 0 ; i < h1.length  ; ++i ) {
			if ( recs[h1[i]].h == recs[h2[i]].h && recs[h3[i]].h == recs[h4[i]].h && recs[h1[i]].w == recs[h2[i]].w && recs[h3[i]].w == recs[h4[i]].w  ) {
				Rectangle to_add = new Rectangle(recs[h1[i]].w+recs[h3[i]].w,recs[h1[i]].h+recs[h2[i]].h);
				if ( ! result.contains(to_add))
					result.add(to_add);
			}
		}
		return result;
	}
	
	public static ArrayList<Rectangle> layout6 ( ) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for ( int i = 0 ; i < h1.length  ; ++i ) {
			if ( recs[h1[i]].w == recs[h2[i]].w && recs[h3[i]].w+recs[h4[i]].w == recs[h1[i]].w && recs[h3[i]].h == recs[h4[i]].h ) {
				Rectangle to_add = new Rectangle(recs[h1[i]].w,recs[h1[i]].h+recs[h3[i]].h+recs[h4[i]].h);
				if ( ! result.contains(to_add))
					result.add(to_add);
			}
		}
		return result;
	}
	
	
	public static ArrayList<Rectangle> getAllSolutions ( ) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		result.addAll(layout1());
		result.addAll(layout2());
		result.addAll(layout3());
		result.addAll(layout4());
		result.addAll(layout5());
		result.addAll(layout6());
		for ( int i = 0 ; i < recs.length ; ++i ) {
			int temp = recs[i].h;
			recs[i].h = recs[i].w;
			recs[i].w = temp;
		}
		result.addAll(layout1());
		result.addAll(layout2());
		result.addAll(layout3());
		result.addAll(layout4());
		result.addAll(layout5());
		result.addAll(layout6());
		return result;
	}
	
	public static void test_all () {
		recs[0] = new Rectangle(1, 2);
		recs[1] = new Rectangle(2, 3);
		recs[2] = new Rectangle(3, 4);
		recs[3] = new Rectangle(3, 5);
		ArrayList<Rectangle> t = getAllSolutions();
		Collections.sort(t);
		System.out.println();
	}
	
	

	public static void main ( String args[] ) {
		test_all();
	}
	
}

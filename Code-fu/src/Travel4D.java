
public class Travel4D {
	
	class Point4d {
		int x;
		int y;
		int z;
		int k;
		
		public Point4d ( String point ) {
			String coords[] = point.split(";");
			x = Integer.parseInt(coords[0]);
			y = Integer.parseInt(coords[1]);
			z = Integer.parseInt(coords[2]);
			k = Integer.parseInt(coords[3]);//"x;y;z;k"
		}
		
		public long distance ( Point4d p ) {
			return (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)+(z-p.z)*(z-p.z)+(k-p.k)*(k-p.k);
		}
	}

	public int minTravel(String[] minerals, String[] wormholes) {
	    String start = "0;0;0;0";
	    int dist = 0;
	    dist += new Point4d(start).distance(new Point4d(minerals[0]));
	    for ( int i = 0 ; i < minerals.length-1 ; ++i ) {
	    	 dist += new Point4d(minerals[i]).distance(new Point4d(minerals[i+1]));
	    }
	    dist += new Point4d(minerals[minerals.length-1]).distance(new Point4d(start));
	    return (int) Math.sqrt(dist);
	}
	
}

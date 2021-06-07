import java.util.StringTokenizer;


public class HighestArea {
	public int highest(String triangles) {
        StringTokenizer tris = new StringTokenizer(triangles, ";");
        double best = 0;
        int bestindex = 0;
        int counter = 0;
        while ( tris.hasMoreTokens() ) {
            String tiangle = tris.nextToken();
            StringTokenizer sides = new StringTokenizer(tiangle, ",");
            int x1 = new Integer(sides.nextToken());
            int y1 = new Integer(sides.nextToken());
            int x2 = new Integer(sides.nextToken());
            int y2 = new Integer(sides.nextToken());
            int x3 = new Integer(sides.nextToken());
            int y3 = new Integer(sides.nextToken());
            double a = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2) );
            double b = Math.sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3) );
            double c = Math.sqrt((x3-x2)*(x3-x2)+(y3-y2)*(y3-y2) );
            double s = (a+b+c)/2;
            double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
            if ( area > best ) {
                best = area;
                bestindex = counter;
            }
            counter++;
        }
    return bestindex;
  }
}

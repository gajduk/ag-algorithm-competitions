package Chapter10;

import java.awt.geom.Point2D;

public class Point1 extends Point2D {
	private double x , y;

	public Point1( double x , double y ) {
		this.setLocation(x,y);
	}
	
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setLocation(double arg0, double arg1) {
		this.x = arg0;
		this.y = arg1;
	}

}

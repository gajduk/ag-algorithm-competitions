package Chapter10;

public class Edge1 implements Comparable {
	private int vertex_1;
	private int vertex_2;
	private double distance;
	
	public double getDistance() {
		return distance;
	}
	@Override
	public String toString() {
		return vertex_1+"  "+vertex_2+"  "+distance;
	}
	public int getVertex_1() {
		return vertex_1;
	}
	
	public int getVertex_2() {
		return vertex_2;
	}
	
	public Edge1() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Edge1 ( int vertex_1 , int vertex_2 , double distance ) {
		this.distance = distance;
		this.vertex_1 = vertex_1;
		this.vertex_2 = vertex_2;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Edge1 a = (Edge1) arg0;
		return new Double(distance).compareTo(new Double(a.getDistance()));
	}
	

}

package Chapter10;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Point2 extends Point2D {
	private double x , y;

	public Point2( double x , double y ) {
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


 class Edge2 implements Comparable {
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
	
	public Edge2() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Edge2 ( int vertex_1 , int vertex_2 , double distance ) {
		this.distance = distance;
		this.vertex_1 = vertex_1;
		this.vertex_2 = vertex_2;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Edge2 a = (Edge2) arg0;
		return new Double(distance).compareTo(new Double(a.getDistance()));
	}
	

}

public class Problem1 {
	
	public static Edge2 edges[];
	public static Point2 vertices[];
	
	public static void populateEdges () {
		edges = new Edge2[(vertices.length*vertices.length-vertices.length) / 2];
		int counter_edges = 0;
		for ( int i = 0 ; i < vertices.length ; ++i ) {
			for ( int m = i+1 ; m < vertices.length ; ++m ) {
				edges[counter_edges++] = new Edge2(i,m,calculateDistance(vertices[i].getX(), vertices[m].getX(), vertices[i].getY(), vertices[m].getY()));
			}
		}
		Arrays.sort(edges);
		//printEdges();
	}
	
	public static double kruskal () {
		int set_id[] = new int[vertices.length];
		for ( int i = 0 ; i < set_id.length ; ++i ) {
			set_id[i] = i;
		}
		double sum_edges = 0;
		int counter_edges = 0;
		for ( int k = 1 ; k < vertices.length ; ++k ) {
			while ( set_id[edges[counter_edges].getVertex_1()] == set_id[edges[counter_edges].getVertex_2()] ) { // will iterate until we find an edge such that v1 and v2 are in different sets
				++counter_edges;
			}
			//System.out.println(edges[counter_edges]);
			//print(set_id );
			sum_edges += edges[counter_edges].getDistance();
			int set2 = set_id[edges[counter_edges].getVertex_2()];
			int set1 = set_id[edges[counter_edges].getVertex_1()];
			for ( int m = 0 ; m < vertices.length ; ++m ) {
				if ( set_id[m] == set2 ) {
					set_id[m] = set1;
				}
			}
		}
		long t = (long) (sum_edges*100);
		if ( sum_edges*100 -t >= 0.5 ) {
			++t;
		}
		System.out.print(t/100+".");
		if ( t % 100 < 10 ) {
			System.out.print("0");
		}
		System.out.println(t%100);
		return t/100+(double)(t%100)/100.0;
		//return sum_edges;
	}
		
	public static double calculateDistance ( double x1 , double x2 , double y1 , double y2 ) {
		double dx = Math.abs(x1-x2);
		double dy = Math.abs(y1-y2);
		double distance = Math.sqrt(dx*dx+dy*dy);
		return distance;
	}
	
	public static void print ( int array[] ) {
		for ( int i = 0 ; i < array.length ; ++i ) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	public static void printEdges ( ) {
		for ( int i = 0 ; i < edges.length ; ++i ) {
			System.out.println(edges[i]);
		}
	}
	
	public static void main ( String args[] ) {
		//test_populateEdges();
		//test_file();
		test_judge();
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		in.nextLine();
    		int num_vertices = new Integer(in.nextLine());
    		vertices = new Point2[num_vertices];
    		for ( int m = 0 ; m < num_vertices ; ++m ) {
    			String s_numbers = in.nextLine();
    			StringTokenizer numbers = new StringTokenizer(s_numbers," ");
    			double x = new Double(numbers.nextToken());
    			double y = new Double(numbers.nextToken());
    			vertices[m] = new Point2(x,y);
    		}
    		getMinimunDistance();
    		if ( h < num_test_cases-1 ) 
    			System.out.println();
    	}
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int h = 0 ; h < num_test_cases ; ++h ) {
    		in.nextLine();
    		int num_vertices = new Integer(in.nextLine());
    		vertices = new Point2[num_vertices];
    		for ( int m = 0 ; m < num_vertices ; ++m ) {
    			String s_numbers = in.nextLine();
    			StringTokenizer numbers = new StringTokenizer(s_numbers," ");
    			double x = new Double(numbers.nextToken());
    			double y = new Double(numbers.nextToken());
    			vertices[m] = new Point2(x,y);
    		}
    		getMinimunDistance();
    		if ( h < num_test_cases-1 ) 
    			System.out.println();
    	}
	}
    
	public static void getMinimunDistance () {
		populateEdges();
		kruskal();
	}

	private static void test_populateEdges() {
		vertices = new Point2[3];
		vertices[0] = new Point2(1,1);
		vertices[1] = new Point2(2,2);
		vertices[2] = new Point2(2,4);
		populateEdges();
	}
	
	

}

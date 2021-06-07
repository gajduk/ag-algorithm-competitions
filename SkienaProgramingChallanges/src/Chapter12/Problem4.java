package Chapter12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

class Queue {
	int queue[] = new int[200001]; 
	int front = 0;
	int rear = 0;
	
	public Queue() {
		// TODO Auto-generated constructor stub
	}

	public void add ( int n ) {
		queue[front++] = n;
	}
	
	public int remove () {
		return queue[rear++];
	}
	
}

public class Problem4 {
	
	public static int n = 100000;
	public static boolean is_visited[][];
	public static int start_x = 1000; public static int start_y = 1000;

	public static void determineCurrentPosition() {
		if ( n == 0 ) {
			System.out.println("0 0");
			return;
		}
		int r = findCycleRadius(n);
		locateStartingNodes(r);
		traverse(r);
	}

	public static void locateStartingNodes ( int r ) {
		start_x = r-1;
		start_y = 1;
	}
	
	public static int findCycleRadius ( int n ) {
		double root = Math.sqrt(9+12*(n-1));
		double result = (3+root)/6;
		return (int) result;
	}
		
	public static void traverse ( int r ) {
		int direction = 2;
		int dx[] = { 1,0,-1,-1,0,1, };
		int dy[] = { 0,1,1,0,-1,-1 };
		int current_x = start_x;
		int current_y = start_y;
//		System.out.println("Starting from: "+start_x+" "+start_y);
		int target = n-(1+3*r*(r-1));
//		System.out.println("Going for "+target+" cells.");
//		System.out.println("Radius "+r);
		int total_counter = 0;
		int direction_counter = 1;
		int direction_max = r;
		if ( target >= r-1 ) {
			target -= r-1;
			current_x = current_x+(r-1)*dx[direction];
			current_y = current_y+(r-1)*dy[direction];
			direction = (direction+1) % dx.length;
//			System.out.println("Currently at: "+current_x+" "+current_y);
			while ( target >= r ) {
				target -= r;
				current_x = current_x+r*dx[direction];
				current_y = current_y+r*dy[direction];
				direction = (direction+1) % dx.length;
//				System.out.println("Currently at: "+current_x+" "+current_y);
			}
			current_x = current_x+target*dx[direction];
			current_y = current_y+target*dy[direction];
//			System.out.println("Currently at: "+current_x+" "+current_y);
		}
		else {
			current_x = current_x+target*dx[direction];
			current_y = current_y+target*dy[direction];
//			System.out.println("Currently at: "+current_x+" "+current_y);
		}
		
		
		/*
		while ( total_counter < target ) {
			if ( direction_counter == direction_max ) {
				direction = (direction+1) % dx.length;
				direction_counter = 0;
			}
			current_x += dx[direction];
			current_y += dy[direction];
			++total_counter;
			++direction_counter;
		}
		*/
		System.out.println(current_x+" "+current_y);
	}
	
	/*
	public static void traverse () {
		is_visited = new boolean[10000][10000];
		int counter = 0;
		Queue queue_x = new Queue();
		Queue queue_y = new Queue();
		
		int dx[] = { 1,0,-1,-1,0,1, };
		int dy[] = { 0,1,1,0,-1,-1 };
		for ( int i = 1 ; i <= dx.length ; ++i ) {
			is_visited[start_x+dx[i%dx.length]][start_y+dy[i%dx.length]] = true;
			queue_x.add(start_x+dx[i%dx.length]); queue_y.add(start_y+dy[i%dx.length]);
		}
		while ( counter < n ) {
			int current_x = queue_x.remove();
			int current_y = queue_y.remove();
			System.out.println(counter+": ("+(current_x-start_x)+","+(current_y-start_y)+")");
			for ( int k = 0 ; k < dx.length ; ++k ) {
				if ( ! is_visited[current_x+dx[k]][current_y+dy[k]] ) {
					is_visited[current_x+dx[k]][current_y+dy[k]] = true;
					queue_x.add(current_x+dx[k]); queue_y.add(current_y+dy[k]);
				}
			}
			++counter;
		}
	}
	*/
		
	public static void main ( String args[] ) {
//		test_file();
		test_judge();
//		test_traverse();

	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter12 problem4.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		n = new Integer(in.nextLine());
    		--n;
    		determineCurrentPosition();
    	}
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		n = new Integer(in.nextLine());
    		--n;
    		determineCurrentPosition();
    	}
	}

	private static void test_traverse() {
//		traverse();
		long start = System.currentTimeMillis();
		for ( int i = 1 ; i < 100000 ; ++i ) {
			n = i;
			int r = findCycleRadius(n);
			locateStartingNodes(r);
			traverse(r);
		}
		long end = System.currentTimeMillis();
		System.out.println("All numbers proccesed fir a total time of "+(end-start)+" ms");
	}
	

}

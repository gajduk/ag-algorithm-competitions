package Chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Turtle implements Comparable<Turtle>{
	int w;
	int s;
	
	public Turtle(int a , int b) {
		w = a;s=b;
	}

	@Override
	public int compareTo(Turtle o) {
		return s+w-o.s-o.w;
	}
	
	@Override
	public String toString() {
		return s+" "+w;
	}
	
	
}

public class Problem3 {
	public static Turtle turtles[];

	public static void main ( String args[] ) {
//		test_file();
		test_judge();
//		System.out.println(getMaxTurtles(4, Integer.MAX_VALUE));
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	int counter = 0;
    	ArrayList<Turtle> temp = new ArrayList<Turtle>();
    	while ( in.hasNext() ) {
    		int weight = in.nextInt();
    		int strength = in.nextInt();
    		//strength -= weight;
    		temp.add(new Turtle(weight,strength));
    	}
    	//Collections.sort(turtles);
    	turtles = new Turtle[temp.size()];
    	for ( int i = 0 ; i < turtles.length ; ++i ) {
    		turtles[i] = temp.get(i);
    	}
    	Arrays.sort(turtles);
    	int best[] = new int[turtles.length];
    	for ( int i = 0 ; i < turtles.length ; ++i ) {
    		best[i] = Integer.MAX_VALUE/3;
    	}
    	best[0] = 0;
    	int maxCount=0;

    	for ( int i = 0; i < turtles.length ; i++) {
    		 for ( int j = maxCount+1 ; j > 0; j-- ) {
	    	     if ( turtles[i].s-turtles[i].w >= best[j-1]  &&  best[j-1]+turtles[i].w < best[j] ) {
	    	        best[j] = best[j-1]+turtles[i].w;
	    	        if ( j > maxCount ) maxCount = j;
	    	     } 
    	     }
    	}
    	System.out.println(maxCount);
	}

	
	private static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter11 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int counter = 0;
    	ArrayList<Turtle> temp = new ArrayList<Turtle>();
    	while ( in.hasNext() ) {
    		int weight = in.nextInt();
    		int strength = in.nextInt();
    		temp.add(new Turtle(weight,strength));
    	}
    	turtles = new Turtle[temp.size()];
    	for ( int i = 0 ; i < turtles.length ; ++i ) {
    		turtles[i] = temp.get(i);
    	}
    	Arrays.sort(turtles);
    	int best[] = new int[turtles.length];
    	for ( int i = 0 ; i < turtles.length ; ++i ) {
    		best[i] = Integer.MAX_VALUE/3;
    	}
    	best[0] = 0;
    	int maxCount=0;
    	for ( int i = 0; i < turtles.length ; i++) {
    		 for ( int j = maxCount+1 ; j > 0; j-- ) {
	    	     if ( turtles[i].s-turtles[i].w >= best[j-1]  &&  best[j-1]+turtles[i].w < best[j] ) {
	    	        best[j] = best[j-1]+turtles[i].w;
	    	        if ( j > maxCount ) maxCount = j;
	    	     } 
    	     }
    	}
    	System.out.println(maxCount);
	}

	
	
}

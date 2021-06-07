package mendo;

import java.util.Scanner;

public class Stepeni {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); --n;
		System.out.println(charAt(n)+" "+charAt(n+1));
	}
	
	public static String charAt( int n ) {
		int i = (int) Math.sqrt(n*2);
		return (i*(i+1) == n*2)?"1":"0"; 
	}

}

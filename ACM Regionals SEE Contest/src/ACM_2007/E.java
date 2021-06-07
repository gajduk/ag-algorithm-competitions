package ACM_2007;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.Iterator;

public class E {
	
	public static void main ( String args[] ) throws FileNotFoundException {
		FileInputStream input = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/Desktop/ACM Regionals Problem Sets SouthEast Europe/Problems2007/in/e.in");
		Scanner in = new Scanner(input);
		while ( true ) {
			String temp = "";
			int den = 500000;
			boolean odd[] = new boolean[den];
			int map_x[] = new int[100000];
			int map_y[] = new int[100000];
			int map_z[] = new int[100000];
			int counter = 0;
			while ( true ) {
				if ( ! in.hasNext() ) {
					break;
				}
				temp = in.nextLine();
//				System.out.println(temp.length());
				if ( temp.length() == 0 ) {
					break;
				}
				StringTokenizer tkr = new StringTokenizer(temp);
				int x = Integer.parseInt(tkr.nextToken());
				int y = Integer.parseInt(tkr.nextToken());
				int z = Integer.parseInt(tkr.nextToken());
				map_x[counter] = x;
				map_y[counter] = y;
				map_z[counter++] = z;
				for ( int t = x ; t <= y ; t += z ) {
					odd[t/den] = !odd[t/den];
				}
			}
			if ( counter == 0 ) {
				continue;
			}
			int i = 0;
			for ( i = 0 ; i < odd.length ; ++i ) {
				if ( odd[i] ) {
					break;
				}
			}
			if ( i == odd.length ) {
				System.out.println("no corruption");
			}
			else {
				int count[] = new int[den];
				for ( int w = 0 ; w < counter ; ++w ) {
					int x = map_x[w];
					int y = map_y[w];
					int z = map_z[w];
					int x_i = i*den;
					int y_i = (i+1)*den;
					int min_k = (x_i-x)/z;
					min_k = min_k < 0 ? 0 : min_k;
					int max_k = (Math.min(y,y_i)-x)/z;
					for ( int k = min_k ; k <= max_k ; ++k ) {
						++count[(x+k*z)%den]; 
					}
				}
				for ( int j = 0 ; j < den ; ++j ) {
					if ( count[j] % 2 != 0 ) {
						System.out.println((i*den+j)+" "+count[j]);
					}
				}
			}
		}
		
	}

}

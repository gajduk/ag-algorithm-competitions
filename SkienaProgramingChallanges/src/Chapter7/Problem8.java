package Chapter7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Box implements Comparable<Box>{
	int a;
	int b;
	double alpha;
	
	public Box( int a1 , int b1 )  {
		a = a1;
		b = b1;
		alpha = Math.atan2(a1,b1);
	}

	@Override
	public int compareTo(Box o) {
		if ( alpha == o.alpha ) {
			return 0;
		}
		if ( alpha-o.alpha > 0 ) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return a+" "+b+" "+alpha;
	}
	
	
}

public class Problem8 {
	
	public static void main ( String args [] ) {
//		test_file();
		test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem8.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
			int num_boxes = in.nextInt();
			if ( num_boxes == 0 ) {
				break;
			}
			int boxes_s1[] = new int[num_boxes];
			int boxes_s2[] = new int[num_boxes];
			int boxes_s3[] = new int[num_boxes];
			Box boxes[] = new Box[num_boxes];
			for ( int i = 0 ; i < num_boxes ; ++i ) {
				boxes_s1[i] = in.nextInt();
				boxes_s2[i] = in.nextInt();
				boxes_s3[i] = in.nextInt();
				boxes[i] = new Box(boxes_s2[i]-boxes_s1[i],boxes_s3[i]-boxes_s1[i]);
			}
			Arrays.sort(boxes);
//			System.out.println(Arrays.toString(boxes));
			double max_t = Math.abs((Math.PI*2+boxes[0].alpha)-boxes[num_boxes-1].alpha);
			for ( int i = 0 ; i < num_boxes-1 ; ++i ) {
				double t = Math.abs(boxes[i+1].alpha-boxes[i].alpha);
//				System.out.print(t+" ");
				if ( max_t < t ) {
					max_t = t;
				}
			}
			if ( max_t > Math.PI ) {
				System.out.println("NO");
			}
			else {
				System.out.println("YES");
			}
			System.out.println(max_t);
		}
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			int num_boxes = in.nextInt();
			if ( num_boxes == 0 ) {
				break;
			}
			int boxes_s1[] = new int[num_boxes];
			int boxes_s2[] = new int[num_boxes];
			int boxes_s3[] = new int[num_boxes];
			Box boxes[] = new Box[num_boxes];
			for ( int i = 0 ; i < num_boxes ; ++i ) {
				boxes_s1[i] = in.nextInt();
				boxes_s2[i] = in.nextInt();
				boxes_s3[i] = in.nextInt();
				boxes[i] = new Box(boxes_s2[i]-boxes_s1[i],boxes_s3[i]-boxes_s1[i]);
			}
			Arrays.sort(boxes);
//			System.out.println(Arrays.toString(boxes));
			double max_t = Math.abs((Math.PI*2+boxes[0].alpha)-boxes[num_boxes-1].alpha);
			for ( int i = 0 ; i < num_boxes-1 ; ++i ) {
				double t = Math.abs(boxes[i+1].alpha-boxes[i].alpha);
//				System.out.print(t+" ");
				if ( max_t < t ) {
					max_t = t;
				}
			}
			if ( max_t > Math.PI ) {
				System.out.println("NO");
			}
			else {
				System.out.println("YES");
			}
//			System.out.println(max_t);
		}
	}

}

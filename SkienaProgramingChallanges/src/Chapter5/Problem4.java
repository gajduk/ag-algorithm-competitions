package Chapter5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem4 {
	
	public static int getNumberOnes ( int n ) {
		if ( n == 0 ) {
			return 0;
		}
		BigInteger a = new BigInteger("1");
		BigInteger n_b = new BigInteger(new Integer(n).toString());
		BigInteger zero = new BigInteger("0");
		int result = 1;
		while ( true ) {
			BigInteger k = a.mod(n_b);
			if ( k.equals(zero) )
				return result;
			a = new BigInteger(k.toString()+"1");
			++result;
		}
	}
	
	public static void main ( String args[] ) {
		test_judge();
//		test_file();
		
	}
	
	private static void test_all() throws IOException {
		//System.out.println(getNumberOnes(2));
		  Writer output = null;
		  String text = "Rajesh Kumar";
		  File file = new File("write.txt");
		  output = new BufferedWriter(new FileWriter(file));
		  for ( int i = 0 ; i < 10001 ; ++i ) {
			  System.out.println(i);
			   if ( i % 2 != 0 && i % 5 != 0 )
					try {
						output = new BufferedWriter(new FileWriter(file));
						output.write(getNumberOnes(i)+",");
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			   else
					try {
						output = new BufferedWriter(new FileWriter(file));
						output.write(0+",");
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		}
		 output.close();
	}

	public static void test_judge () {
	 	Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			System.out.println( getNumberOnes(new Integer(in.nextLine())) );
		}
	}

}

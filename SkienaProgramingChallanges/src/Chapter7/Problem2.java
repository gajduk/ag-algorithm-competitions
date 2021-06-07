package Chapter7;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem2 {
	
	public static int carmichael_numbers[] = { 561,1105,1729,2465,2821,6601,8911,10585,15841,29341,41041,46657,52633,62745,63973 };
	
	public static int modPower ( int number , int power , int mod ) {
		BigInteger b_n = new BigInteger(Integer.toString(number));
		BigInteger b_power = new BigInteger(Integer.toString(power));
		BigInteger b_mod = new BigInteger(Integer.toString(mod));
		return new Integer(b_n.modPow(b_power, b_mod).toString());
	}
	
	public static boolean isPrime( int number ) {
		int boundry = (int) (Math.sqrt(number) + 1);
		if ( number % 2 == 0 )
			return false;
		int divisor = 3;
		while ( divisor < boundry ) {
			if ( number % divisor == 0 )
				return false;
			divisor += 2;
		}
		return true;
	}
	
	public static boolean isFermatPrime ( int number ) {
		for ( int a = 2 ; a < number ; ++a ) {
			if ( a != modPower(a, number, number) )
				return false;
		}
		return true;
	}
	
	public static boolean isCarmichaelBetter ( int number ) {
		for ( int k = 0 ; k< carmichael_numbers.length ; ++ k) {
			if ( carmichael_numbers[k] == number )
				return true;
		}
		return false;
	}
	
	public static boolean isCarmichael ( int number ) {
		return isFermatPrime(number) && ! isPrime(number);
	}
	
	public static void test_all () {
		try{
			  // Create file 
			  FileWriter fstream = new FileWriter("out.txt");
			  BufferedWriter out = new BufferedWriter(fstream);
			  
			  //Close the output stream
			  
			 		for ( int k = 3 ; k < 65001 ; ++k ) {
						System.out.println(k);
						isCarmichaelBetter(k);
						//out.write(isCarmichael(k) ?( Integer.toString(k)+"," ): "" );
					}
			 		out.close();
		 }
		catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		}

	}
	
	public static void main ( String args[] )  {
		//test_all();
		//test_file();
		test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		while ( true ) {
    		int number = new Integer(in.nextLine());
    		if ( number == 0 )
    			return;
    		if ( isCarmichaelBetter(number) ) {
    			System.out.println("The number "+number+" is a Carmichael number.");
    		}
    		else {
    			System.out.println(number+" is normal.");	
    		}
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem2.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( true ) {
    		int number = new Integer(in.nextLine());
    		if ( number == 0 )
    			return;
    		if ( isCarmichael(number) ) {
    			System.out.println("The number "+number+" is a Carmichael number.");
    		}
    		else {
    			System.out.println(number+" is normal.");	
    		}
    	}
 
	}
    	


	
	

}

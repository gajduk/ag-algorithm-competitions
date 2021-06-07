package Chapter7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

public class Problem6 {
	
	public static int sumDigits ( int number ) {
		if ( number < 10 )
			return number;
		int sum = 0;
		String s_number = Integer.toString(number);
		for ( int i = 0 ; i < s_number.length() ; ++i ) {
			if ( s_number.charAt(i) >= '1' && s_number.charAt(i) <= '9' ) {
				sum += s_number.charAt(i)-'1'+1;
			}
		}
		return sum;
	}
	
	public static int sumPrimeFactors ( int number ) {
		int sum = 0;
		int divisor;
		if ( number == 0 )
			return 0;
		while ( number % 2 == 0 ) {
			number /= 2;
			sum += 2;
		}
		divisor = 3;
		int boundry = (int) (Math.sqrt(number)+1);
		while ( divisor <= boundry ) {
			if ( number % divisor == 0 ) {
				sum += sumDigits(divisor);
				number /= divisor;
				boundry = (int) (Math.sqrt(number)+1);
			}
			else
				divisor += 2;
		}
		if ( number > 1 )
			sum += sumDigits(number);
		return sum;
	}
	
	public static boolean isSmithNumber ( int number ) {
		return !isPrime(number) && sumDigits(number) == sumPrimeFactors(number);
	}
	
	public static void main ( String args[] ) {
	//	test_sumDigits();
	//	test_file();
		test_judge();
	}
	
	public static void test_sumDigits() {
		System.out.println(sumDigits(0));
		System.out.println(sumDigits(1));
		System.out.println(sumDigits(2));
		System.out.println(sumDigits(7));
		System.out.println(sumDigits(17));
		System.out.println(sumDigits(4));
		System.out.println(sumDigits(14));
		System.out.println(sumDigits(9845646));
		System.out.println(sumDigits(1234567891));
		System.out.println(sumDigits(-15));
	}
	
	public static boolean isPrime( int number ) {
		int boundry = (int) (Math.sqrt(number) + 1);
		if ( number == 2 )
			return true;
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

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int number = new Integer(in.nextLine());
    		System.out.println(getNextSmithNumber(number));
    	}
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem6.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int number = new Integer(in.nextLine());
    		System.out.println(getNextSmithNumber(number));
    	}
	}

	public static int getNextSmithNumber(int number) {
		int i = number + 1;
		while ( true ) {
			if ( isSmithNumber(i) )
				return i;
			++i;
		}
	}
	

}

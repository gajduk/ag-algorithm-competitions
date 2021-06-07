	package Chapter7;
	
	import java.io.BufferedOutputStream;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Scanner;
	
	public class Problem5 {
		public static final int MAX = 10000001;
		public static boolean primes[] = new boolean[MAX];
		
		public static void populatePrimes() {
			primes[0] = true;
			primes[1] = true;
			primes[2] = false;
			for ( int i = 2 ; i < MAX ; i++ ) {
		        if ( ! primes[i] ) {
		        	int boundry = MAX / i;
		            for ( int j = 2 ; j < boundry ; j++ ) {
		                 primes[i*j] = true;
		            }
		          //System.out.print(i+"   \n");
		        }
		   }
		}
		
		public static void test_judge() {
			Scanner in = new Scanner(System.in);
	    	while ( in.hasNext() ) {
	    		int number = new Integer(in.nextLine());
	    		representAsSumOf4Primes(number);    		
	    	}
		}
		
		public static void test_file() {
			FileInputStream inputFile = null;
		    try {
		    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter7 problem5.txt");
		    }
	    	catch (FileNotFoundException e) {
		      e.printStackTrace(System.err);
		      return;
		    }
	    	Scanner in = new Scanner(inputFile);
	    	while ( in.hasNext() ) {
	    		int number = new Integer(in.nextLine());
	    		representAsSumOf4Primes(number);    		
	    	}
		}
		
		public static void main ( String args[] ) {
			populatePrimes();
			//test_representing();
			//test_file();
			test_judge();
		}
		public static int sum;
		private static void test_representing() {
			for ( int i = 0 ; i < MAX ; ++i ) {
				if ( i % 100000 == 0 )
				//	System.out.println(i);
				sum = 0;
				representAsSumOf4Primes(i);
			}
		}
	
		public static void representAsSumOf4Primes ( int number ) {
			//sum = number;
			if ( number < 8 ) {
				System.out.println("Impossible.");
				return;
			}
			if ( number % 2 == 0  ) {
				int temp1;
				int temp2;
				int temp3;
				temp1 = findNextPrime(number,true);//true means that we will find the next prime number smaller than
										   //number but such that number - prime = composite number
				number -= temp1;
				temp2 = findNextPrime(number,true);
				number -= temp2;
				temp3 = findNextPrime(number,false);
				number -= temp3;
				int default_case = 0;
				if ( temp1 == default_case || temp2 == default_case  || temp3 == default_case || number == default_case ) {
						System.out.println("Impossible.");
				}
				else {
					//sum = temp1+temp2+temp3+number;
					System.out.println(temp1+" "+temp2+" "+temp3+" "+number);
				}
			}
			else {
				number -= 2;
				
				if ( ! primes[number-4] ) {
					//sum = 6 + number-4;
					System.out.println("2 2 2 "+(number-4));
				}
				else {
					int temp1;
					int temp2;
					temp1 = findNextOddPrime(number,true);
					number -= temp1;
					temp2 = findNextOddPrime(number,false);
					number -= temp2;
					if ( number != 0 ) {
						//sum = 2 + temp1+temp2+number;
						System.out.println("2 "+temp1+" "+temp2+" "+number);
					}
					else  {
						System.out.println("Impossible.");
					}
				}
			}
			
		}
	
		public static int findNextPrime(int number , boolean is_prime ) {
			int prime = 0;
			while ( prime < number && ( primes[prime]  || !((is_prime && primes[number-prime]) || ( !is_prime && !primes[number-prime]) ))) {
				++prime; 
			}
			return prime;
		}
		
		public static int findNextOddPrime(int number , boolean is_prime ) {
			int prime = 3;
			while ( prime < number && ( primes[prime]  || !((is_prime && primes[number-prime]) || ( !is_prime && !primes[number-prime]) ))) {
				++prime; 
			}
			return prime;
		}
		
	
	}

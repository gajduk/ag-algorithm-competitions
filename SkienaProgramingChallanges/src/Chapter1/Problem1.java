package Chapter1;import java.util.Scanner;

/*
	 1.6.1 The 3n +1 Problem
	PC/UVa IDs: 110101/100, Popularity: A, Success rate: low Level: 1
	Consider the following algorithm to generate a sequence of numbers. Start with an
	integer n.If n is even, divide by 2. If n is odd, multiply by 3 and add 1. Repeat this
	process with the new value of n, terminating when n = 1. For example, the following
	sequence of numbers will be generated for n = 22:
	22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1
	It is conjectured (but not yet proven) that this algorithm will terminate at n = 1 for
	every integer n. Still, the conjecture holds for all integers up to at least 1,000,000.
	For an input n, the cycle-length of n is the number of numbers generated up to and
	including the 1. In the example above, the cycle length of 22 is 16. Given any two
	numbers i and j, you are to determine the maximum cycle length over all numbers
	between i and j, including both endpoints.
	Input
	The input will consist of a series of pairs of integers i and j, one pair of integers per
	line. All integers will be less than 1,000,000 and greater than 0.
	Output
	For each pair of input integers i and j, output i, j in the same order in which they
	appeared in the input and then the maximum cycle length for integers between and
	including i and j. These three numbers should be separated by one space, with all three
	numbers on one line and with one line of output for each line of input.
	Sample Input
	1 10
	100 200
	201 210
	900 1000
	Sample Output
	1 10 20
	100 200 125
	201 210 89
	900 1000 174
 */

public class Problem1 {
	 
	/*
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			int num1 = in.nextInt();
			int num2 = in.nextInt();
			int start;
			int end;
			if ( num1 > num2 ) {
				start = num2;
				end = num1;
			}
			else {
				start = num1;
				end = num2;
			}
			System.out.println(num1+" "+num2+" "+maxCycleLength(start,end));
			
		}
		
		
	}
	*/
	
	

    static int MAX = 5000000;
    static long[] table = new long[MAX];
    public static long maxCycleLength ( long start , long end ) {
        for ( int i = 0 ; i < MAX ; i++ ) {
               table[i] = -1;
        }
        table[1] = 1;
        long max = 0;
        for ( long i = start ; i <= end ; i++ ) {
                long temp = findCycleLength(i);
                if ( temp > max ) max = temp;
        }
        return max;
    }

    static long findCycleLength ( long number ) {
        if ( number < MAX && number >= 0 ) {
            if ( table[(int)number] != -1 ) return table[(int)number];
            if ( (number>>1)+(number>>1) == number ) table[(int)number] = findCycleLength(number/2)+1;
            else table[(int)number] = findCycleLength((int)number*3+1)+1;
            return table[(int)number];
        }
        if ( (number>>1)+(number>>1) == number ) return findCycleLength(number/2)+1;
        return findCycleLength(number*3+1)+1;
    }

}

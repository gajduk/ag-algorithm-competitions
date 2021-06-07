package Chapter1;
import java.util.Scanner;

/*
	 1.6.4 LCD Display
	PC/UVa IDs: 110104/706, Popularity: A, Success rate: average Level: 1
	A friend of yours has just bought a new computer. Before this, the most powerful
	machine he ever used was a pocket calculator. He is a little disappointed because he
	liked the LCD display of his calculator more than the screen on his new computer! To
	make him happy, write a program that prints numbers in LCD display style.
	Input
	The input file contains several lines, one for each number to be displayed. Each line
	contains integers s and n, where n is the number to be displayed (0 <= n <= 99, 999, 999)
	and s is the size in which it shall be displayed (1 <= s <= 10). The input will be terminated
	by a line containing two zeros, which should not be processed.
	Output
	Print the numbers specified in the input file in an LCD display-style using s “-” signs
	for the horizontal segments and s “|” signs for the vertical ones. Each digit occupies
	exactly s + 2 columns and 2s + 3 rows. Be sure to fill all the white space occupied by
	the digits with blanks, including the last digit. There must be exactly one column of
	blanks between two digits.
	Output a blank line after each number. You will find an example of each digit in the
	sample output below.
	Sample Input
	2 12345
	3 67890
	00
	Sample Output

 */

/*
Test Sample Input:
2 1
2 2
2 3 
2 4
2 5
2 6
2 7
2 8
2 9
2 0 
 */

public class Problem4 {
	/*
	 public static void main ( String args[] ) {
		 	//test();
		 	boolean flag = false;
		 	Scanner in = new Scanner(System.in);
			int size = in.nextInt();
			while ( size != 0 ) {
				int temp = in.nextInt();
				String number = Integer.toString(temp);
				in.nextLine();
				if ( flag ) {
					System.out.println();
				}
				else 
					flag = true;
				lcdDisplay(number, size);
				System.out.println();
				
				size = in.nextInt();
			}
	 }
	 */
	 
	 public static void test() {
		 for ( int i = 0 ; i < 100000 ; ++i  ) {
			 int size = (int) (Math.random()*10)+1;
			 int number = (int) (Math.random()*10000000);
			 System.out.println("Size: "+size+" Number: "+number);
			 lcdDisplay(new Integer(number).toString(), size);
		 }
		 
	 }
	
	 static int[][] display = { { 1,1,1,0,1,1,1 }, //0
             { 0,1,1,0,0,0,0 }, //1
             { 1,1,0,1,0,1,1 }, //2
             { 1,1,1,1,0,0,1 }, //3
             { 0,1,1,1,1,0,0 }, //4
             { 1,0,1,1,1,0,1 }, //5
             { 1,0,1,1,1,1,1 }, //6
             { 1,1,1,0,0,0,0 }, //7
             { 1,1,1,1,1,1,1 }, //8 
             { 1,1,1,1,1,0,1 }, //9
                 };
	 
	 public static  int lcdDisplay ( String number , int size ) {
	        int []digits = new int[100];
	        for ( int i = 0 ; i < 9 ; i++ ) digits[i] = -1;
	        int q = 0;
	        for ( int i = 0 ; i < number.length() ; ++i ) {
	        	digits[q++] = number.charAt(i)-'0';
	        }
	        
	        
	        char[][] result = new char[2*size+3][q*(size+2)+q-1];
	        //size++;
	        for ( int i = 0 ; i < result.length ; i++ ) {
	            for ( int j = 0 ; j < result[0].length ; j++ ) {
	            	result[i][j] = ' ';
	            }
	        }
	        for ( int i = 0 ; i < q ; i++ ) {
	            if ( display[digits[i]][0] == 1 ) {
	                for ( int j = i*(size+3)+1 ;  j < i*(size+3)+size+1 ; j++ ) result[0][j] = '-';
	            }
	            if ( display[digits[i]][3] == 1 ) {
	                for ( int j = i*(size+3)+1 ;  j < i*(size+3)+size+1 ; j++  ) result[size+1][j] = '-';
	            }
	            if ( display[digits[i]][6] == 1 ) {
	                for ( int j = i*(size+3)+1 ;  j < i*(size+3)+size+1 ; j++  ) result[2*size+2][j] = '-';
	            }
	            if ( display[digits[i]][1] == 1 ) {
	                for ( int j = 1 ;  j < size+1 ; j++ ) result[j][i*(size+3)+size+1] = '|';
	            }
	            if ( display[digits[i]][2] == 1 ) {
	                for ( int j = 1 ;  j < size+1 ; j++ ) result[j+size+1][i*(size+3)+size+1] = '|';
	            }
	            if ( display[digits[i]][4] == 1 ) {
	                for ( int j = 1 ;  j < size+1 ; j++ ) result[j][i*(size+3)] = '|';
	            }
	            if ( display[digits[i]][5] == 1 ) {
	                for ( int j = 1 ;  j < size+1 ; j++ ) result[j+size+1][i*(size+3)] = '|';
	            }
	        }
	        String []temp = new String[result.length];
	        for ( int i = 0 ; i < result.length ; i++ ) {
	            temp[i] = new String(result[i]);
	        }
	        for ( int i = 0 ; i < result.length ; i++ ) {
	        	if ( i != 0 ) System.out.println();
		          System.out.print(temp[i]);
	        }
	       return 0;
	    }

}

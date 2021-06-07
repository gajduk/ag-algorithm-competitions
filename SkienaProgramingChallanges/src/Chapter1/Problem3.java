package Chapter1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

	/*
	1.6.3 The Trip
	PC/UVa IDs: 110103/10137, Popularity: B, Success rate: average Level: 1
	A group of students are members of a club that travels annually to diferent lo-
	cations. Their destinations in the past have included Indianapolis, Phoenix, Nashville,
	Philadelphia, San Jose, and Atlanta. This spring they are planning a trip to Eindhoven.
	The group agrees in advance to share expenses equally, but it is not practical to share
	every expense as it occurs. Thus individuals in the group pay for particular things, such
	as meals, hotels, taxi rides, and plane tickets. After the trip, each student’s expenses
	are tallied and money is exchanged so that the net cost to each is the same, to within
	one cent. In the past, this money exchange has been tedious and time consuming. Your
	job is to compute, from a list of expenses, the minimum amount of money that must
	change hands in order to equalize (within one cent) all the students’ costs.
	Input
	Standard input will contain the information for several trips. Each trip consists of a
	line containing a positive integer n denoting the number of students on the trip. This is
	followed by n lines of input, each containing the amount spent by a student in dollars
	and cents. There are no more than 1000 students and no student spent more than
	$10,000.00. A single line containing 0 follows the information for the last trip.
	Output
	For each trip, output a line stating the total amount of money, in dollars and cents,
	that must be exchanged to equalize the students’ costs.
	Sample Input
	3
	10.00
	20.00
	30.00
	4
	15.00
	15.01
	3.00
	3.01
	0
	Sample Output
	$10.00
	$11.99
*/

/*
   Test Sample Inputs:
3
10.00
20.00
30.00
4
15.00
15.01
3.00
3.01
2
10000.70
0.50
2
10000.7
0.5
1
5.12
1
5.1
1
5
2
1
3
2
1
2
4
150
150
150
150.03
0  

   Test Sample Output:
$10.00
$11.99
$5000.10
$5000.10
$0.00
$0.00
$0.00
$1.00
$0.50   
$0.02
 */

public class Problem3 {
	
	
	/*
	 not working solution
	 *********************
	 
	 public static void main ( String args[] ) {
		 
			Scanner in = new Scanner(System.in);
			int num = in.nextInt();
			
			while ( num != 0 ) {
				in.nextLine();
				float money_spent[] = new float[num];
				int dolars[] = new int[num];
				int cents[] = new int[num];
				for ( int i = 0 ; i < num ; ++i ) {
					String number = in.nextLine();
					if ( number.indexOf('.') != -1 ) {
						int dolars_cur = new Integer(number.substring(0,number.indexOf('.')));
						int cents_cur = 0;
						if ( number.substring(number.indexOf('.')+1).length() > 1 )
							cents_cur = new Integer(number.substring(number.indexOf('.')+1));
						else
							cents_cur = new Integer(number.substring(number.indexOf('.')+1))*10;
					//	int to_cents = dolars_cur*100+cents_cur;
					//	money_spent[i] = ((float)to_cents)/100;
						dolars[i] = dolars_cur;
						cents[i] = cents_cur;
					}
					else {
						int dolars_cur = new Integer(number);
						int cents_cur = 0;
						dolars[i] = dolars_cur;
						cents[i] = cents_cur;
					}
				}
				float result = minMoney(dolars,cents);
				num = in.nextInt();
				
			}
			
			
		}
	*/	
		
	
	
	/*
	 public static float minMoney( float[] spent ) {
         float average = 0;
         for ( int i = 0 ; i < spent.length ; i++ )
              average += spent[i];
         average /= spent.length;
         float total_money = 0;
         for ( int i = 0 ; i < spent.length ; i++ )
             if ( spent[i] < average ) total_money += average-spent[i];
         return total_money;
     }
	 */
	/*
	 public static float minMoney( float[] spent ) {
         int dolars[] = new int[spent.length];
         int cents[] = new int[spent.length];
         long total_dolars = 0;
         long total_cents = 0;
         for ( int i = 0 ; i < spent.length ; ++i ) {
        	 dolars[i] = new Float(spent[i]).intValue();
        	 cents[i] =  new Float(spent[i]*100).intValue() % 100;
        	 total_dolars = total_dolars + dolars[i] + ( total_cents + cents[i] ) / 100;
        	 total_cents = (total_cents + cents[i]) % 100;
        	// System.out.println("Number "+i+":"+dolars[i]+"."+cents[i]);
         }
         long all_to_cents = total_dolars*100+total_cents;
         int avarage = (int) all_to_cents / spent.length;
         //System.out.println("calculated avarage="+avarage);
         int total = 0;
         for ( int i = 0 ; i < spent.length ; ++i  ) {
        	 if ( avarage > (dolars[i]*100+cents[i])  ) {
        		 total += avarage - (dolars[i]*100+cents[i]); 
        	 }
         }
         if ( total%100 < 10  ) {
        	 System.out.println("$"+total/100+".0"+total%100);
         }
         else {
        	 System.out.println("$"+total/100+"."+total%100);
         }
         return 0;
     }
	 */
	 /*
	 public static float minMoney( int []spent_dolars , int []spent_cents ) {
        int total_dolars = 0;
        int total_cents = 0;
         for ( int i = 0 ; i < spent_dolars.length ; ++i ) {
        	 total_dolars = total_dolars + spent_dolars[i] + ( total_cents + spent_cents[i] ) / 100;
        	 total_cents = (total_cents + spent_cents[i]) % 100;
        //	 System.out.println("Number"+i+":"+spent_dolars[i]+"."+spent_cents[i]);
         }
         int all_to_cents = total_dolars*100+total_cents;
         float avarage_float = (float) all_to_cents / spent_cents.length;
         BigDecimal rounded_avarage = new BigDecimal(avarage_float);
         System.out.println("Avarage as float:"+rounded_avarage.toPlainString());
         System.out.println("Avarage as rounded float:"+rounded_avarage.round(new MathContext(new Integer(new Float(avarage_float).intValue()).toString().length())).toPlainString());
         int average = new Integer(rounded_avarage.round(new MathContext(new Integer(new Float(avarage_float).intValue()).toString().length())).toPlainString());
         
         System.out.println("calculated average="+average);
         int total = 0;
         for ( int i = 0 ; i < spent_cents.length ; ++i  ) {
        	 if ( average > (spent_dolars[i]*100+spent_cents[i])  ) {
        		 total += average - (spent_dolars[i]*100+spent_cents[i]); 
        	 }
         }
         if ( total%100 < 10  ) {
        	 System.out.println("$"+total/100+".0"+total%100);
         }
         else {
        	 System.out.println("$"+total/100+"."+total%100);
         }
         return 0;
     }
	*/
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}
	
	
	 
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter1 problem3.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num = in.nextInt();
		while ( num != 0 ) {
			in.nextLine();
			double money_spent[] = new double[num];
			for ( int i = 0 ; i < num ; ++i ) {
				String number = in.nextLine();
				if ( number.indexOf('.') != -1 ) {
					int dolars_cur = new Integer(number.substring(0,number.indexOf('.')));
					int cents_cur = 0;
					if ( number.substring(number.indexOf('.')+1).length() > 1 )
						cents_cur = new Integer(number.substring(number.indexOf('.')+1));
					else
						cents_cur = new Integer(number.substring(number.indexOf('.')+1))*10;
					money_spent[i] = dolars_cur + ((double)cents_cur)/100;
				}
				else {
					int dolars_cur = new Integer(number);
					int cents_cur = 0;
					money_spent[i] = (double) dolars_cur;
				}
			}
			calcualteMinMoneyExchange(money_spent);
			num = in.nextInt();
		}
	}



	public static void test_judge() {
		 	Scanner in = new Scanner(System.in);
			int num = in.nextInt();
			while ( num != 0 ) {
				in.nextLine();
				double money_spent[] = new double[num];
				for ( int i = 0 ; i < num ; ++i ) {
					String number = in.nextLine();
					if ( number.indexOf('.') != -1 ) {
						int dolars_cur = new Integer(number.substring(0,number.indexOf('.')));
						int cents_cur = 0;
						if ( number.substring(number.indexOf('.')+1).length() > 1 )
							cents_cur = new Integer(number.substring(number.indexOf('.')+1));
						else
							cents_cur = new Integer(number.substring(number.indexOf('.')+1))*10;
						money_spent[i] = dolars_cur + ((double)cents_cur)/100;
					}
					else {
						int dolars_cur = new Integer(number);
						int cents_cur = 0;
						money_spent[i] = (double) dolars_cur;
					}
				}
				calcualteMinMoneyExchange(money_spent);
				num = in.nextInt();
			}
	}


/*
	public static float minMoney( int []spent_dolars , int []spent_cents ) {
		 	 int total_dolars = 0;
	         int total_cents = 0;
	         for ( int i = 0 ; i < spent_dolars.length ; ++i ) {
	        	 total_dolars = total_dolars + spent_dolars[i] + ( total_cents + spent_cents[i] ) / 100;
	        	 total_cents = (total_cents + spent_cents[i]) % 100;
	         //	 System.out.println("Number"+i+":"+spent_dolars[i]+"."+spent_cents[i]);
	         }
	         int all_to_cents = total_dolars*100+total_cents;
	         int average = all_to_cents / spent_cents.length;
	         int bottom_up = 0;
	         int bottom_up_bottom = 0;
	         int bottom_up_up = 0;
	         for ( int i = 0 ; i < spent_dolars.length ; ++i ){
	        	 if ( spent_dolars[i]*100+spent_cents[i] < average-1 ) {
	        		 bottom_up_bottom += average-1 - spent_dolars[i]*100+spent_cents[i];
	        	 }
	        	 else {
	        		 bottom_up_up += spent_dolars[i]*100+spent_cents[i] - average;
	        	 }
	         }
	         int top_down = 0;
	         int top_down_top = 0;
	         int top_down_down = 0;
	         for ( int i = 0 ; i < spent_dolars.length ; ++i ){
	        	 if ( spent_dolars[i]*100+spent_cents[i] < average ) {
	        		 top_down_down += average - spent_dolars[i]*100+spent_cents[i];
	        	 }
	        	 else {
	        		 top_down_top += spent_dolars[i]*100+spent_cents[i] - average - 1;
	        	 }
	         }
	         System.out.println("bottom_up_bottom: "+bottom_up_bottom+" bottom_up_up: "+bottom_up_up+" top_down_top: "+top_down_top+" top_down_down "+top_down_down );
	         
	         //System.out.println();
	         return 0;
	 }
*/
	 
	 public static void calcualteMinMoneyExchange ( double money_spent[] ) {  
		 		double sum = 0.0;
		 		for ( int i = 0 ; i < money_spent.length ; ++i ) {
		 			sum += money_spent[i]*100;
		 		}
		        double average = sum / money_spent.length;
		 		if( average + 0.5 >= (int)average+1 )
		 			average = (int) average + 1;
		 	    else
		 	      	average = (int) average;
		 	 
		         double amount1 = 0.0;
		         double amount2 = 0.0;
		 	 
		         for(int i = 0; i <  money_spent.length; i++)  {
		             if(money_spent[i]*100 > average)
			                amount1 += (money_spent[i]*100 - average);
		 	            else
			                amount2 += (average - money_spent[i]*100);
		        }
		 	    if(amount1 == 0)
		 	            amount1 = amount2;
		        if(amount2 == 0)
		            amount2 = amount1;
		        String result = "$";
		        if(amount1 > amount2) {
		        	 if ( (int)amount2%100 < 10 ) {
		        		 result += Integer.toString((int)amount2/100) + ".0" + Integer.toString((int)amount2%100);
		        	 }
		        	 else {
		        		 result += Integer.toString((int)amount2/100) + "." + Integer.toString((int)amount2%100);
		        	 }
		        	 System.out.println(result);
		        }
		        else {
		        	 if ( (int)amount1%100 < 10 ) {
		        		 result += Integer.toString((int)amount1/100) + ".0" + Integer.toString((int)amount1%100);
		        	 }
		        	 else {
		        		 result += Integer.toString((int)amount1/100) + "." + Integer.toString((int)amount1%100);
		        	 }
		        	System.out.println(result);
		        }
	 }
	 
}

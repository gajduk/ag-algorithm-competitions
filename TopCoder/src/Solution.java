import java.util.Scanner;


public class Solution {
	
	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			int price = Integer.parseInt(in.next());
			int deposit = Integer.parseInt(in.next());
			if ( price > deposit ) throw new Exception();
			if ( price > 1000 || price < 0 ) throw new Exception();
			if ( deposit > 1000 || deposit < 0 ) throw new Exception();
			if ( price % 5 != 0 || deposit % 5 != 0 ) throw new Exception();
			int count1 = 0;
			int count25 = 0;
			int count10 = 0;
			int count5 = 0;
			int deff = deposit-price;
			while ( deff >= 100 ) {
				deff-=100;++count1;
			}
			while ( deff >= 25 ) {
				deff-=25;++count25;
			}
			while ( deff >= 10 ) {
				deff-=10;++count10;
			}
			while ( deff >= 5 ) {
				deff-=5;++count5;
			}
			System.out.println(count1+" "+count25+" "+count10+" "+count5);
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
	}

}

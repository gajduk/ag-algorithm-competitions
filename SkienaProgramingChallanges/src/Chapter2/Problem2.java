package Chapter2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Problem2 {
	
	public static final int NUM_CARD_SUITS = 4;
	public static final int NUM_CARD_VALUES = 13;
	public static int hand1[] = new int[5];
	public static int hand2[] = new int[5];

	/*
	public static void main( String args[] ) {
		test();
    	Scanner in = new Scanner(System.in);
    	while ( in.hasNext() ) {
    		
    		String game = in.nextLine();
    		//System.out.println(game);
    		StringTokenizer tkr = new StringTokenizer(game," ");
    		int count = 0;
    		while ( count < 5 ) {
    			String card = tkr.nextToken(); 
    		//	System.out.println(card);
    			hand1[count++] = getCardId(card.charAt(1),card.charAt(0));
    		}
    		count = 0;
    		while ( count < 5 ) {
    			String card = tkr.nextToken(); 
    			hand2[count++] = getCardId(card.charAt(1),card.charAt(0));
    		}
    		sortDecresingOrder(hand1);
    		sortDecresingOrder(hand2);
    		//System.out.println(checkHand(hand1)+"  "+checkHand(hand2));
    		int value1 = checkHand(hand1);
    		int value2 = checkHand(hand2);
    		if ( value1 == value2 ) {
    			int winner = resolveTie(value1);
    			if (  winner == 1 ) {
    				System.out.println("Black wins.");
    			}
    			if (  winner == 2 ) {
    				System.out.println("White wins.");
    			}
    			if (  winner == 0 ) {
    				System.out.println("Tie.");
    			}
    			
    		}
    		else {
    			if ( value1 > value2 ) {
    				System.out.println("Black wins.");
    			}
    			else {
    				System.out.println("White wins.");
    			}
    		}
    	}
    	//test();
	}
	*/
	
	/*
	public static void main( String args[] ) {
		
        FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("F:/Програмирање/UVA judge test/chapter2 problem2.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	while ( in.hasNext() ) {
    		String game = in.nextLine();
    		//System.out.println(game);
    		StringTokenizer tkr = new StringTokenizer(game," ");
    		int count = 0;
    		while ( count < 5 ) {
    			String card = tkr.nextToken(); 
    		//	System.out.println(card);
    			hand1[count++] = getCardId(card.charAt(1),card.charAt(0));
    		}
    		count = 0;
    		while ( count < 5 ) {
    			String card = tkr.nextToken(); 
    			hand2[count++] = getCardId(card.charAt(1),card.charAt(0));
    		}
    		sortDecresingOrder(hand1);
    		sortDecresingOrder(hand2);
    		//System.out.println(checkHand(hand1)+"  "+checkHand(hand2));
    		int value1 = checkHand(hand1);
    		int value2 = checkHand(hand2);
    		if ( value1 == value2 ) {
    			int winner = resolveTie(value1);
    			if (  winner == 1 ) {
    				System.out.println("Black wins.");
    			}
    			if (  winner == 2 ) {
    				System.out.println("White wins.");
    			}
    			if (  winner == 0 ) {
    				System.out.println("Tie.");
    			}
    			
    		}
    		else {
    			if ( value1 > value2 ) {
    				System.out.println("Black wins.");
    			}
    			else {
    				System.out.println("White wins.");
    			}
    		}
    	}
    	test();
	}
	*/
	
	
	public static int resolveTie(int value1) {
		if ( value1 == 0 )
			return resolveHighestCard();
		if ( value1 == 1 ) 
			return resolvePair();
		if ( value1 == 2 )
			return resolvePair();
		if ( value1 == 3 ) 
			return resolveThreeOfAKind();
		if ( value1 == 4 )
			return resolveStraight();
		if ( value1 == 5 )
			return resolveHighestCard();
		if ( value1 == 6 ) 
			return resolveThreeOfAKind();
		if ( value1 == 7 ) 
			return resolveFourOfAKind();
		if ( value1 == 8 ) 
			return resolveStraight();
		return 0;
	}

	public static int resolveFourOfAKind() {
		int count1[] = countValues(hand1);
		int count2[] = countValues(hand2);
		for ( int i = count1.length-1 ; i >= 0 ; --i ) {
			if ( count1[i] == 4 && count2[i] != 4 )
				return 1;
			if ( count1[i] != 4 && count2[i] == 4 )
				return 2;
		}
		return 0;
	}

	public static int resolveStraight() {
		if ( hand1[0]%NUM_CARD_VALUES > hand2[0]%NUM_CARD_VALUES )
			return 1;
		if ( hand2[0]%NUM_CARD_VALUES > hand1[0]%NUM_CARD_VALUES )
			return 2;
		return 0;
	}

	public static int resolveThreeOfAKind() {
		int count1[] = countValues(hand1);
		int count2[] = countValues(hand2);
		for ( int i = count1.length-1 ; i >= 0 ; --i ) {
			if ( count1[i] == 3 && count2[i] != 3 )
				return 1;
			if ( count1[i] != 3 && count2[i] == 3 )
				return 2;
		}
		return 0;
	}

	public static int resolvePair() {
		int count1[] = countValues(hand1);
		int count2[] = countValues(hand2);
		for ( int i = count1.length-1 ; i >= 0 ; --i ) {
			if ( count1[i] == 2 && count2[i] != 2 )
				return 1;
			if ( count1[i] != 2 && count2[i] == 2 )
				return 2;
		}
		return resolveHighestCard();
	}

	public static int resolveHighestCard() {
		int count1[] = countValues(hand1);
		int count2[] = countValues(hand2);
		for ( int i = count1.length-1 ; i >= 0 ; --i ) {
			if ( count1[i] == 1 && count2[i] != 1 )
				return 1;
			if ( count1[i] != 1 && count2[i] == 1 )
				return 2;
		}
		return 0;
	}

	public static void test() {
		int hand1[] = { 0 , 1 , 2 , 3 , 4 };
		sortDecresingOrder(hand1);
		System.out.println(checkHand(hand1));
		int hand2[] = { 1, 2, 3 , 4 , 6 };
		sortDecresingOrder(hand2);
		System.out.println(checkHand(hand2));
		int hand3[] = { 0 , 13 , 1 , 14 , 3 };
		sortDecresingOrder(hand3);
		System.out.println(checkHand(hand3));
		int hand4[] = { 0, 13 ,26 , 1 , 14};
		sortDecresingOrder(hand4);
		System.out.println(checkHand(hand4));
		int hand5[] = { 0 , 13 , 26 , 39 , 1};
		sortDecresingOrder(hand5);
		System.out.println(checkHand(hand5));
		int hand6[] = { 0 , 14 , 15 , 29 , 45 };
		sortDecresingOrder(hand6);
		System.out.println(checkHand(hand6));
		int hand7[] = { 0 , 13 , 15 , 29 , 45 };
		sortDecresingOrder(hand7);
		System.out.println(checkHand(hand7));
		int hand8[] = { 0 , 13 , 26 , 29 , 43 };
		sortDecresingOrder(hand8);
		System.out.println(checkHand(hand8));
		int hand9[] = { 0 , 14 , 15 , 29 , 43 };
		sortDecresingOrder(hand9);
		System.out.println(checkHand(hand9));
		
	}
	
	public static void sortDecresingOrder ( int array[] ) {
		for ( int i = 0 ; i < array.length-1 ; ++i ) {
			int min = array[i]%NUM_CARD_VALUES;
			int min_indeks = i;
			for ( int j = i+1 ; j < array.length ; ++j ) {
				if ( min < array[j]%NUM_CARD_VALUES ) {
					min = array[j]%NUM_CARD_VALUES;
					min_indeks = j;
				}
			}
			int temp = array[i];
			array[i] = array[min_indeks];
			array[min_indeks] = temp;
		}
	}
	
	
	public static int checkHand( int hand[] ) {
		if ( checkStraightFlush(hand) ) return 8;
		if ( checkFourOfAKind(hand) ) return 7;
		if ( checkFulllHouse(hand) ) return 6;
		if ( checkFlush(hand) ) return 5;
		if ( checkStraight(hand) ) return 4;
		if ( checkThreeOfAKind(hand) ) return 3;
		if ( checkTwoPairs(hand) ) return 2;
		if ( checkPair(hand) ) return 1;
		return 0;
	}
	
	public static boolean checkTwoPairs(int[] hand) {
		int count[] = countValues(hand);//how often is a card met in 
		boolean one_pair = false;
		for ( int i = 0 ; i < count.length ; ++i ) {
			if ( count[i] == 2 ) {
				if ( one_pair )
					return true;
				one_pair = true;
			}
		}
		return false;
	}
	
	public static boolean checkPair(int[] hand) {
		int count[] = countValues(hand);
		for ( int i = 0 ; i < count.length ; ++i ) {
			if ( count[i] == 2 ) {
				return true;
			}
		}
		return false;
	}


	public static boolean checkThreeOfAKind(int[] hand) {
		int count[] = countValues(hand);
		for ( int i = 0 ; i < count.length ; ++i ) {
			if ( count[i] == 3 ) {
				return true;
			}
		}
		return false;
	}


	public static boolean checkStraight(int[] hand) {
		for ( int i = 1 ; i < hand.length ; ++i ) {
			if ( hand[i] % NUM_CARD_VALUES != (hand[i-1] % NUM_CARD_VALUES)-1 ) 
				return false;
		}
		return true;
	}


	public static boolean checkFlush(int[] hand) {
		for ( int i = 1 ; i < hand.length ; ++i ) {
			if ( hand[i] / NUM_CARD_VALUES != hand[i-1] / NUM_CARD_VALUES ) 
				return false;
		}
		return true;
	}


	public static boolean checkFulllHouse(int[] hand) {
		int count[] = countValues(hand);
		boolean three_of_a_kind = false;
		boolean pair = false;
		for ( int i = 0 ; i < count.length ; ++i ) {
			if ( count[i] == 3 ) {
				three_of_a_kind = true;
			}
			if ( count[i] == 2 ) {
				pair = true;
			}
		}
		return three_of_a_kind && pair;
	}


	public static int[] countValues ( int hand[] ) {
		int count[] = new int[NUM_CARD_VALUES];
		for ( int i = 0 ; i < hand.length ; ++i ) {
			++count[hand[i]%NUM_CARD_VALUES];
		}
		return count;
	}
	
	
	public static boolean checkFourOfAKind(int[] hand) {
		int count[] = countValues(hand);
		for ( int i = 0 ; i < count.length ; ++i ) {
			if ( count[i] == 4 ) {
				return true;
			}
		}
		return false;
	}


	public static boolean checkStraightFlush(int[] hand) {
		for ( int i = 1 ; i < hand.length ; ++i ) {
			if ( hand[i] / NUM_CARD_VALUES != hand[i-1] / NUM_CARD_VALUES ) 
				return false;
			if ( hand[i] != hand[i-1]-1 )
				return false;
		}
		return true;
	}


	//this function will code input card argument and produce a unique ID
	public static int getCardId( char card_suit , char card_value ) {
		int result_id = 0;
		if ( card_suit == 'D' ) result_id += NUM_CARD_VALUES*1;
		if ( card_suit == 'H' ) result_id += NUM_CARD_VALUES*2;
		if ( card_suit == 'S' ) result_id += NUM_CARD_VALUES*3;
		
		if ( card_value == '3' ) result_id += 1;
		if ( card_value == '4' ) result_id += 2;
		if ( card_value == '5' ) result_id += 3;
		if ( card_value == '6' ) result_id += 4;
		if ( card_value == '7' ) result_id += 5;
		if ( card_value == '8' ) result_id += 6;
		if ( card_value == '9' ) result_id += 7;
		if ( card_value == 'T' ) result_id += 8;
		if ( card_value == 'J' ) result_id += 9;
		if ( card_value == 'Q' ) result_id += 10;
		if ( card_value == 'K' ) result_id += 11;
		if ( card_value == 'A' ) result_id += 12;	
		return result_id;
	}
	
	
	

	
	
	
}

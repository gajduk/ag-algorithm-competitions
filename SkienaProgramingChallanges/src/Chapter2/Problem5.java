package Chapter2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class Problem5 {

	public static final int NUM_CARD_VALUES = 13;
	public static int deck[] = new int[52];//will hold the current deck library
	
	public static void main( String args[] ) {
		
    	Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());//the number of test cases to execute
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		for ( int t = 0 ; t < 52 ; ++t )
    			deck[t] = t;
    		if ( i != 0 )
    			System.out.println();
    		int num_known_shuffles = new Integer(in.nextLine());//the number of shuffles the dealer knows
    		int known_shufles [][] = new int[num_known_shuffles][52];
    		for ( int h = 0 ; h < num_known_shuffles ; ++h ) {
    			for ( int j = 0 ; j < 52 ; ++j ) {
        			known_shufles[h][j] = in.nextInt();
        		}
    			in.nextLine();
    		}
    		while ( true ) {
    			int shuffle = -1;
    			try {
    				shuffle = new Integer(in.nextLine());
    			}
    			catch ( Exception e ) {
    				break;
    			}
    			executeShufle(known_shufles,shuffle-1);
    		}
    		printDeck();
    		
    	}
    	
	}
	
	/*
	public static void main( String args[] ) {
		
		
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("F:/Програмирање/UVA judge test/chapter2 problem5.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());//the number of test cases to execute
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
	    	for ( int t = 0 ; t < 52 ; ++t )
				deck[t] = t;
    		if ( i != 0 )
    			System.out.println();
    		int num_known_shuffles = new Integer(in.nextLine());//the number of shuffles the dealer knows
    		int known_shufles [][] = new int[num_known_shuffles][52];
    		for ( int h = 0 ; h < num_known_shuffles ; ++h ) {
    			for ( int j = 0 ; j < 52 ; ++j ) {
        			known_shufles[h][j] = in.nextInt();
        		}
    			in.nextLine();
    		}
    		while ( true ) {
    			int shuffle = -1;
    			try {
    				shuffle = new Integer(in.nextLine());
    			}
    			catch ( Exception e ) {
    				break;
    			}
    			executeShufle(known_shufles,shuffle-1);
    		}
    		printDeck();
    		
    	}
    	
	}
	*/
	
	public static void executeShufle(int[][] known_shufles, int shuffle) {
		int new_deck[] = new int[52];//will temporally hold the cards as we shuffle them from the deck
		for ( int i = 0 ; i < 52 ; ++i ) {
			new_deck[i] = deck[known_shufles[shuffle][i]-1];
		}
		for ( int i = 0 ; i < 52 ; ++i ) {
			deck[i] = new_deck[i];
		}
	}

	public static void printDeck() {
		for ( int i = 0 ; i < 52 ; ++i ) {
			printCard(deck[i]);
		}
	}

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
	
	//this function can print a card on screen by it's id
	public static void printCard ( int card_id ) {
		if ( card_id % NUM_CARD_VALUES ==  0  ) System.out.print("2");
		if ( card_id % NUM_CARD_VALUES ==  1  ) System.out.print("3");
		if ( card_id % NUM_CARD_VALUES ==  2  ) System.out.print("4");
		if ( card_id % NUM_CARD_VALUES ==  3  ) System.out.print("5");
		if ( card_id % NUM_CARD_VALUES ==  4  ) System.out.print("6");
		if ( card_id % NUM_CARD_VALUES ==  5  ) System.out.print("7");
		if ( card_id % NUM_CARD_VALUES ==  6  ) System.out.print("8");
		if ( card_id % NUM_CARD_VALUES ==  7  ) System.out.print("9");
		if ( card_id % NUM_CARD_VALUES ==  8  ) System.out.print("10");
		if ( card_id % NUM_CARD_VALUES ==  9  ) System.out.print("Jack");
		if ( card_id % NUM_CARD_VALUES ==  10 ) System.out.print("Queen");
		if ( card_id % NUM_CARD_VALUES ==  11 ) System.out.print("King");
		if ( card_id % NUM_CARD_VALUES ==  12 ) System.out.print("Ace");
		System.out.print(" of ");
		if ( card_id / NUM_CARD_VALUES ==  0 ) System.out.print("Clubs");
		if ( card_id / NUM_CARD_VALUES ==  1 ) System.out.print("Diamonds");
		if ( card_id / NUM_CARD_VALUES ==  2 ) System.out.print("Hearts");
		if ( card_id / NUM_CARD_VALUES ==  3 ) System.out.print("Spades");
		System.out.println();
	}
}



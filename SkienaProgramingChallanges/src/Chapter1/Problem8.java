package Chapter1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


/*
1.6.8 Australian Voting
PC/UVa IDs: 110108/10142, Popularity: B, Success rate: low Level: 1
Australian ballots require that voters rank all the candidates in order of choice.
Initially only the first choices are counted, and if one candidate receives more than 50%
of the vote then that candidate is elected. However, if no candidate receives more than
50%, all candidates tied for the lowest number of votes are eliminated. Ballots ranking
these candidates first are recounted in favor of their highest-ranked non-eliminated
candidate. This process of eliminating the weakest candidates and counting their ballots
in favor of the preferred non-eliminated candidate continues until one candidate receives
more than 50% of the vote, or until all remaining candidates are tied.

Input
The input begins with a single positive integer on a line by itself indicating the number
of cases following, each as described below. This line is followed by a blank line. There
is also a blank line between two consecutive inputs.
The first line of each case is an integer n <= 20 indicating the number of candidates.
The next n lines consist of the names of the candidates in order, each up to 80 char-
actors in length and containing any printable characters. Up to 1,000 lines follow, each
containing the contents of a ballot. Each ballot contains the numbers from 1 to n in
some order. The first number indicates the candidate of first choice; the second number
indicates candidate of second choice, and so on.

Output
The output of each test case consists of either a single line containing the name of the
winner or several lines containing the names of all candidates who are tied. The output
of each two consecutive cases are separated by a blank line.
Sample Input
1

3
John Doe
Jane Smith
Jane Austen
1 2 3
2 1 3
2 3 1
1 2 3
3 1 2
Sample Output
John Doe
*/

public class Problem8 {
	
	/*
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		in.nextLine();
		for ( int h = 0 ; h < cases ; ++h ) {
			in.nextLine();
			int num_candidates = in.nextInt();
			in.nextLine();
			String candidates[] = new String[num_candidates];
			int listings[][] = new int[num_candidates][num_candidates];
			for ( int i = 0 ; i < num_candidates ; ++i ) {
				candidates[i] = in.nextLine();
			}
			for ( int i = 0 ; i < num_candidates ; ++i ) {
				String list = in.nextLine();
				StringTokenizer tkr = new StringTokenizer(list," ");
				int j = 0;
				while ( tkr.hasMoreTokens() ) {
					listings[i][j++] = new Integer(tkr.nextToken());
				}
			}
			calcualteWinner(listings,candidates);
		}
		
		
	 }
	*/
	
	/*
	public static void calcualteWinner(int[][] listings , String candidates[]  ) {
		int votes[] = new int[listings.length];
		int running[] = new int[listings.length];
		for ( int i = 0 ; i < running.length ; ++i ) running[i] = 1;
		//print(listings);
		while ( true ) {
			votes = calculateVotes(listings,running);
			//System.out.println();
			print(votes);
			//print(running);
			System.out.println();
			if ( checkVictory(votes, running, candidates) ) {
				break;
			}
			elimenateWeakCandidates(votes,running);
		}
		
	}
	*/
	
	
	public static void print ( int matrix[][] ) {
		for ( int i = 0 ; i < matrix.length ; ++i ) {
			print(matrix[i]);
		}
		System.out.println();
	}
	
	public static void print( int array[] ) {
		for ( int i = 0 ; i < array.length ; ++i ) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	
	}
	
	
	/*
	public static void elimenateWeakCandidates(int[] votes, int[] running) {
		int min;
		min = votes[0];
		int i = 0;
		while ( i < votes.length ) {
			if ( min > votes[i] && running[i] == 1 ) min = votes[i];
			++i;
		}
		for (  i = 0 ; i < votes.length ; ++i ) {
			if ( votes[i] == min ) running[i] = 0; 
		}
		
	}
	*/
	
	/*
	public static int[] calculateVotes(int[][] listings, int[] running) {
		int votes[] = new int[running.length];
		for ( int i = 0 ; i < votes.length ; ++i ) {
			votes[i] = 0;
		}
		for ( int i = 0 ; i < listings.length ; ++i ) {
			for ( int j = 0 ; j < votes.length ; ++j ) {
				if ( running[listings[i][j]-1] == 1 ) {
					++votes[listings[i][j]-1];
					break;
				}
			}
		}
		return votes;
	}
	*/

	/*
	public static boolean checkVictory( int[] votes , int[] running , String candidates[] ) {
		int min , max;
		min = max = votes[0];
		int max_index = 0;
		
		int i = 0;
		while ( i < votes.length ) {
			if ( min > votes[i] && running[i] == 1 ) min = votes[i];
			if ( max < votes[i] ) {
				max = votes[i];
				max_index = i;
			}
			++i;
		}
		//System.out.println(min+" "+max);
		if ( max > candidates.length/2 ) {
			System.out.println(candidates[max_index]);
			return true;
		}
		if ( min == max ) {
			for ( i = 0 ; i < running.length ; ++i ) {
				if ( running[i] == 1 ) System.out.println(candidates[i]);
			}
			return true;
		}
		else {
			
		}
		return false;
		
	}
	*/
	
	public static void main ( String args[ ]) {
		test_file();
		//test_judge();
	}
	
	public static final int MAX_BALLOTS = 1000; 
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter1 problem8.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int num_candidates_1 = new Integer(in.nextLine());
    		String candidates_1[] = new String[num_candidates_1];
    		for (int j = 0; j < num_candidates_1 ; j++) {
    			candidates_1[j] = in.nextLine();
			}
    		int counter = 0;
    		String temp[] = new String[MAX_BALLOTS];
    		if ( in.hasNext() ) {
    			String next_line = in.nextLine();
	    		while ( next_line.length() != 0  ) {
	    			temp[counter++] = next_line;
	    			if ( ! in.hasNext() ) {
	    				break;
	    			}
	    			next_line = in.nextLine();
	    		}
    		}
    		int ballots_1[][] = new int[counter][num_candidates_1];
    		for ( int k = 0 ; k < counter ; ++k ) {
    			StringTokenizer candidates_numbers =  new StringTokenizer(temp[k]);
    			for ( int m = 0 ; m < num_candidates_1 ; ++m  ) {
    				ballots_1[k][m] = new Integer(candidates_numbers.nextToken());
    			}
    		}
    		System.out.println(calcuateWinner(ballots_1, candidates_1));
    		if ( i < num_test_cases-1 ) {
    			System.out.println();
    		}
    	}
		
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int num_candidates_1 = new Integer(in.nextLine());
    		String candidates_1[] = new String[num_candidates_1];
    		for (int j = 0; j < num_candidates_1 ; j++) {
    			candidates_1[j] = in.nextLine();
			}
    		int counter = 0;
    		String temp[] = new String[MAX_BALLOTS];
    		if ( in.hasNext() ) {
    			String next_line = in.nextLine();
	    		while ( next_line.length() != 0  ) {
	    			temp[counter++] = next_line;
	    			if ( ! in.hasNext() ) {
	    				break;
	    			}
	    			next_line = in.nextLine();
	    		}
    		}
    		int ballots_1[][] = new int[counter][num_candidates_1];
    		for ( int k = 0 ; k < counter ; ++k ) {
    			StringTokenizer candidates_numbers =  new StringTokenizer(temp[k]);
    			for ( int m = 0 ; m < num_candidates_1 ; ++m  ) {
    				ballots_1[k][m] = new Integer(candidates_numbers.nextToken());
    			}
    		}
    		System.out.println(calcuateWinner(ballots_1, candidates_1));
    		if ( i < num_test_cases-1 ) {
    			System.out.println();
    		}
    	}
	}
	
	public static String calcuateWinner ( int ballots_1[][] , String candidates[] ) {
		ballots = ballots_1;
		is_eliminated = new boolean[candidates.length];
		num_candidates = candidates.length;
		while ( true ) {
			countVotes();
			int winner = checkWinner();
			if ( winner != -1 ) {
				return candidates[winner];
			}
			if ( checkTie() ) {
				String result = "";
				for (int i = 0; i < candidates.length; i++) {
					if ( ! is_eliminated[i] ) {
						result += "\n"+candidates[i];
					}
				}
				return result.substring(1);
			}
			eliminateCandidates();
		}
	}
	
	public static int current_votes[];
	public static int ballots[][];
	public static boolean is_eliminated[];
	public static int num_candidates;
	
	public static int getNextPreferedCandidate ( int voter_id ) {
		int walker = 0;
		while ( is_eliminated[ballots[voter_id][walker]-1]  ) {
			++walker;
		}
		if ( walker > is_eliminated.length ) {
			System.out.println("ERROR");
			return 0;
		}
		return ballots[voter_id][walker]-1;
	}
	
	public static void countVotes () {
		current_votes = new int[num_candidates];
		for (int i = 0; i < ballots.length; i++) {
			++current_votes[getNextPreferedCandidate(i)];
		}
	}
	
	
	//will return -1 if there is no winner, the winner id otherwise
	public static int checkWinner () {
		for ( int i = 0 ; i < current_votes.length ; ++i ) {
			if ( !is_eliminated[i] && current_votes[i] > ballots.length / 2 ) {
				return i;
			}
		}
		return -1;
	}
	
	public static boolean checkTie() {
		int min = Integer.MAX_VALUE;
		int max = -1;
		for ( int i = 0 ; i < current_votes.length ; ++i ) {
			if ( !is_eliminated[i] ) {
				if ( current_votes[i] < min ) {
					min = current_votes[i];
				}
				if ( current_votes[i] > max ) {
					max = current_votes[i];
				}
			}
		}
		return min == max;
	}
	
	public static void eliminateCandidates() {
		int min = Integer.MAX_VALUE;
		for ( int i = 0 ; i < current_votes.length ; ++i ) {
			if ( !is_eliminated[i] ) {
				if ( current_votes[i] < min ) {
					min = current_votes[i];
				}
			}
		}
		for ( int i = 0 ; i < current_votes.length ; ++i ) {
			if ( !is_eliminated[i] ) {
				if ( current_votes[i] == min ) {
					is_eliminated[i] = true;
				}
			}
		}	
	}
	
	
	
	
	
	

};

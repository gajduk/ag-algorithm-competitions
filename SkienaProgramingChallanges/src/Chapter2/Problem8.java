package Chapter2;

class Mapping {
	public int map;
	public int upper;
};

public class Problem8 {
	
	/*
	public static boolean flags[];
	
	public static int bestChoice ( int dices[][] ) {
		int best = 0;
		flags = new boolean[dices.length];
		int count[][] = new int[dices.length][6];
		for ( int i = 0 ; i < dices.length ; ++i ) {
			count[i] = countDices(dices,i);
		}
		best += checkYahtzee(count);
		best += fullHouse(count);
		best += checkLongStraight(count);
		best += checkShortStraight(count);
		best += checkFourOfAKind(count);
		best += checkThreeOfAKind(count);
		best += checkChance(count);
		for ( int i = 6 ; i > 0 ; --i )
			best += checkCount(count,i);
		return best;
	}

	public static int checkCount(int[][] count, int i) {
		int max = 0;
		for ( int j = 0 ; j < count.length ; ++j ) {
			if ( ! flags[j] ) {
				if ( max < count[j][i] ) {
					max = count[j][i];
				}
			}
		}
		return max*i;
	}

	private static int checkChance(int[][] count) {
		int max = 0;
		for ( int j = 0 ; j < count.length ; ++j ) {
			if ( ! flags[j] ) {
				int sum = 0;
				for ( int h = 0 ; h < 6 ; ++h ) {
					sum += count[j][h]*h;
				}
				if ( max < sum ) {
					max = sum;
				}
			}
		}
		return max;
	}

	private static int checkThreeOfAKind(int[][] count) {
		return 0;
	}

	private static int checkFourOfAKind(int[][] count) {
		return 0;
	}

	private static int checkShortStraight(int[][] count) {
		return 0;
	}

	private static int checkLongStraight(int[][] count) {
		
		return 0;
	}

	private static int fullHouse(int[][] count) {
		return 0;
	}

	private static int checkYahtzee(int[][] count) {
		int min = 7;
		for ( int j = 0 ; j < count.length ; ++j ) {
			if ( ! flags[j] ) {
				for ( int i = 0 ; i < 6 ; ++i ) {
					if ( count[j][i] == 5 ) {
						if ( min > i ) {
							min = i;
							break;
						}
					}
				}
			}
		}
		if ( min == 7 ) return 0;
		return 50;
	}
	*/

	/*
	 * ones - sum of all ones thrown
twos - sum of all twos thrown
threes - sum of all threes thrown
fours - sum of all fours thrown
fives - sum of all fives thrown
sixes - sum of all sixes thrown
chance - sum of all dice
	 * 
	 */
	
	
	/*
	public static final int NUM_CATAGORIES = 13;
	public static final int NUM_ROUNDS = 13;
	public static final int NUM_DICES = 5;
	public static final int NUM_COMBINATIONS = 8192; 
	public static final int NUM_UPPER = 64; 
	public static int num_mapped[] = new int[NUM_COMBINATIONS];
	
	
	public static int ones ( int dices[] ) {
		int score = 0;
		for ( int i = 0 ; i < dices.length ; ++i ) {
			if ( dices[i] == 1 ) {
				score += 1;
			}
		}
		return score;
	}
	
	public static int twos ( int dices[] ) {
		int score = 0;
		for ( int i = 0 ; i < dices.length ; ++i ) {
			if ( dices[i] == 2 ) {
				score += 2;
			}
		}
		return score;
	}

	public static int threes ( int dices[] ) {
		int score = 0;
		for ( int i = 0 ; i < dices.length ; ++i ) {
			if ( dices[i] == 3 ) {
				score += 3;
			}
		}
		return score;
	}

	public static int fours ( int dices[] ) {
		int score = 0;
		for ( int i = 0 ; i < dices.length ; ++i ) {
			if ( dices[i] == 4 ) {
				score += 4;
			}
		}
		return score;
	}

	public static int fives ( int dices[] ) {
		int score = 0;
		for ( int i = 0 ; i < dices.length ; ++i ) {
			if ( dices[i] == 5 ) {
				score += 5;
			}
		}
		return score;
	}
	
	public static int sixes ( int dices[] ) {
		int score = 0;
		for ( int i = 0 ; i < dices.length ; ++i ) {
			if ( dices[i] == 6 ) {
				score += 6;
			}
		}
		return score;
	}
	
	public static int chance ( int dices[] ) {
	  int score = 0;
	  for (int i = 0; i < dices.length; ++i) {
	    score += dices[i];
	  }
	  return score;
	}
	
	public static int three_kind ( int dices[] ) {
	  int score = 0;
	  if (dices[0] == dices[2] || dices[1] == dices[3] || dices[2] == dices[4]) {
	    for (int i = 0; i < dices.length; ++i) {
	      score += dices[i];
	    }
	  }
	  return score;
	}
	
	public static int four_kind(int dices[]) {
		 int score = 0;
		 if ( dices[0] == dices[3] || dices[1] == dices[4] ) {
			 for ( int i = 0 ; i < dices.length ; ++i ) {
			       score += dices[i];
			 }
		 }
     	 return score;
	}
	
	public static int five_kind ( int dices[] ) {
	  int score = 0;
	  if (dices[0] == dices[4]) {
	      score = 50;
	  }
	  return score;
	}
	
	public static int short_straight ( int dices[] ) {
		  int score = 0;
		  if ( dices[0] == dices[1]-1 && dices[1] == dices[2]-1 &&  dices[2] == dices[3]-1 || dices[1] == dices[2]-1 && dices[2] == dices[3]-1 && dices[3] == dices[4]-1 ) {
		    score = 25;
		  }
		  return score;
	}
	
	public static int long_straight ( int dices[] ) {
	   int score = 0;
	   if (dices[0] == dices[1]-1 && dices[1] == dices[2]-1 && dices[2] == dices[3]-1 && dices[3] == dices[4]-1) {
	       score = 35;
	   }
	   return score;
	}
	
	public static int full_house ( int dices[] ) {
	   int score = 0;
	   if ( dices[0] == dices[1] && dices[2] == dices[4] || dices[0] == dices[2] && dices[3] == dices[4] ) {
	       score = 40;
	   }
	   return score;
	}
	
	void best_categorization(int scores[][], int categorization[], int bonus, int score) {
		int best[][] = new int[NUM_CATAGORIES][NUM_UPPER];
		Mapping previous[][] = new Mapping[NUM_COMBINATIONS][NUM_UPPER];
		for (int i = 0; i < NUM_COMBINATIONS; ++i) {
			for (int j = 0; j < NUM_UPPER; ++j) {
				best[i][j] = Integer.MIN_VALUE;
			}
		}
		best[0][0] = 0;
		for (int r = 0; r < NUM_ROUNDS ; ++r) {
			for (int c = 0; c < NUM_CATAGORIES; ++c) {
				int score_1 = scores[r][c];
				int upper = c < 6 ? score : 0;
				for (int map = 0, add = 1 << c; map < NUM_COMBINATIONS; ++map) {
					if (num_mapped[map] != r || map != 0 & add != 0 ) continue;
					for (int u = 0; u < NUM_UPPER; ++u) {
						int newscore = score + best[map][u];
						int newupper = upper + u < NUM_UPPER ? upper + u : NUM_UPPER - 1;
						if (newscore > best[map|add][newupper]) {
							best[map|add][newupper] = newscore;
							previous[map|add][newupper].map = map;
							previous[map|add][newupper].upper = u;
						}
					}
				}
			}
		}
		int map = NUM_COMBINATIONS - 1;
		int upper = NUM_UPPER - 1;
		bonus = 35;
		score = best[map][upper] + bonus;
		for (int u = 0; u < NUM_UPPER; ++u) {
		if (score < best[map][u]) {
		bonus = 0;
		score = best[map][u];
		upper = u;
		}
		}
		
		while ( map != 0 ) {
		Mapping pre = previous[map][upper];
		int c = 0;
		for (int add = map ^ pre.map; (add >>= 1) != 0 ; ++c) /* NOP ;
		categorization[c] = best[map][upper] - best[pre.map][pre.upper];
		map = pre.map;
		upper = pre.upper;
		}
	}
		*/
	
	public static void main ( String args[] ) {
		int faktoritel = 1;
		for ( int i = 2 ; i <= 13 ; ++i ) {
			faktoritel *= i;
		}
		System.out.println(faktoritel);
		for ( int k = 0 ; k < faktoritel ; ++k ) {
			for ( int i = 0 ; i < 13 ; ++i );
		}
	}

	
	/*
	public static int[] countDices(int[][] dices, int i) {
		int count[] = new int[6];
		for ( int j = 0 ; j < 5 ; ++j ) {
			++count[dices[i][j]];
		}
		return count;
	}
	*/
}

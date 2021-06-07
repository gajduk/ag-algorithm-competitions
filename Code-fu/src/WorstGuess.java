
public class WorstGuess {

	
	  public String whoBuysBeer(String[] guesses, String finalScore) {
		  int max = 0;
		  String res = "";
		  for ( String guess : guesses ) {
			  int dist = dist(guess,finalScore);
			  if ( dist == max ) {
				  res = res+" "+getName(guess);
			  }
			  if ( dist > max ) {
				  max = dist;
				  res = getName(guess);
			  }

		  }
		  
		  return res;
	  }

	private String getName(String guess) {
		return guess.substring(0,guess.indexOf(":"));
	}

	private int dist(String guess, String finalScore) {
		int x[] = getCoddes(guess);
		int y[] = getCoddes(finalScore);
		int res = 0;
		for ( int i = 0 ; i < x.length ; ++i ) {
			res += (x[i]-y[i])*(x[i]-y[i]);
		}
		return res;
	}

	private int[] getCoddes(String guess) {
		int res[] = new int[2];
		res[0] = Integer.parseInt(guess.substring(guess.indexOf(":")+1,guess.indexOf("-")));
		res[1] = Integer.parseInt(guess.substring(guess.indexOf("-")+1));
		return res;
	}
}

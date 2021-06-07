
public class Anagrams {
	 public int howMany(String word, String[] words) {
		 	int count[] = new int[250];
		 	for ( int i = 0 ; i < word.length() ; ++i ) {
		 		++count[word.charAt(i)]; 
		 	}
		 	int counter = 0;
		 	for ( int i = 0 ; i < words.length ; ++i ) {
		 		int c[] = new int[250];
		 		for ( int j = 0 ; j < words[i].length() ; ++j ) {
		 			if ( Character.isLetter(words[i].charAt(j)))
		 			c[words[i].charAt(j)]++;
		 		}
		 		int k = 0;
		 		for ( k = 0 ; k < 250 ; ++k ) {
		 			if ( c[k] != count[k] ) {
		 				break;
		 			}
		 		}
		 		if ( k == 250 ) {
		 			++counter;
		 		}
		 	}
		    return counter;
	 }

}


public class CharacterShift {
	public String shift(String word, int n) {
	    String res = "";
	    for ( int i = 0 ; i < word.length() ; ++i ) {
	    	res += (char)((((word.charAt(i)-'a')+n)+26)%26+'a');
	    }
	    return res;
	}
}

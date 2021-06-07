import java.util.Arrays;
import java.util.TreeSet;



public class VowelCount {
	public static void main(String[] args) {
		System.out.println(new VowelCount().count("Heeelo"));

	}
	
  public int count(String sentence) {
    String res = "";
    TreeSet<Character> vowel = new TreeSet(Arrays.asList('A', 'E', 'I', 'O', 'U', 'Y'));
    res += Character.toUpperCase(sentence.charAt(0));
    for ( int i = 1 ; i < sentence.length() ; ++i ) {
    	char c = Character.toUpperCase(sentence.charAt(i));
    	if ( vowel.contains(c) ) {
    		if ( res.charAt(res.length()-1) == c ) continue;
    	}
    	res += c;
    }
//    System.out.println(res);
    int count = 0;
    for ( int i = 0 ; i < res.length() ; ++i ) {
    	char c = res.charAt(i);
    	if ( vowel.contains(c) ) ++count;
    }
    return count;
  }
}
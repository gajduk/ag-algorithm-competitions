import java.util.StringTokenizer;


public class MasterYodasSpeech {
	
	public static void main(String[] args) {
		MasterYodasSpeech m = new MasterYodasSpeech();
		System.out.println(m.translateYoda("Like his father there is much anger in him"));
		
	}
	public String translateYoda(String sentence) {
	    StringTokenizer tkr = new StringTokenizer(sentence,",");
	    String sentence_parts[] = new String[tkr.countTokens()];
	    for ( int i = 0 ; i < sentence_parts.length ; ++i ) {
	    	sentence_parts[i] = tkr.nextToken().toLowerCase().trim();
	    }
	    String result = "";
	    for ( int i = sentence_parts.length-1 ; i >= 0 ; --i ) {
	    	result += sentence_parts[i]+" ";
	    }
	    char temp = Character.toUpperCase(result.charAt(0));
	    result = result.substring(1,result.length()-1);
	    return temp+result; 
	 }
	
	
}

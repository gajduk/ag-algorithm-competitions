import java.util.HashMap;
import java.util.Map;


public class FrequentNumber {
	
	private String[] map = { "2" , "2" , "2" ,
			"3" , "3" , "3" ,
			"4" , "4" , "4" ,
			"5" , "5" , "5" ,
			"6" , "6" , "6" ,
			"7" , "7" , "7" , "7" ,
			"8" , "8" , "8" ,
			"9" , "9" , "9" , "9" };
	
	public String mostFrequentNumber( String[] letterNumbers ) {
		HashMap<String,Integer> counts = new HashMap<String,Integer>();
		String most_often = "";
		for ( int i = 0 ; i < letterNumbers.length ; ++i ) {
			String converted = convert(letterNumbers[i]);
			most_often = converted;
			if ( counts.containsKey(converted) ) {
				counts.put(converted, counts.get(converted)+1);
			}
			else {
				counts.put(converted, 1);
			}
		}
		for ( Map.Entry<String,Integer> e : counts.entrySet() ) {
			int c = e.getValue();
			int mc = counts.get(most_often);
			if ( c > mc ) {
				most_often = e.getKey();
			}
			if ( c == mc ) {
				most_often = most_often.compareTo(e.getKey())<0?most_often:e.getKey();
			}
		}
	    return most_often;
	}
	
	public String convert(String number_as_letters ) {
		String result = "";
		for ( int i = 0 ; i < number_as_letters.length() ; ++i ) {
			result += map[number_as_letters.charAt(i)-'A'];
		}
		return result;
	}

}

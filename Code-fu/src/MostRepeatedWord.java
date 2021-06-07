
import java.util.StringTokenizer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class MostRepeatedWord {
    public String mostRepeated(String sentence) {
        sentence = sentence.toLowerCase();
        String[] words =  new String[1000];
        int[] count = new int[1000];
        int counter = 0;
        StringTokenizer tkr = new StringTokenizer(sentence," ");
        String longest;
        String current;
        while ( tkr.hasMoreTokens() ) {
            current = tkr.nextToken();
            int i;
            for ( i = 0 ; i < counter ; i++ ) {
                if ( words[i].toLowerCase() == current.toLowerCase() ) {
                    count[i]++;
                    break;
                }
            }
            if ( i == counter ) {
                words[counter] = current;
                count[counter] = 0;
                counter++;
            }
        }
        longest = "z";
        int index = 0;
        for ( int i = 0 ; i < counter ; i++  ) {
            if ( count[i] > count[index]  ) {
                longest = words[i];
                
            }
            if ( count[i] == count[index]) {
                if ( longest.toLowerCase().compareTo(words[i].toLowerCase()) > 0 ) {
                    longest = words[i];
                }
            }
        }
      return longest;
    }

    public static String mostRepeated1(String sentence) {
        StringTokenizer tkr = new StringTokenizer(sentence);
        int max = 0;
        String best = "";
        while ( tkr.hasMoreTokens() ) {
            String word = tkr.nextToken();
            StringTokenizer rest = new StringTokenizer(sentence.substring(sentence.indexOf(word)));
            int count = 0;
            while ( rest.hasMoreTokens() ) {
                if ( rest.nextToken().equals(word) ) count++;
            }
            if ( count > max ) {
                max = count;
                best = word;
            }
            if ( count == max ) {
                if ( best.compareTo(word) > 0 ) {
                    max = count;
                    best = word;
                }
            }
            sentence.replaceAll(best,"");
        }
        return best;
    }


}



import java.util.StringTokenizer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class MostUsedCharacter {
    public static String mostUsed(String sentence) {
        int[] flags = new int[26];
        int[] count = new int[26];
        for ( int i = 0 ; i < 26 ; i++) {
                count[i] = 0;
            }
        String word;
        StringTokenizer tkr = new StringTokenizer(sentence," ");
        while ( tkr.hasMoreTokens() ) {
            word = tkr.nextToken();
            for ( int i = 0 ; i < 26 ; i++) {
                flags[i] = 0;
            }
            for ( int i = 0 ; i < word.length() ; i++ ) {
                if ( flags[word.charAt(i)-'a'] == 0  ) {
                    flags[word.charAt(i)-'a'] = 1;
                    count[word.charAt(i)-'a']++;
                }
            }
        }
        int max = 0;
        int  letter = 25;
        for ( int i = 0 ; i < 26 ; i++  ) {
            if ( count[i] > max ) {
                max = count[i];
                letter = i;
            }
        }
        char[] toreturn = new char[1];
        toreturn[0] =  (char) ((char) 'a' + letter);
        return new String(toreturn);
    }
       

}

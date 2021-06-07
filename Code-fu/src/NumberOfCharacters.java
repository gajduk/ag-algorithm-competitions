
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class NumberOfCharacters {
    public int countDistinct(String string) {
        int[] contains = new int[26];
        for ( int i =0 ; i < 26 ; i++ ) {
            contains[i] = 0;
        }
        for ( int i = 0 ; i < string.length() ; i++ ) {
            contains[string.charAt(i)-'a']++;
        }
        int counter = 0;
        for ( int i = 0 ; i < 26 ; i++ ) {
            if ( contains[i] > 0 ) counter++;
        }
        return counter;
    }


}

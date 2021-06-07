
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class PalindromSubstring {
    public String longest(String string) {
        String longest = "";
        for ( int i = 0 ; i < string.length() ; i++ )  {
            for ( int j = i+1 ; j < string.length() ; j++ ) {
                if ( isPalindrom(string.substring(i, j))  ) {
                    if ( j-i > longest.length() ) {
                        longest = string.substring(i,j);
                    }
                    if ( j-i == longest.length() ) {
                        if ( longest.compareTo(string.substring(i, j)) > 0 ) {
                            longest = string;
                        }
                    }


                }
            }
        }
        return longest;
    }
    public boolean isPalindrom(String inString ) {
        for ( int i = 0 ; i <= inString.length() ; i++  ) {
            if ( inString.charAt(i) != inString.charAt(inString.length()-1-i))
                return false;
        }
        return true;
    }

}

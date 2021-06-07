/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class StrongPassword {
        public String easyRemember(String password, String[] knownWords) {
            String result = "";
            for ( int i = 0 ; i < password.length() ; i++ ) {
                char first = password.charAt(i);
                int j;
                for ( j = 0 ; j < knownWords.length ; j++) {
                    if ( knownWords[j].charAt(0) == first ) break;
                }
                if ( j == knownWords.length ) {
                    char[] temp = new char[1];
                    temp[0] = first;
                    result += new String(temp);
                }
                else {
                    result += knownWords[j];
                }
                result += " ";
            }
            return result.substring(0, result.length()-1);
        }



}

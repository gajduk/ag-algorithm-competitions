
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class PalindromSubsequence {
    public int longest(String string) {
        String reversed = new StringBuffer(string).reverse().toString();
        int[][] table = new int[string.length()+1][string.length()+1];
        for ( int i= 0 ; i < string.length()+1 ; i++ ) {
            table[0][i] = 0;
            table[i][0] = 0;
        }
        for ( int i = 1; i < string.length()+1 ;i++ ) {
            for ( int j = 1 ; j < string.length() ; j++ ) {
                if ( reversed.charAt(j-1) == string.charAt(i-1) ) {
                    table[i][j] = table[i-1][j-1]+1;
                }
                else {
                    table[i][j] = max(table[i-1][j],table[i][j-1]);
                }

            }
        }
        return table[string.length()][string.length()];
    }

    private int max(int i, int i0) {
        if ( i > i0 ) return i;
        return i0;
    }

}

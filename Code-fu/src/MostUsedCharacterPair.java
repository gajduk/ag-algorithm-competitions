/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.StringTokenizer;

/**
 *
 * @author Andrej
 */
public class MostUsedCharacterPair {
    public static String mostUsed(String sentence) {
        int[][]  table = new int[26][26];
        for ( int i = 0 ; i < 26 ; i++ ) {
            for ( int j =0 ; j < 26 ; j++ ) {
                table[i][j] = 0;
            }
        }
        StringTokenizer tkr = new StringTokenizer(sentence," ");
        while ( tkr.hasMoreTokens() ) {
            String word = tkr.nextToken();
            for ( int i = 0 ; i < word.length()-1 ; i++ ) {
                table[(int) word.charAt(i) - (int) 'a' ][(int) word.charAt(i+1) - (int) 'a' ]++;
                //table[(int) word.charAt(i+1) - (int) 'a' ][(int) word.charAt(i) - (int) 'a' ]++;
            }
        }
        int max = 0;
        int indi = 0;
        int indj = 0;
        for ( int i = 0 ; i < 26 ; i++ ) {
            for ( int j =0 ; j < 26 ; j++ ) {
                if ( table[i][j] > max  ) {
                    max = table[i][j];
                    indi = i;
                    indj = j;
                }
            }
        }
        char[] toreturn = new char[2];
        toreturn[0] = (char) (indi + 'a');
        toreturn[1] = (char) (indj + 'a');
        return new String(toreturn);
    }
    
    public String mostUsed1(String sentence) {
        int frequency[][] = new int[26][26];
        for ( int i = 0 ; i < 26 ; i++ ) {
            for ( int j = 0 ; j < 26 ; j++ ) {
                frequency[i][j] = 0;
            }
        }
        sentence.trim();
        StringTokenizer tkr = new StringTokenizer(sentence," ");
        while ( tkr.hasMoreTokens() ) {
            String word = tkr.nextToken();
            for ( int i = 0 ; i < word.length()-1 ; i++ ) {
                frequency[(int) word.charAt(i) - (int) 'a' ][(int) word.charAt(i+1) - (int) 'a' ]++;
            }
        }
        int max = -1;
        char char1 = 'A';
        char char2 = 'A';
        for ( int i = 0 ; i < 26 ; i++ ) {
            for ( int j = 0 ; j < 26 ; j++ ) {
                if ( frequency[i][j] > max ) {
                    max = frequency[i][j];
                    char1 = (char) (i+(int)'a');
                    char2 = (char) (j+(int)'a');
                }
            }
        }
        char aray[] = new char[2];
        aray[0] = char1;
        aray[1] = char2;
        return new String(aray);
    }

            
}

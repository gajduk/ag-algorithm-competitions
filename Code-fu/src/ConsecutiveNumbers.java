/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class ConsecutiveNumbers {
    public static int ConsecutiveNumbers(int[] numbers) {
        int numzeros = 0;
        for ( int i = 0 ; i < numbers.length ; i++ ) {
            if ( numbers[i] == 0 ) numzeros++;
        }
        int max = 0;
        for ( int i = 0 ; i < numbers.length ; i++ ) {
            if ( numbers[i] != 0 ) {
                int currentzeros = numzeros;
                int  lastnumber = numbers[i];
                while ( true ) {
                    int j;
                    for (  j = 0 ; j < numbers.length ; j++ ) {
                        if ( numbers[j] == lastnumber+1 ) {
                               break;
                        }
                    }
                    if ( j == numbers.length ) {
                        if ( currentzeros == 0 ) {
                            break;
                        }
                        if ( currentzeros > 0 ) {
                            currentzeros--;
                        }
                     }
                     lastnumber++;
                 }
                if ( lastnumber-numbers[i]+1 > max ) max = lastnumber-numbers[i]+1;
                }
        }
        return max;
        
    }

}

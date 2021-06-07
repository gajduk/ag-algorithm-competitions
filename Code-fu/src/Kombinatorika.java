/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class Kombinatorika {
    public static void  generateVariationWithReapeating ( int number_elements ) {
        for ( int i = 1 ; i < (1 << number_elements) ; i++ ) {
            String variation = "";
            for ( int j = 0 ; j < number_elements ; j++ ) {
                if ( (i & (1<<j)) == 0 ) variation += "0";
                else variation += "1";
            }
            System.out.println(variation);
        }
        
    }

}

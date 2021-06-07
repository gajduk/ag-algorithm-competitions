/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 *
 * @author Andrej
 */
public class RoundDecimal {
     public static String round(String number, int decimals) {
           
                BigDecimal d = new BigDecimal(number);
                d = d.setScale(decimals, RoundingMode.HALF_UP);
                d = d.stripTrailingZeros();
                int temp = 0;
                try {
                    temp = d.intValueExact();
                }
                catch ( Exception e ) {
                    return d.toString();
                }
                return new Integer(temp).toString();
	}



}

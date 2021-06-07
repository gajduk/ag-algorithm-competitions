/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class PalindromNumber {
    public static int closest(int range, int start) {
        int i = start;
        int j = start;
        while ( j <= range ) {
            if ( checkPalindrom(i) ) {
                return i;
            }
            if ( checkPalindrom(j) ) {
                return j;
            }
            i--;
            j++;
        }
        while ( true ) {
            if ( checkPalindrom(i) ) {
                return i;
            }
            i--;
        }
       // return 0;
  }
    public static  boolean checkPalindrom( int number ) {
        String normal = new Integer(number).toString();
        String reversed = new StringBuffer(normal).reverse().toString();
   //     System.out.print(normal+"   "+reversed+" "+ (normal == reversed)+"\n");
        return normal.equals(reversed);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class IntegerPair {
    public static int count( int N ) {
        int counter = 0;
        for ( int i = 10 ; i <= N ; i++ ) {
            String firstnumber = new Integer(i).toString();
            for ( int j = 0 ; j < firstnumber.length() ; j++ ) {
                String secondnumber = firstnumber.substring(0,firstnumber.length()-j-1);
                secondnumber += firstnumber.substring(firstnumber.length()-1);
               // System.out.println("First "+firstnumber+" second: " + secondnumber);
                if ( i + (int) new Integer(secondnumber) == N  ) counter++;
            }
        }
        return counter;
    }


}

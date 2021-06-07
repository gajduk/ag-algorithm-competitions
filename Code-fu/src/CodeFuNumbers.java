/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class CodeFuNumbers {
    public int count(int start, int end) {
        int counter = 0;
        for ( int i = start ; i < end ; i++ ) {
            if ( finalSum(i) == finalMult(i) ) {
                counter++;
            }
        }
        return counter;
    }
    public int finalSum( int number ) {
        if ( number < 10 ) return number;
        int sum = 0;
        while ( number > 0 ) {
            int digit = number % 10;
            number /= 10;
            sum += digit;
        }
        return finalSum(sum);
    }
    public int finalMult( int number ) {
        if ( number < 10 ) return number;
        int sum = 1;
        while ( number > 0 ) {
            int digit = number % 10;
            number /= 10;
            sum *= digit;
        }
        return finalMult(sum);
    }
    

}

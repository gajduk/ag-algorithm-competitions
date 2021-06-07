/**
 *
 * @author Andrej
 */
public class AscendingDescendingDigits {
    public int count(int start, int end) {
        int counter = 0;
        for ( int i = start ; i <= end ; i++ ) {
            if ( checkAscending(i)  || checkDescending(i) ) {
                counter++;
            }
        }
        return counter;
    }

    public boolean checkDescending( int number ) {
        int temp = number;
        int digit;
        int last_digit = temp % 10;
        temp /= 10;
        while ( temp > 0 ) {
            digit = temp % 10;
            temp /= 10;
            if ( digit != last_digit-1 ) return false;
            last_digit = digit;
        }
        return true;
    }

    public boolean checkAscending( int number ) {
        int temp = number;
        int digit;
        int last_digit = temp % 10;
        temp /= 10;
        while ( temp > 0 ) {
            digit = temp % 10;
            temp /= 10;
            if ( digit != last_digit+1 ) return false;
            last_digit = digit;
        }
        return true;
        
    }

}

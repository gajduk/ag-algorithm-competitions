

import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class SortAndReturn {
      public int nth( int[] numbers, int n ) {
           Arrays.sort(numbers);
           return numbers[n];
      }

}

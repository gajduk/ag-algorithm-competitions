/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class DivisorSort {
      public int[] sort(int[] original) {
          int[] re = new int[original.length];
          for ( int i = 0 ; i < original.length ; i++ ) {
              re[i] = numDivisors(original[i]);
          }
          for ( int i = 0 ; i < original.length ; i++ ) {
              int min = 0;
              int ind = -1;
              for ( int j = i+1 ; j < original.length ; j++ ) {
                   if ( re[j] == min ) {
                       if ( original[j] < original[ind] ) {
                           ind = j;
                           min = original[j];
                       }
                   }
                   else {
                         if ( re[j] < min ) {
                             ind = j;
                             min = original[j];
                         }
                         
                   }
              }
              int temp = re[ind];
              re[ind] = re[i];
              re[i] = temp;
              temp = original[ind];
              original[ind] = original[i];
              original[i] = temp;
              
          }
          return original;
      }

      public int numDivisors( int number ) {
          int counter = 2;
          for ( int i = 2 ; i < number ; i++ ) {
              if ( number % i == 0 ) counter++;
          }
          return counter;
      }


}

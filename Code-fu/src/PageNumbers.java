/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class PageNumbers {
      public static int numPages( int digits ) {
          int mark = 10;
          int num_digits = 1;
          int count = 0;
          int i = 1;
          while ( count < digits  ) {
              if ( i == mark ) {
                  num_digits++;
                  mark *= 10;
              }
              count += num_digits;
              i++;
          }
          if ( count != digits ) return -1;
          return i-1;
      }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class Expression {
      public int smallest(String allowed, int target) {
          int[] best = new int[1001];
          for ( int i = 0 ; i < 1001 ; i++ ) {
            best[i] = 1234567;
          }
          for ( int i = 0 ; i < allowed.length() ; i++ ) {
              char a = allowed.charAt(i);
              Character temp = new Character(a);
              if ( temp.isDigit(a) ) {
                  best[a-48] = 1;
              }
          }
          return 0;
      }



}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Andrej
 */
public class PowersOfThree {
          public static int nthElement1( int N ) {
              int[] powers = new int[100000];
              powers[0] = 1;
              for ( int i = 1 ; i < 100000 ; i++ ) {
                  powers[i] = powers[i-1]*3;
              }
              ArrayList<Integer> sums = new ArrayList<Integer>();
              sums.add(0);
              int counter = 1;
              for ( int i = 0 ; i < powers.length ; i++ ) {
                  int currentsize = sums.size();
                  for ( int j = 0 ; j < currentsize ; j++ ) {
                      if ( sums.contains(sums.get(j)+powers[i])) continue;
                      if ( counter++ ==  N ) return sums.get(j)+powers[i];
                      sums.add(sums.get(j)+powers[i]);
                  }
              }
              return 5;
          }

          public static int nthElement( int N ) {
              int[] powers = new int[100000];
              powers[0] = 1;
              for ( int i = 1 ; i < 100000 ; i++ ) {
                  powers[i] = powers[i-1]*3;
              }
              int[] sums = new int[1000000];
              int counter = 0;
              sums[counter++] = 0;
              
              for ( int i = 0 ; i < powers.length ; i++ ) {
                  int currentsize = counter;
                  for ( int j = 0 ; j < currentsize ; j++ ) {
                      if ( counter++ ==  N ) return sums[j]+powers[i];
                      sums[counter-1] = sums[j]+powers[i];
                  }
              }
              return 5;
          }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.StringTokenizer;

/**
 *
 * @author Andrej
 */
public class ChessTournament {
      public String ranklist(String[] players, String[] results) {
          String[] names = new String[players.length];
          String[] numbers = new String[players.length];
          int[] points = new int[players.length];
          for ( int i = 0 ; i < players.length ; i++ ) {
              StringTokenizer tkr = new StringTokenizer(players[i],":");
              numbers[i] = tkr.nextToken();
              names[i] = tkr.nextToken();
              points[i] = 0;
          }
         
          for ( int i = 0 ; i < results.length ; i++ ) {
              StringTokenizer tkr = new StringTokenizer(results[i],"-:");
              String first = tkr.nextToken();
              String second = tkr.nextToken();
              String outcome = tkr.nextToken();
              int index1 = find(numbers,first);
              int index2 = find(numbers,second);
              if ( outcome.equals("F") ) {
                  points[index1] += 2;
              }
              if ( outcome.equals("S") ) {
                  points[index2] += 2;
              }
              if ( outcome.equals("D") ) {
                  points[index1]++;
                  points[index2]++;
              }
          }
          String result = "";
          for ( int i = 0 ; i < players.length ; i++ ) {
              int max = -1;
              int index = -1;
              for ( int j = 0 ; j < players.length ; j++ ) {
                if ( max < points[j] ) {
                    max = points[j];
                    index = j;
                }
                if ( max == points[j] && new Integer(numbers[index]) >  new Integer(numbers[j]) ) {
                    max = points[j];
                    index = j;
                }
              }
              points[index] = -1;
              result += names[index]+",";
          }

          return result.substring(0,result.length()-1);
      }

    private int find( String[] numbers, String number ) {
        for ( int i = 0 ; i < numbers.length ; i++ ) {
            if ( numbers[i].equals(number )) return i;
        }
        return 0;
    }




}

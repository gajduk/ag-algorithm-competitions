/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.StringTokenizer;

/**
 *
 * @author Andrej
 */
public class Mirrors {
      public static int howMany(String sentence1, String sentence2) {
          int count = 0;
          sentence1.trim();
          sentence2.trim();
          StringTokenizer tkr = new StringTokenizer(sentence1," ");
          while ( tkr.hasMoreTokens() ) {
              String word = tkr.nextToken();
              sentence1.replaceAll(word,"");
          }
          tkr = new StringTokenizer(sentence1," ");
          String temp =  " ";
          temp += sentence2;
          temp += " ";
          sentence2 = temp;
          while ( tkr.hasMoreTokens() ) {
              String word = tkr.nextToken();
              String reversedword = " ";
              reversedword +=new StringBuffer(word).reverse().toString();
              reversedword += " ";
              if ( sentence2.indexOf(reversedword) >= 0 ) {
                  System.out.println(reversedword);
                  count++;
              }
          }
          return count;
      }

}

/**
 *
 * @author Andrej Gajduk
 */
      /*
public class Mirrors {
    public static int howMany(String sentence1, String sentence2) {
            String word2[] = new String[1000];
            StringTokenizer tkr2 = new StringTokenizer(sentence2," n");
            int numwords2 = 0;
            while ( tkr2.hasMoreTokens() ) {
                word2[numwords2++] = tkr2.nextToken();
            }
            StringTokenizer tkr1 = new StringTokenizer(sentence1," n");
            int counter = 0;
            int i = 0;
            while ( tkr1.hasMoreTokens() ) {
                StringBuffer buffer=new StringBuffer(tkr1.nextToken().toLowerCase()).reverse();
                String reversed = new String(buffer);
                i = 0;
                while (i < numwords2 && ! reversed.equalsIgnoreCase(word2[i])   )
                     i++;
                if ( i != numwords2 ) counter++;
            }
            return counter;
    }
}

}
*/
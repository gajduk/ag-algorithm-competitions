/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrej
 */
public class RailFenceCipher {
     public static String decode(String cipher, int rails) {
         int[][]  table = new int[rails][cipher.length()];
         for ( int i = 0 ; i < rails ; i++ ) {
             for ( int j = 0 ; j < cipher.length() ; j++ ) {
                 table[i][j] = 0;
             }
         }
         int row = 0;
         boolean up = true;
         for ( int j = 0 ; j < cipher.length() ; j++ ) {
             table[row][j] = 1;
             if ( up ) {
                 ++row;
                 if ( row == rails) {
                     up = !up;
                     --row;
                 }
             }
             if ( ! up ) {
                  --row;
                 if ( row == -1) {
                     up = !up;
                     ++row;
                     ++row;
                 }
             }
         }
         for ( int i = 0 ; i < rails ; i++ ) {
             for ( int j = 0 ; j < cipher.length() ; j++ ) {
                 System.out.print(table[i][j] +" ");
             }
             System.out.print("\n");;
         }
         row = 0;
         up = true;
         char[] temp = new char[cipher.length()];
         int[] coll = new int[rails];
         for ( int i = 0 ; i < rails ; i++ ) {
            coll[i] = 0;
         }
         int counter = 0;
         while ( counter < cipher.length() ) {
                 while ( coll[row] < cipher.length() && table[row][coll[row]] == 0   ) {
                     coll[row]++;
                 }
               //System.out.println(coll[row]+ "   "+counter +"  "+new String(temp) );
                 if (  coll[row] == cipher.length() ) {
                    if ( ++row == rails ) break;
                    continue;
                 }
               //System.out.println(coll[row]+ "   "+counter +"  "+new String(temp) );
                 temp[coll[row]++] = cipher.charAt(counter++);
              

        }
         
         return new String(temp);


     }


}

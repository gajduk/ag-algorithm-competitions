/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class SquareCount1 {
     public static long pos(int N) {
         long[][] table = new long[3350000][3];
         int last_basic = 5;
         int last_basic_2 = 25;
         long i = 0;
         int j = 0;
         long m = 6;
         boolean mm;
         int last_index = 1;
         table[0][0] = 4;
         table[0][1] = 3;
         table[0][2] = 2;
         table[1][0] = 9;
         table[1][1] = 9;
         table[1][2] = 3;
         for ( i = 10 ; true ; i++ ) {
            j = 0 ;
            mm = true;
            while( j <= last_index  ){
                table[j][1]--;
                if ( table[j][1] == 0 ) {
                    table[j][1] = table[j][0];
                    mm = false;
                }
                j++;
            }
             //-------------------------------------------------
            if ( last_basic_2 == i ) {
                //
                int k;
                for ( k = last_basic + 2 ; ; k += 2 ) {
                    boolean flag = true;
                    for ( int z = 1 ; z <= last_index  ; z++ ) {
                        if ( k % table[z][2] == 0 ) {
                            flag = false;
                            break;
                        }
                    }
                    if ( flag ) {
                        table[++last_index][2] = last_basic;
                        table[last_index][0] = table[last_index][1] =  last_basic_2;
                        last_basic_2 = k*k;
                        last_basic = k;
                        mm = false;
                        break;
                    }
                }
             }
             //------------------------------------------
            /*for ( int n1 = 0 ; n1 <= last_index ; n1++ ) {
                System.out.print(table[n1][0]+"  "+table[n1][1] +"\n");
            }
            System.out.print("m="+m+" mm="+mm+" i="+i+"\n");*/
            if ( mm ) m++;
            if ( m == N ) return i;
         }
     }

}

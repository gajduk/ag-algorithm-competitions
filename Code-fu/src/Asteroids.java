/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 *
 *      /*
        for ( int i = 0 ; i < 1001 ; i++ ) {
            for ( int j = 0 ; j < length ; j++ ) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.print("\n");
        }
      *                // if ( total > 0 ) System.out.println(total+"  "+caught+"\n");
      
 */
public class Asteroids {
    public static  int hits(int length, int shieldlength, int[] time, int[] position) {
        int[][] map = new int[1000+1][length]; // time,pos
        for ( int i = 0 ; i < 1001 ; i++ ) {
            for ( int j = 0 ; j < length ; j++ ) {
                map[i][j] = 0;
            }
        }
        for ( int k = 0 ; k < time.length ; k++ ) {
           map[time[k]][position[k]] = 1;
        }

        int[][] table = new int[1001][length];
        for ( int i = 0 ; i < length ; i++ ) {
            table[0][i] = 10000000;
        }
        int temp = 0;
        for ( int i =  shieldlength ; i < length ; i++ ) {
            if ( map[0][i] == 1 ) temp++;
        }
        table[0][0] = temp;
        for ( int i = 1 ; i < 1001 ; i++ ) {
            for ( int j = 0 ; j < length ; j++ ) {
                int caught = 0;
                int total = 0;
                for ( int k = 0 ; k < length ; k++ ) {
                    if ( map[i-1][k] == 1 ) total++;
                }
                for ( int k = j ; k < length && k-j < shieldlength ; k++ ) {
                    if ( map[i-1][k] == 1 ) caught++;
                }

                int hits = total-caught;
                if ( j == 0 ) {
                    table[i][j] = min(table[i-1][j],table[i-1][j+1] ) + hits;
                }
                 else {
                    if ( j == length-1 ) {
                        table[i][j] = min(table[i-1][j-1],table[i-1][j] ) + hits;
                    }
                     else {
                        table[i][j] = min ( table[i-1][j-1],min(table[i-1][j],table[i-1][j+1] )) + hits;
                     }
                 }
            }
        }
        int min = 100000;
        for ( int i = 0 ; i < length ; i++ ) {
            if ( table[1000][i] < min )  min = table[1000][i];
        }
        return min;
    }
    
    public static int min( int  a , int b ) {
        if ( a > b ) return b;
        return a;
    }

}

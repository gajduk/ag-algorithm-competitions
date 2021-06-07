/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class ThirstyWalker {
   public int minCost(int n, int bottle, int[] shopPos, int[] waterPrice) {
      /*  for ( int i = 0 ; i < shopPos.length ; i++ ) {
            int min = shopPos[i];
            int ind = i;
            for ( int j = i+1 ; j < shopPos.length ; j++ ) {
                if ( shopPos[j] < min ) {
                    min = shopPos[j];
                    ind = j;
                }
            }
            int temp = shopPos[i];
            shopPos[i] = shopPos[ind];
            shopPos[ind] = temp;
            temp = waterPrice[i];
            waterPrice[i] = waterPrice[ind];
            waterPrice[ind] = temp;
        }*/
        int map[] =new int[n+1];
        int infinite = 0;
        for ( int i = 0 ; i < waterPrice.length ; i++ ) infinite += waterPrice[i];
        infinite++;
        for ( int j = 0 ; j <= n ; j++ ) map[j] = 9999999;
        for ( int i = 0 ; i < bottle && i <= n; i++ ) map[i] = 0;

        for ( int i = 0 ; i < shopPos.length ; i++ ) {
            for ( int j = shopPos[i]+1 ; j < shopPos[i] + bottle && j <= n; j++) {
                if ( map[j] > map[shopPos[i]]+waterPrice[i] ) {
                     map[j] = map[shopPos[i]]+waterPrice[i];
                }
            }
        }
        for ( int j = 0 ; j <= n ; j++ ) {
            if ( map[j] == 9999999 ) return -1;
        }
        return map[n];


}
}

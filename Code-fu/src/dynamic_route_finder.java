/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class dynamic_route_finder {

    void dynamic_route_find () {

        int []pipes = new int [1000];
        int last=0;
        for ( int i = 0; i < 10000 ; i++)
            pipes[i] = (int)Math.random() % 200;

        int length = 12321;

        int [][]temp = new int [10000][2];

        for ( int used = 1; used < 1000; used++) {

            for ( int i = 0 ; i < used; i++) {
                temp[last][0] = pipes[i];
                temp[last][1] = 1;
                last++;
            }
        }





    }



}

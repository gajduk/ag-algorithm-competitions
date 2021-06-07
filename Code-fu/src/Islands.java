/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class Islands {
      public int howMany(String[] map) {
          int[][] table = new int[map.length][ map[0].length()];
          for ( int i = 0 ; i  < map.length ; i++ ) {
              for ( int j = 0 ; j < map[0].length() ; j++  ) {
                  table[i][j] = 0;
              }
          }
          int count = 0;
          for ( int i = 0 ; i  < map.length ; i++ ) {
              for ( int j = 0 ; j < map[0].length() ; j++  ) {
                  if ( map[i].charAt(j) == 'X' ) {
                      if ( table[i][j] == 0 ) {
                          table[i][j] = ++count;
                          int[] queuex = new int[200000];
                          int[] queuey = new int[200000];
                          int front = 0;
                          int rear = 0;
                          queuex[rear] = i;
                          queuey[rear++] = j;
                          while ( front != rear ) {
                              int curx = queuex[front];
                              int cury = queuey[front++];
                              if ( check(curx-1,cury,map) ) {
                                  if ( map[curx-1].charAt(cury) == 'X' ) {
                                      if ( table[curx-1][cury] == 0 ) {
                                          table[curx-1][cury] = count;
                                          queuex[rear] = curx-1;
                                          queuey[rear++] = cury;
                                      }
                                  }
                              }
                              if ( check(curx+1,cury,map) ) {
                                  if ( map[curx+1].charAt(cury) == 'X' ) {
                                      if ( table[curx+1][cury] == 0 ) {
                                          table[curx+1][cury] = count;
                                          queuex[rear] = curx+1;
                                          queuey[rear++] = cury;
                                      }
                                  }
                              }
                              if ( check(curx,cury-1,map) ) {
                                  if ( map[curx].charAt(cury-1) == 'X' ) {
                                      if ( table[curx][cury-1] == 0 ) {
                                          table[curx][cury-1] = count;
                                          queuex[rear] = curx;
                                          queuey[rear++] = cury-1;
                                      }
                                  }
                              }
                              if ( check(curx,cury+1,map) ) {
                                  if ( map[curx].charAt(cury+1) == 'X' ) {
                                      if ( table[curx][cury+1] == 0 ) {
                                          table[curx][cury+1] = count;
                                          queuex[rear] = curx;
                                          queuey[rear++] = cury+1;
                                      }
                                  }
                              }
                          }
                      }
                  }
              }
          }
          return count;
      }

    private boolean check(int i, int j, String[] map) {
        if ( i >= 0 && j >= 0 && i < map.length  && map[0].length() > j ) return true;
        return false;
    }

// vtorio nacin
    public void mark( int map[][] , int i , int j , int stamp , int length , int length1 ) {
         map[i][j] = stamp;
         if ( ( i-1 >= 0 ) && map[i-1][j] == -1 ) mark(map,i-1,j,stamp,length,length1);
         if ( ( j-1 >= 0 ) && map[i][j-1] == -1 ) mark(map,i,j-1,stamp,length,length1);
         if ( ( i+1 < length ) && map[i+1][j] == -1 ) mark(map,i+1,j,stamp,length,length1);
         if ( ( j+1 < length1 ) && map[i][j+1] == -1 ) mark(map,i,j+1,stamp,length,length1);
    }

      public int howMany1(String[] map) {
          int mapnum[][] = new int[map.length][map[0].length()];
          int counter = 1;
          for ( int i = 0 ; i < map.length ; i++ ) {
              for ( int j = 0 ; j < map[0].length() ; j++ ) {
                  if ( map[i].charAt(j) == 'X' ) {
                      mapnum[i][j] = -1;
                  }
                  else {
                    mapnum[i][j] = 0;
                  }
              }
          }
          for ( int i = 0 ; i < map.length ; i++ ) {
              for ( int j = 0 ; j < map[0].length() ; j++ ) {
                  if (  mapnum[i][j] == -1 ) {
                      mark(mapnum,i,j,counter,map.length,map[0].length());
                      counter++;
                  }
              }
          }
          return counter-1;
      }

}

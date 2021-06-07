/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class ChessFreeSpots {
     public static int[][] qwerty = new int[8][8];
      public static int freeSpots(String[] board) {
          int[][] map = new int[8][8];
          for ( int i = 0 ; i < 8 ; i++ ) {
              for ( int j = 0 ; j < 8 ; j++ ) {
                 map[i][j] = 0;
                 if ( board[i].charAt(j) == ' ' ) {
                     qwerty[i][j] = 0;
                  }
                 else {
                     qwerty[i][j] = 1;
                 }
              }
          }
          for ( int i = 0 ; i < 8 ; i++ ) {
              for ( int j = 0 ; j < 8 ; j++ ) {
                  if ( board[i].charAt(j) == 'Q' ) {
                      populateQ(map,i,j);
                  }
                  if ( board[i].charAt(j) == 'K' ) {
                      populateK(map,i,j);
                  }
                  if ( board[i].charAt(j) == 'R' ) {
                      populateR(map,i,j);
                  }
              }
          }
          int count = 0;
          for ( int i = 0 ; i < 8 ; i++ ) {
              for ( int j = 0 ; j < 8 ; j++ ) {
                  System.out.print(map[i][j]+"  ");
                 if (  map[i][j] == 0 ) count++;
              }
               System.out.print("\n");
          }
          return count;
      }

      public static void populateQ( int[][] map , int x , int y ) {
          int i = x+1;
          int j = y+1;
          while ( check(i,j) ) {
              map[i++][j++] = 1;
          }
          i = x-1;
          j = y-1;
          while ( check(i,j) ) {
              map[i--][j--] = 1;
          }
          i = x+1;
          j = y-1;
          while ( check(i,j) ) {
              map[i++][j--] = 1;
          }
          i = x-1;
          j = y+1;
          while ( check(i,j) ) {
              map[i--][j++] = 1;
          }
          i = x;
          j = y+1;
          while ( check(i,j) ) {
              map[i][j++] = 1;
          }
          i = x;
          j = y-1;
          while ( check(i,j) ) {
              map[i][j--] = 1;
          }
          i = x+1;
          j = y;
          while ( check(i,j) ) {
              map[i++][j] = 1;
          }
          i = x-1;
          j = y;
          while ( check(i,j) ) {
              map[i--][j] = 1;
          }
          map[x][y] = 1;
      }

      public static void populateK( int[][] map , int x , int y ) {
            if ( check(x+2,y+1) ) {
                map[x+2][y+1] = 1;
            }
            if ( check(x+1,y+2) ) {
                map[x+1][y+2] = 1;
            }
            if ( check(x-1,y+2) ) {
                map[x-1][y+2] = 1;
            }
            if ( check(x-2,y+1) ) {
                map[x-2][y+1] = 1;
            }
            if ( check(x-2,y-1) ) {
                map[x-2][y-1] = 1;
            }
            if ( check(x-1,y-2) ) {
                map[x-1][y-2] = 1;
            }
            if ( check(x+1,y-2) ) {
                map[x+1][y-2] = 1;
            }
            if ( check(x+2,y-1) ) {
                map[x+2][y-1] = 1;
            }
            
      }

      public static void populateR( int[][] map , int x , int y ) {
          int i = x;
          int j = y+1;
          while ( check(i,j) ) {
              map[i][j++] = 1;
          }
          i = x;
          j = y-1;
          while ( check(i,j) ) {
              map[i][j--] = 1;
          }
          i = x+1;
          j = y;
          while ( check(i,j) ) {
              map[i++][j] = 1;
          }
          i = x-1;
          j = y;
          while ( check(i,j) ) {
              map[i--][j] = 1;
          }
          map[x][y] = 1;
      }

      public static boolean check( int x , int y ) {
          return x >= 0 && y >=  0  && y < 8 && x < 8 && qwerty[x][y] == 0;
      }



}

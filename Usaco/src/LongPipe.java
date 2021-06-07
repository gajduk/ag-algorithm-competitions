
public class LongPipe {
	
    public static int numSegments( int length , int[] pipes ) {
        int[][] table = new int[pipes.length+1][length+1];
        for ( int i = 0 ; i < pipes.length+1 ; i++ ) {
            table[i][0] = 0;
        }
        for ( int i = 0 ; i  < length+1 ; i++ ) {
            table[0][i] = 20000;
        }
        for ( int i = 1 ; i < pipes.length+1 ; i++ ) {
            for ( int j = 1 ; j < length+1 ; j++ ) {
                if ( j >= pipes[i-1] ) {
                    if ( table[i-1][j] > table[i-1][j-pipes[i-1]]+1 ) {
                        table[i][j] = table[i-1][j-pipes[i-1]]+1;
                    }
                    else {
                        table[i][j] = table[i-1][j];
                    }
                }
                else {
                    table[i][j] = table[i-1][j];
                }
            }
        }
        if ( table[pipes.length][length] > pipes.length ) {
            return -1;
        }
        return table[pipes.length][length];  
    }

    public static int numSegments1( int length , int[] pipes ) {

        return generate_sum(pipes,length,0,0,0);
    }

    private static int generate_sum(int[] pipes, int length, int index, int num_segments , int sum ) {
        if ( index == pipes.length ) {
            if ( sum == length ) return num_segments;
            return -1;
        }
        int a = generate_sum(pipes,length,index+1,num_segments,sum);
        if ( a != -1 ) return a;
        a = generate_sum(pipes,length,index+1,num_segments+1,sum+pipes[index]);
        if ( a != -1 ) return a;
        return -1;
    }

    public static int numSegments2( int length , int[] pipes ) {
        int a =  recursive(pipes,length,pipes.length);
        if ( a > pipes.length ) {
            return -1;
        }
        return a;
    }

    private static int recursive(int[] pipes, int length ,  int index ) {
    	System.out.println(index);
        if ( length == 0 ) {
            return 0;
        }
        if ( index == 0 ) {
            return Integer.MAX_VALUE-10;
        }
        if ( length <= pipes[index-1] ) {
            int a1 = recursive(pipes,length,index-1);
            int a2 = recursive(pipes,length-pipes[index-1],index-1)+1;
            if ( a1 > a2 ) {
            	System.out.println(a2);
                return a2;
            }
            System.out.println(a1);
            return a1;
        }
        return recursive(pipes,length,index-1);
    }
    
    public static int numSegments3(int length, int[] pipes) {
        int a =  recursive1(pipes,length,pipes.length);
          if ( a > pipes.length ) {
              return -1;
          }
          return a;
      }

      private static int recursive1(int[] pipes, int length ,  int index ) {
         if ( length == 0 ) {
              return 0;
          }
          if ( length < 0 ) {
              return Integer.MAX_VALUE-10;
          }
          if ( index == 0 ) {
              return Integer.MAX_VALUE-10;
          }
          if ( length >= pipes[index-1] ) {
              int a1 = recursive1(pipes,length,index-1);
              int a2 = recursive1(pipes,length-pipes[index-1],index-1)+1;
              if ( a1 > a2 ) {
                  return a2;
              }
              return a1;
          }
          return recursive1(pipes,length,index-1);
      }
    
    public static void main ( String args[] ) {
    	
    	int evil[] = {14502711,84917079,601708221,847269305,712513199,151470142,982089837,927648630,613258222,792273386,579138563,218631959,864381557,378549216,835014109
    		,309855222,710654176,857188836,708347039,77617490,597788768,808807449,881981922,169972807,7453019,983600659,281111232,248190884,308962106,177264330,80502002,
    		795967746,141723029,388260981,253331859,818028114,217096148,39366786};
    	System.out.println(numSegments3(2000000000,evil));
    }

}

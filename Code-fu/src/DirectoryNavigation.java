import java.util.StringTokenizer;


public class DirectoryNavigation {
	public static int min(String[] directories) {
	       String[] folders = new String[1000];
	       int[] level = new int[1000];
	       int numf = 0;
	       int[][] t = new int[1000][1000];
	       for ( int i = 0 ; i < 999 ; i++ ) {
	            for ( int j = 0 ; j < 999 ; j++ ) {
	                t[i][j] = 0;
	            }
	       }
	       String prev = "root123";
	       int first = 999;
	       for ( int i = 0 ; i < directories.length ; i++ )  {
	           StringTokenizer tkr = new StringTokenizer(directories[i],"/");
	           first = 999;
	           int counter = 0;
	           while ( tkr.hasMoreTokens() ) {
	               String dir = tkr.nextToken();
	               boolean flag = false;
	               int ind = 0;
	               for ( int j = 0 ; j < numf ; j++ ) {
	                   if ( folders[j].equals(dir) && level[j] == counter ) {
	                        flag = true;
	                        ind = j;
	                        break;
	                   }
	               }
	               if ( !flag ) {
	                   folders[numf++] = dir;
	                   t[first][numf-1] = 1;
	                   level[numf-1] = counter;
	                   first = numf-1;
	               }
	               else {
	                   t[first][ind] = 1;
	                   first = ind;
	                   
	              }
	              counter++;
	               
	          }
	 
	        }

	    int[] queue = new int[10000];
	    int[] mark = new int[1000];
	    
	    for ( int i = 0 ; i < 999 ; i++ ) {
	        mark[i] = 0;
	    }
	    int front = 0;
	    int rear = 0;
	    queue[rear++] = 999;
	    mark[999] = 1;
	    while ( front != rear ) {
	        int current = queue[front++];
	        for ( int j = 0 ; j < 999 ; j++ ) {
	            if ( mark[j] == 0 ) {
	                if ( t[current][j] == 1 ) {
	                    queue[rear++] = j;
	                    mark[j] = 1;
	                }
	            }
	        }
	    }
	    int max = 0;
	    StringTokenizer tkr;
	    for ( int i = 0 ; i < directories.length ; i++ ) {
	        tkr = new StringTokenizer(directories[i],"/");
	        if ( tkr.countTokens() > max  ) max = tkr.countTokens();
	    }
	    return (front-1)*2-max;

	   }
}

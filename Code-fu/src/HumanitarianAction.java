import java.util.StringTokenizer;


public class HumanitarianAction {
	public static String taken(String[] map, String dropouts) {
	       char[] result =  new char[dropouts.length()];
	       int counter = 0;
	       StringTokenizer drops = new StringTokenizer(dropouts,";");
	       while ( drops.hasMoreTokens() ) {
	           StringTokenizer coords = new StringTokenizer(drops.nextToken(),",");
	           int x = new Integer(coords.nextToken());
	           int y = new Integer(coords.nextToken());
	           result[counter++] = search(map, x, y);
	       }
	   
	       return new String(result);
	    }

	    public static char search( String[] map , int x , int y ) {
	        int[] queuex = new int[10000];
	        int[] queuey = new int[10000];
	        int front = 0;
	        int rear = 0;
	        queuex[rear] = x;
	        queuey[rear++] = y;
	        int[][] marks = new int[map.length][map[0].length()];
	        for ( int i = 0 ; i < map.length ; i++ ) {
	            for ( int j = 0 ; j < map[0].length() ; j++ ) {
	                if ( map[i].charAt(j) == '#' ) {
	                    marks[i][j] = 1;
	                }
	                else {
	                    marks[i][j] = 0;
	                }
	           }
	        }
	        int temp = 1;
	        boolean one = false;
	        boolean two = false;
	        char c = ' ';
	        while ( front != rear ) {
	            int currentx = queuex[front];
	            int currenty = queuey[front++];
	            if ( check(currentx,currenty-1,marks) ) {
	                    if ( map[currentx].charAt(currenty-1) != ' ' ) {
	                       if ( one ) two = one;
	                       else {
	                           one =true;
	                           c =  map[currentx].charAt(currenty-1);
	                       }
	                   }
	                    else {
	                        marks[currentx][currenty-1] = 1;
	                        queuex[rear] = currentx;
	                        queuey[rear++] = currenty-1;
	                    }
	            }
	              if ( check(currentx+1,currenty,marks) ) {
	                    if ( map[currentx+1].charAt(currenty) != ' ' ) {
	                       if ( one ) two = one;
	                       else {
	                           one =true;
	                           c =  map[currentx+1].charAt(currenty);}
	                   }
	                    else {
	                        queuex[rear] = currentx+1;
	                        queuey[rear++] = currenty;
	                        marks[currentx+1][currenty] = 1;
	                    }
	            }
	             
	            if ( check(currentx-1,currenty,marks) ) {
	                    if ( map[currentx-1].charAt(currenty) != ' ' ) {
	                       if ( one ) two = one;
	                       else {
	                           one =true;
	                           c =  map[currentx-1].charAt(currenty);}
	                   }
	                    else {
	                        queuex[rear] = currentx-1;
	                        queuey[rear++] = currenty;
	                        marks[currentx-1][currenty] = 1;
	                    }
	            }
	             
	            if ( check(currentx,currenty+1,marks) ) {
	                    if ( map[currentx].charAt(currenty+1) != ' ' ) {
	                       if ( one ) two = one;
	                       else {
	                           one =true;
	                           c =  map[currentx].charAt(currenty+1);}
	                   }
	                    else {
	                       marks[currentx][currenty+1] = 1;
	                        queuex[rear] = currentx;
	                        queuey[rear++] = currenty+1;
	                }
	            }
	            if ( check(currentx,currenty,marks) ) {
	                if ( map[currentx].charAt(currenty) != ' ' ) {
	                           if ( one ) two = one;
	                           else {
	                               one =true;
	                               c =  map[currentx].charAt(currenty);
	                       }
	                }
	                else {
	                       marks[currentx][currenty] = 1;
	                        queuex[rear] = currentx;
	                        queuey[rear++] = currenty;
	                }
	            }
	            
	            if ( temp == front ) {
	                if ( one ) break;
	                temp = rear;
	            }
	        }
	        if ( two ) {
	           return '?';
	        }
	        if ( one ) {
	            return c;
	        }
	        return '*';
	    }

	    private static boolean check(int i, int j, int[][] marks) {
	        return (i >= 0 && i < marks.length && j >= 0 && j < marks[0].length && marks[i][j] == 0);
	    }
}

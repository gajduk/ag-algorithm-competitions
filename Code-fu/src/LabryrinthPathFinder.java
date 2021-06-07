/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class LabryrinthPathFinder {

    public static int minimalMovesMazeSolver( String map[] , int startx , int starty , int endx , int endy ) {
        int[] dx = { -1 , 1 ,  0 , 0 };
        int[] dy = { 0 , 0 , -1 , 1 };
        int[] queuex = new int[map.length*map[0].length()+1000];
        int[] queuey = new int[map.length*map[0].length()+1000];
        int[][] mark = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < map.length ; i++ ) {
            for ( int j = 0 ; j < map[0].length() ; j++ ) {
                mark[i][j] = 0;
            }    
        }
        mark[startx][starty] = 1;
        int rear = 0;
        int front = 0;
        queuex[rear] = startx;
        queuey[rear++] = starty;
        int border = 1;
        int level = 0;
        boolean foundexit = false;
        while ( front != rear ) {
            int currentx = queuex[front];
            int currenty = queuey[front++];
            if ( currentx == endx && currenty == endy ) {
                foundexit = true;
                break;
            }
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] == 0  ) {
                        mark[currentx+dx[i]][currenty+dy[i]] = 1;
                        queuex[rear] = currentx+dx[i];
                        queuey[rear++] = currenty+dy[i];
                    }
                }
            }
            if ( border == front ) {
                border = rear;
                level++;
            }
        }
        System.out.println(foundexit);
        return level;
    }

    private static boolean check(int i, int j,String[] map ) {
        return i>= 0 && i < map.length && j >= 0 && j < map[0].length()&& map[i].charAt(j) != '#';
    }

    public static int minimalMovesMazeSolverDiaogonal( String map[] , int startx , int starty , int endx , int endy ) {
        int[] dx = { -1 , 1 ,  0 , 0 , -1 , 1 , -1 , 1 };
        int[] dy = { 0 , 0 , -1 , 1 , -1 , -1 , 1 , 1 };
        int[] queuex = new int[map.length*map[0].length()+1000];
        int[] queuey = new int[map.length*map[0].length()+1000];
        int[][] mark = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < map.length ; i++ ) {
            for ( int j = 0 ; j < map[0].length() ; j++ ) {
                mark[i][j] = 0;
            }
        }
        mark[startx][starty] = 1;
        int rear = 0;
        int front = 0;
        queuex[rear] = startx;
        queuey[rear++] = starty;
        int border = 1;
        int level = 0;
        boolean foundexit = false;
        while ( front != rear ) {
            int currentx = queuex[front];
            int currenty = queuey[front++];
            if ( currentx == endx && currenty == endy ) {
                foundexit = true;
                break;
            }
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] == 0  ) {
                        mark[currentx+dx[i]][currenty+dy[i]] = 1;
                        queuex[rear] = currentx+dx[i];
                        queuey[rear++] = currenty+dy[i];
                    }
                }
            }
            if ( border == front ) {
                border = rear;
                level++;
            }
        }
        System.out.println(foundexit);
        return level;
    }  

    public static int pathFinderwithExtraMatrix( String map[] , int startx , int starty , int endx , int endy ) {
        int[] dx = { -1 , 1 ,  0 , 0 };
        int[] dy = { 0 , 0 , -1 , 1 };
        int[][] mark = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < map.length ; i++ ) {
            for ( int j = 0 ; j < map[0].length() ; j++ ) {
                mark[i][j] = 0;
            }
        }
        int[][] tovisit = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < tovisit.length ; i++ ) {
            for ( int j = 0 ; j < tovisit[i].length ; j++ ) {
                tovisit[i][j] = 0;
            }
        }
        int next = 1;
        int now = 0;
        int level = 1;
        int border = next;
        tovisit[startx][starty] = 1;
        while ( !  check_null(tovisit) ) {
            int currentx = 0;
            int currenty = 0;
            for ( currentx = 0 ; currentx < tovisit.length ; currentx++ ) {
                for ( currenty = 0 ; currenty < tovisit[0].length ; currenty++ ) {
                    if ( tovisit[currentx][currenty] == 1 ) break;
                }
                if ( currenty != tovisit[0].length ) break;
            }
            now++;
            tovisit[currentx][currenty] = 0;
            mark[currentx][currenty] = level;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] == 0 ) {
                        tovisit[currentx+dx[i]][currenty+dy[i]] = 1;
                        next++;
                    }
                }
            }

            if ( now == border ) {
                border = next;
                level++;
            }
        }



        for ( int i = 0 ; i < tovisit.length ; i++ ) {
            for ( int j = 0 ; j < tovisit[i].length ; j++ ) {
                tovisit[i][j] = 0;
            }
        }
        int[] stackx = new int[100000];
        int[] stacky = new int[100000];
        int top = 0;
        tovisit[endx][endy] = 1;
        for ( int i = 0 ; i < map.length ; i++ ) {
            for ( int j = 0 ; j < map[0].length() ; j++ ) {
                System.out.print(mark[i][j] +"    ");
            }
            System.out.print("\n");
        }
        boolean kill = false;
        while ( !  check_null(tovisit) ) {
            kill = false;
            int currentx = 0;
            int currenty = 0;
            for ( currentx = 0 ; currentx < tovisit.length ; currentx++ ) {
                for ( currenty = 0 ; currenty < tovisit[0].length ; currenty++ ) {
                    if ( tovisit[currentx][currenty] == 1 ) break;
                }
                if ( currenty != tovisit[0].length ) break;
            }
            tovisit[currentx][currenty] = 0;
            stackx[top] = currentx;
            stacky[top++] = currenty;
            int min = 10000;
            int indi = -1;
            int indj = -1;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] > 0 ) {
                        if ( mark[currentx+dx[i]][currenty+dy[i]] == 1 ) {
                            stackx[top] = currentx+dx[i];
                            stacky[top++] = currenty+dy[i];
                            kill = true;
                            break;
                        }
                        if ( mark[currentx+dx[i]][currenty+dy[i]] < min ) {
                            indi = currentx+dx[i];
                            indj = currenty+dy[i];
                            min = mark[currentx+dx[i]][currenty+dy[i]];
                        }
                    }
                }
            }
            if ( kill ) break;
            tovisit[indi][indj] = 1;
        }
        for ( int k = top-1 ; k >= 0 ; k-- ) {
            System.out.println(stackx[k]+"  "+stacky[k]);
        }
        return level;
    }

    public static int pathFinderwithQueue( String map[] , int startx , int starty , int endx , int endy ) {
        int[] dx = { -1 , 1 ,  0 , 0 };
        int[] dy = { 0 , 0 , -1 , 1 };
        int[][] mark = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < map.length ; i++ ) {
            for ( int j = 0 ; j < map[0].length() ; j++ ) {
                mark[i][j] = 0;
            }
        }
        int[] queuex = new int[10000];
        int[] queuey = new int[10000];
        int front = 0;
        int rear = 0;
        int level = 1;
        queuex[rear] = startx;
        queuey[rear++] = starty;
        int border = 1;
        while ( front != rear ) {
            int currentx = queuex[front];
            int currenty = queuey[front++];
            mark[currentx][currenty] = level;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] == 0 ) {
                        queuex[rear] = currentx+dx[i];
                        queuey[rear++] = currenty+dy[i];
                    }
                }
            }

            if ( front == border ) {
                border = rear;
                level++;
            }
        }
        front = 0;
        rear = 0;
        int[] stackx = new int[100000];
        int[] stacky = new int[100000];
        int top = 0;
        queuex[rear] = endx;
        queuey[rear++] = endy;
        boolean kill = false;
        int currentx = endx;
        int currenty = endy;
        while ( currentx != startx || currenty != starty ) {
            kill = false;
            currentx = queuex[front];
            currenty = queuey[front++];
            stackx[top] = currentx;
            stacky[top++] = currenty;
            int min = 10000;
            int indi = -1;
            int indj = -1;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] > 0 ) {
                        if ( mark[currentx+dx[i]][currenty+dy[i]] == 1 ) {
                            stackx[top] = currentx+dx[i];
                            stacky[top++] = currenty+dy[i];
                            kill = true;
                            break;
                        }
                        if ( mark[currentx+dx[i]][currenty+dy[i]] < min ) {
                            indi = currentx+dx[i];
                            indj = currenty+dy[i];
                            min = mark[currentx+dx[i]][currenty+dy[i]];
                        }
                    }
                }
            }
            if ( kill ) break;
            queuex[rear] = indi;
            queuey[rear++] = indj;
        }
        for ( int k = top-1 ; k >= 0 ; k-- ) {
            System.out.println(stackx[k]+"  "+stacky[k]);
        }
        return level;
    }

    public static int reversePathFinder( String map[] , int startx , int starty , int endx , int endy ) {
        int[] dx = { -1 , 1 ,  0 , 0 };
        int[] dy = { 0 , 0 , -1 , 1 };
        int[][] mark = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < map.length ; i++ ) {
            for ( int j = 0 ; j < map[0].length() ; j++ ) {
                mark[i][j] = 0;
            }
        }
        int[][] tovisit = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < tovisit.length ; i++ ) {
            for ( int j = 0 ; j < tovisit[i].length ; j++ ) {
                tovisit[i][j] = 0;
            }
        }
        int next = 1;
        int now = 0;
        int level = 1;
        int border = next;
        tovisit[startx][starty] = 1;
        while ( !  check_null(tovisit) ) {
            int currentx = 0;
            int currenty = 0;
            for ( currentx = 0 ; currentx < tovisit.length ; currentx++ ) {
                for ( currenty = 0 ; currenty < tovisit[0].length ; currenty++ ) {
                    if ( tovisit[currentx][currenty] == 1 ) break;
                }
                if ( currenty != tovisit[0].length ) break;
            }
            now++;
            tovisit[currentx][currenty] = 0;
            mark[currentx][currenty] = level;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] == 0 ) {
                        tovisit[currentx+dx[i]][currenty+dy[i]] = 1;
                        next++;
                    }
                }
            }

            if ( now == border ) {
                border = next;
                level++;
            }
        }



        for ( int i = 0 ; i < tovisit.length ; i++ ) {
            for ( int j = 0 ; j < tovisit[i].length ; j++ ) {
                tovisit[i][j] = 0;
            }
        }
        tovisit[endx][endy] = 1;
        while ( !  check_null(tovisit) ) {
            int currentx = 0;
            int currenty = 0;
            for ( currentx = 0 ; currentx < tovisit.length ; currentx++ ) {
                for ( currenty = 0 ; currenty < tovisit[0].length ; currenty++ ) {
                    if ( tovisit[currentx][currenty] == 1 ) break;
                }
                if ( currenty != tovisit[0].length ) break;
            }
            tovisit[currentx][currenty] = 0;
            mark[currentx][currenty] = level;
            int min = 10000;
            int indi = -1;
            int indj = -1;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] > 0 ) {
                        if ( mark[currentx+dx[i]][currenty+dy[i]] == 1 ) {
                            return 0;
                        }
                        if ( mark[currentx+dx[i]][currenty+dy[i]] < min ) {
                            indi = currentx+dx[i];
                            indj = currenty+dy[i];
                            min = mark[currentx+dx[i]][currenty+dy[i]];
                        }
                    }
                }
            }
            System.out.println(indi+"  "+indj);
            tovisit[indi][indj] = 1;
        }
        return level;
    }

    private static boolean check_null(int[][] tovisit) {
        for ( int i = 0 ; i < tovisit.length ; i++ ) {
            for ( int j = 0 ; j < tovisit[i].length ; j++ ) {
                if ( tovisit[i][j] == 1 ) return false;
            }
        }
        return true;
    }

    public static int oneBoxSokoban ( String[] map , int manx , int many , int boxx , int boxy , int endx , int endy ) {
        int[] dx = { -1 , 1 ,  0 , 0 };
        int[] dy = { 0 , 0 , -1 , 1 };
        int[][] mark = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < map.length ; i++ ) {
            for ( int j = 0 ; j < map[0].length() ; j++ ) {
                mark[i][j] = 0;
            }
        }
        int[][] tovisit = new int[map.length][map[0].length()];
        for ( int i = 0 ; i < tovisit.length ; i++ ) {
            for ( int j = 0 ; j < tovisit[i].length ; j++ ) {
                tovisit[i][j] = 0;
            }
        }
        int next = 1;
        int now = 0;
        int level = 1;
        int border = next;
        tovisit[boxx][boxy] = 1;
        while ( !  check_null(tovisit) ) {
            int currentx = 0;
            int currenty = 0;
            for ( currentx = 0 ; currentx < tovisit.length ; currentx++ ) {
                for ( currenty = 0 ; currenty < tovisit[0].length ; currenty++ ) {
                    if ( tovisit[currentx][currenty] == 1 ) break;
                }
                if ( currenty != tovisit[0].length ) break;
            }
            now++;
            tovisit[currentx][currenty] = 0;
            mark[currentx][currenty] = level;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] == 0 ) {
                        tovisit[currentx+dx[i]][currenty+dy[i]] = 1;
                        next++;
                    }
                }
            }
            if ( now == border ) {
                border = next;
                level++;
            }
        }
        for ( int i = 0 ; i < tovisit.length ; i++ ) {
            for ( int j = 0 ; j < tovisit[i].length ; j++ ) {
                tovisit[i][j] = 0;
            }
        }
        int[] stackx = new int[1000];
        int[] stacky = new int[1000];
        int top = 0;
        
        tovisit[endx][endy] = 1;
        boolean kill = false;
        while ( !  check_null(tovisit) ) {
            kill = false;
            int currentx = 0;
            int currenty = 0;
            for ( currentx = 0 ; currentx < tovisit.length ; currentx++ ) {
                for ( currenty = 0 ; currenty < tovisit[0].length ; currenty++ ) {
                    if ( tovisit[currentx][currenty] == 1 ) break;
                }
                if ( currenty != tovisit[0].length ) break;
            }
            tovisit[currentx][currenty] = 0;
            stackx[top] = currentx;
            stacky[top++] = currenty;
            mark[currentx][currenty] = level;
            int min = 10000;
            int indi = -1;
            int indj = -1;
            for ( int i = 0 ; i < dx.length ; i++ ) {
                if ( check(currentx+dx[i],currenty+dy[i],map) ) {
                    if ( mark[currentx+dx[i]][currenty+dy[i]] > 0 ) {
                        if ( mark[currentx+dx[i]][currenty+dy[i]] == 1 ) {
                            kill = true;
                            break;
                        }
                        if ( mark[currentx+dx[i]][currenty+dy[i]] < min ) {
                            indi = currentx+dx[i];
                            indj = currenty+dy[i];
                            min = mark[currentx+dx[i]][currenty+dy[i]];
                        }
                    }
                }
            }
            if ( kill ) break;
            tovisit[indi][indj] = 1;
        }
        stackx[top] = boxx;
        stacky[top++] = boxy;
        for ( int k = top-1 ; k >= 0 ; k-- ) {
            System.out.println(stackx[k]+"  "+stacky[k]);
        }
        int moves = 0;
        for ( int k = top-1 ; k >= 1 ; k-- ) {
            if ( stackx[k-1] == stackx[k]+1 && stacky[k-1] == stacky[k] ) {
                
                //System.out.println("down");
            }
            if ( stackx[k-1] == stackx[k]-1 && stacky[k-1] == stacky[k] ) {
                //System.out.println("up");
            }
            if ( stackx[k-1] == stackx[k] && stacky[k-1] == stacky[k]-1 ) {
                //System.out.println("left");
            }
                
            if ( stackx[k-1] == stackx[k]+1 && stacky[k-1] == stacky[k]+1 ) {
                //System.out.println("right");
            }
            moves += minimalMovesMazeSolver(map, manx, many, stackx[k],stacky[k]);
            manx = stackx[k];
            many = stacky[k];
        }
        return moves;

        
    }

}

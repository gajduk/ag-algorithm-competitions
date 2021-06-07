/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class LongPipe {

    //rabote UberBrzo ama, samo za mali dolzini
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


    // ne rabote bidejki se predpostavuva deka prvoto podmnozestvo od cevki koe dava zbir == langth e oedno i minimalno so ne e e tocno
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




    //works and it is better then the itterative solution
    public static int numSegments2( int length , int[] pipes ) {
        System.out.println(pipes.length);
        int a = recursive(pipes,length,pipes.length);
        if ( a > pipes.length ) {
            return -1;
        }
        return a;
    }

    private static int recursive(int[] pipes, int length ,  int index ) {
        if ( length == 0 ) {
            return 0;
        }
        if ( index == 0 ) {
            return Integer.MAX_VALUE-10;
        }
        if ( length >= pipes[index-1] ) {
            int a1 = recursive(pipes,length,index-1);
            int a2 = recursive(pipes,length-pipes[index-1],index-1)+1;
            if ( a1 > a2 ) {
                return a2;
            }
            return a1;
        }
        return recursive(pipes,length,index-1);
    }

    // works but it's not the best solution
     public static int numSegments3( int length , int[] pipes ) {
         System.out.println(pipes.length);
         int[] a = new int[(pipes.length+1)/2];
         int[] b = new int[pipes.length-(pipes.length+1)/2];
         for ( int i = 0 ; i < (pipes.length+1)/2 ; i++ ) {
             a[i] = pipes[i];
         }
         for ( int i = (pipes.length+1)/2 ; i < pipes.length ; i++ ) {
             b[i-(pipes.length+1)/2 ] = pipes[i];
         }
         int min = pipes.length+1;
         int[] sums = new int[1<<a.length];
         int[] nums_segs = new int[1<<a.length];
         int sum = 0;
         int num_seg = 0;
         boolean kill = true;
         int count = 1;
         for ( int i = 1 ; i < 1<<a.length; i++ ) {
            sum = 0;
            num_seg = 0;
            kill = false;
            for ( int j = 0 ; j < a.length ; j++ ) {
                if ( (i & (1<<j) ) != 0 ) {
                    sum += pipes[j];
                    num_seg++;
                    if ( sum > length ) {
                        kill = true;
                        break;
                    }
                }
            }
            if ( ! kill ) {
                sums[count] = sum;
                nums_segs[count++] = num_seg;
             }
        }    
        int[] sums1 = new int[1<<a.length];
        int[] nums_segs1 = new int[1<<a.length];
        int count1 = 1;
        for ( int i = 1 ; i < (1<<b.length) ; i++ ) {
                sum = 0;
                num_seg = 0;
                kill = false;
                for ( int j = 0 ; j < b.length ; j++ ) {
                    if ( (i & (1<<j) ) != 0 ) {
                        sum += b[j];
                        num_seg++;
                        if ( sum > length ) {
                            kill = true;
                            break;
                        }
                    }
                }
                if ( ! kill ) {
                    sums1[count1] = sum;
                    nums_segs1[count1++] = num_seg;
                }
        }
        sums[0] = 0;
        nums_segs[0] = 0;
        sums1[0] = 0;
        nums_segs1[0] = 0;
        for ( int i = 0 ; i < count ; i++ ) {
            for ( int j = 0 ; j < count1 ; j++ ) {
                if ( sums[i]+sums[j] == length ) {
                    if ( nums_segs[i]+nums_segs1[j] < min ) {
                        min = nums_segs[i]+nums_segs1[j];
                    }
                }
            }
        }
        if ( min == pipes.length+1 ) return -1;
        return min;
     }


     // doesnt' work because 1<<pipe.length causes overflow and becomes a negative number
     public static int numSegments4( int length , int[] pipes ) {
         int sum = 0;
         int min = pipes.length+1;
         int num_seg = 0;
         boolean kill = true;
         for ( long i = 1 ; i < 1<<pipes.length; i++ ) {
            sum = 0;
            num_seg = 0;
            kill = false;
            for ( int j = 0 ; j < pipes.length ; j++ ) {
                if ( (i & (1<<j) ) != 0 ) {
                    sum += pipes[j];
                    num_seg++;
                    if ( sum > length ) {
                        kill = true;
                        break;
                    }
                }
            }
            if ( ! kill ) {
                System.out.println(sum+"  "+num_seg);
                if ( sum == length )
                    if ( num_seg < min ) min = num_seg;
             }
        }
        if ( min == pipes.length+1 ) return -1;
        return min;
         
     }

}

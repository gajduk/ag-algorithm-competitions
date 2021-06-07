/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Andrej
 */
public class RightWeight {


    //completely hopeless
      public static int ways1(int[] rocks, int amount) {
          amounts = new int[(int)Math.pow(2,(rocks.length/2)+1)];
          index = 0;        
          int temp = 0;
          temp =  recursive(rocks,(rocks.length/2)-1,amount)%1000000000;
          System.out.print((rocks.length/2)-1+"   "+amounts.length+"  "+index+"  "+temp);
          for ( int i = 0 ; i < index ; i++ ) {
                    temp += recursive1(rocks,rocks.length-1,amounts[i])%1000000000;
          }
          return temp;
    }

      
    public  static int[] amounts;
    public  static int index;
    private  static int recursive(int[] rocks, int i, int amount) {
        if ( amount == 0 ) return 1;
        if ( i < 0 ) {
            amounts[index++] = amount;
            return 0;
        }
        if ( rocks[i] <= amount) {
            return recursive(rocks, i-1, amount-rocks[i])+recursive(rocks, i-1, amount);
        }
        else {
            return recursive(rocks, i-1, amount);
        }
    }

    private   static int recursive1(int[] rocks, int i, int amount) {
        if ( amount == 0 ) return 1;
        if ( i == rocks.length/2-1 ) return 0;
        if ( rocks[i] <= amount) {
            return recursive1(rocks, i-1, amount-rocks[i])+recursive1(rocks, i-1, amount);
        }
        else {
            return recursive1(rocks, i-1, amount);
        }
    }


    
    //not working also paintfully slow beacause of ArrayLists see ways2 and ways4 for better alternatives
     public static int ways(int[] rocks, int amount) {
         ArrayList<Integer> sums = new ArrayList<Integer>();
         ArrayList<Integer> ways = new ArrayList<Integer>();
         sums.add(0);
         ways.add(0);
         for ( int i = 0 ; i < rocks.length/2 ; i++ ) {
             int currentsize = sums.size();
             for ( int j = 0 ; j < currentsize ; j++ ) {
                 if ( sums.get(j)+rocks[i] > amount ) continue;
                 int temp = sums.indexOf(sums.get(j)+rocks[i]);
                 if ( temp != -1 ) {
                    ways.set(temp, ways.get(temp)+1);
                 }
                 else {
                    sums.add(sums.get(j)+rocks[i]);
                    ways.add(1);
                 }
             }
         }
         ArrayList<Integer> sums1 = new ArrayList<Integer>();
         ArrayList<Integer> ways1 = new ArrayList<Integer>();
         sums1.add(0);
         ways1.add(0);
         for ( int i = rocks.length/2 ; i < rocks.length ; i++ ) {
             int currentsize = sums1.size();
             for ( int j = 0 ; j < currentsize ; j++ ) {
                 if ( sums1.get(j)+rocks[i] > amount ) continue;
                 int temp = sums1.indexOf(sums1.get(j)+rocks[i]);
                 if ( temp != -1 ) {
                    ways1.set(temp, ways1.get(temp)+1);
                 }
                 else {
                    sums1.add(sums1.get(j)+rocks[i]);
                    ways1.add(1);
                 }
             }
         }
         int temp = sums.indexOf(amount);
         int temp1 = sums1.indexOf(amount);
         if ( temp == - 1 && temp1 == -1 ) return 0; 
         if ( temp1 == -1 ) temp = ways.get(temp);
         else if ( temp == -1 ) temp = ways1.get(temp1);
             else temp = ways.get(temp) + ways1.get(temp1);
         int currentsize = sums.size();
         for ( int j = 1 ; j < currentsize ; j++ ) {
            for ( int i = 1 ; i < sums1.size() ; i++ ) {
                if ( sums1.get(i)+sums.get(j) == amount ) temp += ways.get(j) + ways1.get(i);;
            }
         }
         return temp;
         
     }


    // dosn't work for a lot of cases, slow for 30% test cases, solution almost unreadable ways4 better alternative although it has some problems with memory
    // no explanation why not working
     public static int ways2(int[] rocks, int amount) {
         System.out.println(1<<rocks.length);
         //Arrays.sort(rocks);
         int[] sums = new int[1000000];
         int[] ways = new int[1000000];
         sums[0] = 0;
         ways[0] = 1;
         int num = 1;
         for ( int i = 0 ; i < rocks.length/2 ; i++ ) {
             int currentsize = num;
             for ( int j = 0 ; j < currentsize ; j++ ) {
                 int temp = sums[j]+rocks[i];
                 if ( temp > amount ) continue;
                 int k = 0;
                 for ( k = 0 ; k < num ; k++ ) {
                     if ( sums[k] == temp ) {
                         ways[k]++;
                         break;
                     }
                 }
                 if ( k == num ) {
                    sums[num] = temp;
                    ways[num++] = 1;
                 }
             }
         }
         
         int[] sums1 = new int[1000000];
         int[] ways1 = new int[1000000];
         sums1[0] = 0;
         ways1[0] = 1;
         int num1 = 1;
         for ( int i = rocks.length/2 ; i < rocks.length ; i++ ) {
             int currentsize = num1;
             for ( int j = 0 ; j < currentsize ; j++ ) {
                 int temp = sums1[j]+rocks[i];
                 if ( temp > amount ) continue;
                 int k = 0;
                 for ( k = 0 ; k < num1 ; k++ ) {
                     if ( sums1[k] == temp ) {
                         ways1[k]++;
                         break;
                     }
                 }
                 if ( k == num1 ) {
                    sums1[num1] = temp;
                    ways1[num1++] = 1;
                 }
             }
         }
       /*  int k;
         for ( k = 0 ; k < num1 ; k++ ) {
             if ( sums1[k] == amount ) {
                   break;
             }
         }*/
         int temp = 0;
    /*     if ( k == num1  )  temp = 0;
         else {
             temp += ways1[k];
         }
         for ( k = 0 ; k < num ; k++ ) {
             if ( sums[k] == amount ) {
                   break;
             }
         }
         if ( k == num  )  temp += 0;
         else {
             temp += ways[k];
         }*/
     /*    for ( int j = 1 ; j < num ; j++ ) {
            System.out.println(ways[j]+"   "+sums[j]);
         }System.out.println("dnjw\n\n\n\n\nn\nquwdygbquwydbquybdw");
         for ( int j = 1 ; j < num1 ; j++ ) {
            System.out.println(ways1[j]+"   "+sums1[j]);
         }
         */
         for ( int j = 0 ; j < num ; j++ ) {
            for ( int i = 0 ; i < num1 ; i++ ) {
                if ( sums1[i]+sums[j] == amount ) temp += ( ways[j] * ways1[i] );
            }
         }
         return temp;

     }


     // works for some cases but timeouts for more than 30 members, rocks.length > 30
     public static int ways3( int[] rocks , int amount ) {
        System.out.println(rocks.length);
        int a = recursive3(rocks,amount,rocks.length);
        return a;
    }

    private static int recursive3(int[] pipes, int length ,  int index ) {
        if ( length == 0 ) {
            return 1;
        }
        if ( index == 0 ) {
            return 0;
        }
        if ( length >= pipes[index-1] ) {
            int a1 = recursive3(pipes,length,index-1);
            int a2 = recursive3(pipes,length-pipes[index-1],index-1);
            return a1+a2;
        }
        return recursive3(pipes,length,index-1);
    }


    // dosn't work for a lot of cases, slow for 30% test cases and may generate an out of memory error == index out of bounds
    // no explanation why not working
    public static int ways4(int[] rocks, int amount) {
         int[] sums = new int[10000000];
         int[] ways = new int[10000000];
         sums[0] = 0;
         ways[0] = 1;
         int num = 1;
         int count = 0;
         for ( int i = 0 ; i < rocks.length ; i++ ) {
             int currentsize = num;
             for ( int j = 0 ; j < currentsize ; j++ ) {
                 int current_sum = sums[j]+rocks[i];
                 if ( current_sum > amount ) continue;
                 int k = 0;
                 for ( k = 0 ; k < num ; k++ ) {
                     if ( sums[k] == current_sum ) {
                         ways[k] += ways[j];
                         ways[k] %= 1000000000;
                         break;
                     }
                 }
                 if ( k == num ) {
                    sums[num] = current_sum;
                    ways[num++] = ways[j];
                 }
             }
         }
         for ( int i = 0 ; i < num ; i++ ) {
             //System.out.println(sums[i]+"   "+ways[i]);
             if ( sums[i] == amount ) return ways[i];
         }
         return count;
    }



    //the solution (hopefully)
    // only partially solves the problem still not working for rocks.length > 20-5
    public static int ways5(int[] rocks, int amount) {
        System.out.println( rocks.length);
        int count = 0;
        for ( int i = 1 ; i < (1 << rocks.length) ; i++ ) {
            int sum = 0;
            for ( int j = 0 ; j < rocks.length ; j++ ) {
                if ( (i & (1<<j)) != 0 ) sum += rocks[j];
                if ( sum > amount )    break;
            }
            if ( sum == amount ) count++;
        }
        return count;
    }


    //still causing timeouts , but it works for 89 test cases which is the best so far, probably the only way to solve this is to use a HashMap
    public static int ways6(int[] rocks, int amount) {
        int[] a = new int[(rocks.length+1)/2];
        int[] b = new int[rocks.length-(rocks.length+1)/2];
        int[] sums_a = new int[1<<(rocks.length+1)/2];
        int[] ways_a = new int[1<<(rocks.length+1)/2];
        int[] sums_b = new int[1<<(rocks.length-(rocks.length+1)/2)];
        int[] ways_b = new int[1<<(rocks.length-(rocks.length+1)/2)];
        int num_a = 1;
        int num_b = 1;
        sums_a[0] = 0;
        ways_a[0] = 1;
        sums_b[0] = 0;
        ways_b[0] = 1;
        for ( int i = 0 ; i < (rocks.length+1)/2 ; i++ ) {
            a[i] = rocks[i];
        }
        for ( int i = (rocks.length+1)/2 ; i < rocks.length ; i++ ) {
            b[i-(rocks.length+1)/2] = rocks[i];
        }
        for ( int i = 1 ; i < (1 << a.length) ; i++ ) {
            int sum = 0;
            boolean kill = false;
            for ( int j = 0 ; j < a.length ; j++ ) {
                if ( (i & (1<<j)) != 0 ) sum += a[j];
                if ( sum > amount ) {
                    kill = true;
                    break;
                }
            }
            if ( ! kill ) {
                int k = 0;
                for (  k = 0 ; k < num_a ; k++ ) {
                    if ( sums_a[k] == sum ) {
                        ways_a[k]++;
                        break;
                    }
                }
                if ( k == num_a ) {
                    sums_a[num_a] = sum;
                    ways_a[num_a++] = 1;
                }
            }
        }

        for ( int i = 1 ; i < (1 << b.length) ; i++ ) {
            int sum = 0;
            boolean kill = false;
            for ( int j = 0 ; j < b.length ; j++ ) {
                if ( (i & (1<<j)) != 0 ) sum += b[j];
                if ( sum > amount )  {
                    kill = true;
                    break;
                }
            }
            if ( ! kill ) {
                int k = 0;
                for (  k = 0 ; k < num_b ; k++ ) {
                    if ( sums_b[k] == sum ) {
                        ways_b[k]++;
                        break;
                    }
                }
                if ( k == num_b ) {
                    sums_b[num_b] = sum;
                    ways_b[num_b++] = 1;
                }
            }
        }
        int result = 0;
        for (  int ka = 0 ; ka < num_a ; ka++ ) {
            for (  int kb = 0 ; kb < num_b ; kb++ ) {
                if ( sums_a[ka]+sums_b[kb] == amount ) {
                    result += ways_a[ka]*ways_b[kb];
                    result %= 1000000000;
                }
            }
        }
        return result;     
    }
    
}

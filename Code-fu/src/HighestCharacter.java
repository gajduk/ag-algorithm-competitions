
public class HighestCharacter {
	public static String highest(String[] strings) {
        int[] mark = new int[strings.length];
        int[] tocheck = new int[strings.length];
        for ( int k = 0 ;k < strings.length ; k++ ) {
            mark[k] = 0;
            tocheck[k] = 1;
        }
        boolean flag = false;
        int i = 127;
        int last = 0;
        while ( check(mark) != 1 && i != 1) {
            
                if ( flag ) {
                    for ( int k = 0 ;k < strings.length ; k++ ) {
                       
                        tocheck[k] = mark[k];
                        mark[k] = 0;
                    }
                 
                }
                
                flag = false;
                for ( int j = 0 ; j < strings.length ; j++ ) {
                    if ( tocheck[j] == 1 ) {
                        if ( strings[j].indexOf(i) >= 0 ) {
                            mark[j] = 1;
                            flag = true;
                        }
                    }
                }
                for ( int k = 0 ;k < strings.length ; k++ ) {
                    if ( mark[k] == 1 ) last = k;
                }
                i--;
        }
        if ( check(mark) != 1 ) {
            System.out.print("asdujh");
        String toreturn =strings[last];
            for ( int k = 0 ;k < strings.length ; k++ ) {
                if ( tocheck[k] == 1 ) {
                    if ( toreturn.compareTo(strings[k]) > 0 ) {
                     toreturn = strings[k];
                    }
                }
                
            }
            return toreturn;
        }
        for ( int k = 0 ;k < strings.length ; k++ ) {
               if ( mark[k] == 1 ) return strings[k];
        }
        return strings[last];
    }

    private static int check(int[] mark) {
        int j = 0;
        for ( int i = 0 ; i < mark.length ; i++ ) {
            if ( mark[i] == 1 ) {
                j++;
            }
        }
       
        return j;
    }
}

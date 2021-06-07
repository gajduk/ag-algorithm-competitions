package Chapter2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class Problem1 {
	
	
	public static void main( String args[] ) {
		Scanner in = new Scanner(System.in);
        
        while ( in.hasNext() ) {
            int length = in.nextInt();
            if ( length == 0 ) break;
            int sequence[] = new int[length];
            for ( int i = 0 ; i < length ; ++ i ) {
                sequence[i] = in.nextInt();
            }
            if ( jollyJumperWithSets(sequence) ) {
                System.out.println("Jolly");
            }
            else {
                 System.out.println("Not jolly");
            }
            if ( in.hasNext() )
            	in.nextLine();
        }
    }
    
	
    /*
    public static void main( String args[] ) {
        FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("F:/Програмирање/UVA judge test/chapter2 problem1.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
        
        while ( in.hasNext() ) {
            int length = in.nextInt();
            if ( length == 0 ) break;
            int sequence[] = new int[length];
            for ( int i = 0 ; i < length ; ++ i ) {
                sequence[i] = in.nextInt();
            }
            if ( jollyJumperWithSets(sequence) ) {
                System.out.println("Jolly");
            }
            else {
                 System.out.println("Not jolly");
            }
            if ( in.hasNext() )
            	in.nextLine();
        }
        
    }
    
	*/
    public static boolean jollyJumper( int []sequence ) {
        int mark[] = new int[sequence.length];
        for ( int i = 0 ; i < sequence.length ; ++i ) {
            mark[i] = 0;
        }
        for ( int i = 0 ; i < sequence.length-1 ; ++i ) {
            if ( Math.abs(sequence[i]-sequence[i+1]) < mark.length )
               mark[Math.abs(sequence[i]-sequence[i+1])] = 1;
        }
        int debug = 5;
        for ( int i = 1 ; i < sequence.length ; ++i ) {
            if ( mark[i] == 0 ) return false;
        }
        return true;
    }

    public static boolean jollyJumperWithSets( int []sequence ) {
        HashSet<Integer> meet = new HashSet<Integer>();
        for ( int i = 0 ; i < sequence.length-1 ; ++i ) {
            meet.add(Math.abs(sequence[i]-sequence[i+1]));
        }
        for ( int i = 1 ; i < sequence.length ; ++i) {
            if ( ! meet.contains(i) ) return false;
        }
        return true;
    }

    public static void priorityQueue ( ) {
        TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
        map.put(1,112);
        map.put(2,212);
        map.put(7,312);
        map.put(4,412);
        map.put(5,512);
        System.out.println(map.firstEntry());
        System.out.println(map.lastEntry());
        System.out.println(map.higherKey(2));
        System.out.println(map.floorKey(2));
        System.out.println(map.lowerEntry(3));
        System.out.println(map.containsKey(2));
        System.out.println(map.containsValue(2));
    }
    
    
    
}

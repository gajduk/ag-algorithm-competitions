package Section2;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Prefix {
	
	public static char[] text;
	public static boolean possible[];
	public static char sequence[][];
	public static int last[] = new int[200000];
	public static int last_counter = 0;
	public static int last_sequence[];


	public static boolean isValidPrefix( int star_index , int sequence_id ) {
		for ( int i = star_index ; i < star_index+sequence[sequence_id].length ; ++i ) {
			if ( text[i] != sequence[sequence_id][i-star_index] ) {
				return false;
			}
		}
		return true;
	}
	
	public static int calculateLongestPrefix () {
		boolean change_made = true;
		while ( change_made ) {
			change_made = false;
			print(last);
			for ( int i = 0 ; i < sequence.length ; ++i ) {
				
				/*
				for ( int k = 0 ; k < possible.length ; ++k ) {
					if ( possible[k] ) {
						if ( k+sequence[i].length < possible.length && !possible[k+sequence[i].length] ) {
							if ( isValidPrefix(k, i) ) {
								possible[k+sequence[i].length] = true;
								change_made = true;
								last[last_counter++] =  k+sequence[i].length;
							}
						}
					}
				}
				*/
				int temp = last_counter;
				for ( int k = last_sequence[i] ; k < last_counter ; ++k ) {
					if ( last[k]+sequence[i].length < possible.length && !possible[last[k]+sequence[i].length] ) {
						if ( isValidPrefix(last[k], i) ) {
							last[last_counter++] =  last[k]+sequence[i].length;
							possible[last[k]+sequence[i].length] = true;
							change_made = true;
						}
					}
				}
				last_sequence[i] = last_counter;
			}
		}
		
		
		for ( int k = possible.length-1 ; k >=0 ; --k ) {
			if ( possible[k] ) {
				return k;
			}
		}
		return 0;
	}
	
	private static void print(int[] last2) {
		for ( int i = 0 ; i < last_counter ; ++i ) {
			System.out.print(last2[i]+" ");
		}
		System.out.println();
	}

	public static int findLongestPrefix ( String text1 , String primitives[] ) {
		//text = text1.toCharArray();
		possible = new boolean[text.length+1];
		possible[0] = true;
		sequence = new char[primitives.length][];
		last[last_counter++] = 0;
		last_sequence = new int[sequence.length];
		for ( int i = 0 ; i < primitives.length ; ++i ) {
			sequence[i] = primitives[i].toCharArray();
		}
		return calculateLongestPrefix();
	}
	
	public static void main(String[] args) throws Exception {
		//  BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
			FileInputStream inputFile = null;
		    try {
		    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/prefix.txt");
		    }
	    	catch (FileNotFoundException e) {
		      e.printStackTrace(System.err);
		      return;
		    }
	    	Scanner s = new Scanner(inputFile);
	    	
		//  Scanner s=new Scanner(System.in);
		  
		  String k[]=new String[202];
		  int p=0;
		  while(s.hasNext()){
			   String tak=s.next();
			   if(tak.equals("."))
				   break;
			   k[p++]=tak;
		  }
		  String prims[]=new String [p];
		  for (int i = 0; i < prims.length; i++) {
			  prims[i]=k[i];
		  }
		  System.out.println(Arrays.toString(prims));
		  
		  String take="";
		  while(s.hasNext()){
		      take+=s.next();
		  }
		  text=take.toCharArray();
		  
		  System.out.println(findLongestPrefix(take, prims));
		            
		  System.exit(0);  
	 }

	public static void test_longestPrefix() {
		String []pr = { "A", "AA", "AAA", "AAAA", "AAAAA","AAAAAA", "AAAAAA", "AAAAAAA", "AAAAAAAAB"
				,"AAB" ,"AB", "ABB", "BAB", "BBA"  };
		System.out.println(findLongestPrefix("AAAABAAABAABAAABAABACBA",pr));
	}

}

package Chapter3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Problem7 {

	public static String words[] = new String[25000];
	public static int word_index[][] = new int[16][3500];
	public static int num_words_len[] = new int[16];
	public static int counter[] = new int[25000];
	public static int num_words;
	public static int neighbour[][];
	
	/*
	public static graph[];
	public static String words[] = new String[25144];
	public static int words_length;
	
	public static void print_words () {
		for ( int i = 0 ; i < words.length ; ++i ) {
			System.out.println(words[i]);
		}
	}
	
	public static int getIndex(String word ) {
		for ( int i = 0 ; i < words_length ; ++i ) {
			if ( word.equals(words[i]) )
				return i;
		}
		return -1;
	}

	public static void bfs ( int start , int end ) {
		ArrayList<Integer>  queue = new ArrayList<Integer>();
		int prev[] = new int[graph.length];//the previous vertex, so we can reconstruct the path
		boolean visited[] = new boolean[graph.length];
		for ( int i = 0 ; i < graph.length ; ++i ) {
			prev[i] = -1;
			visited[i] = false;
		}
		prev[start] = -2;
		queue.add(start);
		visited[start] = true;
		while (! queue.isEmpty() ) {
			int current_vertex = queue.remove(0);
			if ( current_vertex == end ) break;
			for ( int adjacent : graph[current_vertex] ) {
				if ( ! visited[adjacent] ) {
					 visited[adjacent]= true;
					 prev[adjacent] = current_vertex;
					 queue.add(adjacent);
				}
			}
		}
		if ( prev[end] == -1 ) {
			System.out.println("No solution.");
			return;
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		int next_vertex = end;
		while ( next_vertex != -2 ) {
			path.add(next_vertex);
			next_vertex = prev[next_vertex];
		}
		for ( int k = path.size()-1 ; k >= 0 ; --k ) {
			System.out.println(words[path.get(k)]);
		}
		
	}

	public static void populateGraph ( String words[] ) {
		graph = new ArrayList[words_length];
		for ( int i = 0 ; i < words_length ; ++i ) {
			graph[i] = new ArrayList<Integer>();
		}
		for ( int i = 0 ; i < words_length ; ++i ) {
			for ( int j = i+1 ; j < words_length ; ++j ) {
				if ( checkDifferences(words[i],words[j])  ) {
					graph[i].add(j);
					graph[j].add(i);
				}
			}
		}
	}
	
	
	public static boolean checkDifferences ( String word_1 , String word_2 ) {
		  boolean result = false;
		  int word_1_length = word_1.length();
		  for ( int i = 0; i < word_1_length ; ++i) {
			    if ( word_1.charAt(i) !=  word_2.charAt(i) ) {
			    	result = !result;
			        if ( !result ) break;
			    }
		  }
		  return result;
	}
	
	
	public static boolean checkDifferences ( String word_1 , String word_2 ) {
		int word_1_length = word_1.length();
		int word_2_length = word_2.length();
		if ( word_1_length == word_2_length ) {
			boolean flag = false;
			for ( int i = 0 ; i < word_1_length ; ++i ) {
				if ( word_1.charAt(i) != word_2.charAt(i) ) {
					if ( flag )
						return false;
					flag = true;
				}
			}
			return true;
		}
		
		if ( word_1_length+1 == word_2_length ) {
		   int i_1 = 0;
			int i_2 = 0;
			while ( i_1 < word_1_length && word_1.charAt(i_1) == word_2.charAt(i_2) ) {
				++i_1; ++i_2;
			}
			++i_2;
			while ( i_2 < word_2_length && word_1.charAt(i_1) == word_2.charAt(i_2) ) {
				++i_1; ++i_2;
			}
			if ( i_1 == word_1.length() )
				return true;
			return false;
		}
		if ( word_1_length == word_2_length+1 ) {
			int i_1 = 0;
			int i_2 = 0;
			while (  i_2 < word_2.length() && word_1.charAt(i_1) == word_2.charAt(i_2) ) {
				++i_1; ++i_2;
			}
			++i_1;
			while ( i_2 < word_2_length && word_1.charAt(i_1) == word_2.charAt(i_2)  ) {
				++i_1; ++i_2;
			}
			if ( i_1 == word_1_length )
				return true;
			return false;
		}
		
		return false;
	}
	*/
	
	public static boolean adjacent ( String word_1 , String word_2 ) {
		boolean end = false;
		  for ( int i = 0 ; i < word_1.length() ; ++i ) {
			  if ( word_1.charAt(i) != word_2.charAt(i) ) {
				  if ( end ) {
					  return false;
				  }
				  end = true;
			  }
		  }
		  return true;
	}
	
	public static void populateGraph () {
		neighbour = new int[25000][500];
		for ( int i = 0 ; i < 16 ; ++i ) {
			populateGraph(i);
		}
	}
	
	public static void populateGraph ( int len ) {
		for ( int i = 0 ; i < num_words_len[len] ; ++i ) {
			for ( int j = i+1 ; j < num_words_len[len] ; ++j ) {
				if ( adjacent(words[word_index[len][i]],words[word_index[len][j]] ) ) {
					neighbour[word_index[len][i]][counter[word_index[len][i]]++] =  word_index[len][j];
					neighbour[word_index[len][j]][counter[word_index[len][j]]++] =  word_index[len][i];
				}
			}
		}
	}
	
	
	private static void bfs( int start, int end ) {
		ArrayList<Integer> queue = new ArrayList<Integer>();
		boolean is_visited[] = new boolean[num_words];
		int prev[] = new int[num_words];
		queue.add(start);
		prev[start] = -1;
		is_visited[start] = true;
		boolean found = false;
		while ( ! queue.isEmpty() ) {
				int current = queue.remove(0);
				if ( current == end ) {
					found = true;
					break;
				}
				for ( int i = 0 ; i < counter[current] ; ++i ) {
					if ( ! is_visited[neighbour[current][i]] ) {
						queue.add(neighbour[current][i]);	
						prev[neighbour[current][i]] = current;
						is_visited[neighbour[current][i]] = true;
					}
				}
		}
		if ( found ) {
			while ( end != -1 ) {
				System.out.println(words[end]);
				end = prev[end];
			}
		}
		else {
			System.out.println("No solution.");
		}
	}

	public static int getIndex(String word1) {
		int l = word1.length()-1;
		for ( int i = 0 ; i < num_words_len[l] ; ++i ) {
			if ( words[word_index[l][i]].equals(word1) ) {
				return word_index[l][i];
			}
		}
		return -1;
	}
	
	public static void main ( String args[] ) {
//		test_countDifferences();
		test_file();
		test_judges();
	}
	
	public static void test_judges() {
		Scanner in = new Scanner(System.in);
		num_words = 0;
    	while ( true ) {
    		String temp = in.nextLine();
    		if ( temp.length() == 0  ) {
    			break;
    		}
    		words[num_words++] = temp;
    		word_index[temp.length()-1][num_words_len[temp.length()-1]++] = num_words-1;
    	}
    	/*
    	for ( int i = 0 ; i < 16 ; ++i ) {
    		for ( int j = 0 ; j < num_words_len[i] ; ++j ) {
    			System.out.println(words_len[i][j]);
    		}
    	}
    	*/
    	populateGraph();
    	/*
    	for ( int i = 0 ; i < num_words ; ++i ) {
    		System.out.println(neighbour[i]);
    	}
    	*/
    	
    	boolean first = false;
    	while ( in.hasNext() ) {
    		if ( first ) {
    			System.out.println();
    		}
    		else {
    			first = true;
    		}
    		String word1 = in.next();
    		String word2 = in.next();
    		try {
    			bfs(getIndex(word2),getIndex(word1));
    		}
    		catch ( Exception e ) {
    			
    		}
    	}
    	
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter3 problem7.txt");
	    }
    	catch ( FileNotFoundException e ) {
	          e.printStackTrace(System.err);
	          return;
	    }
    	Scanner in = new Scanner(inputFile);
    	num_words = 0;
    	while ( true ) {
    		String temp = in.nextLine();
    		if ( temp.length() == 0  ) {
    			break;
    		}
    		words[num_words++] = temp;
    		word_index[temp.length()-1][num_words_len[temp.length()-1]++] = num_words-1;
    	}
    	/*
    	for ( int i = 0 ; i < 16 ; ++i ) {
    		for ( int j = 0 ; j < num_words_len[i] ; ++j ) {
    			System.out.println(words[word_index[i][j]]);
    		}
    	}
    	*/
    	populateGraph();
    	/*
    	for ( int i = 0 ; i < num_words ; ++i ) {
    		System.out.println(neighbour[i]);
    	}
    	*/
    	
    	boolean first = false;
    	while ( in.hasNext() ) {
    		if ( first ) {
    			System.out.println();
    		}
    		else {
    			first = true;
    		}
    		String word1 = in.next();
    		String word2 = in.next();
    		try {
    			 bfs(getIndex(word2),getIndex(word1));
    		}
    		catch ( Exception e ) {
    			
    		}
    	}
    	
	}

	
	
}

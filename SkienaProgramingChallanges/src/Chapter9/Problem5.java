package Chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Problem5 {

	public static String words[][];
	public static int id[][];
	public static int words_counter[];
	public static int num_words;
	public static int length_to[];

	public static boolean areNeigboursSameLength(String word1, String word2) {
		boolean flag = false;
		int i = 0;
		for (; i < word1.length(); ++i) {
			if (word1.charAt(i) != word2.charAt(i)) {
				if (flag) {
					return false;
				} else {
					flag = true;
				}
			}
		}
		return true;
	}

	public static boolean areNeigboursDifferentLength(String word1, String word2) {
		int j = 0;
		int i = 0;
		for (; i < word2.length(); ) {
			if (word1.charAt(j) != word2.charAt(i)) {
				++j;
				if ( j > i + 1 ) {
					return false;
				}
			}
			else {
				++i; ++j;
			}
		}
		return true;
	}

	public static void main(String args[]) {
//		test_file();
		test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter9 problem5.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	words_counter = new int[16];
		words = new String[16][25010];
		length_to = new int[25010];
		id = new int[16][25010];
		int counter = 0;
		int max_length = 0;
		while (in.hasNext()) {
			String word = in.nextLine();
			words[word.length() - 1][words_counter[word.length() - 1]] = word;
			id[word.length() - 1][words_counter[word.length() - 1]++] = counter++;
			int k = word.length() - 1;
			int i = words_counter[k] - 1;
			if (k > 0) {
				for (int j = 0; j < words_counter[k-1]; ++j) {
//					System.out.println("Mindfuck1 ");
					if (areNeigboursDifferentLength(words[k][i],words[k-1][j])) {
//						System.out.println("Mindfuck2");
//						System.out.println(length_to[id[k-1][j]]);	
						if ( length_to[id[k][i]] < length_to[id[k-1][j]]+1 ) {
							 length_to[id[k][i]] = length_to[id[k-1][j]]+1;
						}
//						System.out.println(length_to[id[k][i]]);	
					}
				}
			}
			for (int j = 0; j < i; ++j) {
				if (areNeigboursSameLength(words[k][i], words[k][j])) {
					if (length_to[id[k][i]] < length_to[id[k][j]] + 1) {
						length_to[id[k][i]] = length_to[id[k][j]] + 1;
					}
				}
//				System.out.println(length_to[id[k][i]]);	
			}
			if (k < 15) {
				for (int j = 0; j < words_counter[k+1]; ++j) {
					if (areNeigboursDifferentLength(words[k + 1][j],words[k][i])) {
						if (length_to[id[k][i]] < length_to[id[k+1][j]] + 1) {
							length_to[id[k][i]] = length_to[id[k+1][j]] + 1;
						}
					}
				}
//				System.out.println(length_to[id[k][i]]);	
			}
			if ( length_to[counter-1] > max_length ) {
				max_length = length_to[counter-1];
			}
			
//			System.out.println(word+" "+max_length);
		}
		/*
		for ( int i = 0 ; i < 16 ; ++i ) {
			for ( int j = 0 ; j < words_counter[i] ; ++j ) {
				System.out.print(words[i][j]+" "+id[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(Arrays.toString(length_to));
		*/
		++max_length;
		System.out.println(max_length);
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		words_counter = new int[16];
		words = new String[16][4000];
		length_to = new int[25010];
		id = new int[16][4000];
		int j = 0;
		int counter = 0;
		int max_length = 0;
		while (in.hasNext()) {
			String word = in.nextLine();
			words[word.length() - 1][words_counter[word.length() - 1]] = word;
			id[word.length() - 1][words_counter[word.length() - 1]++] = counter++;
			int k = word.length() - 1;
			int i = words_counter[k] - 1;
			if (k > 0) {
				j = 0;
				for (; j < words_counter[k-1]; ++j) {
					if (areNeigboursDifferentLength(words[k][i],words[k-1][j])) {
						if ( length_to[id[k][i]] < length_to[id[k-1][j]]+1 ) {
							 length_to[id[k][i]] = length_to[id[k-1][j]]+1;
						}
					}
				}
			}
			
			for (; j < i; ++j) {
				if (areNeigboursSameLength(words[k][i], words[k][j])) {
					if (length_to[id[k][i]] < length_to[id[k][j]] + 1) {
						length_to[id[k][i]] = length_to[id[k][j]] + 1;
					}
				}	
			}
			if (k < 15) {
				j = 0;
				for (; j < words_counter[k+1]; ++j) {
					if (areNeigboursDifferentLength(words[k + 1][j],words[k][i])) {
						if (length_to[id[k][i]] < length_to[id[k+1][j]] + 1) {
							length_to[id[k][i]] = length_to[id[k+1][j]] + 1;
						}
					}
				}	
			}
			if ( length_to[counter-1] > max_length ) {
				max_length = length_to[counter-1];
			}
		}
		++max_length;
		System.out.println(max_length);
	}

}

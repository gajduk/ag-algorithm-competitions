package mendo;

import java.util.Scanner;

public class Kod {

	static int table[];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String word = in.next();
		table = new int[word.length()];
		System.out.println(numWords(word,word.length()-1));
		
	}

	private static int numWords(String word, int i) {
		if ( i <= 0 ) return 1;
		if ( table[i] == 0 ) {
			int one = 0;
			if ( word.charAt(i) >= '1' &&  word.charAt(i) <= '9' ) one = numWords(word, i-1);
			int two = 0;
			if ( i >= 1 && check(word,i) ) two = numWords(word,i-2);
			table[i] = one+two;
		}
		return table[i];
	}

	private static boolean check(String word, int i) {
		int code = Integer.parseInt(word.substring(i-1,i+1));
		return code <= 25;
	}
}

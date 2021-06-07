package round143;

import java.util.Scanner;

public class ProblemA {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		int count = 0;
		for (int j = 0; j < n ; j++) {
			String s = in.nextLine();
			int c = 0;
			if ( s.charAt(0) == '1' ) ++c;
			if ( s.charAt(2) == '1' ) ++c;
			if ( s.charAt(4) == '1' ) ++c;
			if ( c >= 2 ) ++count;
		}
		System.out.println(count);
	}

}

package ACM_2003;

import java.util.Arrays;
import java.util.Scanner;

public class A {
	
	public static int map[] = new int[300];
	public static char cipher[] = new char[26];
	public static char default_cipher[];
	
	public static void main ( String args[] ) {
		default_cipher = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		for ( int i = 0 ; i < default_cipher.length ; ++i ) {
			cipher[i] = default_cipher[i];
		}
		for ( int i = 0 ; i < cipher.length ; ++i ) {
			map[cipher[i]] = i;
		}
//		System.out.println(Arrays.toString(map));
		Scanner in = new Scanner(System.in);
		while ( in.hasNext() ) {
			String command = in.nextLine();
			if ( command.indexOf("ENCRYPT") == 0 ) {
				encrypt(command.substring(8));
			}
			else {
			if ( command.indexOf("DECRYPT") == 0 ) {

				decrypt(command.substring(8));
			}
			else {
			
			if ( command.indexOf("CIPHER") == 0 ) {
				setCipher(command.substring(7));
			}
			else {
				System.out.println("Command not understood.");
			}
			}
			}
		
		}
	}

	public static void decrypt( String s ) {
		char result[] = new char[s.length()];
		int prev = map['A'];
		for ( int i = 0 ; i < s.length() ; ++i ) {
			if ( s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' || s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) {
				int index = (26+map[Character.toUpperCase(s.charAt(i))]+prev)%26;
//				System.out.println(index);
				result[i] = cipher[index];
				prev = map[Character.toUpperCase(result[i])];
			}
			else {
				result[i] = s.charAt(i);
				prev = map['A'];
			}
		}
		for ( int i = 0 ; i < s.length() ; ++i ) {
			if ( s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) {
				result[i] = Character.toLowerCase(result[i]);
			}
		}
		System.out.println("RESULT: "+new String(result));
	}

	private static void encrypt( String s ) {
		char result[] = new char[s.length()];
		int prev = map['A'];
		for ( int i = 0 ; i < s.length() ; ++i ) {
			if ( s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' || s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) {
				int index = (26+map[Character.toUpperCase(s.charAt(i))]-prev)%26;
//				System.out.println(index);
				result[i] = cipher[index];
				prev = map[Character.toUpperCase(s.charAt(i))];
			}
			else {
				result[i] = s.charAt(i);
				prev = map['A'];
			}
		}
		for ( int i = 0 ; i < s.length() ; ++i ) {
			if ( s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) {
				result[i] = Character.toLowerCase(result[i]);
			}
		}
		System.out.println("RESULT: "+new String(result));
	}

	private static void setCipher(String substring) {
		for ( int i = 0 ; i < map.length ; ++i ) {
			map[i] = -1;
		}
		int count = 0;
		for ( int k = 0 ; k < substring.length() ; ++k ) {
			if ( substring.charAt(k) >= 'A' && substring.charAt(k) <= 'Z' || substring.charAt(k) >= 'a' && substring.charAt(k) <= 'z' ) {
				if ( map[substring.charAt(k)] != -1 ) {
					System.out.println("Bad cipher.  Using default. ");
					for ( int i = 0 ; i < default_cipher.length ; ++i ) {
						cipher[i] = default_cipher[i];
					}
					for ( int i = 0 ; i < cipher.length ; ++i ) {
						map[cipher[i]] = i;
					}
					return;
				}
				else {
					cipher[count] = Character.toUpperCase(substring.charAt(k));
					map[cipher[count]] = count++;
				}
			}
			
		}
		if ( count == 26 ) {
			System.out.println("Good cipher.  Using "+new String(cipher));
		}
		else {
			System.out.println("Bad cipher.  Using default. ");
			for ( int i = 0 ; i < default_cipher.length ; ++i ) {
				cipher[i] = default_cipher[i];
			}
			for ( int i = 0 ; i < cipher.length ; ++i ) {
				map[cipher[i]] = i;
			}
			return;
		}
	}
	

}

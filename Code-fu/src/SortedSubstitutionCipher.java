
public class SortedSubstitutionCipher {
	
	public static void main ( String args[] ) {
		System.out.println(encrypt("abcdefghijklmnopqrstuvxwyz"));
	}
	
	 public static String encrypt(String message) {
		 char substitute[] = new char[27];
		 int count = 'a';
		 for ( int i = 0 ; i < message.length() ; ++i ) {
			 if ( substitute[message.charAt(i)-'a'+1] == 0 ) {
				 substitute[message.charAt(i)-'a'+1] = (char) count;
				 ++count;
			 }
		 }
		 String result = "";
		 for ( int i = 0 ; i < message.length() ; ++i ) {
				result += substitute[message.charAt(i)-'a'+1];
		 }
		 return result;
	 }

}


public class OtpEncrypt {
	
	public static void main ( String args[] ) {
		for ( int i = 0 ; i < 127 ; ++i ) {
			for ( int j = 0 ; j < 127 ; ++j ) {
				System.out.println(encrypt(i,j));
			}
		}
	}
	
	public static  String encrypt(int[] data, int[] key) {
		String result = "";
		for ( int i = 0 ; i < data.length ; ++i ) {
			result += encrypt(data[i],key[i%key.length]);
			if ( i < data.length-1 ) {
				result += ",";
			}
		}
		return result;
	}
	
	public static String encrypt(int data, int key) {
		String s_data = Integer.toBinaryString(data);
		String s_key= Integer.toBinaryString(key);
		int max_length = Math.max(s_data.length(),s_key.length());
		while ( s_data.length() < max_length ) {
			s_data = "0"+s_data;
		}
		while ( s_key.length() < max_length ) {
			s_key = "0"+s_key;
		}
		String result = "";
		for ( int i = 0 ; i < max_length ; ++i ) {
			result += s_data.charAt(i) == s_key.charAt(i) ? "0" : "1";
		}
		int res = Integer.parseInt(result, 2);
		return Integer.toString(res);
	}

}

package srm.round144;

import java.util.Arrays;

public class BinaryCode {
	
	public static void main(String[] args) {
		BinaryCode b = new BinaryCode();
		System.out.println(Arrays.toString(b.decode("0")));
	}
	
	public String[] decode(String message) {
		String []result = new String[2];
		if ( message.length() == 1 ) {
			result[0] = message.charAt(0)=='0'?"0":"NONE";
			result[1] = message.charAt(0)=='1'?"1":"NONE";
			return result;
		}
		int mes[] = new int[message.length()];
		for ( int i = 0 ; i < message.length() ; ++i ) {
			mes[i] = Integer.parseInt(message.substring(i, i+1));
		}
		int res[] = new int[mes.length];
		res[0] = 0;
		if ( decode(mes,res) ) {
			result[0] = "";
			for ( int i = 0 ; i < res.length ; ++i ) {
				result[0] += ""+res[i];
			}
		}
		else {
			result[0] = "NONE";
		}
		res[0] = 1;
		if ( decode(mes,res) ) {
			result[1] = "";
			for ( int i = 0 ; i < res.length ; ++i ) {
				result[1] += ""+res[i];
			}
		}
		else {
			result[1] = "NONE";
		}
		return result;
	}

	private boolean decode(int[] mes, int[] res) {
		if ( mes[0] == res[0] ) {
			res[1] = 0;
		}
		else {
			if ( mes[0] == res[0]+1 ) {
				res[1] = 1;
			}
			else {
				return false;
			}
		}
		for ( int i = 1 ; i < mes.length-1 ; ++i ) {
//			System.out.println(Arrays.toString(mes));
//			System.out.println(Arrays.toString(res));
			if ( mes[i] == res[i-1]+res[i] ) {
				res[i+1] = 0;
			}
			else {
				if ( mes[i] == res[i-1]+res[i]+1 ) {
					res[i+1] = 1;
				}
				else {
					return false;
				}
			}
		}
		int i = mes.length-1;
		if ( mes[i] != res[i-1]+res[i] ) {
			return false;
		}
		return true;
		
	}

}

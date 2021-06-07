import java.util.Arrays;
import java.util.Scanner;




public class AW {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt(); 
		for ( int w = 0 ; w < t; ++w ) {
			int k = in.nextInt();
			String gates = in.next();
			int num_output = countOutput(k,gates);
			int result[] = new int[num_output];
			for ( int i = 0 ; i < (1<<k) ; ++i ) {
				int inputs[] = new int[k];
				for ( int j = 0 ; j < k ; ++j ) {
					if ( (i&(1<<j)) > 0 ) {
						inputs[j] = 1;
					}
				}
//				System.out.println(Arrays.toString(inputs));
				//
				int outputs[] = calculate(inputs,gates,num_output);
				for ( int j = 0 ; j < num_output ; ++j ) {
					if ( outputs[j] == 1 ) ++result[j];
				}
			}
			System.out.print(result[0]);
			for ( int i = 1 ; i < num_output ; ++i ) {
				System.out.print(","+result[i]);
			}
			System.out.println();
		}
	}

	private static int[] calculate(int[] inputs, String gates, int num_outputs) {
		int value[] = new int[26];
		int next = inputs.length;
		boolean used[] = new boolean[26];
		for ( int i = 0 ; i < inputs.length ; ++i ) {
			value[i] = inputs[i];
		}
		for ( int i = 0 ; i < gates.length() ; i += 2) {
			char a = gates.charAt(i);
			char b = gates.charAt(i+1);
			int vala = 0;
			if ( Character.isUpperCase(a) )
				vala = 1-value[Character.toLowerCase(a)-'a'];
			else 
				vala = value[a-'a'];
			int valb = 0;
			if ( Character.isUpperCase(b) )
				valb = 1-value[Character.toLowerCase(b)-'a'];
			else
				valb = value[b-'a'];
			a = Character.toLowerCase(a);
			b = Character.toLowerCase(b);
			used[a-'a'] = true;
			used[b-'a'] = true;
			if ( a <= b ) { //AND
				value[next] = vala&valb;
			}
			else { //OR
				value[next] = vala|valb;
			}
			++next;
		}
		int res[] = new int[num_outputs];
		int c = 0;
		for ( int i = inputs.length ; i < next ; ++i ) {
			if ( ! used[i] ) res[c++] = value[i]; 
		}
		return res;
	}

	private static int countOutput(int inputs, String gates) {
		int next = inputs;
		boolean used[] = new boolean[26];
		for ( int i = 0 ; i < gates.length() ; i += 2) {
			used[Character.toLowerCase(gates.charAt(i))-'a'] = true;
			used[Character.toLowerCase(gates.charAt(i+1))-'a'] = true;
			++next;
		}
		int count = 0;
		for ( int i = inputs ; i < next ; ++i ) {
			if ( !used[i] ) ++count;
		}
		return count;
	}

}

import javax.xml.soap.Text;


public class TextSimulator {
	
	public static void main(String[] args) {
		TextSimulator t = new TextSimulator();
		System.out.println(t.simulate("+2-1*=*^^"));
	}
	
	public String simulate(String input) {
		char current = 'a';
		String result = "";
		for ( int i = 0 ; i < input.length() ; ++i ) {
			char operator = input.charAt(i);
			if ( operator == '+' ) {
				String number = "";
				++i;
				while ( i < input.length() && Character.isDigit(input.charAt(i)) ) {
					number += input.charAt(i);
					++i;
				}
				int i_number = Integer.parseInt(number);
				--i;
				current += i_number;
				result += current;
				continue;
			}
			if ( operator == '-' ) {
				String number = "";
				++i;
				while ( i < input.length() && Character.isDigit(input.charAt(i)) ) {
					number += input.charAt(i);
					++i;
				}
				int i_number = Integer.parseInt(number);
				--i;
				current -= i_number;
				result += current;		
				continue;
			}
			if ( operator == '*' ) {
				result += current;
				continue;
			}
			if ( operator == '^' ) {
				current = Character.isUpperCase(current)? Character.toLowerCase(current):Character.toUpperCase(current);
			//	result += current;
				continue;
			}
			if ( operator == '=' ) {
				current = Character.isUpperCase(current)?'A':'a';
				continue;
			}
		}
		return result;	
	}

}

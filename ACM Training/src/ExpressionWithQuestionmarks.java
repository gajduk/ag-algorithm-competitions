import java.util.Arrays;
import java.util.Scanner;


public class ExpressionWithQuestionmarks {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String expr = in.nextLine();
		String s_digits = in.nextLine();
		int digits[] = new int[s_digits.length()];
		for ( int i = 0 ; i < s_digits.length() ; ++i ) {
			digits[i] = Integer.parseInt(s_digits.substring(i,i+1));
		}
		Arrays.sort(digits);
//		System.out.println(Arrays.toString(digits));
		expr = "+"+expr;

//		System.out.println(expr);
		for ( int i = 0 ; i < expr.length() ; ++i ) {
				if ( expr.charAt(i) == '(' ) {
					int open = 1;
					for ( int j = i+1 ; j < expr.length() ;++j ) {
						if ( expr.charAt(j) == '(' ) {
							++open;
						}
						if ( expr.charAt(j) == ')' ) {
							--open;
							if ( open == 0 ) {
								if ( expr.charAt(i-1) == '-' ) {
									open = 0;
									for ( int k = i ; k < j ; ++k ) {
										if ( open == 1 ) {
											if ( expr.charAt(k) == '-' ) {
												expr = expr.substring(0,k)+"+"+expr.substring(k+1);
												continue;
											}
											if ( expr.charAt(k) == '+' ) {
												expr = expr.substring(0,k)+"-"+expr.substring(k+1);
											}
										}
										if ( expr.charAt(k) == '(' ) {
											++open;
										}
										if ( expr.charAt(k) == ')' ) {
											--open;
										}
									}
								}
//								System.out.println(expr);
								expr = expr.substring(0,i)+expr.substring(i+1,j)+expr.substring(j+1);

//								System.out.println(expr);
								break;
							}
						}
					}
				}
		}
//		System.out.println(expr);
		String contributing[] = new String [500];
		int counter_contri = 0;
		String undermining[] = new String [500];
		int counter_under = 0;
		for ( int i = 0 ; i < expr.length() ; ++i ) {
			if ( expr.charAt(i) == '-' || expr.charAt(i) == '+' ) {
				int j  = i+1;
				while ( j < expr.length() && expr.charAt(j) != '-' && expr.charAt(j) != '+' ) ++j;
				if ( expr.charAt(i) == '-' ) {
					undermining[counter_under++] = expr.substring(i+1,j);
				}
				if ( expr.charAt(i) == '+' ) {
					contributing[counter_contri++] = expr.substring(i+1,j);
				}
			}
		}
//		System.out.println(Arrays.toString(contributing));
//		System.out.println(Arrays.toString(undermining));
		int counter_used = digits.length;
		while ( true ) {
			int max = -1;
			int index = -1;
			for ( int i = 0 ; i < counter_contri ; ++i ) {
				int t = contributing[i].indexOf('?');
				if ( t > max ) {
					max = t;
					index = i;
				}
			}
			if ( max == -1 ) {
				break;
			}
			contributing[index] = contributing[index].substring(0,max)+Integer.toString(digits[--counter_used])+contributing[index].substring(max+1);
//			System.out.println(Arrays.toString(contributing));
		}
		counter_used = 0;
		while ( true ) {
			int max = -1;
			int index = -1;
			for ( int i = 0 ; i < counter_under ; ++i ) {
				int t = undermining[i].indexOf('?');
				if ( t > max ) {
					max = t;
					index = i;
				}
			}
			if ( max == -1 ) {
				break;
			}
			undermining[index] = undermining[index].substring(0,max)+Integer.toString(digits[counter_used++])+undermining[index].substring(max+1);
//			System.out.println(Arrays.toString(undermining));
		}
		int value = 0;
		for ( int i = 0 ; i < counter_contri ; ++i ) {
			value += Integer.parseInt(contributing[i]);
		}
		for ( int i = 0 ; i < counter_under ; ++i ) {
			value -= Integer.parseInt(undermining[i]);
		}
		System.out.println(value);
	}
}
